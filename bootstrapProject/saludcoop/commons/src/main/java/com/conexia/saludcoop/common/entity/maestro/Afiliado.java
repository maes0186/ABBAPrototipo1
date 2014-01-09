package com.conexia.saludcoop.common.entity.maestro;

import java.util.Date;
import java.util.Set;

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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.enumerator.TipoAfiliado;

/**
 * Entidad afiliado.
 * 
 * @author ebarbin
 * 
 */
@Entity
@Table(name = "afiliado", schema = "maestros")
public class Afiliado extends BaseMaestro implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="tipo_identificacion_id", nullable = false)
	@ForeignKey(name="fk_afiliado_tipo_identificacion")
	private TipoIdentificacion tipoIdentificacion;

	@Index(name="ix_afiliado_numero_identificacion")
	@Column(name = "numero_identificacion", nullable = false, length=20)
	private String numeroIdentificacion;

	@Index(name="ix_afiliado_primer_nombre")
	@Column(name = "primer_nombre", nullable = false, length=30)
	private String primerNombre;

	@Index(name="ix_afiliado_segundo_nombre")
	@Column(name = "segundo_nombre", nullable = false, length=30)
	private String segundoNombre;

	@Index(name="ix_afiliado_primer_apellido")
	@Column(name = "primer_apellido", nullable = false, length=30)
	private String primerApellido;

	@Index(name="ix_afiliado_segundo_apellido")
	@Column(name = "segundo_apellido", nullable = false, length=30)
	private String segundoApellido;

	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fechaNacimiento;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="afiliado")
	private Set<AfiliadoPrograma> programas;
	

	@OneToOne
	@JoinColumn( nullable = false)
	@ForeignKey(name="fk_afiliado_genero")
	private Genero genero;
	
	@OneToOne
	@JoinColumn(name="estado_civil_id", nullable = false)
	@ForeignKey(name="fk_afiliado_estado_civil")
	private EstadoCivil estadoCivil;

	@Column(name = "direccion_de_residencia", nullable = true, length=100)
	private String direccionDeResidencia;
	
	@Column(name = "telefono_residencial", nullable = true, length=20)
	private String telefonoResidencial;

	
	@Column(name = "telefono_celular", nullable = true, length=20)
	private String telefonoCelular;
	
	@OneToOne
	@JoinColumn( name="departamento_seccional_id", nullable = false)
	@ForeignKey(name="fk_afiliado_departamento_seccional")
	private Departamento departamentoSeccional;
	

	@OneToOne
	@JoinColumn( name="localidad_id")
	@ForeignKey(name="fk_afiliado_localidad")
	private Localidad localidad;
	
	@ManyToOne
	@JoinColumn(name="municipio_residencia_id", nullable = false)
	@ForeignKey(name="fk_afiliado_municipio")
	private Municipio municipioResidencia;

	@Column(name = "email_personal", nullable = false, length=50)
	private String emailPersonal;

	@ManyToOne
	@JoinColumn( name="sede_ips_afiliacion_id", nullable = true)
	@ForeignKey(name="fk_afiliado_sede_ips_afiliacion")
	private SedeIps sedeIpsAfiliacion;


	@Column(name = "fecha_afiliacion_ips", nullable = false)
	private Date fechaAfiliacionIps;

	@Column(name = "sem_cotizadas", nullable = true)
	private Integer semCotizadas;
	
	@Column(name = "fecha_afiliacion_sgsss", nullable = false)
	private Date fechaAfiliacionSGSS;
	
	@OneToOne
	@JoinColumn( name="estado_afiliacion_id", nullable = false)
	@ForeignKey(name="fk_afiliado_estado_afiliacion")
	private EstadoAfiliacion estadoAfiliacion; // Relacionado con una razon de estado
	
	@Column(name = "razon_estado_afiliacion", length=100)
	private String razonEstadoAfiliacion;

	@Column(name = "tipo_afiliado_id", nullable = false)
	private Long tipoAfiliadoId;
	
	@Column(name="sem_eps", nullable = true)
	private Integer semEps;
	
	@Column(name="sem_sgsss", nullable = true)
	private Integer semSGSSS;

	/**
	 * Cantidad de dias afiliado a SALUD.
	 */
	@Column(name = "dias_continuos", nullable = false)
	private Integer diasContinuos;

	/**
	 * Cantidad de semanas desde fecha de afiliacion.
	 */
	@Column(name = "semanas_cotizadas", nullable = false)
	private Integer semanasCotizadas;

	/**
	 * Al ingresar el afiliado tiene un mes donde solo puede atenderse por
	 * urgencia. Por lo tanto el campo indica cuando termina ese mes.
	 */
	@Column(name = "fecha_fin_urgencias", nullable = false)
	private Date fechaFinUrgencias;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@ForeignKey(name="fk_afiliado_eps")
	private Eps eps;
	
	@OneToOne
	@JoinColumn(nullable = true, name="nivel_ibc_id")
	@ForeignKey(name="fk_afiliado_nivel_ibc")
	private NivelIBC nivelIbc;
	
	@OneToOne
	@JoinColumn( name="nivel_sisben_id", nullable = true)
	@ForeignKey(name="fk_afiliado_nivel_sisben")
	private NivelSISBEN nivelSisBen;
	
	@ManyToOne
	@JoinColumn(name = "grupo_poblacional_id")
	private GrupoPoblacional grupoPoblacional;
	
	@Column(name="regimen_afiliacion_enum", nullable = false)
	private Long regimenAfiliacionId;
	
	
	@Column(name="tutela", nullable = false)
	private Short tutela;
	
	@Column(name="fecha_expedicion")
	private Date fechaExpedicion;
	
	@Column(name="cliente_pk")
	private Integer clientePk;
	
	@OneToOne(mappedBy="afiliado")
	private AfiliadoCotizante afiliadoCotizante;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(
			TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getDireccionDeResidencia() {
		return direccionDeResidencia;
	}

	public void setDireccionDeResidencia(String direccionDeResidencia) {
		this.direccionDeResidencia = direccionDeResidencia;
	}

	public String getTelefonoResidencial() {
		return telefonoResidencial;
	}

	public void setTelefonoResidencial(String telefonoResidencial) {
		this.telefonoResidencial = telefonoResidencial;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}



	public Municipio getMunicipioResidencia() {
		return municipioResidencia;
	}

	public void setMunicipioResidencia(Municipio municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}



	public Date getFechaAfiliacionIps() {
		return fechaAfiliacionIps;
	}

	public void setFechaAfiliacionIps(Date fechaAfiliacionIps) {
		this.fechaAfiliacionIps = fechaAfiliacionIps;
	}

	public Date getFechaAfiliacionSGSS() {
		return fechaAfiliacionSGSS;
	}

	public void setFechaAfiliacionSGSS(Date fechaAfiliacionSGSS) {
		this.fechaAfiliacionSGSS = fechaAfiliacionSGSS;
	}


	public Integer getDiasContinuos() {
		return diasContinuos;
	}

	public void setDiasContinuos(Integer diasContinuos) {
		this.diasContinuos = diasContinuos;
	}

	public Integer getSemanasCotizadas() {
		return semanasCotizadas;
	}

	public void setSemanasCotizadas(Integer semanasCotizadas) {
		this.semanasCotizadas = semanasCotizadas;
	}

	public Date getFechaFinUrgencias() {
		return fechaFinUrgencias;
	}

	public void setFechaFinUrgencias(Date fechaFinUrgencias) {
		this.fechaFinUrgencias = fechaFinUrgencias;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public NivelIBC getNivelIbc() {
		return nivelIbc;
	}

	public void setNivelIbc(NivelIBC nivelIbc) {
		this.nivelIbc = nivelIbc;
	}

	public NivelSISBEN getNivelSisBen() {
		return nivelSisBen;
	}

	public void setNivelSisBen(NivelSISBEN nivelSisBen) {
		this.nivelSisBen = nivelSisBen;
	}

	/**
	 * Obtiene el régimen de afiliación.
	 * 
	 * @return Régimen de afiliación.
	 */
	public RegimenAfiliacion getRegimenAfiliacion() {
		
		return (RegimenAfiliacion.fromId(this.regimenAfiliacionId));
	}

	/**
	 * Asigna el régimen de afiliación.
	 * 
	 * @param regimenAfiliacion Régimen de afiliación.
	 */
	public void setRegimenAfiliacion(final RegimenAfiliacion regimenAfiliacion) {
		
		if (regimenAfiliacion != null) {
			this.regimenAfiliacionId = regimenAfiliacion.getId();
		} else {
			this.regimenAfiliacionId = null;
		}
	}



	public EstadoAfiliacion getEstadoAfiliacion() {
		return estadoAfiliacion;
	}

	public void setEstadoAfiliacion(EstadoAfiliacion estadoAfiliacion) {
		this.estadoAfiliacion = estadoAfiliacion;
	}

	public SedeIps getSedeIpsAfiliacion() {
		return sedeIpsAfiliacion;
	}

	public void setSedeIpsAfiliacion(SedeIps sedeIpsAfiliacion) {
		this.sedeIpsAfiliacion = sedeIpsAfiliacion;
	}

	public AfiliadoDto toDto(){
		AfiliadoDto dto = new AfiliadoDto();
		dto.setId(this.id);
		dto.setTipoIdentificacion(this.tipoIdentificacion.toDto());
		dto.setNumeroIdentificacion(this.numeroIdentificacion);
		dto.setPrimerNombre(this.primerNombre);
		dto.setSegundoNombre(this.segundoNombre);
		dto.setPrimerApellido(this.primerApellido);
		dto.setSegundoApellido(this.segundoApellido);
		dto.setFechaNacimiento(this.fechaNacimiento);
		dto.setGenero(this.genero.toDto());
		dto.setEstadoCivil(this.estadoCivil.toDto());
		dto.setDireccionDeResidencia(this.direccionDeResidencia);
		dto.setTelefonoResidencial(this.telefonoResidencial);
		dto.setTelefonoCelular(this.telefonoCelular);
		dto.setMunicipioResidencia(this.municipioResidencia.toDto());
		dto.setEmailPersonal(this.emailPersonal);
		dto.setSedeIpsAfiliacion(this.sedeIpsAfiliacion != null ? this.sedeIpsAfiliacion.toDto() : null);
		dto.setFechaAfiliacionIps(this.fechaAfiliacionIps);
		dto.setFechaAfiliacionSGSS(this.fechaAfiliacionSGSS);
		dto.setEstadoAfiliacion(this.estadoAfiliacion.toDto());
		dto.setDiasContinuos(this.diasContinuos);
		dto.setSemanasCotizadas(this.semanasCotizadas);
		dto.setFechaFinUrgencias(this.fechaFinUrgencias);
		dto.setEps(this.eps.toDto());
		dto.setNivelIbc(this.nivelIbc != null ? this.nivelIbc.toDto() : null);
		dto.setNivelSisBen(this.nivelSisBen != null ? this.nivelSisBen.toDto() : null);
		dto.setRegimenAfiliacion(this.getRegimenAfiliacion().getId());
		dto.setRazonEstadoAfiliacion(this.razonEstadoAfiliacion);
		dto.setTutela(this.tutela);
		dto.setTipoAfiliado(this.tipoAfiliadoId);
		return dto;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Departamento getDepartamentoSeccional() {
		return departamentoSeccional;
	}

	public void setDepartamentoSeccional(Departamento departamentoSeccional) {
		this.departamentoSeccional = departamentoSeccional;
	}

	public String getRazonEstadoAfiliacion() {
		return razonEstadoAfiliacion;
	}

	public void setRazonEstadoAfiliacion(String razonEstadoAfiliacion) {
		this.razonEstadoAfiliacion = razonEstadoAfiliacion;
	}

	/**
	 * Obtiene el tipo de afiliado.
	 * 
	 * @return Tipo de afiliado.
	 */
	public TipoAfiliado getTipoAfiliado() {
		
		return (TipoAfiliado.fromId(this.tipoAfiliadoId));
	}

	/**
	 * Asigna el tipo de afiliado.
	 * 
	 * @param tipoAfiliado Tipo de afiliado.
	 */
	public void setTipoAfiliado(final TipoAfiliado tipoAfiliado) {
		
		if (tipoAfiliadoId != null) {
			this.tipoAfiliadoId = tipoAfiliado.getId();
		} else {
			this.tipoAfiliadoId = null;
		}
	}

	public Integer getSemEps() {
		return semEps;
	}

	public void setSemEps(Integer semEps) {
		this.semEps = semEps;
	}

	public Integer getSemSGSSS() {
		return semSGSSS;
	}

	public void setSemSGSSS(Integer semSGSSS) {
		this.semSGSSS = semSGSSS;
	}


	public Integer getSemCotizadas() {
		return semCotizadas;
	}

	public void setSemCotizadas(Integer semCotizadas) {
		this.semCotizadas = semCotizadas;
	}

	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public Short getTutela() {
		return tutela;
	}

	public void setTutela(Short tutela) {
		this.tutela = tutela;
	}

	public Set<AfiliadoPrograma> getProgramas() {
		return programas;
	}

	public void setProgramas(Set<AfiliadoPrograma> programas) {
		this.programas = programas;
	}
	@JsonIgnore
	public String getNombreCompleto() {
		return this.getPrimerNombre() + " " + this.getSegundoNombre() + " " + this.getPrimerApellido() + " " + this.getSegundoApellido();
	}

	public GrupoPoblacional getGrupoPoblacional() {
		return grupoPoblacional;
	}

	public void setGrupoPoblacional(GrupoPoblacional grupoPoblacional) {
		this.grupoPoblacional = grupoPoblacional;
	}

	public Long getTipoAfiliadoId() {
		return tipoAfiliadoId;
	}

	public void setTipoAfiliadoId(Long tipoAfiliadoId) {
		this.tipoAfiliadoId = tipoAfiliadoId;
	}

	public Long getRegimenAfiliacionId() {
		return regimenAfiliacionId;
	}

	public void setRegimenAfiliacionId(Long regimenAfiliacionId) {
		this.regimenAfiliacionId = regimenAfiliacionId;
	}

	public AfiliadoCotizante getAfiliadoCotizante() {
		return afiliadoCotizante;
	}

	public void setAfiliadoCotizante(AfiliadoCotizante afiliadoCotizante) {
		this.afiliadoCotizante = afiliadoCotizante;
	}




}
