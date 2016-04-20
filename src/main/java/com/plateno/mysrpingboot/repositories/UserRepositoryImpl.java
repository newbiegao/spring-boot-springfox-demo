package com.plateno.mysrpingboot.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.plateno.mysrpingboot.domain.UserEntity;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl implements UserRepositoryCums {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@PersistenceContext
	private EntityManager em ;
	
	@Override
	public void deleteUser( Integer userId ) 
	{
		UserEntity user =  em.find(UserEntity.class, userId) ;
		if( user != null )
		{
			logger.debug("删除用户{}",userId);
			em.remove(user);
		}
		logger.debug("没有找到要删除用户{}",userId);
	}

	@Override
	public Integer countUsers(String name, Integer age) {
		
		String sql = " select count(*) from t_users where user_name like ?0 and user_age = ?1 ";
		return this.querySqlSingleIntValue(sql, "%"+name+"%" , age) ;
	}
	
	@Override
	public SimplePagedList loadUsersByPage( SimplePagedList pageList )
	{
		QueryModel qm = new QueryModel() ;
		qm.setCountSql(" select count(u) from UserEntity u  ");
		qm.setQuerySql(" select u from UserEntity u order by u.userId asc ");
		qm.setNativeSql(false);
		
		return this.loadPagedList(pageList, qm) ;
	}

}
