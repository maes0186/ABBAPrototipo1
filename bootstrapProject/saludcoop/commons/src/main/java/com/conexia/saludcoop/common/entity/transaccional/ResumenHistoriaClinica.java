package com.conexia.saludcoop.common.entity.transaccional;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.entity.maestro.CausaExterna;
import com.conexia.saludcoop.common.entity.maestro.TipoCatastrofico;

@Entity
@Table(name = "resumen_historia_clinica", schema = "transaccional")
@Mappeable(mappedTo=AfiliadoDto.class)
public class ResumenHistoriaClinica {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name="fecha_inicio", nullable=false)
	private Date fechaInicio;
	
	@Column(name="fecha_fin", nullable=false)
	private Date fechaFin;
	
	@Column(name = "evolucion", nullable = false, length = 255)
	private String evolucion;
	
	@ManyToOne
	@JoinColumn(name = "causa_externa_id", nullable = false)
	private CausaExterna causaExterna;
	
	@ManyToOne
	@JoinColumn(name = "tipo_catastrofico_id", nullable = false)
	private TipoCatastrofico tipoCatastrofico;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "resumen_id", nullable = false)
	private Set<ResumenDiagnostico> resumenDiagnosticos;
	
	@Column(name = "conducta", nullable = false, length = 255)
	private String conducta;
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEvolucion() {
		return evolucion;
	}
	public void setEvolucion(String evolucion) {
		this.evolucion = evolucion;
	}
	public CausaExterna getCausaExterna() {
		return causaExterna;
	}
	public void setCausaExterna(CausaExterna causaExterna) {
		this.causaExterna = causaExterna;
	}


	public TipoCatastrofico getTipoCatastrofico() {
		return tipoCatastrofico;
	}
	public void setTipoCatastrofico(TipoCatastrofico tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
	}
	public Long getId() {
		return id;
	}
	public Set<ResumenDiagnostico> getResumenDiagnosticos() {
		return resumenDiagnosticos;
	}
	public void setResumenDiagnosticos(Set<ResumenDiagnostico> resumenDiagnosticos) {
		this.resumenDiagnosticos = resumenDiagnosticos;
	}
	public String getConducta() {
		return conducta;
	}
	public void setConducta(String conducta) {
		this.conducta = conducta;
	}	
}
