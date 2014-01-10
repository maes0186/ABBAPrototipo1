package com.conexia.saludcoop.web.vo;

public class DocumentoSoporteVO {

	private Long id;
	private String tipoSoporte;
	private String nombreArchivoServidor;
	private String nombreArchivoOriginal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoSoporte() {
		return tipoSoporte;
	}

	public void setTipoSoporte(String tipoSoporte) {
		this.tipoSoporte = tipoSoporte;
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
}
