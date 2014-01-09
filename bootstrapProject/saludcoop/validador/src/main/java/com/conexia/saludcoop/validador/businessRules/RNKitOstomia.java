package com.conexia.saludcoop.validador.businessRules;

import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.conexia.rules.Rule;

import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDiagnosticoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.validador.constantes.ConstantesContexto;

@Component
@Rule(description = "Valida si el afiliado es apto para obtener un kit de ostomia.")
public class RNKitOstomia  extends RNProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(RNKitOstomia.class);
        
    private static final String [] diagnosticos = {"C182","C184","C186", "C187", "C188",
        "C189", "D010", "D011", "D012", "C19", "C218", "C785"};
    
    /*El caracter "X" en el codigo CIE 10 significa que puede ser cualquier digito entre 0 y 9. */
    private static final String [] diagnosticosX = {"C19X", "C20X"};
    
    private static final String COD_PINZA = "29861.0";
    
    private static final String COD_PASTA = "3719.0";
    
    private static final String [] bolsas = {"1018.0","3706.0","1019.0","1025.0", "1026.0","1027.0","42599.0"};
    
    private static final String [] barreras = {"1036.0", "1037.0","1038.0","1039.0","1040.0","3702.0",
        "3703.0", "3704.0", "3707.0", "30801.0", "42600.0"};
         
    
    private boolean validarRegla(HashMap<String, Object> aContext) throws Exception {
        SolicitudDto solDto = (SolicitudDto) aContext.get(ConstantesContexto.SOLICITUD);
        
        try{
            int numPinzas = 0;
            int numPastas = 0;
            int numBolsas = 0;
            int numBarreras = 0;

            boolean tieneDiagnostico = validarEsOstomia(solDto); 
            
            for (SolicitudItemDto item : solDto.getSolicitudItems()) {
                if (item.isInsumo()) {
                    
                    String codigo =  item.getSolInsumo().getInsumo().getCodigo();
                    
                    if(codigo.equals(COD_PINZA)){
                        if(tieneDiagnostico){
                            numPinzas++;
                        } else {
                            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.pacienteNoOstomia"), "Paciente no apto,no puede recibir kit de ostomia");
                            return false;
                        }
                        
                    } else if (codigo.equals(COD_PASTA)){
                        if(tieneDiagnostico){
                            numPastas++;
                        } else {
                            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.pacienteNoOstomia"), "Paciente no apto,no puede recibir kit de ostomia");
                            return false;
                        }
                    } else if (Arrays.asList(bolsas).contains(codigo)){
                        if(tieneDiagnostico){
                            numBolsas++;
                        } else {
                            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.pacienteNoOstomia"), "Paciente no apto,no puede recibir kit de ostomia");
                            return false;
                        }
                    } else if (Arrays.asList(barreras).contains(codigo)){
                        if(tieneDiagnostico){
                            numBarreras++;
                        } else {
                            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", I18NUtils.getInstance().getText("respuestaTrx.pacienteNoOstomia"), "Paciente no apto,no puede recibir kit de ostomia");
                            return false;
                        }
                    }
                }
            }
            
            if(numPinzas > 1 || numPastas > 1 || numBolsas > 1 || numBarreras > 1){
                
                generarRespuesta(aContext, "respuestaTrx.codigoNoOk",I18NUtils.getInstance().getText("respuestaTrx.kitOstomia"), "Kit de ostomia mal equipado");
                return false;
            }
            
            if((numPinzas == 0 && numPastas == 0 && numBolsas == 0 && numBarreras == 0)||
                    (numPinzas == 1 && numPastas == 1 && numBolsas == 1 && numBarreras == 1)){
                //kit completo o no pidio kit 
                return true;
            } else {
                //kit incompleto
                generarRespuesta(aContext, "respuestaTrx.codigoNoOk",I18NUtils.getInstance().getText("respuestaTrx.kitOstomia"), "Kit de ostomia incompleto");                
                return false;
            }
            
            
        } catch (Exception e) {
            generarRespuesta(aContext, "respuestaTrx.codigoNoOk", "Error al validar el kit de ostomia", "Error al procesar la transacción");
            return false;
        }
    }
    
    /**
     * se validan los casos en que un afiliado es apto para el kit de ostomia
     */
    private boolean validarEsOstomia(SolicitudDto solDto) {

         // validar diagnosticos de la solicitud
        for (SolicitudDiagnosticoDto diagnostico : solDto.getSolDiagnosticos()) {
            String codigo = diagnostico.getDiagnostico().getCodigo();

            if(Arrays.asList(diagnosticos).contains(codigo)){
                return true;
            }
            
            for (int i = 0; i < diagnosticosX.length; i++) {
                if(codigo.indexOf(diagnosticosX[i]) != -1){
                    return true;
                }
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
