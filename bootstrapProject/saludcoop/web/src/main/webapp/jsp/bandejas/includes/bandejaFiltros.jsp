<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@page import="com.conexia.saludcoop.common.entity.maestro.EstadoAutorizacion"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="./../../includes/header.jsp">
    <jsp:param name="includeMenu" value="true" />
</jsp:include>
<jsp:include page="./../../includes/components.jsp" />

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<c:set var="TITULO" value="${param.titulo}" />
<c:set var="redireccion" value="<%=SystemConstants.REDIRECCIONAMIENTO%>" />
<c:set var="anulacion" value="<%=SystemConstants.ANULAMIENTO%>" />
<c:set var="estadoNoAutorizada" value="<%=SystemConstants.ESTADO_NO_AUTORIZADA%>" />
<c:set var="estadoAutorizada" value="<%=SystemConstants.ESTADO_AUTORIZADA%>" />
<c:set var="bandejaCTCNac" value="<%=SystemConstants.BANDEJA_CTC_NAC%>" />
<c:set var="bandejaACNac" value="<%=SystemConstants.BANDEJA_AC_NAC%>" />
<c:set var="bandejaCTCReg" value="<%=SystemConstants.BANDEJA_CTC_REG%>" />
<c:set var="bandejaACReg" value="<%=SystemConstants.BANDEJA_AC_REG%>" />
<c:set var="tutelas" value="<%=SystemConstants.BANDEJA_TUTELAS%>" />

