package com.conexia.saludcoop.common.entity.transaccional;

import java.util.ArrayList;
import java.util.Date;
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

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.common.dto.transaccional.AutorizacionDto;
import com.conexia.saludcoop.common.dto.transaccional.RoleDTO;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudInsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudItemDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.SolicitudProcedimientoDto;
import com.conexia.saludcoop.common.entity.maestro.EntidadRecobro;
import com.conexia.saludcoop.common.entity.maestro.Especialidad;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;
import com.conexia.saludcoop.common.entity.maestro.Servicio;
import com.conexia.saludcoop.common.entity.security.Role;

@Entity
@Table(name = "autorizacion", schema = "transaccional")
public class Autorizacion implements Identifiable<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 10)
    private Long numeroAutorizacion;

    @ManyToOne
    @JoinColumn(name="servicio_id", nullable = true)
    private Servicio servicio;
    
    @ManyToOne
    @JoinColumn(name="especialidad_id", nullable = true)
	private Especialidad especialidad;
   
    @OneToMany(mappedBy="autorizacion") 
    private Set<SolicitudItem> solicitudItems = new HashSet<SolicitudItem>();

    @OneToOne
    @JoinColumn(name = "estado_autorizacion_id", nullable = false)
    private EstadoAutorizacion estadoAutorizacion;
    
    @ManyToOne
    @JoinColumn(name="rol_destino_id", nullable = false)
    private Role roleDestinatario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "concepto_nacional_id", nullable = true)
    private ConceptoAutorizacion conceptoNacional;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "concepto_regional_id", nullable = true)
    private ConceptoAutorizacion conceptoRegional;
    
    @Column(name = "fecha_autorizacion", nullable = true, length = 10)
    private Date fechaAutorizacion;
    
    
    @Column(name="fecha_update_cambio_estado",nullable= true )
	private Date fechaUpdateCambioEstado;
    
    @Column(name="fecha_update",nullable= true )
	private Date fechaUpdate;
    
    @ManyToOne
    @JoinColumn(nullable = true, name="sede_ips_efectora_id")
    private SedeIps sedeIpsEfectora;

    @Column(name = "justificacion_ips", nullable = true, length=500)
    private String justificacionIps;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entidad_recobro_id", nullable = true)
    private EntidadRecobro entidadRecobro;
    
    @ManyToOne
    @JoinColumn(name="grupo_autorizacion_id", nullable=false)
    private GrupoAutorizacion grupoAutorizacion;
    
//    @Column(name="copago", nullable=false)
//    private BigDecimal copago; 
    
//    @Column(name="valor_ppm", nullable=false)
//    private BigDecimal valorPpm; 
    
    @Column(name="dosis_medicamento_homologo", nullable=true)
    private Integer dosisMedicamentoHomologo; 
    
