package com.conexia.saludcoop.common.entity.ticket;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dozer.DozerBeanMapper;
import org.hibernate.Hibernate;

import com.conexia.saludcoop.common.dto.ticket.TicketCabeceraDto;
import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;

@Entity
@Table (name="ticket_cabecera", schema="ticket")
public class TicketCabecera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@OneToOne
    @JoinColumn(name = "autorizacion_id", nullable = false)
    private Autorizacion autorizacion;
	
	@Column(nullable=false, name="numero_autorizacion")
	private Integer numeroAutorizacion;
	
	@Column(nullable=false, name="cantidad_de_entregas")
	private Integer cantidadDeEntregas;
	
	@Column(nullable=false, name="numero_de_entrega")
	private Integer numeroDeEntrega;
	
	@Column(nullable=false, name="eps")
	private String eps;
	
	@Column(nullable=false, name="nombre_del_paciente")
	private String nombresDelPaciente;
	
	@Column(nullable=false, name="tipo_afiliado")
	private String tipoAfiliado;
	
	@Column(nullable=false, name="tipo_de_identificacion")
	private String tipoDeIdentificacion;
	
	@Column(nullable=false, name="numero_identificacion")
	private String numeroIdentificacion;
	
	@Column(nullable=false, name="edad")
	private Integer edad;
	
	@Column(nullable=false, name="nivel")
	private String nivel;
	
	@Column(nullable=false, name="plan_afiliado")
	private String planAfiliado;
	
	@Column(nullable=false, name="ips_primaria")
	private String ipsPrimaria;
	
	@Column(nullable=false, name="entidad_solicitante")
	private String entidadSolicitante;
	
	@Column(nullable=false, name="fecha")
	private Date fecha;

	@Column(nullable=false, name="usuario_transcriptor")
	private String usuarioTranscriptor;

	@Column(nullable=true, name="causa_externa")
	private String causaExterna;

	@Column(nullable=true, name="entidad_recobro")
	private String entidadRecobro;

	@Column(nullable=false, name="origen")
	private String origen;

	@Column(nullable=false, name="diagnostico_principal")
	private String diagnosticoPrincipal;

	@Column(nullable=true, name="diagnosticos_secundarios")
	private String diagnosticosSecundarios;
	
	@OneToMany(mappedBy="ticketCabecera", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<TicketItem> items = new HashSet<>();

	@Column(nullable=false, name="pago_compartido_eps")
	private String pagoCompartidoEps;

	@Column(nullable=false, name="pago_compartido_usuario")
	private String pagoCompartidoUsuario;

	@Column(nullable=false, name="copago_porcentaje")
	private String copagoPorcentaje;

	@Column(nullable=false, name="copago_valor")
	private String copagoValor;

	@Column(nullable=false, name="cuota_moderadora")
	private String cuotaModeradora;

	@Column(nullable=false, name="descuento_capitacion_ips")
	private String descuentoCapitacionIps;

	@Column(nullable=false, name="institucion_remitida_nombre")
	private String institucionRemitidaNombre;

	@Column(nullable=false, name="institucion_remitida_direccion")
	private String institucionRemitidaDireccion;

	@Column(nullable=false, name="institucion_remitida_telefono")
	private String institucionRemitidaTelefono;

	@Column(nullable=false, name="firma_medico")
	private String firmaMedico;
	
	@Column(nullable=false, name="nombre_completo_medico")
	private String nombreCompletoMedico;

	@Column(nullable=false, name="registro_medico")
	private String registroMedico;

	@Column(nullable=false, name="numero_solicitud")
	private Integer numeroSolicitud;
	
	@Column(nullable=false, name="tipo_servicio")
	private int tipoServicio;
	
	@Column(nullable=false, name="tipo_ppm")
	private String tipoPpm;
	
	@Column(nullable=false, name="vigencia")
	private String vigencia;
	
	@Column(nullable=true, name="fecha_impresion")
	private Date fechaImpresion;
	
	@Column(nullable=false, name="cantidad_copias", columnDefinition="int DEFAULT 0")
	private Integer cantidadCopias;
		
	public static void main(String[] args) {
		Class<TicketCabecera> clase = TicketCabecera.class;
		for (Field f : clase.getDeclaredFields()){
			System.out.println("tc.set"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1)+"();");
		}
	}
	

	public String getVigencia() {
		return vigencia;
	}


	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}


	public Autorizacion getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(Autorizacion autorizacion) {
		this.autorizacion = autorizacion;
	}

	public Integer getCantidadDeEntregas() {
		return cantidadDeEntregas;
	}

	public void setCantidadDeEntregas(Integer cantidadDeEntregas) {
		this.cantidadDeEntregas = cantidadDeEntregas;
	}

	public Integer getNumeroDeEntrega() {
		return numeroDeEntrega;
	}

	public void setNumeroDeEntrega(Integer numeroDeEntrega) {
		this.numeroDeEntrega = numeroDeEntrega;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getNombresDelPaciente() {
		return nombresDelPaciente;
	}

	public void setNombresDelPaciente(String nombresDelPaciente) {
		this.nombresDelPaciente = nombresDelPaciente;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getTipoDeIdentificacion() {
		return tipoDeIdentificacion;
	}

	public void setTipoDeIdentificacion(String tipoDeIdentificacion) {
		this.tipoDeIdentificacion = tipoDeIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}


	public String getPlanAfiliado() {
		return planAfiliado;
	}

	public void setPlanAfiliado(String planAfiliado) {
		this.planAfiliado = planAfiliado;
	}

	public String getIpsPrimaria() {
		return ipsPrimaria;
	}

	public void setIpsPrimaria(String ipsPrimaria) {
		this.ipsPrimaria = ipsPrimaria;
	}

	public String getEntidadSolicitante() {
		return entidadSolicitante;
	}

	public void setEntidadSolicitante(String entidadSolicitante) {
		this.entidadSolicitante = entidadSolicitante;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUsuarioTranscriptor() {
		return usuarioTranscriptor;
	}

	public void setUsuarioTranscriptor(String usuarioTranscriptor) {
		this.usuarioTranscriptor = usuarioTranscriptor;
	}

	public String getCausaExterna() {
		return causaExterna;
	}

	public void setCausaExterna(String causaExterna) {
		this.causaExterna = causaExterna;
	}

	public String getEntidadRecobro() {
		return entidadRecobro;
	}

	public void setEntidadRecobro(String entidadRecobro) {
		this.entidadRecobro = entidadRecobro;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDiagnosticoPrincipal() {
		return diagnosticoPrincipal;
	}

	public void setDiagnosticoPrincipal(String diagnosticoPrincipal) {
		this.diagnosticoPrincipal = diagnosticoPrincipal;
	}

	public String getDiagnosticosSecundarios() {
		return diagnosticosSecundarios;
	}

	public void setDiagnosticosSecundarios(String diagnosticosSecundarios) {
		this.diagnosticosSecundarios = diagnosticosSecundarios;
	}

	public String getPagoCompartidoEps() {
		return pagoCompartidoEps;
	}

	public void setPagoCompartidoEps(String pagoCompartidoEps) {
		this.pagoCompartidoEps = pagoCompartidoEps;
	}

	public String getPagoCompartidoUsuario() {
		return pagoCompartidoUsuario;
	}

	public void setPagoCompartidoUsuario(String pagoCompartidoUsuario) {
		this.pagoCompartidoUsuario = pagoCompartidoUsuario;
	}

	public String getCopagoPorcentaje() {
		return copagoPorcentaje;
	}

	public void setCopagoPorcentaje(String copagoPorcentaje) {
		this.copagoPorcentaje = copagoPorcentaje;
	}

	public String getCopagoValor() {
		return copagoValor;
	}

	public void setCopagoValor(String copagoValor) {
		this.copagoValor = copagoValor;
	}

	public String getCuotaModeradora() {
		return cuotaModeradora;
	}

	public void setCuotaModeradora(String cuotaModeradora) {
		this.cuotaModeradora = cuotaModeradora;
	}

	public String getDescuentoCapitacionIps() {
		return descuentoCapitacionIps;
	}

	public void setDescuentoCapitacionIps(String descuentoCapitacionIps) {
		this.descuentoCapitacionIps = descuentoCapitacionIps;
	}

	public String getInstitucionRemitidaNombre() {
		return institucionRemitidaNombre;
	}

	public void setInstitucionRemitidaNombre(String institucionRemitidaNombre) {
		this.institucionRemitidaNombre = institucionRemitidaNombre;
	}

	public String getInstitucionRemitidaDireccion() {
		return institucionRemitidaDireccion;
	}

	public void setInstitucionRemitidaDireccion(String institucionRemitidaDireccion) {
		this.institucionRemitidaDireccion = institucionRemitidaDireccion;
	}

	public String getInstitucionRemitidaTelefono() {
		return institucionRemitidaTelefono;
	}

	public void setInstitucionRemitidaTelefono(String institucionRemitidaTelefono) {
		this.institucionRemitidaTelefono = institucionRemitidaTelefono;
	}

	public String getFirmaMedico() {
		return firmaMedico;
	}

	public void setFirmaMedico(String firmaMedico) {
		this.firmaMedico = firmaMedico;
	}

	public String getRegistroMedico() {
		return registroMedico;
	}

	public void setRegistroMedico(String registroMedico) {
		this.registroMedico = registroMedico;
	}

	public Integer getNumeroSolicitud() {
		return numeroSolicitud;
	}

	public void setNumeroSolicitud(Integer numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	public Integer getId() {
		return id;
	}

	public Set<TicketItem> getItems() {
		return items;
	}
	
	public void addItem(TicketItem item){
		item.setTicketCabecera(this);
		this.items.add(item);
	}
	
	public int getTipoServicio() {
		return tipoServicio;
	}


	public void setTipoServicio(int tipoServicio) {
		this.tipoServicio = tipoServicio;
	}


	public String getTipoPpm() {
		return tipoPpm;
	}

	public void setTipoPpm(String tipoPpm) {
		this.tipoPpm = tipoPpm;
	}
	

	public String getNombreCompletoMedico() {
		return nombreCompletoMedico;
	}

	public void setNombreCompletoMedico(String nombreCompletoMedico) {
		this.nombreCompletoMedico = nombreCompletoMedico;
	}

	public Integer getNumeroAutorizacion() {
		return numeroAutorizacion;
	}

	public void setNumeroAutorizacion(Integer numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	public Date getFechaImpresion() {
		return fechaImpresion;
	}


	public void setFechaImpresion(Date fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}


	public Integer getCantidadCopias() {
		return cantidadCopias;
	}


	public void setCantidadCopias(Integer cantidadCopias) {
		this.cantidadCopias = cantidadCopias;
	}


	public TicketCabeceraDto toDto(){
		TicketCabeceraDto dto = new DozerBeanMapper().map(this, TicketCabeceraDto.class);
		dto.setAutorizacionId(this.autorizacion.getId());
		return dto;
	}
	
}
