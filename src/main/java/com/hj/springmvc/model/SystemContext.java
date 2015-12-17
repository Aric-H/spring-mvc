package com.hj.springmvc.model;

public class SystemContext {
	//获取当前线程当前页
	private static ThreadLocal<Integer> currentPage = new ThreadLocal<Integer>();
	//获取当前线程分页大小
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	public static int getCurrentPage() {
		return currentPage.get();
	}
	public static void setCurrentPage(Integer _pageOffset) {
		SystemContext.currentPage.set(_pageOffset);
	}
	public static int getPageSize() {
		return pageSize.get();
	}
	public static void setPageSize(Integer _pageSize) {
		SystemContext.pageSize.set(_pageSize);
	}
	
	public static void removeCurrentPage(){
		currentPage.remove();
	}
	public static void removePageSize(){
		pageSize.remove();
	}
	
}
