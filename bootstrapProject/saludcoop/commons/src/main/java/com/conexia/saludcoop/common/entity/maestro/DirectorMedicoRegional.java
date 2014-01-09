package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

/**
 * @author ebarbin
 *
 */
@Entity
@Table(name="director_medico_regional",schema="maestros")
public class DirectorMedicoRegional  extends BaseMaestro implements Identifiable<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "nombre_apellido", nullable = false, length=50)
	private String nombreApellido;
	
	@Column(name = "email", nullable = false, length=50)
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "regional_id")
	private Regional regional;

    public Long getId() {
    	return id;
    }
	
    public void setId(Long id) {
    	this.id = id;
    }
	
    public String getNombreApellido() {
    	return nombreApellido;
    }
	
    public void setNombreApellido(String nombreApellido) {
    	this.nombreApellido = nombreApellido;
    }
	
    public String getEmail() {
    	return email;
    }
	
    public void setEmail(String email) {
    	this.email = email;
    }

    public Regional getRegional() {
    	return regional;
    }
	
    public void setRegional(Regional regional) {
    	this.regional = regional;
    }
}
