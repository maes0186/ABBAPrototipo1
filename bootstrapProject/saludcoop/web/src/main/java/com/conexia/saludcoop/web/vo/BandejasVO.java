/**
 * 
 */
package com.conexia.saludcoop.web.vo;

import java.util.List;

/** 
 * Clase usada realizar el mapeo desde la capa de datos a las bandejas de alto costo y ctc,
 * conteniendo los VOs usados por ambas bandejas y mapeando los datos necesarios de acuerdo
 * a la bandeja utilizada
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 17/10/2013
 * 
 */
public class BandejasVO {
    private InfoBandejaProcedimientoVO bandejaProcedimientoVO;
    private InfoBandejaMedicamentoVO bandejaMedicamentoVO;
    private InfoBandejaInsumoVO bandejaInsumoVO;
    private BandejaAltoCostoVO bandejaAltoCostoVO;
    private BandejaContactServiceVO bandejaContactServiceVO;
	private BandejaIpsPrestadorVO bandejaIpsPrestadorVO;
    private InfoGeneralBandejaIniVO infoGeneralVO;
    private SedeIpsVO direccionamientoVO;
    private ConceptoCTCVO conceptoCTCVO;
    private ConceptoACVO conceptoACVO;
    private ConceptoCSVO conceptoCSVO;
	private SolicitudHistorialVO solicitudHistorialVO;
    private InfoSolicitudBandejaVO infoSolicitudBandejaVO;
    private DiagnosticoVO diagnosticoBandeja;
    private BandejasParamVO bandejasParam;
    private InfoDevolucionVO infoDevoluciones;
    private int tipoItem;
    private List<DocumentoSoporteVO> adjuntos;
    private List<DocumentoSoporteVO> adjuntosItem;

    public ConceptoCSVO getConceptoCSVO() {
        return conceptoCSVO;
    }
    public void setConceptoCSVO(ConceptoCSVO conceptoCSVO) {
        this.conceptoCSVO = conceptoCSVO;
    }
    public BandejaContactServiceVO getBandejaContactServiceVO() {
        return bandejaContactServiceVO;
    }
    public void setBandejaContactServiceVO(
            BandejaContactServiceVO bandejaContactServiceVO) {
        this.bandejaContactServiceVO = bandejaContactServiceVO;
    }
    public BandejaAltoCostoVO getBandejaAltoCostoVO() {
        return bandejaAltoCostoVO;
    }
    public void setBandejaAltoCostoVO(BandejaAltoCostoVO bandejaAltoCostoVO) {
        this.bandejaAltoCostoVO = bandejaAltoCostoVO;
    }
    public InfoGeneralBandejaIniVO getInfoGeneralVO() {
        return infoGeneralVO;
    }
    public void setInfoGeneralVO(InfoGeneralBandejaIniVO infoGeneralVO) {
        this.infoGeneralVO = infoGeneralVO;
    }
    public SedeIpsVO getDireccionamientoVO() {
        return direccionamientoVO;
    }
    public void setDireccionamientoVO(SedeIpsVO direccionamientoVO) {
        this.direccionamientoVO = direccionamientoVO;
    }
    public ConceptoCTCVO getConceptoCTCVO() {
        return conceptoCTCVO;
    }
    public void setConceptoCTCVO(ConceptoCTCVO conceptoCTCVO) {
        this.conceptoCTCVO = conceptoCTCVO;
    }
    public SolicitudHistorialVO getSolicitudHistorialVO() {
        return solicitudHistorialVO;
    }
    public void setSolicitudHistorialVO(SolicitudHistorialVO solicitudHistorialVO) {
        this.solicitudHistorialVO = solicitudHistorialVO;
    }
    public InfoSolicitudBandejaVO getInfoSolicitudBandejaVO() {
        return infoSolicitudBandejaVO;
    }
    public void setInfoSolicitudBandejaVO(InfoSolicitudBandejaVO infoSolicitudBandejaVO) {
        this.infoSolicitudBandejaVO = infoSolicitudBandejaVO;
    }
    public DiagnosticoVO getDiagnosticoBandeja() {
        return diagnosticoBandeja;
    }
    public void setDiagnosticoBandeja(DiagnosticoVO diagnosticoBandeja) {
        this.diagnosticoBandeja = diagnosticoBandeja;
    }
    public int getTipoItem() {
        return tipoItem;
    }
    public void setTipoItem(int tipoItem) {
        this.tipoItem = tipoItem;
    }
	public BandejaIpsPrestadorVO getBandejaIpsPrestadorVO() {
		return bandejaIpsPrestadorVO;
	}
	public void setBandejaIpsPrestadorVO(BandejaIpsPrestadorVO bandejaIpsPrestadorVO) {
		this.bandejaIpsPrestadorVO = bandejaIpsPrestadorVO;
	}
	public InfoBandejaProcedimientoVO getBandejaProcedimientoVO() {
		return bandejaProcedimientoVO;
	}
	public void setBandejaProcedimientoVO(
			InfoBandejaProcedimientoVO bandejaProcedimientoVO) {
		this.bandejaProcedimientoVO = bandejaProcedimientoVO;
	}
    public BandejasParamVO getBandejasParam() {
        return bandejasParam;
    }
    public void setBandejasParam(BandejasParamVO bandejasParam) {
        this.bandejasParam = bandejasParam;
    }
    public InfoDevolucionVO getInfoDevoluciones() {
        return infoDevoluciones;
    }
    public void setInfoDevoluciones(InfoDevolucionVO infoDevoluciones) {
        this.infoDevoluciones = infoDevoluciones;
    }
    public ConceptoACVO getConceptoACVO() {
        return conceptoACVO;
    }
    public void setConceptoACVO(ConceptoACVO conceptoACVO) {
        this.conceptoACVO = conceptoACVO;
    }
    public List<DocumentoSoporteVO> getAdjuntos() {
        return adjuntos;
    }
    public void setAdjuntos(List<DocumentoSoporteVO> adjuntos) {
        this.adjuntos = adjuntos;
    }
    public InfoBandejaMedicamentoVO getBandejaMedicamentoVO() {
        return bandejaMedicamentoVO;
    }
    public void setBandejaMedicamentoVO(InfoBandejaMedicamentoVO bandejaMedicamentoVO) {
        this.bandejaMedicamentoVO = bandejaMedicamentoVO;
    }
    public List<DocumentoSoporteVO> getAdjuntosItem() {
        return adjuntosItem;
    }
    public void setAdjuntosItem(List<DocumentoSoporteVO> adjuntosItem) {
        this.adjuntosItem = adjuntosItem;
    }
	public InfoBandejaInsumoVO getBandejaInsumoVO() {
		return bandejaInsumoVO;
	}
	public void setBandejaInsumoVO(InfoBandejaInsumoVO bandejaInsumoVO) {
		this.bandejaInsumoVO = bandejaInsumoVO;
	}
 
}
