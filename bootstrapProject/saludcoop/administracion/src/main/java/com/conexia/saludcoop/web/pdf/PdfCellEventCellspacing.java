package com.conexia.saludcoop.web.pdf;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;
import com.lowagie.text.pdf.PdfPTable;

public class PdfCellEventCellspacing implements PdfPCellEvent {

	/**
	 * Simula cellspacing
	 */
	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		float x1 = position.getLeft() + 1;
		float x2 = position.getRight() - 1;
		float y1 = position.getTop() - 1;
		float y2 = position.getBottom() + 1;
		PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
		canvas.rectangle(x1, y1, x2 - x1, y2 - y1);
		canvas.stroke();
	}

}