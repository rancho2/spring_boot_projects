package com.docker.model.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.docker.model.example.services.ChatService;

@RestController
@RequestMapping
public class ChatController {
	@Autowired
	private ChatService chatService;
	@GetMapping("/chat")
	public ResponseEntity<String> chat(@RequestParam("q") String q)
	{
		return ResponseEntity.ok(chatService.chat(q));
	}
}
