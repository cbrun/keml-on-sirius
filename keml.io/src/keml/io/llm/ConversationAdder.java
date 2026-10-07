package keml.io.llm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

import keml.Conversation;
import keml.Message;
import keml.ReceiveMessage;
import keml.SendMessage;

public class ConversationAdder {
	
	public static boolean addOriginalConv(Conversation conv, File originalConv) throws IOException {
		ArrayList<Message> kemlMsgs = conv.getAuthor().getMessages().stream()
				.filter(msg -> msg.getCounterPart().getName().equals("LLM")).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<LLMMessage> msgs;
		try{
			msgs = LLMMessage.readFile(Path.of(originalConv.getPath()));
		} catch(IOException e) {
			System.err.println("Cannot read " + originalConv + ". I will not add the original conversation information to " + conv.getTitle() +".keml");
			return false;
		}
		if (msgs.size() != kemlMsgs.size()) {
			System.err.println("The size of original messages ("+ msgs.size() + ") and keml messages with LLM ("+ kemlMsgs.size() + ") do not fit.");
		}
		for(int i=0; i<msgs.size(); i++) {
			Message kemlM = kemlMsgs.get(i);
			LLMMessage msg = msgs.get(i);
			if(kemlM instanceof SendMessage && msg.getAuthor().equals("Author") ||
					kemlM instanceof ReceiveMessage && msg.getAuthor().equals("LLM"))
				kemlM.setOriginalContent(msg.getMessage());
			else
				System.err.println("No match on "+msg +" and " + kemlM);
		}
		return true;
	}

}
