<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="./../../includes/header.jsp">
    <jsp:param name="includeMenu" value="true" />
</jsp:include>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<c:set var="TITULO" value="${param.titulo}" />
<fmt:message key='label.id.type' var="TIPO_DOCUMENTO_LABEL" />
<fmt:message key='label.id.number' var="NUMERO_DOCUMENTO_LABEL" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />


<style type="text/css">
.hiddenRow {
	padding: 0 !important;
}
</style>

<form class="form-horizontal" id="formFiltroPrincipal" method="post">
    <div class="row">
        <div class=" col-lg-12">
            <div id="messages"></div>
            <div class="well form-horizontal">
                <fieldset id="fieldSetFiltro">
                    <blockquote class="col-lg-12" style="padding: 2px; font-size: 20px;">
                        <strong>
                           ${param.TITULO}
                        </strong>
                    </blockquote>
                    <c:if test="${param.esProfesional}">
                    <jsp:include page="./profesionalesFiltro.jsp"/>
                    </c:if>
                </fieldset>
                <input type="hidden" name="actualPage" id="actualPage" value="1" />
            </div>
        </div>
    </div>

    <div class="row" id="crudResult">
        <div class=" col-lg-12">
            <div class="well form-horizontal">
                <div id="crudContent" class="tab-content"></div>
            </div>
        </div>
    </div>
		
	
	
</form>

<%@include file="./../../includes/footer.jsp"%>
<script src="${webContext}/resources/js/crudTable.js"></script>
<script>
    $(document).ready(function() {
    	$("#crearNuevoElemento").click(function(){
    		editarElemento(null);
    	});
    	//No se ejecuta la busqueda de ips
    	ejecutarBusqueda=false;
		
        selectDefaultValues();
        setContainer($('#crudContent'));
        actualizarElementos(1);

        $('#search').click(function(event) {
            $('#messages').empty();
            actualizarElementos(1);
        });

        if('${msgRespuesta != null}') {
            createMessages($("#messages"), ${msgRespuesta != null ? msgRespuesta : 'null'}, 'info', 'icon-info-sign');
        }

    });
    
	function getMensajeRango(){
		var min = $("#formFiltroPrincipal [id^=tipo] option:selected").attr("data-min-length");
		var max = $("#formFiltroPrincipal [id^=tipo] option:selected").attr("data-max-length");
		
		if( min === max){
			return '<fmt:message key="validation.restriction.exactlyNChars"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'.replace("{1}",min);
		}else{
			return '<fmt:message key="validation.restriction.betweenNandMChars"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'.replace("{1}",min).replace("{2}",max);	
		}
	}
    
	// El parámetro page corresponde a la página de la consulta que se quiere cargar
    function actualizarElementos(page) {
	    // Se actualiza el valor de la página actual para que lo envíe en la solicitud
    	$('#actualPage').val(page);
    	$('#crudContent').empty();
        var formData = $('#formFiltroPrincipal').serializeArray();
        if ('${param.esProfesional}'){
        	url = "${webContext}/profesionalCrud/cargarProfesionales";
        	$.post(url, formData, function(data) {
                if (data.generalErrors.length > 0) {
                    createMessages($("#messages"), data.generalErrors, 'info', 'icon-info-sign');
                    $("#crudResult").hide();
                } else {
                    $("#crudResult").show();
                	createTableExpandibleAsync(data.content, data.actualPage, data.totalPages);
                }
            }, "json"); 
        };
        
    }
    
    /**
    * Selecciona por defecto los valores que se habían aplicado previamente al filtro 
    */
    function selectDefaultValues() {
        try {
        	if ('${param.esProfesional}'){
            selectDefault("tipoDocumentoSearch", ${crudFilter.tipoDocumento != null ? crudFilter.tipoDocumento : 'null'});
        	}
        } catch(e) {
            log(e);
        }
    }
</script>