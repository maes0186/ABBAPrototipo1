package com.conexia.saludcoop.common.dto;

import java.util.Set;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;
@Mappeable(mappedTo=Procedimiento.class)
public class ProcedimientoDto { 
	private long serialVersionUID;
	private Long id;
	private String codigo;
	private String descripcion;
	private DescriptivoDto tipoPPM;
	private Set<DiagnosticoDto> diagnosticos;
	private Set<EspecialidadDto> especialidades;
	private Integer nivelDeAtencion;
	private Integer nivelDeAutorizacion;
	
	/**
	 * Tipo de pago requerido.
	 */
	private TipoPagoRequerido tipoPagoRequerido;

	public void setSerialVersionUID(long serialVersionUID){
		this.serialVersionUID = serialVersionUID;
	}

	public long getSerialVersionUID(){
		return this.serialVersionUID;
	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
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
	public void setTipoPPM(DescriptivoDto tipoPPM){
		this.tipoPPM = tipoPPM;
	}

	public DescriptivoDto getTipoPPM(){
		return this.tipoPPM;
	}
	public void setDiagnosticos(Set<DiagnosticoDto> diagnosticos){
		this.diagnosticos = diagnosticos;
	}

	public Set<DiagnosticoDto> getDiagnosticos(){
		return this.diagnosticos;
	}

	public Set<EspecialidadDto> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<EspecialidadDto> especialidades) {
		this.especialidades = especialidades;
	}

	public Integer getNivelDeAtencion() {
		return nivelDeAtencion;
	}

	public void setNivelDeAtencion(Integer nivelDeAtencion) {
		this.nivelDeAtencion = nivelDeAtencion;
	}

	public Integer getNivelDeAutorizacion() {
		return nivelDeAutorizacion;
	}

	public void setNivelDeAutorizacion(Integer nivelDeAutorizacion) {
		this.nivelDeAutorizacion = nivelDeAutorizacion;
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
}
