<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="./../../bandejas/includes/bandejaFiltros.jsp">
    <jsp:param name="esIps" value="true" />
    <jsp:param name="esAutorizacion" value="true" />
    <jsp:param name="esRedireccion" value="false" />
    <jsp:param name="esAnulacion" value="true" />
</jsp:include>
<script>
var localizacion;
var item;

    function gestionarPadre(numAutorizacion) {
    	$("#justificacionModal").modal('show');
    	localizacion = "${webContext}/web/bandejas/bandejaAnulacion";
    	item = numAutorizacion;
    }
    
    function gestionarAnulacion(numItem) {
    	localizacion = "${webContext}/web/prestador/gestionarItemAutorizadoAnulacion/";
    	item = numItem;
    	window.location = localizacion+item;
    }
    
    function escalarAuditor(numItem) {
    	$("#justificacionModal").modal('show');
    	localizacion = "${webContext}/web/bandejas/bandejaAnulacion";
    	item = numItem;
    }
    
    function Justificacion(){
    	$.ajax({
    	    type : 'POST',
    	    async : false,
    	    url : "${webContext}/web/prestador/funcionalidadEscalamientoAnu/"+item,
    	    data : $("#idJustificacion").serializeArray(),
    	    success : function(response) {
    	    	window.location = localizacion;
    	    }
    	});
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