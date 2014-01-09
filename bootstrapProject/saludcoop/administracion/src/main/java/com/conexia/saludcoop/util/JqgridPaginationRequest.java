package com.conexia.saludcoop.util;

import org.apache.commons.lang.StringUtils;

import ar.com.conexia.common.persistence.pagination.IPaginationRequest;
import ar.com.conexia.common.persistence.pagination.SortCriterion;
import ar.com.conexia.common.persistence.pagination.SortDirection;

/**
 * Adaptador de solicitud de paginacion, compatible con jqgrid.
 * 
 * @author ppieroni
 */
public class JqgridPaginationRequest implements IPaginationRequest {

	/**
	 * Número de página actual.
	 */
	private Integer requestedPageNumber;

	/**
	 * La cantidad de resultados a devolver por página.
	 */
	private Integer requestedPageSize;

	/**
	 * El nombre de la columna por la cual ordenar.
	 */
	private SortCriterion sortCriterion;

	/**
	 * Asigna el número de página a buscar.
	 * 
	 * @param value Número de página a buscar.
	 */
	public final void setPage(final Integer value) {

		this.requestedPageNumber = value;
	}

	@Override
	public final Integer getRequestedPageNumber() {

		return (this.requestedPageNumber);
	}

	/**
	 * Asigna el tamaño de página a usar.
	 * 
	 * @param value Tamaño de página a usar.
	 */
	public final void setRows(final Integer value) {

		this.requestedPageSize = value;
	}

	@Override
	public final Integer getRequestedPageSize() {

		return (this.requestedPageSize);
	}

	/**
	 * Asigna el nombre de la columna a partir de la cual ordenar.
	 * 
	 * @param value Nombre de la columna a partir de la cual ordenar.
	 */
	public final void setSidx(final String value) {

		if (this.sortCriterion == null) {
			this.sortCriterion = new SortCriterion();
		}

		this.sortCriterion.setFieldName(value);
	}

	/**
	 * Asigna el orden a usar en el ordenamiento de una columna.
	 * 
	 * @param value Orden a usar en el ordenamiento de una columna.
	 */
	public final void setSord(final String value) {

		if (this.sortCriterion == null) {
			this.sortCriterion = new SortCriterion();
		}

		this.sortCriterion.setDirection(SortDirection.fromValue(value));
	}

	@Override
	public final SortCriterion getSortCriterion() {

		if (this.sortCriterion == null || StringUtils.isBlank(this.sortCriterion.getFieldName())
		        || this.sortCriterion.getDirection() == null) {

			return (null);
		}

		return (this.sortCriterion);
	}

	@Override
	public final String toString() {

		return ("RequestedPageNumber="
		        + ((this.requestedPageNumber != null) ? this.requestedPageNumber : "(null)") + ","
		        + "RequestedPageSize="
		        + ((this.requestedPageSize != null) ? this.requestedPageSize : "(null)") + ","
		        + "SortCriterion=" + ((this.sortCriterion != null) ? this.sortCriterion.toString()
		            : "(null)"));
	}
}
