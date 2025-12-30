package com.spring.ai.chatclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.ai.chatclient.service.ChatService;

@SpringBootTest
class ChtclientApiApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private ChatService chatService;
	@Test
	void testTemplateRender()
	{
		System.out.println("Template Renderer");
		var output=chatService.chatTemplate();
		System.out.println(output);
	}
}
