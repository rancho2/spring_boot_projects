package com.substring.helpdesk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
	Logger logger=LoggerFactory.getLogger(getClass());
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder, JdbcChatMemoryRepository jdbcChatMemoryRepository)
	//public ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory)
	{
		//chat memory create kar sakte hai
		var chatMemory = MessageWindowChatMemory
						.builder()
						.chatMemoryRepository(jdbcChatMemoryRepository)
						.build();
		logger.info("ChatClient has been created.");
		logger.info("Chat memory has been created. {}",chatMemory.getClass().getName());
		return builder
				.defaultSystem("Summarize the response within 400 words.")
				.defaultAdvisors(new SimpleLoggerAdvisor(),MessageChatMemoryAdvisor.builder(chatMemory).build())
				.build();
	}
}
