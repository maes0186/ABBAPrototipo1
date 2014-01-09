package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conexia.saludcoop.common.dto.transaccional.FormulaInsumoDto;
import com.conexia.saludcoop.common.entity.maestro.CausaExterna;
import com.conexia.saludcoop.common.entity.maestro.Finalidad;
import com.conexia.saludcoop.common.entity.maestro.TipoCatastrofico;

@Entity
@Table(name="formula_item_insumo",schema="transaccional")
public class FormulaInsumo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name="solicitud_insumo_id", nullable=false)
	private SolicitudInsumo solicitudInsumo;
		
	@ManyToOne
	@JoinColumn(name = "causa_externa_id", nullable = false)
	private CausaExterna causaExterna;

	@ManyToOne
	@JoinColumn(name = "finalidad_id", nullable = false)
	private Finalidad finalidad;
	
	@ManyToOne
	@JoinColumn(name = "tipo_catastrofico_id", nullable = false)
	private TipoCatastrofico tipoCatastrofico;
	
	@Column(name="cantidad", nullable=false)
	private Integer cantidad;
	
	
	@Column(name="duracion", nullable=false)
	private Integer duracion;
	
	   
	

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	
	public SolicitudInsumo getSolicitudInsumo() {
		return solicitudInsumo;
	}

	public void setSolicitudInsumo(SolicitudInsumo solicitudInsumo) {
		this.solicitudInsumo = solicitudInsumo;
	}

	public FormulaInsumoDto toDto(){
	    FormulaInsumoDto dto = new FormulaInsumoDto();
	    dto.setCantidad(this.cantidad);
	    dto.setDuracion(this.duracion);
	    dto.setId(this.id);
	    dto.setTipoCatastrofico(this.tipoCatastrofico.getId());
	    return dto;
	}

	public CausaExterna getCausaExterna() {
		return causaExterna;
	}

	public void setCausaExterna(CausaExterna causaExterna) {
		this.causaExterna = causaExterna;
	}

	public Finalidad getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(Finalidad finalidad) {
		this.finalidad = finalidad;
	}

	public TipoCatastrofico getTipoCatastrofico() {
		return tipoCatastrofico;
	}

	public void setTipoCatastrofico(TipoCatastrofico tipoCatastrofico) {
		this.tipoCatastrofico = tipoCatastrofico;
	}

}
