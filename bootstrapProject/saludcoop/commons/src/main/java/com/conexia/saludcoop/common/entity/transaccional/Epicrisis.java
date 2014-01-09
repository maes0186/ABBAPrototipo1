package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

/*@Entity
@Table(name = "epicrisis", schema = "transaccional")*/
public class Epicrisis implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "motivo_consulta", nullable = false, length = 255)
	private String motivoConsulta;
	
	@Column(name = "enfermedad_actual", nullable = false, length = 255)
	private String enfermedadActual;
	
	@Column(name = "antecedentes", nullable = false, length = 255)
	private String antecedentes;
	
	@Column(name = "sintomas_generales", nullable = true, length = 255)
	private String sintomasGenerales;
	
	@Column(name = "sistema_respiratorio", nullable = true, length = 255)
	private String sistemaRespiratorio;
	
	@Column(name = "sistema_cardiovascular", nullable = true, length = 255)
	private String sistemaCardiovascular;
	
	@Column(name = "sistemaGastroDigestivo", nullable = true, length = 255)
	private String sistemaGastroDigestivo;
	
	@Column(name = "sistema_genitourinario", nullable = true, length = 255)
	private String sistemaGenitourinario;
	
	@Column(name = "sistema_endocrino", nullable = true, length = 255)
	private String sistemaEndocrino;
	
	@Column(name = "sistema_neurologico", nullable = true, length = 255)
	private String sistemaNeurologico;

	public Long getId() {
		return id;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getEnfermedadActual() {
		return enfermedadActual;
	}

	public void setEnfermedadActual(String enfermedadActual) {
		this.enfermedadActual = enfermedadActual;
	}

	public String getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	public String getSintomasGenerales() {
		return sintomasGenerales;
	}

	public void setSintomasGenerales(String sintomasGenerales) {
		this.sintomasGenerales = sintomasGenerales;
	}

	public String getSistemaRespiratorio() {
		return sistemaRespiratorio;
	}

	public void setSistemaRespiratorio(String sistemaRespiratorio) {
		this.sistemaRespiratorio = sistemaRespiratorio;
	}

	public String getSistemaCardiovascular() {
		return sistemaCardiovascular;
	}

	public void setSistemaCardiovascular(String sistemaCardiovascular) {
		this.sistemaCardiovascular = sistemaCardiovascular;
	}

	public String getSistemaGastroDigestivo() {
		return sistemaGastroDigestivo;
	}

	public void setSistemaGastroDigestivo(String sistemaGastroDigestivo) {
		this.sistemaGastroDigestivo = sistemaGastroDigestivo;
	}

	public String getSistemaGenitourinario() {
		return sistemaGenitourinario;
	}

	public void setSistemaGenitourinario(String sistemaGenitourinario) {
		this.sistemaGenitourinario = sistemaGenitourinario;
	}

	public String getSistemaEndocrino() {
		return sistemaEndocrino;
	}

	public void setSistemaEndocrino(String sistemaEndocrino) {
		this.sistemaEndocrino = sistemaEndocrino;
	}

	public String getSistemaNeurologico() {
		return sistemaNeurologico;
	}

	public void setSistemaNeurologico(String sistemaNeurologico) {
		this.sistemaNeurologico = sistemaNeurologico;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
