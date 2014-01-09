package com.conexia.saludcoop.common.dto.transaccional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.ProfesionalDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;

public class SolicitudDto {
	private Long id;
	private Integer nroSolicitud;
	private SedeIpsDto sedeIps;
	private AfiliadoDto afiliado;
	private ProfesionalDto profesionalSolicitante;
	private Set<SolicitudDiagnosticoDto> solDiagnosticos = new HashSet<>();
	private Set<DocumentoSoporteDto> documentosSoporte = new HashSet<>();
	private ResumenHistoriaClinicaDto resumenHistoriaClinica;
	private Set<SolicitudItemDto> solicitudItems = new HashSet<SolicitudItemDto>();
//	private Set<AutorizacionDto> autorizaciones = new HashSet<AutorizacionDto>();
	private Date fechaCreacion;
	private Long usuarioCreador;
	private DescriptivoDto tipoServicio;
	private String observaciones;
	private List<GrupoAutorizacionDto> gruposAutorizaciones = new Vector<>();
	private boolean ldf;
	
	/**
	 * Indica si es la primera formulación del año.
	 */
	private boolean primeraFormulacionAnio;
	
	public void addAutorizacion(GrupoAutorizacionDto grupo){
		this.gruposAutorizaciones.add(grupo);
	}
	
	public Integer getNroSolicitud() {
		return nroSolicitud;
	}
	public void setNroSolicitud(Integer nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	public SedeIpsDto getSedeIps() {
		return sedeIps;
	}
	public void setSedeIps(SedeIpsDto sedeIps) {
		this.sedeIps = sedeIps;
	}
	public AfiliadoDto getAfiliado() {
		return afiliado;
	}
	public void setAfiliado(AfiliadoDto afiliado) {
		this.afiliado = afiliado;
	}
	public ProfesionalDto getProfesionalSolicitante() {
		return profesionalSolicitante;
	}
	public void setProfesionalSolicitante(ProfesionalDto profesionalSolicitante) {
		this.profesionalSolicitante = profesionalSolicitante;
	}
	public Set<SolicitudDiagnosticoDto> getSolDiagnosticos() {
		return solDiagnosticos;
	}
	public void setSolDiagnosticos(Set<SolicitudDiagnosticoDto> solDiagnosticos) {
		this.solDiagnosticos = solDiagnosticos;
	}
	public Set<DocumentoSoporteDto> getDocumentosSoporte() {
		return documentosSoporte;
	}
	public void setDocumentosSoporte(Set<DocumentoSoporteDto> documentosSoporte) {
		this.documentosSoporte = documentosSoporte;
	}
	public ResumenHistoriaClinicaDto getResumenHistoriaClinica() {
		return resumenHistoriaClinica;
	}
	public void setResumenHistoriaClinica(ResumenHistoriaClinicaDto resumenHistoriaClinica) {
		this.resumenHistoriaClinica = resumenHistoriaClinica;
	}
	public Set<SolicitudItemDto> getSolicitudItems() {
		return solicitudItems;
	}
	public void setSolicitudItems(Set<SolicitudItemDto> solicitudItems) {
		this.solicitudItems = solicitudItems;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getUsuarioCreador() {
		return usuarioCreador;
	}
	public void setUsuarioCreador(Long usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}
	public DescriptivoDto getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(DescriptivoDto tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public List<GrupoAutorizacionDto> getGruposAutorizaciones() {
		return gruposAutorizaciones;
	}
	public void setGruposAutorizaciones(List<GrupoAutorizacionDto> gruposAutorizaciones) {
		this.gruposAutorizaciones = gruposAutorizaciones;
	}
	

	/**
	 * Indica si es la primera formulación del año.
	 * 
	 * @return True si es la primera formulación del año; caso contrario, False.
	 */
	@JsonIgnore
	public boolean isPrimeraFormulacionAnio() {
		
		return (this.primeraFormulacionAnio);
	}

	/**
	 * Asigna el indicador de si es la primera formulación del año.
	 * 
	 * @param primeraFormulacionAnio Indicador de si es la primera formulación del año.
	 */
	@JsonIgnore
	public void setPrimeraFormulacionAnio(final boolean primeraFormulacionAnio) {
		
		this.primeraFormulacionAnio = primeraFormulacionAnio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		
	}

	public void addGrupo(GrupoAutorizacionDto grupo) {
		this.gruposAutorizaciones.add(grupo);
		
	}

    public boolean isLdf() {
        return ldf;
    }

    public void setLdf(boolean ldf) {
        this.ldf = ldf;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
}
