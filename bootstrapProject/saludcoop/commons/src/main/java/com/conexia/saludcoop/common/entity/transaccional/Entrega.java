package com.conexia.saludcoop.common.entity.transaccional;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

@Entity
@Table(name = "entrega", schema = "transaccional")
public class Entrega implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "cantidad_entrega", nullable = false)
    private Integer cantidadEntrega;

    @Column(name = "fecha_inicio_vigencia", nullable = false)
    private Date fechaInicioVigencia;

    @Column(name = "fecha_fin_vigencia", nullable = false)
    private Date fechaFinVigencia;

    @Column(name = "fecha_activacion", nullable = false)
    private Date fechaActivacion;

    @ManyToOne
    @JoinColumn(name = "solicitud_medicamento_id", nullable = false)
    private SolicitudMedicamento solicitudMedicamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public SolicitudMedicamento getSolicitudMedicamento() {
        return solicitudMedicamento;
    }

    public void setSolicitudMedicamento(SolicitudMedicamento solicitudMedicamento) {
        this.solicitudMedicamento = solicitudMedicamento;
    }

    public Integer getCantidadEntrega() {
        return cantidadEntrega;
    }

    public void setCantidadEntrega(Integer cantidadEntrega) {
        this.cantidadEntrega = cantidadEntrega;
    }

}
