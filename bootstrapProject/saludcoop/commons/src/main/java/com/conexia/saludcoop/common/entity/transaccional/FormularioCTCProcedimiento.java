package com.conexia.saludcoop.common.entity.transaccional;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.entity.maestro.CausaExterna;
import com.conexia.saludcoop.common.entity.maestro.Finalidad;
import com.conexia.saludcoop.common.entity.maestro.TipoCatastrofico;

@Entity
@Table(name = "formulario_CTC_procedimiento", schema = "transaccional")
public class FormularioCTCProcedimiento implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name = "solicitud_procedimiento_id", nullable = false)
	private SolicitudProcedimiento solicitudProcedimiento;

	@Column(name = "resumen_historia_clinica", nullable = false, length = 500)
	private String resumenHistoriaClinica;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "formularioCTCProcedimiento")
	private Set<ProcedimientoPosPrevio> procedimientosAnteriores = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "formularioCTCProcedimiento")
	private ProcedimientoHomologo procedimientoHomologo;

	@ManyToOne
	@JoinColumn(name = "causa_externa_id", nullable = false)
	private CausaExterna causaExterna;

	@ManyToOne
	@JoinColumn(name = "finalidad_id", nullable = false)
	private Finalidad finalidad;

	@ManyToOne
	@JoinColumn(name = "tipo_catastrofico_id", nullable = false)
	private TipoCatastrofico tipoCatastrofico;

	@Column(name = "existe_riesgo_inminente", nullable = false)
	private Boolean existeRiesgoInminente = false;

	@Column(name = "justificacion_riesgo_inminente", nullable = true, length = 500)
	private String justificacionRiesgoInminente;

	@Column(name = "posibilidades_pos_agotadas", nullable = false)
	private Boolean posibilidadesPosAgotadas = false;

	@Column(name = "autorizado_invima", nullable = false)
	private String autorizadoINVIMA;

	@Column(name = "justificacion_sin_pos_previo", nullable = true, length = 500)
	private String justificacionSinPosPrevio;

	@Column(name = "sin_alternativa_pos", nullable = false)
	private Boolean sinAlternativaPos = false;
	
	@Column(name="justificacion_sin_homologo", nullable=true ,length = 500)
	private String justificacionSinHomologo;

	@Column(name = "justificacion_medico", nullable = false, length = 500)
	private String justificacionMedico;

	public String getResumenHistoriaClinica() {
		return resumenHistoriaClinica;
	}

	public void setResumenHistoriaClinica(String resumenHistoriaClinica) {
		this.resumenHistoriaClinica = resumenHistoriaClinica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ProcedimientoPosPrevio> getProcedimientosAnteriores() {
		return procedimientosAnteriores;
	}

	public void setProcedimientosAnteriores(
			Set<ProcedimientoPosPrevio> procedimientosAnteriores) {
		this.procedimientosAnteriores = procedimientosAnteriores;
		for (ProcedimientoPosPrevio procedimientoPosPrevio : this.procedimientosAnteriores) {
			procedimientoPosPrevio.setFormularioCTCProcedimiento(this);
		}
	}

	public ProcedimientoHomologo getProcedimientoHomologo() {
		return procedimientoHomologo;
	}

	public void setProcedimientoHomologo(
			ProcedimientoHomologo procedimientoHomologo) {
		this.procedimientoHomologo = procedimientoHomologo;
		this.procedimientoHomologo.setFormularioCTCProcedimiento(this);
	}

	public void setExisteRiesgoInminente(boolean existeRiesgoInminente) {
		this.existeRiesgoInminente = existeRiesgoInminente;
	}

	public void setPosibilidadesPosAgotadas(boolean posibilidadesPosAgotadas) {
		this.posibilidadesPosAgotadas = posibilidadesPosAgotadas;
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

	public String getJustificacionRiesgoInminente() {
		return justificacionRiesgoInminente;
	}

	public void setJustificacionRiesgoInminente(
			String justificacionRiesgoInminente) {
		this.justificacionRiesgoInminente = justificacionRiesgoInminente;
	}

	public String getAutorizadoINVIMA() {
		return autorizadoINVIMA;
	}

	public void setAutorizadoINVIMA(String autorizadoINVIMA) {
		this.autorizadoINVIMA = autorizadoINVIMA;
	}

	public String getJustificacionSinPosPrevio() {
		return justificacionSinPosPrevio;
	}

	public void setJustificacionSinPosPrevio(String justificacionSinPosPrevio) {
		this.justificacionSinPosPrevio = justificacionSinPosPrevio;
	}

	public boolean isSinAlternativaPos() {
		return sinAlternativaPos;
	}

	public void setSinAlternativaPos(boolean sinAlternativaPos) {
		this.sinAlternativaPos = sinAlternativaPos;
	}

	public String getJustificacionMedico() {
		return justificacionMedico;
	}

	public void setJustificacionMedico(String justificacionMedico) {
		this.justificacionMedico = justificacionMedico;
	}

	public boolean isExisteRiesgoInminente() {
		return existeRiesgoInminente;
	}

	public boolean isPosibilidadesPosAgotadas() {
		return posibilidadesPosAgotadas;
	}

	public SolicitudProcedimiento getSolicitudProcedimiento() {
		return solicitudProcedimiento;
	}

	public void setSolicitudProcedimiento(
			SolicitudProcedimiento solicitudProcedimiento) {
		this.solicitudProcedimiento = solicitudProcedimiento;
	}

	public Boolean getExisteRiesgoInminente() {
		return existeRiesgoInminente;
	}

	public void setExisteRiesgoInminente(Boolean existeRiesgoInminente) {
		this.existeRiesgoInminente = existeRiesgoInminente;
	}

	public Boolean getPosibilidadesPosAgotadas() {
		return posibilidadesPosAgotadas;
	}

	public void setPosibilidadesPosAgotadas(Boolean posibilidadesPosAgotadas) {
		this.posibilidadesPosAgotadas = posibilidadesPosAgotadas;
	}

	public Boolean getSinAlternativaPos() {
		return sinAlternativaPos;
	}

	public void setSinAlternativaPos(Boolean sinAlternativaPos) {
		this.sinAlternativaPos = sinAlternativaPos;
	}
	public void addProcedimientoPosPrevio(
			ProcedimientoPosPrevio procedimientoPosPrevio) {
			procedimientoPosPrevio.setFormularioCTCProcedimiento(this);
			this.procedimientosAnteriores.add(procedimientoPosPrevio);
	}

	public String getJustificacionSinHomologo() {
		return justificacionSinHomologo;
	}

	public void setJustificacionSinHomologo(String justificacionSinHomologo) {
		this.justificacionSinHomologo = justificacionSinHomologo;
	}

	
	
}
