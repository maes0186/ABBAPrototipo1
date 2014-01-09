package com.conexia.saludcoop.common.dto.transaccional;

import com.conexia.saludcoop.common.dto.DescriptivoDto;

public class DocumentoSoporteDto {
	private Long id;
	private DescriptivoDto tipoDocSoporte;
	private String nombreArchivoServidor;
	private String nombreArchivoOriginal;

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreArchivoOriginal() {
		return nombreArchivoOriginal;
	}

	public void setNombreArchivoOriginal(String nombreArchivoOriginal) {
		this.nombreArchivoOriginal = nombreArchivoOriginal;
	}

	public Long getId() {
		return this.id;
	}

	public void setTipoDocSoporte(DescriptivoDto tipoDocSoporte) {
		this.tipoDocSoporte = tipoDocSoporte;
	}

	public DescriptivoDto getTipoDocSoporte() {
		return this.tipoDocSoporte;
	}

	public void setNombreArchivoServidor(String nombreArchivoServidor) {
		this.nombreArchivoServidor = nombreArchivoServidor;
	}

	public String getNombreArchivoServidor() {
		return this.nombreArchivoServidor;
	}

}
