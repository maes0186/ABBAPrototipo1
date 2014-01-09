package com.conexia.saludcoop.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.TipoPPM;
import com.conexia.saludcoop.common.entity.maestro.TipoTecnologia;
import com.conexia.saludcoop.common.entity.maestro.Vigencia;

public interface VigenciaRepository extends CrudRepository<Vigencia, Integer>{

	@Query("select vig from Vigencia vig where vig.tipoTecnologia = ? and vig.tipoPPM = ?")
	public Vigencia findVigenciaByTipoTecnologiaAndTipoPPM(TipoTecnologia tipoTecnologia, TipoPPM tipoPPM);
}
