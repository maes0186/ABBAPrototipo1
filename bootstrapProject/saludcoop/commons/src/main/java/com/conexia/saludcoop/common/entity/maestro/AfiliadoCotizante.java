package com.conexia.saludcoop.common.entity.maestro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "afiliado_cotizante", schema = "maestros")
@Entity
public class AfiliadoCotizante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cotizante_id", nullable = false)
	private Afiliado cotizante;
	
	@ManyToOne
	@JoinColumn(name = "afiliado_id", nullable = false)
	private Afiliado afiliado;

	public Integer getId() {
		return id;
	}

	public Afiliado getCotizante() {
		return cotizante;
	}

	public void setCotizante(Afiliado cotizante) {
		this.cotizante = cotizante;
	}

	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

}
