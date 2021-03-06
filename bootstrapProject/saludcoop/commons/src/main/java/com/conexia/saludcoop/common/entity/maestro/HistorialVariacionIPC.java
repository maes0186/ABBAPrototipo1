package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.conexia.saludcoop.common.dto.HistorialVariacionIPCDto;

@Entity
@Table(name = "historial_variacion_ipc", schema = "maestros")
public class HistorialVariacionIPC {

    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    
    
    @Column(name = "valor", nullable = false)
    private Double valor;
    
    /**
     * Año al que aplica el valor.
     */
    @Column(name = "anio", nullable = false)
    private Integer anio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    
    public HistorialVariacionIPCDto toDto(){
        HistorialVariacionIPCDto dto = new HistorialVariacionIPCDto();
        dto.setId(this.id);
        dto.setValor(this.valor);
        dto.setAnio(this.anio);        
        return dto;
    }

}
