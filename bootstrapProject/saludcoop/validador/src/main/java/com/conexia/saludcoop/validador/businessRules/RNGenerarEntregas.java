package com.conexia.saludcoop.validador.businessRules;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.InfoEntregasDto;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.entity.maestro.AfiliadoPrograma;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.transaccional.Entrega;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.enumerator.TipoFrecuencia;
import com.conexia.saludcoop.common.repository.EntregaRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Genera las entregas para los medicamentos.")
public class RNGenerarEntregas extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RNGenerarEntregas.class);

    @Autowired
    SolicitudItemRepository solicitudItemRepository;

    @Autowired
    EntregaRepository entregaRepository;

    @SuppressWarnings("unchecked")
    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {
        boolean result = true;
        List<InfoEntregasDto> infoEntregas = (List<InfoEntregasDto>) aContext.get(ConstantesContexto.INFO_ENTREGAS);

        try {
            if (infoEntregas != null && infoEntregas.size() > 0) {
            	List<Entrega> entregas = new ArrayList<Entrega>();
                for (InfoEntregasDto infoEntrega : infoEntregas) {

                    SolicitudItem solItem = solicitudItemRepository.findOne(infoEntrega.getItemId());

                    if (EstadoAutorizacion.AUTORIZADO.equals(solItem.getAutorizacion().getEstadoAutorizacion().getId())) {
                        if (infoEntrega.isSolicitud()) {
                            Hibernate.initialize(solItem.getSolicitud().getAfiliado().getProgramas());

                            // Se verifica si el afiliado está asociado a algún programa de tratamientos crónicos
                            if (solItem.getSolicitud().getAfiliado().getProgramas() != null
                                    && solItem.getSolicitud().getAfiliado().getProgramas().size() > 0
                                    && medicamentoPertenecePrograma(solItem.getSolicitud().getAfiliado().getProgramas(), solItem
                                            .getSolMedicamento().getMedicamento())) {

                                Double frecuenciaD = 0d;
                                if (TipoFrecuencia.HORAS.equals(TipoFrecuencia.fromId(infoEntrega.getCada()))) {
                                    frecuenciaD = infoEntrega.getFrecuencia() / 24d;
                                } else {
                                    frecuenciaD = Double.valueOf(infoEntrega.getFrecuencia());
                                }

                                Integer cantidadMes = Double.valueOf(Math.round((30d / frecuenciaD) * infoEntrega.getDosis())).intValue();
                                Integer numEntregas = Double.valueOf(
                                        Math.ceil(infoEntrega.getUnidadesAprobadas() / Double.valueOf(cantidadMes))).intValue();

                                int cantidadTmp = infoEntrega.getUnidadesAprobadas();

                                Calendar c = Calendar.getInstance();
                                for (int i = 1; i <= numEntregas; i++) {
                                    Entrega entrega = new Entrega();
                                    entrega.setNumero(i);

                                    if (cantidadTmp > cantidadMes) {
                                        entrega.setCantidadEntrega(cantidadMes);
                                    } else {
                                        entrega.setCantidadEntrega(cantidadTmp);
                                    }

                                    cantidadTmp -= cantidadMes;

                                    entrega.setFechaInicioVigencia(c.getTime());

                                    // Se verifica si se trata de una entrega diferente de la primera para calcular la fecha de activación
                                    if (i == 1) {
                                        entrega.setFechaActivacion(c.getTime());
                                        c.add(Calendar.DAY_OF_YEAR, infoEntrega.getDiasPeriodo() - 1);
                                    } else {
                                        c.add(Calendar.DAY_OF_YEAR, -5);
                                        entrega.setFechaActivacion(c.getTime());
                                        c.add(Calendar.DAY_OF_YEAR, infoEntrega.getDiasPeriodo() + 4);
                                    }
                                    entrega.setFechaFinVigencia(c.getTime());
                                    c.add(Calendar.DAY_OF_YEAR, 1);
                                    entrega.setSolicitudMedicamento(solItem.getSolMedicamento());
                                    entregaRepository.save(entrega);
                                    entregas.add(entrega);
                                }
                            } else if (solItem.getAutorizacion().getRoleDestinatario() == null) {
                                Calendar c = Calendar.getInstance();
                                // Se verifica si se trata de
                                Entrega entrega = new Entrega();
                                entrega.setNumero(1);
                                entrega.setCantidadEntrega(infoEntrega.getUnidadesAprobadas());
                                entrega.setFechaInicioVigencia(c.getTime());

                                entrega.setFechaActivacion(c.getTime());

                                c.add(Calendar.DAY_OF_YEAR, infoEntrega.getDiasPeriodo() - 1);
                                entrega.setFechaFinVigencia(c.getTime());

                                entrega.setSolicitudMedicamento(solItem.getSolMedicamento());
                                entregaRepository.save(entrega);
                                entregas.add(entrega);
                            }
                        } else {
                            Calendar c = Calendar.getInstance();
                            for (int i = 1; i <= infoEntrega.getNumeroEntregas(); i++) {
                                Entrega entrega = new Entrega();
                                entrega.setNumero(i);
                                entrega.setCantidadEntrega(infoEntrega.getUnidadesAprobadas());
                                entrega.setFechaInicioVigencia(c.getTime());

                                // Se verifica si se trata de una entrega diferente de la primera para calcular la fecha de activación
                                if (i == 1) {
                                    entrega.setFechaActivacion(c.getTime());
                                    c.add(Calendar.DAY_OF_YEAR, infoEntrega.getDiasPeriodo() - 1);
                                } else {
                                    c.add(Calendar.DAY_OF_YEAR, -5);
                                    entrega.setFechaActivacion(c.getTime());
                                    c.add(Calendar.DAY_OF_YEAR, infoEntrega.getDiasPeriodo() + 4);
                                }
                                entrega.setFechaFinVigencia(c.getTime());
                                c.add(Calendar.DAY_OF_YEAR, 1);
                                entrega.setSolicitudMedicamento(solItem.getSolMedicamento());
                                entregaRepository.save(entrega);
                                entregas.add(entrega);
                            }
                        }
                    }
                }
                aContext.put(ConstantesContexto.ENTREGAS, entregas);
            }
        } catch (Exception e) {
            RespuestaDto rta = new RespuestaDto();
            Integer codRespuesta = new Integer(I18NUtils.getInstance().getText("respuestaTrx.codigoNoOk"));
            rta.setCodigoRespuesta(codRespuesta);
            rta.setMensajeRespuesta("Error al generar las entregas");

            aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
            LOGGER.info("Error al procesar la transacción", e);

            return false;
        }

        return result;
    }

    /**
     * Verifica si el medicamento enviado por parámetro pertence a la lista de programas del afiliado
     * 
     * @param programas
     * @param medicamento
     * @return
     */
    private boolean medicamentoPertenecePrograma(Set<AfiliadoPrograma> programas, Medicamento medicamento) {
        if (programas != null) {
            for (AfiliadoPrograma programa : programas) {
                if (programa.getPrograma().getMedicamentos() != null) {
                    for (Medicamento med : programa.getPrograma().getMedicamentos()) {
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
