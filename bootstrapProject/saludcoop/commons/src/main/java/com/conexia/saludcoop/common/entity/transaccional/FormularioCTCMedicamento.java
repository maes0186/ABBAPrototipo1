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
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.TipoCatastrofico;

@Entity
@Table(name = "formulario_CTC_medicamento", schema = "transaccional")
public class FormularioCTCMedicamento implements Identifiable<Long> {

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

	@OneToMany( cascade=CascadeType.ALL, mappedBy="formularioCTCMedicamento")
	private Set<MedicamentoPosPrevio> medicamentosAnteriores = new HashSet<MedicamentoPosPrevio>();

	@ManyToOne
	@JoinColumn(name="medicamento_homologo_id", nullable= true)
	private Medicamento medicamentoHomologo;

	@Column(name = "autorizado_invima", nullable = false)
	private String autorizadoINVIMA;

	@Column(name = "justificacion_riesgo_inminente", nullable = true, length = 500)
	private String justificacionRiesgoInminente;

	@ManyToOne
	@JoinColumn(name = "tipo_catastrofico_id", nullable = false)
	private TipoCatastrofico tipoCatastrofico;

	@Column(name = "existe_riesgo_inminente", nullable = false)
	private Boolean existeRiesgoInminente = false;

	@Column(name = "posibilidades_pos_agotadas", nullable = false)
	private Boolean posibilidadesPosAgotadas = false;

	@Column(name = "sin_alternativa_pos", nullable = false)
	private Boolean sinAlternativaPos = false;

	@Column(name = "justificacion_sin_pos_previo", nullable = true, length = 500)
	private String justificacionSinPosPrevio;

	@Column(name = "justificacion_medico", nullable = false, length = 500)
	private String justificacionMedico;

	@Column(name = "resumen_historia_clinica", nullable = false, length = 500)
	private String resumenHistoriaClinica;

	public Long getId() {
		return id;
	}

	public Set<MedicamentoPosPrevio> getMedicamentosAnteriores() {
		return medicamentosAnteriores;
	}

	public void setMedicamentosAnteriores(Set<MedicamentoPosPrevio> medicamentosAnteriores) {
		this.medicamentosAnteriores = medicamentosAnteriores;
		for (MedicamentoPosPrevio med : this.medicamentosAnteriores){
			med.setFormularioCTCMedicamento(this);
		}
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setExisteRiesgoInminente(boolean existeRiesgoInminente) {
		this.existeRiesgoInminente = existeRiesgoInminente;
	}

	public void setPosibilidadesPosAgotadas(boolean posibilidadesPosAgotadas) {
		this.posibilidadesPosAgotadas = posibilidadesPosAgotadas;
	}

	public Medicamento getMedicamentoHomologo() {
		return medicamentoHomologo;
	}

	public void setMedicamentoHomologo(Medicamento MedicamentoHomologo) {
		this.medicamentoHomologo = MedicamentoHomologo;
	}

	public String getAutorizadoINVIMA() {
		return autorizadoINVIMA;
	}

	public void setAutorizadoINVIMA(String autorizadoINVIMA) {
		this.autorizadoINVIMA = autorizadoINVIMA;
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

	public String getJustificacionRiesgoInminente() {
		return justificacionRiesgoInminente;
	}

	public void setJustificacionRiesgoInminente(String justificacionRiesgoInminente) {
		this.justificacionRiesgoInminente = justificacionRiesgoInminente;
	}

	public TipoCatastrofico getTipoCatastrofico() {
		return tipoCatastrofico;
	}

	public void setTipoCatastrofico(TipoCatastrofico tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
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

	public boolean isSinAlternativaPos() {
		return sinAlternativaPos;
	}

	public void setSinAlternativaPos(boolean sinAlternativaPos) {
		this.sinAlternativaPos = sinAlternativaPos;
	}

	public String getJustificacionSinPosPrevio() {
		return justificacionSinPosPrevio;
	}

	public void setJustificacionSinPosPrevio(String justificacionSinPosPrevio) {
		this.justificacionSinPosPrevio = justificacionSinPosPrevio;
	}

	public String getJustificacionMedico() {
		return justificacionMedico;
	}

	public void setJustificacionMedico(String justificacionMedico) {
		this.justificacionMedico = justificacionMedico;
	}

	public String getResumenHistoriaClinica() {
		return resumenHistoriaClinica;
	}

	public void setResumenHistoriaClinica(String resumenHistoriaClinica) {
		this.resumenHistoriaClinica = resumenHistoriaClinica;
	}

	public SolicitudMedicamento getSolicitudMedicamento() {
		return solicitudMedicamento;
	}

	public void setSolicitudMedicamento(SolicitudMedicamento solicitudMedicamento) {
		this.solicitudMedicamento = solicitudMedicamento;
	}

	public Boolean getSinAlternativaPos() {
		return sinAlternativaPos;
	}

	public void setSinAlternativaPos(Boolean sinAlternativaPos) {
		this.sinAlternativaPos = sinAlternativaPos;
	}

	public void addMedicamentoPosPrevio(
			MedicamentoPosPrevio medicamentoPosPrevio) {
			this.medicamentosAnteriores.add(medicamentoPosPrevio);
			medicamentoPosPrevio.setFormularioCTCMedicamento(this);
			
	}
	

}
