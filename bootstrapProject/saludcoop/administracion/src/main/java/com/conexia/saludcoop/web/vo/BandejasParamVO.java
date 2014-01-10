/**
 * 
 */
package com.conexia.saludcoop.web.vo;

/** 
 * Clase usada para guardar parámetros generales de configuración para las bandejas
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 21/10/2013
 * 
 */
public class BandejasParamVO {
    private boolean editable;
    private boolean editableAdjuntos;
    private boolean editableRedireccion;
    private int estado;
    private String tipoBandeja;
    private int caseDevoluciones;
    private boolean editableRespuesta;
    
    /**
     * Array booleano que indica que acciones se deben mostrar en la sección
     * para dar el concepto por parte del auditor regional o nacional. El orden de los
     * booleanos es: [Aprobar, No aprobar, Anular, Devolver, Responder]
     */
    private boolean[] mostrarAcciones = new boolean[5];
    
    public boolean isEditable() {
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public String getTipoBandeja() {
        return tipoBandeja;
    }
    public void setTipoBandeja(String tipoBandeja) {
        this.tipoBandeja = tipoBandeja;
    }
    public int getCaseDevoluciones() {
        return caseDevoluciones;
    }
    public void setCaseDevoluciones(int caseDevoluciones) {
        this.caseDevoluciones = caseDevoluciones;
    }
    /**
     * Array booleano que indica que acciones se deben mostrar en la sección
     * para dar el concepto por parte del auditor regional o nacional. El orden de los
     * booleanos es: [Aprobar, No aprobar, Anular, Devolver, Responder]
     */
    public boolean[] getMostrarAcciones() {
        return mostrarAcciones;
    }
    public void setMostrarAcciones(boolean[] mostrarAcciones) {
        this.mostrarAcciones = mostrarAcciones;
    }
    public boolean isEditableRespuesta() {
        return editableRespuesta;
    }
    public void setEditableRespuesta(boolean editableRespuesta) {
        this.editableRespuesta = editableRespuesta;
    }
    public boolean isEditableRedireccion() {
        return editableRedireccion;
    }
    public void setEditableRedireccion(boolean editableRedireccion) {
        this.editableRedireccion = editableRedireccion;
    }
	public boolean isEditableAdjuntos() {
		return editableAdjuntos;
	}
	public void setEditableAdjuntos(boolean editableAdjuntos) {
		this.editableAdjuntos = editableAdjuntos;
	}
}
