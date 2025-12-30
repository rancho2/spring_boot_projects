package com.docker.model.example.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.docker.model.example.tools.SimpleDateTimeTool;
import com.docker.model.example.tools.WeatherTool;

@Service
public class ChatService {
	private ChatClient chatClient;
	private WeatherTool weatherTool;
	public ChatService(ChatClient chatClient,WeatherTool weatherTool)
	{
		this.chatClient=chatClient;
		this.weatherTool=weatherTool;
	}
	public String chat(String q)
	{
		return chatClient
				.prompt()
				.tools(new SimpleDateTimeTool(),weatherTool)
				.user(q)
				.call()
				.content();
	}
}
