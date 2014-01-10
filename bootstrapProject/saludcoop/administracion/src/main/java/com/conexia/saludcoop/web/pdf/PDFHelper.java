package com.conexia.saludcoop.web.pdf;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class PDFHelper {

	private static final Font TITLE_FONT = new Font(Font.HELVETICA, 9f);
	private static final Color TITLE_BG_COLOR = new Color(120, 150, 180);

	public static final Font DEFAULT_FONT = new Font(Font.HELVETICA, 7f);
	
	public static final Font BOLD_FONT = new Font(Font.HELVETICA, 7f, Font.BOLD);

	public static final int DEFAULT_PADDING = 5;
	public static final float DEFAULT_WIDTH = 100f;

	private static Logger LOGGER = LoggerFactory.getLogger(PDFHelper.class);

	/**
	 * Crea una tabla que simula el cellspacing.
	 * 
	 * @param cols Lista de proporciones de las columnas.
	 * @return PdfPTable
	 */
	public static PdfPTable createCellSpacingTable(float[] cols) {

		PdfPTable table = new PdfPTable(cols);
		table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
		table.getDefaultCell().setPadding(PDFHelper.DEFAULT_PADDING);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.getDefaultCell().setCellEvent(new PdfCellEventCellspacing());
		return table;
	}

	/**
	 * Crea una celda para título.
	 * 
	 * @param title Texto del título.
	 * @return PdfPCell
	 */
	public static PdfPCell createTitleCell(String title) {

		PdfPCell cell = new PdfPCell(new Paragraph(title, PDFHelper.TITLE_FONT));
		cell.setBackgroundColor(PDFHelper.TITLE_BG_COLOR);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderColor(Color.WHITE);

		return cell;
	}

	public static PdfPCell createCell(String content) {

		return createCell(content, true);

	}

	public static PdfPCell createCell(String content, boolean withBorder) {

		PdfPCell cell = new PdfPCell(new Paragraph(content, PDFHelper.DEFAULT_FONT));
		if (!withBorder) {
			cell.setBorderColor(Color.WHITE);
		}

		return cell;

	}

	/**
	 * Crea una celda con una imagen o con un texto alternativo.
	 * 
	 * @param imgPath Ruta a la imagen.
	 * @param altText Texto alternativo si no encuentra la imagen.
	 * @param fitToSize Bandera que indica si ajusta o no el tamaño de la imagen a la celda.
	 * @return PdfPCell
	 */
	public static PdfPCell createImageCell(URL imgPath, String altText, boolean fitToSize) {

		// TODO : ver de dónde se obtiene el archivo; se neceista o el path completo o la URL de la imagen.

		PDFHelper.LOGGER.debug("Se intenta crear una imagen con el archivo " + imgPath);

		PdfPCell cell = null;
		try {
			cell = new PdfPCell(Image.getInstance(imgPath), fitToSize);
		} catch (BadElementException e) {
			PDFHelper.LOGGER.error("No se pudo obtener la imagen", e);
		} catch (MalformedURLException e) {
			PDFHelper.LOGGER.error("No se pudo obtener la imagen por malformación de URL", e);
		} catch (IOException e) {
			PDFHelper.LOGGER.error("No se pudo obtener la imagen por error de E/S", e);
		}

		if (null == cell) {
			PDFHelper.LOGGER.debug("No se pudo obtener la imagen; se usa el texto alternativo: " + altText);
			cell = new PdfPCell(new Paragraph(altText, PDFHelper.DEFAULT_FONT));
		}

		cell.setBorderColor(Color.WHITE);

		return cell;
	}

}