package com.conexia.saludcoop.web.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;

import com.conexia.saludcoop.common.dto.DescriptivoDto;
import com.conexia.saludcoop.common.dto.DiagnosticoDto;
import com.conexia.saludcoop.common.dto.EpsDto;
import com.conexia.saludcoop.common.dto.InsumoDto;
import com.conexia.saludcoop.common.dto.IpsDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.TipoIdentificacionDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaInsumoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaMedicamentoDto;
import com.conexia.saludcoop.common.dto.transaccional.FormulaProcedimientoDto;
import com.conexia.saludcoop.common.entity.maestro.CriterioNegacion;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.maestro.NivelAutorizacion;
import com.conexia.saludcoop.common.entity.maestro.Procedimiento;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;
import com.conexia.saludcoop.common.entity.transaccional.Autorizacion;
import com.conexia.saludcoop.common.entity.transaccional.ConceptoAutorizacion;
import com.conexia.saludcoop.common.entity.transaccional.DocumentoSoporte;
import com.conexia.saludcoop.common.entity.transaccional.FormularioCTCInsumo;
import com.conexia.saludcoop.common.entity.transaccional.FormularioCTCMedicamento;
import com.conexia.saludcoop.common.entity.transaccional.FormularioCTCProcedimiento;
import com.conexia.saludcoop.common.entity.transaccional.MedicamentoPosPrevio;
import com.conexia.saludcoop.common.entity.transaccional.ProcedimientoPosPrevio;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.enumerator.OrigenSolicitud;
import com.conexia.saludcoop.common.enumerator.RegimenAfiliacion;
import com.conexia.saludcoop.common.enumerator.TipoFrecuencia;
import com.conexia.saludcoop.common.util.ConverterUtil;
import com.conexia.saludcoop.common.util.SystemConstants;
import com.conexia.saludcoop.util.DateUtilities;

/**
 * Clase utilitaria para realizar la conversión de objetos DTO a objetos VO
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 08/10/2013
 * 
 */
public class VOUtils {

    /*
     * Constantes usadas para determinar el tipo de bandeja para la que se realizará el mapeo para la gestión del item seleccionada en
     * alguna de las bandejas
     */
    public static final int BANDEJA_AC = 1;
    public static final int BANDEJA_CTC = 2;
    public static final int BANDEJA_CS = 4;
    public static final int BANDEJA_IPS_PRESTADOR = 3;
    public static final int BANDEJA_CONTACT_CENTER = 5;

    private static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Convierte una lista de Objetos EpsDto a una lista de objetos EpsVO
     * 
     * @param dtos
     * @return
     */
    public static List<EpsVO> toEpsVO(List<EpsDto> dtos) {
        List<EpsVO> vos = new ArrayList<>();
        for (EpsDto dto : dtos) {
            EpsVO vo = new EpsVO();
            BeanUtils.copyProperties(dto, vo);
            TipoIdentificacionDto tipoId = dto.getTipoIdentificacion();

            if (tipoId != null) {
                vo.setDescripcionTipoIdentificacion(tipoId.getDescripcion());
                vo.setIdTipoIdentificacion(tipoId.getId());
            }

            vos.add(vo);
        }
        return vos;
    }

    /**
     * Convierte una lista de Objetos IpsDto a una lista de objetos IpsVO
     * 
     * @param dtos
     * @return
     */
    public static List<IpsVO> toIpsVO(List<IpsDto> dtos) {
        List<IpsVO> vos = new ArrayList<>();
        for (IpsDto dto : dtos) {
            IpsVO vo = new IpsVO();
            vo.setDireccion(dto.getDireccion());
            vo.setId(dto.getId());
            vo.setNumeroIdentificacion(dto.getNumeroIdentificacion());
            vo.setRazonSocial(dto.getRazonSocial());
            vo.setTelefono(dto.getTelefono());
            TipoIdentificacionDto tipoId = dto.getTipoIdentificacion();

            if (tipoId != null) {
                vo.setTipoIdentificacion(tipoId.getDescripcion());
            }

            vos.add(vo);
        }
        return vos;
    }

    /**
     * Convierte una lista de Objetos SedeIpsDto a una lista de objetos SedeIpsVO
     * 
     * @param dtos
     * @return
     */
    public static List<SedeIpsVO> toSedeIpsVO(List<SedeIpsDto> dtos) {
        List<SedeIpsVO> vos = new ArrayList<>();
        for (SedeIpsDto dto : dtos) {
            SedeIpsVO vo = new SedeIpsVO();
            vo.setRazonSocial(dto.getNombre());
            vo.setId(dto.getId());
            vo.setDireccion(dto.getDireccion());
            vo.setTelefono1(dto.getTelefono1());
            vo.setMunicipioNombre(dto.getMunicipio().getDescripcion());
            vo.setNombreIps(dto.getNombre());
            vos.add(vo);
        }
        return vos;
    }

    /**
     * Convierte una instancia de la clase SedeIps a una instancia de SedeIpsVO
     * 
     * @param dtos
     * @return
     */
    public static SedeIpsVO toSedeIpsVO(SedeIps sede) {
        SedeIpsVO vo = new SedeIpsVO();

        if (sede != null) {
            vo.setRazonSocial(sede.getNombre());
            vo.setId(sede.getId());
            vo.setDireccion(sede.getDireccion());
            vo.setTelefono1(sede.getTelefono1());
            vo.setMunicipioNombre(sede.getLocalidad() != null ? sede.getLocalidad().getMunicipio().getDescripcion() : sede.getMunicipio()
                    .getDescripcion());
        }
        return vo;
    }

    /**
     * Convierte una lista de Objetos DiagnosticoDto a una lista de objetos DiagnosticoVO
     * 
     * @param dtos
     * @return
     */
    public static List<DiagnosticoVO> toDiagnosticoVO(List<DiagnosticoDto> dtos) {
        List<DiagnosticoVO> vos = new ArrayList<>();
        for (DiagnosticoDto dto : dtos) {
            DiagnosticoVO vo = new DiagnosticoVO();
            vo.setId(dto.getId());
            vo.setCodigo(dto.getCodigo());
            vo.setDescripcion(dto.getDescripcion());
            vos.add(vo);
        }
        return vos;
    }

    /**
     * Convierte una lista de MedicamentoDtos a Medicamnetos VO
     * 
     * @param dtos
     * @return
     */
    public static List<MedicamentoVO> toMedicamentoVO(List<MedicamentoDto> dtos) {
        List<MedicamentoVO> vos = new ArrayList<>();
        for (MedicamentoDto dto : dtos) {
            MedicamentoVO vo = new MedicamentoVO();
            vo.setId(dto.getId());
            vo.setCodigo(dto.getCodigo());
            vo.setDescripcion(dto.getDescripcion());
            vos.add(vo);
        }
        return vos;
    }

    /**
     * Convierte una lista de MedicamentoDtos a Medicamnetos VO
     * 
     * @param dtos
     * @return
     */
    public static MedicamentoVO toOneMedicamentoVO(MedicamentoDto dto) {

        MedicamentoVO vo = new MedicamentoVO();
        vo.setId(dto.getId());
        vo.setCodigo(dto.getCodigo());
        vo.setDescripcion(dto.getDescripcion());

        return vo;
    }

    /**
     * Convierte un DiagnosticoDto a un objeto DiagnosticoVO
     * 
     * @param dto
     * @return
     */
    public static DiagnosticoVO toDiagnosticoVO(DiagnosticoDto dto) {
        DiagnosticoVO vo = new DiagnosticoVO();
        vo.setId(dto.getId());
        vo.setCodigo(dto.getCodigo());
        vo.setDescripcion(dto.getDescripcion());

        return vo;
    }

    /**
     * Transforma los items consultados en base de datos al VO correspondiente
     * 
     * @param items
     *            Lista de SolicitudItem consultados para las bandejas de CTC y alto costo
     * @return Lista de VOs correspondientes para visualizar la información consultada en pantalla.
     */
    public static List<BandejaItemVO> toBandeja(List<SolicitudItem> items) {

        List<BandejaItemVO> solicitudesVo = new ArrayList<BandejaItemVO>();

        // Se utiliza para guardar las solicitudes por su número y no repetirlas en la lista
        Map<Long, BandejaItemVO> sols = new TreeMap<>();

        // Se recorren los items retornados por la consulta y se crean los VOs correspondientes
        for (SolicitudItem item : items) {

            // Si la solicitud aún no existe se crea
            if (!sols.containsKey(item.getSolicitud().getNroSolicitud())) {
                BandejaItemVO solVO = new BandejaItemVO();
                solVO.setNombreAfiliado((item.getSolicitud().getAfiliado().getTipoIdentificacion().getCodigo() + " "
                        + item.getSolicitud().getAfiliado().getNumeroIdentificacion() + " "
                        + item.getSolicitud().getAfiliado().getPrimerNombre() + " " + item.getSolicitud().getAfiliado().getSegundoNombre()
                        + " " + item.getSolicitud().getAfiliado().getPrimerApellido() + " " + item.getSolicitud().getAfiliado()
                        .getSegundoApellido()));
                solVO.setEps(item.getSolicitud().getAfiliado().getEps().getRazonSocial());
                solVO.setFechaCreacionSolicitud(df.format(item.getSolicitud().getFechaCreacion()));
                solVO.setNumeroSolicitud(item.getSolicitud().getNroSolicitud().toString());
                solVO.setProfesionalSolicitante(item.getSolicitud().getProfesionalSolicitante().getPrimerNombre() + " "
                        + item.getSolicitud().getProfesionalSolicitante().getSegundoNombre() + " "
                        + item.getSolicitud().getProfesionalSolicitante().getPrimerApellido() + " "
                        + item.getSolicitud().getProfesionalSolicitante().getSegundoApellido());

                if (item.getSolicitud().getAfiliado().getProgramas() != null && !item.getSolicitud().getAfiliado().getProgramas().isEmpty()) {
                    solVO.setPrograma(item.getSolicitud().getAfiliado().getProgramas().iterator().next().getPrograma().getDescripcion());
                }
                solVO.setRegional(item.getSolicitud().getSedeIps().getLocalidad().getRegional().getDescripcion());
                List<BandejaSubItemVO> itemsVO = new ArrayList<BandejaSubItemVO>();
                solVO.setSubitems(itemsVO);
                sols.put(item.getSolicitud().getId(), solVO);
                solicitudesVo.add(solVO);
            }

            // Se crean y agregan los items correspondientes a cada solicitud
            BandejaItemVO solVO = sols.get(item.getSolicitud().getId());
            BandejaSubItemVO itemVO = new BandejaSubItemVO();
            itemVO.setDiagnostico(item.getDiagnostico().getDiagnostico().getDescripcion());
            itemVO.setEstado(item.getAutorizacion().getEstadoAutorizacion().getDescripcion());
            itemVO.setFechaModificacion(df.format(item.getSolicitud().getFechaCreacion()));
            itemVO.setNumero(item.getNroItem().toString());
            itemVO.setTecnologia(item.getSolMedicamento() == null ? "Procedimiento" : "Medicamento");
            itemVO.setTipoSolicitud(item.getTipoPPM().getDescripcion());
            itemVO.setServicioSolicitado(item.getSolMedicamento() == null ? item.getSolProcedimiento().getProcedimiento().getDescripcion()
                    : item.getSolMedicamento().getMedicamento().getDescripcion());
            solVO.getSubitems().add(itemVO);
        }

        return solicitudesVo;
    }

