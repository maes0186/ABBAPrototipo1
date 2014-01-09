package com.conexia.saludcoop.web.controller.prestador;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.SeachCodigoDescripcionForm;
import com.conexia.saludcoop.web.form.SolicitudForm;
import com.conexia.saludcoop.web.manager.AfiliadoManager;
import com.conexia.saludcoop.web.manager.CausaExternaManager;
import com.conexia.saludcoop.web.manager.EspecialidadManager;
import com.conexia.saludcoop.web.manager.FinalidadManager;
import com.conexia.saludcoop.web.manager.LateralidadManager;
import com.conexia.saludcoop.web.manager.ObjetivoManager;
import com.conexia.saludcoop.web.manager.OrigenRepeticionManager;
import com.conexia.saludcoop.web.manager.RespuestaClinicaObservadaManager;
import com.conexia.saludcoop.web.manager.TipoCatastroficoManager;
import com.conexia.saludcoop.web.manager.TipoPrestacionManager;
import com.conexia.saludcoop.web.manager.ViaAdministracionManager;
import com.conexia.saludcoop.web.vo.FileMeta;
//import com.conexia.saludcoop.manager.ManagerDescriptivo;

@Controller
@SessionAttributes({ "afiliado", "archivos","epicrisis" })
public class SolicitudController extends BaseValidatingController {
	public static final String SOLICITUD = "solicitud";
	
	@Autowired
	private AfiliadoManager aManager;
	@Autowired
	private EspecialidadManager eManager;
	@Autowired
	private LateralidadManager lateralidadManager;
	@Autowired
	private ViaAdministracionManager viaAdministracionManager;
	@Autowired
	private CausaExternaManager causaExternaManager;
	@Autowired
	private FinalidadManager finalidadManager;
	@Autowired	
	private TipoCatastroficoManager tipoCatastroficoManager;
	@Autowired	
	private RespuestaClinicaObservadaManager respClinicaObservadaManager;
	@Autowired
	private ObjetivoManager objetivoManager;
	@Autowired
	private TipoPrestacionManager tipoPrestacionManager;
	@Autowired
	private OrigenRepeticionManager origenRepeticionManager;
	
	@RequestMapping(value = "/prestador/solicitud", method = RequestMethod.GET)
	protected String iniciarComprobacion(SeachCodigoDescripcionForm form, ModelMap map) {

		map.put("especialidades", eManager.getEspecialidades());
		
		cargarElementosDescriptivo("viasAdministracion", map, viaAdministracionManager.getAll());
		cargarElementosDescriptivo("lateralidades", map, lateralidadManager.getAll());
		cargarElementosDescriptivo("causasExternas", map, causaExternaManager.getAll());
		cargarElementosDescriptivo("finalidades", map, finalidadManager.getAll());
		cargarElementosDescriptivo("tiposCatastroficos", map, tipoCatastroficoManager.getAll());
		cargarElementosDescriptivo("respClinicaObservadas", map, respClinicaObservadaManager.getAll());
		cargarElementosDescriptivo("objetivos", map, objetivoManager.getAll());
		cargarElementosDescriptivo("tiposPrestacion", map, tipoPrestacionManager.getAll());
		cargarElementosDescriptivo("origenesRepeticion", map, origenRepeticionManager.getAll());
		map.put("archivos", new HashMap<String,String>());
		return "prestador/common/solicitud";
	}

	@RequestMapping(value = "/prestador/crearSolicitud", method = RequestMethod.POST)
	protected String crearSolicitud(@ModelAttribute SolicitudForm form,
			ModelMap map) {


		return "common/ticket";
	}

	@RequestMapping(value = "/prestador/upload/otros", method = RequestMethod.POST)
	protected void uploadOtros(MultipartHttpServletRequest request, HttpServletResponse response, ModelMap map ){
		
		HashMap<String, String> archivos = (HashMap<String, String>)map.get("archivos");
		
	    LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	    FileMeta fileMeta = null;

        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
             System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
 
             //2.2 if files > 10 remove the first from the list
             if(files.size() >= 10)
                 files.pop();
 
             //2.3 create new fileMeta
             fileMeta = new FileMeta();
             fileMeta.setFileName(mpf.getOriginalFilename());
             fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
             fileMeta.setFileType(mpf.getContentType());
 
             try {
                fileMeta.setBytes(mpf.getBytes());
 
                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)      
                String url = "C:/temp/files/"+mpf.getOriginalFilename();
                archivos.put(mpf.getOriginalFilename(), url);
                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(System.getProperty("user.dir") + "/" +  mpf.getOriginalFilename()));
 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             files.add(fileMeta);
         }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]

	}
	@RequestMapping(value = "/prestador/upload/epicrisis", method = RequestMethod.POST)
	protected void uploadEpicrisis(MultipartHttpServletRequest request, HttpServletResponse response, ModelMap map ){
		
				
	    LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	    FileMeta fileMeta = null;

        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
             System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
 
             //2.2 if files > 10 remove the first from the list
             if(files.size() >= 10)
                 files.pop();
 
             //2.3 create new fileMeta
             fileMeta = new FileMeta();
             fileMeta.setFileName(mpf.getOriginalFilename());
             fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
             fileMeta.setFileType(mpf.getContentType());
 
             try {
                fileMeta.setBytes(mpf.getBytes());
 
                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)      
                String url = "C:/temp/files/"+mpf.getOriginalFilename();
                map.put("epicrisis", url);
                 FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(System.getProperty("user.dir") + "/" +  mpf.getOriginalFilename()));
 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             files.add(fileMeta);
         }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]

	}
	
	private String saveFile(String name){
		
		String url = "";
		
		
		
		return url;
	}
	
}
