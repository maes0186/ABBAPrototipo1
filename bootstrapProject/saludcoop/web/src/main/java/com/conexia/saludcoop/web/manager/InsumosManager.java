package com.conexia.saludcoop.web.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.TipoPPM;
import com.conexia.saludcoop.common.repository.InsumoRepository;
import com.conexia.saludcoop.common.repository.TipoPPMRepository;
import com.conexia.saludcoop.web.manager.exceptions.NoEncontradoException;

@Component
@Transactional
public class InsumosManager extends GeneralManager {

	@Autowired
	private InsumoRepository insumoRepo;

	@Autowired
	private TipoPPMRepository tipoPPMRepository;

	public List<InsumoDto> getInsumoByCodigoDescripcion(String codigo, String descripcion) {

		List<InsumoDto> insumosDTO = new java.util.Vector<>();

		List<Insumo> insumos = insumoRepo.findByCodigoLikeAndDescripcionLikeAllIgnoreCase(getLikeParameter(codigo), getLikeParameter(descripcion));
		for (Insumo medicamento : insumos) {

			insumosDTO.add(medicamento.toDto());

		}

		return insumosDTO;

	}

	public List<InsumoDto> getInsumosPOSByCodigoDescripcion(String codigo, String descripcion) {
		  List<InsumoDto> insumosDTO = new java.util.Vector<>();

	        List<Insumo> insumos = insumoRepo.findByTipoIdAndCodigoLikeIgnoreCaseAndDescripcionLikeIgnoreCase(
	                TipoPPM.POS_ID, getLikeParameter(codigo), getLikeParameter(descripcion));
	        for (Insumo insumo : insumos) {

	            insumosDTO.add(insumo.toDto());

	        }

	        return insumosDTO;
		
	}

	public InsumoDto findById(Long id) throws NoEncontradoException {
		Insumo i = insumoRepo.findOne(id);
		if(i == null){
			throw new NoEncontradoException(id);
		}
		return i.toDto();
	}

	public InsumoDto getInsumoHomologo(Long id) {
		Insumo ins = insumoRepo.findOne(id);
		
		return ins.getHomologo()!=null?ins.getHomologo().toDto():null;
	}

}
