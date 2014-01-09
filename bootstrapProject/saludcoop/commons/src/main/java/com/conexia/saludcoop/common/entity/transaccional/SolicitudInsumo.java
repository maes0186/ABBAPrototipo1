package com.conexia.saludcoop.common.entity.transaccional;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conexia.saludcoop.common.entity.maestro.Insumo;
import com.conexia.saludcoop.common.entity.maestro.Insumo;

@Entity
@Table(name = "solicitud_insumo", schema = "transaccional")
public class SolicitudInsumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	public Long getId() {
		return id;
	}
	
	@OneToOne
	@JoinColumn(name="solicitud_item_id")
	private SolicitudItem solicitudItem;
	
	@ManyToOne
	@JoinColumn(name="insumo_id", nullable=false)
	private Insumo insumo;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="solicitudInsumo" )
	private FormularioCTCInsumo formCTCInsumo;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="solicitudInsumo")
	private FormulaInsumo formulaInsumo;

//    @OneToMany(mappedBy = "solicitudInsumo") 
//    private Set<Entrega> entregas = new HashSet<Entrega>();
    

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public FormularioCTCInsumo getFormCTCInsumo() {
		return formCTCInsumo;
	}

	public void setFormCTCInsumo(FormularioCTCInsumo formCTCInsumo) {
		this.formCTCInsumo = formCTCInsumo;
		this.formCTCInsumo.setSolicitudInsumo(this);
	}

	public FormulaInsumo getFormulaInsumo() {
		return formulaInsumo;
	}

	public void setFormulaInsumo(FormulaInsumo formulaInsumo) {
		this.formulaInsumo = formulaInsumo;
		this.formulaInsumo.setSolicitudInsumo(this);
	}

	public SolicitudItem getSolicitudItem() {
		return solicitudItem;
	}

	public void setSolicitudItem(SolicitudItem solicitudItem) {
		this.solicitudItem = solicitudItem;
	}

//    public Set<Entrega> getEntregas() {
//        return entregas;
//    }
//
//    public void setEntregas(Set<Entrega> entregas) {
//        this.entregas = entregas;
//    }

//    public void addEntrega(Entrega entrega) {
//        if(entregas == null){
//            entregas = new HashSet<Entrega>();
//        }
//        entregas.add(entrega);
//        entrega.setSolicitudInsumo(this);
//    }

}
