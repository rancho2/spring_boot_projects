package com.embed.project.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{
	private VectorStore vectorStore;
	
	public ChatServiceImpl(VectorStore vectorStore) {
		super();
		this.vectorStore = vectorStore;
	}

	@Override
	public void saveData(List<String> list) {
		List<Document> list2 = list.stream().map(Document::new).toList();
		vectorStore.add(list2);
	}

}
