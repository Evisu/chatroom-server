package com.jdyh.chatroom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdyh.chatroom.common.cache.CacheManager;
import com.jdyh.chatroom.model.User;
import com.jdyh.chatroom.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private CacheManager cacheManager;

	@Override
	public void save(User user) {
		cacheManager.putUser(user.getUserId(), user);
	}

	@Override
	public void delete(String userId) {
		cacheManager.deleteUser(userId);
	}

	@Override
	public List<User> query() {
		return cacheManager.getUserList();
	}

}