<fmt:message key='label.id.type' var="TIPO_DOCUMENTO_LABEL" />
<fmt:message key='label.id.number' var="NUMERO_DOCUMENTO_LABEL" />
<fmt:message key='label.EPS' var="EPS_LABEL" />
<fmt:message key='label.solicitud.estado' var="ESTADO_LABEL" />
<fmt:message key='label.technology' var="TECNOLOGIA_LABEL" />
<fmt:message key='label.medicamentos' var="MEDICAMENTOS_LABEL" />
<fmt:message key='label.procedimientos' var="PROCEDIMIENTOS_LABEL" />
<fmt:message key='label.insumo' var="INSUMOS_LABEL" />
<fmt:message key='label.regional' var="REGIONAL_LABEL" />
<fmt:message key='label.tipoSolicitud' var="TIPO_SOLICITUD_LABEL" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />
<fmt:message key="label.bandeja.consultaSolicitudes" var="CONSULTA_SOLICITUDES" />


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
                            <c:choose>
                                <c:when test="${not empty TITULO}">${TITULO}</c:when>
                                <c:otherwise>${CONSULTA_SOLICITUDES}</c:otherwise>
                            </c:choose>
                        </strong>
                    </blockquote>
                    <div id="filtrosContent" class="tab-content">
                        <div class="form-group form-group-sm ">
                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="numeroSolicitudSearch"><fmt:message
                                    key="label.solicitud.numero" /></label>
                            <div class=" col-lg-3 col-sm-3 col-xs-6">
                                <input type="text" class="form-control input-sm digits-only" id="numeroSolicitudSearch" name="numeroSolicitud"
                                    value="${bandejasFilter.numeroSolicitud}"
                                    placeholder="<fmt:message
                                    key="label.solicitud.numero" />">
                            </div>
                            
                             <c:if test="${param.esAutorizacion && !param.esSolicitudParcial}">
	                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="numeroAutorizacionSearch"><fmt:message
	                                    key="label.autorizacion.numero" /></label>
	                            <div class=" col-lg-3 col-sm-3 col-xs-6">
	                                <input type="text"class="form-control input-sm digits-only" id="numeroAutorizacionSearch" name="numeroAutorizacion"
	                                    value="${bandejasFilter.numeroAutorizacion}"
	                                    placeholder="<fmt:message
	                                    key="label.autorizacion.numero" />">
	                            </div>
                            </c:if>
                        </div>
                        <div id="identificacionComponent" class="form-group form-group-sm identificacionComponent ">
							<label class=" col-lg-2 control-label control-label-sm" for="">Tipo Documento</label>
							<div class="col-lg-3">
								<select id="tipoDocumento" name="tipoDocumento" class="form-control input-sm input-sm">
									<option value="" data-min-length="6" data-max-length="15" data-alpha="false">${SELECCIONE_LABEL}</option>
									<c:forEach items="${tiposDeDocumento}" var="tipoIdent">
                                      <c:if test="${tipoIdent.aplicaAfiliado}">
										<option value="${tipoIdent.id}" data-min-length="${tipoIdent.minLength}" data-max-length="${tipoIdent.maxLength}" data-alpha="${tipoIdent.esAlfanumerico}">${tipoIdent.descripcion}</option>	
									  </c:if>
                                    </c:forEach>
								</select>
							</div>
							<label class="col-lg-2 control-label control-label-sm" for="">${NUMERO_DOCUMENTO_LABEL }</label>
							<div class="col-lg-3">
								<input type="text" class="form-control input-sm input-sm" id="numeroDocumento" name="numeroDocumento" placeholder ="${NUMERO_DOCUMENTO_LABEL }">
							</div>
						</div>
                        <c:if test="${param.esSolicitudParcial}">
                            <input placeholder="Fecha de Creación" row=2 role="date-range" name="fechaCreacion" class="col-lg-12 template-hidden" nameRight="fechaHasta" nameLeft="fechaDesde" >
                        
                        
                            <div class="abmComponent withSearch">
            							<div class="row searchForm">
            								 <input placeholder="Fecha de Creación" row=2 role="date-range" name="fechaCreacion" class="col-lg-12 template-hidden" nameRight="fechaHasta" nameLeft="fechaDesde" >
            							</div>
            
            							<div class="row">
            								<div class="col-lg-12">
            									<table class="table  table-hover">
            										<thead>
            											<tr class="row">
            												<th class="data col-lg-3" name="fechaCreacion">Fecha Creación</th>            												
            											</tr>
            										</thead>
            									</table>
            								</div>
            							</div>
            					</div>
                        </c:if>
                        
                        <c:if test="${!param.esSolicitudParcial}">
                             <div class="form-group form-group-sm ">                                
                                <label for="epsSearch" class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"><fmt:message
                                         key="label.EPS" /></label> 
                                <div class="col-lg-3 col-sm-3 col-xs-6">
                                     <form:select path="eps" name="eps" id="epsSearch" class="form-control input-sm"> 
                                         <form:option value="" label="${SELECCIONE_LABEL}" /> 
                                         <form:options items="${eps}" itemLabel="razonSocial" itemValue="id" /> 
                                     </form:select> 
                                 </div> 
                                 <c:if test="${!param.esIps}"> 
                                     <label for="regionalSearch" class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"><fmt:message 
                                             key="label.regional" /></label> 
                                     <div class="col-lg-3 col-sm-3 col-xs-6"> 
                                        <form:select path="regionales" name="regional" id="regionalSearch" class="form-control input-sm"> 
                                            <form:option value="" label="${SELECCIONE_LABEL}" /> 
                                             <form:options items="${regionales}" itemLabel="descripcion" itemValue="id" /> 
                                         </form:select> 
                                     </div> 
                                 </c:if> 
                             </div> 
                       </c:if>
                       <c:if test="${!param.esSolicitudParcial}">
                           <div class="form-group form-group-sm ">
                                <c:if test="${!param.esAutorizacion && !param.tutelas}">
    	                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="estadoSolicitudSearch"><fmt:message
    	                                    key="label.solicitud.estado" /></label>
    	                            <div class=" col-lg-3 col-sm-3 col-xs-6">
    	                                <form:select path="estadosAutorizacion" name="estadoSolicitud" id="estadoSolicitudSearch"
    	                                    class="form-control input-sm">
    	                                    <form:option value="" label="${SELECCIONE_LABEL}" />
    	                                    <form:options items="${estadosAutorizacion}" itemLabel="descripcion" itemValue="id" />
    	                                </form:select>
    	                            </div>
                                </c:if>                            
                                <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="tecnologiaSearch"><fmt:message
                                        key="label.technology" /></label>
                                <div class=" col-lg-3 col-sm-3 col-xs-6">
                                    <select name="tecnologia" id="tecnologiaSearch" class="form-control input-sm">
                                        <option value="">${SELECCIONE_LABEL}</option>
                                        <option value="1">${MEDICAMENTOS_LABEL}</option>
                                        <option value="2">${PROCEDIMIENTOS_LABEL}</option>
                                        <option value="3">${INSUMOS_LABEL}</option>
                                    </select>
                                </div>
                            </div>
                        </c:if>
                        <br />
                        <div class="form-group text-right" style="">
                            <div class=" col-lg-12">
                                <button type="button" id="search" class="btn btn-info btn-sm">
                                    <fmt:message key="label.button.buscar" />
                                    <i class="icon-search"></i>
                                </button>
                                <button type="button" id="clean" class="btn btn-warning btn-sm" onclick="$('#formFiltroPrincipal input, #formFiltroPrincipal select').val(''); updateSolicitudes(1);">
                                    <fmt:message key="label.button.limpiar" />
                                    <i class="icon-eraser"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <input type="hidden" name="actualPage" id="actualPage" value="1" />
                <c:if test="${param.tutelas}">
                    <input type="hidden" name="esTutela" value="true" />
                </c:if>
            </div>
        </div>
    </div>

    <div class="row" id="bandejaResult">
        <div class=" col-lg-12">
            <div class="well form-horizontal">
                <div id="bandejaContent" class="tab-content"></div>
            </div>
        </div>
    </div>
		
	<input name="bandeja" type="hidden" value="${param.bandeja}" />
