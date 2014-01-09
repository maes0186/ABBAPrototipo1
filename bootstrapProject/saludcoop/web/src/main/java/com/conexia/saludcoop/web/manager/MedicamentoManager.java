package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.MedicamentoCondicionado;
import com.conexia.saludcoop.common.entity.maestro.Programa;
import com.conexia.saludcoop.common.entity.maestro.TipoPPM;
import com.conexia.saludcoop.common.enumerator.TipoFrecuencia;
import com.conexia.saludcoop.common.repository.MedicamentoCondicionadoRepository;
import com.conexia.saludcoop.common.repository.MedicamentoRepository;
import com.conexia.saludcoop.common.repository.TipoPPMRepository;
import com.conexia.saludcoop.common.repository.custom.ExtendedAfiliadoRepository;
@Component
@Transactional
public class MedicamentoManager extends GeneralManager {

    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @Autowired
    private MedicamentoCondicionadoRepository medicamentoCondicionadoRepository;
    @Autowired
    private TipoPPMRepository tipoPPMRepository;
    
    @Autowired
    private ExtendedAfiliadoRepository afiliadoRepository;

    public List<MedicamentoDto> getMedicamentosByCodigoDescripcionComercial(String codigo, String descripcion, boolean comercial) {

        List<MedicamentoDto> medicamentosDTO = new java.util.Vector<>();

        List<Medicamento> medicamentos = (comercial? medicamentoRepository.findByCodigoLikeAndDescripcionLikeAllIgnoreCase(getLikeParameter(codigo),
                getLikeParameter(descripcion)): medicamentoRepository.findByCodigoLikeAndDescripcionLikeAllIgnoreCaseAndComercial(getLikeParameter(codigo),
                getLikeParameter(descripcion),(short)(0)));
        for (Medicamento medicamento : medicamentos) {

            medicamentosDTO.add(medicamento.toDto());

        }

        return medicamentosDTO;
    }

    public List<MedicamentoDto> getMedicamentosPOSByCodigoDescripcion(String codigo, String descripcion) {

        List<MedicamentoDto> medicamentosDTO = new java.util.Vector<>();

        List<Medicamento> medicamentos = medicamentoRepository.findByTipoIdAndCodigoLikeIgnoreCaseAndDescripcionLikeIgnoreCase(
                TipoPPM.POS_ID, getLikeParameter(codigo), getLikeParameter(descripcion));
        for (Medicamento medicamento : medicamentos) {

            medicamentosDTO.add(medicamento.toDto());

        }

        return medicamentosDTO;
    }
    
    public MedicamentoDto getMedicamentoById(Long id) {
        return medicamentoRepository.findOne(id).toDto();

    }

    public MedicamentoDto getMedicamentoByCodigo(String codigo) {
        return medicamentoRepository.findOneByCodigoIgnoreCase(codigo).toDto();

    }

    public List<MedicamentoDto> findAll() {
        List<MedicamentoDto> medicamentosDTO = new ArrayList<>();
        for (Medicamento medicamento : medicamentoRepository.findAll()) {
            medicamentosDTO.add(medicamento.toDto());
        }
        return medicamentosDTO;
    }

    /**
     * Verifica si el valor de las unidades aprobadas para el medicamento enviadas por parámetro, cumplen con el rango establecido para el
     * medicamento
     * 
     * @param idSolicitudItem
     * @param unidadesAprobadas
     * @param diasPeriodo
     * @return
     */
    public Object[] validarRangoEntrega(Long idSolicitudItem, Integer unidadesAprobadas, Integer diasPeriodo, Integer numeroEntregas) {
        Medicamento medicamento = medicamentoRepository.obetenerMedicamentoPorSolictudItem(idSolicitudItem);
        Integer cantidadMaxima = medicamento.getCantidadMaximaAutorizada();
        Integer cantidadMinima = medicamento.getAcMenorC();

        if (diasPeriodo == null) {
            diasPeriodo = 1;
        }

        if (unidadesAprobadas == null) {
            unidadesAprobadas = 0;
        }

        Integer cantidadMes = 0;

        if ((numeroEntregas * diasPeriodo) > 30) {
            cantidadMes = Double.valueOf(Math.round((30d / diasPeriodo) * unidadesAprobadas)).intValue();
        } else {
            cantidadMes = unidadesAprobadas;
        }

        if ((cantidadMaxima != null && cantidadMinima != null) && cantidadMes >= cantidadMinima && cantidadMes <= cantidadMaxima) {
            return new Object[] { 0, "Exitoso" };
        } else if (cantidadMaxima == null || cantidadMinima == null) {
            return new Object[] { 1, "La cantidad mínima y/o maxima son nulas" };
        } else {
            return new Object[] { 2, "(" + cantidadMinima + " y " + cantidadMaxima + ")", "" + cantidadMes };
        }

    }

