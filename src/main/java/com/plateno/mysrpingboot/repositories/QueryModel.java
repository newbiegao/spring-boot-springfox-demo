package com.plateno.mysrpingboot.repositories;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @类说明 ： 数据查询模型
 * @创建时间 ：2009-5-18 上午11:29:50
 * @创建人： gaolk
 */
public class QueryModel {

	/**
	 * 计算总行数
	 */
	protected String countSql;

	/**
	 * 查询语句
	 */
	protected String querySql;

	/**
	 * 是否是普通SQL语句
	 */
	protected boolean isNativeSql = false;

	/**
	 * 查询参数键值对
	 * key : 查询参数名称
	 *       参数以 “：”开头的参数是SQL语句参数用于为SQL参数赋值，没有“:”的参数是控制参数，用于模板逻辑运算
	 * value : 查询参数值
	 */
	protected Map<String, Object> keyValueMap = new HashMap<String, Object>();

	public Map<String, Object> getKeyValueMap() {

		return keyValueMap;

	}

	public void setKeyValueMap(Map keyValueMap) {
		this.keyValueMap = keyValueMap;
	}
	
	public void addKeyValueMap( Map<String,String> keyValueMap  )
	{
		for( String key : keyValueMap.keySet() )
		{
			this.keyValueMap.put(key, keyValueMap.get(key));
		}
	}

	/**
	 * 是否是SQL模板 
	 */
	private boolean isSqlTemplate = false;

	public boolean isSqlTemplate() {

		return isSqlTemplate;

	}

	public void setSqlTemplate(boolean isSqlTemplate) {

		this.isSqlTemplate = isSqlTemplate;

	}

	/**
	 * 
	 * 获取查询的参数集合
	 * @return
	 */
	public Map<String , Object> getExecuteQueryParamer() {

		Map<String , Object> params = new HashMap<String , Object>();
		for (String paramName : this.keyValueMap.keySet() ) {
			if( paramName.startsWith(":") && this.keyValueMap.get(paramName) != null )
			{
				params.put( paramName , this.keyValueMap.get(paramName));
			}
		}

		return params;

	}

	/**
	 * 获取可执行查询SQL语句
	 * @return
	 */
	public String getExecuteQuerySql() {
		if( !this.isSqlTemplate ) return this.querySql  ;
		return SqlStatementFilter.passSqlTemplate(this.querySql,this.keyValueMap);
	}

	/**
	 * 获取可执行的count SQL语句
	 * @return
	 */
	public String getCountExecuteQuerySql() {
		if( !this.isSqlTemplate ) return this.countSql ;
		return SqlStatementFilter.passSqlTemplate(this.countSql,this.keyValueMap);
	}

	/**
	 * 增加查询参数
	 * @param key
	 * @param value
	 */
	public void addQueryParamer( String key , Object value )
	{
		if(!this.keyValueMap.keySet().contains(key))
		{
			this.keyValueMap.put(key, value);
		}
		
	}
	
	public String getCountSql() {
		return countSql;

	}

	public void setCountSql(String countSql) {

		this.countSql = countSql;

	}

	public String getQuerySql() {

		return querySql;

	}

	public void setQuerySql(String querySql) {

		this.querySql = querySql;

	}

	public boolean isNativeSql() {

		return isNativeSql;

	}

	public void setNativeSql(boolean isNativeSql) {

		this.isNativeSql = isNativeSql;

	}
}
