package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.dto.BandejasDto;
import com.conexia.saludcoop.common.dto.EscalamientoDto;
import com.conexia.saludcoop.common.dto.RespuestaCompuesta;
import com.conexia.saludcoop.common.dto.RespuestaDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.GestionItemRedir_AnulaDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.dto.transaccional.TopTenSedeIpsDto;
import com.conexia.saludcoop.common.dto.transaccional.ValidarTopesCantidadDto;
import com.conexia.saludcoop.common.enumerator.TipoTransaccion;

@Service
@Transactional
public class ValidatorServiceManager {

    private static Logger LOGGER = LoggerFactory.getLogger(ValidatorServiceManager.class);

    @Autowired
    private PropertiesManager pManager;

    public RespuestaDto comprobarDerechos(AfiliadoDto afiliadoDto) {
        try {

            RespuestaDto rta = this.getRestClient().postForObject(getUrl(TipoTransaccion.COMPROBACION_DERECHOS), afiliadoDto,
                    RespuestaDto.class);

            return rta;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    public RespuestaCompuesta<List<AutorizacionDto>> solicitarAutorizacion(SolicitudDto sol) {
        try {

            RespuestaCompuesta<List<AutorizacionDto>> rta = this.getRestClient().postForObject(
                    getUrl(TipoTransaccion.SOLICITUD_AUTORIZACION), sol, RespuestaCompuesta.class);

            return rta;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 
     * @param sol
     * @return
     */
    @SuppressWarnings("unchecked")
    public RespuestaCompuesta<AutorizacionDto> calcularNivelAutorizacion(Long subItem) {
        try {
            RespuestaCompuesta<AutorizacionDto> rta = this.getRestClient().postForObject(getUrl(TipoTransaccion.OBTENER_AUTORIZACION),
                    subItem, RespuestaCompuesta.class);
            return rta;

        } catch (Exception e) {
            LOGGER.error("Error calculando nivel de autorizacion", e);
            throw e;
        }

    }

    /**
     * Metodo que extrae el TARGET al que debe escalarse la autorización indicando en el dto el tipo de Bandeja y el itemId
     * 
     * @param dto
     *      
     * @return
     */
    
    @SuppressWarnings("unchecked")
    public RespuestaCompuesta<RoleDTO> getRoleEscalamiento(EscalamientoDto dto){
        try {
            RespuestaCompuesta<RoleDTO> rta = this.getRestClient().postForObject(getUrl(TipoTransaccion.OBTENER_ROLE_ESCALAMIENTO),
                    dto, RespuestaCompuesta.class);
            return rta;

        } catch (Exception e) {
            LOGGER.error("Error obteniendo TARGET de destino", e);
            throw e;
        }
    }
    /**
     * Realiza el llamado a la operación en el validador encargada de realizar el proceso de autorización de la solicitud CTC
     * 
     * @param dto
     *            Información de la solicitud que se desea actualizar al momento de persistirla
     * @return Respuesta que indica si el proceso de autorización se pudo realizar correctamento o no
     */
    public RespuestaDto autorizarSolicitudCTC(BandejasDto dto) {
        try {
            RespuestaDto respuesta = this.getRestClient().postForObject(getUrl(TipoTransaccion.AUTORIZACION_CTC), dto, RespuestaDto.class);
            return respuesta;
        } catch (Exception e) {
            LOGGER.error("Error al autorizar la solicitud CTC", e);
            return null;
        }
    }

    /**
     * Realiza el llamado a la operación en el validador encargada de realizar el proceso de autorización de la solicitud Alto Costo
     * 
     * @param dto
     *            Información de la solicitud que se desea actualizar al momento de persistirla
     * @return Respuesta que indica si el proceso de autorización se pudo realizar correctamento o no
     */
    public RespuestaDto autorizarSolicitudAC(BandejasDto dto) {
        try {
            RespuestaDto respuesta = this.getRestClient().postForObject(getUrl(TipoTransaccion.AUTORIZACION_AC), dto, RespuestaDto.class);
            return respuesta;
        } catch (Exception e) {
            LOGGER.error("Error al autorizar la solicitud AC", e);
            return null;
        }
    }

    /**
     * Realiza el llamado a la operación en el validador encargada de realizar las operaciones sobre solicitudes para las que aplican tutela
     * 
     * @param dto
     *            Información de la solicitud que se desea actualizar al momento de persistirla
     * @return Respuesta que indica si el proceso se pudo realizar correctamento o no
     */
    public RespuestaDto gestionarItemTutela(BandejasDto dto) {
        try {
            RespuestaDto respuesta = this.getRestClient().postForObject(getUrl(TipoTransaccion.GESTIONAR_ITEM_TUTELA), dto,
                    RespuestaDto.class);
            return respuesta;
        } catch (Exception e) {
            LOGGER.error("Error al autorizar la solicitud con Tutela", e);
            return null;
        }
    }

    /**
     * Realiza el llamado a la operación en el validador encargada de realizar el proceso de autorización de la solicitud desde la bandeja
     * de contact service
     * 
     * @param dto
     *            Información de la solicitud que se desea actualizar al momento de persistirla
     * @return Respuesta que indica si el proceso de autorización se pudo realizar correctamento o no
     */
    public RespuestaDto autorizarSolicitudCS(BandejasDto dto) {
        try {
            RespuestaDto respuesta = this.getRestClient().postForObject(getUrl(TipoTransaccion.AUTORIZACION_CS), dto, RespuestaDto.class);
            return respuesta;
        } catch (Exception e) {
            LOGGER.error("Error al autorizar la solicitud desde la bandeja de Contact service", e);
            return null;
        }
    }

    /**
     * Realiza el llamado a la operación en el validador encargada de ejecutar las operaciones sobre solicitudes desde las bandejas de
     * auditor especializado
     * 
     * @param dto
     *            Información del item que se está procesando
     * @return Respuesta indicando si el proceso se realizó correctamento o no
     */
    public RespuestaDto autorizacionBandejaEspecializada(BandejasDto dto) {
        try {
            RespuestaDto respuesta = this.getRestClient().postForObject(getUrl(TipoTransaccion.AUTORIZACION_BANDEJA_ESPECIALIZADA), dto, RespuestaDto.class);
            return respuesta;
        } catch (Exception e) {
            LOGGER.error("Error al autorizar la solicitud desde la bandeja para auditores especializados", e);
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public RespuestaCompuesta<List<AutorizacionDto>> consumirSolicitudItem(SolicitudItemDto item) {
        try {

        	RespuestaCompuesta<List<AutorizacionDto>> rta = this.getRestClient()
                    .postForObject(getUrl(TipoTransaccion.CONSUMIR_SOLICITUD_ITEM), item, RespuestaCompuesta.class);

            return rta;

        } catch (Exception e) {
            LOGGER.error("Error al consumir el item", e);
            return null;
        }

    }

    public RespuestaDto devolverSolicitudItem(SolicitudItemDto item) {
        try {

            RespuestaDto rta = this.getRestClient()
                    .postForObject(getUrl(TipoTransaccion.DEVOLVER_SOLICITUD_ITEM), item, RespuestaDto.class);

            return rta;

        } catch (Exception e) {
            LOGGER.error("Error al devolver el item", e);
            return null;
        }

    }

    public RespuestaDto redireccionarItem(GestionItemRedir_AnulaDto item) {
        try {
            RespuestaDto rta = this.getRestClient().postForObject(getUrl(TipoTransaccion.REDIRECCION_IPS_ITEM), item, RespuestaDto.class);
            return rta;

        } catch (Exception e) {
            LOGGER.error("Error al devolver el item", e);
            return null;
        }

    }

    public RespuestaDto anularItem(GestionItemRedir_AnulaDto item) {
        try {
            RespuestaDto rta = this.getRestClient().postForObject(getUrl(TipoTransaccion.ANULAR_IPS_ITEM), item, RespuestaDto.class);
            return rta;

        } catch (Exception e) {
            LOGGER.error("Error al devolver el item", e);
            return null;
        }

    }

    public RespuestaDto actualizarDatosContactoAfiliado(AfiliadoDto afiliadoDto) {
        try {
            RespuestaDto rta = this.getRestClient().postForObject(getUrl(TipoTransaccion.ACTUALIZAR_DATOS_CONTACTO_AFILIADO), afiliadoDto,
                    RespuestaDto.class);
            return rta;
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el afiliado", e);
            return null;
        }
    }

    private String getUrl(TipoTransaccion transaction) {

        return pManager.getUrlValidador() + transaction.getDescription();
        
        // return "http://localhost:8081/Validador/"+ transaction.getDescription();
    }

    private RestTemplate getRestClient() {

        RestTemplate restTemplate = new RestTemplate();

        setTimeOut(restTemplate);

        HttpMessageConverter<?> conv = new MappingJacksonHttpMessageConverter();

        HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
        List<HttpMessageConverter<?>> convs = new ArrayList<HttpMessageConverter<?>>();
        convs.add(conv);
        convs.add(formHttpMessageConverter);
        restTemplate.setMessageConverters(convs);

        return restTemplate;

    }

    public static void setTimeOut(RestTemplate restTemplate) {
        if (restTemplate.getRequestFactory() instanceof SimpleClientHttpRequestFactory) {
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(10 * 1000);
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(10 * 10000);
        } else if (restTemplate.getRequestFactory() instanceof HttpComponentsClientHttpRequestFactory) {
            ((HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(10 * 1000);
            ((HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(10 * 10000);
        }
    }

    @SuppressWarnings("unchecked")
    public RespuestaCompuesta<Set<SedeIpsDto>> consultarTopTenSedeIps(TopTenSedeIpsDto tenSedeIpsDto) {
        try {
            RespuestaCompuesta<Set<SedeIpsDto>> rta = this.getRestClient().postForObject(
                    getUrl(TipoTransaccion.CONSULTAR_TOP_TEN_SEDES_IPS_REDIRECCIONAMIENTO), tenSedeIpsDto, RespuestaCompuesta.class);
            return rta;

        } catch (Exception e) {
            LOGGER.error("Error calculando consulta de IPS top Ten", e);
            throw e;
        }
    }
    
    public RespuestaDto excedioTope(ValidarTopesCantidadDto topesDto) {
        try {
            RespuestaDto rta = this.getRestClient().postForObject(getUrl(TipoTransaccion.VALIDAR_TOPE_CANTIDAD), topesDto,
                    RespuestaDto.class);
            return rta;
        } catch (Exception e) {
            LOGGER.error("Error al validar los topes del elemento dado", e);
            return null;
        }
    }
}
