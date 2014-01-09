package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dozer.DozerBeanMapper;
import org.hibernate.annotations.Index;

import com.conexia.saludcoop.common.dto.ProfesionalDto;

@Entity
@Table(name = "profesional", schema="maestros")
public class Profesional extends BaseMaestro{
	
	/**
	 * Identificador del profesion
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	/**
	 * Define el primer nombre del profesional
	 */
	@Column(name = "primer_nombre", nullable = false, length=100)
	private String primerNombre;
	
	/**
	 * Define el segundo nombre del profesional
	 */
	@Column(name = "segundo_nombre", nullable = true, length=100)
	private String segundoNombre;

	/**
	 * Define el primer apellido del profesional
	 */
	@Column(name = "primer_apellido", nullable = false, length=100)
	private String primerApellido;

	/**
	 * Define el primer apellido del profesional
	 */
	@Column(name = "segundo_apellido", nullable = true, length=100)
	private String segundoApellido;

	/**
	 * Define el registro medico del profesional
	 */
	@Column(name = "registro_medico", nullable = true, length=20)
	private String registroMedico;
	
	/**
	 * Define la direccion del profesional
	 */
	@Column(name = "direccion", nullable = true, length=100)
	private String direccion;
	
	/**
	 * Define el numero telefonico del profesional
	 */
	@Column(name = "telefono1", nullable = true, length=50)
	private String telefono1;
	/**
	 * Define un segundo numero telefonico del profesional
	 */
	@Column(name = "telefono2", nullable = true, length=50)
	private String telefono2;
	
	@ManyToOne
	@JoinColumn(name="tipo_profesional_id")
	private TipoProfesional tipoProfesional;
	/**
	 * Define el numero telefonico de atencion del profesional como por ejemplo el call center
	 */
	@Column(name = "telefono_atencion", nullable = true, length=50)
	private String telefonoAtencion;
	
	/**
	 * Define el numero del celular del profesional
	 */
	@Column(name = "celular", nullable = true, length=50)	
	private String celular;
	
	/**
	 * Define el fax del profesional
	 */
	@Column(name = "fax", nullable = true, length=20)	
	private String fax;
	
	/**
	 * Define el fax del profesional
	 */
	@Column(name = "beeper", nullable = true, length=50)		
	private String beeper;
	
	/**
	 * Define el fax del profesional
	 */
	@Column(name = "email", nullable = true, length=50)			
	private String email;
	
	/**
	 * Define firma impresa
	 */
	@Column(name = "firma_impresa", nullable = true, length=70)			
	private String firmaImpresa;
	
	
	@ManyToOne
	@JoinColumn(name="division_seccional_id")
	private DivisionSeccional divisionSeccional;
	
	@ManyToOne
	private Municipio municipio;
	
	@ManyToOne
	private Eps eps;
	
	@ManyToOne
	@JoinColumn(name="identificacion_profesional_id")
	private TipoIdentificacion identificacionProfesional;
	
	@Index(name="ix_profecional_numero_identificacion")
	@Column(name = "numero_identificacion", nullable = false, length=20)
	private String numeroIdentificacion;

	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;
	
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
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getRegistroMedico() {
		return registroMedico;
	}
	public void setRegistroMedico(String registroMedico) {
		this.registroMedico = registroMedico;
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
	public String getTelefonoAtencion() {
		return telefonoAtencion;
	}
	public void setTelefonoAtencion(String telefonoAtencion) {
		this.telefonoAtencion = telefonoAtencion;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getBeeper() {
		return beeper;
	}
	public void setBeeper(String beeper) {
		this.beeper = beeper;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public DivisionSeccional getDivisionSeccional() {
		return divisionSeccional;
	}
	public void setDivisionSeccional(DivisionSeccional divisionSeccional) {
		this.divisionSeccional = divisionSeccional;
	}


	public TipoIdentificacion getIdentificacionProfesional() {
		return identificacionProfesional;
	}
	public void setIdentificacionProfesional(TipoIdentificacion identificacionProfesional) {
		this.identificacionProfesional = identificacionProfesional;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getFirmaImpresa() {
		return firmaImpresa;
	}
	public void setFirmaImpresa(String firmaImpresa) {
		this.firmaImpresa = firmaImpresa;
	}

	public ProfesionalDto toDto() {
		return new DozerBeanMapper().map(this, ProfesionalDto.class);
	}
	public Integer getClientePk() {
		return clientePk;
	}
	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}
	public TipoProfesional getTipoProfesional() {
		return tipoProfesional;
	}
	public void setTipoProfesional(TipoProfesional tipoProfesional) {
		this.tipoProfesional = tipoProfesional;
	}
	public Eps getEps() {
		return eps;
	}
	public void setEps(Eps eps) {
		this.eps = eps;
	}
	


	
}
