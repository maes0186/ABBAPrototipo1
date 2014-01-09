package com.conexia.saludcoop.common.entity.maestro;

import static com.conexia.saludcoop.common.util.SystemConstants.SHORT_FALSE;
import static com.conexia.saludcoop.common.util.SystemConstants.SHORT_TRUE;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;

@Entity
@Table(name = "medicamento", schema = "maestros")
public class Medicamento extends BaseMaestro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1766590015842029854L;

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Index(name="ix_medicamento_codigo")
	@Column(name = "codigo", nullable = false, length= 20)
	private String codigo;
	
	@Index(name="ix_medicamento_descripcion")
	@Column(name = "descripcion", nullable = false, length= 1500)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="tipo_ppm_id", nullable = false)
	@ForeignKey(name="fk_medicamento_tipo_ppm")
	private TipoPPM tipo;

	
	@ManyToOne
	@JoinColumn(name="estado_medicamento_id", nullable = false)
	@ForeignKey(name="fk_medicamento_estado_medicamento")
	private EstadoProcedimiento estadoMedicamento;
	
	@Column(name = "cantidad_maxima_autorizada")
	private Integer cantidadMaximaAutorizada;

	@Column(name = "ac_menor_c")
	private Integer acMenorC;
	
	@Column(name = "controlados")
	private Short controlados;
	
	@Column(name = "nivel_autorizacion")
	private Short nivelAutorizacion;
	
	@ManyToOne
	@JoinColumn(name="homologo_id")
	@ForeignKey(name="fk_medicamento_homologo")
	private Medicamento homologo;
	
	
	@ManyToOne
	@JoinColumn(name="programa_medicamento_alto_costo_id", nullable = true)
	@ForeignKey(name="fk_medicamento_programa_medicamento_alto_costo")
	private ProgramaMedicamentoAltoCosto programaMedicamentoAltoCosto;
	
	@Column(name = "alto_costo")	
	private Short altoCosto;
	
	@Column(name = "nombre_alterno", length=255)	
	private String nombreAlterno;
	
	@Column(name="es_comercial")
	private Short comercial;
	
	@Column(name = "es_proveeduria", nullable = false)
	private Boolean proveeduria;
	
	/**
	 * Tipo de pago requerido.
	 */
	@Column(name="tipo_pago_requerido_enum", nullable = false)
	private Long tipoPagoRequeridoId;
	
	@OneToMany(fetch = FetchType.LAZY ,mappedBy="medicamento")
	private Set<EspecialidadMedicamento> especialidades = new HashSet<EspecialidadMedicamento>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="medicamento")
	private Set<DiagnosticoMedicamento> diagnosticos = new HashSet<DiagnosticoMedicamento>();
	
	@Column(name = "visible_ctc")	
	private Short visibleCtc;
	
	@Column(name="es_p_y_pp", nullable = false)
	private Short esPyPP;
	
	@ManyToOne
	@JoinColumn( name="genero_id", nullable = true)
	@ForeignKey(name="fk_procedimiento_genero")
	private Genero genero;

	@Column(name="suministra_medicarte", nullable = false)
	private Short suministraMedicarte;
	
	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;

	@Column(name="es_insumo", nullable = true)
	private Short insumo;
	
	@Column(name="es_quirurgico", nullable = false)
	private Short quirurgico;	

    @Column(name="es_epsifarma", nullable = true)
    private Short epsifarma;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public TipoPPM getTipoPPM() {
		return tipo;
	}

	public void setTipoPPM(TipoPPM tipoPPM) {
		this.tipo = tipoPPM;
	}


	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public MedicamentoDto toDto() {
		MedicamentoDto dto = new MedicamentoDto();
		dto.setId(this.id);
		dto.setCodigo(this.codigo);
		dto.setDescripcion(this.descripcion);
		dto.setTipoPPM(this.getTipoPPM().toDto());
		dto.setVisibleCtc(getVisibleCtc());
		dto.setAltoCosto(getAltoCosto());
		dto.setTipoPagoRequerido(this.getTipoPagoRequerido());
		
		return dto;
	}

	public Integer getClientePk() {
		return clientePk;
	}

	public void setClientePk(Integer clientePk) {
		this.clientePk = clientePk;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Short getEsPyPP() {
		return esPyPP;
	}

	public void setEsPyPP(Short esPyPP) {
		this.esPyPP = esPyPP;
	}

	public EstadoProcedimiento getEstadoMedicamento() {
		return estadoMedicamento;
	}

	public void setEstadoMedicamento(EstadoProcedimiento estadoMedicamento) {
		this.estadoMedicamento = estadoMedicamento;
	}

	public Short getControlados() {
		return controlados;
	}

	public void setControlados(Short controlados) {
		this.controlados = controlados;
	}

	public Medicamento getHomologo() {
		return homologo;
	}

	public void setHomologo(Medicamento homologo) {
		this.homologo = homologo;
	}

	public ProgramaMedicamentoAltoCosto getProgramaMedicamentoAltoCosto() {
		return programaMedicamentoAltoCosto;
	}

	public void setProgramaMedicamentoAltoCosto(ProgramaMedicamentoAltoCosto programaMedicamentoAltoCosto) {
		this.programaMedicamentoAltoCosto = programaMedicamentoAltoCosto;
	}

	public Boolean getAltoCosto() {
		return altoCosto==SHORT_TRUE?true:false;
	}

	public void setAltoCosto(Boolean altoCosto) {
		this.altoCosto = altoCosto?SHORT_TRUE:SHORT_FALSE;;
	}

	public String getNombreAlterno() {
		return nombreAlterno;
	}

	public void setNombreAlterno(String nombreAlterno) {
		this.nombreAlterno = nombreAlterno;
	}

	



	public Boolean getVisibleCtc() {
		return visibleCtc==SHORT_TRUE?true:false;
	}

	public void setVisibleCtc(Boolean visibleCtc) {
		this.visibleCtc = visibleCtc?SHORT_TRUE:SHORT_FALSE;
	}

	public Integer getAcMenorC() {
		return acMenorC;
	}

	public void setAcMenorC(Integer acMenorC) {
		this.acMenorC = acMenorC;
	}



	public Integer getCantidadMaximaAutorizada() {
		return cantidadMaximaAutorizada;
	}

	public void setCantidadMaximaAutorizada(Integer cantidadMaximaAutorizada) {
		this.cantidadMaximaAutorizada = cantidadMaximaAutorizada;
	}
	
    public Boolean getSuministraMedicarte() {
    	return suministraMedicarte==0?false:true;
    }

    public void setSuministraMedicarte(Boolean suministraMedicarte) {
    	this.suministraMedicarte = suministraMedicarte?Short.valueOf("1"):Short.valueOf("0");
    }

	public TipoPPM getTipo() {
		return tipo;
	}

	public void setTipo(TipoPPM tipo) {
		this.tipo = tipo;
	}

	public Set<EspecialidadMedicamento> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<EspecialidadMedicamento> especialidades) {
		this.especialidades = especialidades;
	}

	public Set<DiagnosticoMedicamento> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(Set<DiagnosticoMedicamento> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	/**
	 * Obtiene el tipo de pago requerido.
	 * 
	 * @return Tipo de pago requerido.
	 */
	public TipoPagoRequerido getTipoPagoRequerido() {
		
		return (TipoPagoRequerido.fromId(this.tipoPagoRequeridoId));
	}

	/**
	 * Asigna el tipo de pago requerido.
	 * 
	 * @param tipoPagoRequerido Tipo de pago requerido.
	 */
	public void setTipoPagoRequerido(final TipoPagoRequerido tipoPagoRequerido) {
		
		if (tipoPagoRequerido != null) {
			this.tipoPagoRequeridoId = tipoPagoRequerido.getId();
		} else {
			this.tipoPagoRequeridoId = null;
		}
	}

	public Short getInsumo() {
		return insumo;
	}

	public void setInsumo(Short insumo) {
		this.insumo = insumo;
	}

	public Short getNivelAutorizacion() {
		return nivelAutorizacion;
	}

	public void setNivelAutorizacion(Short nivelAutorizacion) {
		this.nivelAutorizacion = nivelAutorizacion;
	}

	public Short getComercial() {
		return comercial;
	}

	public void setComercial(Short comercial) {
		this.comercial = comercial;
	}
	
	public Boolean getQuirurgico() {
        return quirurgico==SHORT_TRUE?true:false;
    }

    public void setQuirurgico(Boolean quirurgico) {
        this.quirurgico = quirurgico?SHORT_TRUE:SHORT_FALSE;
    }
    
    public Boolean getEpsifarma() {
        return epsifarma==SHORT_TRUE?true:false;
    }

    public void setEpsifarma(Boolean epsifarma) {
        this.epsifarma = epsifarma?SHORT_TRUE:SHORT_FALSE;
    }

	public Boolean getProveeduria() {
		return proveeduria;
	}

	public void setProveeduria(Boolean proveeduria) {
		this.proveeduria = proveeduria;
	}
}
