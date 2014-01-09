package com.conexia.saludcoop.common.entity.maestro;

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

import com.conexia.saludcoop.common.dto.EspecialidadDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.enumerator.TipoPagoRequerido;

@Entity
@Table(name = "procedimiento", schema = "maestros")
public class Procedimiento extends BaseMaestro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Index(name="ix_procedimiento_codigo")
	@Column(name = "codigo", nullable = false, length= 20)
	private String codigo;
	
	@Index(name="ix_procedimiento_descripcion")
	@Column(name = "descripcion", nullable = false, length= 1500)
	private String descripcion;
	
	/**
	 *  indica si se necesita una impresión
	 */
	@Column(name = "requiere_autorizacion")
	private Short requiereAutorizacion;
	
	@Column(name = "autorizado")
	private Short autorizado;
	
	@ManyToOne
	@JoinColumn(name="tipo_ppm_id", nullable = false)
	@ForeignKey(name="fk_procedimiento_tipo_ppm")
	private TipoPPM tipo;

	
	@ManyToOne
	@JoinColumn(name="estado_procedimiento_id", nullable = false)
	@ForeignKey(name="fk_procedimiento_tipo_ppm")
	private EstadoProcedimiento estadoProcedimiento;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="procedimiento")
	private Set<EspecialidadProcedimiento> especialidades = new HashSet<EspecialidadProcedimiento>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="procedimiento")
	private Set<DiagnosticoProcedimiento> diagnosticos = new HashSet<DiagnosticoProcedimiento>();

	@Column(name="nivel_autorizacion", nullable = false)
	private Integer nivelAutorizacion;
	
	@Column(name="nivel_complejidad", nullable = false)
	private Integer nivelComplejidad;   // solo a nivel informativo
	
	/**
	 * Tipo de pago requerido.
	 */
	@Column(name="tipo_pago_requerido_enum", nullable = false)
	private Long tipoPagoRequeridoId;
	
	@Column(name="es_p_y_pp", nullable = false)
	private Short esPyPP;
	
	@ManyToOne
	@JoinColumn( name="genero_id", nullable = true)
	@ForeignKey(name="fk_procedimiento_genero")
	private Genero genero;
	
	@Column(name = "es_proveeduria", nullable = false)
	private Boolean proveeduria;
	
	@Column(name="cliente_pk", nullable = true)
	private Integer clientePk;
	
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

	
	public ProcedimientoDto toDto() {
		ProcedimientoDto dto = new ProcedimientoDto();
		dto.setId(this.id);
		dto.setCodigo(this.codigo);
		dto.setDescripcion(this.descripcion);
		dto.setTipoPPM(this.getTipoPPM().toDto());
		dto.setEspecialidades(new HashSet<EspecialidadDto>());
		for (EspecialidadProcedimiento e : this.getEspecialidades()){
			dto.getEspecialidades().add(e.getEspecialidad().toEspecialidadDto());
			
		}
		dto.setNivelDeAutorizacion(this.nivelAutorizacion);
		dto.setTipoPagoRequerido(this.getTipoPagoRequerido());
		
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

	public Integer getNivelAutorizacion() {
		return nivelAutorizacion;
	}

	public void setNivelAutorizacion(Integer nivelAutorizacion) {
		this.nivelAutorizacion = nivelAutorizacion;
	}

	public Integer getNivelComplejidad() {
		return nivelComplejidad;
	}

	public void setNivelComplejidad(Integer nivelComplejidad) {
		this.nivelComplejidad = nivelComplejidad;
	}

	public EstadoProcedimiento getEstadoProcedimiento() {
		return estadoProcedimiento;
	}

	public void setEstadoProcedimiento(EstadoProcedimiento estadoProcedimiento) {
		this.estadoProcedimiento = estadoProcedimiento;
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

	public Short getEsPyPP() {
		return esPyPP;
	}

	public void setEsPyPP(Short esPyPP) {
		this.esPyPP = esPyPP;
	}

	public Short getRequiereAutorizacion() {
		return requiereAutorizacion;
	}

	public void setRequiereAutorizacion(Short requiereAutorizacion) {
		this.requiereAutorizacion = requiereAutorizacion;
	}

	public Short getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Short autorizado) {
		this.autorizado = autorizado;
	}

	public TipoPPM getTipo() {
		return tipo;
	}

	public void setTipo(TipoPPM tipo) {
		this.tipo = tipo;
	}

	public Set<EspecialidadProcedimiento> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<EspecialidadProcedimiento> especialidades) {
		this.especialidades = especialidades;
	}

	public Set<DiagnosticoProcedimiento> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(Set<DiagnosticoProcedimiento> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public Boolean getProveeduria() {
		return proveeduria;
	}

	public void setProveeduria(Boolean proveeduria) {
		this.proveeduria = proveeduria;
	}
}
