package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.conexia.saludcoop.common.dto.InsumoTopeDto;
import com.conexia.saludcoop.common.enumerator.TipoInsumo;

/**
 * Tope en los insumos
 * @author dprieto
 *
 */
@Entity
@Table(name = "insumo_tope", schema = "maestros")
public class InsumoTope {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn( nullable = false)
    private Insumo insumo;
    
    @Column(nullable = false)
    private Integer tope;
    
    @Column(name = "periodicidad_dias", nullable = false)
    private Integer periodicidadDias;
    
    /**
     * tipo de insumo
     */
    @Column( name="tipo_insumo_enum", nullable = false)
    private Long tipoInsumoId;
    
    
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTope() {
        return tope;
    }

    public void setTope(Integer tope) {
        this.tope = tope;
    }

    public Integer getPeriodicidadDias() {
        return periodicidadDias;
    }

    public void setPeriodicidadDias(Integer periodicidadDias) {
        this.periodicidadDias = periodicidadDias;
    }

    /**
     * Obtiene el tipo de insumo.
     * 
     * @return Tipo de insumo.
     */
    public TipoInsumo getTipoInsumo() {
        
        return (TipoInsumo.fromId(this.tipoInsumoId));
    }

    /**
     * Asigna el tipo de insumo.
     * 
     * @param tipoInsumo Tipo de insumo.
     */
    public void setTipoInsumo(final TipoInsumo tipoInsumo) {
        
        if (tipoInsumo != null) {
            this.tipoInsumoId = tipoInsumo.getId();
        } else {
            this.tipoInsumoId = null;
        }
    }

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	
	public InsumoTopeDto toDto(){
        InsumoTopeDto dto = new InsumoTopeDto();
        dto.setId(this.id);
        dto.setInsumo(this.insumo.toDto());
        dto.setPeriodicidadDias(this.periodicidadDias);
        dto.setTipoInsumoId(this.tipoInsumoId);
        dto.setTope(this.tope);
        return dto;
    }


}
