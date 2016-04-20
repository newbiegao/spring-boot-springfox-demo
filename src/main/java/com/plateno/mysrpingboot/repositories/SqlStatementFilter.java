package com.plateno.mysrpingboot.repositories;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @类说明 ： 对SQL模板进行处理
 * @创建时间 ：2009-11-13 下午03:24:23
 * @创建人： gaolk
 */
public class SqlStatementFilter {

	private static Logger log = LoggerFactory.getLogger(SqlStatementFilter.class);
	
	public static String passSqlTemplate(String sql,
			Map<String, Object> keyValues) {

		if (sql == null || sql.trim().length() == 0) {

			return sql;
		}
		//处理SQL模板
		String sqltemp = freemarkerSqlTemplate(sql, keyValues);

		//处理SQL参数
		return passSqlStatement(sqltemp, keyValues);

	}

	/**
	 * 处理SQL参数
	 * @param sql
	 * @param keyValues
	 * @return
	 */
	private static String passSqlStatement(String sql,
			Map<String, Object> keyValues) {

		String sqltemp = sql ;
		//预处理SQL语句
		List<Map<String, String>> statements = passStatement(sql);
		for (Map<String, String> statement : statements ) 
		{
			String paramName = statement.keySet().iterator().next() ;
			if (keyValues.keySet().contains(paramName.replace(":", "")) && keyValues.get(paramName.replace(":", "")) != null && keyValues.get(paramName.replace(":", "")).toString().trim().length() != 0)
			{
				//替换
				String temp = statement.get(paramName).replace("[", "").replace("]", "");	
				sqltemp = sqltemp.replace(statement.get(paramName), temp);
			} 
			else 
			{
				//清空
				sqltemp = sqltemp.replace(statement.get(paramName), "");
			}
		}
		return sqltemp;
	}


	/**
	 * 对FREEMARKER模板进行处理
	 * @param sql
	 * @return
	 */
	private static String freemarkerSqlTemplate(String sql,
			Map<String, Object> keyValues) {

		Writer out = null;

		try {
			
			//配置模板,支持从多数据源获取模板
			TemplateLoader[] loaders = new TemplateLoader[2] ;
			
			loaders[1] = new FreemarkerClassTemplateLoader() ;
			
			StringTemplateLoader sp = new StringTemplateLoader();
			loaders[0] = sp ;
			
			sp.putTemplate("sql_template", sql);
			
			MultiTemplateLoader multiLoader = new MultiTemplateLoader(loaders);
			
			Configuration cfg = new Configuration();
			cfg.setTemplateLoader(multiLoader);
			
			//处理模板
			Template tempate = cfg.getTemplate("sql_template");
			out = new StringWriter();
			tempate.process(keyValues, out);
			cfg.clearTemplateCache();

			return out.toString();

		}
		catch( Exception e )
		{
			//构造参数
			StringBuilder parms = new StringBuilder();
			for( String key : keyValues.keySet() )
			{
				parms.append( key);
				parms.append("=") ;
				parms.append( String.valueOf( keyValues.get( key ) ) ) ;
			}
			
			StringBuilder sb = new StringBuilder() ;
			sb.append("解析SQL模板出错,");
			sb.append("错误SQL语句:") ;
			sb.append( sql );
			sb.append(", 错误参数： " + parms );
			sb.append(" , "  + e.getMessage() );
			
			log.error( sb.toString() ) ;
			
			throw new RuntimeException(e) ;
		}
		finally {
			if (out != null)
			{
				try { out.close() ; } catch( Exception e ) { out = null ; }
			}
			out = null;
		}

	}

	/**
	 * 获取条件表达式
	 * @param sql
	 * @return
	 */
	private static List<Map<String, String>> passStatement(String sql) {

		List<Map<String, String>> statements = new ArrayList<Map<String, String>>();
		
		boolean isSplit = false;
		boolean isParam = false;
		StringBuilder sqlParam = new StringBuilder();
		StringBuilder paramName = new StringBuilder();
		for (char c : sql.toCharArray()) {
			//条件语句判断
			if (c == '[') {
				isSplit = true;
			}
			if (c == ']') {
				isSplit = false;
				sqlParam.append(c);
			}
			//参数名称判断
			if (c == ':' & isSplit) {
				isParam = true;
			}
			if (c == ' ') {
				isParam = false;
			}
			if (isParam && c != ']' ) {
				paramName.append(c);
			}

			if (isSplit) {
				sqlParam.append(c);
			} else if (isSplit == false & c == ']') {
				Map<String, String> statement = new HashMap<String, String>();
				statement.put(paramName.toString(), sqlParam.toString());
				statements.add( statement ) ;
				
				sqlParam = new StringBuilder();
				paramName = new StringBuilder();
			}
		}

		return statements;

	}

}
