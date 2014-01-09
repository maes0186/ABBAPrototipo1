package com.conexia.saludcoop.web.xls;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;

public class BandejaAcXls {
	private Long solId;
	private Long solItemId;
	private String regional;
	private String fecha;
	private String tipoDocumento;
	private String documento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String tecnologia;
	private String diagnostico;
	private String estado;
	private String ambito;
	private String origen;
	private String entidad;
	private String eps;
	private String origino;
	private String tipoCTC; 
	private String prioridad;
	private String tipoPrioridad;
	
	
	
	public BandejaAcXls(Long solId, Long solItemId, String regional,
			String fecha, String tipoDocumento, String documento,
			String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String tecnologia, String diagnostico,
			String estado, String ambito, String origen, String entidad,
			String eps, String origino, String tipoCTC, String prioridad,
			String tipoPrioridad) {
		this.solId = solId;
		this.solItemId = solItemId;
		this.regional = regional;
		this.fecha = fecha;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.tecnologia = tecnologia;
		this.diagnostico = diagnostico;
		this.estado = estado;
		this.ambito = ambito;
		this.origen = origen;
		this.entidad = entidad;
		this.eps = eps;
		this.origino = origino;
		this.tipoCTC = tipoCTC;
		this.prioridad = prioridad;
		this.tipoPrioridad = tipoPrioridad;
	}
	
	public Long getSolId() {
		return solId;
	}
	public void setSolId(Long solId) {
		this.solId = solId;
	}
	public Long getSolItemId() {
		return solItemId;
	}
	public void setSolItemId(Long solItemId) {
		this.solItemId = solItemId;
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getUsuario() {
		return (this.primerNombre + " " + this.segundoNombre + " " + this.primerApellido + " " + this.segundoApellido);
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getEps() {
		return eps;
	}
	public void setEps(String eps) {
		this.eps = eps;
	}
	public String getOrigino() {
		return origino;
	}
	public void setOrigino(String origino) {
		this.origino = origino;
	}
	public String getTipoCTC() {
		return tipoCTC;
	}
	public void setTipoCTC(String tipoCTC) {
		this.tipoCTC = tipoCTC;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getTipoPrioridad() {
		return tipoPrioridad;
	}
	public void setTipoPrioridad(String tipoPrioridad) {
		this.tipoPrioridad = tipoPrioridad;
	}
	
	public static void main(String[] args) {
		/*Class<BandejaAcXls> clase = BandejaAcXls.class;
		int i = 0;
		for (Field f : clase.getDeclaredFields()){
			System.out.println("rowhead.createCell((short) "+ (i++) +").setCellValue(new HSSFRichTextString(\""+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1)+"\"));");
		}
		System.out.println();
		i=0;
		for (Field f : clase.getDeclaredFields()){
			System.out.println("row.createCell((short) "+ (i++) +").setCellValue(new HSSFRichTextString(item.get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1)+"()));");
		}*/
		System.out.println(StringUtils.isEmpty(null));
	}
	
}
