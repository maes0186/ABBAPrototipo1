package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.TipoPlanContratoDto;


@Entity
@Table(name = "tipo_plan_contrato", schema="maestros")
@Mappeable(mappedTo=TipoPlanContratoDto.class)
public class TipoPlanContrato extends Descriptivo {
	
}
