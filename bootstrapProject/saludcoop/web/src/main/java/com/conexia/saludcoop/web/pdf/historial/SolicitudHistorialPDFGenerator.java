package com.conexia.saludcoop.web.pdf.historial;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.util.DateUtilities;
import com.conexia.saludcoop.web.pdf.PDFHelper;
import com.conexia.saludcoop.web.vo.SolicitudHistorialVO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class SolicitudHistorialPDFGenerator {


	public static void crearPDF(Document document, AfiliadoDto afiliadoDto, List<SolicitudHistorialVO> historialList) throws DocumentException, IOException {

		SolicitudHistorialPDFGenerator.addEncabezado(document);
		SolicitudHistorialPDFGenerator.addDatosSolicitud(document, afiliadoDto);
		SolicitudHistorialPDFGenerator.addHistorico(document, historialList);

	}

	private static void addEncabezado(Document document) throws DocumentException,
	IOException {

		PdfPTable table = new PdfPTable(new float[] { 8 });
		table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		table.getDefaultCell().setPadding(PDFHelper.DEFAULT_PADDING);

		table.addCell(PDFHelper.createTitleCell("HISTORIAL DE SOLICITUDES"));

		document.add(table);

		document.add(new Paragraph(" "));
	}


	private static void addDatosSolicitud(Document document, AfiliadoDto afiliadoDto)
			throws DocumentException, IOException {

		PdfPTable table = PDFHelper.createCellSpacingTable(new float[] { 2, 1, 1, 1 });
		PdfPCell cell;

		// Fila 1 --- //

		table.addCell(new Paragraph("NOMBRE DEL PACIENTE", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("TIPO IDENTIFICACION", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("IDENTIFICACION", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("EDAD", PDFHelper.DEFAULT_FONT));

		cell = new PdfPCell(table.getDefaultCell());

		cell.setPhrase(new Phrase(afiliadoDto.getNombreCompleto(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(afiliadoDto.getTipoIdentificacion().getDescripcion(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(afiliadoDto.getNumeroIdentificacion(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		String edad = String.valueOf(DateUtilities.calcularEdad(afiliadoDto.getFechaNacimiento(), Calendar.getInstance().getTime()));
		cell.setPhrase(new Phrase(edad, PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		document.add(table);

		// Fila 2 --- //

		table = PDFHelper.createCellSpacingTable(new float[] { 3, 1, 1});

		table.addCell(new Paragraph("EPS", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("NIVEL", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("ESTADO AFILIACIÓN", PDFHelper.DEFAULT_FONT));

		cell.setPhrase(new Phrase(afiliadoDto.getEps().getRazonSocial(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase(afiliadoDto.getNivelIbc().getDescripcion(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase(afiliadoDto.getEstadoAfiliacion().getDescripcion(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		document.add(table);

		document.add(new Paragraph(" "));

	}

	
	public static void addHistorico(Document document, List<SolicitudHistorialVO> historialList) throws DocumentException, IOException {

		PdfPTable table = new PdfPTable(new float[] { 1 });
		table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		table.getDefaultCell().setPadding(PDFHelper.DEFAULT_PADDING);

		PdfPCell cell = PDFHelper.createTitleCell("SOLICITUDES");
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		document.add(table);
		
		table = PDFHelper.createCellSpacingTable(new float[] { 1.1f, 2, 1.4f, 4, 1, 1.2f, 1.3f, 1.3f, 1.3f, 0.8f });

		table.addCell(new Paragraph("Número", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("Regional", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("Fecha", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("Descripcion", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("Estado", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("Tipo de Solicitud", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("Codigo Producto", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("Unidades Aprobadas", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("Periodo Aprobado", PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("Dias", PDFHelper.DEFAULT_FONT));
		

		for (SolicitudHistorialVO historial : historialList) {

			table.addCell(new Paragraph(String.valueOf(historial.getNumeroSolicitud()), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(historial.getRegional(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(historial.getFecha(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(historial.getDescripcionProducto(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(historial.getEstado(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(historial.getTipoDeSolicitud(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(historial.getCodigoProducto(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(String.valueOf(historial.getUnidadesAprobadas()), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(String.valueOf(historial.getPeriodoAprobado()), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(String.valueOf(historial.getDiasPeriodo()), PDFHelper.DEFAULT_FONT));

		}
		
		document.add(table);

		document.add(new Paragraph(" "));

	}

}
