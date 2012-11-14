/**
 *
 */
package org.springframework.webflow.samples.booking;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;

public class HotelLazyDataModel extends ExtendedDataModel implements
		Serializable {

	private static final long serialVersionUID = -8832831134966938627L;

	SearchCriteria searchCriteria;

	BookingService bookingService;

	private Hotel selected;

	private int rowIndex;

	private int currentPage;

	private Long currentId;

	private Map<Long, Hotel> hotelMap = new HashMap<Long, Hotel>();
	private Collection<? extends Serializable> selection;
	private String tableState;

	public HotelLazyDataModel(SearchCriteria searchCriteria,
			BookingService bookingService) {
		this.searchCriteria = searchCriteria;
		this.bookingService = bookingService;
	}

	@Override
	public int getRowCount() {
		return bookingService.getNumberOfHotels(searchCriteria);
	}

	public Hotel getSelected() {
		return selected;
	}

	public void setSelected(Hotel selected) {
		this.selected = selected;
	}

	public void setSelection(Collection<? extends Serializable> selection) {
		this.selection = selection;
	}

	public Collection<? extends Serializable> getSelection() {
		return selection;
	}

	public List<Hotel> fetchLazyData(int first, int pagesize) {
		searchCriteria.setCurrentPage(first / pagesize + 1);
		return bookingService.findHotels(searchCriteria, first, "city", true);
	}

	// data model

	@Override
	public Object getRowKey() {
		return currentId;
	}

	@Override
	public void setRowKey(Object rowKey) {
		if (rowKey != null) {
			this.currentId = (Long) rowKey;
		} else {
			this.currentId = null;
		}

	}

	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range,
			Object argument) {
		int firstResult = ((SequenceRange) range).getFirstRow();
		int maxResults = ((SequenceRange) range).getRows();

		List<Hotel> list = fetchLazyData(firstResult, maxResults);
		hotelMap = new HashMap<Long, Hotel>();
		for (Hotel row : list) {
			Long id = row.getId();
			hotelMap.put(id, row);
			visitor.process(context, id, argument);
		}
	}

	@Override
	public Object getRowData() {
		return hotelMap.get(currentId);
	}

	@Override
	public int getRowIndex() {
		return rowIndex;
	}

	@Override
	public Object getWrappedData() {
		throw new UnsupportedOperationException("Not supported");
	}

	@Override
	public boolean isRowAvailable() {
		if (currentId == null) {
			return false;
		}
		if (hotelMap.containsKey(currentId)) {
			return true;
		}
		return false;
	}

	@Override
	public void setRowIndex(int arg0) {
		this.rowIndex = arg0;

	}

	@Override
	public void setWrappedData(Object arg0) {
		throw new UnsupportedOperationException();

	}

	public String getTableState() {
		return tableState;
	}

	public void setTableState(String tableState) {
		this.tableState = tableState;
	}

}