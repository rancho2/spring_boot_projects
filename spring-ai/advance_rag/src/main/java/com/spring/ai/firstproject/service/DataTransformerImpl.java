package com.spring.ai.firstproject.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;
@Service
public class DataTransformerImpl implements DataTransformer{

	@Override
	public List<Document> transform(List<Document> documents) {
		TokenTextSplitter splitter = new TokenTextSplitter(300,400,10,5000,true);
		List<Document> transformed = splitter.transform(documents);
		return transformed;
	}

}
