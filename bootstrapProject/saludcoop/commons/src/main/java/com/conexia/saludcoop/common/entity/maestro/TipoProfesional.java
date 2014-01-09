package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DescriptivoDto;


/**
 * Lista los tipos de afiliados según Estructura_BDUA_Ejemplo.xls.
 * 
 * @author ebarbin
 *
 */
@Entity
@Table(name="tipo_profesional", schema="maestros")
@Mappeable(mappedTo=DescriptivoDto.class)
public class TipoProfesional extends Descriptivo{
}
