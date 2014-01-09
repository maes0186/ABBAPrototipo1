package com.conexia.saludcoop.web.vo.utils;

import java.text.SimpleDateFormat;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.util.I18NUtils;
import com.conexia.saludcoop.web.vo.AfiliadoVO;
@Component
public class ParserVO {

	
	public AfiliadoVO getAfiliadoVO(AfiliadoDto afiliadoDto) {

		AfiliadoVO af = new AfiliadoVO();

		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		
		af.setPrimerNombre(afiliadoDto.getPrimerNombre());
		af.setSegundoNombre(afiliadoDto.getSegundoNombre());
		af.setPrimerApellido(afiliadoDto.getPrimerApellido());
		af.setSegundoApellido(afiliadoDto.getSegundoApellido());
		
		af.setEps(afiliadoDto.getEps().getRazonSocial());
		af.setTipoIdent(I18NUtils.getInstance().getText(afiliadoDto.getTipoIdentificacion().getDescripcion()));
		af.setTipoIdentID(afiliadoDto.getTipoIdentificacion().getId());
		af.setNumeroIdentificacion(afiliadoDto.getNumeroIdentificacion());
		af.setGenero(afiliadoDto.getGenero().getDescripcion());

		af.setEdad(String.valueOf(Years.yearsBetween(
				new LocalDate(afiliadoDto.getFechaNacimiento()),
				new LocalDate()).getYears()));
		
		af.setFechaNacimiento(sf.format(afiliadoDto.getFechaNacimiento()));
		af.setNivel("1"); //TODO: FALTA NIVEL EN LA ENTITY
		af.setTipoAfiliado("afiliado1"); //TODO: FALTA TIPO AFILIADO EN LA ENTITY
		//af.setRazonEstado(afiliadoDto.getRazonEstado());
		af.setIpsPrimaria(afiliadoDto.getSedeIpsAfiliacion().getIps().getRazonSocial());
		af.setDireccionIPS(afiliadoDto.getSedeIpsAfiliacion().getIps().getDireccion());
		af.setTelefonoIPS(afiliadoDto.getSedeIpsAfiliacion().getIps().getTelefono());
//		af.setDepartamento(afiliadoDto.getMunicipioResidencia().getDepartamento().getDescripcion());
		af.setMunicipio(afiliadoDto.getMunicipioResidencia().getDescripcion());
		af.setDireccionResidencial(afiliadoDto.getDireccionDeResidencia());
		af.setEmailPersonal(afiliadoDto.getEmailPersonal());
		af.setTelefonoResidencial(afiliadoDto.getTelefonoResidencial());
		af.setTelefonoCelular(af.getTelefonoCelular());
		
		

		return af;
	}

	
}