    /**
     * Verifica si el valor de las unidades aprobadas para el medicamento enviadas por parámetro, cumplen con el rango establecido para el
     * medicamento
     * 
     * @return
     */
    @Transactional
    public Object[] validarRangoMedicamentoSolicitud(Long idMedicamento, Integer dosis, Integer frecuencia, Integer cada, Integer duracion,
            Long afiliadoId) {
        Medicamento medicamento = medicamentoRepository.findOne(idMedicamento);
        
        Integer cantidadMaxima = medicamento.getCantidadMaximaAutorizada();
        Integer cantidadMinima = medicamento.getAcMenorC();

        if (dosis == null) {
            dosis = 0;
        }

        if (frecuencia == null) {
            frecuencia = 1;
        }

        Double frecuenciaD = 0d;
        if (TipoFrecuencia.HORAS.equals(TipoFrecuencia.fromId(cada))) {
            frecuenciaD = frecuencia / 24d;
        } else {
            frecuenciaD = Double.valueOf(frecuencia);
        }

        Integer cantidad = 0;

        Integer cantidadMes = 0;

        if (duracion > 30) {
            cantidad = Double.valueOf(Math.round(dosis * (30 / frecuenciaD))).intValue();
        } else {
            cantidad = Double.valueOf(Math.round(dosis * (duracion / frecuenciaD))).intValue();
        }

        cantidadMes = cantidad;

        if ((cantidadMaxima != null && cantidadMinima != null) && cantidadMes >= cantidadMinima && cantidadMes <= cantidadMaxima) {

            cantidad = Double.valueOf(Math.round(dosis * (duracion / frecuenciaD))).intValue();
            
            // Se verifica si el medicamento pertenece a programas crónicos
            List<Programa> programas = afiliadoRepository.getProgrmasByIdAfiliado(afiliadoId);
            
            if(medicamentoPertenecePrograma(programas, medicamento)) {

                // Se genera el número de entregas para determinar si se cumple que sean mínimo 2 y máximo 6
                Integer numEntregas = Double.valueOf(
                        Math.ceil(cantidad / Double.valueOf(cantidadMes))).intValue();
                
                if(numEntregas < 2 || numEntregas > 6) {
                    return new Object[] { 3, "Número inválido de entregas para un medicamento en programas crónicos" };
                }
            }
            
            return new Object[] { 0, "Exitoso" };
        } else if (cantidadMaxima == null || cantidadMinima == null) {
            return new Object[] { 1, "La cantidad mínima y/o maxima son nulas" };
        } else {
            return new Object[] { 2, "(" + cantidadMinima + " y " + cantidadMaxima + ")", "" + cantidadMes };
        }

    }

    /**
     * Verifica si el medicamento enviado por parámetro pertence a la lista de programas del afiliado
     * 
     * @param programas
     * @param medicamento
     * @return
     */
    private boolean medicamentoPertenecePrograma(List<Programa> programas, Medicamento medicamento) {
        if (programas != null) {
            for (Programa programa : programas) {
                if (programa.getMedicamentos() != null) {
                    for (Medicamento med : programa.getMedicamentos()) {
                        if (medicamento.getId().compareTo(med.getId()) == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Verifica si un medicamento es condicionado y, en caso afirmativo, si el diagnostico asociado es valido
     * 
     * @param med
     * @param dx
     * @return
     */
    public boolean cumpleMedicamentoCondicionadoDiagnostico(String idMedicamento, String codigoDx) {
        boolean cumple = false;
    	List<MedicamentoCondicionado> meds = medicamentoCondicionadoRepository.findByMedicamentoId(Long.valueOf(idMedicamento));
        if (meds.isEmpty())
        	cumple = true;
        else {
        	for (MedicamentoCondicionado med : meds) {
				if (med.getDiagnostico().getCodigo().equals(codigoDx))
					cumple = true;
			}
        }
        return cumple;
    }
    public MedicamentoDto findById(Long id) {

		return medicamentoRepository.findOne(id).toDto();
	}

	public MedicamentoDto getMedicamentoHomologo(Long id) {
		Medicamento med = medicamentoRepository.findOne(id);
		
		return med.getHomologo()!=null?med.getHomologo().toDto():null;
	}
}
