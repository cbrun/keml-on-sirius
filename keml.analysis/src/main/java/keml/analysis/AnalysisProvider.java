package keml.analysis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.LocaleUtil;
import org.eclipse.emf.ecore.util.EcoreUtil;

import keml.Conversation;
import keml.Information;
import keml.NewInformation;
import keml.analysis_server.utils.ExecutionMode;
import keml.io.KemlFileHandler;

public class AnalysisProvider {

	/**
	 * Writes the two CSV reports and nine trust workbooks for a live Sirius model.
	 * TrustEvaluator changes trust attributes: copy the complete conversation so report
	 * generation never changes the user's document, including when evaluation fails.
	 * The caller owns the output directory and its eventual cleanup/download.
	 */
	public void writeReports(Conversation conversation, Path outputDirectory) throws IOException {
		validate(conversation);
		Conversation copy = EcoreUtil.copy(conversation);
		Files.createDirectories(outputDirectory);
		String prefix = outputDirectory.resolve("conversation").toString();
		new ConversationAnalyser(copy).createCSVs(prefix);
		writeTrustReports(copy, prefix);
	}

	private static void validate(Conversation conversation) {
		if (conversation == null || conversation.getAuthor() == null) {
			throw new IllegalArgumentException("Analysis requires a conversation with an Author.");
		}
		// The upstream tables and scenarios identify partners by name.
		var names = new HashSet<String>();
		for (var partner : conversation.getConversationPartners()) {
			String name = partner.getName();
			if (name == null || name.isBlank() || "Author".equals(name) || !names.add(name)) {
				throw new IllegalArgumentException("Analysis requires distinct, non-blank partner names other than Author.");
			}
		}
		for (var message : conversation.getAuthor().getMessages()) {
			if (message.getCounterPart() == null || !conversation.getConversationPartners().contains(message.getCounterPart())) {
				throw new IllegalArgumentException("Every message must reference a participant in this conversation.");
			}
		}
		var information = Stream.concat(conversation.getAuthor().getPreknowledge().stream(),
				ConversationAnalyser.getNewInfos(ConversationAnalyser.getReceives(conversation)).stream()).toList();
		for (var item : information) {
			if (item.getCauses().stream().anyMatch(link -> !information.contains(link.getTarget()))) {
				throw new IllegalArgumentException("Every argument must target knowledge in this conversation.");
			}
		}
	}

	/** Browser results contain values only; no mutable EMF objects escape the library. */
	public record Results(String title, List<ParticipantCounts> overview, int repetitions,
			List<String> argumentHeaders, String[][] arguments, List<Knowledge> knowledge,
			List<TrustScenario> trustScenarios) { }
	public record ParticipantCounts(String participant, long sent, long received, long interrupted,
			long facts, long instructions) { }
	public record Knowledge(String text, String participant, boolean instruction, boolean preknowledge,
			Float recordedImmediately, Float recordedAfterwards) { }
	public record TrustScore(float initial, float computed) { }
	public record TrustScenario(int weight, String preset, List<TrustScore> scores) { }

