package com.conexia.saludcoop.common.util;

public class Pagination {
	
	public static final Integer DEFAULT_PAGE_SIZE = 10;

	private int actualPage;
	private int pageSize;
	private int totalPages;
	private Long totalItems;

	public Pagination(int actualPage, int pageSize){
		this.actualPage = actualPage;
		this.pageSize = pageSize;
	}
	
	public int getActualPage() {
		return actualPage;
	}

	public void setActualPage(int actualPage) {
		this.actualPage = actualPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

}
