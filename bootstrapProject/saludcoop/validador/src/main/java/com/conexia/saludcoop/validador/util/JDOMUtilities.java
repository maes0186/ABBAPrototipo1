package com.conexia.saludcoop.validador.util;

import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

public class JDOMUtilities 
{
    
    
    public static synchronized void setElementValue(Document xmlDoc,String xPath,String sValue) throws Exception
    {
        XPath x      = XPath.newInstance( xPath.trim() );
        Element e    = (Element) x.selectSingleNode(xmlDoc);
        e.setText( sValue );
    }
    
    
    public static synchronized Element getElement(Document xmlDoc,String xPath) throws Exception
    {
        XPath x      = XPath.newInstance( xPath.trim() );
        Element e=(Element) x.selectSingleNode(xmlDoc);
        
        return e;
    }    

    
    public static synchronized List getElementsAsList(Document xmlDoc,String xPath) throws Exception
    {
        XPath x      = XPath.newInstance( xPath.trim() );
        List list_Element =  x.selectNodes(xmlDoc);  
        
        return list_Element;
    }

    
    public static synchronized String getElementValueAsString(Document xmlDoc,String xPath) throws Exception
    {
        XPath x      = XPath.newInstance( xPath.trim() );
        
        Element e=(Element) x.selectSingleNode(xmlDoc);
        
        return (e == null) ? "" : e.getText();
    }    
    
    
    public static synchronized int getElementValueAsInt(Document xmlDoc,String xPath) throws Exception
    {
        int iResult=0;
        
        XPath x      = XPath.newInstance( xPath.trim() );
        Element e    = (Element) x.selectSingleNode(xmlDoc);
     
        try
        {
            iResult = Integer.parseInt(e.getText().trim());
        }
        catch (Exception ex)
        {
        }
        
        return iResult;
    }
    
    
    public static synchronized String getParameter(Element anElement, String paramName ){
		 int indice;
		 List parameterList;
		 Element parameterElement, auxElement;
		 Attribute nameAttribute, valueAttribute;
		 String result="";
		 
		 try{
			 auxElement=  anElement.getChild("parameters");
			 parameterList = auxElement.getChildren("parameter");
			 
			 indice=0;
			 while (parameterList.listIterator(indice).hasNext() ){
		    		parameterElement = (Element) parameterList.listIterator(indice).next();	
		    		
		    		nameAttribute = parameterElement.getAttribute("name");
		    		
		    		if (nameAttribute.getValue().equalsIgnoreCase(paramName)){
		    			valueAttribute = parameterElement.getAttribute("value");
		    			return valueAttribute.getValue();
		    		}
		    		indice++;
			 }
			 return result;
		}catch ( Exception ex ){
			ex.printStackTrace();
			return result;
		} 
	}

	
	public static synchronized int getParameterAsInt(Element anElement, String paramName ){
		String strResult; 
		int result=-1;
		
		strResult=getParameter(anElement,paramName );
		 
		if ( strResult != "" ){
			
	    		try{
	    			result = Integer.parseInt( strResult );
	    			
	    		} catch ( NumberFormatException ex ){
	    			result = -1;
	    		}
	    }
	    return result;
	}

	
	public static synchronized long getParameterAsLong(Element anElement, String paramName ){
		String strResult; 
		long result=-1;
		
		strResult=getParameter(anElement,paramName );
		 
		if ( strResult != "" ){
			
	    		try{
	    			result = Long.parseLong( strResult );
	    			
	    		} catch ( NumberFormatException ex ){
	    		  result = -1;
	    		}
	    }
	    return result;  
	}
	
	
	 public static synchronized byte getParameterAsByte( Element anElement, String paramName ){
	    	
	    	String strResult;
	    	byte result = -1;
	    	
	    	strResult = getParameter( anElement, paramName );
	    	
	    	if ( strResult != null ){
	    		
	    		try{
	    			result = Byte.parseByte( strResult );
	    		
	    		} catch ( NumberFormatException ex ){
	    			result = -1;
	    		}
	    	}
	    	return result;
	    }


	public static synchronized String getAttribute(Element anElement, String paramName){
		 Attribute atributo;
		 
		 try{
			 	atributo = anElement.getAttribute(paramName);
	    		return atributo.getValue();
		    	
		}catch ( Exception ex ){
			return "";
		} 
	}
    
    
    /**
     * Funcion que permite formatear un documento xml, en un formato
     * "amigable".
     * 
     * @author Adrian Muñoz
     * @param xmlDoc          : el documento a formatear.
     * @param omitEncoding    : ver JDOM Documentation.
     * @param omitDeclaration : ver JDOM Documentation.
     * @param showExpandEmptyElements    : ver JDOM Documentation.
     * @return: un string con el DOM formateado.
     */
    public static synchronized String  getFormattedXmlAsString(Document xmlDoc
                                                                , boolean omitEncoding 
                                                                , boolean omitDeclaration
                                                                , boolean showExpandEmptyElements) throws Exception 
      {
          /**TODO: el xmloutputter,es el que formatea el xml como se me ocurra. */
          Format formatXml=Format.getPrettyFormat() ;
          formatXml.setOmitEncoding( omitEncoding );
          formatXml.setOmitDeclaration( omitDeclaration );
          formatXml.setExpandEmptyElements( showExpandEmptyElements); 
          
          XMLOutputter fmt = new XMLOutputter( formatXml);
          return   fmt.outputString( xmlDoc ).trim();
      }
    
    public static synchronized String  getFormattedXmlAsString(Document xmlDoc
                                                                ,Format formatter
                                                                , boolean omitEncoding 
                                                                , boolean omitDeclaration
                                                                , boolean showExpandEmptyElements) throws Exception 
    {
        
        Format formatXml=formatter ;
        formatXml.setOmitEncoding( omitEncoding );
        formatXml.setOmitDeclaration( omitDeclaration );
        formatXml.setExpandEmptyElements( showExpandEmptyElements); 
        
        XMLOutputter fmt = new XMLOutputter( formatXml);
        return   fmt.outputString( xmlDoc ).trim();
    }
}
