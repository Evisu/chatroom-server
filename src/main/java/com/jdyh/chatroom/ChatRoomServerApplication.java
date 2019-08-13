package com.jdyh.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.jdyh.chatroom.server.ChatRoomServer;

@SpringBootApplication
@ComponentScan(basePackages = { "com.jdyh.chatroom"})
public class ChatRoomServerApplication {
	
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(ChatRoomServerApplication.class, args);
		ChatRoomServer chatRoomServer = context.getBean(ChatRoomServer.class);
		chatRoomServer.start(8899);
	}
}
