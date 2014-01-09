package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DescriptivoDto;

@Entity
@Table(name="tipo_PPM", schema="maestros")
@Mappeable(mappedTo=DescriptivoDto.class)
public class TipoPPM extends Descriptivo{
	public static final int POS_ID = 1;
	
	
}
