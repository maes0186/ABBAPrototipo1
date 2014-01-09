package com.conexia.saludcoop.common.dto;



public class RespuestaCompuesta<T> {
	private T transactionData;
	private RespuestaDto respuestaDto;
	public T getTransactionData() {
		return transactionData;
	}
	
	

	public void setTransactionData(T transactionData) {
		this.transactionData = transactionData;
	}

	public RespuestaDto getRespuestaDto() {
		return respuestaDto;
	}

	public void setRespuestaDto(RespuestaDto respuestaDto) {
		this.respuestaDto = respuestaDto;
	}
	

}

