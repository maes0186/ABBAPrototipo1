package com.conexia.saludcoop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conexia.saludcoop.common.dto.HistorialSMLDVDto;
import com.conexia.saludcoop.common.dto.HistorialVariacionIPCDto;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.HistorialEditarForm;
import com.conexia.saludcoop.web.form.HistorialForm;
import com.conexia.saludcoop.web.manager.HistorialIPCManager;
import com.conexia.saludcoop.web.manager.HistorialSMLDVManager;

/**
 * 
 * @author dprieto
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class ConfiguracionController extends BaseValidatingController {
    
    @Autowired
    private HistorialSMLDVManager hsm;
    
    @Autowired
    private HistorialIPCManager him;

    
    @RequestMapping(value = "/configurarSMLDV")
    protected String configurarParametroSMLDV(ModelMap map) {
        map.put("historialSMLDV", hsm.findAllOrderByAnio());
        return "admin/parametroSMLDV";
    }
    
    @RequestMapping(value = "/agregarSMLDV", method = RequestMethod.POST)
    protected @ResponseBody String agregarNuevoSMLDV(@ModelAttribute HistorialForm form, ModelMap map){ 
        if(hsm.findByAnio(form.getAnio())==null){
            hsm.addSMLDV(form.getValor(), form.getAnio());
            return "OK";
        } else {
            return "Ya existe";
        }        
    }
    
    @RequestMapping(value = "/eliminarSMLDV/{registroId}")
    protected String eliminarSMLDV(@PathVariable("registroId") Long id, ModelMap map){ 
        hsm.removeSMLDV(id);
        map.put("historialSMLDV", hsm.findAllOrderByAnio());
        return "admin/parametroSMLDV";
    }
    
    @RequestMapping(value = "/cargarSMLDVEditar/{registroId}", method = RequestMethod.POST)
    protected  @ResponseBody String cargarSMLDVEditar(@PathVariable("registroId") Long id, ModelMap map){ 
        HistorialSMLDVDto dto = hsm.findById(id);
        return "{\"id\": \""+dto.getId()+"\", \"valor\":\""+dto.getValor()+"\", \"anio\":\""+dto.getAnio()+"\"}";
    }
    
    @RequestMapping(value = "/editarSMLDV", method = RequestMethod.POST)
    protected @ResponseBody String editarSMLDV(@ModelAttribute HistorialEditarForm form, ModelMap map){ 
        HistorialSMLDVDto dto = hsm.findByAnio(form.getAnioEditar());
        if(dto==null){
            hsm.editSMLDV(form.getRegistroEditarId(), form.getValorEditar(), form.getAnioEditar());
            return "OK";
        } else if(dto.getId().equals(form.getRegistroEditarId())) {
            hsm.editSMLDV(form.getRegistroEditarId(), form.getValorEditar(), form.getAnioEditar());
            return "OK";
        } else {
            return "Ya existe";
        }        
    }
    
    
    @RequestMapping(value = "/configurarIPC")
    protected String configurarParametroIPC(ModelMap map) {
        map.put("historialIPC", him.findAllOrderByAnio());        
        return "admin/parametroIPC";
    }
    
    @RequestMapping(value = "/agregarIPC", method = RequestMethod.POST)
    protected @ResponseBody String agregarNuevoIPC(@ModelAttribute HistorialForm form, ModelMap map){ 
        if(him.findByAnio(form.getAnio())==null){
            him.addVariacionIPC(form.getValor(), form.getAnio());
            return "OK";
        } else {
            return "Ya existe";
        }        
    }
    
    @RequestMapping(value = "/eliminarIPC/{registroId}")
    protected String eliminarIPC(@PathVariable("registroId") Long id, ModelMap map){ 
        him.removeVariacionIPC(id);
        map.put("historialIPC", him.findAllOrderByAnio());
        return "admin/parametroIPC";
    }
    
    @RequestMapping(value = "/cargarIPCEditar/{registroId}", method = RequestMethod.POST)
    protected  @ResponseBody String cargarIPCEditar(@PathVariable("registroId") Long id, ModelMap map){ 
        HistorialVariacionIPCDto dto = him.findById(id);
        return "{\"id\": \""+dto.getId()+"\", \"valor\":\""+dto.getValor()+"\", \"anio\":\""+dto.getAnio()+"\"}";
    }
    
    @RequestMapping(value = "/editarIPC", method = RequestMethod.POST)
    protected @ResponseBody String editarIPC(@ModelAttribute HistorialEditarForm form, ModelMap map){ 
        HistorialVariacionIPCDto dto = him.findByAnio(form.getAnioEditar());
        if(dto==null){
            him.editVariacionIPC(form.getRegistroEditarId(), form.getValorEditar(), form.getAnioEditar());
            return "OK";
        } else if(dto.getId().equals(form.getRegistroEditarId())) {
            him.editVariacionIPC(form.getRegistroEditarId(), form.getValorEditar(), form.getAnioEditar());
            return "OK";
        } else {
            return "Ya existe";
        }        
    }
}
