package com.conexia.saludcoop.web.vo.utils;

import java.text.SimpleDateFormat;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.stereotype.Component;

import com.conexia.saludcoop.common.dto.AfiliadoDto;
import com.conexia.saludcoop.common.enumerator.TipoAfiliado;
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
		
		af.setEps(afiliadoDto.getEps().getId());
		af.setEpsNombre(afiliadoDto.getEps().getRazonSocial());
		af.setTipoIdent(afiliadoDto.getTipoIdentificacion().getDescripcion());
		af.setTipoIdentID(afiliadoDto.getTipoIdentificacion().getId());
		af.setNumeroIdentificacion(afiliadoDto.getNumeroIdentificacion());
		af.setGenero(afiliadoDto.getGenero().getDescripcion());

		af.setEstado(afiliadoDto.getEstadoAfiliacion().getDescripcion());
		af.setEdad(String.valueOf(Years.yearsBetween(new LocalDate(afiliadoDto.getFechaNacimiento()), new LocalDate()).getYears()));

		af.setFechaNacimiento(sf.format(afiliadoDto.getFechaNacimiento()));
		if (afiliadoDto.getNivelSisBen() != null) {
			af.setNivel(afiliadoDto.getNivelSisBen().getDescripcion());
		}
		if (afiliadoDto.getNivelIbc() != null) {
			af.setNivel(afiliadoDto.getNivelIbc().getDescripcion());
		}
		af.setRegimenAfiliacion(afiliadoDto.getRegimenAfiliacion());
		af.setTipoAfiliado(TipoAfiliado.fromId(afiliadoDto.getTipoAfiliado()).getDescripcion());
		af.setRazonEstado(afiliadoDto.getRazonEstadoAfiliacion());
		if (afiliadoDto.getSedeIpsAfiliacion() != null) {
			af.setIpsPrimaria(afiliadoDto.getSedeIpsAfiliacion().getNombre());
			af.setMunicipioIpsPrimaria(afiliadoDto.getSedeIpsAfiliacion().getMunicipio().getDescripcion());
			af.setDireccionIPS(afiliadoDto.getSedeIpsAfiliacion().getDireccion());
			af.setTelefonoIPS(afiliadoDto.getSedeIpsAfiliacion().getTelefono1());
		}
		af.setDepartamento(afiliadoDto.getMunicipioResidencia().getDepartamento().getDescripcion());
		af.setDepartamentoId(afiliadoDto.getMunicipioResidencia().getDepartamento().getId());
		af.setMunicipio(afiliadoDto.getMunicipioResidencia().getDescripcion());
		af.setMunicipioId(afiliadoDto.getMunicipioResidencia().getId());
		af.setDireccionResidencial(afiliadoDto.getDireccionDeResidencia());
		af.setEmailPersonal(afiliadoDto.getEmailPersonal());
		af.setTelefonoResidencial(afiliadoDto.getTelefonoResidencial());
		af.setTelefonoCelular(afiliadoDto.getTelefonoCelular());
		af.setNombreCompleto(afiliadoDto.getNombreCompleto());
		af.setId(afiliadoDto.getId());
		af.setTutela(afiliadoDto.getTutela());
		return af;
	}
}
