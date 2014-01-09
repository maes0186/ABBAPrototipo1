package com.conexia.saludcoop.common.entity.transaccional;



import java.math.BigDecimal;
import java.util.Collections;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.conexia.common.persistence.entity.Identifiable;

import com.conexia.saludcoop.common.entity.maestro.TipoPPM;
import com.conexia.saludcoop.common.entity.maestro.TipoServicio;
import com.conexia.saludcoop.common.entity.maestro.TipoTecnologia;

@Entity
@Table(name = "solicitud_item", schema = "transaccional")
@org.hibernate.annotations.Entity(dynamicInsert=true)
public class SolicitudItem implements Identifiable<Long> {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long nroItem;

	@Column(name = "cantidad_solicitada", nullable = false)
	private Integer cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	private SolicitudDiagnostico diagnostico;

	@OneToOne(cascade=CascadeType.ALL, mappedBy="solicitudItem")
	private SolicitudMedicamento solMedicamento;

	@OneToOne(cascade=CascadeType.ALL, mappedBy="solicitudItem")
	private SolicitudProcedimiento solProcedimiento;

	@OneToOne(cascade=CascadeType.ALL, mappedBy="solicitudItem")
	private SolicitudInsumo solInsumo;
	
	@ManyToOne
	@JoinColumn(name = "tipo_ppm_id", nullable = false)
	private TipoPPM tipoPPM;
	
	@ManyToOne
	@JoinColumn(name = "solicitud_id", nullable = false)
	private Solicitud solicitud;
	
	@ManyToOne
	@JoinColumn(name = "tipo_servicio_id", nullable = false)
	private TipoServicio tipoServicio;
	
	@ManyToOne
	@JoinColumn(name="autorizacion_id", nullable=false)
	private Autorizacion autorizacion;
	
	@OneToMany(mappedBy="solicitudItem",cascade=CascadeType.ALL)
	private Set<Consumo> consumos;
	
	@Column(name="saldo_a_consumir", nullable=false)
	private Integer saldoAConsumir;

	@ManyToOne
	@JoinColumn(name="tipo_tecnologia_id", nullable=false)
	private TipoTecnologia tipoTecnologia;
	
	@Column(name="aplica_tutela", nullable = false)
    private Boolean aplicaTutela=false;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="solicitudItem")
	private Set<DocumentoSoporte> documentosSoporte;

	@Column(name="copago_estimado", nullable=false)
	private BigDecimal copagoEstimado = new BigDecimal(0);
	
	@Column(name="supera_topes", nullable = true)
    private Boolean superaTopes;
	
	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Autorizacion getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(Autorizacion autorizacion) {
		this.autorizacion = autorizacion;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

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

	public SolicitudDiagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(SolicitudDiagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public SolicitudMedicamento getSolMedicamento() {
		return solMedicamento;
	}

	public void setSolMedicamento(SolicitudMedicamento solMedicamento) {
		this.solMedicamento = solMedicamento;
		solMedicamento.setSolicitudItem(this);
	}

	public SolicitudProcedimiento getSolProcedimiento() {
		return solProcedimiento;
	}

	public void setSolProcedimiento(SolicitudProcedimiento solProcedimiento) {
		this.solProcedimiento = solProcedimiento;
		solProcedimiento.setSolicitudItem(this);
	}

	public TipoPPM getTipoPPM() {
		return tipoPPM;
	}

	public void setTipoPPM(TipoPPM tipoPPM) {
		this.tipoPPM = tipoPPM;
	}

	public Set<Consumo> getConsumos() {
		return consumos;
	}

	public Integer getSaldoAConsumir() {
		return saldoAConsumir;
	}

	public void addConsumo(Consumo consumo){
		if(this.consumos == null){
			this.consumos = new HashSet<Consumo>();
		}
		this.consumos.add(consumo);
		consumo.setSolicitudItem(this);
		this.saldoAConsumir =- consumo.getCantidadConsumida(); 
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.nroItem;
	}

	public boolean isMedicamento() {
		return this.getSolMedicamento()!= null;
	}

	public boolean isProcedimiento() {

		return this.getSolProcedimiento() != null;
	}

	
	public TipoTecnologia getTipoTecnologia() {
		return tipoTecnologia;
	}

	public void setTipoTecnologia(TipoTecnologia tipoTecnologia) {
		this.tipoTecnologia = tipoTecnologia;
	}

	public void setSaldoAConsumir(Integer saldoAConsumir) {
		this.saldoAConsumir = saldoAConsumir;
	}

    public Boolean getAplicaTutela() {
        return aplicaTutela;
    }

    public void setAplicaTutela(Boolean aplicaTutela) {
        this.aplicaTutela = aplicaTutela;
    }
	
    public void addDocumentoSoporte(DocumentoSoporte documento){
    	if(documento!=null){
    		if(this.documentosSoporte== null){
    			this.documentosSoporte = new HashSet<>();
    		}
    		this.documentosSoporte.add(documento);	
    		documento.setSolicitudItem(this);
    	}
    	
    }
    
    public Set<DocumentoSoporte> getDocumentosSoporte(){
    	return Collections.unmodifiableSet(this.documentosSoporte); 
    }

	public SolicitudInsumo getSolInsumo() {
		return solInsumo;
	}

	public void setSolInsumo(SolicitudInsumo solInsumo) {
		
		this.solInsumo = solInsumo;
		solInsumo.setSolicitudItem(this);
	}

	public boolean isInsumo() {
		return this.getSolInsumo()!= null;
	}

	public BigDecimal getCopagoEstimado() {
		return copagoEstimado;
	}

	public void setCopagoEstimado(BigDecimal copagoEstimado) {
		this.copagoEstimado = copagoEstimado;
	}

    public Boolean getSuperaTopes() {
        return superaTopes;
    }

    public void setSuperaTopes(Boolean superaTopes) {
        this.superaTopes = superaTopes;
    }

    
    
}
