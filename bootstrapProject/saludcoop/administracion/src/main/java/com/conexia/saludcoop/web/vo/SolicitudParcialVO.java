package com.conexia.saludcoop.web.vo;

import java.text.SimpleDateFormat;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudParcialDto;

public class SolicitudParcialVO {
	private Integer id;
	private Integer nroSolicitud;
	private String 	nombreAfiliado;
	private String tipoIdentificacionAfiliado;
	private String	numeroIdentificacionAfiliado;
	private String 	fechaCreacion;
	private String 	fechaUpdate;
	
	public Integer getNroSolicitud() {
		return nroSolicitud;
	}

	public void setNroSolicitud(Integer nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}

	public SolicitudParcialVO(){}
	
	public SolicitudParcialVO(SolicitudParcialDto solParcialDto) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		
		this.id = solParcialDto.getId();     
		               
		this.tipoIdentificacionAfiliado = solParcialDto.getTipoIdentificacionAfiliado().getDescripcion();  
		this.numeroIdentificacionAfiliado = solParcialDto.getNumeroIdentificacionAfiliado();
		this.nombreAfiliado = solParcialDto.getNombreCompletoAfiliado();
		
		if(solParcialDto.getFechaCreacion()!= null){
			this.fechaCreacion = sdf.format(solParcialDto.getFechaCreacion());
		}
		if(solParcialDto.getFechaUpdate()!= null){
			this.fechaUpdate = sdf.format(solParcialDto.getFechaUpdate());
		}
		if(solParcialDto.getNroSolicitud()!= null){
			this.nroSolicitud = solParcialDto.getNroSolicitud();
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipoIdentificacionAfiliado() {
		return tipoIdentificacionAfiliado;
	}
	public void setTipoIdentificacionAfiliado(String tipoIdentificacionAfiliado) {
		this.tipoIdentificacionAfiliado = tipoIdentificacionAfiliado;
	}
	public String getNumeroIdentificacionAfiliado() {
		return numeroIdentificacionAfiliado;
	}
	public void setNumeroIdentificacionAfiliado(String numeroIdentificacionAfiliado) {
		this.numeroIdentificacionAfiliado = numeroIdentificacionAfiliado;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaUpdate() {
		return fechaUpdate;
	}

	public void setFechaUpdate(String fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}

    public String getNombreAfiliado() {
    	return nombreAfiliado;
    }

    public void setNombreAfiliado(String nombreAfiliado) {
    	this.nombreAfiliado = nombreAfiliado;
    }
}
