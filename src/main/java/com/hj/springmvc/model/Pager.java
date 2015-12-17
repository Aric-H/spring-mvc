package com.hj.springmvc.model;

import java.util.List;

public class Pager<T> {
	//��ҳ������
	private List<T> datas;
	//��ǰҳ
	private int currentPage;
	//�ܼ�¼��
	private long totalRecord;
	//��ҳ��С
	private int pageSize;
	//��ҳ��
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
