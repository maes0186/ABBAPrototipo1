package com.conexia.saludcoop.util;

import java.util.ArrayList;
import java.util.List;

public class PageDTO<T> {

	private List<T> rows;
	private Integer totalCount;
	
	private Integer currentPage;
	
	private Integer offset;
	private Integer count;
	
	public PageDTO() {
		totalCount = 0;
		rows = new ArrayList<T>();
		offset = new Integer(0);
		count = new Integer(0);
	}
	
	public PageDTO(List<T> rows) {
		this.rows = rows;
		offset = new Integer(0);
		setCount(rows.size());
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getLast() {
		return new Integer(offset+rows.size());
	}
	
	public Integer getCurrentPage() {
		return this.currentPage;
	}
	
	public void setCurrentPage() {
		this.setCurrentPage(null);
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage != null) {
			this.currentPage = currentPage;
		} else {
			if(this.count > 0) {
				this.currentPage = (int)Math.floor(this.offset / this.count);
			} else {
				this.currentPage = 0;
			}
		}
	}
	
	public Integer getPages() {
		if(count == 0) {
			return 0;
		}
		return (int)Math.ceil((double)totalCount / (double)count);
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "PageDTO ( "
	        + super.toString() + TAB
	        + "rows = " + this.rows + TAB
	        + "totalCount = " + this.totalCount + TAB
	        + "currentPage = " + this.currentPage + TAB
	        + "offset = " + this.offset + TAB
	        + "count = " + this.count + TAB
	        + " )";
	
	    return retValue;
	}


	
}
