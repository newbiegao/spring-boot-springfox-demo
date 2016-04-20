package com.plateno.mysrpingboot.repositories ;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 公共数据访问
 * @author gaolk
 *
 */
public class BaseRepositoryImpl implements BaseRepository {

	private static Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);
	
	@PersistenceContext
	private EntityManager em ;
	

	/**
	 * 根据参数名称设置参数
	 * @param query
	 * @param params
	 */
	private void setQueryParameterByName( Query query , Map<String,Object> params )
	{
		if( params == null ) return ;
		for( Object key : params.keySet() )
		{
			query.setParameter( key.toString() , params.get(key) );
		}
	}
			
	
	/**
	 * 通过SQL语句查询
	 * 
	 * @param sql
	 * @param params 站位符查询参数 如： user_name = ?
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	public List querySql(final String sql, final Object... params) {

		long t1 = System.currentTimeMillis() ;
		
		Query query = em.createNativeQuery(sql);
		// 设置参数
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行SQL语句: "+sql+" 时间:" + (t2-t1) + "毫秒" );
		
		return query.getResultList() ;
	}
	
	
	/**
	 * 通过SQL语句查询
	 * @param sql  SQL语句
	 * @param entityClass 需要返回的实体
	 * @param params 查询参数
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	public List querySql(final String sql, final Class entityClass , final Object... params) {

		long t1 = System.currentTimeMillis() ;
		
		Query query = em.createNativeQuery(sql,entityClass);
		
		// 设置参数
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行SQL语句: "+sql+" 时间:" + (t2-t1) + "毫秒" );
		
		return query.getResultList() ;
	}
	
	/**
	 * 通过SQL语句查询
	 * 
	 * @param sql
	 * @param params 键值对查询参数 如 user_name = :user_name
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	public List querySql( String sql,  Map<String,Object> params) {

		long t1 = System.currentTimeMillis() ;
		
		Query query = em.createNativeQuery(sql);
		
		setQueryParameterByName(query, params);
		
		long t2  = System.currentTimeMillis() ;
		
		logger.debug("执行SQL语句: "+sql+" 时间:" + (t2-t1) + "毫秒" );
		return query.getResultList() ;
	}

	/**
	 * 通过SQL语句查询
	 * 
	 * @param sql SQL语句
	 * @param start 开始记录数
	 * @param resultCount 查询技术数
	 * @param params 查询技术数
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public List querySql( String sql,  int start,  int resultCount,  Object... params) {
		
		long t1 = System.currentTimeMillis() ;
		
		Query query = em.createNativeQuery(sql);
		// 设置参数
		
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}

		query.setFirstResult(start);
		query.setMaxResults(resultCount);
		
		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行SQL语句: "+sql+" 时间:" + (t2-t1) + "毫秒" );
		
		return query.getResultList() ;
	}
	
	
	/**
	 * 执行SQL语句
	 * 
	 * @param sql SQL语句
	 * @param params SQL参数
	 * @return
	 */
	public int executeSql(final String sql, final Object... params) {
	
		Query query = em.createNativeQuery(sql);
		
		// 设置参数
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		
		long t1 = System.currentTimeMillis() ;
		
		int count = query.executeUpdate();
		
		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行SQL语句: "+sql+" 时间:" + (t2-t1) + "毫秒" );
		
		return count ;
		
	}
	
	
	/**
	 * 分页查询 , 根据查询对象PagedList查询数据
	 * 
	 * @param queryCountStr
	 * @param queryString
	 * @param pagedList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PagedList loadPagedList(final String queryCountStr, final String queryString, final PagedList queryPagedList) {

		long t1 = System.currentTimeMillis() ;
		
		PagedList pageList = new PagedList();
		// 设置分页组件
		pageList.setStart(queryPagedList.getStart());
		pageList.setLimit(queryPagedList.getLimit());
		pageList.setOption(queryPagedList.getOption());
		pageList.setCurrentPage(queryPagedList.currentPage);
		
		//不需要查询总行数则使用前面查询到值授予当前值
		if( !queryPagedList.isAlwaysQuery() )
		{
			pageList.setRowCount( queryPagedList.getRowCount() );
		}
		
		if( queryPagedList.isAlwaysQuery() )
		{
			long tcount1 = System.currentTimeMillis() ;
			Query countQuery = em.createNativeQuery(queryCountStr);
			// 查询总行数
			pageList.setRowCount(Integer.valueOf(countQuery.getSingleResult().toString()));
			long tcount2 = System.currentTimeMillis() ;
			logger.debug("执行汇总SQL语句: "+queryCountStr+" 时间:" + (tcount2-tcount1) + "毫秒" );
		}
		
		
		// 查询分页后的数据
		// 如果是最后一行，重新设置pageList。
		long newStart = pageList.getPostStart();

		if (newStart >= pageList.getRowCount()) {
			newStart = pageList.getLastPageStart();
		}
		// 查询数据
		Query query = em.createNativeQuery(queryString)
				.setFirstResult( Integer.valueOf(String.valueOf(newStart)) )
				.setMaxResults( Integer.valueOf(String.valueOf(pageList.getLimit())) );
		
		
		query.setFirstResult(Integer.valueOf(String.valueOf(newStart)) ).setMaxResults(Integer.valueOf(String.valueOf(pageList.getLimit())));
		
		pageList.setList(query.getResultList());
		
		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行SQL语句: "+queryString+" 时间:" + (t2-t1) + "毫秒" );
		
		return pageList;

	}

	/**
	 * 根据查询对象查询数据
	 * 
	 * @param pagedList
	 * @param queryModel
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PagedList loadPagedList( PagedList pagedList, QueryModel queryModel){

		// 构造带有查询条件的查询语句
		String queryCountStr = queryModel.getCountExecuteQuerySql();
	    String queryString = queryModel.getExecuteQuerySql();
		
		PagedList pageList = new PagedList();

		// 设置分页组件
		pageList.setStart(pagedList.getStart());
		pageList.setLimit(pagedList.getLimit());
		pageList.setOption(pagedList.getOption());
		pageList.setCurrentPage(pagedList.currentPage);

		//不需要查询总行数则使用前面查询到值授予当前值
		if( !pagedList.isAlwaysQuery() )
		{
			pageList.setRowCount(pagedList.getRowCount());
		}
		
		Query countQuery = null;
		Query query = null;

		if (queryModel.isNativeSql()) // 构造SQL语句
		{
			if(pagedList.isAlwaysQuery()) countQuery =  em.createNativeQuery(queryCountStr);
			query = em.createNativeQuery(queryString);
		} 
		else // 构造HQL语句
		{
			if(pagedList.isAlwaysQuery())  countQuery = em.createQuery(queryCountStr);
			query = em.createQuery(queryString);
		}
		
		if (queryModel.isSqlTemplate())
		{
			setQueryParamersByName( queryModel.getKeyValueMap() , queryString , query ,  countQuery ) ;
		}
		else
		{
			setQueryParamersByName( queryModel.getExecuteQueryParamer() , queryString , query ,  countQuery ) ;
		}
		
		
		// 查询总行数
		if(pagedList.isAlwaysQuery()) 
		{
			long tcount1 = System.currentTimeMillis() ;
			// 设置总行数
			pageList.setRowCount(Integer.valueOf(countQuery.getSingleResult().toString()));
			long tcount2 = System.currentTimeMillis() ;
			logger.debug("执行汇总SQL语句: "+queryCountStr+" 时间:" + (tcount2-tcount1) + "毫秒" );
		}
				

		// 查询分页后的数据
		// 如果是最后一行，重新设置pageList。
		long newStart = pageList.getPostStart();
		if (newStart >= pageList.getRowCount() ) {
			newStart = pageList.getLastPageStart();
		}
		query.setFirstResult( Integer.valueOf(String.valueOf(newStart)) ).setMaxResults( Integer.valueOf(String.valueOf(pageList.getLimit())) );
		
		long t1 = System.currentTimeMillis() ;
		
		pageList.setList(query.getResultList());
		
		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行查询SQL语句: "+queryString+" 时间:" + (t2-t1) + "毫秒" );
		
		return pageList;

	}
	
	/**
	 * 根据查询对象查询数据
	 * 
	 * @param pagedList
	 * @param queryModel
	 * @return
	 */

	public SimplePagedList loadPagedList( SimplePagedList pagedList, QueryModel queryModel){

		// 构造带有查询条件的查询语句
		String queryCountStr = queryModel.getCountExecuteQuerySql();
	    String queryString = queryModel.getExecuteQuerySql();
		
		//不需要查询总行数则使用前面查询到值授予当前值
		if( !pagedList.isAlwaysQuery() )
		{
			pagedList.setRowCount(pagedList.getRowCount());
		}
		
		Query countQuery = null;
		Query query = null;

		if (queryModel.isNativeSql()) // 构造SQL语句
		{
			if(pagedList.isAlwaysQuery()) countQuery = em.createNativeQuery(queryCountStr);
			query = em.createNativeQuery(queryString);
		} 
		else // 构造HQL语句
		{
			if(pagedList.isAlwaysQuery())  countQuery = em.createQuery(queryCountStr);
			query = em.createQuery(queryString);
		}
		
		if (queryModel.isSqlTemplate())
		{
			setQueryParamersByName( queryModel.getKeyValueMap() , queryString , query ,  countQuery ) ;
		}
		else
		{
			setQueryParamersByName( queryModel.getExecuteQueryParamer() , queryString , query ,  countQuery ) ;
		}
		
		
		// 查询总行数
		if(pagedList.isAlwaysQuery()) 
		{
			long tcount1 = System.currentTimeMillis() ;
			// 设置总行数
			pagedList.setRowCount(Integer.valueOf(countQuery.getSingleResult().toString()));
			long tcount2 = System.currentTimeMillis() ;
			logger.debug("执行汇总SQL语句: "+queryCountStr+" 时间:" + (tcount2-tcount1) + "毫秒" );
		}
		
		System.out.println(pagedList.getStart());
		query.setFirstResult(pagedList.getStart()).setMaxResults(pagedList.getLimit());
		
		long t1 = System.currentTimeMillis() ;
		
		pagedList.setList(query.getResultList());
		
		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行查询SQL语句: "+queryString+" 时间:" + (t2-t1) + "毫秒" );
		
		return pagedList ;

	}

	/**
	 * 设置查询参数
	 * @param params 参数键值对
	 * @param sql 查询语句
	 * @param query 查询对象
	 */
	private void setQueryParamersByName( Map<String,Object> params , String sql , Query query , Query countQuery )
	{
		for( String paramName : params.keySet() )
		{
			//判断查询语句是否包含参数变量名称,这里参数名称是不带":"符号的,所以在判断时候需要加上":"
			String queryParamName = paramName ;
			if(!paramName.startsWith(":")) 
			{
				queryParamName = ":" + paramName ;
			}
			
			if( sql.toLowerCase().contains(queryParamName.toLowerCase()) && params.get(paramName) != null )
			{
				query.setParameter(paramName, params.get(paramName) );
				if( countQuery != null ) countQuery.setParameter(paramName, params.get(paramName) );
				logger.debug("sql:" + sql + " 查询语句参数:"+ paramName + " 参数值:"+  params.get(paramName));
			}
		}
	}
	
	/**
	 * 根据SQL语句查询数据
	 * 
	 * @param queryCountStr
	 * @param queryString
	 * @param pagedList
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SimplePagedList loadPagedListSql( String queryCountStr, String queryString,  SimplePagedList queryPagedList) {

		long t1 = System.currentTimeMillis() ;
		
		// 查询总行数
		if( queryPagedList.isAlwaysQuery() )
		{
			long tcount1 = System.currentTimeMillis() ;
			Query countQuery = em.createNativeQuery(queryCountStr);
			queryPagedList.setRowCount(Integer.valueOf(countQuery.getSingleResult().toString()));
			long tcount2 = System.currentTimeMillis() ;
			logger.debug("执行汇总SQL语句: "+queryCountStr+" 时间:" + (tcount2-tcount1) + "毫秒" );
		}
	
		// 查询数据
		Query query = em.createNativeQuery(queryString)
				.setFirstResult(queryPagedList.getStart())
				.setMaxResults(queryPagedList.getLimit());
		
		queryPagedList.setList(query.getResultList());

		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行SQL语句: "+queryString+" 时间:" + (t2-t1) + "毫秒" );
		
		return queryPagedList ;

	}

	
	/**
	 * 根据SQL语句查询数据
	 * 
	 * @param queryCountStr
	 * @param queryString
	 * @param pagedList
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PagedList loadPagedListSql( String queryCountStr, String queryString,  PagedList queryPagedList) {

		long t1 = System.currentTimeMillis() ;
		
		PagedList pageList = new PagedList();

		// 初始化分页组件
		pageList.setStart(queryPagedList.getStart());
		pageList.setLimit(queryPagedList.getLimit());
		pageList.setOption(queryPagedList.getOption());
		pageList.setCurrentPage(queryPagedList.currentPage);
		
		//不需要查询总行数则使用前面查询到值授予当前值
		if( !queryPagedList.isAlwaysQuery() )
		{
			pageList.setRowCount(queryPagedList.getRowCount());
		}
		
		if( queryPagedList.isAlwaysQuery() )
		{
			long tcount1 = System.currentTimeMillis() ;
			Query countQuery = em.createNativeQuery(queryCountStr);
			// 查询总行数
			pageList.setRowCount(Integer.valueOf(countQuery.getSingleResult().toString()));
			long tcount2 = System.currentTimeMillis() ;
			logger.debug("执行汇总SQL语句: "+queryCountStr+" 时间:" + (tcount2-tcount1) + "毫秒" );
		}
	
		// 查询分页后的数据
		// 如果是最后一行，重新设置pageList。
		long newStart = pageList.getPostStart();

		if (newStart >= pageList.getRowCount()) {
			newStart = pageList.getLastPageStart();
		}
		// 查询数据
		Query query = em.createNativeQuery(queryString)
				.setFirstResult( Integer.valueOf(String.valueOf(newStart)))
				.setMaxResults(Integer.valueOf( String.valueOf( pageList.getLimit())));
		
		pageList.setList(query.getResultList());

		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行SQL语句: "+queryString+" 时间:" + (t2-t1) + "毫秒" );
		
		return pageList;

	}


	/**
	 * 查询总数,使用本地SQL
	 * 
	 * @param queryStr
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getIntUniqueResult( String queryStr) {

		Query query = em.createNativeQuery(queryStr);
		Object retObj =  query.getSingleResult() ;

		return Integer.parseInt(retObj.toString());

	}
	

	@SuppressWarnings({"rawtypes" })
	public int querySqlSingleIntValue(final String sql , Object... params )
	{
		List list = this.querySql(sql, params);
		if( list.size() == 0 )
		{
			return 0 ;
		}
		else
		{
			Object obj = list.get(0);
			if( obj == null ) return 0 ;
			if( obj.getClass().isArray() )
			{
				Object[] objsarray = (Object[])obj ;
				if( objsarray.length == 0 ) return 0 ;
				return Integer.valueOf( objsarray[0].toString());
			}
			else
			{
				return Integer.valueOf(obj.toString()) ;
			}
		}
	}
	
	/**
	 * 根据查询模型获取数据
	 * 
	 * @param queryModel 查询模型
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getList(final QueryModel queryModel) {

		// 构造带有查询条件的查询语句
		final String queryString = queryModel.getExecuteQuerySql();

		Query query = null;
		
		if (queryModel.isNativeSql()) // 构造SQL语句
		{
			query =  em.createNativeQuery(queryString);
		} else // 构造HQL语句
		{
			query = em.createQuery(queryString);
		}
		if (queryModel.isSqlTemplate())
		{
			setQueryParamersByName( queryModel.getKeyValueMap() , queryString , query ,  null ) ;
		}
		else
		{
			setQueryParamersByName( queryModel.getExecuteQueryParamer() , queryString , query ,  null ) ;
		}
		
		long t1 = System.currentTimeMillis() ;
		
		// 查询后的数据
		List result = query.getResultList() ;
		
		long t2 = System.currentTimeMillis() ;
		
		logger.debug("执行SQL语句: "+queryString+" 时间:" + (t2-t1) + "毫秒" );
		
		return result ;

	}
	
}