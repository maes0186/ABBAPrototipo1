package com.conexia.saludcoop.web.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.transaccional.SolicitudParcialDto;
import com.conexia.saludcoop.common.entity.maestro.LineaDeFrente;
import com.conexia.saludcoop.common.entity.security.User;
import com.conexia.saludcoop.common.entity.security.UsuarioEntidad;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudParcial;
import com.conexia.saludcoop.common.repository.SedeIpsRepository;
import com.conexia.saludcoop.common.repository.SolicitudDao;
import com.conexia.saludcoop.common.repository.SolicitudParcialDao;
import com.conexia.saludcoop.common.repository.SolicitudParcialRepository;
import com.conexia.saludcoop.common.repository.TipoIdentificacionRepository;
import com.conexia.saludcoop.common.repository.UsuarioEntidadRepository;
import com.conexia.saludcoop.common.repository.exceptions.FormatoParametroException;
import com.conexia.saludcoop.common.util.Pagination;
import com.conexia.saludcoop.web.form.FiltroBandejasForm;
import com.conexia.saludcoop.web.form.SearchSolicitudParcialForm;
import com.conexia.saludcoop.web.manager.exceptions.NoSePuedeElilminarSPException;
import com.conexia.saludcoop.web.vo.AfiliadoVO;
import com.conexia.saludcoop.web.vo.PageVO;
import com.conexia.saludcoop.web.vo.SolicitudParcialVO;

@Transactional
@Service
public class SolicitudParcialManager extends GeneralManager {

	@Autowired
	private SolicitudParcialRepository spRepo;
	@Autowired
	private SedeIpsRepository sedeRepo;
	@Autowired
	private TipoIdentificacionRepository tiRepo;
	@Autowired
	private SolicitudParcialDao spDao;
	@Autowired
	private SolicitudDao sDao;
	
	@Autowired
	private UsuarioEntidadRepository userRepo;
	
	public Integer guardarNuevaSolicitudParcial(Long idSedeIps, AfiliadoVO afiliado, User user, String json){
		
		SolicitudParcial sp = new SolicitudParcial();
		try{
		
			sp.setSedeIps(idSedeIps==null?null:sedeRepo.findOne(idSedeIps));
			sp.setUser(user);
			sp.setTipoIdentificacionAfiliado(tiRepo.findOne(afiliado.getTipoIdentID()));
			sp.setNumeroIdentificacionAfiliado(afiliado.getNumeroIdentificacion());
			sp.setNombreCompletoAfiliado(afiliado.getNombreCompleto());
			sp.setFormData(json);
			Date date = new Date();
			sp.setFechaCreacion(date);
			sp.setFechaUpdate(date);
			if(sp.getNroSolicitud()== null){
				sp.setNroSolicitud(sDao.getNextNumeroSolicitud());
			}
			
			return spRepo.save(sp).getNroSolicitud();
		}
		catch(Exception e ){
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer modificarSolicitudParcial(Integer id, Long idSedeIps, String json){
		SolicitudParcial sp = spRepo.findOneByNroSolicitud(id);
		try{
			if(sp.getSedeIps()!=null && !sp.getSedeIps().equals(sp.getSedeIps().getId()) && idSedeIps!=null )
				sp.setSedeIps(sedeRepo.findOne(idSedeIps));
			sp.setFormData(json);
			return spRepo.save(sp).getNroSolicitud();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<SolicitudParcialDto> getSolicitudesParciales(SearchSolicitudParcialForm form, User user){
		
		Integer lineaDeFrenteId = null;
		UsuarioEntidad ue = userRepo.findOneByUsuarioId(user.getId());
		LineaDeFrente ldf = ue.getLdf();
		lineaDeFrenteId = ldf.getId();
		
		List<SolicitudParcial> sols;
		try {
			sols = spDao.findForBandeja(form.getTipoIdentificacionAfiliado(), 
												form.getNumeroIdentificacionAfiliado(), 
												lineaDeFrenteId, 
												form.getFechaDesde(), 
												form.getFechaHasta(),
												form.getNroSolicitud(),null);
		} catch (FormatoParametroException e) {
			e.printStackTrace();
			return null;
		}
		List<SolicitudParcialDto> solDtos = null;
		if (sols!=null && !sols.isEmpty()){
			solDtos = new Vector<>(sols.size()); 
			for (SolicitudParcial sp : sols){
				solDtos.add(sp.toDto());
			}
		}
		return solDtos;
	}
	
    public PageVO<SolicitudParcialVO> getSolicitudesParciales(FiltroBandejasForm form, User user) {
        Pagination pagination = new Pagination(form.getActualPage(), 10);
        Integer lineaDeFrenteId = null;
        UsuarioEntidad ue = userRepo.findOneByUsuarioId(user.getId());
        LineaDeFrente ldf = ue.getLdf();
        lineaDeFrenteId = ldf.getId();

        List<SolicitudParcial> sols;
        try {
            sols = spDao.findForBandeja(form.getTipoDocumento(), form.getNumeroDocumento(), lineaDeFrenteId, form.getFechaDesde(),
                    form.getFechaHasta(), form.getNumeroSolicitud(), pagination);
        } catch (FormatoParametroException e) {
            e.printStackTrace();
            return null;
        }
        List<SolicitudParcialVO> solicitudesParciales = null;
        if (sols != null && !sols.isEmpty()) {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
            solicitudesParciales = new Vector<>(sols.size());
            for (SolicitudParcial sp : sols) {
                SolicitudParcialVO  obj = new SolicitudParcialVO();
                obj.setId(sp.getId());
                obj.setFechaCreacion(sp.getFechaCreacion() != null ? sdf.format(sp.getFechaCreacion()) : "");
                obj.setNombreAfiliado(sp.getNombreCompletoAfiliado());
                obj.setNroSolicitud(sp.getNroSolicitud());
                obj.setNumeroIdentificacionAfiliado(sp.getNumeroIdentificacionAfiliado());
                obj.setTipoIdentificacionAfiliado(sp.getTipoIdentificacionAfiliado() != null ? sp.getTipoIdentificacionAfiliado().getDescripcion() : null); 
                solicitudesParciales.add(obj);
            }
        }        
        PageVO<SolicitudParcialVO> paginaBandeja = new PageVO<>(solicitudesParciales, pagination.getTotalPages(), solicitudesParciales != null?solicitudesParciales.size():0, pagination.getActualPage());
        return paginaBandeja;
    }
	    
	
	public SolicitudParcialDto findById(Integer id){
		return spRepo.findOne(id).toDto();
	}

	public void eliminarSolicitudParcial(String nroSolicitud) throws NoSePuedeElilminarSPException {
		try {
			spRepo.delete(spRepo.findOneByNroSolicitud(Integer.valueOf(nroSolicitud)));	
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoSePuedeElilminarSPException();
		}
	}
}
