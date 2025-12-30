package com.gemini.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gemini.app.services.AiService;

@RestController
@RequestMapping("/ai")
public class AiController {
	private final AiService aiService;
	
	public AiController(AiService aiService) {
		super();
		this.aiService = aiService;
	}

	@GetMapping
	public ResponseEntity<String> getResponseFromAI(@RequestParam("query") String query)
	{
		//String response=aiService.getResponseFromAI("Hey, how are you, can you define http protocols with example");
		String response=aiService.getResponseFromAI(query);
		return ResponseEntity.ok(response);
	}
}
