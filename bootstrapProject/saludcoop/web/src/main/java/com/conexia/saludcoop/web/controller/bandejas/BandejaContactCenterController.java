package com.conexia.saludcoop.web.controller.bandejas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.manager.EpsManager;
import com.conexia.saludcoop.web.manager.EstadoAutorizacionManager;
import com.conexia.saludcoop.web.manager.RegionalManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.TipoPPMManager;
import com.conexia.saludcoop.web.vo.BandejasVO;
import com.conexia.saludcoop.web.vo.RegionalVO;
import com.conexia.saludcoop.web.vo.VOUtils;

@Controller
@SessionAttributes({ "archivos", "idSolicitudItem" })
@RequestMapping(value = "/contactCenter/")
public class BandejaContactCenterController extends BaseValidatingController {

	@Autowired
	private TipoIdentificacionManager tipoIdentAfiliadoManager;

	@Autowired
	private EpsManager epsManager;

	@Autowired
	private RegionalManager regionalManager;

	@Autowired
	private EstadoAutorizacionManager estadoManager;

	@Autowired
	private TipoPPMManager tipoPPMManager;

	@Autowired
	private SolicitudItemManager im;
	
	@RequestMapping(value = "detalleItem/{itemId}")
	protected String getDetalleAutorizacion(ModelMap map, @PathVariable("itemId") Long id) {
		
        BandejasVO vo = im.getInfoItem(id, VOUtils.BANDEJA_CONTACT_CENTER);
        
        map.put("infoGeneral", vo.getInfoGeneralVO());
        map.put("bandejaCC", vo.getBandejaIpsPrestadorVO());
        map.put("regionales", ConverterUtil.tranformObjects(regionalManager.getAll(), RegionalVO.class));
        cargarElementosDescriptivo("estadosAutorizacion", map, estadoManager.getAll());
        return "auditor/gestionarItemContactCenter";
	}
	
}
