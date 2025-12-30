package com.spring.ai.firstproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.ai.firstproject.helper.Helper;
import com.spring.ai.firstproject.service.ChatService;

@SpringBootTest
class FirstProjectApplicationTests {
	@Autowired
	private ChatService chatService;
	@Test
	void saveDataToVectorDatabase()
	{
		System.out.println("Saving data to database");
		this.chatService.saveData(Helper.getData());
		System.out.println("Data is saved successfully");
	}
}
