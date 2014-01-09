package com.conexia.saludcoop.web.pdf;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conexia.saludcoop.common.dto.ticket.TicketCabeceraDto;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;

public class TicketGenerator {

	private final static String PREFIJO_NOMBRE_ARCHIVO = "Autorizacion";
	private static Logger LOGGER = LoggerFactory.getLogger(TicketGenerator.class);

	/**
	 * Crea un documento PDF por cada procedimiento o medicamento.
	 * 
	 * @param fileRepositoryProvider Componente para guardar los archivos en el repositorio.
	 * @param ticket Contenedor de los tados a procesar.
	 * @param cortarAlPrimerError Bandera que indica si se siguen generando los documentos si hubo un fallo.
	 * @return
	 */
	public static void crearPdf(Document document,
									HttpServletRequest request, 
									TicketCabeceraDto ticket, 
									boolean esReimpresion) {

		TicketGenerator.LOGGER.debug("Comienzo proceso de generación de PDFs");
		TicketGenerator.LOGGER.debug("Se ingresó la bandera esReimpresion " + esReimpresion);
		
		if (ticket != null && !CollectionUtils.isEmpty(ticket.getItems())) {
			
			try {
				
				URL urlCabecera = null;
				
				if (ticket.getEps().equals("Cruz Blanca EPS")){
					urlCabecera = new URL("http://localhost:"+request.getServerPort()+"/"+request.getContextPath()+"/resources/images/CruzBlanca-logo.png");
				}else if(ticket.getEps().equals("Cafesalud EPS Contributivo") || ticket.getEps().equals("Cafesalud EPS Subsidiado")){
					urlCabecera = new URL("http://localhost:"+request.getServerPort()+"/"+request.getContextPath()+"/resources/images/Cafesalud-logo.png");
				}else {
					urlCabecera = new URL("http://localhost:"+request.getServerPort()+"/"+request.getContextPath()+"/resources/images/SaludCoopEps-logo.png");
				}
				
				document.open();			
				
				if (ticket.getEsProcedimiento()){
					TicketProcedimientoGenerator.crearPDF(document, ticket, esReimpresion, urlCabecera);
				}else if(ticket.getEsMedicamento()){
					TicketMedicamentoGenerator.crearPDF(document, ticket, esReimpresion, urlCabecera);
				}else if (ticket.getEsInsumo()){
					TicketInsumoGenerator.crearPDF(document, ticket, esReimpresion, urlCabecera);
				}
				
				document.close();
			} catch (DocumentException e) {
				TicketGenerator.LOGGER.error("Error procesando el documento.", e);
			} catch (IOException e) {
				TicketGenerator.LOGGER.error("Error agregando imagen al documento.", e);
			}
			
		}
	}

}