    private static String getOrigen(SolicitudItem solicitudItem) {
        String origen = "";
        Boolean tutela = (solicitudItem.getAplicaTutela() != null) ? solicitudItem.getAplicaTutela() : Boolean.FALSE;
        if (tutela) {
            origen = OrigenSolicitud.TUTELA.getCode();
        } else {
            if (solicitudItem.getSolMedicamento() != null) {
                Medicamento m = solicitudItem.getSolMedicamento().getMedicamento();
                if (!m.getVisibleCtc() && !m.getAltoCosto()) {
                    origen = OrigenSolicitud.POS.getCode();
                } else if (m.getVisibleCtc() && !m.getAltoCosto()) {
                    origen = OrigenSolicitud.CTC.getCode();
                } else if (m.getAltoCosto() && Short.valueOf("1").equals(m.getInsumo())) {
                    origen = OrigenSolicitud.INSUMOS_ALTO_COSTO.getCode();
                }
            } else if (solicitudItem.getSolProcedimiento() != null) {
                Procedimiento p = solicitudItem.getSolProcedimiento().getProcedimiento();
                Integer nivelAut = p.getNivelAutorizacion();
                if (nivelAut == NivelAutorizacion.NIVEL_1 || nivelAut == NivelAutorizacion.NIVEL_2) {
                    origen = OrigenSolicitud.POS.getCode();
                } else if (nivelAut == 5) {
                    origen = OrigenSolicitud.CTC.getCode();
                }

            }
        }

        return origen;
    }

    /**
     * Realiza el mapeo del item a la bandeja correspondiente de acuerdo al tipo de bandeja indicada por parámetro. Se retorna una instancia
     * de {@link BandejasVO} con los VOs correspondientes mapeados.
     * 
     * @param item
     *            Item a partir del cual se realizará el mapping para mostrar la información en pantalla.
     * @param tipoAuditor
     *            Indica el tipo de auditor para el que se realizará el mapeo, se pueden usar las constantes: {@link VOUtils#BANDEJA_AC} ,
     *            {@link VOUtils#BANDEJA_CTC} y {@link VOUtils#BANDEJA_CS}
     * @param tipoBandeja
     *            Indica el tipo de bandeja para el que se realizará el mapeo, se pueden usar las constantes:
     *            {@link SystemConstants#BANDEJA_NACIONAL} y {@link SystemConstants#BANDEJA_REGIONAL}
     * @return
     */
    public static BandejasVO toGestionBandejas(SolicitudItem item, int tipoAuditor, String tipoBandeja) {
        BandejasVO bandejasVO = new BandejasVO();
        bandejasVO.setInfoGeneralVO(toInfoGeneralBandejaIniVO(item));
        bandejasVO.setDiagnosticoBandeja(toDiagnosticoVO(Collections.singletonList(item.getDiagnostico().getDiagnostico().toDto())).get(0));
        bandejasVO.setDireccionamientoVO(toSedeIpsVO(item.getAutorizacion().getSedeIpsEfectora()));

        if (tipoBandeja == null) {
            tipoBandeja = "";
        }

        // Se establece el tipo de item
        if (item.getSolMedicamento() != null) {
            bandejasVO.setTipoItem(SystemConstants.ITEM_MEDICAMENTO);
        } else if (item.getSolProcedimiento() != null) {
            bandejasVO.setTipoItem(SystemConstants.ITEM_PROCEDIMIENTO);
        }  else if (item.getSolInsumo() != null) {
            bandejasVO.setTipoItem(SystemConstants.ITEM_INSUMO);
        }

        // Se crea el objeto de parametrización de las bandejas
        BandejasParamVO bandejasParam = new BandejasParamVO();
        bandejasParam.setEstado(item.getAutorizacion().getEstadoAutorizacion().getId());
        bandejasVO.setBandejasParam(bandejasParam);
        bandejasVO.setAdjuntos(getDocumentosSolicitud(item));
        bandejasVO.setAdjuntosItem(getDocumentosItem(item));

        // Se verifica el tipo de bandeja para el que se requiere hacer el mapeo
        if (BANDEJA_AC == tipoAuditor) {
            bandejasVO.setBandejaAltoCostoVO(toBandejaAltoCostoVO(item));
            bandejasVO.setConceptoACVO(toConceptoACVO(item, tipoBandeja));
            bandejasVO.setInfoDevoluciones(toInfoDevolucionACVO(item, tipoBandeja));
            bandejasVO.setInfoSolicitudBandejaVO(toInfoSolicitudBandejaVO_AC(item, bandejasVO.getBandejaAltoCostoVO().getTipoItem()));
        } else if (BANDEJA_CS == tipoAuditor) {
            bandejasVO.setBandejaContactServiceVO(toBandejaContactServiceVO(item));
            bandejasVO.setConceptoCSVO(toConceptoCSVO(item, tipoBandeja));
            bandejasVO.setInfoDevoluciones(toInfoDevolucionCSVO(item, tipoBandeja));
            bandejasVO.setInfoSolicitudBandejaVO(toInfoSolicitudBandejaVO_CS(item, bandejasVO.getBandejaContactServiceVO().getTipoItem()));
        } else if (BANDEJA_CTC == tipoAuditor) {
            bandejasVO.setInfoSolicitudBandejaVO(toInfoSolicitudBandejaVO(item));
            bandejasParam.setEditableAdjuntos(true);
            if (SystemConstants.ITEM_MEDICAMENTO == bandejasVO.getTipoItem()) {
                bandejasVO.setBandejaMedicamentoVO(toInfoBandejaMedicamentoVO(item));
            } else if(SystemConstants.ITEM_PROCEDIMIENTO == bandejasVO.getTipoItem() ){
                bandejasVO.setBandejaProcedimientoVO(toInfoBandejaProcedimientoVO(item));
            } else if(SystemConstants.ITEM_INSUMO == bandejasVO.getTipoItem()  ){
            	bandejasVO.setBandejaInsumoVO(toInfoBandejaInsumoVO(item));
            }
            bandejasVO.setConceptoCTCVO(toConceptoCTCVO(item, tipoBandeja));
            bandejasVO.setInfoDevoluciones(toInfoDevolucionCTCVO(item, tipoBandeja));
        } else if (BANDEJA_IPS_PRESTADOR == tipoAuditor) {
            bandejasVO.setInfoGeneralVO(toInfoGeneralBandejaIniVO(item));
            bandejasVO.setBandejaIpsPrestadorVO(toBandejaIpsPrestadorVO(item));
        } else if (BANDEJA_CONTACT_CENTER == tipoAuditor) {
            bandejasVO.setInfoGeneralVO(toInfoGeneralBandejaIniVO(item));
            bandejasVO.setBandejaIpsPrestadorVO(toBandejaIpsPrestadorVO(item));
        }
        return bandejasVO;
    }



	private static InfoBandejaInsumoVO toInfoBandejaInsumoVO(SolicitudItem item) {
        InfoBandejaInsumoVO bandejaInsumoVO = new InfoBandejaInsumoVO();
        FormularioCTCInsumo formularioCTCInsumo = item.getSolInsumo().getFormCTCInsumo();

        if (item.getSolInsumo() != null) {

            FormulaInsumoDto formulaDto = item.getSolInsumo().getFormulaInsumo().toDto();

            bandejaInsumoVO.setCantidadInsumoSolicitado(item.getCantidad());
            bandejaInsumoVO.setInsumoSolicitado(new InsumoCTCVO(item.getSolInsumo().getInsumo().toDto(),
                    formulaDto.getCantidad(),  formulaDto.getDuracion()));

            if (formularioCTCInsumo != null) {
                bandejaInsumoVO.setTieneFormCTC(true);
//                bandejaInsumoVO.setSinAlternativaPOSPrev(formularioCTCInsumo.isSinAlternativaPos());
//                if (bandejaInsumoVO.isSinAlternativaPOSPrev()) {
//                    bandejaInsumoVO.setJustificacionSinPOSPrev(formularioCTCInsumo.getJustificacionSinPosPrevio());
//                } else {
//                    List<InsumoPosPrevioVO> insumoPosPrevioVOs = new ArrayList<InsumoPosPrevioVO>();
//                    for (InsumoPosPrevio insumosPrevios : formularioCTCInsumo.getInsumosAnteriores()) {
//                        insumoPosPrevioVOs.add(new InsumoPosPrevioVO(insumosPrevios.toDto()));
//                    }
//                    bandejaInsumoVO.setInsumosAnteriores(insumoPosPrevioVOs);
//                }
                String respuesta = ConverterUtil.getStringSI_NOFromBoolean(formularioCTCInsumo.getExisteRiesgoInminente());
                bandejaInsumoVO.setRiesgoInminente(respuesta);
                respuesta = ConverterUtil.getStringSI_NOFromBoolean(formularioCTCInsumo.getPosibilidadesPosAgotadas());
                bandejaInsumoVO.setJustificacionRiesgo(formularioCTCInsumo.getJustificacionRiesgoInminente());
                bandejaInsumoVO.setPosibilidadesAgotadas(respuesta);
                respuesta = ConverterUtil.getStringSI_NO_NOSABEFromStringNumber(formularioCTCInsumo.getAutorizadoINVIMA());
                
                bandejaInsumoVO.setJustificacionMedico(formularioCTCInsumo.getJustificacionMedico());
                
                // TODO: SE ESTÁ SETEANDO EL MISMO MEDICAMENTO SOLICITADO EN EL HOMOLOGO PARA QUE TOME LOS VALORES (DOSIS, FRECUENCIA,
                // DURACIÓN) DEBIDO A QUE ESTOS NO SE ESTÁN ALMANCENANDO EN LA BD
                bandejaInsumoVO.setInsumoHomologo(bandejaInsumoVO.getInsumoSolicitado());
                // SE MODIFICA EL MEDICAMENTO POR EL SELECCIONADO EN EL FORMULARIO DE SOLICITUD
//                bandejaInsumoVO.getInsumoHomologo().setInsumo(
//                        new ProcedimientoMedicamentoVO(formularioCTCInsumo.getInsumoHomologo().toDto()));
                // LA CANTIDAD DEL MEDICAMENTO HOMOLOGO TAMPOCO ES ALMACENADA ASÍ QUE SE TOMA LA MISMA DEL MEDICAMENTO SOLICITADO
                bandejaInsumoVO.setCantidadInsumoHomologo(bandejaInsumoVO.getCantidadInsumoSolicitado());
                // if (item.getSolInsumo().getFormCTCInsumo().getInsumoHomologo() != null) {
                //
                // } else {
                //
                // }
            }
        }

        return bandejaInsumoVO;
	}

