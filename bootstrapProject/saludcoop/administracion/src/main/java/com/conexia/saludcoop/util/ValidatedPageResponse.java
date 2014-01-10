package com.conexia.saludcoop.util;

/**
 * Clase que envuelve una respuesta a una petición a servidor junto con mensajes de validaci&oacute;n. Además permite realizar paginación
 * sobre los registros retornados El contenido de la respuesta está parametrizado.
 * 
 * @param <T>
 *            Tipo de la respuesta del servidor.
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 07/10/2013
 */
public class ValidatedPageResponse<T> extends ValidatedResponse<T> {

    /**
     * Página actual dentro del paginador
     */
    private Integer actualPage;
    /**
     * Total de páginas retornadas por la consulta
     */
    private Integer totalPages;
    
    private Long totalItems;
    
    public Integer getActualPage() {
        return actualPage;
    }
    
    public void setActualPage(Integer actualPage) {
        this.actualPage = actualPage;
    }
    
    public Integer getTotalPages() {
        return totalPages;
    }
    
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}
}
