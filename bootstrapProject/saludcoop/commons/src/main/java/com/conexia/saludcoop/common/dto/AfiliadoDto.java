package com.conexia.saludcoop.common.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.entity.maestro.Afiliado;
@Mappeable(mappedTo=Afiliado.class)
public class AfiliadoDto {
	
	private Long id;
	private TipoIdentificacionDto tipoIdentificacion;
	private String numeroIdentificacion;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Date fechaNacimiento;
	private DescriptivoDto programa;
	private DescriptivoDto genero;
	private DescriptivoDto estadoCivil;
	private String direccionDeResidencia;
	private String telefonoResidencial;
	private String telefonoCelular;
	private DescriptivoDto zonaDeResidencia;
	private MunicipioDto municipioResidencia;
	private String emailPersonal;
	private SedeIpsDto sedeIpsAfiliacion;
	private Date fechaAfiliacionIps;
	private Date fechaAfiliacionSGSS;
	private DescriptivoDto estadoAfiliacion;
	private Integer diasContinuos;
	private Integer semanasCotizadas;
	private Date fechaFinUrgencias;
	private EpsDto eps;
	private DescriptivoDto nivelIbc;
	private DescriptivoDto nivelSisBen;
	private Long regimenAfiliacion;
	private LocalidadDto localidad;
	private DivisionSeccionalDto divisionSeccional;
	private String razonEstadoAfiliacion;
	private Short tutela;
	private Long tipoAfiliado;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setTipoIdentificacion(TipoIdentificacionDto tipoIdentificacion){
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public TipoIdentificacionDto getTipoIdentificacion(){
		return this.tipoIdentificacion;
	}
	public void setNumeroIdentificacion(String numeroIdentificacion){
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNumeroIdentificacion(){
		return this.numeroIdentificacion;
	}
	public void setPrimerNombre(String primerNombre){
		this.primerNombre = primerNombre;
	}

	public String getPrimerNombre(){
		return this.primerNombre;
	}
	public void setSegundoNombre(String segundoNombre){
		this.segundoNombre = segundoNombre;
	}

	public String getSegundoNombre(){
		return this.segundoNombre;
	}
	public void setPrimerApellido(String primerApellido){
		this.primerApellido = primerApellido;
	}

	public String getPrimerApellido(){
		return this.primerApellido;
	}
	public void setSegundoApellido(String segundoApellido){
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoApellido(){
		return this.segundoApellido;
	}
	public void setFechaNacimiento(Date fechaNacimiento){
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaNacimiento(){
		return this.fechaNacimiento;
	}
	public void setPrograma(DescriptivoDto programa){
		this.programa = programa;
	}

	public DescriptivoDto getPrograma(){
		return this.programa;
	}
	public void setGenero(DescriptivoDto genero){
		this.genero = genero;
	}

	public DescriptivoDto getGenero(){
		return this.genero;
	}
	public void setEstadoCivil(DescriptivoDto estadoCivil){
		this.estadoCivil = estadoCivil;
	}

	public DescriptivoDto getEstadoCivil(){
		return this.estadoCivil;
	}
	public void setDireccionDeResidencia(String direccionDeResidencia){
		this.direccionDeResidencia = direccionDeResidencia;
	}

	public String getDireccionDeResidencia(){
		return this.direccionDeResidencia;
	}
	public void setTelefonoResidencial(String telefonoResidencial){
		this.telefonoResidencial = telefonoResidencial;
	}

	public String getTelefonoResidencial(){
		return this.telefonoResidencial;
	}
	public void setTelefonoCelular(String telefonoCelular){
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoCelular(){
		return this.telefonoCelular;
	}
	public void setZonaDeResidencia(DescriptivoDto zonaDeResidencia){
		this.zonaDeResidencia = zonaDeResidencia;
	}

	public DescriptivoDto getZonaDeResidencia(){
		return this.zonaDeResidencia;
	}
	public void setMunicipioResidencia(MunicipioDto municipioResidencia){
		this.municipioResidencia = municipioResidencia;
	}

	public MunicipioDto getMunicipioResidencia(){
		return this.municipioResidencia;
	}
	public void setEmailPersonal(String emailPersonal){
		this.emailPersonal = emailPersonal;
	}

	public String getEmailPersonal(){
		return this.emailPersonal;
	}
	public void setSedeIpsAfiliacion(SedeIpsDto sedeIpsAfiliacion){
		this.sedeIpsAfiliacion = sedeIpsAfiliacion;
	}

	public SedeIpsDto getSedeIpsAfiliacion(){
		return this.sedeIpsAfiliacion;
	}
	public void setFechaAfiliacionIps(Date fechaAfiliacionIps){
		this.fechaAfiliacionIps = fechaAfiliacionIps;
	}

	public Date getFechaAfiliacionIps(){
		return this.fechaAfiliacionIps;
	}
	public void setFechaAfiliacionSGSS(Date fechaAfiliacionSGSS){
		this.fechaAfiliacionSGSS = fechaAfiliacionSGSS;
	}

	public Date getFechaAfiliacionSGSS(){
		return this.fechaAfiliacionSGSS;
	}
	public void setEstadoAfiliacion(DescriptivoDto estadoAfiliacion){
		this.estadoAfiliacion = estadoAfiliacion;
	}

	public DescriptivoDto getEstadoAfiliacion(){
		return this.estadoAfiliacion;
	}
	public void setDiasContinuos(Integer diasContinuos){
		this.diasContinuos = diasContinuos;
	}

	public Integer getDiasContinuos(){
		return this.diasContinuos;
	}
	public void setSemanasCotizadas(Integer semanasCotizadas){
		this.semanasCotizadas = semanasCotizadas;
	}

	public Integer getSemanasCotizadas(){
		return this.semanasCotizadas;
	}
	public void setFechaFinUrgencias(Date fechaFinUrgencias){
		this.fechaFinUrgencias = fechaFinUrgencias;
	}

	public Date getFechaFinUrgencias(){
		return this.fechaFinUrgencias;
	}
	public void setEps(EpsDto eps){
		this.eps = eps;
	}

	public EpsDto getEps(){
		return this.eps;
	}
	public void setNivelIbc(DescriptivoDto nivelIbc){
		this.nivelIbc = nivelIbc;
	}

	public DescriptivoDto getNivelIbc(){
		return this.nivelIbc;
	}
	public void setNivelSisBen(DescriptivoDto nivelSisBen){
		this.nivelSisBen = nivelSisBen;
	}

	public DescriptivoDto getNivelSisBen(){
		return this.nivelSisBen;
	}
	
	/**
	 * Asigna el régimen de afiliación.
	 * 
	 * @param regimenAfiliacion Régimen de afiliación.
	 */
	public void setRegimenAfiliacion(Long regimenAfiliacion) {
		
		this.regimenAfiliacion = regimenAfiliacion;
	}

	/**
	 * Obtiene el régimen de afiliación.
	 * 
	 * @return Régimen de afiliación.
	 */
	public Long getRegimenAfiliacion() {
		
		return this.regimenAfiliacion;
	}

	public LocalidadDto getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDto localidad) {
		this.localidad = localidad;
	}

	public DivisionSeccionalDto getDivisionSeccional() {
		return divisionSeccional;
	}

	public void setDivisionSeccional(DivisionSeccionalDto divisionSeccional) {
		this.divisionSeccional = divisionSeccional;
	}

	public String getRazonEstadoAfiliacion() {
		return razonEstadoAfiliacion;
	}

	public void setRazonEstadoAfiliacion(String razonEstadoAfiliacion) {
		this.razonEstadoAfiliacion = razonEstadoAfiliacion;
	}

	@JsonIgnore
	public String getNombreCompleto() {
		return this.primerNombre + " " + this.segundoNombre + " " + this.primerApellido + " " + this.segundoApellido; 
	}

    public Short getTutela() {
        return tutela;
    }

    public void setTutela(Short tutela) {
        this.tutela = tutela;
    }

    public Long getTipoAfiliado() {
        return tipoAfiliado;
    }

    public void setTipoAfiliado(Long tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
    }

}
