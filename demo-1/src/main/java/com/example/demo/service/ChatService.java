package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageGeneration;
//import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.demo.payload.CricketResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

@Service
public class ChatService {
	@Autowired
	private ChatModel chatModel;
	/*@Autowired
	private ImageModel imageModel;*/
	@Autowired
	private StreamingChatModel streamingChatModel;
	public String generateResponse(String inputText) {
		//you are using ai
		String response = chatModel.call(inputText);
		return response;
	}
	public Flux<String> streamResponse(String inputText) {
		//you are using ai
		Flux<String> response = chatModel.stream(inputText);
		return response;
	}
	public CricketResponse generateCricketResponse(String inputText) throws IOException
	{
		//String promptString="";
		String s = this.loadPromptTemplate("prompts/cricket_bot.txt");
		String promptTemplate = this.putValuesInPromptTemplate(s, Map.of("inputText",inputText));
		ChatResponse cricketResponse = chatModel.call(
				new Prompt(promptTemplate)
				);
		String responseString=cricketResponse.getResult().getOutput().getText();
		//System.out.println(responseString);
		ObjectMapper mapper=new ObjectMapper();
		CricketResponse value = mapper.readValue(responseString, CricketResponse.class);
		return value;
	}
	//ollama cannot generate image.so we are unable to generate image.
	/*public List<String> generateImages(String imageDesc,String size,int numbers) throws IOException
	{
		String s = this.loadPromptTemplate("prompts/image_bot.txt");
		String promptString = this.putValuesInPromptTemplate(s, Map.of("numberOfImages",numbers+"","description",imageDesc,"size",size));
	    ImageResponse imageResponse = imageModel.call(new ImagePrompt(promptString,
	    		ImageOptionsBuilder.builder().height(512).width(512).build()));
	    //String imageResponse = chatModel.call(promptString);
	    /*ChatResponse response = chatModel.call(new Prompt(this.promptString,
	            OllamaOptions.builder().model(OllamaModel.LLAVA)).build());*/
	   /* List<String> imageUrls = imageResponse.getResults().stream().map(generation->generation.getOutput().getUrl()).collect(Collectors.toList());
	    return imageUrls;
	}*/
	//load prompt from classpath
	public String loadPromptTemplate(String fileName) throws IOException
	{
		Path filePath = new ClassPathResource(fileName).getFile().toPath();
		return Files.readString(filePath);
	}
	//put values to prompt
	public String putValuesInPromptTemplate(String template,Map<String, String> variables)
	{
		for(Map.Entry<String,String> entry:variables.entrySet())
		{
			template = template.replace("{"+entry.getKey()+"}", entry.getValue());
		}
		return template;
	}
}
