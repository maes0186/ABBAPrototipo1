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

import com.conexia.saludcoop.common.entity.maestro.CausaExterna;
import com.conexia.saludcoop.common.entity.maestro.Finalidad;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.TipoCatastrofico;

@Entity
@Table(name = "formulario_CTC_insumo", schema = "transaccional")
public class FormularioCTCInsumo implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name="solicitud_insumo_id", nullable=false)
	private SolicitudInsumo solicitudInsumo;
	
	
	@ManyToOne
	@JoinColumn(name = "causa_externa_id", nullable = false)
	private CausaExterna causaExterna;

	@ManyToOne
	@JoinColumn(name = "finalidad_id", nullable = false)
	private Finalidad finalidad;

//	@OneToMany( cascade=CascadeType.ALL, mappedBy="formularioCTCInsumo")
//	private Set<InsumoPosPrevio> insumosAnteriores = new HashSet<InsumoPosPrevio>();

	@ManyToOne
	@JoinColumn(name="insumo_homologo_id", nullable= true)
	private Insumo insumoHomologo;

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

//	public Set<InsumoPosPrevio> getInsumosAnteriores() {
//		return insumosAnteriores;
//	}
//
//	public void setInsumosAnteriores(Set<InsumoPosPrevio> insumosAnteriores) {
//		this.insumosAnteriores = insumosAnteriores;
//		for (InsumoPosPrevio ins : this.insumosAnteriores){
//			ins.setFormularioCTCInsumo(this);
//		}
//	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setExisteRiesgoInminente(boolean existeRiesgoInminente) {
		this.existeRiesgoInminente = existeRiesgoInminente;
	}

	public void setPosibilidadesPosAgotadas(boolean posibilidadesPosAgotadas) {
		this.posibilidadesPosAgotadas = posibilidadesPosAgotadas;
	}

	public Insumo getInsumoHomologo() {
		return insumoHomologo;
	}

	public void setInsumoHomologo(Insumo InsumoHomologo) {
		this.insumoHomologo = InsumoHomologo;
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

	public SolicitudInsumo getSolicitudInsumo() {
		return solicitudInsumo;
	}

	public void setSolicitudInsumo(SolicitudInsumo solicitudInsumo) {
		this.solicitudInsumo = solicitudInsumo;
	}

	public Boolean getSinAlternativaPos() {
		return sinAlternativaPos;
	}

	public void setSinAlternativaPos(Boolean sinAlternativaPos) {
		this.sinAlternativaPos = sinAlternativaPos;
	}

//	public void addInsumoPosPrevio(
//			InsumoPosPrevio insumoPosPrevio) {
//			this.insumosAnteriores.add(insumoPosPrevio);
//			insumoPosPrevio.setFormularioCTCInsumo(this);
//			
//	}
	

}

