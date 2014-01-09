package com.conexia.saludcoop.web.controller.bandejas;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.conexia.repository.access.StoredFile;
import com.conexia.repository.exception.FileRepositoryException;
import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;
import com.conexia.saludcoop.common.io.repository.FileRepositoryProvider;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.manager.TipoDocumentoSoporteManager;
import com.conexia.saludcoop.web.vo.FileMeta;

/**
 * Componente usado para realizar la carga de los documentos adicionales de soporte en las diferentes bandejas
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 30/10/2013
 * 
 */
@Controller
@SessionAttributes({ "archivos" })
@RequestMapping(value = "/docSoporte/")
public class DocSoporteController {

    private static Logger LOGGER = LoggerFactory.getLogger(DocSoporteController.class);

    /**
     * Proveedor de archivos del repositorio.
     */
    @Autowired
    private FileRepositoryProvider fileRepositoryProvider;

    @Autowired
    private TipoDocumentoSoporteManager tipoDocSoporteManager;

    /**
     * Sube un archivo de soporte al repositorio de archivos y lo cuarda en el mapa del modelo para tomar los documentos correspondientes al
     * autorizar la solicitud.
     * 
     * @param request
     * @param response
     * @param map
     * @param tipoArchivo
     *            Corresponde a la clave con la que se subirán los documentos al mapa del modelo
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/upload/{tipoArchivo}", method = RequestMethod.POST)
    protected void uploadOtros(MultipartHttpServletRequest request, HttpServletResponse response, ModelMap map,
            @PathVariable("tipoArchivo") String tipoArchivo) {

        HashMap<String, List<DocumentoSoporteDto>> archivos = (HashMap<String, List<DocumentoSoporteDto>>) map.get("archivos");

        if (archivos == null) {
            archivos = new HashMap<>();
            map.put("archivos", archivos);
        }

        LinkedList<FileMeta> files = new LinkedList<FileMeta>();
        FileMeta fileMeta = null;

        // 1. build an iterator
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf = null;

        // 2. get each file
        while (itr.hasNext()) {

            // 2.1 get next MultipartFile
            mpf = request.getFile(itr.next());
            LOGGER.debug(mpf.getOriginalFilename() + " uploaded! " + files.size());

            // 2.2 if files > 10 remove the first from the list
            if (files.size() >= 10)
                files.pop();

            // 2.3 create new fileMeta
            fileMeta = new FileMeta();
            fileMeta.setFileName(mpf.getOriginalFilename());
            fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
            fileMeta.setFileType(mpf.getContentType());

            /* Se almacena el archivo en el repositorio */
            try {
                final StoredFile archivo = fileRepositoryProvider.getInstance().storeFile(mpf.getBytes());
                DocumentoSoporteDto doc = new DocumentoSoporteDto();

                doc.setTipoDocSoporte(tipoDocSoporteManager.getByCodigo(SystemConstants.DOC_OTROS));
                doc.setNombreArchivoServidor(archivo.getFilename());
                doc.setNombreArchivoOriginal(mpf.getOriginalFilename());

                if (archivos.containsKey(tipoArchivo)) {
                    archivos.get(tipoArchivo).add(doc);
                } else {
                    archivos.put(tipoArchivo, new ArrayList<DocumentoSoporteDto>());
                    archivos.get(tipoArchivo).add(doc);
                }

            } catch (final IOException e) {
                LOGGER.error("Error al obtener el contenido a almancenar", e);
                throw new RuntimeException("Error al obtener contenido a almacenar: " + e.getMessage());
            } catch (final FileRepositoryException e) {
                LOGGER.error("Error al almacenar el archivo", e);
                /* TODO Se debe manejar este error para presentarlo en pantalla */
                throw new RuntimeException("Error al almacenar archivo: " + e.getMessage());
            }

            files.add(fileMeta);
        }

    }

    /**
     * Elimina un archivo del mapa del modelo para que este no se asocie a la solicitud al guardarla
     * 
     * @param fileName
     *            Nombre del archivo que se desea eliminar
     * @param tipoArchivo
     *            Clave a la que se encuentran asociados los archivos en el mapa
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/removeFile/{tipoArchivo}/{fileName}", method = RequestMethod.POST)
    protected void removeFile(@PathVariable("fileName") String fileName, @PathVariable("tipoArchivo") String tipoArchivo, ModelMap map) {

        HashMap<String, List<DocumentoSoporteDto>> archivos = (HashMap<String, List<DocumentoSoporteDto>>) map.get("archivos");

        if (archivos != null) {
            if (archivos.containsKey(tipoArchivo)) {
                List<DocumentoSoporteDto> docs = archivos.get(tipoArchivo);
                for (int i = 0; i < docs.size(); i++) {
                    if (fileName != null && fileName.equals(docs.get(i).getNombreArchivoOriginal())) {
                        try {
                            fileRepositoryProvider.getInstance().deleteFile(docs.get(i).getNombreArchivoServidor());
                            docs.remove(i);
                        } catch (Exception e) {
                            LOGGER.error("Error al eliminar el archivo", e);
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * Permite realizar la descarga de un archivo desde el servidor
     * 
     * @param response
     * @param nombreArchivoServidor
     * @param nombreArchivoOriginal
     */
    @RequestMapping(value = "/download/{nombreArchivoOriginal}/{nombreArchivoServidor}")
    protected void donwloadFile(HttpServletResponse response, @PathVariable("nombreArchivoServidor") String nombreArchivoServidor,
            @PathVariable("nombreArchivoOriginal") String nombreArchivoOriginal) {

        byte[] retrieveFile;
        try {
            retrieveFile = fileRepositoryProvider.getInstance().retrieveFile(new StoredFile(nombreArchivoServidor));
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivoOriginal + "\"");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setContentLength(retrieveFile.length);

                final InputStream is = new ByteArrayInputStream(retrieveFile);
                IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            } catch (final IOException e) {
                LOGGER.error("Error al realizar la descarga del archivo", e);
            }
        } catch (FileNotFoundException | FileRepositoryException e) {
            LOGGER.error("Error al realizar la descarga del archivo", e);
        }

    }

    /**
     * Verifica si el archivo se encuentra disponible en el servidor para su descarga
     * 
     * @param response
     * @param nombreArchivoServidor
     */
    @ResponseBody
    @RequestMapping(value = "/check/{nombreArchivoServidor}")
    protected ValidatedResponse<Boolean> checkFile(HttpServletResponse response, @PathVariable("nombreArchivoServidor") String nombreArchivoServidor) {

        ValidatedResponse<Boolean> res = new ValidatedResponse<>();
        try {
            fileRepositoryProvider.getInstance().retrieveFile(new StoredFile(nombreArchivoServidor));
            res.setContent(Boolean.TRUE);
        } catch (FileNotFoundException | FileRepositoryException e) {
            LOGGER.error("Error al realizar la descarga del archivo", e);
            res.setContent(Boolean.FALSE);
            res.addGeneralError(I18NUtils.getInstance().getText("message.fileNotFound"));
        }
        return res;
    }
}
