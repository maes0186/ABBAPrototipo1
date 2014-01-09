package com.conexia.saludcoop.common.entity.maestro;

import static com.conexia.saludcoop.common.util.SystemConstants.SHORT_FALSE;
import static com.conexia.saludcoop.common.util.SystemConstants.SHORT_TRUE;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

@Entity
@Table(name = "servicio", schema = "maestros")

public class Servicio extends BaseMaestro implements Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -223609332426438244L;

	/**
	 * Identificador del servicio.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "descripcion", nullable = false, length = 50)
	private String descripcion;
	
	@Column(name = "codigo_ministerio_salud", nullable = false)
	private Integer codigoMinisterioSalud;
	
	@Column(name = "nivel_de_atencion", nullable = true)
	private Integer nivelDeAtencion;//TODO revisar si este campo se usa.
	
	@Column(name = "homologacion", nullable = false)
	private Short homologacion;
	
	@Column(name = "hospitalario", nullable = false)
	private Short hospitalario;
	
	@ManyToOne
	@JoinColumn( name="unidad_funcional_id", nullable = true)
	private UnidadFuncional unidadFuncional;

	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="servicio")
	private Set<ServicioEspecialidad> especialidades;

	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigoMinisterioSalud() {
		return codigoMinisterioSalud;
	}

	public void setCodigoMinisterioSalud(Integer codigoMinisterioSalud) {
		this.codigoMinisterioSalud = codigoMinisterioSalud;
	}

	public Boolean getHospitalario() {
		return hospitalario==SHORT_TRUE?true:false;
	}

	public void setHospitalario(Boolean hospitalario) {
		this.hospitalario = hospitalario?SHORT_TRUE:SHORT_FALSE;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<ServicioEspecialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<ServicioEspecialidad> especialidades) {
		this.especialidades = especialidades;
	}

	public Integer getNivelDeAtencion() {
		return nivelDeAtencion;
	}

	public void setNivelDeAtencion(Integer nivelDeAtencion) {
		this.nivelDeAtencion = nivelDeAtencion;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public UnidadFuncional getUnidadFuncional() {
		return unidadFuncional;
	}

	public void setUnidadFuncional(UnidadFuncional unidadFuncional) {
		this.unidadFuncional = unidadFuncional;
	}

	public Boolean getHomologacion() {
		return homologacion==SHORT_TRUE?true:false;
	}

	public void setHomologacion(Boolean homologacion) {
		this.homologacion = homologacion?SHORT_TRUE:SHORT_FALSE;
	}

}
