package com.conexia.saludcoop.common.entity.maestro;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.SedeIpsDto;

/**
 * @author fgonzalez
 *
 * representa una sucursal de una ips
 *
 */
@Entity
@Table(name = "sede_ips", schema="maestros")
@Mappeable(mappedTo=SedeIpsDto.class)
public class  SedeIps extends BaseMaestro implements Serializable {


	private static final long serialVersionUID = 288666346984611874L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;


	@ManyToOne
	@JoinColumn(name = "localidad_id", nullable = true)
	private Localidad localidad;

	@ManyToOne
	@JoinColumn(name = "municipio_id", nullable = false)
	private Municipio municipio;

	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name = "sede_ips_division_seccional", schema = "maestros", 
			joinColumns = { @JoinColumn(name = "sede_ips_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "division_seccional_id") })
	private Set<DivisionSeccional> divisionesSeccionales;
	
	@ManyToOne
	@JoinColumn(name = "regional_id", nullable = false)
	private Regional regional;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="sedeIps")
	private Set<ProfesionalEspecialidadSedeIps> profesionalEspecialidades = new HashSet<>();

	@ManyToOne
	private Ips ips;
	
	/**
	 * Define el Codigo de ministerio de Salud de la Ips	
	 */

	@Column(name="codigo_min_salud", nullable = false, length=20)
	private String codigoMinisterioSalud;

	//	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	//	private Contrato contrato;

	@ManyToOne
	@JoinColumn(name="tipo_ips_id", nullable = false)
	private TipoIps tipoIps;

	@ManyToOne
	@JoinColumn(name="estado_ips_id", nullable = false)
	private EstadoIps estadoIps;


	@Column(name = "direccion", nullable = false, length=100)
	private String direccion;

	@Column(name = "telefono1", nullable = false, length=50)
	private String telefono1;

	@Column(name = "telefono2", nullable = true, length=50)
	private String telefono2;

	@Column(name = "email", nullable = true, length=50)
	private String email;

	@Column(name = "pag_web", nullable = true, length=50)
	private String pagWeb;

	@Column(name = "fecha_habilitacion", nullable = true)
	private Date fechaHabilitacion;


	@Column(name = "es_especialista", nullable = true)
	private Short esEspecialista;


	@Column(name = "fecha_inactivacion", nullable = true)
	private Date fechaInactivacion;


	@Column(name = "nivel_de_atencion", nullable = false)
	private Integer nivelDeAtencion;

	@Column(name = "nombre", nullable = true, length=100)
	private String nombre;

	@ManyToOne
	@JoinColumn(name="ubicacion_id", nullable = true)
	private Ubicacion ubicacion;

	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;

	@ManyToOne
	@JoinColumn(name="tipo_servicio_id", nullable = false)
	private TipoServicio tipoServicio;

	@ManyToOne
	@JoinColumn(name="regimen_juridico_id", nullable = false)
	private RegimenJuridico regimenJuridico;

	@ManyToOne
	@JoinColumn(name="regimen_tributario_id", nullable = false)
	private RegimenTributario regimenTributario;
	
	@ManyToOne
	@JoinColumn(name="eps_id", nullable = false)
	private Eps eps;

	@OneToMany(mappedBy="sedeIps", fetch=FetchType.LAZY)
	private Set<Contrato> contratos = new HashSet<>();
	
	@Column(name = "es_epsifarma", nullable = false)
	private Short esEpsifarma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public Date getFechaHabilitacion() {
		return fechaHabilitacion;
	}

	public void setFechaHabilitacion(Date fechaHabilitacion) {
		this.fechaHabilitacion = fechaHabilitacion;
	}

	public Ips getIps() {
		return ips;
	}

	public void setIps(Ips ips) {
		this.ips = ips;
	}


	public Set<ProfesionalEspecialidadSedeIps> getProfesionalEspecialidades() {
		return profesionalEspecialidades;
	}

	public void setProfesionalEspecialidades(Set<ProfesionalEspecialidadSedeIps> profesionalEspecialidades) {
		this.profesionalEspecialidades = profesionalEspecialidades;
	}

	public Integer getNivelDeAtencion() {
		return nivelDeAtencion;
	}

	public void setNivelDeAtencion(Integer nivelDeAtencion) {
		this.nivelDeAtencion = nivelDeAtencion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public SedeIpsDto toDto(){
		SedeIpsDto dto = new SedeIpsDto();
		dto.setId(this.id);
		dto.setCodigoMinisterioSalud(this.codigoMinisterioSalud);
		dto.setNombre(this.nombre);
		dto.setMunicipio(this.municipio.toDto());
		dto.setDireccion(this.direccion);
		dto.setTelefono1(this.telefono1);
		dto.setTelefono2(this.telefono2);
		dto.setFechaHabilitacion(this.fechaHabilitacion);
		dto.setNivelDeAtencion(this.nivelDeAtencion);
		dto.setIps(this.ips.toDto());
		dto.setTipoServicio(this.tipoServicio.getId());
		return dto;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public String getCodigoMinisterioSalud() {
		return codigoMinisterioSalud;
	}

	public void setCodigoMinisterioSalud(String codigoMinisterioSalud) {
		this.codigoMinisterioSalud = codigoMinisterioSalud;
	}

	public TipoIps getTipoIps() {
		return tipoIps;
	}

	public void setTipoIps(TipoIps tipoIps) {
		this.tipoIps = tipoIps;
	}

	public EstadoIps getEstadoIps() {
		return estadoIps;
	}

	public void setEstadoIps(EstadoIps estadoIps) {
		this.estadoIps = estadoIps;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPagWeb() {
		return pagWeb;
	}

	public void setPagWeb(String pagWeb) {
		this.pagWeb = pagWeb;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public RegimenJuridico getRegimenJuridico() {
		return regimenJuridico;
	}

	public void setRegimenJuridico(RegimenJuridico regimenJuridico) {
		this.regimenJuridico = regimenJuridico;
	}

	public RegimenTributario getRegimenTributario() {
		return regimenTributario;
	}

	public void setRegimenTributario(RegimenTributario regimenTributario) {
		this.regimenTributario = regimenTributario;
	}

	public Date getFechaInactivacion() {
		return fechaInactivacion;
	}

	public void setFechaInactivacion(Date fechaInactivacion) {
		this.fechaInactivacion = fechaInactivacion;
	}

	public Short getEsEspecialista() {
		return esEspecialista;
	}

	public void setEsEspecialista(Short esEspecialista) {
		this.esEspecialista = esEspecialista;
	}


	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof SedeIps))
			return false;
		
		SedeIps otherSedeIps = (SedeIps) obj;
		
		return otherSedeIps.getId().equals(this.id);
	}
	
	public int hashCode() {
		return (int) (41 * (41 + this.id) + this.id);
    }

	public Set<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(Set<Contrato> contratos) {
		this.contratos = contratos;
	}

	public Short getEsEpsifarma() {
		return esEpsifarma;
	}

	public void setEsEpsifarma(Short esEpsifarma) {
		this.esEpsifarma = esEpsifarma;
	}

	public Set<DivisionSeccional> getDivisionesSeccionales() {
		return divisionesSeccionales;
	}

	public void setDivisionesSeccionales(Set<DivisionSeccional> divisionesSeccionales) {
		this.divisionesSeccionales = divisionesSeccionales;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}


	
}
