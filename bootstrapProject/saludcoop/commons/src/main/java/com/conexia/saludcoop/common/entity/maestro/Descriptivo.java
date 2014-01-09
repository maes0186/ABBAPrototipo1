package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Mappeable(mappedTo=DescriptivoDto.class)
public abstract class Descriptivo extends BaseMaestro implements Identifiable<Integer>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name="codigo", nullable=true, length=50)	
	private String codigo;
	
	@Column(name="descripcion", nullable=false, length=500)
	private String descripcion;
	@Column(name = "cliente_pk")
	private Integer clientePk;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public DescriptivoDto toDto(){
		DescriptivoDto dto = new DescriptivoDto();
		dto.setId(this.id);
		dto.setCodigo(this.codigo);
		dto.setDescripcion(this.descripcion);
		return dto;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}
}
