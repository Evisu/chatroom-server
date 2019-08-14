package com.jdyh.chatroom.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jdyh.chatroom.model.User;

public class CacheManager {
	
	private static final Map<String,User> userCache = new HashMap<String,User>();
	
	
	public void putUser(String key,User value) {
		if(value != null) {
			userCache.put(key, value);
		}
	}
	
	public void deleteUser(String key) {
		if(userCache.containsKey(key)) {
			userCache.remove(key);
		}
	}
	
	public List<User> getUserList(){
		return new ArrayList<User>(userCache.values());
	}


}
