package com.conexia.saludcoop.web.controller.prestador;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conexia.saludcoop.common.crud.ProfesionalItemVO;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudParcialDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.security.SaludCoopUserDetails;
import com.conexia.saludcoop.util.ValidatedPageResponse;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.FiltroBandejasForm;
import com.conexia.saludcoop.web.manager.SolicitudParcialManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.manager.UsuarioManager;
import com.conexia.saludcoop.web.vo.BandejaAutorizacionItemProjVO;
import com.conexia.saludcoop.web.vo.PageVO;
import com.conexia.saludcoop.web.vo.SolicitudParcialVO;

@Controller
public class BandejaSolicitudParcialController extends BaseValidatingController{
    private static Logger LOGGER = LoggerFactory.getLogger(BandejaSolicitudParcialController.class);
	@Autowired
	private SolicitudParcialManager spManager;
	@Autowired
	private TipoIdentificacionManager tipoIdentificacionManager;

    @Autowired
    private UsuarioManager userManager;
    
    @Autowired
    private SolicitudParcialManager solicitudParcialManager;
	
	@RequestMapping(value="ldf/bandejaSolicitudParcial", method = RequestMethod.GET)
	public String init(ModelMap map){
		
		cargarElementosTipoIdentificacion("tiposDeDocumento", map, tipoIdentificacionManager.getAll());
		
		return "ldf/bandejaSP";
	}
	
	@RequestMapping(value = "/prestador/cargarSolicitudParcial", method = RequestMethod.GET)
    @ResponseBody
    public ValidatedPageResponse<List<SolicitudParcialVO>> getAutorizaciones(FiltroBandejasForm filter, ModelMap map) {
	    ValidatedPageResponse<List<SolicitudParcialVO>> validatedResponse = new ValidatedPageResponse<>();
	    SaludCoopUserDetails userDetails = (SaludCoopUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userManager.loadUserByUsername(userDetails.getUsername());
        try {
            
            PageVO<SolicitudParcialVO> paginaBandeja = solicitudParcialManager.getSolicitudesParciales(filter, user);

            String nombreBandeja = (String) map.get("nombreBandeja");
            if (paginaBandeja.getContent() != null && paginaBandeja.getContent().size() > 0) {
                validatedResponse.setContent(paginaBandeja.getContent());
                validatedResponse.setTotalPages(paginaBandeja.getTotalPages());
                validatedResponse.setActualPage(paginaBandeja.getPaginaActual());
                validatedResponse.setTotalItems(paginaBandeja.getTotalElements());
            } else {
                validatedResponse.addGeneralError("No existen registros para mostrar");
            }
            
           
            return validatedResponse;
        } catch (final Exception e) {
            LOGGER.error("Error buscando las Solicitudes Parciales", e);
            validatedResponse.addGeneralError("Error buscando las autorizaciones");
            return validatedResponse;
        }
	    
	            
        
    
        
	}
}
