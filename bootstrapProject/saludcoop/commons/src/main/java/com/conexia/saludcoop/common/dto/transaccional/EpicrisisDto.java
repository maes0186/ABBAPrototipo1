package com.conexia.saludcoop.common.dto.transaccional;


public class EpicrisisDto { 
	private Long id;
	private String motivoConsulta;
	private String enfermedadActual;
	private String antecedentes;
	private String sintomasGenerales;
	private String sistemaRespiratorio;
	private String sistemaCardiovascular;
	private String sistemaGastroDigestivo;
	private String sistemaGenitourinario;
	private String sistemaEndocrino;
	private String sistemaNeurologico;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}
	public void setMotivoConsulta(String motivoConsulta){
		this.motivoConsulta = motivoConsulta;
	}

	public String getMotivoConsulta(){
		return this.motivoConsulta;
	}
	public void setEnfermedadActual(String enfermedadActual){
		this.enfermedadActual = enfermedadActual;
	}

	public String getEnfermedadActual(){
		return this.enfermedadActual;
	}
	public void setAntecedentes(String antecedentes){
		this.antecedentes = antecedentes;
	}

	public String getAntecedentes(){
		return this.antecedentes;
	}
	public void setSintomasGenerales(String sintomasGenerales){
		this.sintomasGenerales = sintomasGenerales;
	}

	public String getSintomasGenerales(){
		return this.sintomasGenerales;
	}
	public void setSistemaRespiratorio(String sistemaRespiratorio){
		this.sistemaRespiratorio = sistemaRespiratorio;
	}

	public String getSistemaRespiratorio(){
		return this.sistemaRespiratorio;
	}
	public void setSistemaCardiovascular(String sistemaCardiovascular){
		this.sistemaCardiovascular = sistemaCardiovascular;
	}

	public String getSistemaCardiovascular(){
		return this.sistemaCardiovascular;
	}
	public void setSistemaGastroDigestivo(String sistemaGastroDigestivo){
		this.sistemaGastroDigestivo = sistemaGastroDigestivo;
	}

	public String getSistemaGastroDigestivo(){
		return this.sistemaGastroDigestivo;
	}
	public void setSistemaGenitourinario(String sistemaGenitourinario){
		this.sistemaGenitourinario = sistemaGenitourinario;
	}

	public String getSistemaGenitourinario(){
		return this.sistemaGenitourinario;
	}
	public void setSistemaEndocrino(String sistemaEndocrino){
		this.sistemaEndocrino = sistemaEndocrino;
	}

	public String getSistemaEndocrino(){
		return this.sistemaEndocrino;
	}
	public void setSistemaNeurologico(String sistemaNeurologico){
		this.sistemaNeurologico = sistemaNeurologico;
	}

	public String getSistemaNeurologico(){
		return this.sistemaNeurologico;
	}

}
