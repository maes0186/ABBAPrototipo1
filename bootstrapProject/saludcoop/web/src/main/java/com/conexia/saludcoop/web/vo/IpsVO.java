package com.conexia.saludcoop.web.vo;

import com.conexia.saludcoop.common.dto.IpsDto;


/**
 * <b>Bandeja auditor alto costo</b> 
 * Clase usada para mostrar los datos de las ips
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 16/10/2013
 * 
 */
public class IpsVO { 
	private Long id;
	private String direccion;
	private String telefono;
	private String razonSocial;
	private String tipoIdentificacion;
	private String numeroIdentificacion;

	public IpsVO() {	    
	}
    public IpsVO(IpsDto ipsDto) {
        tipoIdentificacion = ipsDto.getTipoIdentificacion().getDescripcion();
        numeroIdentificacion = ipsDto.getNumeroIdentificacion();
        razonSocial = ipsDto.getRazonSocial();
    }
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
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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

}
