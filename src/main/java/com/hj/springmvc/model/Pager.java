package com.hj.springmvc.model;

import java.util.List;

public class Pager<T> {
	//分页的数据
	private List<T> datas;
	//当前页
	private int currentPage;
	//总记录数
	private long totalRecord;
	//分页大小
	private int pageSize;
	//总页数
	private long pageCount;
	
	public long getPageCount() {
		return totalRecord%pageSize==0?totalRecord/pageSize:totalRecord/pageSize+1;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
