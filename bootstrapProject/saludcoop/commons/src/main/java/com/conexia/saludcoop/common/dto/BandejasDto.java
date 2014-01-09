package com.conexia.saludcoop.common.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.conexia.saludcoop.common.dto.transaccional.DocumentoSoporteDto;

/**
 * Clase con los datos para la autorización de solicitudes CTC
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 08/10/2013
 * 
 */
public class BandejasDto {
    private Integer causaExterna;
    private Integer finalidad;
    private Integer tipoCatastrofico;
    private Integer entidadRecobro;
    private Long idDiagnostico;
    private Long idSedeIps;
    private Integer concepto;
    private Integer periodoAprobado;
    private Integer diasPeriodo;
    private Integer unidadesAprobadas;
    private Integer numeroEntregas;
    private String justificacion;
    private Long itemId;
    private String tipoBandeja;
    private Integer tipoItem;
    private Integer causalDevolucion;
    private Integer causalAnulacion;
    private List<Integer> criteriosNegacion;
	private Integer cantidadPeridoHomologo;
	private Integer objProcedHom;
    private Integer tiempoPeriodoHomologo;
    private Integer frecuenciaUsoHomologo;
    private Long idProcedimientoHom;
    private Integer dosisAprobada;
    private Integer dosisPrescritaMedHomologo;
    private Set<DocumentoSoporteDto> documentosSoporte = new HashSet<>();
    private String numeroTutela;
    private String numeroFallo;
    private String justificacionConcepto;
    private String justificacionConexidad;
    private Boolean esTutelaIntegral;
    private Boolean excentaCopagos;
    private Boolean esLdf;
    
    public Integer getCausaExterna() {
        return causaExterna;
    }
    public void setCausaExterna(Integer causaExterna) {
        this.causaExterna = causaExterna;
    }
    public Integer getFinalidad() {
        return finalidad;
    }
    public void setFinalidad(Integer finalidad) {
        this.finalidad = finalidad;
    }
    public Integer getTipoCatastrofico() {
        return tipoCatastrofico;
    }
    public void setTipoCatastrofico(Integer tipoCatastrofico) {
        this.tipoCatastrofico = tipoCatastrofico;
    }
    public Integer getEntidadRecobro() {
        return entidadRecobro;
    }
    public void setEntidadRecobro(Integer entidadRecobro) {
        this.entidadRecobro = entidadRecobro;
    }
    public Long getIdDiagnostico() {
        return idDiagnostico;
    }
    public void setIdDiagnostico(Long idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }
    public Long getIdSedeIps() {
        return idSedeIps;
    }
    public void setIdSedeIps(Long idSedeIps) {
        this.idSedeIps = idSedeIps;
    }
    public String getTipoBandeja() {
        return tipoBandeja;
    }
    public void setTipoBandeja(String tipoBandeja) {
        this.tipoBandeja = tipoBandeja;
    }
    public Integer getConcepto() {
        return concepto;
    }
    public void setConcepto(Integer concepto) {
        this.concepto = concepto;
    }
    public Integer getPeriodoAprobado() {
        return periodoAprobado;
    }
    public void setPeriodoAprobado(Integer periodoAprobado) {
        this.periodoAprobado = periodoAprobado;
    }
    public Integer getDiasPeriodo() {
        return diasPeriodo;
    }
    public void setDiasPeriodo(Integer diasPeriodo) {
        this.diasPeriodo = diasPeriodo;
    }
    public Integer getUnidadesAprobadas() {
        return unidadesAprobadas;
    }
    public void setUnidadesAprobadas(Integer unidadesAprobadas) {
        this.unidadesAprobadas = unidadesAprobadas;
    }
    public Integer getNumeroEntregas() {
        return numeroEntregas;
    }
    public void setNumeroEntregas(Integer numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }
    public String getJustificacion() {
        return justificacion;
    }
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
    
