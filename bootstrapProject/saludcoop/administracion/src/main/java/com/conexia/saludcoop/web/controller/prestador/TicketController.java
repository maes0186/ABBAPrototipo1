package com.conexia.saludcoop.web.controller.prestador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conexia.repository.access.StoredFile;
import com.conexia.repository.exception.FileRepositoryException;
import com.conexia.saludcoop.common.dto.ticket.TicketCabeceraDto;
import com.conexia.saludcoop.common.entity.transaccional.Entrega;
import com.conexia.saludcoop.common.io.repository.FileRepositoryProvider;
import com.conexia.saludcoop.util.ValidatedResponse;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.manager.AutorizacionManager;
import com.conexia.saludcoop.web.manager.EntregaManager;
import com.conexia.saludcoop.web.manager.SolicitudItemManager;
import com.conexia.saludcoop.web.manager.TicketManager;
import com.conexia.saludcoop.web.pdf.TicketGenerator;
import com.conexia.saludcoop.web.vo.CheckEntregasVO;
import com.conexia.saludcoop.web.vo.EntregaVO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

@Controller
@RequestMapping(value="ticket")
public class TicketController extends BaseValidatingController {

	private static Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

	/**
	 * Proveedor de archivos del repositorio.
	 */
	@Autowired
	private FileRepositoryProvider fileRepositoryProvider;
	@Autowired
	private TicketManager ticketManager;
	@Autowired
	private AutorizacionManager autorizacionManager;
	@Autowired
	private SolicitudItemManager solItemManager;
	@Autowired 
	private EntregaManager entregasManager;
	
	@RequestMapping(value = "/obtenerPDF/{idAutorizacion}/{nroEntrega}", method = RequestMethod.GET)
	protected void descargarPDF(@PathVariable("idAutorizacion") Long id, @PathVariable("nroEntrega") Integer entrega, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		TicketCabeceraDto ticket = ticketManager.getTicketByAutorizacionIdNroEntrega(id, entrega);
		
		Document documento = new Document(PageSize.A4);
		
		if (null != documento) {
			try {
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(documento, baos);
				
				TicketGenerator.crearPdf(documento, request, ticket, ticket.getFechaImpresion()!=null);
				byte[] retrieveFile = baos.toByteArray();
				try {
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition", "attachment; filename=\"Ticket "+ticket.getNumeroAutorizacion()+"-"+ticket.getNumeroDeEntrega()+" ("+(ticket.getFechaImpresion()!=null?"COPIA":"ORIGINAL")+").pdf\"");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setContentLength(retrieveFile.length);

					final InputStream is = new ByteArrayInputStream(retrieveFile);
					IOUtils.copy(is, response.getOutputStream());
					response.flushBuffer();
					
					ticketManager.updateDatosImpresion(ticket);
				} catch (final IOException e) {
					LOGGER.error("Error de E/S.", e);
				}
			}catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	@RequestMapping(value = "/printTicket/{nroItem}/{entrega}", method = RequestMethod.GET)
	protected void printTicket(@PathVariable("nroItem") Long nroItem,  @PathVariable("entrega") Integer entrega, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws MalformedURLException {
	    try {
                
    		TicketCabeceraDto ticket = solItemManager.getTicketAutorizacionItemSolicitud(nroItem,entrega);		
    		Document documento = new Document(PageSize.A4);
    		
    		if (null != documento) {
    			try {
    				
    				ByteArrayOutputStream baos = new ByteArrayOutputStream();
    				PdfWriter writer = PdfWriter.getInstance(documento, baos);
    				
    				TicketGenerator.crearPdf(documento, request, ticket, ticket.getFechaImpresion()!=null);
    				byte[] retrieveFile = baos.toByteArray();
    				try {
    					response.setContentType("application/octet-stream");
    					response.setHeader("Content-Disposition", "attachment; filename=\"Ticket "+ticket.getNumeroAutorizacion()+"-"+ticket.getNumeroDeEntrega()+" ("+(ticket.getFechaImpresion()!=null?"COPIA":"ORIGINAL")+").pdf\"");
    					response.setHeader("Pragma", "no-cache");
    					response.setHeader("Cache-Control", "no-cache");
    					response.setContentLength(retrieveFile.length);
    
    					final InputStream is = new ByteArrayInputStream(retrieveFile);
    					IOUtils.copy(is, response.getOutputStream());
    					response.flushBuffer();
    					
    					ticketManager.updateDatosImpresion(ticket);
    				} catch (final IOException e) {
    					LOGGER.error("Error de E/S.", e);
    				}
    			} catch (DocumentException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    		}

        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	@RequestMapping(value = "/checkEntregas/{nroItem}", method = RequestMethod.GET)
	@ResponseBody
	protected ValidatedResponse<CheckEntregasVO> checkEntregas(@PathVariable("nroItem") Long nroItem,  ModelMap model, HttpServletRequest request, HttpServletResponse response) throws MalformedURLException {

		ValidatedResponse<CheckEntregasVO> respuesta = new ValidatedResponse<>();
		
		List<Entrega> entregas = entregasManager.checkEntregas(nroItem);
		if (CollectionUtils.isEmpty(entregas) || entregas.size() == 1){
			 respuesta.setContent(new CheckEntregasVO(1,null));
		}else{
			List<EntregaVO> entregasVO = new ArrayList<>(entregas.size());
			for (Entrega entrega : entregas){
				entregasVO.add(new EntregaVO(entrega.getNumero(), entrega.getCantidadEntrega(), new LocalDate(entrega.getFechaInicioVigencia().getTime()).toString("dd/MM/yyyy"), new LocalDate(entrega.getFechaFinVigencia().getTime()).toString("dd/MM/yyyy")));
			}
			respuesta.setContent(new CheckEntregasVO(entregas.size(), entregasVO));
		}
		
		return respuesta;
		
	}
	
}