	/**
     * Obtiene los documentos asociados a la solicitud del item pasado por parámetro
     * 
     * @param item
     * @return
     */
    private static List<DocumentoSoporteVO> getDocumentosSolicitud(SolicitudItem item) {
        List<DocumentoSoporteVO> docs = new ArrayList<>();
        for (DocumentoSoporte doc : item.getSolicitud().getDocumentosSoporte()) {
            DocumentoSoporteVO adj = new DocumentoSoporteVO();
            adj.setId(doc.getId());
            adj.setTipoSoporte(doc.getTipoDocSoporte().getDescripcion());
            adj.setNombreArchivoServidor(doc.getNombreArchivoServidor());
            adj.setNombreArchivoOriginal(doc.getNombreArchivoOriginal());
            docs.add(adj);
        }
        return docs;
    }

    /**
     * Obtiene los documentos asociados al item pasado por parámetro
     * 
     * @param item
     * @return
     */
    private static List<DocumentoSoporteVO> getDocumentosItem(SolicitudItem item) {
        List<DocumentoSoporteVO> docs = new ArrayList<>();
        for (DocumentoSoporte doc : item.getDocumentosSoporte()) {
            DocumentoSoporteVO adj = new DocumentoSoporteVO();
            adj.setId(doc.getId());
            adj.setTipoSoporte(doc.getTipoDocSoporte().getDescripcion());
            adj.setNombreArchivoServidor(doc.getNombreArchivoServidor());
            adj.setNombreArchivoOriginal(doc.getNombreArchivoOriginal());
            docs.add(adj);
        }
        return docs;
    }

    private static InfoSolicitudBandejaVO toInfoSolicitudBandejaVO_AC(SolicitudItem item, String tipoItem) {
        InfoSolicitudBandejaVO vo = new InfoSolicitudBandejaVO();

        if(item.getSolMedicamento() != null) {
            if (item.getSolMedicamento().getFormulaMedicamento().getFinalidad() != null) {
                vo.setFinalidadSeleccionada(item.getSolMedicamento().getFormulaMedicamento().getFinalidad().getId());
            } else {
                vo.setFinalidadSeleccionada(SystemConstants.FINALIDAD_MEDI_AC);
            }
            if (item.getSolMedicamento().getFormulaMedicamento().getCausaExterna() != null) {
                vo.setCausaExternaSeleccionada(item.getSolMedicamento().getFormulaMedicamento().getCausaExterna().getId());
            } else {
                vo.setCausaExternaSeleccionada(SystemConstants.CAUSA_EXTERNA_AC);                    
            }
            vo.setTipoCatastroficoSeleccionado(item.getSolMedicamento().getFormulaMedicamento().getTipoCatastrofico().getId());
        } else if(item.getSolProcedimiento() != null) {
            if (item.getSolProcedimiento().getFormulaProcedimiento().getFinalidad() != null) {
                vo.setFinalidadSeleccionada(item.getSolProcedimiento().getFormulaProcedimiento().getFinalidad().getId());
            } else {
                vo.setFinalidadSeleccionada(SystemConstants.FINALIDAD_PROC_AC);
            }
            if (item.getSolProcedimiento().getFormulaProcedimiento().getCausaExterna() != null) {
                vo.setCausaExternaSeleccionada(item.getSolProcedimiento().getFormulaProcedimiento().getCausaExterna().getId());
            } else {
                vo.setCausaExternaSeleccionada(SystemConstants.CAUSA_EXTERNA_AC);                    
            }
            vo.setTipoCatastroficoSeleccionado(item.getSolProcedimiento().getFormulaProcedimiento().getTipoCatastrofico().getId());
        } else if(item.isInsumo()){
        	 if (item.getSolInsumo().getFormulaInsumo().getFinalidad() != null) {
                 vo.setFinalidadSeleccionada(item.getSolInsumo().getFormulaInsumo().getFinalidad().getId());
             } else {
                 vo.setFinalidadSeleccionada(SystemConstants.FINALIDAD_MEDI_AC);
             }
             if (item.getSolInsumo().getFormulaInsumo().getCausaExterna() != null) {
                 vo.setCausaExternaSeleccionada(item.getSolInsumo().getFormulaInsumo().getCausaExterna().getId());
             } else {
                 vo.setCausaExternaSeleccionada(SystemConstants.CAUSA_EXTERNA_AC);                    
             }
             vo.setTipoCatastroficoSeleccionado(item.getSolInsumo().getFormulaInsumo().getTipoCatastrofico().getId());
        }
        if (item.getAutorizacion().getEntidadRecobro() != null) {
            vo.setEntidadSeleccionada(item.getAutorizacion().getEntidadRecobro().getId());
        } else {
            vo.setEntidadSeleccionada(SystemConstants.ENTIDAD_RECOBRO_AC);
        }
        return vo;
    }

    private static InfoSolicitudBandejaVO toInfoSolicitudBandejaVO_CS(SolicitudItem item, String tipoItem) {
        InfoSolicitudBandejaVO vo = new InfoSolicitudBandejaVO();
        if(item.getSolMedicamento() != null) {
            if (item.getSolMedicamento().getFormulaMedicamento().getFinalidad() != null) {
                vo.setFinalidadSeleccionada(item.getSolMedicamento().getFormulaMedicamento().getFinalidad().getId());
            } else {
                vo.setFinalidadSeleccionada(SystemConstants.FINALIDAD_MEDI_AC);
            }
            if (item.getSolMedicamento().getFormulaMedicamento().getCausaExterna() != null) {
                vo.setCausaExternaSeleccionada(item.getSolMedicamento().getFormulaMedicamento().getCausaExterna().getId());
            } else {
                vo.setCausaExternaSeleccionada(SystemConstants.CAUSA_EXTERNA_AC);                    
            }
            if (item.getSolMedicamento().getFormulaMedicamento().getTipoCatastrofico() != null) {
                vo.setTipoCatastroficoSeleccionado(item.getSolMedicamento().getFormulaMedicamento().getTipoCatastrofico().getId());
            } else {
                // TODO: PENDIENTE POR VALIDAR SI HAY QUE HACER HOMOLOGACIÓN DEL PROGRAMA_MEDICAMENTO CON TIPO CATASTROFICO
                vo.setTipoCatastroficoSeleccionado(item.getSolMedicamento().getMedicamento().getProgramaMedicamentoAltoCosto().getId());
            }
        } else if(item.getSolProcedimiento() != null) {
            if (item.getSolProcedimiento().getFormulaProcedimiento().getFinalidad() != null) {
                vo.setFinalidadSeleccionada(item.getSolProcedimiento().getFormulaProcedimiento().getFinalidad().getId());
            } else {
                vo.setFinalidadSeleccionada(SystemConstants.FINALIDAD_PROC_AC);
            }
            if (item.getSolProcedimiento().getFormulaProcedimiento().getCausaExterna() != null) {
                vo.setCausaExternaSeleccionada(item.getSolProcedimiento().getFormulaProcedimiento().getCausaExterna().getId());
            } else {
                vo.setCausaExternaSeleccionada(SystemConstants.CAUSA_EXTERNA_AC);                    
            }
            if (item.getSolProcedimiento().getFormulaProcedimiento().getTipoCatastrofico() != null) {
                vo.setTipoCatastroficoSeleccionado(item.getSolProcedimiento().getFormulaProcedimiento().getTipoCatastrofico().getId());
            } else {
                vo.setTipoCatastroficoSeleccionado(SystemConstants.TIPO_CATASTROFICO_PROC_AC);
            }
        } else if(item.isInsumo()){
        	 if (item.getSolInsumo().getFormulaInsumo().getFinalidad() != null) {
                 vo.setFinalidadSeleccionada(item.getSolInsumo().getFormulaInsumo().getFinalidad().getId());
             } else {
                 vo.setFinalidadSeleccionada(SystemConstants.FINALIDAD_MEDI_AC);
             }
             if (item.getSolInsumo().getFormulaInsumo().getCausaExterna() != null) {
                 vo.setCausaExternaSeleccionada(item.getSolInsumo().getFormulaInsumo().getCausaExterna().getId());
             } else {
                 vo.setCausaExternaSeleccionada(SystemConstants.CAUSA_EXTERNA_AC);                    
             }
             if (item.getSolInsumo().getFormulaInsumo().getTipoCatastrofico() != null) {
                 vo.setTipoCatastroficoSeleccionado(item.getSolInsumo().getFormulaInsumo().getTipoCatastrofico().getId());
             } else {
                 // TODO: PENDIENTE POR VALIDAR SI HAY QUE HACER HOMOLOGACIÓN DEL PROGRAMA_MEDICAMENTO CON TIPO CATASTROFICO
//                 vo.setTipoCatastroficoSeleccionado(item.getSolInsumo().getInsumo().getProgramaInsumoAltoCosto().getId());
             }
        }
        if (item.getAutorizacion().getEntidadRecobro() != null) {
            vo.setEntidadSeleccionada(item.getAutorizacion().getEntidadRecobro().getId());
        } else {
            vo.setEntidadSeleccionada(SystemConstants.ENTIDAD_RECOBRO_AC);
        }
        return vo;
    }

