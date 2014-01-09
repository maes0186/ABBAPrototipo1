package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.entity.maestro.TipoDocumentoSoporte;

@Entity
@Table(name = "documento_soporte", schema = "transaccional")
public class DocumentoSoporte implements Identifiable<Long> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "tipo_doc_soporte_id", nullable = false)
	private TipoDocumentoSoporte tipoDocSoporte;
	
	@Column(name="nombre_archivo_servidor")
	private String nombreArchivoServidor;
	
	@Column(name="nombre_archivo_original", nullable = false)
	private String nombreArchivoOriginal;
	
	@ManyToOne
	@JoinColumn(name="solicitud_id", nullable=true)
	private Solicitud solicitud;
	

	@ManyToOne
	@JoinColumn(name="solicitud_item_id", nullable=true)
	private SolicitudItem solicitudItem;
	
	public Long getId() {
		return id;
	}



	public TipoDocumentoSoporte getTipoDocSoporte() {
		return tipoDocSoporte;
	}

	public void setTipoDocSoporte(TipoDocumentoSoporte tipoDocSoporte) {
		this.tipoDocSoporte = tipoDocSoporte;
	}

	public String getNombreArchivoServidor() {
		return nombreArchivoServidor;
	}

	public void setNombreArchivoServidor(String nombreArchivoServidor) {
		this.nombreArchivoServidor = nombreArchivoServidor;
	}



	public String getNombreArchivoOriginal() {
		return nombreArchivoOriginal;
	}



	public void setNombreArchivoOriginal(String nombreArchivoOriginal) {
		this.nombreArchivoOriginal = nombreArchivoOriginal;
	}



	public Solicitud getSolicitud() {
		return solicitud;
	}



	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}



	public SolicitudItem getSolicitudItem() {
		return solicitudItem;
	}



	public void setSolicitudItem(SolicitudItem solicitudItem) {
		this.solicitudItem = solicitudItem;
	}

	
}
