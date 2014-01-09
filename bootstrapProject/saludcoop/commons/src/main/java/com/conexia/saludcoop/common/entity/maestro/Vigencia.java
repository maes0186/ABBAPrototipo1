package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vigencia",schema="maestros")
public class Vigencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="tipo_tecnologia_id", nullable = false)
	private TipoTecnologia tipoTecnologia;
	
	@ManyToOne
	@JoinColumn(name="tipo_ppm_id", nullable = false)
	private TipoPPM tipoPPM;
	
	@Column(name="dias_vigencia", nullable = true)
	private Integer diasVigencia;

	@Column(name="horas_vigencia", nullable = true)
	private Integer horasVigencia;

	
	public Integer getId() {
		return id;
	}

	public TipoTecnologia getTipoTecnologia() {
		return tipoTecnologia;
	}

	public void setTipoTecnologia(TipoTecnologia tipoTecnologia) {
		this.tipoTecnologia = tipoTecnologia;
	}

	public TipoPPM getTipoPPM() {
		return tipoPPM;
	}

	public void setTipoPPM(TipoPPM tipoPPM) {
		this.tipoPPM = tipoPPM;
	}

	public Integer getDiasVigencia() {
		return diasVigencia;
	}

	public void setDiasVigencia(Integer diasVigencia) {
		this.diasVigencia = diasVigencia;
	}

	public Integer getHorasVigencia() {
		return horasVigencia;
	}

	public void setHorasVigencia(Integer horasVigencia) {
		this.horasVigencia = horasVigencia;
	}
	
}
