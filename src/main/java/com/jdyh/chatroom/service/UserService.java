package com.jdyh.chatroom.service;

import java.util.List;

import com.jdyh.chatroom.model.User;

public interface UserService {
	
	void save(User user);
	
	void delete(String userId);
	
	List<User> query();

}
