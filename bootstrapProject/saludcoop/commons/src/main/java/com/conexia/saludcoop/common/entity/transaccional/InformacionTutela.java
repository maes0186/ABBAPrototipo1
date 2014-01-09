package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.conexia.saludcoop.common.entity.maestro.CausalAnulacion;
import com.conexia.saludcoop.common.entity.maestro.CausalDevolucion;

/**
 * Entidad para manejar la información para items de solicitud que hacen parte de una tutela
 * 
 * @author mortega
 * 
 */
@Entity
@Table(name = "informacion_tutela", schema = "transaccional")
public class InformacionTutela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "es_tutela_integral", nullable = true)
    private Boolean esTutelaIntegral;

    @Column(name = "excenta_copago", nullable = true)
    private Boolean exentaCopago;

    @Column(name = "numeroTutela", nullable = true)
    private String numeroTutela;

    @Column(name = "justificacion_concepto_ldf", nullable = true, length = 2000)
    private String justificacionConceptoLdf;

    @Column(name = "justificacion_concepto", nullable = true, length = 2000)
    private String justificacionConcepto;

    @Column(name = "justificacion_conexidad", nullable = true, length = 2000)
    private String justificacionConexidad;

    @Column(name = "numero_entregas", nullable = true)
    private Integer numeroEntregas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "causal_anulacion_id", nullable = true)
    private CausalAnulacion causalAnulacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "causal_devolucion_id", nullable = true)
    private CausalDevolucion causalDevolucion;

    @Column(name = "numero_fallo", nullable = true)
    private String numeroFallo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEsTutelaIntegral() {
        return esTutelaIntegral;
    }

    public void setEsTutelaIntegral(Boolean esTutelaIntegral) {
        this.esTutelaIntegral = esTutelaIntegral;
    }

    public Boolean getExentaCopago() {
        return exentaCopago;
    }

    public void setExentaCopago(Boolean exentaCopago) {
        this.exentaCopago = exentaCopago;
    }

    public String getNumeroTutela() {
        return numeroTutela;
    }

    public void setNumeroTutela(String numeroTutela) {
        this.numeroTutela = numeroTutela;
    }

    public String getJustificacionConcepto() {
        return justificacionConcepto;
    }

    public void setJustificacionConcepto(String justificacionConcepto) {
        this.justificacionConcepto = justificacionConcepto;
    }

    public String getJustificacionConexidad() {
        return justificacionConexidad;
    }

    public void setJustificacionConexidad(String justificacionConexidad) {
        this.justificacionConexidad = justificacionConexidad;
    }

    public Integer getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(Integer numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public CausalAnulacion getCausalAnulacion() {
        return causalAnulacion;
    }

    public void setCausalAnulacion(CausalAnulacion causalAnulacion) {
        this.causalAnulacion = causalAnulacion;
    }

    public CausalDevolucion getCausalDevolucion() {
        return causalDevolucion;
    }

    public void setCausalDevolucion(CausalDevolucion causalDevolucion) {
        this.causalDevolucion = causalDevolucion;
    }

    public String getJustificacionConceptoLdf() {
        return justificacionConceptoLdf;
    }

    public void setJustificacionConceptoLdf(String justificacionConceptoLdf) {
        this.justificacionConceptoLdf = justificacionConceptoLdf;
    }

	public String getNumeroFallo() {
		return numeroFallo;
	}

	public void setNumeroFallo(String numeroFallo) {
		this.numeroFallo = numeroFallo;
	}
}
