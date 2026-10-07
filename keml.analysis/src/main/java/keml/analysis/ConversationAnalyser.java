package keml.analysis;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import keml.Conversation;
import keml.Message;
import keml.NewInformation;
import keml.ReceiveMessage;
import keml.SendMessage;

public class ConversationAnalyser {

	Conversation conv;
	static String auth = "Author";
	List<String> partners; // works as headers
	List<ReceiveMessage> receives; // starting point for work on knowledge part
	InformationPartAnalyser infoAnalyser;

	public ConversationAnalyser(Conversation conv) {
		this.conv = conv;
		this.partners = getPartnerNames(conv); // works as header row
		this.receives = getReceives(conv);
		infoAnalyser = new InformationPartAnalyser(partners, receives, conv.getAuthor().getPreknowledge());
	}

	public static List<String> getPartnerNames(Conversation conv) {
		return conv.getConversationPartners().stream().map(s -> s.getName()).toList();
	}

	public static List<ReceiveMessage> getReceives(Conversation conv) {
		return conv.getAuthor().getMessages().stream().filter(m -> m instanceof ReceiveMessage).map(ms -> {
			return (ReceiveMessage) ms;
		}).toList();
	}

	public static List<NewInformation> getNewInfos(List<ReceiveMessage> receives) {
		return receives.stream().map(m -> m.getGenerates()).flatMap(List::stream).toList();
	}

	public void createCSVs(String basePath) throws IOException {
		writeGeneralCSV(basePath + "-general.csv");
		writeArgumentationCSV(basePath + "-arguments.csv");
	}

	public void writeGeneralCSV(String path) throws IOException {

		BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			csvPrinter.printRecord("MessagePart");
			printPartnerHeaderRow(csvPrinter);
			writeMessageCounts(csvPrinter);
			csvPrinter.printRecord();

			csvPrinter.printRecord("KnowledgePart");
			csvPrinter.print("Information");
			ArrayList<String> headers = new ArrayList<String>(partners);
			headers.add("Author");
			csvPrinter.printRecord(headers);
			HashMap<InformationType, Map<String, Long>> newInfos = countInformationByPartner();
			// Long allNew =
			// newInfos.get(InformationType.OVERALL).values().stream().collect(Collectors.reducing(0L,
			// (x, y) -> Long.sum(x, y)));
			// csvPrinter.printRecord("NewInformation", allNew);
			// printPartnerHeaderRow(csvPrinter);
			// writeForPartners(newInfos.get(InformationType.OVERALL), "All", csvPrinter,
			// 0L);
			writeForPartners(newInfos.get(InformationType.FACT), "Facts", csvPrinter, 0L, true);
			writeForPartners(newInfos.get(InformationType.INSTRUCTION), "Instructions", csvPrinter, 0L, true);
			// csvPrinter.printRecord("Trust:");
			csvPrinter.printRecord("Repetitions", countRepetitions());
			csvPrinter.flush();
		}
		System.out.println("Wrote general analysis to " + path);
	}

	public void writeArgumentationCSV(String path) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			infoAnalyser.writeInformationConnections(csvPrinter);
			csvPrinter.flush();
		}
		System.out.println("Wrote argumentation analysis to " + path);
	}

	private void printPartnerHeaderRow(CSVPrinter csvPrinter) throws IOException {
		csvPrinter.print("");
		csvPrinter.printRecord(partners);
	}

	enum InformationType {
		INSTRUCTION, FACT, OVERALL;
	}

	HashMap<InformationType, Map<String, Long>> countInformationByPartner() {
		List<String> instructions = new ArrayList<String>();
		List<String> facts = new ArrayList<String>();
		List<String> overall = new ArrayList<String>(); // could be computed from the other two if we ever ran into
														// memory issues

		receives.forEach(msg -> {
			String partner = msg.getCounterPart().getName();
			msg.getGenerates().forEach(info -> {
				overall.add(partner);
				if (info.isIsInstruction()) {
					instructions.add(partner);
				} else {
					facts.add(partner);
				}
			});
		});

		Map<String, Long> factsMap = countFromStringStream(facts.stream());
		Map<String, Long> instrMap = countFromStringStream(instructions.stream());
		Map<String, Long> overallMap = countFromStringStream(overall.stream());

		// *** add preknowledge (as Author) ***
		long allPre = conv.getAuthor().getPreknowledge().size();
		long instructionsPre = conv.getAuthor().getPreknowledge().stream().filter(p -> p.isIsInstruction()).count();
		factsMap.put(auth, allPre - instructionsPre);
		instrMap.put(auth, instructionsPre);
		overallMap.put(auth, allPre);

		// ** combine Maps **
		HashMap<InformationType, Map<String, Long>> res = new HashMap<InformationType, Map<String, Long>>();
		res.put(InformationType.FACT, factsMap);
		res.put(InformationType.INSTRUCTION, instrMap);
		res.put(InformationType.OVERALL, overallMap);
		return res;
	}

	Integer countRepetitions() {
		return receives.stream().mapToInt(s -> s.getRepeats().size()).sum();
	}

	private void writeMessageCounts(CSVPrinter csvPrinter) throws IOException {
		var counts = countMessages();
		writeForPartners(counts.get("sent"), "SendMsg", csvPrinter, 0L, false);
		writeForPartners(counts.get("received"), "ReceiveMsg", csvPrinter, 0L, false);
		writeForPartners(counts.get("interrupted"), "Interrupted", csvPrinter, 0L, false);
	}

	Map<String, Map<String, Long>> countMessages() {
		Map<Boolean, List<Message>> isSend = conv.getAuthor().getMessages().stream()
				.collect(Collectors.partitioningBy(m -> m instanceof SendMessage));
		var sent = countByName(isSend.get(true));
		var receive = countByName(isSend.get(false));
		var interrupted = countByName(isSend.get(false).stream().filter(msg -> {
			ReceiveMessage m = (ReceiveMessage) msg;
			return m.isIsInterrupted();
		}).toList());
		return Map.of("sent", sent, "received", receive, "interrupted", interrupted);
	}

	private static Map<String, Long> countByName(List<Message> msgs) {
		return msgs.stream().collect(Collectors.groupingBy(s -> s.getCounterPart().getName(), Collectors.counting()));
	}

	private static Map<String, Long> countFromStringStream(Stream<String> stream) {
		return stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	// write according to header line partners into line
	private <T> void writeForPartners(Map<String, T> content, String firstColumn, CSVPrinter csvPrinter, T defaultValue,
			boolean withAuthor) throws IOException {
		csvPrinter.print(firstColumn);
		partners.forEach(p -> {
			try {
				csvPrinter.print(content.getOrDefault(p, defaultValue));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		if (withAuthor) {
			csvPrinter.print(content.getOrDefault(auth, defaultValue));
		}
		csvPrinter.printRecord();
	}

}
