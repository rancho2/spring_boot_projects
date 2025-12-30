package com.spring.ai.firstproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.join.ConcatenationDocumentJoiner;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.spring.ai.firstproject.advisors.TokenPrintAdvisor;

import reactor.core.publisher.Flux;
@Service
public class ChatServiceImpl implements ChatService{
	private Logger logger=LoggerFactory.getLogger(ChatServiceImpl.class);
	private ChatClient chatClient;
	//for reading prompts from file
	@Value("classpath:/prompts/user-message.st")
	private Resource userMessage;
	@Value("classpath:/prompts/system-message.st")
	private Resource systemMessage;
	private VectorStore vectorStore;
	public ChatServiceImpl(ChatClient chatClient,VectorStore vectorStore)
	{
		/*this.chatClient=builder
				.defaultOptions(OpenAiChatOptions.builder()
						.model("gpt-4o-mini")
						.temperature(0.3)
						.maxTokens(100)
						.build())
				.build();*/
		this.chatClient=chatClient;
		this.vectorStore=vectorStore;
	}
	/*@Override
	public String chat(String query) {
		String prompt="tell me about virat kohli?";
		//String content = chatClient.prompt().user(prompt).system("As an expert in cricket.").call().content();
		/*Prompt prompt1=new Prompt(query,OpenAiChatOptions.builder()
				.model("gpt-4o-mini")
				.temperature(0.3)
				.maxTokens(100)
				.build());*/
		/*Prompt prompt1=new Prompt(prompt);
		var metaData = chatClient.prompt(prompt1)
				.call()
				.chatResponse().getMetadata();
		System.out.println(metaData);
		/*List<Tut> tutorial = chatClient.prompt(prompt1)
		.call()
		.entity(new ParameterizedTypeReference<List<Tut>>() {
		});*/
		/*.chatResponse()
		.getResult()
		.getOutput()
		.getText();*/
		//System.out.println(content);
		//return content;
		//String queryStr="As an expert in coding and programming. Always write code in java. Now reply for this question:"+query;
		/*String queryStr="As an expert in coding and programming. Always write code in java. Now reply for this question:{query}";
		String content = chatClient
				.prompt()
				.user(u -> u.text(queryStr).param("query", query))
				.call()
				.content();
		return content;
	}
	public String chatTemplate(String query,String userId)
	{
		//first step. template is ready
		/*PromptTemplate strTemplate = PromptTemplate.builder()
				.template("What is {techName}?tell me also about {techExample}").build();*/
		//render the template.TemplateRenderer
		/*String renderedMessage = strTemplate.render(Map.of(
				"techName","Spring",
				"techExample","Spring Exception"
				));*/
		//Now create prompt
		//Prompt prompt=new Prompt(renderedMessage);
		/*var systemPromptTemplate=SystemPromptTemplate.builder()
		.template("You are a helpful coding assistant.You are an expert in coding.")
		.build();*/
		//var systemMessage = systemPromptTemplate.render();
		/*var systemMessage = systemPromptTemplate.createMessage();
		var userPromptTemplate=PromptTemplate.builder()
		.template("What is {techName}?tell me also about {techExample}").build();*/
		/*var userMessage=userPromptTemplate.render(Map.of(
				"techName","Spring",
				"techExample","Spring Exception"
				));*/
		/*var userMessage=userPromptTemplate.createMessage(Map.of(
				"techName","Spring",
				"techExample","Spring Exception"
				));
		Prompt prompt=new Prompt(systemMessage,userMessage);
		String content = chatClient.prompt(prompt).call().content();*/
		//chatclient fluent api for prompt templating
		/*return chatClient
		.prompt()
		.system(system->system.text("You are a helpful coding assistant.You are an expert in coding."))
		.user(user->user.text("What is {techName}?tell me also about {techExample}")
				.param("techExample", "Spring Controller Example")
				.param("techName", "Collection framework in java"))
		.call()
		.content();*/
		//return content;
		/*return chatClient
				.prompt()
				.system(system->system.text("You are a helpful coding assistant.You are an expert in coding."))
				.user(user->user.text(userMessage).param("concept", "Spring Framework Validation"))
				.call()
				.content();*/
		//load data from vector database
		/*SearchRequest searchRequest = SearchRequest.builder().
				topK(5)
				.similarityThreshold(0.6)
				.query(query)
				.build();
		List<Document> documents = this.vectorStore.similaritySearch(searchRequest);
		//List<String> documentList = documents.stream().map(item->item.getText()).toList();
		List<String> documentList = documents.stream().map(Document::getText).toList();
		String contextData = String.join(", ", documentList);
		logger.info("Context Data: {}",contextData);*/
		//similar result user query
		//pass in context
		/*var advisor=RetrievalAugmentationAdvisor.builder()
				.documentRetriever(VectorStoreDocumentRetriever.builder()
						.vectorStore(vectorStore)
						.topK(3)
						.similarityThreshold(0.5)
						.build())
				.queryAugmenter(ContextualQueryAugmenter.builder().allowEmptyContext(true).build())
				.build();
		return chatClient
				.prompt()
				.advisors(advisor)
				//.advisors(new TokenPrintAdvisor(),new SimpleLoggerAdvisor(),new SafeGuardAdvisor(List.of("games")))//advisor for this request
				//.advisors(advisorSpec->advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))//advisor for this request
				//.system(system->system.text(systemMessage).param("documents", contextData))
				//.user(user->user.text(userMessage).param("concept", "Spring Framework Validation"))
				//.advisors(new QuestionAnswerAdvisor(vectorStore))
				//.advisors(QuestionAnswerAdvisor.builder(vectorStore).
				//		searchRequest(SearchRequest.builder().topK(3).similarityThreshold(0.5).build()).build())
				.user(user->user.text(userMessage).param("query", query))
				.call()
				.content();
	}
	@Override
	public Flux<String> streamChat(String q) {
		// TODO Auto-generated method stub
		return this.chatClient
				.prompt()
				.system(system->system.text(systemMessage))
				.user(user->user.text(userMessage).param("concept", q))
				//.call()->used for blocking
				.stream()//->used for streaming
				.content()
				;
	}*/
	@Override
	public void saveData(List<String> list) {
		//List<Document> documentList = list.stream().map(item->new Document(item)).collect(Collectors.toList());
		List<Document> documentList = list.stream().map(Document::new).toList();
		this.vectorStore.add(documentList);
	}
	@Override
	public String getResponse(String userQuery) {
		// TODO Auto-generated method stub
		var advisor=RetrievalAugmentationAdvisor
		.builder()
		.queryTransformers(
				RewriteQueryTransformer.builder()
				.chatClientBuilder(chatClient.mutate().clone())
				.build(),
				TranslationQueryTransformer.builder()
				.chatClientBuilder(chatClient.mutate().clone())
				.targetLanguage("hindi").build()
				)
		.queryExpander(MultiQueryExpander.builder().chatClientBuilder(chatClient.mutate().clone()).numberOfQueries(3).build())
		.documentRetriever(
				VectorStoreDocumentRetriever.builder()
				.vectorStore(vectorStore)
				.topK(3)
				.similarityThreshold(0.3)
				.build()
				)
		.documentJoiner(new ConcatenationDocumentJoiner())
		.queryAugmenter(ContextualQueryAugmenter.builder().build())
		.build();
		return chatClient
				.prompt()
				.advisors(advisor)
				.user(userQuery)
				.call()
				.content();
	}
}
