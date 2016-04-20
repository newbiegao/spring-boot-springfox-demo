package com.plateno.mysrpingboot.repositories;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

public interface BaseRepository {


	/**
	 * 通过SQL语句查询
	 * 
	 * @param sql
	 * @param params 站位符查询参数 如： user_name = ?
	 * @return
	 */
	public List querySql(final String sql, final Object... params) ;

	
	/**
	 * 通过SQL语句查询
	 * @param sql  SQL语句
	 * @param entityClass 需要返回的实体
	 * @param params 查询参数
	 * @return
	 */
	public List querySql(final String sql, final Class entityClass , final Object... params) ;
	
	/**
	 * 通过SQL语句查询
	 * 
	 * @param sql
	 * @param params 键值对查询参数 如 user_name = :user_name
	 * @return
	 */
	public List querySql( String sql,  Map<String,Object> params) ;
	

	/**
	 * 通过SQL语句查询
	 * 
	 * @param sql SQL语句
	 * @param start 开始记录数
	 * @param resultCount 查询技术数
	 * @param params 查询技术数
	 * @return
	 */
	public List querySql( String sql,  int start,  int resultCount,  Object... params) ;
	
	/**
	 * 执行SQL语句
	 * 
	 * @param sql SQL语句
	 * @param params SQL参数
	 * @return
	 */
	public int executeSql(final String sql, final Object... params) ;
	
	
	/**
	 * 分页查询 , 根据查询对象PagedList查询数据
	 * 
	 * @param queryCountStr
	 * @param queryString
	 * @param pagedList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PagedList loadPagedList(final String queryCountStr, final String queryString, final PagedList queryPagedList) ;

	/**
	 * 根据查询对象查询数据
	 * 
	 * @param pagedList
	 * @param queryModel
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PagedList loadPagedList( PagedList pagedList, QueryModel queryModel) ;
	
	/**
	 * 根据查询对象查询数据
	 * 
	 * @param pagedList
	 * @param queryModel
	 * @return
	 */

	public SimplePagedList loadPagedList( SimplePagedList pagedList, QueryModel queryModel);

	
	/**
	 * 根据SQL语句查询数据
	 * 
	 * @param queryCountStr
	 * @param queryString
	 * @param pagedList
	 * @return
	 */
	public SimplePagedList loadPagedListSql( String queryCountStr, String queryString,  SimplePagedList queryPagedList) ;

	
	/**
	 * 根据SQL语句查询数据
	 * 
	 * @param queryCountStr
	 * @param queryString
	 * @param pagedList
	 * @return
	 */
	public PagedList loadPagedListSql( String queryCountStr, String queryString,  PagedList queryPagedList) ;


	/**
	 * 查询总数,使用本地SQL
	 * 
	 * @param queryStr
	 * @return
	 */
	public int getIntUniqueResult( String queryStr) ;
	
	public int querySqlSingleIntValue(final String sql , Object... params ) ;
	
	/**
	 * 根据查询模型获取数据
	 * 
	 * @param queryModel 查询模型
	 * @return
	 */
	public List getList(final QueryModel queryModel);
	
}
