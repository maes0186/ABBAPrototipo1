package com.conexia.saludcoop.web.controller.crud;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.conexia.saludcoop.common.crud.ProfesionalItemVO;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.util.ValidatedPageResponse;
import com.conexia.saludcoop.web.crud.util.CrudUtil;
import com.conexia.saludcoop.web.form.FiltroCrudForm;
import com.conexia.saludcoop.web.manager.ProfesionalManager;
import com.conexia.saludcoop.web.manager.TipoIdentificacionManager;
import com.conexia.saludcoop.web.vo.PageVO;
@Controller
@RequestMapping(value = "/crud/")
@SessionAttributes({ "crudFilter", "nombreCrud" })
public class CrudController {
	@Autowired
	TipoIdentificacionManager tipoIdentificacionManager;
	
	private static Logger LOGGER = LoggerFactory.getLogger(CrudController.class);
	
	@Autowired 
	ProfesionalManager profesionalManager;
	
	@RequestMapping(value = "{crud}")
    protected String getBandeja(ModelMap map, @PathVariable("crud") String bandeja) {

        String nombreBandejaAnterior = (String) map.get("nombreCrud");

        if (bandeja != null && !bandeja.equals(nombreBandejaAnterior)) {
            map.put("crudFilter", new FiltroCrudForm());
        }
        if(SystemConstants.PROFESIONAL_CRUD.equals(bandeja)){
        	CrudUtil.cargarElementosTipoIdentificacion("tiposDeDocumento", map, tipoIdentificacionManager.getAll());
        }
        map.put("nombreCrud", bandeja);
        return "crud/cruds";
    }
	
	  /**
     * Para Editar Elemento
     * @param map
     * @return
     */
    @RequestMapping(value = "editarItem/{itemId}")
    protected String editar(@PathVariable("itemId") Long id, ModelMap map) {
        String crud = (String) map.get("nombreCrud");
        String crudRedireccion=null;
        if (SystemConstants.PROFESIONAL_CRUD.equals(crud) ) {
        	crudRedireccion = "redirect:/profesionalCrud/editarItem/" + id;
        } 
        return crudRedireccion;
    }
    
    /**
     * Para Crear Elemento
     * @param map
     * @return
     */
    @RequestMapping(value = "crearItem")
    protected String crear(ModelMap map) {
        String crud = (String) map.get("nombreCrud");
        String crudRedireccion=null;
        if (SystemConstants.PROFESIONAL_CRUD.equals(crud) ) {
        	crudRedireccion = "redirect:/profesionalCrud/crearItem";
        } 
        return crudRedireccion;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "cargarProfesionales", method = RequestMethod.GET)
    public ValidatedPageResponse<List<ProfesionalItemVO>> getSolicitudes(FiltroCrudForm filter, ModelMap map) {
        ValidatedPageResponse<List<ProfesionalItemVO>> response = new ValidatedPageResponse<>();

        try {

            PageVO<ProfesionalItemVO> bandeja = profesionalManager.getProfesionalesProj(filter);

            if (bandeja.getTotalElements() <= 0) {
                response.addGeneralError("No existen registros para mostrar");
            } else {
                response.setContent(bandeja.getContent());
                response.setTotalPages(bandeja.getTotalPages());
                response.setActualPage(bandeja.getPaginaActual());
            }

            map.put("crudFilter", filter);

        } catch (Exception e) {
            LOGGER.error("Error al consultar las solicitudes", e);
            response.addGeneralError("No existen registros para mostrar");
        }
        return response;

    }
    
    

}
