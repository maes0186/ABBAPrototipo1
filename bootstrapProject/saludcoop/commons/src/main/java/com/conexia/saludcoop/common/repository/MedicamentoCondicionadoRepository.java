package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.MedicamentoCondicionado;

public interface MedicamentoCondicionadoRepository extends CrudRepository<MedicamentoCondicionado, Long> {

	public List<MedicamentoCondicionado> findByMedicamentoId(Long idMedicamento);
}
