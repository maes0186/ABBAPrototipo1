/*
 * Created on 08-nov-2004
 */
package com.conexia.saludcoop.validador.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author kappesa
 *
 */
public class StringUtilities {
	
	
	/** Permite darle formato a 'aString' alineandolo a la izquierda y completando con tantos 'charToAdd' como sean 
	 *  necesarios en caso de que la longitud de 'aString' sea menor que 'size' o truncando 'aString' a 'size' caracteres, 
	 *  segun corresponda
	 *  @param aString
	 *  @param size */
	public static synchronized StringBuffer toLeftAlignedFormat( String aString, int size, char charToAdd ){
		
		StringBuffer result = null;

		int len = 0;
		int i = 0;
		int dif = 0;
		
        if( aString == null ){
            aString = "";
        }
        
    	len = aString.length();
    	result = new StringBuffer( "" );
    		
    	//Si la longitud de la cadena y el tamaño deseado son mayores a 0
    	if ( ( len >= 0 ) && ( size > 0 ) ){
    			
    		//Si la longitud de la cadena es igual al tamaño deseado devuelve la cadena como esta
    		if ( len == size ){
    			result = result.append( aString );
    			
    		// Si la longitud de la cadena es menor al tamaño deseado completa con espacios, 
    		// alineando hacia donde corresponda
    		} else if ( len < size ){
    			dif = size - len;
    				
    			result = result.append( aString );					
    			for ( i = 0; i < dif; i++ ){
    				result = result.append( charToAdd );
    			}
    				
    		// Si la longitud de la cadena es mayor que el tamaño deseado se trunca la cadena a los primeros digitos.
    		} else {
    			result = result.append( aString.substring( 0, size ) );
    		}
    	} 
		
		return result;
	}
	
	
	
	/** Permite darle formato a 'aString' alineandolo a la derecha y completando con tantos 'charToAdd' como sean 
	 *  necesarios en caso de que la longitud de 'aString' sea menor que 'size' o truncando 'aString' a 'size' caracteres, 
	 *  segun corresponda
	 *  @param aString
	 *  @param size 
	 *  @param charToAdd */
	public static synchronized StringBuffer toRightAlignedFormat( String aString, int size, char charToAdd ){
		
		StringBuffer result = null;

		int len = 0;
		int i = 0;
		int dif = 0;
        
        if( aString == null ){
            aString = "";
        }
		
    	len = aString.length();
    	result = new StringBuffer( "" );
    		
    	//Si la longitud de la cadena y el tamaño deseado son mayores a 0
    	if ( ( len >= 0 ) && ( size > 0 ) ){
    		
    		//Si la longitud de la cadena es igual al tamaño deseado devuelve la cadena como esta
    		if ( len == size ){
    			result = result.append( aString );
    			
    		// Si la longitud de la cadena es menor al tamaño deseado
    		// completa con espacios, alineando hacia donde corresponda
    		} else if ( len < size ){
    			dif = size - len;
    									
    			for ( i = 0; i < dif; i++ ){
    				result = result.append( charToAdd );
    			}
    				
    			result = result.append( aString );
    				
    		// Si la longitud de la cadena es mayor que el tamaño deseado
    		// se trunca la cadena a los últimos digitos.
    		} else {
    			dif = len - size;
    			result = result.append( aString.substring( dif, len ) );
    		}
    	} 
        
		return result;
	}

	

	/** Permite replicar una cadena de caracteres ('caracter'), 'repeticiones' veces
	 *  @param caracter
	 *  @param repeticiones */
	public static synchronized String repeatChar( String caracter, int repeticiones ) {
	
	    StringBuffer sAux = new StringBuffer( "" );	
		
		for ( int i = 1; i <= repeticiones; i++ ){
				sAux.append( caracter );
		}
	
		return sAux.toString() ;
	}

	
	/**TODO: funcion para pasar el stack trace a un string, y poder loggearlo **/
	public static synchronized String Stack2String(Exception e) 
	{
	    try {
	      StringWriter sw = new StringWriter();
	      PrintWriter pw = new PrintWriter(sw);
	      e.printStackTrace(pw);
	      return "\r\n--------\r\n" + sw.toString() + "\r\n------\r\n";
	      }
	    catch(Exception e2) {
	      return "Error en Stack2String";
	      }
	    } 
    
    
    /**Devuelve verdadero si el string pasado como parametro coincide con
     * alguno de los elementos del vector.
     * 
     * @param args
     * @param txtSearch
     * @return
     */
    private static synchronized boolean isInVectorString(String args[],String txtSearch)
    {
        boolean hResult = false;
        
        
        for (int i = 0;i< args.length;i++)
        {
            if (txtSearch.trim().equals(  args[i].trim() ))
            {
                hResult = true;
                break;
            }
        }
        
        return hResult;
    }
    
    /**Devuelve un StringBuffer con los metodos y valores de una instancia de clase, como lista de python.
     * recibe como parametro la clase, y un vector de strings, con los nombres de los metodos a invocar.
     * La funcion invoca en tiempo de ejecucion esos metodos, y captura el valor para devolverlo en 
     * el string buffer. No fue probado con objetos 
     * 
     * @param oClase
     * @param customList
     * @throws Exception
     */
    public static synchronized StringBuffer getMethodsClassAsPythonList(Object oClase,String[] customList) throws Exception
    {
    
     Class c = oClase.getClass();
  
     Method[] theMethods = c.getMethods();
     StringBuffer pList = new StringBuffer("");
     String sValue = "";
     
     
     pList.append("(");
     
     for (int i = 0; i < theMethods.length; i++) 
     {
        String methodString = theMethods[i].getName();
        
        if (isInVectorString(customList,methodString))
           {
            //System.out.println("Name: " + methodString);
            String returnString = theMethods[i].getReturnType().getName();
            
            //System.out.println("   Return Type: " + returnString);
            Class[] parameterTypes = theMethods[i].getParameterTypes();
            
            //System.out.println("invocacion: "+sValue );
            if (returnString.equals("java.lang.String"))
            {
                sValue = String.valueOf( theMethods[i].invoke(oClase,parameterTypes) );
                pList.append("'" + sValue + "'");
            }
            else if (returnString.equals("java.util.Date"))
            {
                Date dValue = (Date) theMethods[i].invoke(oClase,parameterTypes); 
                pList.append( DateUtilities.parseDateTime(dValue,"yyyyMMddHHmmss"));
            }
            else
            {
                sValue = String.valueOf( theMethods[i].invoke(oClase,parameterTypes) );
                pList.append(sValue);
            }
            
            pList.append(",");
           }
     }
     
     pList.append(")");
     
     return pList;
   }
    
    /**
     * Devuelve el primer string que encuentre que no sea null
     */
    public static synchronized String notNull(String... cadena){
    	String notnull = null;
    	for (String s : cadena)
    		if (s!=null){
    			notnull = s;
    		}
    	return notnull;
    }
    
}
