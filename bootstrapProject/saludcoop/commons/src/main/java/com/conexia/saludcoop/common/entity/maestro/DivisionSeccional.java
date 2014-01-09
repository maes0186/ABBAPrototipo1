package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DivisionSeccionalDto;

@Entity
@Table(name = "division_seccional", schema="maestros")
@Mappeable(mappedTo=DivisionSeccionalDto.class)
public class DivisionSeccional {
	
	/**
	 * Identificador de la division seccional.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	
	@Index(name="ix_division_seccional_descripcion")
	@Column(name = "descripcion", nullable = false, length=100)
	private String descripcion;

	@Index(name="ix_division_seccional_codigo")
	@Column(name = "codigo", nullable = true, length=10)
	private String codigo;
	
	@ManyToOne
	@JoinColumn( name="eps_id", nullable = true)
	private Eps eps;

	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}
	
    public String getCodigo() {
    	return codigo;
    }
    

	
    public void setCodigo(String codigo) {
    	this.codigo = codigo;
    }

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}
}
