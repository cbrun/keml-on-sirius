package keml.io.llm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LLMMessage {
	
	private String author;
	private String message;
	
	public LLMMessage() {
		super();
	}
	
	public LLMMessage(String author, String message) {
		super();
		this.author = author;
		this.message = message;
	}


	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	@Override
	public String toString() {
		return "LLMMessage [author=" + author + ", message=" + message + "]";
	}
	
	public static void writeFile(List<LLMMessage> content, File file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(file, mapper);
	}

	public static ArrayList<LLMMessage> readFile(Path path) throws IOException {
		String content = Files.readString(path);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(content, new TypeReference<ArrayList<LLMMessage>>(){});
	}

}
