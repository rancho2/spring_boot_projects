package com.spring.ai.chatclient.controllers;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ai.chatclient.entity.Tut;
import com.spring.ai.chatclient.service.ChatService;

@RestController
@RequestMapping
public class ChatController {
	/*private ChatClient chatClient;
	public ChatController(ChatClient.Builder builder)
	{
		this.chatClient=builder.build();
	}*/
	private ChatService chatService;
	public ChatController(ChatService chatService)
	{
		this.chatService=chatService;
	}
	/*private ChatClient openAiChatClient;
	private ChatClient ollamaChatClient;*/
	/*public ChatController(@Qualifier("openAi") ChatClient openAiChatClient,@Qualifier("ollama")ChatClient ollamaChatClient)
	{
		this.openAiChatClient=openAiChatClient;
		this.ollamaChatClient=ollamaChatClient;
	}*/
	@GetMapping("/chat")
	public ResponseEntity<String> chat(@RequestParam(value="q") String q)
	{
		//String res=chatClient.prompt(q).call().content();
		//List<Tut> res = chatService.chat(q);
		String res=chatService.chat(q);
		return ResponseEntity.ok(res);
	}
}
