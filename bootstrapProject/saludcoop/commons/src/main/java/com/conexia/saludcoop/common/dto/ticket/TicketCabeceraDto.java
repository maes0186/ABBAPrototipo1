package com.conexia.saludcoop.common.dto.ticket;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;

import com.conexia.saludcoop.common.util.SystemConstants;

public class TicketCabeceraDto { 
	private Integer id;
	private Long autorizacionId;
	private String numeroAutorizacion;
	private Integer cantidadDeEntregas;
	private Integer numeroDeEntrega;
	private String eps;
	private String nombresDelPaciente;
	private String tipoAfiliado;
	private String tipoDeIdentificacion;
	private String numeroIdentificacion;
	private String edad;
	private String nivel;
	private String planAfiliado;
	private String ipsPrimaria;
	private String entidadSolicitante;
	private Date fecha;
	private String usuarioTranscriptor;
	private String causaExterna;
	private String entidadRecobro;
	private String origen;
	private String diagnosticoPrincipal;
	private String diagnosticosSecundarios;
	private Set<TicketItemDto> items;
	private String pagoCompartidoEps;
	private String pagoCompartidoUsuario;
	private String copagoPorcentaje;
	private String copagoValor;
	private String cuotaModeradora;
	private String descuentoCapitacionIps;
	private String institucionRemitidaNombre;
	private String institucionRemitidaDireccion;
	private String institucionRemitidaTelefono;
	private String firmaMedico;
	private String nombreCompletoMedico;
	private String registroMedico;
	private String numeroSolicitud;
	private int tipoServicio;
	private String tipoPpm;
	private String vigencia;
	private Date fechaImpresion;
	private Integer cantidadCopias;
	

	public Date getFechaImpresion() {
		return fechaImpresion;
	}

