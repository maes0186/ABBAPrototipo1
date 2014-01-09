package com.conexia.saludcoop.validador.process;

import java.util.HashMap;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conexia.saludcoop.validador.util.StringUtilities;


/**
 * @author ADRIAN KAPPES
 */
public abstract class SequencedProcess implements Cloneable {

	private String processId;
	private String sequenceString;
	private String orderString;
	private int sequence;
	private int order;
	private int endingCode;
	private static Logger LOGGER = LoggerFactory.getLogger(SequencedProcess.class);
	
	/** Constructor */
    public SequencedProcess() {
        super();
     }    
 	
    /* **************************** ACCESSORS ***************************************** */
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String id) {
		this.processId = id;
	}
	public int getEndingCode() {
		return endingCode;
	}
	public String getEndingCodeAsString() {
		return StringUtilities.toRightAlignedFormat( String.valueOf( endingCode ), SequencesManager.ENDING_CODE_LENGTH, '0' ).toString();
	}
	public void setEndingCode(int endingCode) {
		this.endingCode = endingCode;
	}
	public int getOrder() {
		return order;
	}
	public String getOrderAsString() {
		return this.orderString;
	}
	public void setOrderFromString( String orderStr ) {
		this.orderString = orderStr;
		this.order = Integer.parseInt( orderStr );
	}
	public String getSequenceAsString() {
		return this.sequenceString;
	}
	public void setSequenceFromString( String sequence ) {
		this.sequenceString = sequence;
		this.sequence = Integer.parseInt( sequence );
	}
		
	/** Implementación del método clone() */
	public Object clone(){
		try{
			return super.clone();
			
		}catch( CloneNotSupportedException cnsEx ){
			return null;
		}
	}
	
    /**  Realiza la configuración del processor */   
	public void configure( Element processElement ) throws Exception {
		String id;
		
		try{
			id = processElement.getAttributeValue( "id" );
			if( (id == null) || ("".equals( id.trim() )) ){
				throw new Exception( "ERROR DE CONFIGURACION --> No se ha definido el atributo 'id' para el SequencedProcess" );	
			}else{
				this.setProcessId( StringUtilities.toRightAlignedFormat( id ,SequencesManager.PROCESS_ID_LENGTH, '0' ).toString() );
			}			
		}catch( Exception ex ){
			LOGGER.error(ex.toString());
		}
	}  
    
	/** Método invocado por el flowController
	 *   
	 *  @param aContext : Contexto que contiene informacion de la ejecucion y 
	 *  	el producto de los procesos anteriores
	 *  @param errorFree : indica como finalizo el proceso anterior, a partir de este valor 
	 *  	podemos decidir si debemos ejecutar, usualmente no lo hacemos.
	 * @throws Exception 
	 */
    public abstract int execute( HashMap<String, Object> context, boolean errorFree ) throws Exception;
}