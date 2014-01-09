package com.conexia.saludcoop.common.entity.transaccional;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.conexia.saludcoop.common.entity.maestro.Profesional;
import com.conexia.saludcoop.common.entity.maestro.Tarifario;

@Entity
@Table(name="consumo", schema="transaccional")
public class Consumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

    @Column(name="fecha_consumo",nullable= false )
	private Date fechaConsumo;
    
    @ManyToOne
    @JoinColumn(name="profesional_efector_id")
    private Profesional profesional;
    
    @Column(name="cantidad_consumida", nullable=false)
    private Integer cantidadConsumida;

    @Column(name="cuota_moderadora", nullable = false)
    private BigDecimal cuotaModeradora = new BigDecimal(0);
    
    @Column(name="copago", nullable = false)
    private BigDecimal copago = new BigDecimal(0);
    
    @Column(name="valor", nullable = false)
    private BigDecimal valor;
    
    @ManyToOne
    @JoinColumn(name="tarifario_id")
    private Tarifario tarifario;
    
    @ManyToOne
    @JoinColumn(name="solicitud_item_id", nullable=false)
    private SolicitudItem solicitudItem;
    
	public Date getFechaConsumo() {
		return fechaConsumo;
	}

	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Integer getCantidadConsumida() {
		return cantidadConsumida;
	}

	public void setCantidadConsumida(Integer cantidadConsumida) {
		this.cantidadConsumida = cantidadConsumida;
	}

	public Long getId() {
		return id;
	}

	public SolicitudItem getSolicitudItem() {
		return solicitudItem;
	}

	public void setSolicitudItem(SolicitudItem solicitudItem) {
		this.solicitudItem = solicitudItem;
	}

    public BigDecimal getCopago() {
    	return copago;
    }
	
    public void setCopago(BigDecimal copago) {
    	this.copago = copago;
    }

    public BigDecimal getValor() {
    	return valor;
    }
	
    public void setValor(BigDecimal valor) {
    	this.valor = valor;
    }
	
    public Tarifario getTarifario() {
    	return tarifario;
    }
	
    public void setTarifario(Tarifario tarifario) {
    	this.tarifario = tarifario;
    }
	
    public BigDecimal getCuotaModeradora() {
    	return cuotaModeradora;
    }
	
    public void setCuotaModeradora(BigDecimal cuotaModeradora) {
    	this.cuotaModeradora = cuotaModeradora;
    }
	
    public void setId(Long id) {
    	this.id = id;
    }
}
