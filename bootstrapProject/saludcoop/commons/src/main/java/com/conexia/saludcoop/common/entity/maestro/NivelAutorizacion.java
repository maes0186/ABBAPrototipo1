package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="nivel_autorizacion", schema="maestros")
public class NivelAutorizacion extends Descriptivo{

	public static final int NIVEL_1 = 1;
	public static final int NIVEL_2 = 2;
	public static final int NIVEL_3 = 3;
	public static final int NIVEL_4 = 4;
	public static final int NIVEL_5 = 5;

}
