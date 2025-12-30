package com.spring.ai.firstproject;

import java.io.IOError;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.ai.firstproject.helper.Helper;
import com.spring.ai.firstproject.service.ChatService;
import com.spring.ai.firstproject.service.DataLoader;
import com.spring.ai.firstproject.service.DataTransformer;

@SpringBootTest
class FirstProjectApplicationTests {
	@Autowired
	private DataLoader dataLoader;
	@Autowired
	private DataTransformer dataTransformer;
	@Autowired
	private VectorStore vectorStore;
	/*@Test
	void testDataLoader()
	{
		var documents = dataLoader.loadDocumentsFromJson();
		System.out.println(documents.size());
		documents.forEach(item->{
			System.out.println(item);
		}
		);
	}*/
	@Test
	void testPdfDataLoader()
	{
		List<Document> documents = dataLoader.loadDocumentsFromPdf();
		System.out.println(documents.size());
		documents.forEach(item->{
			System.out.println(item);
			System.out.println("_____________________________________");
			});
		System.out.println("Data read....now going to transform");
		List<Document> transformedDocument = this.dataTransformer.transform(documents);
		System.out.println(transformedDocument.size());
		transformedDocument.forEach(item->{
			System.out.println(item);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		});
		System.out.println("Going to save the data into database");
		vectorStore.add(transformedDocument);
		System.out.println("Done with saving data in db");
	}
}
