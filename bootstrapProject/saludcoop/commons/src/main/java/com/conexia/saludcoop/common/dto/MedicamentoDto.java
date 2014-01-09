package com.conexia.saludcoop.common.dto;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;

@Mappeable(mappedTo=Medicamento.class)
public class MedicamentoDto { 
	private Long id;
	private DescriptivoDto tipoPPM;
	private String codigo;
	private String descripcion;
	private boolean visibleCtc;
	private boolean altoCosto;
	/**
	 * Tipo de pago requerido.
	 */
	private TipoPagoRequerido tipoPagoRequerido;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setTipoPPM(DescriptivoDto tipoPPM){
		this.tipoPPM = tipoPPM;
	}

	public DescriptivoDto getTipoPPM(){
		return this.tipoPPM;
	}
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}

	public String getCodigo(){
		return this.codigo;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion(){
		return this.descripcion;
	}

	public boolean isVisibleCtc() {
		return visibleCtc;
	}

	public void setVisibleCtc(boolean visibleCtc) {
		this.visibleCtc = visibleCtc;
	}

	/**
	 * Obtiene el tipo de pago requerido.
	 * 
	 * @return Tipo de pago requerido.
	 */
	public TipoPagoRequerido getTipoPagoRequerido() {
		
		return (this.tipoPagoRequerido);
	}

	/**
	 * Asigna el tipo de pago requerido.
	 * 
	 * @param tipoPagoRequerido Tipo de pago requerido.
	 */
	public void setTipoPagoRequerido(final TipoPagoRequerido tipoPagoRequerido) {
		
		this.tipoPagoRequerido = tipoPagoRequerido;
	}

    public boolean isAltoCosto() {
        return altoCosto;
    }

    public void setAltoCosto(boolean altoCosto) {
        this.altoCosto = altoCosto;
    }
}
