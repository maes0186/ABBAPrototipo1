package com.conexia.saludcoop.web.xls;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class XLSGenerator {

	public static HSSFWorkbook generarXLSBandejaAC(List<BandejaXls> items) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Reporte Bandeja Alto Costo");
        
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell((short) 0).setCellValue(new HSSFRichTextString("No. Solicitud"));
        rowhead.createCell((short) 1).setCellValue(new HSSFRichTextString("No. Solicitud Item"));
        rowhead.createCell((short) 2).setCellValue(new HSSFRichTextString("Regional"));
        rowhead.createCell((short) 3).setCellValue(new HSSFRichTextString("Fecha"));
        rowhead.createCell((short) 4).setCellValue(new HSSFRichTextString("Tipo Documento"));
        rowhead.createCell((short) 5).setCellValue(new HSSFRichTextString("Documento"));
        rowhead.createCell((short) 6).setCellValue(new HSSFRichTextString("Usuario"));
        rowhead.createCell((short) 7).setCellValue(new HSSFRichTextString("Tecnologia"));
        rowhead.createCell((short) 8).setCellValue(new HSSFRichTextString("Diagnostico"));
        rowhead.createCell((short) 9).setCellValue(new HSSFRichTextString("Estado"));
        rowhead.createCell((short) 10).setCellValue(new HSSFRichTextString("Ambito"));
        rowhead.createCell((short) 11).setCellValue(new HSSFRichTextString("Origen"));
        rowhead.createCell((short) 12).setCellValue(new HSSFRichTextString("Entidad"));
        rowhead.createCell((short) 13).setCellValue(new HSSFRichTextString("Eps"));
        rowhead.createCell((short) 14).setCellValue(new HSSFRichTextString("Origino"));
        rowhead.createCell((short) 15).setCellValue(new HSSFRichTextString("Tipo CTC"));
        rowhead.createCell((short) 16).setCellValue(new HSSFRichTextString("Prioridad"));
        rowhead.createCell((short) 17).setCellValue(new HSSFRichTextString("Tipo Prioridad"));
        
        int index = 1;
        for (BandejaXls item : items){
            HSSFRow row = sheet.createRow((short) index);
            row.createCell((short) 0).setCellValue(new HSSFRichTextString(item.getSolId().toString()));
            row.createCell((short) 1).setCellValue(new HSSFRichTextString(item.getSolItemId().toString()));
            row.createCell((short) 2).setCellValue(new HSSFRichTextString(item.getRegional()));
            row.createCell((short) 3).setCellValue(new HSSFRichTextString(item.getFecha()));
            row.createCell((short) 4).setCellValue(new HSSFRichTextString(item.getTipoDocumento()));
            row.createCell((short) 5).setCellValue(new HSSFRichTextString(item.getDocumento()));
            row.createCell((short) 6).setCellValue(new HSSFRichTextString(item.getUsuario()));
            row.createCell((short) 7).setCellValue(new HSSFRichTextString(item.getTecnologia()));
            row.createCell((short) 8).setCellValue(new HSSFRichTextString(item.getDiagnostico()));
            row.createCell((short) 9).setCellValue(new HSSFRichTextString(item.getEstado()));
            row.createCell((short) 10).setCellValue(new HSSFRichTextString("Ambulatorio"));
            row.createCell((short) 11).setCellValue(new HSSFRichTextString(item.getOrigen()));
            row.createCell((short) 12).setCellValue(new HSSFRichTextString("Contratada"));
            row.createCell((short) 13).setCellValue(new HSSFRichTextString(item.getEps()));
            row.createCell((short) 14).setCellValue(new HSSFRichTextString(item.getOrigino()));
            row.createCell((short) 15).setCellValue(new HSSFRichTextString(item.getTipoCTC()));
            row.createCell((short) 16).setCellValue(new HSSFRichTextString("2"));
            row.createCell((short) 17).setCellValue(new HSSFRichTextString("Programada"));
            
            index++;	
        }
        
        return wb;
	}
	
	public static HSSFWorkbook generarXLSBandejaTutela(List<BandejaXls> items){
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Reporte Bandeja Tutelas");
        
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell((short) 0).setCellValue(new HSSFRichTextString("No. Solicitud"));
        rowhead.createCell((short) 1).setCellValue(new HSSFRichTextString("No. Solicitud Item"));
        rowhead.createCell((short) 2).setCellValue(new HSSFRichTextString("Regional"));
        rowhead.createCell((short) 3).setCellValue(new HSSFRichTextString("Fecha"));
        rowhead.createCell((short) 4).setCellValue(new HSSFRichTextString("Tipo Documento"));
        rowhead.createCell((short) 5).setCellValue(new HSSFRichTextString("Documento"));
        rowhead.createCell((short) 6).setCellValue(new HSSFRichTextString("Usuario"));
        rowhead.createCell((short) 7).setCellValue(new HSSFRichTextString("Tecnologia"));
        rowhead.createCell((short) 8).setCellValue(new HSSFRichTextString("Diagnostico"));
        rowhead.createCell((short) 9).setCellValue(new HSSFRichTextString("Estado"));
        rowhead.createCell((short) 10).setCellValue(new HSSFRichTextString("Ambito"));
        rowhead.createCell((short) 11).setCellValue(new HSSFRichTextString("Origen"));
        rowhead.createCell((short) 12).setCellValue(new HSSFRichTextString("Entidad"));
        rowhead.createCell((short) 13).setCellValue(new HSSFRichTextString("Eps"));
        rowhead.createCell((short) 14).setCellValue(new HSSFRichTextString("Origino"));
        rowhead.createCell((short) 15).setCellValue(new HSSFRichTextString("Tipo CTC"));
        rowhead.createCell((short) 16).setCellValue(new HSSFRichTextString("Prioridad"));
        rowhead.createCell((short) 17).setCellValue(new HSSFRichTextString("Tipo Prioridad"));
        
        int index = 1;
        for (BandejaXls item : items){
            HSSFRow row = sheet.createRow((short) index);
            row.createCell((short) 0).setCellValue(new HSSFRichTextString(item.getSolId().toString()));
            row.createCell((short) 1).setCellValue(new HSSFRichTextString(item.getSolItemId().toString()));
            row.createCell((short) 2).setCellValue(new HSSFRichTextString(item.getRegional()));
            row.createCell((short) 3).setCellValue(new HSSFRichTextString(item.getFecha()));
            row.createCell((short) 4).setCellValue(new HSSFRichTextString(item.getTipoDocumento()));
            row.createCell((short) 5).setCellValue(new HSSFRichTextString(item.getDocumento()));
            row.createCell((short) 6).setCellValue(new HSSFRichTextString(item.getUsuario()));
            row.createCell((short) 7).setCellValue(new HSSFRichTextString(item.getTecnologia()));
            row.createCell((short) 8).setCellValue(new HSSFRichTextString(item.getDiagnostico()));
            row.createCell((short) 9).setCellValue(new HSSFRichTextString(item.getEstado()));
            row.createCell((short) 10).setCellValue(new HSSFRichTextString("Ambulatorio"));
            row.createCell((short) 11).setCellValue(new HSSFRichTextString(item.getOrigen()));
            row.createCell((short) 12).setCellValue(new HSSFRichTextString("Contratada"));
            row.createCell((short) 13).setCellValue(new HSSFRichTextString(item.getEps()));
            row.createCell((short) 14).setCellValue(new HSSFRichTextString(item.getOrigino()));
            row.createCell((short) 15).setCellValue(new HSSFRichTextString(item.getTipoCTC()));
            row.createCell((short) 16).setCellValue(new HSSFRichTextString("2"));
            row.createCell((short) 17).setCellValue(new HSSFRichTextString("Programada"));
            
            index++;	
        }
        
        return wb;
	}

	public static HSSFWorkbook generarXLSBandejaCtc(List<BandejaXls> items){
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Reporte Bandeja CTC");

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index);

        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell((short) 0).setCellValue(new HSSFRichTextString("No. Solicitud"));
        rowhead.createCell((short) 1).setCellValue(new HSSFRichTextString("No. Solicitud Item"));
        rowhead.createCell((short) 2).setCellValue(new HSSFRichTextString("Regional"));
        rowhead.createCell((short) 3).setCellValue(new HSSFRichTextString("Fecha"));
        rowhead.createCell((short) 4).setCellValue(new HSSFRichTextString("Tipo Documento"));
        rowhead.createCell((short) 5).setCellValue(new HSSFRichTextString("Documento"));
        rowhead.createCell((short) 6).setCellValue(new HSSFRichTextString("Usuario"));
        rowhead.createCell((short) 7).setCellValue(new HSSFRichTextString("Tecnologia"));
        rowhead.createCell((short) 8).setCellValue(new HSSFRichTextString("Diagnostico"));
        rowhead.createCell((short) 9).setCellValue(new HSSFRichTextString("Concepto CTC"));
        rowhead.createCell((short) 10).setCellValue(new HSSFRichTextString("Estado"));
        rowhead.createCell((short) 11).setCellValue(new HSSFRichTextString("Ambito"));
        rowhead.createCell((short) 12).setCellValue(new HSSFRichTextString("Origen"));
        rowhead.createCell((short) 13).setCellValue(new HSSFRichTextString("Entidad"));
        rowhead.createCell((short) 14).setCellValue(new HSSFRichTextString("Eps"));
        rowhead.createCell((short) 15).setCellValue(new HSSFRichTextString("Origino"));
        rowhead.createCell((short) 16).setCellValue(new HSSFRichTextString("Tipo CTC"));
        rowhead.createCell((short) 17).setCellValue(new HSSFRichTextString("Prioridad"));
        rowhead.createCell((short) 18).setCellValue(new HSSFRichTextString("Tipo Prioridad"));
        
        int index = 1;
        for (BandejaXls item : items){
            HSSFRow row = sheet.createRow((short) index);
            row.createCell((short) 0).setCellValue(new HSSFRichTextString(item.getSolId().toString()));
            row.createCell((short) 1).setCellValue(new HSSFRichTextString(item.getSolItemId().toString()));
            row.createCell((short) 2).setCellValue(new HSSFRichTextString(item.getRegional()));
            row.createCell((short) 3).setCellValue(new HSSFRichTextString(item.getFecha()));
            row.createCell((short) 4).setCellValue(new HSSFRichTextString(item.getTipoDocumento()));
            row.createCell((short) 5).setCellValue(new HSSFRichTextString(item.getDocumento()));
            row.createCell((short) 6).setCellValue(new HSSFRichTextString(item.getUsuario()));
            row.createCell((short) 7).setCellValue(new HSSFRichTextString(item.getTecnologia()));
            row.createCell((short) 8).setCellValue(new HSSFRichTextString(item.getDiagnostico()));
            row.createCell((short) 9).setCellValue(new HSSFRichTextString(item.getConceptoCTC()));
            row.createCell((short) 10).setCellValue(new HSSFRichTextString(item.getEstado()));
            row.createCell((short) 11).setCellValue(new HSSFRichTextString("Ambulatorio"));
            row.createCell((short) 12).setCellValue(new HSSFRichTextString(item.getOrigen()));
            row.createCell((short) 13).setCellValue(new HSSFRichTextString("Contratada"));
            row.createCell((short) 14).setCellValue(new HSSFRichTextString(item.getEps()));
            row.createCell((short) 15).setCellValue(new HSSFRichTextString(item.getOrigino()));
            row.createCell((short) 16).setCellValue(new HSSFRichTextString(item.getTipoCTC()));
            row.createCell((short) 17).setCellValue(new HSSFRichTextString("2"));
            row.createCell((short) 18).setCellValue(new HSSFRichTextString("Programada"));
            
            index++;	
        }
        
        return wb;
	}
}
