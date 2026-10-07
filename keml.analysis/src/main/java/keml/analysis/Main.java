package keml.analysis;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.LocaleUtil;

import keml.Conversation;
import keml.analysis_server.utils.ExecutionMode;
import keml.io.KemlFileHandler;

public class Main {

	public static void main(String[] args) throws Exception {

		String folder;
		boolean runFurtherAnalysis = false;
		if (args.length == 0) {
			folder = "../keml.sample/introductoryExamples";
		} else if (args.length == 1){
			runFurtherAnalysis = Boolean.parseBoolean(args[0]);
			folder = "../keml.sample/introductoryExamples";
		} else {
			runFurtherAnalysis = Boolean.parseBoolean(args[0]);
			folder = args[1];			
		}

		File sourceFolder = new File(folder + "/keml/");
		File targetFolder = new File(folder + "/analysis/");

		// if directory contains .keml but no ../analysis/
		if (sourceFolder.exists() && !targetFolder.exists()) {
			targetFolder.mkdirs();
		}

		System.out.println("You started the KEML analysis.\n I will read KEML files from " + folder
				+ ".\n I will write the resulting files into " + targetFolder);

		File[] files = sourceFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".keml"));

		for (File file : files) {
			try {
				String source = file.getAbsolutePath();
				Conversation conv = new KemlFileHandler().loadKeml(source);

				String fileName = FilenameUtils.removeExtension(file.getName());
				String basePath = targetFolder + "/" + fileName;

				new ConversationAnalyser(conv).createCSVs(basePath);
				LocaleUtil.setUserLocale(Locale.US);

				for (int i = 2; i <= 10; i++) {
					TrustEvaluator trusty = new TrustEvaluator(conv, i);
					trusty.writeRowAnalysis(basePath + "-w" + i + "-",
							TrustEvaluator.standardTrustConfigurations(conv.getConversationPartners()), 1.0F);
				}
				if (runFurtherAnalysis) {
					boolean success = PythonExecutor.runPythonScript(targetFolder.getAbsolutePath(), fileName, ExecutionMode.STANDARD);
					if (!success) {
						throw new IOException("Failed to execute python script");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
