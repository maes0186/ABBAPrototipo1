package com.conexia.saludcoop.common.dto.transaccional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.EspecialidadDto;
import com.conexia.saludcoop.common.dto.ServicioDto;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;

public class SolicitudItemDto {

	private Long nroItem;
	private Integer cantidad;
	private SolicitudDiagnosticoDto diagnostico;
	private SolicitudMedicamentoDto solMedicamento;
	private SolicitudProcedimientoDto solProcedimiento;
	private SolicitudInsumoDto solInsumo;
	private DescriptivoDto tipoPPM;
	private SolicitudDto solicitud;
	private Integer tipoServicio;
	private PreAutorizacionDto preAutorizacion;
	private AutorizacionDto autorizacion;
	private Date fechaConsumo;
	private Long sedeIpsEfectora;
	private Integer tipoTecnologia;
	private Boolean aplicaTutela;
	private ConsumoDto consumo;
	private List<DocumentoSoporteDto> formularioCTCAdjunto;
    private String mensajeValidacion;
    private Boolean superaTopes; 
	/**
	 * Copago estimado que debería abonarse por el ítem.
	 */
	private BigDecimal copagoEstimado;

	public Long getNroItem() {
		return nroItem;
	}

	public void setNroItem(Long nroItem) {
		this.nroItem = nroItem;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public SolicitudDiagnosticoDto getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(SolicitudDiagnosticoDto diagnostico) {
		this.diagnostico = diagnostico;
	}

	public SolicitudMedicamentoDto getSolMedicamento() {
		return solMedicamento;
	}

	public void setSolMedicamento(SolicitudMedicamentoDto solMedicamento) {
		this.solMedicamento = solMedicamento;
	}

	public SolicitudProcedimientoDto getSolProcedimiento() {
		return solProcedimiento;
	}

	public void setSolProcedimiento(SolicitudProcedimientoDto solProcedimiento) {
		this.solProcedimiento = solProcedimiento;
	}

	public DescriptivoDto getTipoPPM() {
		return tipoPPM;
	}

	public void setTipoPPM(DescriptivoDto tipoPPM) {
		this.tipoPPM = tipoPPM;
	}

	public SolicitudDto getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudDto solicitud) {
		this.solicitud = solicitud;
	}

	public Integer getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(Integer tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public PreAutorizacionDto getPreAutorizacion() {
		return preAutorizacion;
	}

	public void setPreAutorizacion(PreAutorizacionDto preAutorizacion) {
		this.preAutorizacion = preAutorizacion;
	}

	public AutorizacionDto getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(AutorizacionDto autorizacion) {
		this.autorizacion = autorizacion;
	}

	public Date getFechaConsumo() {
		return fechaConsumo;
	}

	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}

	public Long getSedeIpsEfectora() {
		return sedeIpsEfectora;
	}

	public void setSedeIpsEfectora(Long sedeIpsEfectora) {
		this.sedeIpsEfectora = sedeIpsEfectora;
	}

	@JsonIgnore
	public boolean isMedicamento() {
		return this.getSolMedicamento() != null;
	}

	@JsonIgnore
	public boolean isProcedimiento() {

		return this.getSolProcedimiento() != null;
	}

	public Integer getTipoTecnologia() {
		return tipoTecnologia;
	}

	public void setTipoTecnologia(Integer tipoTecnologia) {
		this.tipoTecnologia = tipoTecnologia;
	}

	/**
	 * Obtiene el tipo de pago requerido del ítem, en lo referido al elemento
	 * contenido.
	 * 
	 * @return Tipo de pago requerido del ítem, en lo referido al elemento
	 *         contenido.
	 */
	@JsonIgnore
	public TipoPagoRequerido getTipoPagoRequerido() {

		if (this.isMedicamento() && this.getSolMedicamento().getMedicamento() != null) {
			return (this.getSolMedicamento().getMedicamento().getTipoPagoRequerido());
		} else if (this.isProcedimiento() && this.getSolProcedimiento().getProcedimiento() != null) {
			return (this.getSolProcedimiento().getProcedimiento().getTipoPagoRequerido());
		} else if (this.isInsumo() && this.getSolInsumo().getInsumo() != null) {
			return (this.getSolInsumo().getInsumo().getTipoPagoRequerido());
		}

		return (null);
	}