	public void setFechaImpresion(Date fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	public Integer getCantidadCopias() {
		return cantidadCopias;
	}

	public void setCantidadCopias(Integer cantidadCopias) {
		this.cantidadCopias = cantidadCopias;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}
	
	public Long getAutorizacionId() {
		return autorizacionId;
	}

	public void setAutorizacionId(Long autorizacionId) {
		this.autorizacionId = autorizacionId;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public void setNumeroAutorizacion(String numeroAutorizacion){
		this.numeroAutorizacion = numeroAutorizacion;
	}

	public String getNumeroAutorizacion(){
		return this.numeroAutorizacion;
	}
	public void setCantidadDeEntregas(Integer cantidadDeEntregas){
		this.cantidadDeEntregas = cantidadDeEntregas;
	}

	public Integer getCantidadDeEntregas(){
		return this.cantidadDeEntregas;
	}
	public void setNumeroDeEntrega(Integer numeroDeEntrega){
		this.numeroDeEntrega = numeroDeEntrega;
	}

	public Integer getNumeroDeEntrega(){
		return this.numeroDeEntrega;
	}
	public void setEps(String eps){
		this.eps = eps;
	}

	public String getEps(){
		return this.eps;
	}
	public void setNombresDelPaciente(String nombresDelPaciente){
		this.nombresDelPaciente = nombresDelPaciente;
	}

	public String getNombresDelPaciente(){
		return this.nombresDelPaciente;
	}
	public void setTipoAfiliado(String tipoAfiliado){
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getTipoAfiliado(){
		return this.tipoAfiliado;
	}
	public void setTipoDeIdentificacion(String tipoDeIdentificacion){
		this.tipoDeIdentificacion = tipoDeIdentificacion;
	}

	public String getTipoDeIdentificacion(){
		return this.tipoDeIdentificacion;
	}

	public void setEdad(String edad){
		this.edad = edad;
	}

	public String getEdad(){
		return this.edad;
	}
	public void setNivel(String nivel){
		this.nivel = nivel;
	}

	public String getNivel(){
		return this.nivel;
	}
	
	public String getPlanAfiliado() {
		return planAfiliado;
	}

	public void setPlanAfiliado(String planAfiliado) {
		this.planAfiliado = planAfiliado;
	}

	public void setIpsPrimaria(String ipsPrimaria){
		this.ipsPrimaria = ipsPrimaria;
	}

	public String getIpsPrimaria(){
		return this.ipsPrimaria;
	}
	public void setEntidadSolicitante(String entidadSolicitante){
		this.entidadSolicitante = entidadSolicitante;
	}

	public String getEntidadSolicitante(){
		return this.entidadSolicitante;
	}
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}

	public Date getFecha(){
		return this.fecha;
	}
	public void setUsuarioTranscriptor(String usuarioTranscriptor){
		this.usuarioTranscriptor = usuarioTranscriptor;
	}

	public String getUsuarioTranscriptor(){
		return this.usuarioTranscriptor;
	}
	public void setCausaExterna(String causaExterna){
		this.causaExterna = causaExterna;
	}

	public String getCausaExterna(){
		return this.causaExterna;
	}
	public void setEntidadRecobro(String entidadRecobro){
		this.entidadRecobro = entidadRecobro;
	}

	public String getEntidadRecobro(){
		return this.entidadRecobro;
	}
	public void setOrigen(String origen){
		this.origen = origen;
	}

	public String getOrigen(){
		return this.origen;
	}
	public void setDiagnosticoPrincipal(String diagnosticoPrincipal){
		this.diagnosticoPrincipal = diagnosticoPrincipal;
	}

	public String getDiagnosticoPrincipal(){
		return this.diagnosticoPrincipal;
	}
	public void setDiagnosticosSecundarios(String diagnosticosSecundarios){
		this.diagnosticosSecundarios = diagnosticosSecundarios;
	}

	public String getDiagnosticosSecundarios(){
		return this.diagnosticosSecundarios;
	}
	public void setItems(Set<TicketItemDto> items){
		this.items = items;
	}

	public Set<TicketItemDto> getItems(){
		return this.items;
	}
	public void setPagoCompartidoEps(String pagoCompartidoEps){
		this.pagoCompartidoEps = pagoCompartidoEps;
	}

	public String getPagoCompartidoEps(){
		return this.pagoCompartidoEps;
	}
	public void setPagoCompartidoUsuario(String pagoCompartidoUsuario){
		this.pagoCompartidoUsuario = pagoCompartidoUsuario;
	}

	public String getPagoCompartidoUsuario(){
		return this.pagoCompartidoUsuario;
	}
	public void setCopagoPorcentaje(String copagoPorcentaje){
		this.copagoPorcentaje = copagoPorcentaje;
	}

	public String getCopagoPorcentaje(){
		return this.copagoPorcentaje;
	}
	public void setCopagoValor(String copagoValor){
		this.copagoValor = copagoValor;
	}

	public String getCopagoValor(){
		return this.copagoValor;
	}
	public void setCuotaModeradora(String cuotaModeradora){
		this.cuotaModeradora = cuotaModeradora;
	}

	public String getCuotaModeradora(){
		return this.cuotaModeradora;
	}
	public void setDescuentoCapitacionIps(String descuentoCapitacionIps){
		this.descuentoCapitacionIps = descuentoCapitacionIps;
	}

	public String getDescuentoCapitacionIps(){
		return this.descuentoCapitacionIps;
	}
	public void setInstitucionRemitidaNombre(String institucionRemitidaNombre){
		this.institucionRemitidaNombre = institucionRemitidaNombre;
	}

	public String getInstitucionRemitidaNombre(){
		return this.institucionRemitidaNombre;
	}
	public void setInstitucionRemitidaDireccion(String institucionRemitidaDireccion){
		this.institucionRemitidaDireccion = institucionRemitidaDireccion;
	}

	public String getInstitucionRemitidaDireccion(){
		return this.institucionRemitidaDireccion;
	}
	public void setInstitucionRemitidaTelefono(String institucionRemitidaTelefono){
		this.institucionRemitidaTelefono = institucionRemitidaTelefono;
	}

	public String getInstitucionRemitidaTelefono(){
		return this.institucionRemitidaTelefono;
	}
	public void setFirmaMedico(String firmaMedico){
		this.firmaMedico = firmaMedico;
	}

	public String getFirmaMedico(){
		return this.firmaMedico;
	}
	public void setRegistroMedico(String registroMedico){
		this.registroMedico = registroMedico;
	}

	public String getRegistroMedico(){
		return this.registroMedico;
	}
	public void setNumeroSolicitud(String numeroSolicitud){
		this.numeroSolicitud = numeroSolicitud;
	}

	public String getNumeroSolicitud(){
		return this.numeroSolicitud;
	}

	public String getTipoPpm() {
		return tipoPpm;
	}

	public void setTipoPpm(String tipoPpm) {
		this.tipoPpm = tipoPpm;
	}

	public String getNombreCompletoMedico() {
		return nombreCompletoMedico;
	}

	public void setNombreCompletoMedico(String nombreCompletoMedico) {
		this.nombreCompletoMedico = nombreCompletoMedico;
	}

	public boolean getEsMedicamento() {
		
		return this.tipoServicio == SystemConstants.ITEM_MEDICAMENTO;
	}
	public boolean getEsInsumo() {
		
		return this.tipoServicio == SystemConstants.ITEM_INSUMO;
	}
	
	public boolean getEsProcedimiento() {
		
		return this.tipoServicio == SystemConstants.ITEM_PROCEDIMIENTO;
	}

	public int getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(int tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	

}
