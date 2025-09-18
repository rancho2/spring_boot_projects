package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.payload.CricketResponse;
import com.example.demo.service.ChatService;

import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/api/v1/chat")
@CrossOrigin("*")
public class ChatController {
	@Autowired
	private ChatService chatService;
	@GetMapping
	@ResponseBody
	public ResponseEntity<String> generateResponse(@RequestParam(value="inputText") String inputText)
	{
		String responseText=chatService.generateResponse(inputText);
		return ResponseEntity.ok(responseText);
	}
	//use stream for heavy response
	@GetMapping("/stream")
	public Flux<String> streamResponse(@RequestParam(value="inputText") String inputText)
	{
		Flux<String> responseText=chatService.streamResponse(inputText);
		return responseText;
	}
	@GetMapping("/cricketbot")
	public ResponseEntity<CricketResponse> getCricketResponse(@RequestParam("inputText") String inputText) throws IOException
	{
		CricketResponse cricketResponse = chatService.generateCricketResponse(inputText);
		return ResponseEntity.ok(cricketResponse);
	}
	/*@GetMapping
	public ResponseEntity<List<String>> generateImages(@RequestParam("imageDesc") String imageDesc,
			@RequestParam(value="size",required=false,defaultValue="512x512") String size,
			@RequestParam(value="numbers",required=false,defaultValue="2") int numbers) throws IOException
	{
		return ResponseEntity.ok(chatService.generateImages(imageDesc, size, numbers));
	}*/
}
