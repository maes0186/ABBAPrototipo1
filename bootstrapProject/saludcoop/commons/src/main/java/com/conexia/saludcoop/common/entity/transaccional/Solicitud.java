package com.conexia.saludcoop.common.entity.transaccional;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Profesional;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;
import com.conexia.saludcoop.common.entity.security.User;

/**
 * Solicitud de Servicio de salud
 * 
 * @author fcostazini
 * 
 */
@Entity
@Table(name = "solicitud", schema = "transaccional")
@org.hibernate.annotations.Entity(dynamicInsert=true)
public class Solicitud implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;

	@Column(name="numero_solicitud", nullable = false)
	private Integer nroSolicitud; 
	
	@ManyToOne
	@JoinColumn(nullable = false, name="sede_ips_id")
	private SedeIps sedeIps;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Afiliado afiliado;
	
	@ManyToOne
	@JoinColumn(name = "profesional_id", nullable = false)
	private Profesional profesionalSolicitante;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "solicitud_id", nullable = false)
	private Set<SolicitudDiagnostico> solDiagnosticos;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="solicitud")
	private Set<DocumentoSoporte> documentosSoporte;

	@OneToOne( cascade=CascadeType.ALL)
	@JoinColumn(name="resumen_historia_clinica_id", nullable=true)
	private ResumenHistoriaClinica resumenHistoriaClinica;

	@OneToMany(mappedBy="solicitud",cascade=CascadeType.ALL)
	private Set<SolicitudItem> solicitudItems = new HashSet<SolicitudItem>();
	
	@Column(name = "fecha_creacion")
	private Date fechaCreacion = new Date();
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User usuarioCreador;
	
	@Column(name="primera_formulacion_anio", nullable=false)
	private Short primeraFormulacionDelAnio = 0;
	
	@Column(name="observaciones", nullable= true, length=500)
	private String observacion;
	
	@OneToMany(mappedBy="solicitud", cascade=CascadeType.ALL)
	private Set<GrupoAutorizacion> grupoAutorizacion = new HashSet<>();
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public void setPrimeraFormulacionDelAnio(Short primeraFormulacionDelAnio) {
		this.primeraFormulacionDelAnio = primeraFormulacionDelAnio;
	}

	public SedeIps getSedeIps() {
		return sedeIps;
	}

	public void setSedeIps(SedeIps sedeIps) {
		this.sedeIps = sedeIps;
	}

	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public Profesional getProfesionalSolicitante() {
		return profesionalSolicitante;
	}

	public void setProfesionalSolicitante(Profesional profesionalSolicitante) {
		this.profesionalSolicitante = profesionalSolicitante;
	}

	public Set<SolicitudDiagnostico> getSolDiagnosticos() {
		return solDiagnosticos;
	}

	public void setSolDiagnosticos(Set<SolicitudDiagnostico> solDiagnosticos) {
		this.solDiagnosticos = solDiagnosticos;
	}



	public Set<DocumentoSoporte> getDocumentosSoporte() {
		return Collections.unmodifiableSet(documentosSoporte);
	}

	
	public void addDocumentoSoporte(DocumentoSoporte doc){
		if(doc != null){
			if(this.documentosSoporte == null){
				this.documentosSoporte = new HashSet<>();
			}
			this.documentosSoporte.add(doc);
			doc.setSolicitud(this);
		}
	}


	public ResumenHistoriaClinica getResumenHistoriaClinica() {
		return resumenHistoriaClinica;
	}

	public void setResumenHistoriaClinica(
			ResumenHistoriaClinica resumenHistoriaClinica) {
		this.resumenHistoriaClinica = resumenHistoriaClinica;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getNroSolicitud() {
		return nroSolicitud;
	}

	public void setNroSolicitud(Integer nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}

	public Set<SolicitudItem> getSolicitudItems() {
		return solicitudItems;
	}

	public void setSolicitudItems(Set<SolicitudItem> solicitudItems) {
		this.solicitudItems = solicitudItems;
	}

	

	public User getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(User usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	
	public void addSolicitudItem(SolicitudItem solicitudItem){
		if(this.solicitudItems == null){
			this.solicitudItems = new HashSet<SolicitudItem>();
		}
		this.solicitudItems.add(solicitudItem);
		solicitudItem.setSolicitud(this);
	}
	@Override
	public Long getId() {

		return this.id;
	}

	
	
	public boolean getPrimeraFormulacionDelAnio() {
		return primeraFormulacionDelAnio == 1;
	}

	public void setPrimeraFormulacionDelAnio(boolean primeraFormulacionDelAnio) {
		this.primeraFormulacionDelAnio = (short) (primeraFormulacionDelAnio ?  1 :  0);
	}
	
	
	public void addGrupoAutorizacion(GrupoAutorizacion grupo){
		this.grupoAutorizacion.add(grupo);
		grupo.setSolicitud(this);
	}
	
	public Set<GrupoAutorizacion> getGrupoAutorizacion(){
		return Collections.unmodifiableSet(this.grupoAutorizacion);
	}

	public void setGrupoAutorizacion(Set<GrupoAutorizacion> grupoAutorizacion) {
		this.grupoAutorizacion = grupoAutorizacion;
	}

}
