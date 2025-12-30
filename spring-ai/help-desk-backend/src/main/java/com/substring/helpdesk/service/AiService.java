package com.substring.helpdesk.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.substring.helpdesk.tools.EmailTool;
import com.substring.helpdesk.tools.TicketDatabaseTool;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AiService {
	private final ChatClient chatClient;
	private final TicketDatabaseTool ticketDatabaseTool;
	@Value("classpath:helpdesk-system.st")
	private Resource systemPromptResource;
	private final EmailTool emailTool;
	public AiService(ChatClient chatClient, TicketDatabaseTool ticketDatabaseTool,EmailTool emailTool) {
		super();
		this.chatClient = chatClient;
		this.ticketDatabaseTool = ticketDatabaseTool;
		this.emailTool=emailTool;
	}
	public String getResponseFromAssistant(String query,String conversationId)
	{
		return this.chatClient.prompt()
				.advisors(advisorSpec->advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
				.tools(ticketDatabaseTool,emailTool)
				.system(systemPromptResource)
				.user(query)
				.call()
				.content();
	}
	public Flux<String> streamResponseFromAssistant(String query,String conversationId)
	{
		return this.chatClient.prompt()
				.advisors(advisorSpec->advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
				.tools(ticketDatabaseTool,emailTool)
				.system(systemPromptResource)
				.user(query)
				.stream()
				.content();		
	}
}
