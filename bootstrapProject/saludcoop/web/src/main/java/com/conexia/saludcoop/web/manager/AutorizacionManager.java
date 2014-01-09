package com.conexia.saludcoop.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion;
import com.conexia.saludcoop.common.repository.AutorizacionVODao;
import com.conexia.saludcoop.common.repository.RoleEstadoRepository;
import com.conexia.saludcoop.common.util.Pagination;
import com.conexia.saludcoop.web.form.FiltroBandejasForm;
import com.conexia.saludcoop.web.vo.BandejaAutorizacionItemProjVO;
import com.conexia.saludcoop.web.vo.PageVO;

@Service
@Transactional
public class AutorizacionManager {

	public AutorizacionManager(){
		
	}
	
	@Autowired
	private RoleEstadoRepository rer;
	
	@Autowired
	private AutorizacionVODao dao;

	public PageVO<BandejaAutorizacionItemProjVO> getAutorizacionesProj(FiltroBandejasForm form, List<Integer> roles, Long sedeIpsId) {
		Pagination pagination = new Pagination(form.getActualPage(), 10);
		List<Integer> estados = new ArrayList<>();

        if (form.getEstadoSolicitud() != null){
            // TODO: Se deja comentado para saber donde estaba, en cualquier momento los funcionales se arrepienten XD
//            for (Integer role : roles) {
//                RoleEstado roleEstado = rer.findOneByEstadoVisibleIdAndRoleId(form.getEstadoSolicitud(), role);
//                if(roleEstado != null) {
//                    for(EstadoAutorizacion estado : roleEstado.getEstadosAutorizacion()){
//                        if(!estados.contains(estado.getId())) {
//                            estados.add(estado.getId());
//                        }
//                    }
//                }                
//            }
            
            if(estados.isEmpty()) {
                estados.add(form.getEstadoSolicitud());
                
                if(EstadoAutorizacion.PENDIENTE_TUTELA.equals(form.getEstadoSolicitud())) {
                    estados.add(EstadoAutorizacion.RESPUESTA_IPS);
                }
            }
        }
		List<BandejaAutorizacionItemProjVO> solicitudesVo = this.dao.getAutorizaciones(form.getNumeroSolicitud(), form.getTipoDocumento(), form.getNumeroDocumento(), form.getEps(), estados, form.getTipoSolicitud(), form.getTecnologia(), form.getNumeroAutorizacion(), pagination, roles, sedeIpsId);
		PageVO<BandejaAutorizacionItemProjVO> paginaBandeja = new PageVO<>(solicitudesVo, pagination.getTotalPages(), pagination.getTotalItems(), pagination.getActualPage());
		
		return paginaBandeja;
	}
	
}
