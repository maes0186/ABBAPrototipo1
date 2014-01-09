package com.conexia.saludcoop.web.manager;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.entity.maestro.Afiliado;
import com.conexia.saludcoop.common.entity.maestro.Departamento;
import com.conexia.saludcoop.common.entity.maestro.Eps;
import com.conexia.saludcoop.common.entity.maestro.EstadoCivil;
import com.conexia.saludcoop.common.entity.maestro.Genero;
import com.conexia.saludcoop.common.entity.maestro.Ips;
import com.conexia.saludcoop.common.entity.maestro.Municipio;
import com.conexia.saludcoop.common.entity.maestro.SedeIps;
import com.conexia.saludcoop.common.entity.maestro.TipoIdentificacionDocumento;
import com.conexia.saludcoop.common.exception.ServiceException;
import com.conexia.saludcoop.common.repository.AfiliadoRepository;

/**
 * @author nobregon
 * 
 */
@Service
@Transactional
public class AfiliadoManager {
	
	@Autowired
	private AfiliadoRepository afiliadoRepo;

	private static Logger LOGGER = LoggerFactory.getLogger(AfiliadoManager.class);

	public AfiliadoManager() {

	}

//	public List<AfiliadoDto> getBeneficiarioByNombreCompleto(String nombreCompleto) throws ServiceException {
//
//		List<AfiliadoDto> dtoList = new ArrayList<AfiliadoDto>();
//		
//		try {
//			//obtengo la lista de entities
//			List<Afiliado> entityList = afiliadoDao.getAfiliadosByNombreCompleto(nombreCompleto);
//			
//			//transformo cada elemento de la lista de entidades a su DTO y la meto en la lista de DTOs
//			for (Afiliado afiliado : entityList) {
//				//TODO Mejorar lo del Casteo - hablar con los duenios de DataPage
//				AfiliadoDto dto = (AfiliadoDto)DtoFactory.mapToDto(afiliado);
//				dtoList.add(dto);
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e.toString());
//			throw new ServiceException("Error retrieving Beneficiario list", e);
//		}
//		return dtoList;
//	}
	
	public AfiliadoDto getBeneficiarioByTipoNumeroDocumento(
	        final Integer tipoDocumento, final String numeroDocumento) throws ServiceException {
		Afiliado afiliado=null;
		try {
			//afiliado = afiliadoRepo.findOneByTipoIdentificacionIdAndNumeroIdentificacion(tipoDocumento, numeroDocumento);
			afiliado = new Afiliado();
			afiliado.setPrimerNombre("Carlos");
			afiliado.setSegundoNombre("Esteban");
			afiliado.setPrimerApellido("Abad");
			afiliado.setSegundoApellido("Gomez");
			afiliado.setFechaNacimiento(((new LocalDate()).minusYears(18)).toDate());
			
			Ips ipsAfiliacion = new Ips();
			ipsAfiliacion.setDireccion("Direccion Ips");
			ipsAfiliacion.setRazonSocial("Riesgo de Fractura");
			ipsAfiliacion.setTelefono("5555555");
			
			SedeIps sede = new SedeIps();
			sede.setIps(ipsAfiliacion);
			
			afiliado.setSedeIpsAfiliacion(sede);
			
			Eps eps = new Eps();
			eps.setId(1);
			eps.setRazonSocial("SaludCoop");
			
			afiliado.setEps(eps);
			afiliado.setTipoIdentificacion(new TipoIdentificacionDocumento());
			afiliado.setEstadoCivil(new EstadoCivil());
			afiliado.setGenero(new Genero());
			
			Municipio mun = new Municipio();
			mun.setCodigo("001");
			mun.setDescripcion("MEDELLÍN");
			Departamento dpto = new Departamento();
			/*dpto.setMunicipios(new HashSet<Municipio>());
			dpto.getMunicipios().add(mun);*/
			
			dpto.setCodigo("05");
			dpto.setDescripcion("ANTIOQUIA");
//			mun.setDepartamento(dpto);
			afiliado.setMunicipioResidencia(mun);

			
			
		} catch (Exception e) {
			LOGGER.error(e.toString());
			return null;
		}
		
		
		return afiliado.toDto();
	}
	
	
//	public boolean actualizarDatosBeneficiario(BeneficiarioDto beneDto){
//		try {
//			afiliadoDao.update((Beneficiario)beneDto.toEntity());
//			return true;
//		} catch (DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//	}
}