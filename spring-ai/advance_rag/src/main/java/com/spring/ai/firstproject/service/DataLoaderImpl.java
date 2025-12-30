package com.spring.ai.firstproject.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Service
public class DataLoaderImpl implements DataLoader{
	@Value("classpath:sample_data.json")
	private Resource jsonResource;
	@Value("classpath:cricket_rules.pdf")
	private Resource pdfResource;
	@Override
	public List<Document> loadDocumentsFromJson() {
		System.out.println("Started loading json");
		var jsonReader=new JsonReader(jsonResource,"project");
		List<Document> list = jsonReader.read();
		return list;
	}

	@Override
	public List<Document> loadDocumentsFromPdf() {
		var pdfReader=new PagePdfDocumentReader(pdfResource,
				PdfDocumentReaderConfig.builder()
				.withPageTopMargin(0)
				.withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
						.withNumberOfTopTextLinesToDelete(0)
						.build())
				.withPagesPerDocument(1)
				.build());
		return pdfReader.read();
	}

}
