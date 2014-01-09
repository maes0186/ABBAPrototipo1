package com.conexia.saludcoop.validador.businessRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.InfoEntregasDto;
import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudInsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudProcedimientoDto;
import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;
import com.conexia.saludcoop.common.entity.transaccional.Solicitud;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudParcial;
import com.conexia.saludcoop.common.repository.AutorizacionRepository;
import com.conexia.saludcoop.common.repository.SolicitudDao;
import com.conexia.saludcoop.common.repository.SolicitudParcialRepository;
import com.conexia.saludcoop.common.repository.SolicitudRepository;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;
import com.conexia.saludcoop.validador.util.ParserSolicitudEntity;

@Component
@Rule(description = "Proceso que guarda la solicitud.")
public class RNSaveSolicitud extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RNSaveSolicitud.class);
    @Autowired
    SolicitudRepository sr;

    @Autowired
    AutorizacionRepository ar;

    @Autowired
    private ParserSolicitudEntity parserEntity;
    
    @Autowired
    private SolicitudParcialRepository solParRepo;
    
    @Autowired
    private SolicitudDao sDao;

    @SuppressWarnings("unchecked")
    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {

        Map<Integer, AutorizacionDto> autorizacionesDto = new HashMap<Integer, AutorizacionDto>();
        Map<Long, Autorizacion> autorizaciones = new HashMap<Long, Autorizacion>();
        SolicitudDto dto = (SolicitudDto) aContext.get(ConstantesContexto.SOLICITUD);
        Solicitud sol = parserEntity.getSolicitud(dto);
        if(sol.getNroSolicitud()== null)
        	sol.setNroSolicitud(sDao.getNextNumeroSolicitud());
        sr.save(sol);
        SolicitudParcial solPar = solParRepo.findOneByNroSolicitud(sol.getNroSolicitud());
        if(solPar!= null){
        	solParRepo.delete(solPar);
        }
        
        
        List<InfoEntregasDto> infoEntregas = new ArrayList<>();
        HashMap<String, String> mensajesMap = (HashMap<String, String>)aContext.get(ConstantesContexto.MSJS_ITEMS);
        
        for (SolicitudItem solItem : sol.getSolicitudItems()) {
        	autorizaciones.put(solItem.getAutorizacion().getId(), solItem.getAutorizacion());
        	
            AutorizacionDto aut = null;
            if (autorizacionesDto.get(solItem.getAutorizacion().getNumeroAutorizacion().intValue()) != null)
            	aut = autorizacionesDto.get(solItem.getAutorizacion().getNumeroAutorizacion().intValue());
            else{
            	aut = new AutorizacionDto();
            	autorizacionesDto.put(solItem.getAutorizacion().getNumeroAutorizacion().intValue(), aut);
            	aut.setNumero(solItem.getAutorizacion().getNumeroAutorizacion().intValue());
                DescriptivoDto estado = new DescriptivoDto();
                estado.setDescripcion(solItem.getAutorizacion().getEstadoAutorizacion().getDescripcion());;
                aut.setEstadoAutorizacion(estado);
                aut.setSolicitudItems(new ArrayList<SolicitudItemDto>());
                aut.setNumeroSolicitud(new Long(sol.getNroSolicitud()));
            }
                        
            SolicitudItemDto item = new SolicitudItemDto();
            item.setNroItem(solItem.getNroItem());
            item.setCantidad(solItem.getCantidad());
            if (solItem.isMedicamento()){
            	SolicitudMedicamentoDto sm = new SolicitudMedicamentoDto();
            	MedicamentoDto m = new MedicamentoDto();
            	m.setCodigo(solItem.getSolMedicamento().getMedicamento().getCodigo());
            	m.setDescripcion(solItem.getSolMedicamento().getMedicamento().getDescripcion());
            	sm.setMedicamento(m);
            	item.setSolMedicamento(sm);
            	
            	if(mensajesMap != null){
                    String mensajeItem =  mensajesMap.get(m.getCodigo());
                      
                    if(mensajeItem != null){
                       item.setMensajeValidacion(mensajeItem);
                    }
                 }
            }
            else if(solItem.isProcedimiento()){
            	SolicitudProcedimientoDto sp = new SolicitudProcedimientoDto();
            	ProcedimientoDto p = new ProcedimientoDto();
            	p.setCodigo(solItem.getSolProcedimiento().getProcedimiento().getCodigo());
            	p.setDescripcion(solItem.getSolProcedimiento().getProcedimiento().getDescripcion());
            	sp.setProcedimiento(p);
            	item.setSolProcedimiento(sp);
            } else if(solItem.isInsumo()){
            	
                	SolicitudInsumoDto sm = new SolicitudInsumoDto();
                	InsumoDto m = new InsumoDto();
                	m.setCodigo(solItem.getSolInsumo().getInsumo().getCodigo());
                	m.setDescripcion(solItem.getSolInsumo().getInsumo().getDescripcion());
                	sm.setInsumo(m);
                	item.setSolInsumo(sm);
                	
                	if(mensajesMap != null){
                        String mensajeItem =  mensajesMap.get(m.getCodigo());
                          
                        if(mensajeItem != null){
                           item.setMensajeValidacion(mensajeItem);
                        }
                     }
                }
            
            aut.getSolicitudItems().add(item);

            if (solItem.getSolMedicamento() != null) {
                InfoEntregasDto infoEntrega = new InfoEntregasDto();
                infoEntrega.setSolicitud(true);
                infoEntrega.setDiasPeriodo(30);
                infoEntrega.setItemId(solItem.getId());
                infoEntrega.setDosis(solItem.getSolMedicamento().getFormulaMedicamento().getDosis());
                infoEntrega.setFrecuencia(solItem.getSolMedicamento().getFormulaMedicamento().getFrecuencia());
                infoEntrega.setCada(solItem.getSolMedicamento().getFormulaMedicamento().getTipoFrecuenciaId());
                infoEntrega.setUnidadesAprobadas(solItem.getCantidad());
                infoEntregas.add(infoEntrega);
            }
        }
        
        aContext.put(ConstantesContexto.INFO_ENTREGAS, infoEntregas);

        // recorrer todos los items y numerarlos de 1 a n
        aContext.put(ConstantesContexto.AUTORIZACIONES, autorizaciones.values());
        aContext.put(ConstantesContexto.AUTORIZACIONES_DTO, autorizacionesDto.values());
        boolean result = true;

        return result;

    }

    /**
     * Ejecuta la regla pasando los datos necesarios para su ejecucion.
     */
    public int executeRegla(HashMap<String, Object> aContext) throws Exception {

        int execResult = RESULT_NOK;

        if (validarRegla(aContext)) {
            execResult = RESULT_OK;
            LOGGER.info("Se ejecuto con exito la regla " + this.getClass().getName());
        } else {
            execResult = RESULT_NOK;
            LOGGER.info("Se ejecuto con errores la regla " + this.getClass().getName());
        }
        return execResult;
    }
}