    private static InfoBandejaMedicamentoVO toInfoBandejaMedicamentoVO(SolicitudItem item) {
        InfoBandejaMedicamentoVO bandejaMedicamentoVO = new InfoBandejaMedicamentoVO();
        FormularioCTCMedicamento formularioCTCMedicamento = item.getSolMedicamento().getFormCTCMedicamento();

        if (item.getSolMedicamento() != null) {

            FormulaMedicamentoDto formulaDto = item.getSolMedicamento().getFormulaMedicamento().toDto();

            bandejaMedicamentoVO.setCantidadMedicamentoSolicitado(item.getCantidad());
            bandejaMedicamentoVO.setMedicamentoSolicitado(new MedicamentoCTCVO(item.getSolMedicamento().getMedicamento().toDto(),
                    formulaDto.getDosis(), formulaDto.getFrecuencia(), formulaDto.getDuracion(), 
                    formulaDto.getViaAdministracion(), formulaDto.getEfectosAdversos(), formulaDto.getTipoFrecuencia()));

            if (formularioCTCMedicamento != null) {
                bandejaMedicamentoVO.setTieneFormCTC(true);
                bandejaMedicamentoVO.setSinAlternativaPOSPrev(formularioCTCMedicamento.isSinAlternativaPos());
                if (bandejaMedicamentoVO.isSinAlternativaPOSPrev()) {
                    bandejaMedicamentoVO.setJustificacionSinPOSPrev(formularioCTCMedicamento.getJustificacionSinPosPrevio());
                } else {
                    List<MedicamentoPosPrevioVO> medicamentoPosPrevioVOs = new ArrayList<MedicamentoPosPrevioVO>();
                    for (MedicamentoPosPrevio medicamentosPrevios : formularioCTCMedicamento.getMedicamentosAnteriores()) {
                        medicamentoPosPrevioVOs.add(new MedicamentoPosPrevioVO(medicamentosPrevios.toDto()));
                    }
                    bandejaMedicamentoVO.setMedicamentosAnteriores(medicamentoPosPrevioVOs);
                }
                String respuesta = ConverterUtil.getStringSI_NOFromBoolean(formularioCTCMedicamento.getExisteRiesgoInminente());
                bandejaMedicamentoVO.setRiesgoInminente(respuesta);
                respuesta = ConverterUtil.getStringSI_NOFromBoolean(formularioCTCMedicamento.getPosibilidadesPosAgotadas());
                bandejaMedicamentoVO.setJustificacionRiesgo(formularioCTCMedicamento.getJustificacionRiesgoInminente());
                bandejaMedicamentoVO.setPosibilidadesAgotadas(respuesta);
                respuesta = ConverterUtil.getStringSI_NO_NOSABEFromStringNumber(formularioCTCMedicamento.getAutorizadoINVIMA());
                bandejaMedicamentoVO.setAutorizacionINVIMA(respuesta);
                bandejaMedicamentoVO.setJustificacionMedico(formularioCTCMedicamento.getJustificacionMedico());
                bandejaMedicamentoVO.setPosologia(item.getSolMedicamento().getFormulaMedicamento().getPosologia());

                // TODO: SE ESTÁ SETEANDO EL MISMO MEDICAMENTO SOLICITADO EN EL HOMOLOGO PARA QUE TOME LOS VALORES (DOSIS, FRECUENCIA,
                // DURACIÓN) DEBIDO A QUE ESTOS NO SE ESTÁN ALMANCENANDO EN LA BD
                bandejaMedicamentoVO.setMedicamentoHomologo(bandejaMedicamentoVO.getMedicamentoSolicitado());
                // SE MODIFICA EL MEDICAMENTO POR EL SELECCIONADO EN EL FORMULARIO DE SOLICITUD
                bandejaMedicamentoVO.getMedicamentoHomologo().setMedicamento(
                        new ProcedimientoMedicamentoVO(formularioCTCMedicamento.getMedicamentoHomologo().toDto()));
                // LA CANTIDAD DEL MEDICAMENTO HOMOLOGO TAMPOCO ES ALMACENADA ASÍ QUE SE TOMA LA MISMA DEL MEDICAMENTO SOLICITADO
                bandejaMedicamentoVO.setCantidadMedicamentoHomologo(bandejaMedicamentoVO.getCantidadMedicamentoSolicitado());
                // if (item.getSolMedicamento().getFormCTCMedicamento().getMedicamentoHomologo() != null) {
                //
                // } else {
                //
                // }
            } else {
                // se debe mostrar el homologo cuando viene de linea de frente
                Medicamento homologo = item.getSolMedicamento().getMedicamento().getHomologo() ;                
                if(homologo != null){
                    bandejaMedicamentoVO.setMedicamentoHomologo(new MedicamentoCTCVO(homologo.toDto() ,
                            formulaDto.getDosis(), formulaDto.getFrecuencia(), formulaDto.getDuracion(), 
                            formulaDto.getViaAdministracion(), formulaDto.getEfectosAdversos(), formulaDto.getTipoFrecuencia()));
                }
                
            }
        }

        return bandejaMedicamentoVO;
    }

    private static InfoBandejaProcedimientoVO toInfoBandejaProcedimientoVO(SolicitudItem item) {
        InfoBandejaProcedimientoVO bandejaProcedimientoVO = new InfoBandejaProcedimientoVO();
        FormularioCTCProcedimiento formularioCTCProcedimiento = item.getSolProcedimiento().getFormCTCProcedimiento();
        if (formularioCTCProcedimiento != null) {
            bandejaProcedimientoVO.setTieneFormCTC(true);
            bandejaProcedimientoVO.setSinAlternativaPOSPrev(formularioCTCProcedimiento.isSinAlternativaPos());
            if (bandejaProcedimientoVO.isSinAlternativaPOSPrev()) {
                bandejaProcedimientoVO.setJustificacionSinPOSPrev(formularioCTCProcedimiento.getJustificacionSinPosPrevio());
            } else {
                List<ProcedimientoPosPrevioVO> posPrevioVOs = new ArrayList<ProcedimientoPosPrevioVO>();
                for (ProcedimientoPosPrevio procedimientosPrevios : item.getSolProcedimiento().getFormCTCProcedimiento().getProcedimientosAnteriores()) {
                    posPrevioVOs.add(new ProcedimientoPosPrevioVO(procedimientosPrevios.toDto()));
                }
                bandejaProcedimientoVO.setProcedimientosAnteriores(posPrevioVOs);                
            }
            String respuesta = ConverterUtil.getStringSI_NOFromBoolean(formularioCTCProcedimiento.isExisteRiesgoInminente());
            bandejaProcedimientoVO.setEsRiesgoParaVida(respuesta);
            bandejaProcedimientoVO.setJustificacionRiesgo(formularioCTCProcedimiento.getJustificacionRiesgoInminente());
            respuesta = ConverterUtil.getStringSI_NOFromBoolean(formularioCTCProcedimiento.isPosibilidadesPosAgotadas());
            bandejaProcedimientoVO.setSeAgotoPosibilTerapEnPOS(respuesta);
            respuesta = ConverterUtil.getStringSI_NO_NOSABEFromStringNumber(formularioCTCProcedimiento.getAutorizadoINVIMA());
            bandejaProcedimientoVO.setEsAutorizadoInvima(respuesta);
            bandejaProcedimientoVO.setJustificacionMedicoSolicitud(formularioCTCProcedimiento.getJustificacionMedico());
            bandejaProcedimientoVO.setJustificacionMedicoNoHomologo(formularioCTCProcedimiento.getJustificacionSinHomologo());
            if (formularioCTCProcedimiento.getProcedimientoHomologo() != null) {
                bandejaProcedimientoVO.setProcedimientoPOSHomologo(new ProcedimientoHomologoVO(formularioCTCProcedimiento
                        .getProcedimientoHomologo().toDto()));
            }
        }

        bandejaProcedimientoVO.setEspecialidadProcSolicitado(item.getSolProcedimiento().getEspecialidad().getDescripcion());
        bandejaProcedimientoVO.setProcedimientoSolicitado(new ProcedimientoMedicamentoVO(item.getSolProcedimiento().getProcedimiento()
                .toDto()));

        bandejaProcedimientoVO.setCantidadSolicitada(item.getCantidad().toString());
        FormulaProcedimientoDto formulaDto = item.getSolProcedimiento().getFormulaProcedimiento().toDto();
        if (formulaDto != null) {
            DescriptivoDto origenRepeticion = formulaDto.getOrigenRepeticion();
            bandejaProcedimientoVO.setOrigenRepeticion((origenRepeticion != null) ? origenRepeticion.getDescripcion() : "");
            DescriptivoDto tipoPrestacion = formulaDto.getTipoPrestacion();
            bandejaProcedimientoVO.setTipoPrestacion((tipoPrestacion != null) ? tipoPrestacion.getDescripcion() : "");
            DescriptivoDto objetivoProcedimiento = formulaDto.getObjetivo();
            bandejaProcedimientoVO.setObjetivoSolicitud((objetivoProcedimiento != null) ? objetivoProcedimiento.getDescripcion() : "");
            DescriptivoDto lateralidad = formulaDto.getLateralidad();
            bandejaProcedimientoVO.setLateralidad((lateralidad != null) ? lateralidad.getDescripcion() : "");
            bandejaProcedimientoVO.setIndicaciones(formulaDto.getPosologia());
        }

        return bandejaProcedimientoVO;
    }