</form>

<div class="modal fade" id="pickEntregaModal"  style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title">Seleccione la entrega para la cual quiere imprimir el ticket</h3>
	</div>
	<div class="modal-body">
		<div class="row col-lg-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<th colspan="2"></th>
						<th colspan="2" class="text-center" style="padding:0">Vigencia</th>
						<th></th>
					</tr>
					<tr>
						<th class="text-center">#</th>
						<th class="text-center">Cantidad</th>
						<th class="text-center">Fecha Inicio</th>
						<th class="text-center">Fecha Fin</th>
						<th class="text-center">Imprimir</th>
					</tr>
				</thead>
				<tbody class="text-center">
				</tbody>
			</table>
		</div>	
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-danger" data-dismiss="modal">Cerrar</a>
	</div>
</div>

<%@include file="./../../includes/footer.jsp"%>
<script src="${webContext}/resources/js/bandejasTable.js"></script>
<script>
var esAnulacionVar;
    $(document).ready(function() {
    	//No se ejecuta la busqueda de ips
    	ejecutarBusqueda=false;
    	$("#tipoDocumento").change(changeTipoIdentificacion);
		$("#numeroDocumento").bind("keypress",keypressNumeroIdentificacion);
		$("#numeroDocumento").bind("keypress",captureEnter);
		
		$("#messages").empty();
		
        if(eval("${msgRespuesta != null && msgRespuesta != ''}")) {
            createMessages($("#messages"), ['${msgRespuesta != null ? msgRespuesta : null}'], 'info', 'icon-info-sign', true);
            $.post( "${webContext}/bandejas/clearMsgRespuesta", function( data ) {
            });
        }
        
        selectDefaultValues();
        setContainer($('#bandejaContent'));
        updateSolicitudes(1);

        $('#search').click(function(event) {
            $('#messages').empty();
            if (!$.parseJSON($("#formFiltroPrincipal #tipoDocumento option:selected").attr("data-alpha"))){
            	$("#formFiltroPrincipal").validate(
    					{
    						onfocusout : false,
    						focusInvalid : false,
    						focusCleanup : false,
    						onkeyup : false,
    						onclick : false,
    						rules : {
    							numeroDocumento : {required:function(){return $("#formFiltroPrincipal #tipoDocumento").val() != "";}, 
    													minlength:parseInt($("#formFiltroPrincipal #tipoDocumento option:selected").attr("data-min-length")),
    													maxlength:parseInt($("#formFiltroPrincipal #tipoDocumento option:selected").attr("data-max-length")),
    													digits:true	}
    						},
    						messages : {
    							numeroDocumento : {	required:'<fmt:message key="validation.required"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.',
    												minlength:getMensajeRango,
    												maxlength:getMensajeRango,
    												digits: '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'}												
    						},  errorPlacement: function (error, element){
    					    	appendErrorMessage($("#formFiltroPrincipal #messages"), error.text());
    					    }
    					});
	
            }else{
            	$("#formFiltroPrincipal").validate(
    					{
    						onfocusout : false,
    						focusInvalid : false,
    						focusCleanup : false,
    						onkeyup : false,
    						onclick : false,
    						rules : {
    							numeroDocumento : {required:function(){return $("#formFiltroPrincipal #tipoDocumento").val() != "";}, 
    													minlength:parseInt($("#formFiltroPrincipal #tipoDocumento option:selected").attr("data-min-length")),
    													maxlength:parseInt($("#formFiltroPrincipal #tipoDocumento option:selected").attr("data-max-length")),
    													digits:false}
    						},
    						messages : {
    							numeroDocumento : {	required:'<fmt:message key="validation.required"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.',
    												minlength:getMensajeRango,
    												maxlength:getMensajeRango}												
    						},  errorPlacement: function (error, element){
    					    	appendErrorMessage($("#formFiltroPrincipal #messages"), error.text());
    					    }
    					});
            }
                        
            
           	if ( $("#formFiltroPrincipal").valid())
           		updateSolicitudes(1);          	
           	
           	
           	
        });

        if(eval('${param.esSolicitudParcial}')){    
         	  $(".col-lg-offset-9").remove();   
     	 }
        
        $("#filtrosContent input[type=text]:visible").bind("keypress", captureEnterBandejas);

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
    var formDataSerialized = "";
	// El parámetro page corresponde a la página de la consulta que se quiere cargar
    function updateSolicitudes(page) {
	    
	    // Se actualiza el valor de la página actual para que lo envíe en la solicitud
    	$('#actualPage').val(page);
    	$('#bandejaContent').empty();
        var formData = $('#formFiltroPrincipal').serializeArray();
        formDataSerialized = $('#formFiltroPrincipal').serialize();
        var url = "../bandejas/cargarSolicitudes";
        
        if (eval('${param.esAutorizacion}')){
        	if(eval('${param.esRedireccion}')){
			    url = "${webContext}/prestador/cargarAutorizacionesRedireccionamiento";
        	}else if(eval('${param.esAnulacion}')){
    			url = "${webContext}/prestador/cargarAutorizacionesAnulacion";	
    		}else if(eval('${param.esSolicitudParcial}')){
    		    url = "${webContext}/prestador/cargarSolicitudParcial";
        	}else {
        		url = "${webContext}/prestador/cargarAutorizaciones";
        	};
        };
        
        $.get(url, formData, function(data) {
            if (data.generalErrors.length > 0) {
                createMessages($("#messages"), data.generalErrors, 'info', 'icon-info-sign', true);
                $("#bandejaResult").hide();
            } else {
                $("#bandejaResult").show();
                if (eval('${param.esRedireccion}') || eval('${param.esAnulacion}')){
                	iconoHeader= (eval('${param.esRedireccion}')) ? 'icon-share-alt': 'icon-remove-sign';
                	createTableSolicitudesAnidadaAsyncRedirec(data.content, data.actualPage, data.totalPages,iconoHeader);        	
                }else if(eval('${param.esSolicitudParcial}') ){
                    createTableSolicitudesAnidadaAsyncParcial(data.content, data.actualPage, data.totalPages);
                }else {
                	createTableSolicitudesAnidadaAsync(data.content, data.actualPage, data.totalPages, data.totalItems);
                
                }
                if ("${nombreBandeja}" == "${bandejaCTCNac}" || "${nombreBandeja}" == "${bandejaACNac}" || "${nombreBandeja}" == "${tutelas}" || "${nombreBandeja}" == "${bandejaCTCReg}" || "${nombreBandeja}" == "${bandejaACReg}") 
                	$("#bandejaResult #bandejaContent").prepend("<div onclick='printXls()' class='cursor-pointer col-lg-12'><div class='alert alert-dismissable alert-info'><span>Haga click aqui para imprimir este listado completo.</span></div></div>");
                
            }
        }, "json");
    }

	function printXls(){
		 location.href = "${webContext}/bandejas/downloadXls/${nombreBandeja}?"+formDataSerialized;
	}
	
    function createTableSolicitudesAnidadaAsync(data, actualPage, totalPages, totalItems) {
    	createTableExpandibleAsync(data, actualPage, totalPages, totalItems);
    }
    function createTableSolicitudesAnidadaAsyncRedirec(data, actualPage, totalPages,iconoHeader) {
    	createTableExpandibleAsyncRedirec(data, actualPage, totalPages,iconoHeader);
    }
    
    function createTableSolicitudesAnidadaAsyncParcial(data, actualPage, totalPages) {
        createTableExpandibleAsyncParcial(data, actualPage, totalPages);
        $(".col-lg-offset-9").remove();   
    }
    
    
    function cargarItemsSolicitud(idFila) {
    	if (idFila == '' || idFila == 'undefined' ) {
    		return;
    	}
    	var spanCollapse = $('#spanCollapse_'+idFila);
    	if(spanCollapse.html() == "+") {
    	    spanCollapse.html("-");
    	} else {
    	    spanCollapse.html("+");
    	}
    	var divContenedor = $('#itemsSolicitudNro_'+idFila);
    	if (!divContenedor.html()) {
    		// Si no está dibujada la subtabla la voy a buscar y dibujo
    	    // Se serializan los datos del formulario de filtros para aplicar los filtros también a los items
            var dataForm = $('#formFiltroPrincipal').serializeArray();
            // Se cambia el valor del número de la solicitud por la seleccionada en la tabla
            dataForm[0].value = idFila;
    		
            if ('${param.esAutorizacion}'){
            	dataForm[0].value = null;
            	dataForm[1].value = idFila;
            };
    		$.get("../bandejas/cargarItemsSolicitud", dataForm, function(data) {
                if (data.generalErrors.length > 0) {
                    divContenedor.html(data.generalErrors);
                } else {
                	dataSet = data.content;
                	var subtable  = $('<table class="table subtable"></table>');
                	createHeader(subtable, $(dataSet), true);
                	var tbody = $('<tbody></tbody>');
                	
                	$(dataSet).each(function(i, node){
            			var row = $('<tr class="'+node.estado.replace(/ /g,"")+'"></tr>');
            				for (var dataProp in node){
            				    // Se verifica si la propiedad no es nula para mostrarla en la tabla
            				    if(node[dataProp] != null) {
            				        row.append('<td>' + node[dataProp] + '</td>');			        
            				    }
            				}
            			
            			var td = $("<td style='width: 60px;'></td>");	
            			if ('${param.esAutorizacion}'){
            			    
            			    var tag = "a";
            			    
            			    if(node.estado != null && node.estado == '${estadoAutorizada}'){
            			        tag = "span";
            			    }
            			    
            			    td.append('<' + tag + ' style="cursor:pointer" id="actionTable" title="Gestionar Item" onclick="gestionar(\''+node.numeroItem+'\')"><i style="font-size: 16px;" class="icon-edit"></i></' + tag + '>&nbsp;&nbsp;');
                			td.append('<' + tag + '  class="cursor-pointer" title="Imprimir Ticket" onclick="checkEntregasBeforePrint('+node.numeroItem+')" target="_blank"><i style="font-size: 16px;" class="icon-print"></i></' + tag + '>');
                		}else{
            			    td.append('<a style="cursor:pointer" id="actionTable" title="Gestionar Item" onclick="gestionar(\''+node.numeroItem+'\')"><i style="font-size: 16px;" class="icon-edit"></i></a>&nbsp;&nbsp;');
            				if(node.estado != null && node.estado == '${estadoNoAutorizada}'){
                		       td.append('<a href="/web/auditorCTC/formatoNegacionServicios/'+node.numeroItem+'" target="_blank" title="Formato Negación de Servicios"><i style="font-size: 16px;" class="icon-file-text"></i></a>');
            				}
                		}
            			row.append(td);	
            			tbody.append(row);		
            		});
            		subtable.append(tbody);
            		divContenedor.append(subtable);
                }

            }, "json");
    		
    	}
    }
    
    function checkEntregasBeforePrint(numeroItem){
    	$.get("${webContext}/ticket/checkEntregas/"+numeroItem, 
    			function(data){
    				if (data.content.cantidad > 1) {
    					$("#pickEntregaModal tbody").empty();
    					for (var i = 0; i<data.content.cantidad; i++){
    						$("#pickEntregaModal tbody").append("<tr><td>"+data.content.entregas[i].nroEntrega+"</td><td>"+data.content.entregas[i].cantidadEntrega+"</td><td>"+data.content.entregas[i].fechaInicioVigencia+"</td><td>"+data.content.entregas[i].fechaFinVigencia+"</td><td><a href='${webContext}/ticket/printTicket/"+numeroItem+"/"+data.content.entregas[i].nroEntrega+"' target='_blank'><i style='font-size: 16px;' class='icon-print'></i></a></td></tr>");
    					}
    					$("#pickEntregaModal").modal();
    				}
    				else{
    					location.href="${webContext}/ticket/printTicket/"+numeroItem+"/1";
    				}
    			});
    }
    
    function cargarItemsSolicitudRedirec(idFila) {
    	if (idFila == '' || idFila == 'undefined' ) {
    		return;
    	}
    	var spanCollapse = $('#spanCollapse_'+idFila);
    	if(spanCollapse.html() == "+") {
    	    spanCollapse.html("-");
    	} else {
    	    spanCollapse.html("+");
    	}
    	var divContenedor = $('#itemsSolicitudNro_'+idFila);
    	if (!divContenedor.html()) {
    		// Si no está dibujada la subtabla la voy a buscar y dibujo
    	    // Se serializan los datos del formulario de filtros para aplicar los filtros también a los items
            var dataForm = $('#formFiltroPrincipal').serializeArray();
            // Se cambia el valor del número de la solicitud por la seleccionada en la tabla
            dataForm[0].value = idFila;
    		
            if (eval('${param.esAutorizacion}')){
            	dataForm[0].value = null;
            	dataForm[1].value = idFila;
            }
            
            tAccion = (eval('${param.esAnulacion}'))? '${anulacion}' : '${redireccion}';
            
    		$.get("${webContext}/bandejas/cargarItemsSolicitud/"+tAccion, dataForm, function(data) {
                if (data.generalErrors.length > 0) {
                    divContenedor.html(data.generalErrors);
                } else {
                	dataSet = data.content;
                	var subtable  = $('<table class="table subtable"></table>');
                	createHeaderRedirec(subtable, $(dataSet), true);
                	var tbody = $('<tbody></tbody>');
                	
                	$(dataSet).each(function(i, node){
            			var row = $('<tr class="'+node.estado.replace(/ /g,"")+'"></tr>');
            				for (var dataProp in node){
            				    // Se verifica si la propiedad no es nula para mostrarla en la tabla
            				    if(node[dataProp] != null) {
            				    	if("esNivelAutorizacionAuditor"!=dataProp)row.append('<td>' + node[dataProp] + '</td>');			        
            				    }
            				}
            				
            				var icono = '';
            				var gestion = '';
            				//New
            				var link;
            				var title;
            				
           					if(node.esNivelAutorizacionAuditor){
               					//icono="icon-arrow-up";
               					gestion="escalarAuditor";
   								link='<i style="font-size: 16px;" class="icon-arrow-up"></i>';
               					title='title=\'<fmt:message key="label.escalar"/>\'';
               				} else {
               					if (eval('${param.esAnulacion}')){
                   					//icono="icon-remove-circle";
                   					gestion="gestionarAnulacion";
       								link='<i style="font-size: 16px;" class="icon-remove-circle"></i>';
                   					title='title=\'<fmt:message key="label.button.anular"/>\'';
               					}else if(eval('${param.esRedireccion}')){
                   					//icono="icon-share-alt";
                   					gestion="gestionarRedireccion";
       								link='<i style="font-size: 16px;" class="icon-share"></i>';
                   					title='title=\'<fmt:message key="label.button.redireccionar"/>\'';
                   				}
               				}
            				row.append('<td><a style="cursor:pointer" id="actionTable" ' + title + ' onclick="'+gestion+'(\''+node.numeroItem+'\')">'+link+'</a></td>');
            			tbody.append(row);
            		});
            		subtable.append(tbody);
            		divContenedor.append(subtable);
                }
            }, "json");
    		
    	}
    }
    /**
    * Selecciona por defecto los valores que se habían aplicado previamente al filtro 
    */
    function selectDefaultValues() {
        try {
            selectDefault("tipoDocumentoSearch", ${bandejasFilter.tipoDocumento != null ? bandejasFilter.tipoDocumento : 'null'});
            selectDefault("epsSearch", ${bandejasFilter.eps != null ? bandejasFilter.eps : 'null'});
            selectDefault("regionalSearch", ${bandejasFilter.regional != null ? bandejasFilter.regional : 'null'});
            selectDefault("estadoSolicitudSearch", ${bandejasFilter.estadoSolicitud != null ? bandejasFilter.estadoSolicitud : 'null'});
            selectDefault("tecnologiaSearch", ${bandejasFilter.tecnologia != null ? bandejasFilter.tecnologia : 'null'});
            selectDefault("tipoSolicitudSearch", ${bandejasFilter.tipoSolicitud != null ? bandejasFilter.tipoSolicitud : 'null'});            
        } catch(e) {
            log(e);
        }
    }
</script>