package com.embed.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.embed.project.helper.Helper;
import com.embed.project.service.ChatService;

@SpringBootTest
class EmbedProjectApplicationTests {
	@Autowired
	private ChatService chatService;
	@Test
	void saveDateToVectorDb()
	{
		System.out.println("Going to save data into db");
		chatService.saveData(Helper.getData());
		System.out.println("Data is saved to db");
	}
}
