package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.TipoPlanAfiliadoDto;

@Entity
@Table(name = "tipo_plan_afiliado", schema="maestros")
@Mappeable(mappedTo=TipoPlanAfiliadoDto.class)
public class TipoPlanAfiliado extends Descriptivo {
	
}