//    @Column(name="tipo_pago_enum", nullable = false)
//    private TipoPagoRequerido tipoPago;

    @Column(name = "justificacion", nullable = true, length=500)
    private String justificacion;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="autorizacion_anterior_id", nullable = true)
    private Autorizacion autorizacionAnterior;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "informacion_tutela_id", nullable = true)
    private InformacionTutela infoTutela;

    public Long getId() {
        return numeroAutorizacion;
    }
	/**
	 * Obtiene siempre la primer solicitudItem
	 * @return
	 */
    public SolicitudItem getSolicitudItem() {
        return this.solicitudItems.iterator().next();
    }
    
	/**
	 *  Setea la primer solicitudItem siempre.
	 * @param solicitudItem
	 */
    public void setSolicitudItem(SolicitudItem solicitudItem) {
        this.solicitudItems.add(solicitudItem);
    }
    
	public void addSolicitudItem(SolicitudItem solItem){
		if(solicitudItems== null){
			solicitudItems = new HashSet<SolicitudItem>();
		}
		this.solicitudItems.add(solItem);
		solItem.setAutorizacion(this);
	}
    public Set<SolicitudItem> getSolicitudItems() {
		return solicitudItems;
	}


	public Long getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(Long numero) {
        this.numeroAutorizacion = numero;
    }

    public EstadoAutorizacion getEstadoAutorizacion() {
        return estadoAutorizacion;
    }

    public void setEstadoAutorizacion(EstadoAutorizacion estadoAutorizacion) {
        this.estadoAutorizacion = estadoAutorizacion;
        this.fechaUpdateCambioEstado = new Date();
    }

    //Sirve mayormente para mostrar tickets
    public AutorizacionDto toDto() {
        AutorizacionDto aut = new AutorizacionDto();
        aut.setNumero(this.numeroAutorizacion.intValue());
        DescriptivoDto estado = new DescriptivoDto();
        estado.setDescripcion(this.getEstadoAutorizacion().getDescripcion());
        estado.setId(this.getEstadoAutorizacion().getId());
        aut.setEstadoAutorizacion(estado);
        aut.setSolicitudItems(new ArrayList<SolicitudItemDto>());
        aut.setTarget((roleDestinatario != null) ? roleDestinatario.toDTO() : new RoleDTO());
        if(this.especialidad != null)aut.setEspecialidadId(this.especialidad.getId());
        if(this.servicio!=null)aut.setServicioId(this.servicio.getId());
        for (SolicitudItem solItem : this.solicitudItems){
        	SolicitudItemDto item = new SolicitudItemDto();
            item.setNroItem(solItem.getNroItem());
            item.setCantidad(solItem.getCantidad());
            if (solItem.isMedicamento()){
            	SolicitudMedicamentoDto sm = new SolicitudMedicamentoDto();
            	MedicamentoDto m = new MedicamentoDto();
            	m.setCodigo(solItem.getSolMedicamento().getMedicamento().getCodigo());
            	m.setDescripcion(solItem.getSolMedicamento().getMedicamento().getDescripcion());
            	sm.setMedicamento(m);
            	item.setSolMedicamento(sm);
            }
            else if (solItem.isProcedimiento()){
            	SolicitudProcedimientoDto sp = new SolicitudProcedimientoDto();
            	ProcedimientoDto p = new ProcedimientoDto();
            	p.setCodigo(solItem.getSolProcedimiento().getProcedimiento().getCodigo());
            	p.setDescripcion(solItem.getSolProcedimiento().getProcedimiento().getDescripcion());
            	sp.setProcedimiento(p);
            	item.setSolProcedimiento(sp);
            } else if (solItem.isInsumo()){
            	SolicitudInsumoDto sm = new SolicitudInsumoDto();
            	InsumoDto m = new InsumoDto();
            	m.setCodigo(solItem.getSolInsumo().getInsumo().getCodigo());
            	m.setDescripcion(solItem.getSolInsumo().getInsumo().getDescripcion());
            	sm.setInsumo(m);
            	item.setSolInsumo(sm);
            }
            aut.getSolicitudItems().add(item);
            aut.setNumeroSolicitud(solItem.getSolicitud().getNroSolicitud().longValue());
        }
        
        
        
        
        return aut;

    }


	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public Date getFechaUpdateCambioEstado() {
		return fechaUpdateCambioEstado;
	}

	public void setFechaUpdateCambioEstado(Date fechaUpdateCambioEstado) {
		this.fechaUpdateCambioEstado = fechaUpdateCambioEstado;
	}

	public Date getFechaUpdate() {
		return fechaUpdate;
	}

	public void setFechaUpdate(Date fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}

	public SedeIps getSedeIpsEfectora() {
		return sedeIpsEfectora;
	}

	public void setSedeIpsEfectora(SedeIps sedeIpsEfectora) {
		this.sedeIpsEfectora = sedeIpsEfectora;
	}

	public ConceptoAutorizacion getConceptoNacional() {
        return conceptoNacional;
    }

    public void setConceptoNacional(ConceptoAutorizacion conceptoNacional) {
        this.conceptoNacional = conceptoNacional;
    }

    public ConceptoAutorizacion getConceptoRegional() {
        return conceptoRegional;
    }

    public void setConceptoRegional(ConceptoAutorizacion conceptoRegional) {
        this.conceptoRegional = conceptoRegional;
    }

    public String getJustificacionIps() {
        return justificacionIps;
    }

    public void setJustificacionIps(String justificacionIps) {
        this.justificacionIps = justificacionIps;
    }

    public EntidadRecobro getEntidadRecobro() {
        return entidadRecobro;
    }

    public void setEntidadRecobro(EntidadRecobro entidadRecobro) {
        this.entidadRecobro = entidadRecobro;
    }

    public Integer getDosisMedicamentoHomologo() {
        return dosisMedicamentoHomologo;
    }

    public void setDosisMedicamentoHomologo(Integer dosisMedicamentoHomologo) {
        this.dosisMedicamentoHomologo = dosisMedicamentoHomologo;
    }

	public Role getRoleDestinatario() {
		return roleDestinatario;
	}

	public void setRoleDestinatario(Role roleDestinatario) {
		this.roleDestinatario = roleDestinatario;
	}

    public String getJustificacion() {
        return justificacion;
    }
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
    public Autorizacion getAutorizacionAnterior() {
        return autorizacionAnterior;
    }
    public void setAutorizacionAnterior(Autorizacion autorizacionAnterior) {
        this.autorizacionAnterior = autorizacionAnterior;
    }
	public GrupoAutorizacion getGrupoAutorizacion() {
		return grupoAutorizacion;
	}
	public void setGrupoAutorizacion(GrupoAutorizacion grupoAutorizacion) {
		this.grupoAutorizacion = grupoAutorizacion;
	} 
	

    /**
     * Genera una nueva autorización a partir de la autorización enviada por parámetro
     * 
     * @param autorizacion
     * @return
     */
    public Autorizacion cloneAutorizacion() {
        Autorizacion newAutorizacion = new Autorizacion();
        newAutorizacion.setAutorizacionAnterior(this);
        newAutorizacion.setConceptoNacional(this.getConceptoNacional());
        newAutorizacion.setConceptoRegional(this.getConceptoRegional());
        newAutorizacion.setDosisMedicamentoHomologo(this.getDosisMedicamentoHomologo());
        newAutorizacion.setEntidadRecobro(this.getEntidadRecobro());
        newAutorizacion.setEstadoAutorizacion(this.getEstadoAutorizacion());
        newAutorizacion.setFechaAutorizacion(this.getFechaAutorizacion());
        newAutorizacion.setFechaUpdate(this.getFechaUpdate());
        newAutorizacion.setFechaUpdateCambioEstado(new Date());
        newAutorizacion.setInfoTutela(this.getInfoTutela());
        newAutorizacion.setGrupoAutorizacion(this.getGrupoAutorizacion());
        newAutorizacion.setJustificacion(this.getJustificacion());
        newAutorizacion.setJustificacionIps(this.getJustificacionIps());
        newAutorizacion.setRoleDestinatario(this.getRoleDestinatario());
        newAutorizacion.setSedeIpsEfectora(this.getSedeIpsEfectora());
//        newAutorizacion.setSolicitudItem(this.getSolicitudItem());
        if(this.especialidad!= null)newAutorizacion.setEspecialidad(this.getEspecialidad());
        if(this.servicio!=null)newAutorizacion.setServicio(this.getServicio());
        return newAutorizacion;
    }
    public InformacionTutela getInfoTutela() {
        return infoTutela;
    }
    public void setInfoTutela(InformacionTutela infoTutela) {
        this.infoTutela = infoTutela;
    }
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

}
