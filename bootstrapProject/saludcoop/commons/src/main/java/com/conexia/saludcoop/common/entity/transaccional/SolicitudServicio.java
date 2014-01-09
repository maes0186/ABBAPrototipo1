package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.com.conexia.common.persistence.entity.Identifiable;
/*@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="solicitud_servicio", schema="transaccional")*/
public abstract class SolicitudServicio implements Identifiable<Long> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	public Long getId() {
		return id;
	}

}