	/** Reuse the report calculations, including all 36 existing trust scenarios. */
	public Results analyse(Conversation conversation) {
		validate(conversation);
		var copy = EcoreUtil.copy(conversation);
		var analyser = new ConversationAnalyser(copy);
		var messages = analyser.countMessages();
		var informationCounts = analyser.countInformationByPartner();
		var names = new ArrayList<>(ConversationAnalyser.getPartnerNames(copy));
		names.add("Author");
		var overview = names.stream().map(name -> new ParticipantCounts(name,
				messages.get("received").getOrDefault(name, 0L), messages.get("sent").getOrDefault(name, 0L),
				messages.get("interrupted").getOrDefault(name, 0L),
				informationCounts.get(ConversationAnalyser.InformationType.FACT).getOrDefault(name, 0L),
				informationCounts.get(ConversationAnalyser.InformationType.INSTRUCTION).getOrDefault(name, 0L))).toList();
		// In the report, partner message counts mean Author -> partner and partner -> Author.
		var author = overview.getLast();
		overview = new ArrayList<>(overview);
		overview.set(overview.size() - 1, new ParticipantCounts("Author",
				messages.get("sent").values().stream().mapToLong(Long::longValue).sum(),
				messages.get("received").values().stream().mapToLong(Long::longValue).sum(),
				messages.get("interrupted").values().stream().mapToLong(Long::longValue).sum(), author.facts(), author.instructions()));
		List<Information> items = Stream.concat(copy.getAuthor().getPreknowledge().stream(),
				ConversationAnalyser.getNewInfos(ConversationAnalyser.getReceives(copy)).stream())
				.map(Information.class::cast).toList();
		var knowledge = items.stream().map(item -> new Knowledge(item.getMessage(),
				item instanceof NewInformation info ? info.getSourceConversationPartner().getName() : "Author",
				item.isIsInstruction(), !(item instanceof NewInformation),
				finite(item.getFeltTrustImmediately()), finite(item.getFeltTrustAfterwards()))).toList();
		var scenarios = new ArrayList<TrustScenario>();
		for (int weight = 2; weight <= 10; weight++) {
			var evaluator = new TrustEvaluator(copy, weight);
			for (var preset : TrustEvaluator.standardTrustConfigurations(copy.getConversationPartners())) {
				var scores = evaluator.analyse(preset.getValue1(), 1F);
				scenarios.add(new TrustScenario(weight, preset.getValue0(), items.stream()
						.map(item -> new TrustScore(scores.get(item).getValue0(), scores.get(item).getValue1())).toList()));
			}
		}
		return new Results(copy.getTitle(), overview, analyser.countRepetitions(),
				List.of(analyser.infoAnalyser.headers()), analyser.infoAnalyser.connectionMatrix(), knowledge, scenarios);
	}

	private static Float finite(Float value) {
		return value != null && Float.isFinite(value) ? value : null;
	}

	private static void writeTrustReports(Conversation conversation, String prefix) throws IOException {
		Locale previous = LocaleUtil.getUserLocale();
		try {
			LocaleUtil.setUserLocale(Locale.US);
			for (int weight = 2; weight <= 10; weight++) {
				new TrustEvaluator(conversation, weight).writeRowAnalysis(prefix + "-w" + weight + "-",
						TrustEvaluator.standardTrustConfigurations(conversation.getConversationPartners()), 1.0F);
			}
		} finally {
			LocaleUtil.setUserLocale(previous);
		}
	}

	public static String runAnalysis(Path json, boolean runFurtherAnalysis, String basePath, ExecutionMode executionMode) throws IOException {
		Path source = json.toAbsolutePath();
		Conversation conv = new KemlFileHandler().loadKemlJSON(source.toString());
		String fileName = FilenameUtils.removeExtension(source.getFileName().toString());
		String dirPath = basePath + "/analysis/" + fileName;
		Path dir = Paths.get(dirPath);
		Files.createDirectories(dir);
		String filePath = dirPath + "/" + fileName;
		new ConversationAnalyser(conv).createCSVs(filePath);
		writeTrustReports(conv, filePath);
		if (runFurtherAnalysis) {
			boolean success = PythonExecutor.runPythonScript(dirPath, fileName, executionMode);
			if (!success) {
				throw new IOException("Failed to execute python script");
			}
		}
		return dirPath;
	}
	
	public static void main(String[] args) throws IOException {
		String folder;
		File file;
		File sourceFolder;
		boolean runFurtherAnalysis = false;
		if (args.length == 0) {
			folder = "../keml.sample/introductoryExamples";
			sourceFolder = new File(folder + "/keml/");
			file = sourceFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"))[0];
		} else if (args.length == 1){
			runFurtherAnalysis = Boolean.parseBoolean(args[0]);
			folder = "../keml.sample/introductoryExamples";
			sourceFolder = new File(folder + "/keml/");
			file = sourceFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"))[0];
		} else if (args.length == 2){
			runFurtherAnalysis = Boolean.parseBoolean(args[0]);
			folder = args[1];
			sourceFolder = new File(folder + "/keml/");
			file = sourceFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"))[0];
		} else {
			runFurtherAnalysis = Boolean.parseBoolean(args[0]);
			folder = args[1];
			sourceFolder = new File(folder + "/keml/");
			file = new File(sourceFolder.getAbsolutePath() + "/" + args[2]);
		}
		runAnalysis(file.toPath(), runFurtherAnalysis, folder, ExecutionMode.STANDARD);
	}
}
