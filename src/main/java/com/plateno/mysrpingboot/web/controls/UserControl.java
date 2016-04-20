package com.plateno.mysrpingboot.web.controls;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plateno.mysrpingboot.domain.UserEntity;
import com.plateno.mysrpingboot.repositories.SimplePagedList;
import com.plateno.mysrpingboot.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/users/")
@Api(value = "/addUser", tags = "用户API接口")
public class UserControl {

	@Autowired
	private UserService userSrv ;
	
	/**
	 * 增加用户
	 * @return
	 */
	@RequestMapping(value="addUser" , method=RequestMethod.POST )
	@ApiOperation(value="增加用户到REDIS中", notes="自动创建用户到REDIS中，并返回用户信息")
	public UserEntity addUser()
	{
		UserEntity user = new UserEntity() ;
		user.setAge(100);
		user.setUserName(UUID.randomUUID().toString());
		user.setSex("男");
		userSrv.saveUser(user);
		
		return user ;
	}
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping( value="deluser/{userId}" , method=RequestMethod.POST )
	@ApiOperation(value="删除用户", notes="从REDIS中删除用户并返回成功")
	public String delUser( @ApiParam(name="userId" , value="用户ID" , required=true ) @PathVariable Integer userId )
	{
		userSrv.delUser(userId);
		return "ok" ;
	}
	
	@RequestMapping("count/{name}/{age}")
	public Integer countUser( @PathVariable String name , @PathVariable Integer age )
	{
		return userSrv.countUsers(name, age) ;
	}
	
	@RequestMapping("user/age/{age}")
	public List<UserEntity> findUserByAge( @PathVariable Integer age )
	{
		return userSrv.findUserByAge(age) ;
	}
	
	@RequestMapping("user/page/{page}")
	public SimplePagedList loadUserPageList( @PathVariable Integer page )
	{
		SimplePagedList sp = new SimplePagedList() ;
		sp.setCurrentPage(page);
		sp.setLimit(20);
		return userSrv.loadUserPageList(sp) ;
	}
	
	@RequestMapping("user/addusers")
	public List<UserEntity> addUsers()
	{
		return userSrv.addUsers() ;
	}
	
}
