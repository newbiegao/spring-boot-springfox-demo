package com.plateno.mysrpingboot.repositories;

public interface UserRepositoryCums extends BaseRepository {

	public void deleteUser( Integer userId ) ;
	
	public Integer countUsers( String name , Integer age ) ;
	
	public SimplePagedList loadUsersByPage( SimplePagedList pageList ) ;
	
}
