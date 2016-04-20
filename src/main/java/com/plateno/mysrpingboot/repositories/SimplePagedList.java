package com.plateno.mysrpingboot.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 简单数据分页模型
 * @author gaolk
 *
 */
public class SimplePagedList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1719656539570161036L;

	/**
	 * 总行数
	 */
	private int rowCount = 0;

	/**
	 * 数据列表
	 */
	private List list = new ArrayList();

	/**
	 * 每页显示行数
	 */
	protected int limit = 20  ;

	/**
	 * 当前页
	 */
	public int currentPage = 0;

	/**
	 * 在分页时是否执行总行数的查询 
	 */
	private boolean alwaysQuery = true ;

	/**
	 * 获取开始行
	 * @return
	 */
	public int getStart()
	{
	   return (this.currentPage * this.limit >= this.rowCount) ? (this.currentPage -1)*this.limit : this.currentPage * this.limit  ;
	}
	
	
	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isAlwaysQuery() {
		return alwaysQuery;
	}

	public void setAlwaysQuery(boolean alwaysQuery) {
		this.alwaysQuery = alwaysQuery;
	}
	
}