    private static BandejaAltoCostoVO toBandejaAltoCostoVO(SolicitudItem item) {

        BandejaAltoCostoVO vo = new BandejaAltoCostoVO();

        vo.setCodigoDiagnostico(item.getDiagnostico().getDiagnostico().getCodigo());
        vo.setDescDiagnostico(item.getDiagnostico().getDiagnostico().getDescripcion());

        // Se verifica el tipo de item
        if (item.isMedicamento()) {
            vo.setTipoItem(SystemConstants.ITEM_MEDICAMENTO_STRING);
            BandejaAltoCostoMedicamentoVO medVO = new BandejaAltoCostoMedicamentoVO();
            medVO.setCantidadSolicitada(item.getCantidad());
            medVO.setCodigo(item.getSolMedicamento().getMedicamento().getCodigo());
            medVO.setDosis(item.getSolMedicamento().getFormulaMedicamento().getDosis());
            medVO.setDuracionTratamiento(item.getSolMedicamento().getFormulaMedicamento().getDuracion());

            String tipoFrecuencia = "Días";
            if (TipoFrecuencia.HORAS.getId().equals(item.getSolMedicamento().getFormulaMedicamento().getTipoFrecuenciaId())) {
                tipoFrecuencia = "Horas";
            }
            medVO.setTipoFrecuencia(tipoFrecuencia);

            medVO.setFrecuencia(item.getSolMedicamento().getFormulaMedicamento().getFrecuencia());
            medVO.setNombre(item.getSolMedicamento().getMedicamento().getDescripcion());
            medVO.setViaAdministracion(item.getSolMedicamento().getFormulaMedicamento().getViaAdministracion().getDescripcion());
            medVO.setPosologia(item.getSolMedicamento().getFormulaMedicamento().getPosologia());
            vo.setMedicamento(medVO);
        } else if(item.isProcedimiento()){
            vo.setTipoItem(SystemConstants.ITEM_PROCEDIMIENTO_STRING);
            BandejaAltoCostoProcedimientoVO procVO = new BandejaAltoCostoProcedimientoVO();
            procVO.setCantidadSolicitada(item.getCantidad());
            procVO.setCodigo(item.getSolProcedimiento().getProcedimiento().getCodigo());
            procVO.setNombre(item.getSolProcedimiento().getProcedimiento().getDescripcion());
            procVO.setLateralidad(item.getSolProcedimiento().getFormulaProcedimiento().getLateralidad().getDescripcion());
            vo.setProcedimiento(procVO);
        } else if(item.isInsumo()){
        	 vo.setTipoItem(SystemConstants.ITEM_INSUMO_STRING);
             BandejaAltoCostoInsumoVO insVO = new BandejaAltoCostoInsumoVO();
             insVO.setCantidadSolicitada(item.getCantidad());
             insVO.setCodigo(item.getSolInsumo().getInsumo().getCodigo());
             insVO.setDuracionTratamiento(item.getSolInsumo().getFormulaInsumo().getDuracion());

             insVO.setNombre(item.getSolInsumo().getInsumo().getDescripcion());
             vo.setInsumo(insVO);
        }
        // Se verifica si la solicitud fue devuelta previamente
        if (EstadoAutorizacion.DEVUELTA_REGIONAL.equals(item.getAutorizacion().getEstadoAutorizacion().getId())) {
            vo.setDevuelta(true);
        }
        return vo;
    }

    private static BandejaContactServiceVO toBandejaContactServiceVO(SolicitudItem item) {

        BandejaContactServiceVO vo = new BandejaContactServiceVO();

        vo.setCodigoDiagnostico(item.getDiagnostico().getDiagnostico().getCodigo());
        vo.setDescDiagnostico(item.getDiagnostico().getDiagnostico().getDescripcion());

        // Se verifica el tipo de item
        if (item.getSolMedicamento() != null) {
            vo.setTipoItem(SystemConstants.ITEM_MEDICAMENTO_STRING);
            BandejaContactServiceMedicamentoVO medVO = new BandejaContactServiceMedicamentoVO();
            medVO.setCantidadSolicitada(item.getCantidad());
            medVO.setCodigo(item.getSolMedicamento().getMedicamento().getCodigo());
            medVO.setDosis(item.getSolMedicamento().getFormulaMedicamento().getDosis());
            medVO.setDuracionTratamiento(item.getSolMedicamento().getFormulaMedicamento().getDuracion());

            String tipoFrecuencia = "Días";
            if (TipoFrecuencia.HORAS.getId().equals(item.getSolMedicamento().getFormulaMedicamento().getTipoFrecuenciaId())) {
                tipoFrecuencia = "Horas";
            }
            medVO.setTipoFrecuencia(tipoFrecuencia);

            medVO.setFrecuencia(item.getSolMedicamento().getFormulaMedicamento().getFrecuencia());
            medVO.setNombre(item.getSolMedicamento().getMedicamento().getDescripcion());
            medVO.setViaAdministracion(item.getSolMedicamento().getFormulaMedicamento().getViaAdministracion().getDescripcion());
            medVO.setPosologia(item.getSolMedicamento().getFormulaMedicamento().getPosologia());
            vo.setMedicamento(medVO);
        } else {
            vo.setTipoItem(SystemConstants.ITEM_PROCEDIMIENTO_STRING);
            BandejaContactServiceProcedimientoVO procVO = new BandejaContactServiceProcedimientoVO();
            procVO.setCantidadSolicitada(item.getCantidad());
            procVO.setCodigo(item.getSolProcedimiento().getProcedimiento().getCodigo());
            procVO.setNombre(item.getSolProcedimiento().getProcedimiento().getDescripcion());
            procVO.setLateralidad(item.getSolProcedimiento().getFormulaProcedimiento().getLateralidad().getDescripcion());
            vo.setProcedimiento(procVO);
        }

        // Se verifica si la solicitud fue devuelta previamente
        if (EstadoAutorizacion.DEVUELTA_REGIONAL.equals(item.getAutorizacion().getEstadoAutorizacion().getId())) {
            vo.setDevuelta(true);
        }
        return vo;
    }

    private static InfoGeneralBandejaIniVO toInfoGeneralBandejaIniVO(SolicitudItem item) {
        InfoGeneralBandejaIniVO vo = new InfoGeneralBandejaIniVO();

        // Información de la solicitud
        vo.setEmailUsuario(item.getSolicitud().getAfiliado().getEmailPersonal());
        vo.setNumeroSolicitud(item.getNroItem().toString());
        vo.setFechaSolicitud(df.format(item.getSolicitud().getFechaCreacion()));
        vo.setAmbito("Ambulatorio");
        vo.setOrigen(getOrigen(item)); // TODO: Verificar de donde sacar este dato
        vo.setRegional(item.getSolicitud().getSedeIps().getMunicipio().getRegional().getDescripcion());
        vo.setNombreIps(item.getSolicitud().getSedeIps().getNombre());
        if(item.getAutorizacion().getSedeIpsEfectora()!=null)vo.setSedeIpsEfectoraId(item.getAutorizacion().getSedeIpsEfectora().getId());
        // Información del médico
        vo.setTipoIdentificacionMedico(item.getSolicitud().getProfesionalSolicitante().getIdentificacionProfesional().getDescripcion());
        vo.setIdentificacionEntidad(item.getSolicitud().getSedeIps().getIps().getNumeroIdentificacion());
        vo.setIdentificacionMedico(item.getSolicitud().getProfesionalSolicitante().getNumeroIdentificacion());
        vo.setPrimerNombreMedico(item.getSolicitud().getProfesionalSolicitante().getPrimerNombre());
        vo.setSegundoNombreMedico(item.getSolicitud().getProfesionalSolicitante().getSegundoNombre());
        vo.setPrimerApellidoMedico(item.getSolicitud().getProfesionalSolicitante().getPrimerApellido());
        vo.setSegundoApellidoMedico(item.getSolicitud().getProfesionalSolicitante().getSegundoApellido());

        // TODO: Verificar si este es el dato que se debe mostrar.
        if(item.getSolicitud().getProfesionalSolicitante().getTipoProfesional()!=null)vo.setEspecialidadMedico(item.getSolicitud().getProfesionalSolicitante().getTipoProfesional().getDescripcion());

        // Información del afiliado
        vo.setTipoIdentificacionUsuario(item.getSolicitud().getAfiliado().getTipoIdentificacion().getDescripcion());
        vo.setTipoIdentificacionUsuarioId(item.getSolicitud().getAfiliado().getTipoIdentificacion().getId());
        vo.setIdentificacionUsuario(item.getSolicitud().getAfiliado().getNumeroIdentificacion());
        vo.setNombreCompletoUsuario(item.getSolicitud().getAfiliado().getPrimerNombre() + " "
                + item.getSolicitud().getAfiliado().getSegundoNombre() + " " + item.getSolicitud().getAfiliado().getPrimerApellido() + " "
                + item.getSolicitud().getAfiliado().getSegundoApellido());
        String numCelular = item.getSolicitud().getAfiliado().getTelefonoCelular().trim();
        vo.setTelefonosUsuario(item.getSolicitud().getAfiliado().getTelefonoResidencial() + (!numCelular.equals("") ? " / " : "") + numCelular);
        vo.setEpsUsuario(item.getSolicitud().getAfiliado().getEps().getRazonSocial());
        
        if (item.getSolicitud().getAfiliado().getRegimenAfiliacion() == RegimenAfiliacion.CONTRIBUTIVO) {
        	 vo.setNivelIBC(item.getSolicitud().getAfiliado().getNivelIbc().getDescripcion());
        } else {
        	 vo.setNivelIBC(item.getSolicitud().getAfiliado().getNivelSisBen().getDescripcion());
        }
        vo.setEstadoAfiliacion(item.getSolicitud().getAfiliado().getEstadoAfiliacion().getDescripcion());
        vo.setRazonEstado(item.getSolicitud().getAfiliado().getRazonEstadoAfiliacion());
        vo.setEdadUsuario(String.valueOf(DateUtilities.calcularEdad(item.getSolicitud().getAfiliado().getFechaNacimiento(), Calendar
                .getInstance().getTime())));
        vo.setObservaciones(item.getSolicitud().getObservacion());
        vo.setTipoAfiliadoUsuario(item.getSolicitud().getAfiliado().getTipoAfiliado().getDescripcion());
        vo.setSemanasepsUsuario(item.getSolicitud().getAfiliado().getSemEps().toString());
        vo.setSemanassgsssUsuario(item.getSolicitud().getAfiliado().getSemSGSSS().toString());
        return vo;
    }

