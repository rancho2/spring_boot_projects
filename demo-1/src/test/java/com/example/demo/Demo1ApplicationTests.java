package com.example.demo;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.payload.CricketResponse;
import com.example.demo.service.ChatService;

@SpringBootTest
class Demo1ApplicationTests {
	@Autowired
	private ChatService chatService;
	@Test
	void testResponse() throws IOException {
		CricketResponse cricketResponse = chatService.generateCricketResponse("Who is Sachin?");
		System.out.println(cricketResponse);
	}
	@Test
	void testTemplate() throws IOException
	{
		String promptTemplate = chatService.loadPromptTemplate("prompts/cricket_bot.txt");
		String putValuesInPromptTemplate = chatService.putValuesInPromptTemplate(promptTemplate, Map.of("inputText","What is Cricket"));
		System.out.println(putValuesInPromptTemplate);
		//System.out.println(promptTemplate);
	}
}
