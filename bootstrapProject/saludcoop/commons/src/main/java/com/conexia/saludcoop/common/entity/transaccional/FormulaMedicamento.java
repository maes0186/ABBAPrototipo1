package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.dto.transaccional.FormulaMedicamentoDto;
import com.conexia.saludcoop.common.entity.maestro.CausaExterna;
import com.conexia.saludcoop.common.entity.maestro.Finalidad;
import com.conexia.saludcoop.common.entity.maestro.TipoCatastrofico;
import com.conexia.saludcoop.common.entity.maestro.ViaAdministracion;
import com.conexia.saludcoop.common.enumerator.TipoFrecuencia;
@Entity
@Table(name="formula_item_medicamento",schema="transaccional")
public class FormulaMedicamento implements Identifiable<Long> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name="solicitud_medicamento_id", nullable=false)
	private SolicitudMedicamento solicitudMedicamento;
		
	@ManyToOne
	@JoinColumn(name = "causa_externa_id", nullable = false)
	private CausaExterna causaExterna;

	@ManyToOne
	@JoinColumn(name = "finalidad_id", nullable = false)
	private Finalidad finalidad;
	
	@ManyToOne
	@JoinColumn(name = "tipo_catastrofico_id", nullable = false)
	private TipoCatastrofico tipoCatastrofico;
	
	@Column(name="dosis", nullable=false)
	private Integer dosis;
	
	@Column(name="frecuencia", nullable=false)
	private Integer frecuencia;
	
	@Column(name="duracion", nullable=false)
	private Integer duracion;
	
	@ManyToOne
	@JoinColumn(name="via_administracion_id", nullable = false)
	private ViaAdministracion viaAdministracion;
	
	@Column(name="posologia", nullable=false, length=500)
	private String posologia;
	
	@Column(name="efectosAdversos", nullable=true, length=500)
	private String efectosAdversos;

    @Column(name="tipo_frecuencia_enum", nullable = false)
    private Integer tipoFrecuenciaId;
    
	
	
	public String getEfectosAdversos() {
		return efectosAdversos;
	}

	public void setEfectosAdversos(String efectosAdversos) {
		this.efectosAdversos = efectosAdversos;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Integer getDosis() {
		return dosis;
	}

	public void setDosis(Integer dosis) {
		this.dosis = dosis;
	}

	public Integer getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public ViaAdministracion getViaAdministracion() {
		return viaAdministracion;
	}

	public void setViaAdministracion(ViaAdministracion viaAdministracion) {
		this.viaAdministracion = viaAdministracion;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}
	
	public SolicitudMedicamento getSolicitudMedicamento() {
		return solicitudMedicamento;
	}

	public void setSolicitudMedicamento(SolicitudMedicamento solicitudMedicamento) {
		this.solicitudMedicamento = solicitudMedicamento;
	}

	public FormulaMedicamentoDto toDto(){
	    FormulaMedicamentoDto dto = new FormulaMedicamentoDto();
	    dto.setDosis(this.dosis);
	    dto.setDuracion(this.duracion);
	    dto.setEfectosAdversos(this.efectosAdversos);
	    dto.setFrecuencia(this.frecuencia);
	    dto.setId(this.id);
	    dto.setPosologia(this.posologia);
	    dto.setViaAdministracion(viaAdministracion.toDto());
	    dto.setTipoFrecuencia(TipoFrecuencia.fromId(this.getTipoFrecuenciaId()));
	    return dto;
	}

    public Integer getTipoFrecuenciaId() {
        return tipoFrecuenciaId;
    }

    public void setTipoFrecuenciaId(Integer tipoFrecuenciaId) {
        this.tipoFrecuenciaId = tipoFrecuenciaId;
    }	
	public CausaExterna getCausaExterna() {
		return causaExterna;
	}

	public void setCausaExterna(CausaExterna causaExterna) {
		this.causaExterna = causaExterna;
	}

	public Finalidad getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(Finalidad finalidad) {
		this.finalidad = finalidad;
	}

	public TipoCatastrofico getTipoCatastrofico() {
		return tipoCatastrofico;
	}

	public void setTipoCatastrofico(TipoCatastrofico tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
	}
}
