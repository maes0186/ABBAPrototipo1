package com.conexia.saludcoop.web.manager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.exception.ServiceException;
import com.conexia.saludcoop.common.repository.SolicitudHistorialVODao;
import com.conexia.saludcoop.common.util.Pagination;
import com.conexia.saludcoop.web.pdf.historial.SolicitudHistorialPDFGenerator;
import com.conexia.saludcoop.web.vo.PageVO;
import com.conexia.saludcoop.web.vo.SolicitudHistorialVO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

@Service
@Transactional
public class SolicitudHistorialManager {
	
	@Autowired
	private IAfiliadoManager aManager;
	
	@Autowired
	private SolicitudHistorialVODao historialVODao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(SolicitudHistorialManager.class);
	
	public SolicitudHistorialManager(){
		
	}
	
	/**
	 * Cuenta la cantidad de resultados existentes
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param numeroSolicitud
	 * @param estadoSolicitud
	 * @param regional
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	public Long countResults(Integer tipoDocumento,
			String numeroDocumento, 
			Long numeroSolicitud,
			Integer estadoSolicitud, 
			Integer regional,
			Date fechaDesde,
			Date fechaHasta)
	{
		
		return historialVODao.countHistorialSolicitudes(
				tipoDocumento,
				numeroDocumento,
				numeroSolicitud,
				estadoSolicitud,
				regional,
				fechaDesde,
				fechaHasta);
	}
	
	/**
	 * Lista el historial de atencion
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param numeroSolicitud
	 * @param estadoSolicitud
	 * @param regional
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param actualPage
	 * @return
	 */
	public PageVO<SolicitudHistorialVO> getHistorial(Integer tipoDocumento,
			String numeroDocumento, 
			Long numeroSolicitud,
			Integer estadoSolicitud, 
			Integer regional,
			Date fechaDesde,
			Date fechaHasta,
			Integer actualPage) {
		
		Pagination pagination = new Pagination(actualPage, Pagination.DEFAULT_PAGE_SIZE);
		
		List<SolicitudHistorialVO> historialList = historialVODao.getHistorialSolicitudes(
																			tipoDocumento,
																			numeroDocumento,
																			numeroSolicitud,
																			estadoSolicitud,
																			regional,
																			fechaDesde,
																			fechaHasta,
																			pagination);
		

		PageVO<SolicitudHistorialVO> paginaBandeja = new PageVO<>(historialList, pagination.getTotalPages(), historialList.size(), pagination.getActualPage());
        return paginaBandeja;
    }

	
	
	public byte[] buildHistorialPDF(
			Integer tipoDocumento,
			String numeroDocumento, 
			Long numeroSolicitud,
			Integer estadoSolicitud, 
			Integer regional, 
			Date fechaDesde,
			Date fechaHasta) {
		
		
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			
			
			AfiliadoDto afiliadoDto = aManager.getAfiliadoByTipoNumeroDocumento(tipoDocumento,numeroDocumento);
			
			//Se trae el historial completo de solicitudes
			List<SolicitudHistorialVO> historialList = historialVODao.getHistorialSolicitudes(
																				tipoDocumento,
																				numeroDocumento,
																				numeroSolicitud,
																				estadoSolicitud,
																				regional,
																				fechaDesde,
																				fechaHasta);
			
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);

			document.open();

			SolicitudHistorialPDFGenerator.crearPDF(document, afiliadoDto, historialList);

			document.close();

			return baos.toByteArray();


		} catch (DocumentException e) {
			SolicitudHistorialManager.LOGGER.error("Error procesando el documento.", e);
		} catch (IOException e) {
			SolicitudHistorialManager.LOGGER.error("Error agregando imagen al documento.", e);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