    /**
     * Adaptador modulo generico de la informacion de la solicitud
     * 
     * @param item
     * @return
     */
    private static InfoSolicitudBandejaVO toInfoSolicitudBandejaVO(SolicitudItem item) {
        InfoSolicitudBandejaVO infoSolicitudBandejaVO = new InfoSolicitudBandejaVO();
        if (item.getSolMedicamento() != null && item.getSolMedicamento().getFormCTCMedicamento() != null) {
            if (item.getSolMedicamento().getFormCTCMedicamento().getResumenHistoriaClinica() != null) {
                infoSolicitudBandejaVO.setDescHistClinica(item.getSolMedicamento().getFormCTCMedicamento().getResumenHistoriaClinica());
            }
        } else if (item.getSolInsumo() != null && item.getSolInsumo().getFormCTCInsumo() != null) {
            if (item.getSolInsumo().getFormCTCInsumo().getResumenHistoriaClinica() != null) {
                infoSolicitudBandejaVO.setDescHistClinica(item.getSolInsumo().getFormCTCInsumo().getResumenHistoriaClinica());
            }   
        } else if (item.getSolProcedimiento() != null && item.getSolProcedimiento().getFormCTCProcedimiento() != null) {
            if (item.getSolProcedimiento().getFormCTCProcedimiento().getResumenHistoriaClinica() != null) {
                infoSolicitudBandejaVO.setDescHistClinica(item.getSolProcedimiento().getFormCTCProcedimiento().getResumenHistoriaClinica());
            }
        }
        
        if(item.getSolMedicamento() != null && item.getSolMedicamento().getFormulaMedicamento() != null) {

            if (item.getSolMedicamento().getFormulaMedicamento().getCausaExterna() != null) {
                infoSolicitudBandejaVO.setCausaExternaSeleccionada(item.getSolMedicamento().getFormulaMedicamento().getCausaExterna()
                        .getId());
            }
            if (item.getSolMedicamento().getFormulaMedicamento().getFinalidad() != null) {
                infoSolicitudBandejaVO.setFinalidadSeleccionada(item.getSolMedicamento().getFormulaMedicamento().getFinalidad().getId());
            }
            if (item.getSolMedicamento().getFormulaMedicamento().getTipoCatastrofico() != null) {
                infoSolicitudBandejaVO.setTipoCatastroficoSeleccionado(item.getSolMedicamento().getFormulaMedicamento()
                        .getTipoCatastrofico().getId());
            }
        } else if(item.getSolProcedimiento() != null && item.getSolProcedimiento().getFormulaProcedimiento() != null) {
            if (item.getSolProcedimiento().getFormulaProcedimiento().getCausaExterna() != null) {
                infoSolicitudBandejaVO.setCausaExternaSeleccionada(item.getSolProcedimiento().getFormulaProcedimiento().getCausaExterna()
                        .getId());
            }
            if (item.getSolProcedimiento().getFormulaProcedimiento().getFinalidad() != null) {
                infoSolicitudBandejaVO
                        .setFinalidadSeleccionada(item.getSolProcedimiento().getFormulaProcedimiento().getFinalidad().getId());
            }
            if (item.getSolProcedimiento().getFormulaProcedimiento().getTipoCatastrofico() != null) {
                infoSolicitudBandejaVO.setTipoCatastroficoSeleccionado(item.getSolProcedimiento().getFormulaProcedimiento()
                        .getTipoCatastrofico().getId());
            }
        } else if(item.getSolInsumo() != null && item.getSolInsumo().getFormulaInsumo() != null) {

            if (item.getSolInsumo().getFormulaInsumo().getCausaExterna() != null) {
                infoSolicitudBandejaVO.setCausaExternaSeleccionada(item.getSolInsumo().getFormulaInsumo().getCausaExterna()
                        .getId());
            }
            if (item.getSolInsumo().getFormulaInsumo().getFinalidad() != null) {
                infoSolicitudBandejaVO.setFinalidadSeleccionada(item.getSolInsumo().getFormulaInsumo().getFinalidad().getId());
            }
            if (item.getSolInsumo().getFormulaInsumo().getTipoCatastrofico() != null) {
                infoSolicitudBandejaVO.setTipoCatastroficoSeleccionado(item.getSolInsumo().getFormulaInsumo()
                        .getTipoCatastrofico().getId());
            }
        }
        // Se establece la entidad de recobro seleccionada por defecto
        if (item.getAutorizacion().getEntidadRecobro() == null) {
            infoSolicitudBandejaVO.setEntidadSeleccionada(SystemConstants.ENTIDAD_RECOBRO_FOSYGA);
        } else {
            infoSolicitudBandejaVO.setEntidadSeleccionada(item.getAutorizacion().getEntidadRecobro().getId());
        }

        return infoSolicitudBandejaVO;
    }

    private static BandejaIpsPrestadorVO toBandejaIpsPrestadorVO(SolicitudItem item) {

        BandejaIpsPrestadorVO vo = new BandejaIpsPrestadorVO();

        Autorizacion autorizacion = item.getAutorizacion(); 
        Integer estadoId = autorizacion.getEstadoAutorizacion().getId();
        if (EstadoAutorizacion.DEVUELTA_IPS.equals(estadoId)) {

            vo.setRespuestaMotivoDevolucion(autorizacion.getConceptoNacional() != null ? autorizacion
                    .getConceptoNacional().getCausalDevolucion().getDescripcion()
                    : autorizacion.getConceptoRegional() != null ? autorizacion.getConceptoRegional()
                            .getCausalDevolucion().getDescripcion() : "");

            vo.setRespuestaDevolucion(autorizacion.getConceptoNacional() != null ? autorizacion.getConceptoNacional()
                    .getJustificacion() : autorizacion.getConceptoRegional() != null ? autorizacion
                    .getConceptoRegional().getJustificacion() : "");
        }
        
        if(EstadoAutorizacion.AUTORIZADO.equals(estadoId) || EstadoAutorizacion.APROBADA_REGIONAL.equals(estadoId)
                || EstadoAutorizacion.NEGADA_REGIONAL.equals(estadoId) || EstadoAutorizacion.NO_AUTORIZADA.equals(estadoId)
                || EstadoAutorizacion.NEGADA_NACIONAL.equals(estadoId)){
            
            if(EstadoAutorizacion.AUTORIZADO.equals(estadoId) || EstadoAutorizacion.APROBADA_REGIONAL.equals(estadoId)){
              vo.setAprobada(true);
            }
            
            if(autorizacion.getConceptoNacional() != null){
                vo.setJustificacion(autorizacion.getConceptoNacional().getJustificacion());
                
            } else if (autorizacion.getConceptoRegional() != null){
                vo.setJustificacion(autorizacion.getConceptoRegional().getJustificacion());
                
            }
            
        }
        

        vo.setAdjuntos(getDocumentosSolicitud(item));
        vo.setDiagnostico(toDiagnosticoVO(item.getDiagnostico().getDiagnostico().toDto()));
        vo.setEstado(item.getAutorizacion().getEstadoAutorizacion().getId());
        if (item.isMedicamento()) {
            vo.setTipoItem(String.valueOf(SystemConstants.ITEM_MEDICAMENTO));
            BandejaAltoCostoMedicamentoVO medicamento = new BandejaAltoCostoMedicamentoVO();
            medicamento.setCodigo(item.getSolMedicamento().getMedicamento().getCodigo());
            medicamento.setNombre(item.getSolMedicamento().getMedicamento().getDescripcion());
            medicamento.setCantidadSolicitada(item.getCantidad());
            if (item.getSolMedicamento().getFormulaMedicamento() != null) {
                medicamento.setDosis(item.getSolMedicamento().getFormulaMedicamento().getDosis());
                medicamento.setViaAdministracionId(item.getSolMedicamento().getFormulaMedicamento().getViaAdministracion().getId()
                        .toString());
                medicamento.setViaAdministracion(item.getSolMedicamento().getFormulaMedicamento().getViaAdministracion().getDescripcion()
                        .toString());
                medicamento.setDuracionTratamiento(item.getSolMedicamento().getFormulaMedicamento().getDuracion());
                medicamento.setPosologia(item.getSolMedicamento().getFormulaMedicamento().getPosologia());
            }
            vo.setMedicamento(medicamento);
        } else if(item.isProcedimiento()){
            vo.setTipoItem(String.valueOf(SystemConstants.ITEM_PROCEDIMIENTO));
            BandejaAltoCostoProcedimientoVO procedimiento = new BandejaAltoCostoProcedimientoVO();
            procedimiento.setCodigo(item.getSolProcedimiento().getProcedimiento().getCodigo());
            procedimiento.setNombre(item.getSolProcedimiento().getProcedimiento().getDescripcion());
            procedimiento.setCantidadSolicitada(item.getCantidad());
            if (item.getSolProcedimiento().getFormulaProcedimiento() != null)
                procedimiento.setLateralidad(item.getSolProcedimiento().getFormulaProcedimiento().getLateralidad().getDescripcion());
            procedimiento.setIpsRemitente(item.getSolicitud().getSedeIps().getNombre());
            vo.setProcedimiento(procedimiento);
        } else if( item.isInsumo()){
        	 vo.setTipoItem(String.valueOf(SystemConstants.ITEM_INSUMO));
             BandejaAltoCostoInsumoVO insumo = new BandejaAltoCostoInsumoVO();
             insumo.setCodigo(item.getSolInsumo().getInsumo().getCodigo());
             insumo.setNombre(item.getSolInsumo().getInsumo().getDescripcion());
             insumo.setCantidadSolicitada(item.getCantidad());
             if (item.getSolInsumo().getFormulaInsumo() != null) {
                 insumo.setCantidadSolicitada(item.getSolInsumo().getFormulaInsumo().getCantidad());
                 insumo.setDuracionTratamiento(item.getSolInsumo().getFormulaInsumo().getDuracion());
             }
             vo.setInsumo(insumo);
        }

        // Si el item esta en un estado perteneciente a IPS/Medico, entonces ciertos campos seran editables
        if (EstadoAutorizacion.DEVUELTA_IPS.equals(item.getAutorizacion().getEstadoAutorizacion().getId())) {
            vo.setEditable(true);
        }
        return vo;
    }

