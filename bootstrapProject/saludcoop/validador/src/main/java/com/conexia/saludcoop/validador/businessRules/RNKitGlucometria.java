package com.conexia.saludcoop.validador.businessRules;

import java.util.HashMap;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDiagnosticoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.AfiliadoPrograma;
import com.conexia.saludcoop.common.entity.transaccional.Solicitud;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;
import com.conexia.saludcoop.common.repository.SolicitudDao;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Valida si el afiliado es apto para obtener un kit de glucometria.")
public class RNKitGlucometria extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RNKitGlucometria.class);

    private static final String PROGRAMA_DIABETES = "10";

    private static final String COD_DIAGNOSTICO_1 = "E10";

    private static final String COD_DIAGNOSTICO_2 = "E11";

    private static final String COD_DIAGNOSTICO_3 = "E12";

    private static final String COD_DIAGNOSTICO_4 = "E13";

    private static final String COD_DIAGNOSTICO_5 = "E14";

    private static final String COD_GLUCOMETRO = "43212.0";// glucometro nuevo

    private static final String COD_LANCETA_1 = "43214.0"; // lanceta nueva

    private static final String COD_LANCETA_2 = "3768.0";

    private static final String COD_TIRA_1 = "43213.0"; // tira nueva

    private static final String COD_TIRA_2 = "26949.0";

    @Autowired
    private AfiliadoRepository afiliadoRepository;
    
    @Autowired
    private SolicitudDao solDao;
    
    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {
        SolicitudDto solDto = (SolicitudDto) aContext.get(ConstantesContexto.SOLICITUD);
        HashMap<String, String> mensajesMap = new HashMap<>();
        aContext.put(ConstantesContexto.MSJS_ITEMS, mensajesMap);

        try {

            Afiliado afiliado = afiliadoRepository.findOne(solDto.getAfiliado().getId());
            Hibernate.initialize(afiliado.getProgramas());
            int numTiras1 = 0;
            int numLancetas1 = 0;
            int numTiras2 = 0;
            int numLancetas2 = 0;
            int numGlucometros = 0;

            boolean kitCompleto1 = false;
            boolean kitCompleto2 = false;
            boolean esDiabetico = validarEsDiabetico(afiliado, solDto);            
            
            for (SolicitudItemDto item : solDto.getSolicitudItems()) {
                if (item.isInsumo()) {

                    String codigo =  item.getSolInsumo().getInsumo().getCodigo();
                    
                    if (codigo.equals(COD_GLUCOMETRO)) {  
                        if (esDiabetico) {
                            numGlucometros++;
                        } else {
                            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.pacienteNoDiabetico"), "Paciente no diabetico, no puede recibir kit de glucometria");
                            return false;
                        }
                    } else if (codigo.equals(COD_LANCETA_1)) {  
                        if (esDiabetico) {
                            numLancetas1++;
                        } else {
                            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.pacienteNoDiabetico"), "Paciente no diabetico, no puede recibir kit de glucometria");
                            return false;
                        }
                    } else if (codigo.equals(COD_LANCETA_2)) { 
                        if (esDiabetico) {
                            numLancetas2++;
                        } else {
                            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.pacienteNoDiabetico"), "Paciente no diabetico, no puede recibir kit de glucometria");
                            return false;
                        }
                    } else if (codigo.equals(COD_TIRA_1)) { 
                        if (esDiabetico) {
                            numTiras1++;
                        } else {
                            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.pacienteNoDiabetico"), "Paciente no diabetico, no puede recibir kit de glucometria");
                            return false;
                        }
                    } else if (codigo.equals(COD_TIRA_2)) {  
                        if (esDiabetico) {
                            numTiras2++; 
                        } else {
                            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.pacienteNoDiabetico"), "Paciente no diabetico, no puede recibir kit de glucometria");
                            return false;
                        }
                    }
                }
            }
            

            if (numGlucometros > 1) {
                generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.maxGlucometro"),
                        "El kit debe tener maximo un glucometro");
                return false;
            }

            if (numLancetas1 > 1 || numLancetas2 > 1 || numTiras1 > 1 || numTiras2 > 1) {
                generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.maxLancetasTiras"),
                        "El kit debe tener una lanceta y unas tiras reactivas");
                return false;
            }

            if ((numLancetas1 == 1 && numLancetas2 == 1) || (numLancetas1 == 1 && numTiras2 == 1) || (numLancetas2 == 1 && numTiras1 == 1)
                    || (numTiras1 == 1 && numTiras2 == 1)) {

                // distinto kit
                generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.kitDistinto"),
                        "La lanceta y las tiras reactivas no son del mismo tipo de kit");
                return false;
            }
            

            if (numLancetas1 == 1 && numTiras1 == 1) {

                // validar lancetas-tiras usadas previamente
                Solicitud solLanceta1 = solDao.findLastSolicitudByInsumoAndAfiliado(afiliado.getId(), COD_LANCETA_1);
                Solicitud solLanceta2 = solDao.findLastSolicitudByInsumoAndAfiliado(afiliado.getId(), COD_LANCETA_2);
                
                if(solLanceta1 == null && solLanceta2 != null){
                    generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.kitNoCorresponde"),
                            "La lanceta y las tiras reactivas no son del mismo tipo de kit");
                    return false;
                }
                
                kitCompleto1 = true;

            } else if (numLancetas1 == 1 || numTiras1 == 1) {
                // kit incompleto
                generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.maxLancetasTiras"),
                        "Falta una lanceta o tiras reactivas");
                return false;
            }
            
            

            if (numLancetas2 == 1 && numTiras2 == 1) {

             // validar lancetas-tiras usadas previamente
                Solicitud solLanceta1 = solDao.findLastSolicitudByInsumoAndAfiliado(afiliado.getId(), COD_LANCETA_1);
                Solicitud solLanceta2 = solDao.findLastSolicitudByInsumoAndAfiliado(afiliado.getId(), COD_LANCETA_2);
                
                if(solLanceta2 == null && solLanceta1 != null){
                    generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.kitNoCorresponde"),
                            "La lanceta y las tiras reactivas no son del mismo tipo de kit");
                    return false;
                }
                
                kitCompleto2 = true;

            } else if (numLancetas2 == 1 || numTiras2 == 1) {
                // kit incompleto
                generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.maxLancetasTiras"),
                        "Falta una lanceta o tiras reactiva");
                return false;
            }
            
            

            if (numGlucometros == 1) {
                
                if(!kitCompleto1 && kitCompleto2){
                    // las tiras reactivas y lanceta no corresponden al glucometro que se esta entregando
                    generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.elementosKitNoCorresponde"),
                            "Las tiras reactivas y lanceta no corresponden al glucometro que se esta entregando");

                    return false;
                }
                
                if (!kitCompleto1 && !kitCompleto2) {
                    // kit incompleto
                    generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.maxLancetasTiras"),
                            "Falta una lanceta o tiras reactiva");

                    return false;

                }
            }

            return true;            
            

        } catch (Exception e) {
            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", "Error al validar el kit de glucometria", "Error al procesar la transacción");
            return false;
        }

    }

    /**
     * se validan los casos en que un afiliado es diabetico
     */
    private boolean validarEsDiabetico(Afiliado afiliado, SolicitudDto solDto) {

        // programa diabetes
        for (AfiliadoPrograma afiliadoPrograma : afiliado.getProgramas()) {
            if (afiliadoPrograma.getPrograma().getCodigo().equals(PROGRAMA_DIABETES)) {
                return true;
            }
        }

        // validar diagnosticos de la solicitud
        for (SolicitudDiagnosticoDto diagnostico : solDto.getSolDiagnosticos()) {
            String codigo = diagnostico.getDiagnostico().getCodigo();

            if (codigo.indexOf(COD_DIAGNOSTICO_1) != -1 || codigo.indexOf(COD_DIAGNOSTICO_2) != -1
                    || codigo.indexOf(COD_DIAGNOSTICO_3) != -1 || codigo.indexOf(COD_DIAGNOSTICO_4) != -1
                    || codigo.indexOf(COD_DIAGNOSTICO_5) != -1) {

                return true;
            }
        }

        return false;

    }
 
    /**
     * @param aContext
     */
    private void generarRespuesta(HashMap<String, Object> aContext, String codigoRta, String msjRespuesta, String msjLogger) {
        RespuestaDto rta = new RespuestaDto();
        Integer codRespuesta = new Integer(I18NUtils.getInstance().getText(codigoRta));
        rta.setCodigoRespuesta(codRespuesta);
        rta.setMensajeRespuesta(msjRespuesta);

        aContext.put(ConstantesContexto.RESPUESTA_TRX, rta);
        LOGGER.info(msjLogger);
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
