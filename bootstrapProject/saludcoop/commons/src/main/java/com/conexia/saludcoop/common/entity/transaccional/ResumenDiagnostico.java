package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.entity.maestro.Diagnostico;

/**
 * Entidad que relaciona Diagnostico con la Solicitud
 * @author fcostazini
 *
 */
@Entity
@Table(name = "resumen_diagnostico", schema = "transaccional")
public class ResumenDiagnostico implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne
	private Diagnostico diagnostico;

	@Column(name="es_principal", nullable=false)
	private Boolean esPrincipal = false;

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public boolean isEsPrincipal() {
		return esPrincipal;
	}

	public void setEsPrincipal(boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}

	public Long getId() {
		return id;
	}



}
