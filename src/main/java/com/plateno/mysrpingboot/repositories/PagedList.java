package com.plateno.mysrpingboot.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @类说明 ： 数据分页模型
 * @创建时间 ：2009-5-18 上午11:19:21
 * @创建人： gaolk
 */
public class PagedList implements Serializable {

	/**
	 * 总行数
	 */
	private long rowCount = 0;

	/**
	 * 数据列表
	 */
	private List list = new ArrayList();

	/**
	 * 每页显示行数
	 */
	protected long limit;

	/**
	 * 当前数据行
	 */
	protected long start = 0;

	/**
	 * 分页操作结果
	 */
	protected String option;

	/**
	 * 当前页
	 */
	public long currentPage = 0;

	/**
	 * 最大行数
	 */
	private long DEFAULT_MAX_ROWS = 20l;

	private long pageCount = 0;

	/**
	 * 在分页时是否执行总行数的查询 
	 */
	private boolean alwaysQuery = false ;
	
	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {

		this.list = list;

	}

	public long getPageCount(long pagesize) {

		long pagecount = 0;
		if (rowCount % pagesize != 0) {
			pagecount = rowCount / Long.valueOf(pagesize) + 1;
		} else {
			pagecount = rowCount / Long.valueOf(pagesize);
		}

		return pagecount;

	}

	public long getLimit() {

		if (this.limit == 0) {

			return DEFAULT_MAX_ROWS;
		}

		return limit;

	}

	public void setLimit(long limit) {

		this.limit = limit;

	}

	
	
	public boolean isAlwaysQuery() {
		
		if(this.alwaysQuery) 
		{
			return this.alwaysQuery ; 
		}
		else
		{
			if(this.rowCount == 0) return true ;
			return this.alwaysQuery;
		}
		
	}

	public void setAlwaysQuery(boolean alwaysQuery) {
		this.alwaysQuery = alwaysQuery;
	}

	public long getPostStart() {

		long postStart = 0;
		//第一页
		if ("first".equalsIgnoreCase(this.option)) {
			postStart = 0;
		}
		//最后一页
		if ("last".equalsIgnoreCase(this.option)) {
			postStart = (this.getPageCount() - 1) * this.getLimit();
		}
		//上一页
		if ("pre".equalsIgnoreCase(this.option)) {
			long i = this.start - this.getLimit();
			if (i < 0)
				i = 0;
			postStart = i;
		}
		//下一页
		if ("next".equalsIgnoreCase(this.option)) {
			long i = this.start + this.getLimit();
			if (i > this.getRowCount())
				i = (this.getPageCount() - 1) * this.getLimit();
			postStart = i;

		}
		//跳转到某页
		if ("goto".equalsIgnoreCase(this.option)) {
			long i = (this.currentPage > this.getPageCount() ? this
					.getPageCount() : this.currentPage) - 1;
			if (i < 0)
				i = 0;
			postStart = (i * this.getLimit());
		}

		return postStart ;

	}

	public long getLastPageStart() {

		return (this.getPageCount() - 1) * this.getLimit();

	}

	/**
	 * 获取开始行号
	 * @return
	 */
	public long getStart() {

		return this.start;

	}

	public void setStart(long start) {

		this.start = start;

	}

	public String getOption() {

		return option;

	}

	public void setOption(String option) {

		this.option = option;

	}

	/**
	 * 获取当前页
	 * @return
	 */
	public long getCurrentPage() {

		long i = this.getPostStart() / this.getLimit() + 1;
		if (this.getPostStart() % this.getLimit() != 0)
			i = i + 1;
		if (i > this.getPageCount())
			i = this.getPageCount();

		return Long.valueOf(i);

	}

	/**
	 * 获取总页数
	 * @return
	 */
	public long getPageCount() {

		long pagecount;
		if (this.rowCount % this.getLimit() != 0) {
			pagecount = this.rowCount /  this.getLimit() + 1;
		} else {
			pagecount = this.rowCount / this.getLimit();
		}

		return pagecount;

	}

	public void setPageCount(long pageCount) {

		this.pageCount = pageCount;

	}

	public void setCurrentPage(long currentPage) {

		this.currentPage = currentPage;

	}

}
