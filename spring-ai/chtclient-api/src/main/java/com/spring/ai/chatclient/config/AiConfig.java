package com.spring.ai.chatclient.config;

import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
	/*@Bean(name="openAi")
	public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel)
	{
		return ChatClient.builder(openAiChatModel).build();
	}
	@Bean(name="ollama")
	public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel)
	{
		return ChatClient.builder(ollamaChatModel).build();
	}*/
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder)
	{
		return builder
				.defaultSystem("You are a helpful coding assistant. You are an expert in coding.")
				.defaultOptions(OpenAiChatOptions.builder()
						.model("gpt-4o-mini")
						.temperature(0.3)
						.maxTokens(200)
						.build())
				.build();
	}
}
