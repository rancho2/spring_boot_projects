package com.spring.ai.chatclient.service;

import java.util.List;

import com.spring.ai.chatclient.entity.Tut;

public interface ChatService {
	String chat(String query);
	String chatTemplate();
}