    private static ConceptoCTCVO toConceptoCTCVO(SolicitudItem item, String tipoBandeja) {
        ConceptoCTCVO conceptoCTCVO = new ConceptoCTCVO();
        ConceptoAutorizacion conceptoAutorizacion = null;
        if (item.getAutorizacion() != null) {
            if (tipoBandeja.equals(SystemConstants.BANDEJA_NACIONAL)) {
                conceptoAutorizacion = item.getAutorizacion().getConceptoNacional();
            } else {
                conceptoAutorizacion = item.getAutorizacion().getConceptoRegional();
            }
            if (conceptoAutorizacion != null) {
                conceptoCTCVO.setPeriodoAprobado(conceptoAutorizacion.getPeriodoAprobado());
                conceptoCTCVO.setDiasPeriodo(conceptoAutorizacion.getDiasXperiodo());
                conceptoCTCVO.setUnidadesAprobadas(conceptoAutorizacion.getUnidadesAprobadas());
                conceptoCTCVO.setDosisAprobada(conceptoAutorizacion.getDosisAprobadas());
                conceptoCTCVO.setNumeroEntregas(conceptoAutorizacion.getNumeroEntregas());
                conceptoCTCVO.setJustificacion(conceptoAutorizacion.getJustificacion());
            }
        }
        return conceptoCTCVO;
    }

    private static ConceptoACVO toConceptoACVO(SolicitudItem item, String tipoBandeja) {
        ConceptoACVO conceptoCTCVO = new ConceptoACVO();
        ConceptoAutorizacion conceptoAutorizacion = null;
        if (item.getAutorizacion() != null) {
            if (tipoBandeja.equals(SystemConstants.BANDEJA_NACIONAL)) {
                conceptoAutorizacion = item.getAutorizacion().getConceptoNacional();
            } else {
                conceptoAutorizacion = item.getAutorizacion().getConceptoRegional();
            }
            if (conceptoAutorizacion != null) {
                conceptoCTCVO.setPeriodoAprobado(conceptoAutorizacion.getPeriodoAprobado());
                conceptoCTCVO.setDiasPeriodo(conceptoAutorizacion.getDiasXperiodo());
                conceptoCTCVO.setUnidadesAprobadas(conceptoAutorizacion.getUnidadesAprobadas());
                conceptoCTCVO.setDosisAprobada(conceptoAutorizacion.getDosisAprobadas());
                conceptoCTCVO.setNumeroEntregas(conceptoAutorizacion.getNumeroEntregas());
                conceptoCTCVO.setJustificacion(conceptoAutorizacion.getJustificacion());

                if (conceptoAutorizacion.getLateralidad() != null) {
                    conceptoCTCVO.setLateralidad(conceptoAutorizacion.getLateralidad().getId());
                }
            }

            if (item.getAutorizacion().getInfoTutela() != null && Boolean.TRUE.equals(item.getAplicaTutela())) {
                conceptoCTCVO.setNumeroTutela(item.getAutorizacion().getInfoTutela().getNumeroTutela());
                conceptoCTCVO.setNumeroFallo(item.getAutorizacion().getInfoTutela().getNumeroFallo());
                conceptoCTCVO.setEsTutelaIntegral(item.getAutorizacion().getInfoTutela().getEsTutelaIntegral());
                conceptoCTCVO.setExentaCopago(item.getAutorizacion().getInfoTutela().getExentaCopago());
                conceptoCTCVO.setJustificacion(item.getAutorizacion().getInfoTutela().getJustificacionConcepto());
                conceptoCTCVO.setJustificacionLdf(item.getAutorizacion().getInfoTutela().getJustificacionConceptoLdf());
                conceptoCTCVO.setJustificacionConexidad(item.getAutorizacion().getInfoTutela().getJustificacionConexidad());
            }
        }
        return conceptoCTCVO;
    }

    private static ConceptoCSVO toConceptoCSVO(SolicitudItem item, String tipoBandeja) {
        ConceptoCSVO conceptoCSVO = new ConceptoCSVO();
        ConceptoAutorizacion conceptoAutorizacion = null;
        if (item.getAutorizacion() != null) {
            if (tipoBandeja.equals(SystemConstants.BANDEJA_NACIONAL)) {
                conceptoAutorizacion = item.getAutorizacion().getConceptoNacional();
            } else {
                conceptoAutorizacion = item.getAutorizacion().getConceptoRegional();
            }
            if (conceptoAutorizacion != null) {
                conceptoCSVO.setPeriodoAprobado(conceptoAutorizacion.getPeriodoAprobado());
                conceptoCSVO.setDiasPeriodo(conceptoAutorizacion.getDiasXperiodo());
                conceptoCSVO.setUnidadesAprobadas(conceptoAutorizacion.getUnidadesAprobadas());
                conceptoCSVO.setDosisAprobada(conceptoAutorizacion.getDosisAprobadas());
                conceptoCSVO.setNumeroEntregas(conceptoAutorizacion.getNumeroEntregas());
                conceptoCSVO.setJustificacion(conceptoAutorizacion.getJustificacion());

                if (conceptoAutorizacion.getLateralidad() != null) {
                    conceptoCSVO.setLateralidad(conceptoAutorizacion.getLateralidad().getId());
                }
            }
        }
        return conceptoCSVO;
    }

