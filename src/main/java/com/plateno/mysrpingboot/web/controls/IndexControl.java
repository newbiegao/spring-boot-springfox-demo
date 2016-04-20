package com.plateno.mysrpingboot.web.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plateno.mysrpingboot.services.RedisService;


@RestController
public class IndexControl {

  @Autowired
  private RedisService redisSrv ;

  @RequestMapping("/index")
  public String index()
  {
    return "hello spring boot index" ;
  }
  
	@RequestMapping("/srv/hotel")
	public  String hello() {
		return "hello spring boot" ;
	}
	
	
	@RequestMapping("/srv1/hotel")
	public  String index2()
	{
		return "hello spring boot 2 " ;
	}
	
	@RequestMapping("/redis/add/{id}")
	public String addUser( @PathVariable String id )
	{
		redisSrv.addUser(id);
		return "ok" ;
	}
	
	@RequestMapping("/redis/list/{id}")
	public String userList( @PathVariable String id )
	{
		return redisSrv.getUser(id);
	}
	
}
