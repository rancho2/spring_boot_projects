package com.spring.ai.media.services;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AudioService {
	private final TranscriptionModel transcriptionModel;
	
	public AudioService(TranscriptionModel transcriptionModel) {
		super();
		this.transcriptionModel = transcriptionModel;
	}

	public String convertAudioToText(Resource inputAudio) {
		//speech to text
		String transcribe = transcriptionModel.transcribe(inputAudio);
		return transcribe;
	}

	public String convertAudioToTextWithOptions(Resource inputAudio) {
		String transcribe = transcriptionModel.transcribe(inputAudio, OpenAiAudioTranscriptionOptions.builder()
				.language("en")
				.temperature(0.7f)
				.prompt("Spring boot")
				.build());
		return transcribe;
	}

}
