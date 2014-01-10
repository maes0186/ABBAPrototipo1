/*
 * Created on 08-nov-2004
 */
package com.conexia.saludcoop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author kappesa
 */
public class DateUtilities 
{

    static final long ONE_HOUR = 60 * 60 * 1000L;
    
    /**
     * Devuelve un objeto date en base a un string dado, en el formato dado.
     * 
     * @param sDate :el string de la fecha
     * @param format : el formato de la fecha
     * @return un objeto date.
     * 
     * @throws ParseException
     * @throws IllegalArgumentException
     */
    public static synchronized Date getDateForString(String sDate,String format) throws  ParseException,IllegalArgumentException
    {
        Date dResult=null;
        
         try {
             SimpleDateFormat sdf = new SimpleDateFormat(format);
             sdf.setLenient(false);
             dResult = sdf.parse(sDate);
           }
         catch (ParseException pe) 
         {
            throw pe;
          }
         
         catch (IllegalArgumentException iae) 
         {
           throw iae;
         }
         
         return dResult;
    }
        
    /**
     * Valida una fecha.
     * 
     * @param sDate : la fecha a validar
     * @return verdadero si la fecha esta ok, falso en caso contrario.
     * 
     * @throws ParseException
     * @throws IllegalArgumentException

     */
    public static synchronized boolean isValid(String sDate,String format) throws  ParseException,IllegalArgumentException
    {
        boolean hResult=true;
         
         try {
             SimpleDateFormat sdf = new SimpleDateFormat(format);
             sdf.setLenient(false);
             Date dt2 = sdf.parse(sDate);
           }
         catch (ParseException pe) 
         {
            //throw pe;
             hResult=false;
          }
         
         catch (IllegalArgumentException iae) 
         {
           //throw iae;
             hResult=false;
         }
         
         return hResult;
    }

    
    /**
     * Calcula la diferencia en dias, entre 2 fechas
     * 
     * @param d1: la fecha desde.
     * @param d2: la fecha hasta.
     * @return : la cantidad de dias de diferencia.
     */
    public static synchronized long getDaysBetween(Date d1, Date d2)
    {
      return ( (d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
    }   


    /**
     * Facil, aplica un formato a un date, y se obtiene el mismo date formateado
     * Estoy cansado de buscar siempre y de acordarme como hacer para formatear
     * una maldita fecha, asi que me canse y lo hice. :-)
     * 
     * 
     * @param sDate
     * @param sFormat
     * @return
     * @throws ParseException
     */
    public static synchronized Date applyFormatToDate(Date sDate,String sFormat) throws ParseException
    {
        Date retDate=null;
        
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
        
            String sAux=sdf.format( sDate);
            retDate=sdf.parse(sAux);
        }
        
        catch(ParseException pe) 
        {
            throw pe;
        }
        
        return retDate;
    }
    


    /**
     * Devuelve la fecha actual, como string, en el formato indicado
     * 
     * @param format (todos los de java)
     * 
     *  			"dd MMMMM yyyy"
     *				"yyyyMMdd"
     *				"dd.MM.yy"
     * 				"MM/dd/yy"
     *				"yyyy.MM.dd G 'at' hh:mm:ss z"
     *				"EEE, MMM d, ''yy"
     *				"h:mm a"
     *				"H:mm:ss:SSS"
     *				"K:mm a,z"
     *				"yyyy.MMMMM.dd GGG hh:mm aaa"
     * 
     * @return la fecha actual en el formato indicado
     */
    public static synchronized String getActualDateByFormatAsString (String format) throws Exception
    {
        String datenewformat;
        try
        {
            Date today = new Date();
        
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            datenewformat = formatter.format(today);
        }
        catch (Exception ex)
        {
            throw ex;
        }
        
        return  datenewformat;
    }

    /**
     * Compara 2 fechas.
     * 
     * @param fecha1 : primer fecha a comparar.
     * @param fecha2 : segunda fecha a comparar.
     * @param sDateFormat :opcional, el formato en que se pasan las fechas, si no se proporciona uno,
     * 					   toma un default del sistema.
     * 	
     * @return -1 si fecha1 es ANTERIOR a fecha2
     * 		    1 si fecha1 es POSTERIOR a fecha2
     * 			0 si son iguales.
     * 
     * @throws Exception : si no puede comparar las fechas.
     */
    public static synchronized int compareDates(Calendar fecha1,Calendar fecha2,String sDateFormat) throws Exception
    {
        
        //SimpleDateFormat sdf = new SimpleDateFormat(sDateFormat);
    
        if (fecha1.before(fecha2)) 
        {
            return -1;
        }
        else if (fecha1.after(fecha2)) 
        {
            return 1;     
        }  
        else if (fecha1.equals(fecha2)) 
        {
            return 0;     
        }  
        else
        {
            throw new Exception ("Error al comparar las fechas");
        }
     
    }


    /**
     * Convierte una cadena string a un objeto date, siguiendo un formato
     * 
     * @param sDate
     * @param sFormat
     * @return
     * @throws ParseException
     */
    public static synchronized Date convertStringToDate(String sDate,String sFormat) throws ParseException
    {
        Date retDate=null;
        SimpleDateFormat sdf=null;
        
        if (sFormat.trim().equals(""))
        {
             sdf = new SimpleDateFormat();
        }
        else
        {
            sdf = new SimpleDateFormat(sFormat);
        }
        
        
        try 
        {
            retDate= sdf.parse(sDate);
        }
        catch(ParseException pe) 
        {
            throw pe;
        }
        
        return retDate;
    }

	
	/** Formatea un string que representa a una fecha en formato 'inputFormat' al formato 'outputFormat' */
	public static synchronized String formatDate( String strDate, String inputFormat, String outputFormat ){
			
		Locale location = new Locale ( "ES", "AR" );
		SimpleDateFormat dateFormat;
		//String result;
		Date date;
		
		if (! strDate.trim().equals(""))
		{
		  try{
			   //Reemplaza los caracteres en el formato de entrada
			   inputFormat = inputFormat.replace( 'D', 'd' );
			   inputFormat = inputFormat.replace( 'm', 'M' );
			   inputFormat = inputFormat.replace( 'A', 'y' );
			   inputFormat = inputFormat.replace( 'a', 'y' );
			
			   //Interpreta la entrada de acuerdo al formato ingresado por parametro
			   dateFormat = new SimpleDateFormat( inputFormat, location );			
			   date = dateFormat.parse( strDate );
					
			   return parseDate( date, outputFormat );
			
		  	 } 
		  
		     catch ( NullPointerException npEx )
		     { throw npEx; }
		     
		     catch ( IllegalArgumentException iaEx )
		        { throw iaEx; } 
		     
		     catch ( ParseException pEx )
		        {return "invalid";}
		}
		else
		    return "";
		
	}

	
	/** Realiza el parseo de una fecha a un formato especificado */
	public static synchronized String parseDate( Date date, String outputFormat ){
		
		Locale location = new Locale ( "ES", "AR" );
		SimpleDateFormat dateFormat;
		String result;
		
		//Reemplaza los caracteres en el formato de salida
		outputFormat = outputFormat.replace( 'D', 'd' );
		outputFormat = outputFormat.replace( 'm', 'M' );
		outputFormat = outputFormat.replace( 'A', 'y' );
		outputFormat = outputFormat.replace( 'a', 'y' );
		
		//Formatea la salida al formato ingresado por parametro
		dateFormat = new SimpleDateFormat( outputFormat, location );			
		result = dateFormat.format( date );
		
		return result;
	} 
	
	/** Dado los 2 ultimos digitos de un año realiza la conversion al año de formato AAAA**/
	public static synchronized int formatYear(int aa){
	  if (aa > 80){
			aa = aa + 1900;
		}else {
		  	aa = aa + 2000;
		}
	 return aa;
	}
	
    public static String parseDateTime( Date date)
    {
        return parseDateTime(date,"");
    }
    
	/** Realiza el parseo de una fecha a un formato ddMMaaaaHHmmss */
	public static synchronized String parseDateTime( Date date,String formato)
    {
        String sFormat="";
        
        if ("".equals(formato))
             sFormat="ddMMyyyyHHmmss"; //un formato default
        else
            sFormat=formato;
        
        Locale location = new Locale ( "ES", "AR" );
		SimpleDateFormat dateFormat;
		String result;
		
		//Formatea la salida al formato especificado
		dateFormat = new SimpleDateFormat(sFormat, location );			
		result = dateFormat.format( date );
		
		return result;
	} 
	
	/** Indica si una fecha corresponde al día de hoy o no. Compara por la fecha, no la hora.*/ 
    public static synchronized boolean isToday( Date date ){
        
    	//la fecha de la transaccion
    	Calendar transactionDate = Calendar.getInstance();
    	transactionDate.setTime(date);
    	
    	//la fecha actual
    	Calendar actualDate = Calendar.getInstance();
    	
    	if ( (actualDate.get(Calendar.DAY_OF_MONTH) == transactionDate.get(Calendar.DAY_OF_MONTH)) &&
    	     (actualDate.get(Calendar.MONTH) == transactionDate.get(Calendar.MONTH))               && 
    	     (actualDate.get(Calendar.YEAR) == transactionDate.get(Calendar.YEAR)) ) {
    		
    		return true;
    	} 
    	else { 
    		return false;
    	}
    	
    	
    }
    
	
    
    /** Devuelve la fecha con la hora inicializada a cero */
    public static synchronized Date getDateAtTime( Date date, String time )throws ParseException{
        
        String fechaStr;
        SimpleDateFormat formatter = new SimpleDateFormat();

        try{
            fechaStr = parseDate( date, "DDMMAAAA" );
        
            return formatter.parse( fechaStr.substring( 0, 2 ) + "/" + fechaStr.substring( 2, 4 ) + "/" + fechaStr.substring( 4, 8 ) + " " + time );
            
        }catch( ParseException pex ){
            throw pex;
            
        }catch( Exception ex ){
            ex.printStackTrace();
            return null;
        }
    }
    
    /** calcula la edad */
    public static int calcularEdad(Date fechaNacimiento, Date fechaActual){
        Calendar fechaAct = Calendar.getInstance();
        fechaAct.setTime(fechaActual);

        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);

        int dif_anios = fechaAct.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int dif_meses = fechaAct.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int dif_dias = fechaAct.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);

        //Si esta en ese ano pero todavia no los ha cumplido
        if(dif_meses<0 || (dif_meses==0 && dif_dias<0)){
            dif_anios--;
        }
        return dif_anios;
    }
    
}
