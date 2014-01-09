package com.conexia.saludcoop.common.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;
import ar.com.conexia.generic.annotations.Mappeable;

import com.conexia.saludcoop.common.dto.UbicacionSedeIpsProveedorInsumoDto;

/**
 * Vista auxiliar para obtención de ubicación de sedes de IPS proveedoras de insumos.
 * 
 * @author Leonardo Cruz
 */
@Entity
@Table(name = "ubicacion_sede_ips_proveedor_insumo", schema = "vista")
@Mappeable(mappedTo = UbicacionSedeIpsProveedorInsumoDto.class)
public class UbicacionSedeIpsProveedorInsumo implements Identifiable<Long> {

    /**
     * Identificador de la entidad.
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    /**
     * Identificador de EPS.
     */
    @Column(name = "eps_id", nullable = false)
    private Long epsId;
    
    /**
     * Identificador de sede de IPS.
     */
    @Column(name = "sede_ips_id", nullable = false)
    private Long sedeIpsId;
    
    /**
     * Nombre de sede de IPS.
     */
    @Column(name = "sede_ips_nombre", nullable = false)
    private String sedeIpsNombre;
    
    /**
     * Identificador de municipio.
     */
    @Column(name = "municipio_id", nullable = false)
    private Long municipioId;
    
    /**
     * Identificador de seccional.
     */
    @Column(name = "division_seccional_id", nullable = false)
    private Long divisionSeccionalId;

    /**
     * Identificador de regional.
     */
    @Column(name = "regional_id", nullable = false)
    private Long regionalId;

    /**
     * Identificador del insumo.
     */
    @Column(name = "insumo_id", nullable = false)
    private Long insumoId;

    /**
     * Identificador del servicio.
     */
    @Column(name = "servicio_id", nullable = false)
    private Long servicioId;

    /**
     * Identificador de la especialidad.
     */
    @Column(name = "especialidad_id", nullable = false)
    private Integer especialidadId;

    @Override
    public Long getId() {
        
        return (this.id);
    }

    /**
     * Obtiene el identificador de la Eps.
     * 
     * @return Identificador de la Eps.
     */
    public Long getEpsId() {
        
        return (this.epsId);
    }

    /**
     * Asigna el identificador de la Eps.
     * 
     * @param epsId Identificador de la Eps.
     */
    public void setEpsId(final Long epsId) {
        
        this.epsId = epsId;
    }

    /**
     * Obtiene el identificador de la sede de Ips.
     * 
     * @return Identificador de la sede de Ips.
     */
    public Long getSedeIpsId() {
        
        return (this.sedeIpsId);
    }

    /**
     * Asigna el identificador de la sede de Ips.
     * 
     * @param sedeIpsId Identificador de la sede de Ips.
     */
    public void setSedeIpsId(final Long sedeIpsId) {
        
        this.sedeIpsId = sedeIpsId;
    }

    /**
     * Obtiene el nombre de la sede de Ips.
     * 
     * @return Nombre de la sede de Ips.
     */
    public String getSedeIpsNombre() {
        
        return (this.sedeIpsNombre);
    }

    /**
     * Asigna el nombre de la sede de Ips.
     * 
     * @param sedeIpsNombre Nombre de la sede de Ips.
     */
    public void setSedeIpsNombre(final String sedeIpsNombre) {
        
        this.sedeIpsNombre = sedeIpsNombre;
    }

    /**
     * Obtiene el identificador del municipio.
     * 
     * @return Identificador del municipio.
     */
    public Long getMunicipioId() {
        
        return (this.municipioId);
    }

    /**
     * Asigna el identificador del municipio.
     * 
     * @param municipioId Identificador del municipio.
     */
    public void setMunicipioId(final Long municipioId) {
        
        this.municipioId = municipioId;
    }

    /**
     * Obtiene el identificador de la división de seccional.
     * 
     * @return Identificador de la división de seccional.
     */
    public Long getDivisionSeccionalId() {
        
        return (this.divisionSeccionalId);
    }

    /**
     * Asigna el identificador de la división de seccional.
     * 
     * @param divisionSeccionalId Identificador de la división de seccional.
     */
    public void setDivisionSeccionalId(final Long divisionSeccionalId) {
        
        this.divisionSeccionalId = divisionSeccionalId;
    }

    /**
     * Obtiene el identificador de la regional.
     * 
     * @return Identificador de la regional.
     */
    public Long getRegionalId() {
        
        return (this.regionalId);
    }

    /**
     * Asigna el identificador de la regional.
     * 
     * @param regionalId Identificador de la regional.
     */
    public void setRegionalId(final Long regionalId) {
        
        this.regionalId = regionalId;
    }

    /**
     * Obtiene el identificador del insumo.
     * 
     * @return Identificador del insumo.
     */
    public Long getInsumoId() {
        
        return (this.insumoId);
    }

    /**
     * Asigna el identificador del insumo.
     * 
     * @param servicioId Identificador del insumo.
     */
    public void setInsumoId(final Long insumoId) {
        
        this.insumoId = insumoId;
    }

    /**
     * Obtiene el identificador del servicio.
     * 
     * @return Identificador del servicio.
     */
    public Long getServicioId() {
        
        return (this.servicioId);
    }

    /**
     * Asigna el identificador del servicio.
     * 
     * @param servicioId Identificador del servicio.
     */
    public void setServicioId(final Long servicioId) {
        
        this.servicioId = servicioId;
    }

    /**
     * Obtiene el identificador de la especialidad.
     * 
     * @return Identificador de la especialidad.
     */
    public Integer getEspecialidadId() {
        
        return (this.especialidadId);
    }

    /**
     * Asigna el identificador de la especialidad.
     * 
     * @param especialidadId Identificador de la especialidad.
     */
    public void setEspecialidadId(final Integer especialidadId) {
        
        this.especialidadId = especialidadId;
    }
    
    /**
     * Obtiene el DTO que representa a esta entidad.
     * 
     * @return DTO que representa a esta entidad.
     */
    public UbicacionSedeIpsProveedorInsumoDto toDto() {
        
        final UbicacionSedeIpsProveedorInsumoDto dto = new UbicacionSedeIpsProveedorInsumoDto();
        dto.setEpsId(this.getEpsId());
        dto.setSedeIpsId(this.getSedeIpsId());
        dto.setSedeIpsNombre(this.getSedeIpsNombre());
        dto.setMunicipioId(this.getMunicipioId());
        dto.setDivisionSeccionalId(this.getDivisionSeccionalId());
        dto.setRegionalId(this.getRegionalId());
        dto.setInsumoId(this.getInsumoId());
        dto.setServicioId(this.getServicioId());
        dto.setEspecialidadId(this.getEspecialidadId());

        return (dto);
    }
}
