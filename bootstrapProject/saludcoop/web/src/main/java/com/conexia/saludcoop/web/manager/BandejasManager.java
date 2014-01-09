package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.repository.PropertiesRepository;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.web.form.FiltroBandejasForm;
import com.conexia.saludcoop.web.vo.BandejaItemVO;
import com.conexia.saludcoop.web.vo.BandejaSubItemVO;
import com.conexia.saludcoop.web.vo.PageVO;

/**
 * @author mcuervo
 * 
 */
@Service
@Transactional
public class BandejasManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterUtil.class);  
    
	@Autowired
	private SolicitudItemRepository sir;
	
	@Autowired
	private PropertiesRepository pm;

	public BandejasManager() {
		
	}
	
	public PageVO<BandejaItemVO> getBandeja(FiltroBandejasForm form){
		
//	    try {
//	        Integer maxItems = Integer.valueOf(pm.findOneByClaveAndAplicacion("bandeja_max_items_pagina", "web").getValor());
//	        
//	        Page<SolicitudItem> itemsBandeja = sir.findBandeja(form.getNumeroSolicitud(), form.getTipoDocumento(), form.getNumeroDocumento(), form.getEps(), form.getRegional(), form.getEstadoSolicitud(), form.getTipoSolicitud(), form.getIpsId(), form.getProfesionalId(), new PageRequest(form.getActualPage(),maxItems));
//
//	        List<BandejaItemVO>  bandeja = VOUtils.toBandeja(itemsBandeja.getContent());
//
//	        PageVO<BandejaItemVO> paginaBandeja = new PageVO<>(bandeja,itemsBandeja.getTotalPages(), itemsBandeja.getTotalElements(), itemsBandeja.getNumber());
//            return paginaBandeja;
//        } catch (Exception e) {
//            LOGGER.error("Error al consultar las solicutudes para la bandeja", e);
//
//            PageVO<BandejaItemVO> paginaBandeja = new PageVO<>(new ArrayList<BandejaItemVO>(), 0, 0, 0);
//            return paginaBandeja;
//        }
	    List<BandejaItemVO> retorno = new ArrayList<>();
	    
        BandejaItemVO sol1 = new BandejaItemVO();
        sol1.setNombreAfiliado("CC 33640354 Juan Carlos2");
        sol1.setEps("SaludCoop");
        sol1.setFechaCreacionSolicitud("10/10/2013");
        sol1.setNumeroSolicitud("112313");
        sol1.setProfesionalSolicitante("Juan Perez");
        sol1.setRegional("Costa");
        sol1.setFechaModificacionSolicitud(null);
        sol1.setNumeroIdentificacionAfiliado("4637826473");
        sol1.setPrograma("Programa 1");
        sol1.setTipoIdentificacionAfiliado("Cedula");
        
        List<BandejaSubItemVO> items = new ArrayList<BandejaSubItemVO>();
        BandejaSubItemVO item1 = new BandejaSubItemVO();
        item1.setDiagnostico("Enfermedad General");
        item1.setEstado("Autorizada");
        item1.setFechaModificacion("10/10/2013");
        item1.setNumero("1123131");
        item1.setServicioSolicitado("Buscapina");
        item1.setTecnologia("Medicamento");
        item1.setTipoSolicitud("POS");
        items.add(item1);
        sol1.setSubitems(items);
        retorno.add(sol1);
	    
        PageVO<BandejaItemVO> paginaBandeja = new PageVO<>(retorno, 1, 1, 1);        
        return paginaBandeja;
	}

}
