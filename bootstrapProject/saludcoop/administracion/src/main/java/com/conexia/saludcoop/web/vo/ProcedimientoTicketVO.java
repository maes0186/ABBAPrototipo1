package com.conexia.saludcoop.web.vo;

import java.io.File;
import java.util.List;

public class ProcedimientoTicketVO {

	private String numeroAutorizacion;
	private String pathLogoEPS;
	private String nombreEPS;

	private String nombreCompletoAfiliado;
	private String tipoAfiliado;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String edad;

	private String nivel;
	private String plan;
	private String ipsPrimaria;
	private String entidadRecobro;
	private String ipsSolicitante;
	private String usuarioTranscribe;

	private List<PPMTicketVO> parcticas;

	private String pagoCompartidoEps;
	private String pagoCompartidoUsuario;
	private String copagoPorcentaje;
	private String cuotaModeradora;
	private String descuentoCapitacionIps;

	private String profesionalNombreCompleto;
	private String profesionalRegistro;
	private File profesionalFirma;
	private String pathFirmaProfesional;

	private String institucionRemitida;
	private String direccion;
	private String telefono;

	public String getNumeroAutorizacion() {
		return numeroAutorizacion;
	}

	public void setNumeroAutorizacion(String numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}

	public String getPathLogoEPS() {
		return pathLogoEPS;
	}

	public void setPathLogoEPS(String pathLogoEPS) {
		this.pathLogoEPS = pathLogoEPS;
	}

	public String getNombreEPS() {
		return nombreEPS;
	}

	public void setNombreEPS(String nombreEPS) {
		this.nombreEPS = nombreEPS;
	}

	public String getNombreCompletoAfiliado() {
		return nombreCompletoAfiliado;
	}

	public void setNombreCompletoAfiliado(String nombreCompletoAfiliado) {
		this.nombreCompletoAfiliado = nombreCompletoAfiliado;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getIpsPrimaria() {
		return ipsPrimaria;
	}

	public void setIpsPrimaria(String ipsPrimaria) {
		this.ipsPrimaria = ipsPrimaria;
	}

	public String getEntidadRecobro() {
		return entidadRecobro;
	}

	public void setEntidadRecobro(String entidadRecobro) {
		this.entidadRecobro = entidadRecobro;
	}

	public String getIpsSolicitante() {
		return ipsSolicitante;
	}

	public void setIpsSolicitante(String ipsSolicitante) {
		this.ipsSolicitante = ipsSolicitante;
	}

	public String getUsuarioTranscribe() {
		return usuarioTranscribe;
	}

	public void setUsuarioTranscribe(String usuarioTranscribe) {
		this.usuarioTranscribe = usuarioTranscribe;
	}

	public List<PPMTicketVO> getParcticas() {
		return parcticas;
	}

	public void setParcticas(List<PPMTicketVO> parcticas) {
		this.parcticas = parcticas;
	}

	public String getPagoCompartidoEps() {
		return pagoCompartidoEps;
	}

	public void setPagoCompartidoEps(String pagoCompartidoEps) {
		this.pagoCompartidoEps = pagoCompartidoEps;
	}

	public String getPagoCompartidoUsuario() {
		return pagoCompartidoUsuario;
	}

	public void setPagoCompartidoUsuario(String pagoCompartidoUsuario) {
		this.pagoCompartidoUsuario = pagoCompartidoUsuario;
	}

	public String getCopagoPorcentaje() {
		return copagoPorcentaje;
	}

	public void setCopagoPorcentaje(String copagoPorcentaje) {
		this.copagoPorcentaje = copagoPorcentaje;
	}

	public String getCuotaModeradora() {
		return cuotaModeradora;
	}

	public void setCuotaModeradora(String cuotaModeradora) {
		this.cuotaModeradora = cuotaModeradora;
	}

	public String getDescuentoCapitacionIps() {
		return descuentoCapitacionIps;
	}

	public void setDescuentoCapitacionIps(String descuentoCapitacionIps) {
		this.descuentoCapitacionIps = descuentoCapitacionIps;
	}

	public String getProfesionalNombreCompleto() {
		return profesionalNombreCompleto;
	}

	public void setProfesionalNombreCompleto(String profesionalNombreCompleto) {
		this.profesionalNombreCompleto = profesionalNombreCompleto;
	}

	public String getProfesionalRegistro() {
		return profesionalRegistro;
	}

	public void setProfesionalRegistro(String profesionalRegistro) {
		this.profesionalRegistro = profesionalRegistro;
	}

	public File getProfesionalFirma() {
		return profesionalFirma;
	}

	public void setProfesionalFirma(File profesionalFirma) {
		this.profesionalFirma = profesionalFirma;
	}

	public String getPathFirmaProfesional() {
		return pathFirmaProfesional;
	}

	public void setPathFirmaProfesional(String pathFirmaProfesional) {
		this.pathFirmaProfesional = pathFirmaProfesional;
	}

	public String getInstitucionRemitida() {
		return institucionRemitida;
	}

	public void setInstitucionRemitida(String institucionRemitida) {
		this.institucionRemitida = institucionRemitida;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
