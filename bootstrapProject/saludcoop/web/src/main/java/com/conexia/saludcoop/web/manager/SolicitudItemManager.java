package com.conexia.saludcoop.web.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.FormatoNegacionServiciosDto;
import com.conexia.saludcoop.common.dto.ticket.TicketCabeceraDto;
import com.conexia.saludcoop.common.entity.ticket.TicketCabecera;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.repository.FormatoNegacionDao;
import com.conexia.saludcoop.common.repository.SolicitudItemDao;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.repository.TicketCabeceraRepository;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.web.vo.BandejasVO;
import com.conexia.saludcoop.web.vo.VOUtils;

@Service
@Transactional
public class SolicitudItemManager {

    private static Logger LOGGER = LoggerFactory.getLogger(SolicitudItemManager.class);

    @Autowired
    private SolicitudItemDao sd;

    @Autowired
    private SolicitudItemRepository ir;
    
    @Autowired
    private TicketCabeceraRepository ticketRepo;
    
    @Autowired
    private FormatoNegacionDao fnd;

    public SolicitudItemManager() {

    }

    /**
     * Consulta el item correspondiente al id enviado por parámetro y lo mapea a los VOs
     * necesarios para realizar la gestión del item en pantalla
     * @param nro
     *          Identificador del item que se desea consultar
     * @param tipoAuditor
     *          Indica el tipo de auditor para el que se realizará el mapeo, se pueden usar las constantes:
     *          {@link VOUtils#BANDEJA_AC}  {@link VOUtils#BANDEJA_CTC} y {@link VOUtils#BANDEJA_IPS_MEDICO}
     * @return
     *          VO que contiene los VOs correspondientes a la bandeja que desea gestionar el item consultado
     */
    public BandejasVO getInfoItem(Long nro, int tipoAuditor) {
        return getInfoItem(nro, tipoAuditor, null);
    }
    
    /**
     * Consulta el item correspondiente al id enviado por parámetro y lo mapea a los VOs
     * necesarios para realizar la gestión del item en pantalla
     * @param nro
     *          Identificador del item que se desea consultar
     * @param tipoAuditor
     *          Indica el tipo de auditor para el que se realizará el mapeo, se pueden usar las constantes:
     *          {@link VOUtils#BANDEJA_AC}  {@link VOUtils#BANDEJA_CTC} y {@link VOUtils#BANDEJA_IPS_MEDICO}
     * @param tipoBandeja
     *          Indica el tipo de bandeja para el que se realizará el mapeo, se pueden usar las constantes:
     *          {@link SystemConstants#BANDEJA_NACIONAL} y {@link SystemConstants#BANDEJA_REGIONAL}
     * @return
     *          VO que contiene los VOs correspondientes a la bandeja que desea gestionar el item consultado
     */
    public BandejasVO getInfoItem(Long nro, int tipoAuditor, String tipoBandeja) {
        SolicitudItem item = sd.getSolicitudItemByNumero(nro);
        
        return VOUtils.toGestionBandejas(item, tipoAuditor, tipoBandeja);
    }

	public TicketCabeceraDto getTicketAutorizacionItemSolicitud(Long nroItem, Integer numeroDeEntrega) {
		
		SolicitudItem item =  ir.findOne(nroItem);
		
		if(item != null && item.getAutorizacion() != null && item.getAutorizacion().getNumeroAutorizacion() != null){
		    TicketCabecera ticket = ticketRepo.findOneByAutorizacionNumeroAutorizacionAndNumeroDeEntrega(
                    ir.findOne(nroItem).getAutorizacion().getNumeroAutorizacion(), 
                    numeroDeEntrega);
		    if(ticket != null){
		        return ticket.toDto();
		    }
		    return null;
		}		
		return null;
	}
	
	/**
	 * obtiene la informacion para llenar el formato de negacion de servicios
	 * @param nroItem numero del item asociado al formato
	 * @return dto con la informacion del formato
	 */
	public FormatoNegacionServiciosDto getFormatoNegacionServicios(Long nroItem){
	    return fnd.getDatosFormato(nroItem);
	}

}
