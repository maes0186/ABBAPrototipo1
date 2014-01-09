package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="estado_procedimiento", schema="maestros")
public class EstadoProcedimiento extends Descriptivo{
	public static final Long ACTIVO = 1l;
}
