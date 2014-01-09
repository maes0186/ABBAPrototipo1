package com.conexia.saludcoop.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.Contrato;

public interface ContratoRepository extends CrudRepository<Contrato, Long> {

	Contrato findOneBySedeIpsId(Long id);

    Contrato findOneBySedeIpsIdAndEpsIdAndEstadoId(Long sedeIpsId, Long epsId, Long estadoId);
}
