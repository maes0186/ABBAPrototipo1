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

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.entity.maestro.Medicamento;

@Entity
@Table(name = "solicitud_medicamento", schema = "transaccional")
public class SolicitudMedicamento implements Identifiable<Long> {

	
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
	@JoinColumn(name="medicamento_id", nullable=false)
	private Medicamento medicamento;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="solicitudMedicamento" )
	private FormularioCTCMedicamento formCTCMedicamento;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="solicitudMedicamento")
	private FormulaMedicamento formulaMedicamento;

    @OneToMany(mappedBy = "solicitudMedicamento") 
    private Set<Entrega> entregas = new HashSet<Entrega>();

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public FormularioCTCMedicamento getFormCTCMedicamento() {
		return formCTCMedicamento;
	}

	public void setFormCTCMedicamento(FormularioCTCMedicamento formCTCMedicamento) {
		this.formCTCMedicamento = formCTCMedicamento;
		this.formCTCMedicamento.setSolicitudMedicamento(this);
	}

	public FormulaMedicamento getFormulaMedicamento() {
		return formulaMedicamento;
	}

	public void setFormulaMedicamento(FormulaMedicamento formulaMedicamento) {
		this.formulaMedicamento = formulaMedicamento;
		this.formulaMedicamento.setSolicitudMedicamento(this);
	}

	public SolicitudItem getSolicitudItem() {
		return solicitudItem;
	}

	public void setSolicitudItem(SolicitudItem solicitudItem) {
		this.solicitudItem = solicitudItem;
	}

    public Set<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(Set<Entrega> entregas) {
        this.entregas = entregas;
    }

    public void addEntrega(Entrega entrega) {
        if(entregas == null){
            entregas = new HashSet<Entrega>();
        }
        entregas.add(entrega);
        entrega.setSolicitudMedicamento(this);
    }
	
}
