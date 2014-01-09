package com.conexia.saludcoop.web.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conexia.repository.access.StoredFile;
import com.conexia.repository.exception.FileRepositoryException;
import com.conexia.saludcoop.common.dto.FormatoNegacionServiciosDto;
import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.io.repository.FileRepositoryProvider;
import com.conexia.saludcoop.web.vo.DocumentoSoporteVO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class FormatoNegacionServicioGenerator {
    
    private static Logger LOGGER = LoggerFactory.getLogger(FormatoNegacionServicioGenerator.class);
    
    private final static String PREFIJO_NOMBRE_ARCHIVO = "FormatoNegacionServicio";

    public static StoredFile crearPdf(FileRepositoryProvider fileRepositoryProvider, FormatoNegacionServiciosDto formato, URL urlCabecera) {
     
        LOGGER.debug("Inicio generacion formato negacion servicios");

        StoredFile storedFile = null;
                    
            try {
                Document document = new Document(PageSize.LETTER);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, baos);
                
                document.open();            
                FormatoNegacionServicioGenerator.estructurarPDF(document, formato, urlCabecera);                
                document.close();
                
                storedFile = fileRepositoryProvider.getInstance().storeFile(baos.toByteArray());
            } catch (DocumentException e) {
                LOGGER.error("Error procesando el documento.", e);
            } catch (FileRepositoryException e) {
                LOGGER.error("Error guardando el archivo en el repositorio.", e);
            } catch (IOException e) {
                LOGGER.error("Error agregando imagen al documento.", e);
            }
            
            LOGGER.debug("Fin generacion formato negacion servicios");
        return storedFile;
    }
    
    public static void estructurarPDF(Document document, FormatoNegacionServiciosDto formato, URL urlCabecera) throws DocumentException, IOException {

        FormatoNegacionServicioGenerator.addEncabezado(document, formato, urlCabecera);
        FormatoNegacionServicioGenerator.addInformacionGeneral(document, formato);
        FormatoNegacionServicioGenerator.addClaseServicioRecomendaciones(document, formato);
        FormatoNegacionServicioGenerator.addAlternativas(document);
        FormatoNegacionServicioGenerator.addFirmas(document, formato);
        FormatoNegacionServicioGenerator.addPie(document);

    }

    private static void addEncabezado(Document document, FormatoNegacionServiciosDto formato, URL urlCabecera) throws DocumentException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        PdfPTable outTable = new PdfPTable(1);
        outTable.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
        
        PdfPCell outCell = new PdfPCell(outTable.getDefaultCell());
        
        PdfPTable table = new PdfPTable(new float[] { 2, 5, 2 });
        table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
        table.getDefaultCell().setPadding(10);
        
        table.addCell(PDFHelper.createImageCell(urlCabecera, formato.getEps(), true));

        PdfPCell cell = new PdfPCell(table.getDefaultCell());
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase("REPUBLICA DE COLOMBIA \n SUPERINTENDENCIA NACIONAL DE SALUD \n FORMATO DE NEGACION DE SERVICIOS DE SALUD Y/O MEDICAMENTOS",PDFHelper.BOLD_FONT));
        table.addCell(cell);        

        cell = PDFHelper.createCell(" ");
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        outCell.addElement(table);
        
        table = new PdfPTable(3);
        table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
        
        cell = PDFHelper.createCell(" ");
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        cell = PDFHelper.createCell(formato.getEps());
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        cell = PDFHelper.createCell(""/*"N° "+formato.getNumeroformatonegacion()*/);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);  
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        String fechaSol = formato.getFechasolicitud() != null ? sdf.format(formato.getFechasolicitud()) : "";
        cell = PDFHelper.createCell("Fecha Solicitud (dd/mm/yyyy) : "+ fechaSol);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        String fechaDiligencia = formato.getFechanegacion() != null ? sdf.format(formato.getFechanegacion()) : "";
        cell = PDFHelper.createCell("Fecha de Diligenciamiento (dd/mm/yyyy) : "+ fechaDiligencia);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        cell = PDFHelper.createCell("N° Solicitud "+formato.getNumerosolicitud());
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);  
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        outCell.addElement(table);
        outTable.addCell(outCell);
        document.add(outTable);
        document.add(new Paragraph(" "));


    }
    

    private static void addInformacionGeneral(Document document, FormatoNegacionServiciosDto formato) throws DocumentException {
        Paragraph p = new Paragraph("I. DATOS GENERALES DEL SOLICITANTE DEL SERVICIO", PDFHelper.BOLD_FONT);
        p.setSpacingAfter(5);
        document.add(p);
       
        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setPadding(10);
        table.getDefaultCell().setUseAscender(false);
        table.getDefaultCell().setUseDescender(false);
        table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
        
        PdfPCell cell = PDFHelper.createCell("1er APELLIDO: "+formato.getPrimerapellido());
        table.addCell(cell);
        
        cell = PDFHelper.createCell("2do. APELLIDO (o de CASADA): "+formato.getSegundoapellido());
        table.addCell(cell);
        
        String nombres = formato.getPrimernombre() != null ? formato.getPrimernombre() : "";
        nombres = formato.getSegundonombre() != null ? " "+formato.getSegundonombre() :"";      
        cell = PDFHelper.createCell("NOMBRES: "+nombres);
        table.addCell(cell);
        
        cell = PDFHelper.createCell("TIPO IDENTIFICACIÓN: "+formato.getTipoidentificacion());
        table.addCell(cell);
        
        cell = PDFHelper.createCell("N° DE IDENTIFICACIÓN: "+formato.getNumeroidentificacion());
        table.addCell(cell);
        
        cell = PDFHelper.createCell("N° CONTRATO: "/*+formato.getNumeroContrato()*/);
        table.addCell(cell);
        
        String telefonos = formato.getTelefonoresidencial() != null ? formato.getTelefonoresidencial() : "";
        telefonos = telefonos.concat(formato.getTelefonocelular()!= null && formato.getTelefonocelular().trim().length() > 0? " - "+formato.getTelefonocelular() :"");  
        cell = PDFHelper.createCell("TELÉFONOS: "+telefonos);
        table.addCell(cell);
        
        cell = PDFHelper.createCell("CIUDAD/MUNICIPIO: "+formato.getCiudadmunicipio());
        table.addCell(cell);
        
        cell = PDFHelper.createCell("DEPARTAMENTO: "+formato.getDepartamento());
        table.addCell(cell);    
        
        String cobertura = "";
        if(formato.getTipoplanusuario() != null){
            cobertura = RegimenAfiliacion.fromId(formato.getTipoplanusuario().longValue()).toString();
        }
                                
        cell = PDFHelper.createCell("COBERTURA: "+cobertura);
        table.addCell(cell);
        
        cell = PDFHelper.createCell("N° DE SEMANAS COTIZADAS POR EL USUARIO AL SGSS: "+formato.getNumerosemanassgss());
        table.addCell(cell);
        
        cell = PDFHelper.createCell("ESTADO DE LA AFILIACIÓN/CONTRATO DEL USUARIO: "+formato.getEstadoafiliacion());
        table.addCell(cell);   
            
        document.add(table);
        document.add(new Paragraph(" "));
        
    }
    

    private static void addClaseServicioRecomendaciones(Document document, FormatoNegacionServiciosDto formato) throws DocumentException {
        Paragraph p = new Paragraph("II. CLASE DE SERVICIO NO AUTORIZADO Y RECOMENDACIONES AL USUARIO", PDFHelper.BOLD_FONT);
        p.setSpacingAfter(5);
        document.add(p);
        
        PdfPTable outTable = new PdfPTable(1);
        outTable.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
        
        PdfPTable table = new PdfPTable(new float[] {1,5});
        table.getDefaultCell().setPadding(2);
        table.getDefaultCell().setUseAscender(false);
        table.getDefaultCell().setUseDescender(false);
        table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
        
        PdfPCell cell = new PdfPCell(table.getDefaultCell());
        cell.setColspan(2);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase("SERVICIO NO AUTORIZADO",PDFHelper.BOLD_FONT));
        table.addCell(cell);
               
        String codigo = "";
        String desc = "";
        if(formato.getCodmedicamento() != null){
            codigo = formato.getCodmedicamento();
            desc = formato.getDescmedicamento();
        } else if (formato.getCodprocedimiento() != null){
            codigo = formato.getCodprocedimiento();
            desc = formato.getDescprocedimiento();
        } else if(formato.getCodinsumo() != null){
            codigo = formato.getCodinsumo();
            desc = formato.getDescinsumo();
        }
        
        cell = new PdfPCell(table.getDefaultCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase("CÓDIGO",PDFHelper.BOLD_FONT));
        table.addCell(cell);
        
       
        cell = PDFHelper.createCell(codigo);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        cell = new PdfPCell(table.getDefaultCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase("DESCRIPCION",PDFHelper.BOLD_FONT));
        table.addCell(cell);
        
        cell = PDFHelper.createCell(desc);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        
        String justificacion = "";
        if(formato.getJustificacionnacional() != null){
            justificacion = formato.getJustificacionnacional();
        } else if (formato.getJustificacionregional() != null){
            justificacion = formato.getJustificacionregional();
        }
        
        cell = new PdfPCell(table.getDefaultCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase("JUSTIFICACIÓN",PDFHelper.BOLD_FONT));
        table.addCell(cell);
        
        cell = PDFHelper.createCell(justificacion);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        cell = new PdfPCell(table.getDefaultCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase(" ",PDFHelper.BOLD_FONT));
        cell.setColspan(2);
        table.addCell(cell);
        
        cell = new PdfPCell(table.getDefaultCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase("FUNDAMENTO LEGAL:",PDFHelper.BOLD_FONT));
        cell.setColspan(2);
        table.addCell(cell);
                
        cell = new PdfPCell(table.getDefaultCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase("Acuerdo 029 de 28 de diciembre de 2011 de la Comisión de Regulación en Salud (CRES).",PDFHelper.DEFAULT_FONT));
        cell.setColspan(2);
        table.addCell(cell);
        
        cell = new PdfPCell(table.getDefaultCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase("Decreto 2200 de 2005 Ministerio de la Protección Social y Resolución 5261 de 1994.",PDFHelper.DEFAULT_FONT));
        cell.setColspan(2);
        table.addCell(cell);
        
        outTable.addCell(table);
        document.add(outTable);
        
        
    }

    private static void addAlternativas(Document document) throws DocumentException {
        PdfPTable outTable = new PdfPTable(1);
        outTable.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);

        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setPadding(5);
        table.getDefaultCell().setUseAscender(false);
        table.getDefaultCell().setUseDescender(false);
        table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
        
        PdfPCell cell = new PdfPCell(table.getDefaultCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase("Alternativas para que el usuario acceda al servicio de salud o medicamento solicitado y haga valer sus derechos legales y constitucionales",PDFHelper.BOLD_FONT));
        table.addCell(cell);
        
        StringBuilder sb = new StringBuilder();
        sb.append("El Decreto 806 de 1998 artículo 28 determina para los servicios adicionales no incluidos en el POS lo siguiente:");
        sb.append("Artículo 28º (...) Parágrafo. Cuando el afiliado al Régimen Contributivo requiera de servicios adicionales a los incluidos en el POS deberá financiarlos ");
        sb.append("directamente. Cuando no tenga capacidad de pago para asumir el costo de estos servicios adicionales, podrá acudir a las instituciones públicas y aquellas ");
        sb.append("privadas que tengan contrato con el Estado, las cuales estarán en la obligación de atenderlo de conformidad con su capacidad de oferta y cobrarán por su ");
        sb.append("servicio una cuota de recuperación con sujeción a las normas vigentes. (…). \n\n");
        sb.append("Para los medicamentos y medios de contraste no incluidos en el POS, Usted puede solicitar su estudio por parte del Comité Técnico Científico de la EPS, ");
        sb.append("para lo cual deben ser radicados los soportes correspondientes en la oficina de la EPS.");
       
        
        
        cell = new PdfPCell(table.getDefaultCell());
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPhrase(new Phrase(sb.toString(),PDFHelper.DEFAULT_FONT));
        table.addCell(cell);
        
        outTable.addCell(table);
        document.add(outTable);
        
    }
    

    private static void addFirmas(Document document, FormatoNegacionServiciosDto formato) throws DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setPadding(10);
        table.getDefaultCell().setUseAscender(false);
        table.getDefaultCell().setUseDescender(false);
        table.setWidthPercentage(PDFHelper.DEFAULT_WIDTH);
        
        PdfPCell cell = new PdfPCell(table.getDefaultCell());
        Paragraph paragraph = new Paragraph(formato.getNombrefuncionarioniega(), PDFHelper.DEFAULT_FONT);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(paragraph);
        paragraph = new Paragraph("____________________________________________________________________________________________________", PDFHelper.DEFAULT_FONT);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(paragraph); 
        paragraph = new Paragraph("Nombre, Cargo y Firma del Funcionario que niega el servicio", PDFHelper.BOLD_FONT);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(paragraph);        
        table.addCell(cell);
        
        cell = new PdfPCell(table.getDefaultCell());
        paragraph = new Paragraph(" ", PDFHelper.DEFAULT_FONT);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(paragraph);
        paragraph = new Paragraph("____________________________________________________________________________________________________", PDFHelper.DEFAULT_FONT);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(paragraph); 
        paragraph = new Paragraph("Firma del usuario o de quien recibe", PDFHelper.BOLD_FONT);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(paragraph);        
        table.addCell(cell);
               
        document.add(table);
        
    }

    private static void addPie(Document document) throws DocumentException {

        StringBuilder sb = new StringBuilder();
        sb.append("Si está en desacuerdo con la decisión adoptada, acuda a la oficina de Atención al Usuario de la Entidad. Si su queja no es resuelta, eleve consulta ante la Superintendencia ");
        sb.append("Nacional de Salud anexando copia de este formato debidamente diligenciado, a la Carrera 13 No. 32 - 76. PBX 3300210");
        
        Paragraph p = new Paragraph(sb.toString(), PDFHelper.BOLD_FONT);
        document.add(p);
        
    }
    
    private static DocumentoSoporteVO crearDocumentoSoporte(StoredFile storedFile, FormatoNegacionServiciosDto formato) {

        DocumentoSoporteVO documento = new DocumentoSoporteVO();

        documento.setNombreArchivoOriginal(FormatoNegacionServicioGenerator.PREFIJO_NOMBRE_ARCHIVO
                + formato.getNumeroformatonegacion());
        documento.setNombreArchivoServidor(storedFile.getFilename());

        return documento;
    }
}