	/**
	 * Obtiene el identificador del ítem, en lo referido al elemento contenido.
	 * 
	 * @return Identificador del ítem, en lo referido al elemento contenido.
	 */
	@JsonIgnore
	public Long getItemId() {

		if (this.isMedicamento() && this.getSolMedicamento().getMedicamento() != null) {
			return (this.getSolMedicamento().getMedicamento().getId());
		} else if (this.isProcedimiento() && this.getSolProcedimiento().getProcedimiento() != null) {
			return (this.getSolProcedimiento().getProcedimiento().getId());
		} else if (this.isInsumo() && this.getSolInsumo().getInsumo() != null) {
			return (this.getSolInsumo().getInsumo().getId());
		}

		return (null);
	}

	/**
	 * Obtiene el copago estimado para el ítem.
	 * 
	 * @return Copago estimado para el ítem.
	 */
	@JsonIgnore
	public BigDecimal getCopagoEstimado() {

		return (this.copagoEstimado);
	}

	/**
	 * Asigna el copago estimado para el ítem.
	 * 
	 * @param copagoEstimado
	 *            Copago estimado para el ítem.
	 */
	@JsonIgnore
	public void setCopagoEstimado(final BigDecimal copagoEstimado) {

		this.copagoEstimado = copagoEstimado;
	}

	/**
	 * Obtiene la especialidad, en lo referido al elemento contenido.
	 * 
	 * @return Especialidad, en lo referido al elemento contenido.
	 */
	@JsonIgnore
	public EspecialidadDto getEspecialidadItem() {

		if (this.isMedicamento() && this.getSolMedicamento() != null) {
			/*
			 * TODO Pendiente definir la especialidad de dónde se obtiene en el
			 * medicamento
			 */
			return (new EspecialidadDto());
		} else if (this.isProcedimiento() && this.getSolProcedimiento() != null) {
			return (this.getSolProcedimiento().getEspecialidad());
		}

		return (null);
	}

	/**
	 * Obtiene el servicio, en lo referido al elemento contenido.
	 * 
	 * @return Servicio, en lo referido al elemento contenido.
	 */
	@JsonIgnore
	public ServicioDto getServicioItem() {

		if (this.isMedicamento() && this.getSolMedicamento() != null) {
			/*
			 * TODO Pendiente definir el servicio de dónde se obtiene en el
			 * medicamento
			 */
			return (new ServicioDto());
		} else if (this.isProcedimiento() && this.getSolProcedimiento() != null) {
			/*
			 * TODO Pendiente definir el servicio de dónde se obtiene en el
			 * procedimiento
			 */
			return (new ServicioDto());
		}

		return (null);
	}

	public Boolean getAplicaTutela() {
		return aplicaTutela;
	}

	public void setAplicaTutela(Boolean aplicaTutela) {
		this.aplicaTutela = aplicaTutela;
	}

	public ConsumoDto getConsumo() {
		return consumo;
	}

	public void setConsumo(ConsumoDto consumo) {
		this.consumo = consumo;
	}

	public List<DocumentoSoporteDto> getFormularioCTCAdjunto() {
		return formularioCTCAdjunto;
	}

	public void setFormularioCTCAdjunto(List<DocumentoSoporteDto> formularioCTCAdjunto) {
		this.formularioCTCAdjunto = formularioCTCAdjunto;
	}

	public void addFormularioAdjunto(DocumentoSoporteDto doc) {
		if (doc != null) {
			if (this.formularioCTCAdjunto == null) {
				this.formularioCTCAdjunto = new Vector<DocumentoSoporteDto>();
			}
			this.formularioCTCAdjunto.add(doc);
		}
	}

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

	public SolicitudInsumoDto getSolInsumo() {
		return solInsumo;
	}

	public void setSolInsumo(SolicitudInsumoDto solInsumo) {
		this.solInsumo = solInsumo;
	}
	
	@JsonIgnore
	public boolean isInsumo() {
		return this.getSolInsumo() != null;
	}

    public Boolean getSuperaTopes() {
        return superaTopes;
    }

    public void setSuperaTopes(Boolean superaTopes) {
        this.superaTopes = superaTopes;
    }	
		
}
