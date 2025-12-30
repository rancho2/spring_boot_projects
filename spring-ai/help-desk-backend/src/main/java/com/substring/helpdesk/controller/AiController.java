package com.substring.helpdesk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.substring.helpdesk.service.AiService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/helpdesk")
@CrossOrigin("http://localhost:5173")
public class AiController {
	private final AiService service;

	public AiController(AiService service) {
		super();
		this.service = service;
	}
	@PostMapping
	public ResponseEntity<String> getResponse(@RequestBody String query, @RequestHeader("ConversationId") String conversationId)
	{
		return ResponseEntity.ok(service.getResponseFromAssistant(query,conversationId));
	}
	@PostMapping(value="/stream")
	public Flux<String> streamResponse(@RequestBody String query, @RequestHeader("ConversationId") String conversationId)
	{
		return service.streamResponseFromAssistant(query,conversationId);
	}
}
