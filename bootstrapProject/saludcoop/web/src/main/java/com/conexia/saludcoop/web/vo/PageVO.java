package com.conexia.saludcoop.web.vo;

import java.util.List;

public class PageVO<T>{
	
	private List<T> content;
	private int totalPages;
	private long totalElements;
	private int paginaActual;
	

	public PageVO(List<T> content) {
		this.content=content;
	}
	
	public PageVO(	List<T> content, 
					int totalPages,
					long totalElements,
					int paginaActual){
		this.content=content;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.paginaActual = paginaActual;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	
	

}
