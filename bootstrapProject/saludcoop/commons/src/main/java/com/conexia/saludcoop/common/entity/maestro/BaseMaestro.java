package com.conexia.saludcoop.common.entity.maestro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public  class BaseMaestro {
	
	
	@Column(name="fecha_delete", nullable=true)
	private Date fechaDelete;
	@Column(name="fecha_insert", nullable = false, columnDefinition="timestamp default now()")
	private Date fechaInsert;
	@Column(name="fecha_update", nullable = false,  columnDefinition="timestamp default now()")
	private Date fechaUpdate;
	@Column(name="version", nullable = false, columnDefinition="int4 default 1")
	private Integer version;

	public final Date getFechaDelete() {
		return fechaDelete;
	}

	public final void setFechaDelete(Date fechaDelete) {
		this.fechaDelete = fechaDelete;
	}

	public final Date getFechaInsert() {
		return fechaInsert;
	}

	public final void setFechaInsert(Date fechaInsert) {
		this.fechaInsert = fechaInsert;
	}

	public final Date getFechaUpdate() {
		return fechaUpdate;
	}

	public final void setFechaUpdate(Date fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}

	public final Integer getVersion() {
		return version;
	}

	public final void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
}
