package com.heykeel.entity;

public class Pagination {
	private Integer currentPage;  // 当前页
	private Integer pageSize;     // 每页数据数量
	private Integer startNum;     // 起始数据号
	
	public Pagination(Integer currentPage, Integer pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.startNum = (currentPage-1)*pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStartNum() {
		startNum = (currentPage-1)*pageSize;
		return startNum;
	}
}
