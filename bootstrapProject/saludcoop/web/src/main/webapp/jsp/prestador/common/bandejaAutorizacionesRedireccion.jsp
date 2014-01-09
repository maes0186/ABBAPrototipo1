<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="./../../bandejas/includes/bandejaFiltros.jsp">
    <jsp:param name="esIps" value="true" />
    <jsp:param name="esAutorizacion" value="true" />
    <jsp:param name="esRedireccion" value="true" />
</jsp:include>
<script>
var localizacion;
var item;
var urlGrupo;

    function gestionarPadre(numAutorizacion) {
    	var dataForm = $('#formFiltroPrincipal').serializeArray();
    	$.get("${webContext}/web/bandejas/verificarNivelAutorizacionGrupo/"+numAutorizacion, dataForm, function(data) {
    		if(data=='REDIRECCION'){
	    		$("#redireccionamientoIpsModal").modal('show');
	        	localizacion = "${webContext}/web/bandejas/bandejaRedireccion";
	        	item = numAutorizacion;
	    	}
    		else if(data=='ESCALAR'){
    			$("#justificacionModal").modal('show');
    	    	localizacion = "${webContext}/web/bandejas/bandejaRedireccion";
    	    	item = null;
    	    	urlGrupo="${webContext}/web/prestador/funcionalidadEscalamientoGrupo/";
    		}
	    	else{
				//TODO: pendiente definir para los casos donde hay redireccionamiento
				// y escalar al mismo tiempo
	    	}
    	});
    	
    }
    
    function gestionarRedireccionPadre(numAutorizacion) {
    	$("#redireccionamientoIpsModal").modal('show');
    	localizacion = "${webContext}/web/bandejas/bandejaRedireccion";
    	item = numAutorizacion;
    }
    
    function gestionarRedireccion(numItem) {
    	ejecutarBusqueda = true;
    	localizacion = "${webContext}/web/prestador/gestionarItemAutorizadoRedireccionamiento/";
    	item = numItem;
    	window.location = localizacion+item;
    }
    
    function escalarAuditor(numItem) {
    	$("#justificacionModal").modal('show');
    	localizacion = "${webContext}/web/bandejas/bandejaRedireccion";
    	item = numItem;
    	urlGrupo="${webContext}/web/prestador/funcionalidadEscalamientoRed/";
    }
    
    function redireccionGrupo(){
    	$.ajax({
    	    type : 'POST',
    	    async : false,
    	    url : "${webContext}/web/prestador/funcionalidadRedireccionamientoGrupos/"+item,
    	    data : $("#idSedeIps").serializeArray(),
    	    success : function(response) {
    	    	window.location = localizacion;
    	    }
    	});
    }
    
    
    function Justificacion(){
    	urlFinal=urlGrupo;
    	if(item!=null)urlFinal=urlGrupo+item;
        $.ajax({
            type : 'POST',
            async : false,
            url : urlFinal,
            data : $("#idJustificacion").serializeArray(),
            success : function(response) {
            }
        });
    	window.location = localizacion;
	}
</script>

<div class="modal fade" id="justificacionModal" tabindex="-1" role="dialog" aria-labelledby="justificacionLabel" aria-hidden="true">
    <div class="modal-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <label for="idJustificacion" class="control-label control-label-sm text-right ">Justificacion</label>
                </div>
                <div class="col-lg-9">
                    <textarea class="form-control input-sm"  id="idJustificacion" name="idJustificacion" placeholder="Justificacion"></textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">

        <button type="submit" id="btnAceptar" class="btn btn-success" onclick="Justificacion()">
            <fmt:message key="label.button.aceptar" />
        </button>

        <button type="button" class="btn btn-default" data-dismiss="modal">
            <fmt:message key="label.button.cerrar" />
        </button>
    </div>
</div>
                                                                    <!-------- OTRO MODAL ---------->
<div class="modal fade" id="redireccionamientoIpsModal" tabindex="-1" role="dialog" aria-labelledby="redireccionamientoModal" aria-hidden="true" data-width="65%">
    <div class="modal-body">
        <div class="row">
            <div class="col-lg-12">
                <div class="form-horizontal">
                    <jsp:include page="./../../bandejas/includes/direccionamientoIpsLdf.jsp">
                        <jsp:param value="true" name="editable" />
                        <jsp:param value="${ipsTitulo}" name="ipsTitulo" />
                    </jsp:include>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="submit" id="btnAceptar" class="btn btn-success" onclick="redireccionGrupo()">
            <fmt:message key="label.button.aceptar" />
        </button>
        <button type="button" class="btn btn-default" data-dismiss="modal">
            <fmt:message key="label.button.cerrar" />
        </button>
    </div>
</div>