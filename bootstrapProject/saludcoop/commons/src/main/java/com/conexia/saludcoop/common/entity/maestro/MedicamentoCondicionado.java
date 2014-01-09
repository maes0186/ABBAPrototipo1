package com.conexia.saludcoop.common.entity.maestro;

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
@Table(name = "medicamento_condicionado", schema = "maestros")
public class MedicamentoCondicionado extends BaseMaestro implements Identifiable<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "medicamento_id", nullable = false)
	private Medicamento medicamento;
	
	@ManyToOne
	@JoinColumn(name = "diagnostico_id", nullable = false)
	private Diagnostico diagnostico;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}
	
}
