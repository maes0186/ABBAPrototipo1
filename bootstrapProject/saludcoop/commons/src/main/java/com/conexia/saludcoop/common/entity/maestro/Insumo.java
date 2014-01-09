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

import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;

@Entity
@Table(name = "insumo", schema = "maestros")
public class Insumo extends BaseMaestro implements Serializable {
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
	
	@Index(name="ix_insumo_codigo")
	@Column(name = "codigo", nullable = false, length= 20)
	private String codigo;
	
	@Index(name="ix_insumo_descripcion")
	@Column(name = "descripcion", nullable = false, length= 1500)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="tipo_ppm_id", nullable = false)
	@ForeignKey(name="fk_insumo_tipo_ppm")
	private TipoPPM tipo;

	
	@ManyToOne
	@JoinColumn(name="estado_insumo_id", nullable = false)
	@ForeignKey(name="fk_insumo_estado_insumo")
	private EstadoProcedimiento estadoInsumo;
	
	@Column(name = "cantidad_maxima_autorizada", nullable = true)
	private Integer cantidadMaximaAutorizada;

	@Column(name = "ac_menor_c", nullable = true)
	private Integer acMenorC;
	
	@Column(name = "controlados", nullable = true)
	private Short controlados;
	
	@Column(name = "nivel_autorizacion", nullable = true)
	private Short nivelAutorizacion;
	
	@ManyToOne
	@JoinColumn(name="homologo_id", nullable = true)
	@ForeignKey(name="fk_insumo_homologo")
	private Insumo homologo;
	
	
	@ManyToOne
	@JoinColumn(name="programa_alto_costo_id", nullable = true)
	@ForeignKey(name="fk_insumo_programa_insumo_alto_costo")
	private ProgramaMedicamentoAltoCosto programaAltoCosto;
	
	@Column(name = "alto_costo", nullable = true)	
	private Short altoCosto;
	
	@Column(name = "nombre_alterno", length=255)	
	private String nombreAlterno;
	
	/**
	 * Tipo de pago requerido.
	 */
	@Column(name="tipo_pago_requerido_enum", nullable = false)
	private Long tipoPagoRequeridoId;
	
	@OneToMany(fetch = FetchType.LAZY ,mappedBy="insumo")
	private Set<EspecialidadInsumo> especialidades = new HashSet<EspecialidadInsumo>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="insumo")
	private Set<DiagnosticoInsumo> diagnosticos = new HashSet<DiagnosticoInsumo>();
	
	@Column(name = "visible_ctc")	
	private Short visibleCtc;
	
	@Column(name="es_p_y_pp", nullable = false)
	private Short esPyPP;
	
	@ManyToOne
	@JoinColumn( name="genero_id", nullable = true)
	@ForeignKey(name="fk_insumo_genero")
	private Genero genero;

	@Column(name="suministra_medicarte", nullable = false)
	private Short suministraMedicarte;
	
	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;

	@Column(name="es_insumo", nullable = true)
	private Short insumo;
	
	@Column(name = "es_proveeduria", nullable = false)
	private Boolean proveeduria;
	
	
	
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

	public InsumoDto toDto() {
		InsumoDto dto = new InsumoDto();
		dto.setId(this.id);
		dto.setCodigo(this.codigo);
		dto.setDescripcion(this.descripcion);
		dto.setTipoPPM(this.getTipoPPM().toDto());
		dto.setVisibleCtc(getVisibleCtc());
		dto.setNivelDeAutorizacion(this.nivelAutorizacion);
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


	public Short getControlados() {
		return controlados;
	}

	public void setControlados(Short controlados) {
		this.controlados = controlados;
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

	public Set<EspecialidadInsumo> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<EspecialidadInsumo> especialidades) {
		this.especialidades = especialidades;
	}

	public Set<DiagnosticoInsumo> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(Set<DiagnosticoInsumo> diagnosticos) {
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

	public EstadoProcedimiento getEstadoInsumo() {
		return estadoInsumo;
	}

	public void setEstadoInsumo(EstadoProcedimiento estadoInsumo) {
		this.estadoInsumo = estadoInsumo;
	}

	public Insumo getHomologo() {
		return homologo;
	}

	public void setHomologo(Insumo homologo) {
		this.homologo = homologo;
	}

	public ProgramaMedicamentoAltoCosto getProgramaAltoCosto() {
		return programaAltoCosto;
	}

	public void setProgramaAltoCosto(ProgramaMedicamentoAltoCosto programaAltoCosto) {
		this.programaAltoCosto = programaAltoCosto;
	}

	public Boolean getProveeduria() {
		return proveeduria;
	}

	public void setProveeduria(Boolean proveeduria) {
		this.proveeduria = proveeduria;
	}


}
