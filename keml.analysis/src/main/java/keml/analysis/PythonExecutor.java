package keml.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import keml.analysis_server.utils.ExecutionMode;

public class PythonExecutor {

	public static boolean runPythonScript(String filePath, String fileName, ExecutionMode executionMode) {
		BufferedReader reader = null;
		try {
			List<String> commands = new ArrayList<>();
			commands.add("python3");
			switch(executionMode) {
			case STANDARD:
				commands.add("src/main/java/keml/analysis/py/main.py");
				break;
			case JAR:
				commands.add("../src/main/java/keml/analysis/py/main.py");
				break;
			case DOCKER_JAR:
				commands.add("/app/python-scripts/main.py");
				break;
			}		
			commands.add(filePath);
			commands.add(fileName);
			ProcessBuilder pb = new ProcessBuilder(commands);
			pb.redirectErrorStream(true);
			Process process = pb.start();
			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			int exitCode = process.waitFor();
			return exitCode == 0;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
