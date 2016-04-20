package com.plateno.mysrpingboot.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private StringRedisTemplate redis ;
	
	public void addUser( String userId )
	{
		redis.boundValueOps(userId).set("gaolk"+UUID.randomUUID().toString());
	}
	
	public String getUser( String user )
	{
		return redis.boundValueOps(user).get();
	}
	
}
