package com.conexia.saludcoop.common.enumerator;
/**
 * 
 * @author mortega
 *
 */
public enum PeriodosVigencia {
	
	PERIODO_30(30),
	
	PERIODO_70(70),
	
	PERIODO_90(90);
	
	private Integer periodo;
	
	PeriodosVigencia(Integer periodo) {
		this.periodo = periodo;
	}
	
	public Integer getPeriodo(){
		return this.periodo;
	}
}
