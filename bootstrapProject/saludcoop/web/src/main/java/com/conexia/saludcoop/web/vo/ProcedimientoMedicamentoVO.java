package com.conexia.saludcoop.web.vo;

import java.util.Set;

import com.conexia.saludcoop.common.dto.EspecialidadDto;
import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;


public class ProcedimientoMedicamentoVO {
	private Long id;
	private String tipoPPM;
	private String codigo;
	private String descripcion;
	private Integer nivel;
	private Boolean visibleCtc;
	private Set<EspecialidadDto> especialidades;
	
	
	public ProcedimientoMedicamentoVO(Long id, String tipoPPM, String codigo, String descripcion, Set<EspecialidadDto> especialidades, Integer nivel, Boolean visibleCTC) {
		this.id = id;
		this.tipoPPM = tipoPPM;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.especialidades = especialidades;
		this.nivel = nivel;
		this.visibleCtc = visibleCTC;
	}
	public ProcedimientoMedicamentoVO(ProcedimientoDto o){
		this.id = o.getId();
		this.tipoPPM = o.getTipoPPM().getDescripcion();
		this.codigo = o.getCodigo();
		this.descripcion = o.getDescripcion();
		this.especialidades = o.getEspecialidades();
		this.nivel = o.getNivelDeAutorizacion();
		this.visibleCtc = false;
	}
	public ProcedimientoMedicamentoVO(MedicamentoDto o){
		this.id = o.getId();
		this.tipoPPM = o.getTipoPPM().getDescripcion();
		this.codigo = o.getCodigo();
		this.descripcion = o.getDescripcion();
		this.visibleCtc = o.isVisibleCtc();
		this.nivel = 0;
	}
	
	public ProcedimientoMedicamentoVO(InsumoDto o) {
		this.id = o.getId();
		this.tipoPPM = o.getTipoPPM().getDescripcion();
		this.codigo = o.getCodigo();
		this.descripcion = o.getDescripcion();
		this.visibleCtc = o.isVisibleCtc();
		this.nivel = o.getNivelDeAutorizacion().intValue();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoPPM() {
		return tipoPPM;
	}
	public void setTipoPPM(String tipoPPM) {
		this.tipoPPM = tipoPPM;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public boolean isVisibleCtc() {
		return visibleCtc;
	}
	public void setVisibleCtc(boolean visibleCtc) {
		this.visibleCtc = visibleCtc;
	}
	public void setEspecialidades(Set<EspecialidadDto> especialidades) {
		this.especialidades = especialidades;
	}
	public Set<EspecialidadDto> getEspecialidades() {
		return especialidades;
	}
	
	
	
}
