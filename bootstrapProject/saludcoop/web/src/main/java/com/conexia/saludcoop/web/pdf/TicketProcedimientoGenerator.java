package com.conexia.saludcoop.web.pdf;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.conexia.saludcoop.common.dto.ticket.TicketCabeceraDto;
import com.conexia.saludcoop.common.dto.ticket.TicketItemDto;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class TicketProcedimientoGenerator {

	public static void crearPDF(Document document, TicketCabeceraDto ticket, boolean esReimpresion, URL urlCabecera) throws DocumentException, IOException {

		TicketProcedimientoGenerator.addEncabezado(document, ticket, esReimpresion, urlCabecera);
		TicketProcedimientoGenerator.addDatosSolicitud(document, ticket);
		TicketProcedimientoGenerator.addPracticas(document, ticket.getItems());
		TicketProcedimientoGenerator.addTipoPago(document, ticket);
		TicketProcedimientoGenerator.addPie(document, ticket);

	}

	private static void addEncabezado(Document document, TicketCabeceraDto ticket, boolean esReimpresion, URL urlCabecera) throws DocumentException, IOException {

		PdfPTable table = new PdfPTable(new float[] { 8, 2, 2 });
		table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		table.getDefaultCell().setPadding(PDFHelper.DEFAULT_PADDING);

		table.addCell(PDFHelper.createTitleCell("AUTORIZACIÓN DE SERVICIOS N° "	+ ticket.getNumeroAutorizacion()));

		table.addCell(PDFHelper.createImageCell(urlCabecera, ticket.getEps(), true));

		PdfPCell cell = PDFHelper.createCell(esReimpresion ? "COPIA" : "ORIGINAL", false);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		document.add(table);

		document.add(new Paragraph(" "));

	}

	public static void addDatosSolicitud(Document document, TicketCabeceraDto ticket) throws DocumentException, IOException {

		PdfPTable table = PDFHelper.createCellSpacingTable(new float[] { 2, 1, 1, 1, 1 });
		PdfPCell cell;

		// Fila 1 --- //

		table.addCell(new Paragraph("NOMBRE DEL PACIENTE", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("TIPO AFILIADO", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("TIPO IDENTIFICACION", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("IDENTIFICACION", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("EDAD", PDFHelper.BOLD_FONT));

		cell = new PdfPCell(table.getDefaultCell());

		cell.setPhrase(new Phrase(ticket.getNombresDelPaciente(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getTipoAfiliado(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getTipoDeIdentificacion(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getNumeroIdentificacion(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getEdad(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		document.add(table);

		// Fila 2 --- //

		table = PDFHelper.createCellSpacingTable(new float[] { 1, 2, 5 });

		table.addCell(new Paragraph("NIVEL", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("COBERTURA", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("IPS PRIMARIA", PDFHelper.BOLD_FONT));
		// table.addCell(new Paragraph("ENTIDAD RECOBRO",
		// PDFHelper.DEFAULT_FONT));

		cell.setPhrase(new Phrase(ticket.getNivel(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getPlanAfiliado(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getIpsPrimaria(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		/*
		 * cell.setPhrase(new Phrase(procedimiento.getEntidadRecobro(),
		 * PDFHelper.DEFAULT_FONT)); table.addCell(cell);
		 */

		document.add(table);

		// Fila 3 --- //

		table = PDFHelper.createCellSpacingTable(new float[] { 2, 1 });

		table.addCell(new Paragraph("IPS QUE SOLICITA", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("USUARIO QUE TRANSCRIBE", PDFHelper.BOLD_FONT));

		cell.setPhrase(new Phrase(ticket.getEntidadSolicitante(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getUsuarioTranscriptor(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		document.add(table);

		document.add(new Paragraph(" "));

		table = PDFHelper.createCellSpacingTable(new float[] { 2, 3, 2, 2, 2, 2 });

		table.addCell(new Paragraph("CAUSA EXTERNA", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph(ticket.getCausaExterna(), PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("DX PRINCIPAL", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph(ticket.getDiagnosticoPrincipal(), PDFHelper.DEFAULT_FONT));
		table.addCell(new Paragraph("DX SECUNDARIO", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph(ticket.getDiagnosticosSecundarios(), PDFHelper.DEFAULT_FONT));

		document.add(table);

	}

	public static void addPracticas(Document document, Set<TicketItemDto> practicas) throws DocumentException, IOException {

		PdfPTable table = new PdfPTable(new float[] { 1 });
		table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		table.getDefaultCell().setPadding(PDFHelper.DEFAULT_PADDING);

		PdfPCell cell = PDFHelper.createTitleCell("PROCEDIMIENTO O INTERVENCION A REALIZAR");
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		document.add(table);

		table = PDFHelper.createCellSpacingTable(new float[] { 0.8f, 3, 0.6f, 2, 1, 4 });

		table.addCell(new Paragraph("Código", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("Procedimiento(s)", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("Cant.", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("Finalidad", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("Lateralidad", PDFHelper.BOLD_FONT));
		table.addCell(new Paragraph("Observaciones", PDFHelper.BOLD_FONT));

		for (TicketItemDto practica : practicas) {
			table.addCell(new Paragraph(practica.getCodigo(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(practica.getDescripcion(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(practica.getCantidad(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(practica.getFinalidad(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(practica.getLateralidad(), PDFHelper.DEFAULT_FONT));
			table.addCell(new Paragraph(practica.getObservaciones(), PDFHelper.DEFAULT_FONT));

			// table.completeRow();
		}

		document.add(table);
		document.add(new Paragraph(" "));

	}

	private static void addTipoPago(Document document, TicketCabeceraDto ticket) throws DocumentException, IOException {


		// -------------------- //

		PdfPTable table = new PdfPTable(new float[] { 1 });
		table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		table.getDefaultCell().setPadding(PDFHelper.DEFAULT_PADDING);

		PdfPCell cell = PDFHelper.createTitleCell("TIPO PAGO A REALIZAR");
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell);
		document.add(table);

		// -------------------- //

		PdfPTable outerTable = new PdfPTable(new float[] { 1, 1, 1, 1 });
		outerTable.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		outerTable.getDefaultCell().setPadding(0);
		outerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		PdfPTable innerTable = PDFHelper.createCellSpacingTable(new float[] { 1, 1 });

		cell = new PdfPCell(innerTable.getDefaultCell());
		cell.setColspan(2);
		cell.setPhrase(new Phrase("PAGO COMPARTIDO", PDFHelper.BOLD_FONT));
		innerTable.addCell(cell);

		cell.setColspan(1);
		cell.setPhrase(new Phrase("EPS (%)", PDFHelper.BOLD_FONT));
		innerTable.addCell(cell);

		cell.setPhrase(new Phrase("USUARIO (%)", PDFHelper.BOLD_FONT));
		innerTable.addCell(cell);

		cell = new PdfPCell(outerTable.getDefaultCell());
		cell.addElement(innerTable);
		cell.setColspan(2);
		outerTable.addCell(cell);

		innerTable = PDFHelper.createCellSpacingTable(new float[] { 1, 1 });

		cell = new PdfPCell(innerTable.getDefaultCell());
		cell.setColspan(2);
		cell.setPhrase(new Phrase("COPAGO", PDFHelper.BOLD_FONT));
		innerTable.addCell(cell);
		cell.setColspan(1);
		cell.setPhrase(new Phrase("PORCENTAJE", PDFHelper.BOLD_FONT));
		innerTable.addCell(cell);

		cell.setPhrase(new Phrase("VALOR", PDFHelper.BOLD_FONT));
		innerTable.addCell(cell);

		cell = new PdfPCell(outerTable.getDefaultCell());
		cell.addElement(innerTable);
		outerTable.addCell(cell);

		cell = new PdfPCell(innerTable.getDefaultCell());
		cell.setPhrase(new Phrase("MODERADORA", PDFHelper.BOLD_FONT));
		outerTable.addCell(cell);

		document.add(outerTable);

		table = PDFHelper.createCellSpacingTable(new float[] { 2, 2, 1, 1, 2 });
		cell = new PdfPCell(table.getDefaultCell());

		cell.setPhrase(new Phrase(formatToCurrency(ticket.getPagoCompartidoEps()), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(formatToCurrency(ticket.getPagoCompartidoUsuario()), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getCopagoPorcentaje(), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(formatToCurrency(ticket.getCopagoValor()), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		cell.setPhrase(new Phrase(formatToCurrency(ticket.getCuotaModeradora()), PDFHelper.DEFAULT_FONT));
		table.addCell(cell);

		document.add(table);

		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));


	}

	private static String formatToCurrency(String valor) {
		if(!StringUtils.isEmpty(valor)){
			Double d = Double.valueOf(valor);
			return NumberFormat.getNumberInstance().format(d);
		}else{
			return "";
		}
	}

	private static void addPie(Document document, TicketCabeceraDto ticket) throws DocumentException, IOException {

		PdfPTable table = new PdfPTable(new float[] { 1 });
		table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		table.getDefaultCell().setPadding(PDFHelper.DEFAULT_PADDING);

		PdfPCell cell = PDFHelper.createTitleCell("PRESTADO EL SERVICIO LE AGRADECEMOS ENVIARNOS LA RESPECTIVA CUENTA DE COBRO ADJUNTANDO LA PRESENTE AUTORIZACION");
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell);
		document.add(table);

		// -------------------- //

		PdfPTable outerTable = new PdfPTable(new float[] { 5, 7 });
		outerTable.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		outerTable.getDefaultCell().setPadding(0);
		outerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		PdfPTable innerTable = new PdfPTable(new float[] { 3, 2 });
		innerTable.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		innerTable.getDefaultCell().setPadding(0);
		innerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		cell = new PdfPCell(innerTable.getDefaultCell());
		Paragraph paragraph = null;
		if (!ticket.getTipoPpm().equals("POS")) {
			
			cell.addElement(new Paragraph("FIRMA"));// Image.getInstance(ticket.getFirmaMedico()));
			paragraph = new Paragraph("__________________________________________________", PDFHelper.DEFAULT_FONT);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(paragraph);
			cell.setColspan(2);
			innerTable.addCell(cell);
		}
			cell = new PdfPCell(innerTable.getDefaultCell());
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			paragraph = new Paragraph(ticket.getNombreCompletoMedico(), PDFHelper.DEFAULT_FONT);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(paragraph);
			paragraph = new Paragraph("Profesional", PDFHelper.DEFAULT_FONT);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(paragraph);
			innerTable.addCell(cell);

			cell = new PdfPCell(innerTable.getDefaultCell());
			paragraph = new Paragraph(ticket.getRegistroMedico(), PDFHelper.DEFAULT_FONT);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(paragraph);
			paragraph = new Paragraph("Registro Profesional", PDFHelper.DEFAULT_FONT);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(paragraph);
			innerTable.addCell(cell);

			cell = new PdfPCell(outerTable.getDefaultCell());
			cell.addElement(innerTable);
			
		
		outerTable.addCell(cell);
		innerTable = PDFHelper.createCellSpacingTable(new float[] { 1, 5 });

		cell = new PdfPCell(innerTable.getDefaultCell());
		cell.setPhrase(new Phrase("INSTITUCIÓN A LA QUE SE REMITE", PDFHelper.DEFAULT_FONT));
		cell.setColspan(2);
		innerTable.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getInstitucionRemitidaNombre(), PDFHelper.DEFAULT_FONT));
		innerTable.addCell(cell);

		cell.setPhrase(new Phrase("Dirección", PDFHelper.DEFAULT_FONT));
		cell.setColspan(1);
		innerTable.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getInstitucionRemitidaDireccion(), PDFHelper.DEFAULT_FONT));
		innerTable.addCell(cell);

		cell.setPhrase(new Phrase("Teléfono", PDFHelper.DEFAULT_FONT));
		innerTable.addCell(cell);

		cell.setPhrase(new Phrase(ticket.getInstitucionRemitidaTelefono(), PDFHelper.DEFAULT_FONT));
		innerTable.addCell(cell);

		cell = new PdfPCell(outerTable.getDefaultCell());
		cell.addElement(innerTable);
		outerTable.addCell(cell);

		document.add(outerTable);

		document.add(new Paragraph(" "));

		// -------------------- //

		table = new PdfPTable(new float[] { 1 });
		table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		table.getDefaultCell().setPadding(PDFHelper.DEFAULT_PADDING);

		cell = PDFHelper.createTitleCell("IMPORTANTE: AUTORIZACIÓN VÁLIDA SOLAMENTE EN LOS/AS " + ticket.getVigencia() + " SIGUIENTES A SU EXPEDICIÓN");
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell);
		document.add(table);

	}

}