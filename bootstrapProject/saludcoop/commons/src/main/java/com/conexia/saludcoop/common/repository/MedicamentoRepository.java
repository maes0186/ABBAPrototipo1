package com.conexia.saludcoop.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.conexia.saludcoop.common.entity.maestro.Medicamento;

public interface MedicamentoRepository extends CrudRepository<Medicamento, Long> {

    public List<Medicamento> findByCodigoLikeAndDescripcionLikeAllIgnoreCase(String codigo, String descripcion);
    
	public List<Medicamento> findByCodigoLikeAndDescripcionLikeAllIgnoreCaseAndComercial(String codigo, String descripcion, Short comercial);
	
	public Medicamento findOneByCodigoIgnoreCase(String codigo);

	public List<Medicamento> findByTipoIdAndCodigoLikeIgnoreCaseAndDescripcionLikeIgnoreCase(Integer posId, String codigo, String descripcion);
		
	@Query(value = "select med from SolicitudItem sit JOIN sit.solMedicamento smed JOIN smed.medicamento med where sit.nroItem=?1")
	public Medicamento obetenerMedicamentoPorSolictudItem(Long idSolicitudItem);
}
