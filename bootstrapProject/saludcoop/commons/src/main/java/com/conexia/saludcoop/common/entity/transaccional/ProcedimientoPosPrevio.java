package com.conexia.saludcoop.common.entity.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.conexia.saludcoop.common.dto.transaccional.ProcedimientoPosPrevioDto;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.maestro.RespuestaClinicaObservada;

@Entity
@Table(name = "procedimiento_pos_previo", schema = "transaccional")
public class ProcedimientoPosPrevio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
	@JoinColumn(name="formulario_ctc_procedimiento_id", nullable=false)
	private FormularioCTCProcedimiento formularioCTCProcedimiento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Procedimiento procedimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "respuesta_clinica_observada_id")
    private RespuestaClinicaObservada respuestaClinicaObservada;

    public FormularioCTCProcedimiento getFormularioCTCProcedimiento() {
		return formularioCTCProcedimiento;
	}

	public void setFormularioCTCProcedimiento(
			FormularioCTCProcedimiento formularioCTCProcedimiento) {
		this.formularioCTCProcedimiento = formularioCTCProcedimiento;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(Procedimiento procedimiento) {
        this.procedimiento = procedimiento;
    }

    public RespuestaClinicaObservada getRespuestaClinicaObservada() {
        return respuestaClinicaObservada;
    }

    public void setRespuestaClinicaObservada(RespuestaClinicaObservada respuestaClinicaObservada) {
        this.respuestaClinicaObservada = respuestaClinicaObservada;
    }

    public ProcedimientoPosPrevioDto toDto() {
        ProcedimientoPosPrevioDto dto = new ProcedimientoPosPrevioDto();
        dto.setId(id);
        dto.setProcedimiento(procedimiento.toDto());
        dto.setRespuestaClinicaObservada(respuestaClinicaObservada.toDto());
        return dto;
    }
}
