package keml.io.llm;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class ChatGPTReader {
		
	// reads a conversations.json to generate single jsons per conversation, just reduced to question and answer, incl. interrupts
	public void split(String file, String target) throws IOException {
		
		Files.createDirectories(Paths.get(target));
		
		String content = Files.readString(Path.of(file));
		JSONArray json = new JSONArray(content);
		for (int i=0; i< json.length(); i++) {
			JSONObject obj = json.getJSONObject(i);
			String title = obj.getString("title");
			
			String targetFile = target+java.net.URLEncoder.encode(title, "UTF-8")+".json";
			Path targetPath = Paths.get(targetFile);
			
			List<LLMMessage> conversation = convertConversation(obj.getJSONObject("mapping"), obj.getString("current_node"));
			Files.writeString(targetPath, new JSONArray(conversation).toString());
		}
		System.out.println("Wrote "+json.length()+" json conversation files.");
	}
	
	private LinkedList<LLMMessage> convertConversation(JSONObject mapping, String start) {
		LinkedList<LLMMessage> msgs = new LinkedList<LLMMessage>();
		String currentId = start;
		while (currentId != null ) {
			JSONObject node = mapping.getJSONObject(currentId);
			if (!(node.get("message").equals(null)) &&
                  node.getJSONObject("message").getJSONObject("content") != null &&
                  node.getJSONObject("message").getJSONObject("content").getString("content_type").equals("text") &&
                  node.getJSONObject("message").getJSONObject("content").getJSONArray("parts").length() > 0 &&
                  node.getJSONObject("message").getJSONObject("content").getJSONArray("parts").getString(0).length() > 0) {
				LLMMessage msg = new LLMMessage(
						readAuthor(node.getJSONObject("message").getJSONObject("author")),
						node.getJSONObject("message").getJSONObject("content").getJSONArray("parts").getString(0)
				);
				msgs.add(msg);
			}
			Object temp = node.get("parent");
			if (temp.equals(null)) {
				currentId = null;
			} else {
				currentId = node.getString("parent");				
			}
		}
		return msgs.reversed();	
	}
	
	private String readAuthor(JSONObject author) {
		String role = author.getString("role");
		if (role.equals("assistant")) {
			return "LLM";
		} else return "Author"; // todo there is one more to distinguish on OpenAI itself about system messages (see html from download)	
	}	 

}
