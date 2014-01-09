package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.enumerator.TipoFrecuencia;


public class FormulaMedicamentoDto { 
	private Long id;
	private Integer causaExterna;
	private Integer finalidad;
	private Integer tipoCatastrofico;
	private Integer dosis;
	private Integer frecuencia;
	private Integer cada;
	private Integer duracion;
	private DescriptivoDto viaAdministracion;
	private String posologia;
	private String efectosAdversos;
	private TipoFrecuencia tipoFrecuencia;
	
	
	public TipoFrecuencia getTipoFrecuencia() {
		return tipoFrecuencia;
	}

	public void setTipoFrecuencia(TipoFrecuencia tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
	}

	public String getEfectosAdversos() {
		return efectosAdversos;
	}

	public void setEfectosAdversos(String efectosAdversos) {
		this.efectosAdversos = efectosAdversos;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setDosis(Integer dosis){
		this.dosis = dosis;
	}

	public Integer getDosis(){
		return this.dosis;
	}
	public void setFrecuencia(Integer frecuencia){
		this.frecuencia = frecuencia;
	}

	public Integer getFrecuencia(){
		return this.frecuencia;
	}
	public void setDuracion(Integer duracion){
		this.duracion = duracion;
	}

	public Integer getDuracion(){
		return this.duracion;
	}
	public void setViaAdministracion(DescriptivoDto viaAdministracion){
		this.viaAdministracion = viaAdministracion;
	}

	public DescriptivoDto getViaAdministracion(){
		return this.viaAdministracion;
	}
	public void setPosologia(String posologia){
		this.posologia = posologia;
	}

	public String getPosologia(){
		return this.posologia;
	}

    public Integer getCada() {
        return cada;
    }

    public void setCada(Integer cada) {
        this.cada = cada;
    }

	public Integer getCausaExterna() {
		return causaExterna;
	}

	public void setCausaExterna(Integer causaExterna) {
		this.causaExterna = causaExterna;
	}

	public Integer getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(Integer finalidad) {
		this.finalidad = finalidad;
	}

	public Integer getTipoCatastrofico() {
		return tipoCatastrofico;
	}

	public void setTipoCatastrofico(Integer tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
	}
    

}
