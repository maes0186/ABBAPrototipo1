package com.conexia.saludcoop.common.entity.transaccional;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.conexia.saludcoop.common.entity.maestro.CausalAnulacion;
import com.conexia.saludcoop.common.entity.maestro.CausalDevolucion;
import com.conexia.saludcoop.common.entity.maestro.CriterioNegacion;
import com.conexia.saludcoop.common.entity.maestro.Lateralidad;

/**
 * Entidad para manejar los conceptos de autorizacion
 * 
 * @author mortega
 * 
 */
@Entity
@Table(name = "concepto_autorizacion", schema = "transaccional")
public class ConceptoAutorizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "periodo_aprobado", nullable = true)
    private Integer periodoAprobado;

    @Column(name = "dias_x_periodo", nullable = true)
    private Integer diasXperiodo;

    @Column(name = "unidades_aprobadas", nullable = true)
    private Integer unidadesAprobadas;

    @Column(name = "dosis_aprobadas", nullable = true)
    private Integer dosisAprobadas;
    
    @Column(name = "justificacion", nullable = true, length = 2000)
    private String justificacion;

    @Column(name = "numero_entregas", nullable = true)
    private Integer numeroEntregas;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "con_autorizacion_cri_negacion", schema="transaccional",  joinColumns = { 
    @JoinColumn(name = "concepto_autorizacion_id", nullable = false, updatable = false) }, 
    inverseJoinColumns = { @JoinColumn(name = "criterio_negacion_id", 
    nullable = false, updatable = false) })
    private Set<CriterioNegacion> criteriosNegacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "causal_anulacion_id", nullable = true)
    private CausalAnulacion causalAnulacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "causal_devolucion_id", nullable = true)
    private CausalDevolucion causalDevolucion;

    @ManyToOne
    @JoinColumn(name="lateralidad_id", nullable = true)
    private Lateralidad lateralidad;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPeriodoAprobado() {
        return periodoAprobado;
    }

    public void setPeriodoAprobado(Integer periodoAprobado) {
        this.periodoAprobado = periodoAprobado;
    }

    public Integer getDiasXperiodo() {
        return diasXperiodo;
    }

    public void setDiasXperiodo(Integer diasXperiodo) {
        this.diasXperiodo = diasXperiodo;
    }

    public Integer getUnidadesAprobadas() {
        return unidadesAprobadas;
    }

    public void setUnidadesAprobadas(Integer unidadesAprobadas) {
        this.unidadesAprobadas = unidadesAprobadas;
    }

    public Integer getDosisAprobadas() {
        return dosisAprobadas;
    }

    public void setDosisAprobadas(Integer dosisAprobadas) {
        this.dosisAprobadas = dosisAprobadas;
    }

    public Integer getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(Integer numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public Set<CriterioNegacion> getCriteriosNegacion() {
        return criteriosNegacion;
    }

    public void setCriteriosNegacion(Set<CriterioNegacion> criteriosNegacion) {
        this.criteriosNegacion = criteriosNegacion;
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

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Lateralidad getLateralidad() {
        return lateralidad;
    }

    public void setLateralidad(Lateralidad lateralidad) {
        this.lateralidad = lateralidad;
    }
}
