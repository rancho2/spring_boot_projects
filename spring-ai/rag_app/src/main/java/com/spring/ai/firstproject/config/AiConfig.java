package com.spring.ai.firstproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
//import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.ai.firstproject.advisors.TokenPrintAdvisor;

@Configuration
public class AiConfig {
	private Logger logger=LoggerFactory.getLogger(AiConfig.class);
	/*@Bean
	public ChatMemory chatMemory(JdbcChatMemoryRepository chatMemoryRepository)
	{
		return MessageWindowChatMemory.builder()
				.chatMemoryRepository(chatMemoryRepository).build();
	}*/
	/*@Bean(name="openAiChatClient")
	public ChatClient openAiChatModel(OpenAiChatModel chatModel)
	{
		return ChatClient.builder(chatModel).build();
	}
	@Bean(name="ollamaChatClient")
	public ChatClient ollamaChatModel(OllamaChatModel chatModel)
	{
		return ChatClient.builder(chatModel).build();
	}*/
	@Bean
	public ChatMemory chatMemory()
	{
		InMemoryChatMemoryRepository chatMemoryRepository = new InMemoryChatMemoryRepository();
		return MessageWindowChatMemory.builder().maxMessages(10).
				chatMemoryRepository(chatMemoryRepository)
				.build();
	}
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder,ChatMemory chatMemory)
	{
		logger.info("ChatMemoryImplementation class:"+chatMemory.getClass().getName());
		MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
		return builder
				.defaultAdvisors(messageChatMemoryAdvisor,new SimpleLoggerAdvisor())
				//.defaultSystem("You are a helpful coding assistant. You are an expert in coding.")
				.defaultOptions(OpenAiChatOptions.builder()
						.model("gpt-4o-mini")
						.temperature(0.3)
						.maxTokens(200)
						.build()
						).build();
	}
}
