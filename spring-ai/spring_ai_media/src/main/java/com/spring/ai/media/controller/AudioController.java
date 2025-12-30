package com.spring.ai.media.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ai.media.services.AudioService;

@RestController
@RequestMapping("/api/v1/audio")
public class AudioController {
	private final AudioService audioService;
	
	public AudioController(AudioService audioService) {
		super();
		this.audioService = audioService;
	}

	//handler method that will
	//resource folder se [classpath]--->spring ai--->Text
	@PostMapping("/transcript")
	public ResponseEntity<String> transcriptAudio(@Value("${classpath:Recording.m4a}") Resource inputAudio)
	{
		String responseText=audioService.convertAudioToText(inputAudio);
		return ResponseEntity.ok(responseText);
	}
	
	@PostMapping("/transcript-with-options")
	public ResponseEntity<String> transcriptAudioWithOptions(@Value("${classpath:Recording.m4a}") Resource inputAudio)
	{
		String responseText=audioService.convertAudioToTextWithOptions(inputAudio);
		return ResponseEntity.ok(responseText);
	}

	@PostMapping("/text")
	public ResponseEntity<String> speechToText(@RequestParam("audioFile") MultipartFile audioFile)
	{
		String responseText=audioService.convertAudioToTextWithOptions(audioFile.getResource());
		return ResponseEntity.ok(responseText);
	}
	
}
