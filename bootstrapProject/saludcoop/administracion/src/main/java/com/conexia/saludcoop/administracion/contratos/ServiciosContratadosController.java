/**
 * 
 */
package com.conexia.saludcoop.administracion.contratos;

import com.conexia.saludcoop.common.dto.ServicioDto;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

/**
 * <b>Controlador para la pagina de servicios contratados</b>
 * 
 * @author <a href="mailto:prodas@conexia.com">Pablo Rodas</a>
 * @date 03/10/2013
 *
 */
@Named
@Scope("request")
public class ServiciosContratadosController {
    
    @Inject
    private ContratosModel model;
    
}
