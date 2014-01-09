/**
 *
 */
package com.conexia.saludcoop.administracion.contratos;

import com.conexia.saludcoop.administracion.contratos.dtos.ServicioContratoDto;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.conexia.saludcoop.administracion.contratos.dtos.EspecialidadContratoDto;
import com.conexia.saludcoop.common.dto.ContratoDto;
import com.conexia.saludcoop.common.dto.IpsDto;
import com.conexia.saludcoop.common.dto.SedeIpsDto;
import com.conexia.saludcoop.common.dto.TarifarioDto;
import com.conexia.saludcoop.common.dto.TipoPlanContratoDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * <b>CDI Bean para la administración de contratos</b>
 * Clase encargada de guardar el estado de las página de administración de
 * contratos
 *
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 03/10/2013
 *
 */
@Named
@Scope("session")
public class ContratosModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<ServicioContratoDto> serviciosDisponibles;
    private boolean checkAllServices;
    private List<TarifarioDto> tarifariosDisponibles;
    private ServicioContratoDto servicioSeleccionado;
    private Map<Integer, TarifarioDto> tarifariosMap;
    private List<EspecialidadContratoDto> especialidadesDisponibles;
    private List<IpsDto> listaIps;
    private List<SedeIpsDto> listaSedeIps;
    private ContratoDto contratoDto;
    private SedeIpsDto sedeIpsDto;
    private List<TipoPlanContratoDto> tiposPlanContrato;
    private List<TarifarioDto> tarifariosDto;
    
    private String estado;
    private String descipcionServicioFiltro;
    
    public List<ServicioContratoDto> getServiciosDisponibles() {
        if (serviciosDisponibles == null) {
            serviciosDisponibles = new ArrayList<>();
            for (int i = 0; i < 300; i++) {
                ServicioContratoDto dto = new ServicioContratoDto();
                dto.setCodigoMinisterioSalud("1234MS" + i);
                dto.setDescripcion("Servicio " + i);
                dto.setHospitalario(Boolean.TRUE);
                dto.setId(i);
                dto.setNivelDeAtencion(Double.valueOf(Math.random() * 4).intValue() + 1);
                serviciosDisponibles.add(dto);
            }
        }
        return serviciosDisponibles;
    }
    
    public void probar() {
        System.out.println("holasss");
    }

    public void setServiciosDisponibles(List<ServicioContratoDto> serviciosDisponibles) {
        this.serviciosDisponibles = serviciosDisponibles;
    }

    public boolean isCheckAllServices() {
        return checkAllServices;
    }

    public void setCheckAllServices(boolean checkAllServices) {
        for (ServicioContratoDto dto : serviciosDisponibles) {
            dto.setChecked(checkAllServices);
        }
        this.checkAllServices = checkAllServices;
    }

    public List<TarifarioDto> getTarifariosDisponibles() {
        if(tarifariosDisponibles == null) {
            tarifariosDisponibles = new ArrayList<>();
            tarifariosMap = new TreeMap<>();
            for (int i = 0; i < 5; i++) {
                TarifarioDto dto = new TarifarioDto();
                dto.setFechaFinalizacion(new Date());
                dto.setFechaInicio(new Date());
                dto.setId(i);
                dto.setPorcentajeAjuste(10d);
                tarifariosDisponibles.add(dto);
                tarifariosMap.put(i, dto);
            }
        }
        return tarifariosDisponibles;
    }

    public void setTarifariosDisponibles(List<TarifarioDto> tarifariosDisponibles) {
        this.tarifariosDisponibles = tarifariosDisponibles;
    }

    public ServicioContratoDto getServicioSeleccionado() {
        return servicioSeleccionado;
    }

    public void setServicioSeleccionado(ServicioContratoDto servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }

    public Map<Integer, TarifarioDto> getTarifariosMap() {
        return tarifariosMap;
    }

    public void setTarifariosMap(Map<Integer, TarifarioDto> tarifariosMap) {
        this.tarifariosMap = tarifariosMap;
    }
    public List<EspecialidadContratoDto> getEspecialidadesDisponibles() {
        if(especialidadesDisponibles == null){
            especialidadesDisponibles = new ArrayList<>();
            for(int i = 0; i < 30; i++){
                EspecialidadContratoDto dto = new EspecialidadContratoDto();
                dto.setId(i);
                dto.setDescripcion("Procedimiento " + i);
                dto.setProcedimientos(null);
                dto.setTarifaExcepcion(0.0);
                especialidadesDisponibles.add(dto);
            }   
        }
        return especialidadesDisponibles;
    }

    public void setEspecialidadesDisponibles(List<EspecialidadContratoDto> especialidadesDisponibles) {
        this.especialidadesDisponibles = especialidadesDisponibles;
    }
    
    public List<IpsDto> getListaIps() {
        listaIps= new ArrayList<IpsDto>();
         IpsDto dto= new IpsDto();
         dto.setId(1);
         dto.setRazonSocial("IPS 1");
         listaIps.add(dto);
         dto= new IpsDto();
         dto.setId(2);
         dto.setRazonSocial("IPS 2");
         listaIps.add(dto);
         return listaIps;
     }

     public void setListaIps(List<IpsDto> listaIps) {
         this.listaIps = listaIps;
     }
     public List<SedeIpsDto> getListaSedeIps() {
         this.listaSedeIps=new ArrayList<SedeIpsDto>();
         SedeIpsDto dto= new SedeIpsDto();
         dto.setId(1);
         dto.setNombre(String.valueOf(sedeIpsDto.getIps().getId()));
         listaSedeIps.add(dto);
         return listaSedeIps;
     }

     public void setListaSedeIps(List<SedeIpsDto> listaSedeIps) {
         this.listaSedeIps = listaSedeIps;
     }
          
     public ContratoDto getContratoDto() {
         if(contratoDto==null){
             contratoDto= new ContratoDto();
             contratoDto.setTarifario(new TarifarioDto());
             contratoDto.setTipoPlanContrato(new TipoPlanContratoDto());
         }
         return contratoDto;
     }

     public void setContratoDto(ContratoDto contratoDto) {
         this.contratoDto = contratoDto;
     }
     
     public List<TipoPlanContratoDto> getTiposPlanContrato() {
         tiposPlanContrato = new ArrayList<TipoPlanContratoDto>();
         TipoPlanContratoDto tipoContratoDto= new TipoPlanContratoDto();
         tipoContratoDto.setId(1);
         tipoContratoDto.setDescripcion("tipo contrato1");
         tiposPlanContrato.add(tipoContratoDto);
         return tiposPlanContrato;
     }

     public void setTiposPlanContrato(List<TipoPlanContratoDto> TiposPlanContrato) {
         this.tiposPlanContrato = TiposPlanContrato;
     }
     
     public List<TarifarioDto> getTarifariosDto() {
         tarifariosDto= new ArrayList<TarifarioDto>();
         TarifarioDto dto= new TarifarioDto();
         dto.setId(1);
         tarifariosDto.add(dto);
         return tarifariosDto;
     }

     public void setTarifariosDto(List<TarifarioDto> tarifariosDto) {
         this.tarifariosDto = tarifariosDto;
     }
     
     public String getEstado() {
         return estado;
     }

     public void setEstado(String estado) {
         this.estado = estado;
     }
     
     public SedeIpsDto getSedeIpsDto() {
         if(sedeIpsDto==null){
             sedeIpsDto=new SedeIpsDto();
             sedeIpsDto.setIps(new IpsDto());
         }
         return sedeIpsDto;
     }

     public void setSedeIpsDto(SedeIpsDto sedeIpsDto) {
         this.sedeIpsDto = sedeIpsDto;
     }

    public String getDescipcionServicioFiltro() {
        return descipcionServicioFiltro;
    }

    public void setDescipcionServicioFiltro(String descipcionServicioFiltro) {
        this.descipcionServicioFiltro = descipcionServicioFiltro;
    }

}
