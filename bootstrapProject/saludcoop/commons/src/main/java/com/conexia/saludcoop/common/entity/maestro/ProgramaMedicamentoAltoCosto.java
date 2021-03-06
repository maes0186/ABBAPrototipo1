package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DescriptivoDto;

@Entity
@Table(name = "programa_medicamento_alto_costo", schema = "maestros")
@Mappeable(mappedTo=DescriptivoDto.class)
public class ProgramaMedicamentoAltoCosto extends Descriptivo{

}
