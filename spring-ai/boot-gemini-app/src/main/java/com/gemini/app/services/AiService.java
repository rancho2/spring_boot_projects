package com.gemini.app.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {
	private ChatClient chatClient;
	
	public AiService(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	public String getResponseFromAI(String prompt)
	{
		String content = chatClient.prompt(prompt)
		.call().content();
		return content;
	}
}
