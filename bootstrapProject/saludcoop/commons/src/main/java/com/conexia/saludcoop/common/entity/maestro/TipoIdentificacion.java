package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;

@Entity
@Table(name="tipo_identificacion",schema="maestros")
public class TipoIdentificacion extends BaseMaestro implements Identifiable<Integer>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Index(name="ix_tipo_identificacion_codigo")
	@Column(name="codigo", nullable=true, length=50)	
	private String codigo;
	@Index(name="ix_tipo_identificacion_descripcion")
	@Column(name="descripcion", nullable=false, length=100)
	private String descripcion;
	@Column(name = "es_alfanumerico", nullable = false)
	private Short esAlfanumerico;
	@Column(name="min_length", nullable=false)	
	private Integer minLength;
	@Column(name="max_length", nullable=false)	
	private Integer maxLength;
	@Column(name="cliente_pk")	
	private Integer clientePk;
	@Column(name="aplica_ips") 
    private Short aplicaIps;
	@Column(name="aplica_afiliado") 
    private Short aplicaAfiliado;
	@Column(name="aplica_profesional") 
    private Short aplicaProfesional;
	
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
	public Short getEsAlfanumerico() {
		return esAlfanumerico;
	}
	public void setEsAlfanumerico(Short esAlfanumerico) {
		this.esAlfanumerico = esAlfanumerico;
	}
	
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public TipoIdentificacionDto toDto(){
		TipoIdentificacionDto dto = new TipoIdentificacionDto();
		dto.setId(this.id);
		dto.setCodigo(this.codigo);
		dto.setDescripcion(this.descripcion);
		dto.setEsAlfanumerico(this.esAlfanumerico);
		dto.setMinLength(this.minLength);
		dto.setMaxLength(this.maxLength);
		dto.setAplicaAfiliado(this.aplicaAfiliado);
		dto.setAplicaIps(this.aplicaIps);
		dto.setAplicaProfesional(this.aplicaProfesional);
		return dto;
	}
	@Override
	public boolean equals(Object obj) {
		
		return this.id.equals(((TipoIdentificacion)obj).getId());
	}
	public Integer getClientePk() {
		return clientePk;
	}
	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}
    public Short getAplicaIps() {
        return aplicaIps;
    }
    public void setAplicaIps(Short aplicaIps) {
        this.aplicaIps = aplicaIps;
    }
    public Short getAplicaAfiliado() {
        return aplicaAfiliado;
    }
    public void setAplicaAfiliado(Short aplicaAfiliado) {
        this.aplicaAfiliado = aplicaAfiliado;
    }
    public Short getAplicaProfesional() {
        return aplicaProfesional;
    }
    public void setAplicaProfesional(Short aplicaProfesional) {
        this.aplicaProfesional = aplicaProfesional;
    }
		
}
