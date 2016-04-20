package com.plateno.mysrpingboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plateno.mysrpingboot.domain.UserEntity;
import com.plateno.mysrpingboot.repositories.SimplePagedList;
import com.plateno.mysrpingboot.repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userDao ;
	
	public void saveUser( UserEntity user )
	{
		userDao.save(user) ;
	}
	
	public UserEntity getUser( Integer userId )
	{
		return userDao.findOne(userId) ;
	}
	
	public void delUser( Integer userId )
	{
		userDao.deleteUser(userId);
	}
	
	public Integer countUsers( String name , Integer userAge )
	{
		return userDao.countUsers(name, userAge) ;
	}
	
	public List<UserEntity> findUserByAge( Integer age )
	{
		return userDao.findUserByAge(age) ;
	}
	
	public SimplePagedList loadUserPageList( SimplePagedList plist )
	{
		return userDao.loadUsersByPage(plist) ;
	}
	
	public List<UserEntity> addUsers()
	{
		List<UserEntity> users = new ArrayList<UserEntity>(); 
		for( int i=0 ; i<10 ; i++ )
		{
			UserEntity us = new UserEntity() ;
			us.setAge(10);
			us.setSex("女");
			us.setUserName(UUID.randomUUID().toString());
			userDao.save(us) ;
			users.add(us);
		}
		
		return users ;
		
		//throw new RuntimeException("error") ;
	}
	
}
