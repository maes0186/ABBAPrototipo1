package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.FiltroBandejasDto;
import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.repository.EstadoAutorizacionRepository;
import com.conexia.saludcoop.common.repository.RoleEstadoRepository;
import com.conexia.saludcoop.common.repository.SolicitudHistorialVODao;
import com.conexia.saludcoop.common.repository.SolicitudItemRepository;
import com.conexia.saludcoop.common.repository.SolicitudRepository;
import com.conexia.saludcoop.common.repository.SolicitudVODao;
import com.conexia.saludcoop.common.util.Pagination;
import com.conexia.saludcoop.common.util.RoleUtils;
import com.conexia.saludcoop.web.form.FiltroBandejasForm;
import com.conexia.saludcoop.web.vo.BandejaItemProjVO;
import com.conexia.saludcoop.web.vo.BandejaSubItemProjVO;
import com.conexia.saludcoop.web.vo.PageVO;
import com.conexia.saludcoop.web.vo.SolicitudHistorialVO;

@Service
@Transactional
public class SolicitudManager {

    @Autowired
    private SolicitudVODao sVOd;
    @Autowired
    private SolicitudRepository sr;
    @Autowired
    private SolicitudItemRepository sir;
    @Autowired
    private RoleEstadoRepository rer;
    @Autowired
    private EstadoAutorizacionRepository ear;
    @Autowired
    private SolicitudHistorialVODao solHisVOd;

    public SolicitudManager() {

    }

    public PageVO<BandejaItemProjVO> getSolicitudesIpsMedico(FiltroBandejasForm form, List<Integer> roles, Long sedeIpsId) {

        Pagination pagination = new Pagination(form.getActualPage(), 10);
        List<Integer> estados = new ArrayList<>();
        if (form.getEstadoSolicitud() != null) {
            // TODO: Se deja comentado para saber donde estaba, en cualquier momento los funcionales se arrepienten XD
//            for (Integer role : roles) {
//                RoleEstado roleEstado = rer.findOneByEstadoVisibleIdAndRoleId(form.getEstadoSolicitud(), role);
//                if (roleEstado != null) {
//                    for (EstadoAutorizacion estado : roleEstado.getEstadosAutorizacion()) {
//                        if (!estados.contains(estado.getId())) {
//                            estados.add(estado.getId());
//                        }
//                    }
//                }
//            }

            if (estados.isEmpty()) {
                estados.add(form.getEstadoSolicitud());

                if (EstadoAutorizacion.PENDIENTE_TUTELA.equals(form.getEstadoSolicitud()) && !RoleUtils.esRoleIPS(roles) 
                        && !RoleUtils.esRoleLDF(roles)) {
                    estados.add(EstadoAutorizacion.RESPUESTA_IPS);
                }
            }
        }
        List<BandejaItemProjVO> solicitudesVo = this.sVOd.getSolicitudes(form.getNumeroSolicitud(), form.getTipoDocumento(),
                form.getNumeroDocumento(), form.getEps(), estados, form.getIpsId(), form.getTecnologia(), pagination, roles, form.getEsTutela(), sedeIpsId);
        PageVO<BandejaItemProjVO> paginaBandeja = new PageVO<>(solicitudesVo, pagination.getTotalPages(), pagination.getTotalItems(),
                pagination.getActualPage());
        return paginaBandeja;
    }

    /**
     * Lista los items de una solicitud dado su número.
     * 
     * @param rolId
     * @param numeroSolicitud
     *            Número de solicitud.
     * @return El listado de items.
     */
    public List<BandejaSubItemProjVO> getItemsSolicitudesUpsMedico(FiltroBandejasForm form, List<Integer> roles) {

        // Se crea el objeto DTO de filtro para enviar al DAO
        FiltroBandejasDto filtro = new FiltroBandejasDto();
        BeanUtils.copyProperties(form, filtro);
        List<Integer> estados = new ArrayList<>();
        if (form.getEstadoSolicitud() != null) {
            // TODO: Se deja comentado para saber donde estaba, en cualquier momento los funcionales se arrepienten XD 
//            for (Integer role : roles) {
//                RoleEstado roleEstado = rer.findOneByEstadoVisibleIdAndRoleId(form.getEstadoSolicitud(), role);
//                if (roleEstado != null) {
//                    for (EstadoAutorizacion estado : roleEstado.getEstadosAutorizacion()) {
//                        if (!estados.contains(estado.getId())) {
//                            estados.add(estado.getId());
//                        }
//                    }
//                }
//            }

            if (estados.isEmpty()) {
                estados.add(form.getEstadoSolicitud());

                if (EstadoAutorizacion.PENDIENTE_TUTELA.equals(form.getEstadoSolicitud())) {
                    estados.add(EstadoAutorizacion.RESPUESTA_IPS);
                }
            }
        }
        List<BandejaSubItemProjVO> lista = sVOd.getGetSubitemSolicitud(filtro, estados, roles);
        // TODO: Se deja comentado para saber donde estaba, en cualquier momento los funcionales se arrepienten XD
//        for (BandejaSubItemProjVO subitem : lista) {
//            for (Integer role : roles) {
//                RoleEstado estado = rer.findOneByEstadosAutorizacionIdAndRoleId(subitem.getEstadoId(), role);
//                if (estado != null) {
//                    subitem.setEstado(estado.getEstadoVisible().getDescripcion());
//                } else {
//                    subitem.setEstado(ear.findOne(subitem.getEstadoId()).getDescripcion());
//                }
//                subitem.setEstadoId(null);
//                break;
//            }
//        }
        return lista;
    }
    
    public List<SolicitudHistorialVO> getHistorialSolicitudes(Integer tipoIdentificacionUsuario, String numeroItentifiacionUsuario, String idProcedimiento, String idMedicamento, Integer periodo){
        // Estado Autorizacion
        return solHisVOd.getHistoricoSolicitud(tipoIdentificacionUsuario, numeroItentifiacionUsuario, idProcedimiento, idMedicamento, EstadoAutorizacion.AUTORIZADO, periodo);        
    }

}