	public Integer getTipoItem() {
        return tipoItem;
    }
    public void setTipoItem(Integer tipoItem) {
        this.tipoItem = tipoItem;
    }
    public Integer getCausalDevolucion() {
        return causalDevolucion;
    }
    public void setCausalDevolucion(Integer causalDevolucion) {
        this.causalDevolucion = causalDevolucion;
    }
    public Integer getCausalAnulacion() {
        return causalAnulacion;
    }
    public void setCausalAnulacion(Integer causalAnulacion) {
        this.causalAnulacion = causalAnulacion;
    }
    public List<Integer> getCriteriosNegacion() {
        return criteriosNegacion;
    }
    public void setCriteriosNegacion(List<Integer> criteriosNegacion) {
        this.criteriosNegacion = criteriosNegacion;
    }
	public Integer getCantidadPeridoHomologo() {
		return cantidadPeridoHomologo;
	}
	public void setCantidadPeridoHomologo(Integer cantidadPeridoHomologo) {
		this.cantidadPeridoHomologo = cantidadPeridoHomologo;
	}
	public Integer getObjProcedHom() {
		return objProcedHom;
	}
	public void setObjProcedHom(Integer objProcedHom) {
		this.objProcedHom = objProcedHom;
	}
	public Integer getTiempoPeriodoHomologo() {
		return tiempoPeriodoHomologo;
	}
	public void setTiempoPeriodoHomologo(Integer tiempoPeriodoHomologo) {
		this.tiempoPeriodoHomologo = tiempoPeriodoHomologo;
	}
	public Integer getFrecuenciaUsoHomologo() {
		return frecuenciaUsoHomologo;
	}
	public void setFrecuenciaUsoHomologo(Integer frecuenciaUsoHomologo) {
		this.frecuenciaUsoHomologo = frecuenciaUsoHomologo;
	}
	public Long getIdProcedimientoHom() {
		return idProcedimientoHom;
	}
	public void setIdProcedimientoHom(Long idProcedimientoHom) {
		this.idProcedimientoHom = idProcedimientoHom;
	}
    public Integer getDosisAprobada() {
        return dosisAprobada;
    }
    public void setDosisAprobada(Integer dosisAprobada) {
        this.dosisAprobada = dosisAprobada;
    }
    public Integer getDosisPrescritaMedHomologo() {
        return dosisPrescritaMedHomologo;
    }
    public void setDosisPrescritaMedHomologo(Integer dosisPrescritaMedHomologo) {
        this.dosisPrescritaMedHomologo = dosisPrescritaMedHomologo;
    }
    public Set<DocumentoSoporteDto> getDocumentosSoporte() {
        return documentosSoporte;
    }
    public void setDocumentosSoporte(Set<DocumentoSoporteDto> documentosSoporte) {
        this.documentosSoporte = documentosSoporte;
    }
    public String getNumeroTutela() {
        return numeroTutela;
    }
    public void setNumeroTutela(String numeroTutela) {
        this.numeroTutela = numeroTutela;
    }
    public String getJustificacionConcepto() {
        return justificacionConcepto;
    }
    public void setJustificacionConcepto(String justificacionConcepto) {
        this.justificacionConcepto = justificacionConcepto;
    }
    public String getJustificacionConexidad() {
        return justificacionConexidad;
    }
    public void setJustificacionConexidad(String justificacionConexidad) {
        this.justificacionConexidad = justificacionConexidad;
    }
    public Boolean getEsTutelaIntegral() {
        return esTutelaIntegral;
    }
    public void setEsTutelaIntegral(Boolean esTutelaIntegral) {
        this.esTutelaIntegral = esTutelaIntegral;
    }
    public Boolean getExcentaCopagos() {
        return excentaCopagos;
    }
    public void setExcentaCopagos(Boolean excentaCopagos) {
        this.excentaCopagos = excentaCopagos;
    }
    public Boolean getEsLdf() {
        return esLdf;
    }
    public void setEsLdf(Boolean esLdf) {
        this.esLdf = esLdf;
    }
	public String getNumeroFallo() {
		return numeroFallo;
	}
	public void setNumeroFallo(String numeroFallo) {
		this.numeroFallo = numeroFallo;
	}
}
