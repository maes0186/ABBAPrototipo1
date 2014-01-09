package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.DescriptivoDto;

@Entity
@Table(name = "grupo_poblacional", schema = "maestros")
@Mappeable(mappedTo=DescriptivoDto.class)
public class GrupoPoblacional extends Descriptivo{
	
	@Column(name="eximido_regimen_subsidiado")
	private Short  eximidoRegimenSubsidiado;

	public Short getEximidoRegimenSubsidiado() {
		return eximidoRegimenSubsidiado;
	}

	public void setEximidoRegimenSubsidiado(
			Short eximidoRegimenSubsidiado) {
		this.eximidoRegimenSubsidiado = eximidoRegimenSubsidiado;
	}
}