    private static InfoDevolucionVO toInfoDevolucionCTCVO(SolicitudItem item, String tipoBandeja) {
        InfoDevolucionVO vo = new InfoDevolucionVO();

        // Se establece el concepto a partir del estado
        if (EstadoAutorizacion.RESPUESTA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.RESPONDER);
        } else if (EstadoAutorizacion.PENDIENTE_ACTA == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            if (item.getAutorizacion().getConceptoNacional().getCriteriosNegacion() != null
                    && !item.getAutorizacion().getConceptoNacional().getCriteriosNegacion().isEmpty()) {
                vo.setConcepto(SystemConstants.NO_APROBAR);
            } else {
                vo.setConcepto(SystemConstants.APROBAR);
            }
        } else if (EstadoAutorizacion.NEGADA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.NO_APROBAR_REG);
        } else if (EstadoAutorizacion.APROBADA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.APROBAR_REG);
        } else if (EstadoAutorizacion.DEVUELTA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.DEVOLVER);
        } else if (EstadoAutorizacion.ANULADA == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.ANULAR);
        }

        // Dependiendo del estado de la solicitud, se llenan los campos de la devolución
        if (EstadoAutorizacion.RESPUESTA_IPS == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setJustificacion(item.getAutorizacion().getJustificacionIps());
        } else {

            if (SystemConstants.BANDEJA_REGIONAL.equals(tipoBandeja) && item.getAutorizacion().getConceptoNacional() != null) {
                vo.setJustificacion(item.getAutorizacion().getConceptoNacional().getJustificacion());

                if (item.getAutorizacion().getConceptoNacional().getCausalDevolucion() != null) {
                    vo.setCausalDevolucion(item.getAutorizacion().getConceptoNacional().getCausalDevolucion().getDescripcion());
                }
                if (item.getAutorizacion().getConceptoNacional().getCausalAnulacion() != null) {
                    vo.setCausalAnulacion(item.getAutorizacion().getConceptoNacional().getCausalAnulacion().getDescripcion());
                }
                if (item.getAutorizacion().getConceptoNacional().getCriteriosNegacion() != null) {
                    vo.setCriteriosNegacion(new ArrayList<String>());
                    for (CriterioNegacion c : item.getAutorizacion().getConceptoNacional().getCriteriosNegacion()) {
                        vo.getCriteriosNegacion().add(c.getDescripcion());
                    }
                }

                vo.setDiasPeriodo(item.getAutorizacion().getConceptoNacional().getDiasXperiodo());
                vo.setDosisAprobada(item.getAutorizacion().getConceptoNacional().getDosisAprobadas());
                vo.setNumeroEntregas(item.getAutorizacion().getConceptoNacional().getNumeroEntregas());
                vo.setPeriodoAprobado(item.getAutorizacion().getConceptoNacional().getPeriodoAprobado());
                vo.setUnidadesAprobadas(item.getAutorizacion().getConceptoNacional().getUnidadesAprobadas());

            }
            if (SystemConstants.BANDEJA_NACIONAL.equals(tipoBandeja) && item.getAutorizacion().getConceptoRegional() != null) {
                if (item.getAutorizacion().getConceptoRegional().getCausalDevolucion() != null) {
                    vo.setCausalDevolucion(item.getAutorizacion().getConceptoRegional().getCausalDevolucion().getDescripcion());
                }
                if (item.getAutorizacion().getConceptoRegional().getCausalAnulacion() != null) {
                    vo.setCausalAnulacion(item.getAutorizacion().getConceptoRegional().getCausalAnulacion().getDescripcion());
                }
                if (item.getAutorizacion().getConceptoRegional().getCriteriosNegacion() != null) {
                    vo.setCriteriosNegacion(new ArrayList<String>());
                    for (CriterioNegacion c : item.getAutorizacion().getConceptoRegional().getCriteriosNegacion()) {
                        vo.getCriteriosNegacion().add(c.getDescripcion());
                    }
                }
                vo.setJustificacion(item.getAutorizacion().getConceptoRegional().getJustificacion());
                vo.setDiasPeriodo(item.getAutorizacion().getConceptoRegional().getDiasXperiodo());
                vo.setDosisAprobada(item.getAutorizacion().getConceptoRegional().getDosisAprobadas());
                vo.setNumeroEntregas(item.getAutorizacion().getConceptoRegional().getNumeroEntregas());
                vo.setPeriodoAprobado(item.getAutorizacion().getConceptoRegional().getPeriodoAprobado());
                vo.setUnidadesAprobadas(item.getAutorizacion().getConceptoRegional().getUnidadesAprobadas());
            }

            if (vo.getConcepto() != null) {
                if (EstadoAutorizacion.PENDIENTE_ACTA == item.getAutorizacion().getEstadoAutorizacion().getId()
                        && SystemConstants.APROBAR == vo.getConcepto()) {
                    vo.setDiasPeriodo(item.getAutorizacion().getConceptoNacional().getDiasXperiodo());
                    vo.setDosisAprobada(item.getAutorizacion().getConceptoNacional().getDosisAprobadas());
                    vo.setNumeroEntregas(item.getAutorizacion().getConceptoNacional().getNumeroEntregas());
                    vo.setPeriodoAprobado(item.getAutorizacion().getConceptoNacional().getPeriodoAprobado());
                    vo.setUnidadesAprobadas(item.getAutorizacion().getConceptoNacional().getUnidadesAprobadas());
                    vo.setJustificacion(item.getAutorizacion().getConceptoNacional().getJustificacion());

                } else if (EstadoAutorizacion.PENDIENTE_ACTA == item.getAutorizacion().getEstadoAutorizacion().getId()
                        && SystemConstants.NO_APROBAR == vo.getConcepto()) {
                    if (item.getAutorizacion().getConceptoNacional().getCriteriosNegacion() != null) {
                        vo.setCriteriosNegacion(new ArrayList<String>());
                        for (CriterioNegacion c : item.getAutorizacion().getConceptoNacional().getCriteriosNegacion()) {
                            vo.getCriteriosNegacion().add(c.getDescripcion());
                        }
                    }
                    vo.setJustificacion(item.getAutorizacion().getConceptoNacional().getJustificacion());

                } else if (SystemConstants.ANULAR == vo.getConcepto()) {
                    if (item.getAutorizacion().getConceptoRegional().getCausalAnulacion() != null) {
                        vo.setCausalAnulacion(item.getAutorizacion().getConceptoRegional().getCausalAnulacion().getDescripcion());
                    }
                    vo.setJustificacion(item.getAutorizacion().getConceptoRegional().getJustificacion());
                }
            }
        }

        return vo;
    }

    private static InfoDevolucionVO toInfoDevolucionACVO(SolicitudItem item, String tipoBandeja) {
        InfoDevolucionVO vo = new InfoDevolucionVO();

        // Se establece el concepto a partir del estado
        if (EstadoAutorizacion.RESPUESTA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId()
                || EstadoAutorizacion.RESPUESTA_IPS == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.RESPONDER);
        } else if (EstadoAutorizacion.AUTORIZADO == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.APROBAR);
        } else if (EstadoAutorizacion.DEVUELTA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId()
                || EstadoAutorizacion.DEVUELTA_IPS == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.DEVOLVER);
        } else if (EstadoAutorizacion.ANULADA == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.ANULAR);
        }

        // Dependiendo del estado de la solicitud, se llenan los campos de la devolución
        if (EstadoAutorizacion.RESPUESTA_IPS == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setJustificacion(item.getAutorizacion().getJustificacionIps());
        } else {

            if ((SystemConstants.BANDEJA_REGIONAL.equals(tipoBandeja) && item.getAutorizacion().getConceptoNacional() != null)
                    || (SystemConstants.BANDEJA_NACIONAL == tipoBandeja
                            && (EstadoAutorizacion.AUTORIZADO == item.getAutorizacion().getEstadoAutorizacion().getId() || EstadoAutorizacion.ANULADA == item
                                    .getAutorizacion().getEstadoAutorizacion().getId())
                            || EstadoAutorizacion.DEVUELTA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId() || EstadoAutorizacion.DEVUELTA_IPS == item
                            .getAutorizacion().getEstadoAutorizacion().getId())) {
                if (item.getAutorizacion().getConceptoNacional() != null) {
                    vo.setJustificacion(item.getAutorizacion().getConceptoNacional().getJustificacion());

                    if (item.getAutorizacion().getConceptoNacional().getCausalDevolucion() != null) {
                        vo.setCausalDevolucion(item.getAutorizacion().getConceptoNacional().getCausalDevolucion().getDescripcion());
                    }
                    if (item.getAutorizacion().getConceptoNacional().getCausalAnulacion() != null) {
                        vo.setCausalAnulacion(item.getAutorizacion().getConceptoNacional().getCausalAnulacion().getDescripcion());
                    }

                    vo.setDiasPeriodo(item.getAutorizacion().getConceptoNacional().getDiasXperiodo());
                    vo.setDosisAprobada(item.getAutorizacion().getConceptoNacional().getDosisAprobadas());
                    vo.setNumeroEntregas(item.getAutorizacion().getConceptoNacional().getNumeroEntregas());
                    vo.setPeriodoAprobado(item.getAutorizacion().getConceptoNacional().getPeriodoAprobado());
                    vo.setUnidadesAprobadas(item.getAutorizacion().getConceptoNacional().getUnidadesAprobadas());

                    if (item.getAutorizacion().getConceptoNacional().getLateralidad() != null) {
                        vo.setLateralidad(item.getAutorizacion().getConceptoNacional().getLateralidad().getDescripcion());
                    }
                }

            }
            if (SystemConstants.BANDEJA_NACIONAL.equals(tipoBandeja)
                    && item.getAutorizacion().getConceptoRegional() != null
                    && (EstadoAutorizacion.RESPUESTA_IPS == item.getAutorizacion().getEstadoAutorizacion().getId() || EstadoAutorizacion.RESPUESTA_REGIONAL == item
                            .getAutorizacion().getEstadoAutorizacion().getId())) {
                if (item.getAutorizacion().getConceptoRegional() != null)
                    vo.setJustificacion(item.getAutorizacion().getConceptoRegional().getJustificacion());
            }
        }

        return vo;
    }

    private static InfoDevolucionVO toInfoDevolucionCSVO(SolicitudItem item, String tipoBandeja) {
        InfoDevolucionVO vo = new InfoDevolucionVO();

        // Se establece el concepto a partir del estado
        if (EstadoAutorizacion.RESPUESTA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId()
                || EstadoAutorizacion.RESPUESTA_IPS == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.RESPONDER);
        } else if (EstadoAutorizacion.AUTORIZADO == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.APROBAR);
        } else if (EstadoAutorizacion.DEVUELTA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId()
                || EstadoAutorizacion.DEVUELTA_IPS == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.DEVOLVER);
        } else if (EstadoAutorizacion.ANULADA == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setConcepto(SystemConstants.ANULAR);
        }

        // Dependiendo del estado de la solicitud, se llenan los campos de la devolución
        if (EstadoAutorizacion.RESPUESTA_IPS == item.getAutorizacion().getEstadoAutorizacion().getId()) {
            vo.setJustificacion(item.getAutorizacion().getJustificacionIps());
        } else {

            if ((SystemConstants.BANDEJA_REGIONAL.equals(tipoBandeja) && item.getAutorizacion().getConceptoNacional() != null)
                    || (SystemConstants.BANDEJA_NACIONAL == tipoBandeja
                            && (EstadoAutorizacion.AUTORIZADO == item.getAutorizacion().getEstadoAutorizacion().getId() || EstadoAutorizacion.ANULADA == item
                                    .getAutorizacion().getEstadoAutorizacion().getId())
                            || EstadoAutorizacion.DEVUELTA_REGIONAL == item.getAutorizacion().getEstadoAutorizacion().getId() || EstadoAutorizacion.DEVUELTA_IPS == item
                            .getAutorizacion().getEstadoAutorizacion().getId())) {
                if (item.getAutorizacion().getConceptoNacional() != null) {

                    vo.setJustificacion(item.getAutorizacion().getConceptoNacional().getJustificacion());

                    if (item.getAutorizacion().getConceptoNacional().getCausalDevolucion() != null) {
                        vo.setCausalDevolucion(item.getAutorizacion().getConceptoNacional().getCausalDevolucion().getDescripcion());
                    }
                    if (item.getAutorizacion().getConceptoNacional().getCausalAnulacion() != null) {
                        vo.setCausalAnulacion(item.getAutorizacion().getConceptoNacional().getCausalAnulacion().getDescripcion());
                    }

                    vo.setDiasPeriodo(item.getAutorizacion().getConceptoNacional().getDiasXperiodo());
                    vo.setDosisAprobada(item.getAutorizacion().getConceptoNacional().getDosisAprobadas());
                    vo.setNumeroEntregas(item.getAutorizacion().getConceptoNacional().getNumeroEntregas());
                    vo.setPeriodoAprobado(item.getAutorizacion().getConceptoNacional().getPeriodoAprobado());
                    vo.setUnidadesAprobadas(item.getAutorizacion().getConceptoNacional().getUnidadesAprobadas());

                    if (item.getAutorizacion().getConceptoNacional().getLateralidad() != null) {
                        vo.setLateralidad(item.getAutorizacion().getConceptoNacional().getLateralidad().getDescripcion());
                    }
                }

            }
            if (SystemConstants.BANDEJA_NACIONAL.equals(tipoBandeja)
                    && item.getAutorizacion().getConceptoRegional() != null
                    && (EstadoAutorizacion.RESPUESTA_IPS == item.getAutorizacion().getEstadoAutorizacion().getId() || EstadoAutorizacion.RESPUESTA_REGIONAL == item
                            .getAutorizacion().getEstadoAutorizacion().getId())) {
                vo.setJustificacion(item.getAutorizacion().getConceptoRegional().getJustificacion());
            }
        }

        return vo;
    }

	public static InsumoVO toOneInsumoVO(InsumoDto insumoDto) {
		   

		        InsumoVO vo = new InsumoVO();
		        vo.setId(insumoDto.getId());
		        vo.setCodigo(insumoDto.getCodigo());
		        vo.setDescripcion(insumoDto.getDescripcion());

		        return vo;
		    
	}
}
