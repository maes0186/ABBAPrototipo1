<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />

<fmt:message key='label.select' var="SELECCIONE_LABEL" />
<fmt:message key='label.address.apartment' var="DEPARTAMENTO" />
<fmt:message key='label.address.city_state' var="CIUDAD_MUNICIPIO" />
<fmt:message key='label.direccion' var="DIRECCION" />
<fmt:message key='label.email' var="EMAIL" />
<fmt:message key='label.address.landline' var="TELEFONO_FIJO" />
<fmt:message key='label.address.cellphone' var="TELEFONO_CELULAR" />

<div class="row" id="datosAfiliado" style="display: none">
    <div class="col-lg-12">
        <div class="well">
            <form class="form-horizontal" id="formAfiliado" action="comprobacionDerechos/comprobar" method="post">
                <input type="hidden" id="id" name="id"  class="template-hidden" />
                <fieldset>
                    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
                        <strong>Afiliado</strong> <small style="display: inline"> <span id="primerNombre"></span> <span
                            id="segundoNombre"></span> <span id="primerApellido"></span> <span id="segundoApellido"></span>
                        </small>
                    </blockquote>
                    <div class="collapsible-content">
                    	<div id="message"></div>
                        <div class="form-group form-group-sm">
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="epsNombre">Eps:</label>
                            <div class=" col-lg-2 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="epsNombre" readonly>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-12" for="tipoIdent">Tipo Identificación:</label>
                            <div class=" col-lg-2 col-xs-12">
                                <input style="cursor: auto" class="form-control input-sm" id="tipoIdent" readonly> <input
                                    type="hidden" id="tipoIdentID" name="tipoIdentID" readonly>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-12" for="numeroIdentificacion">Número de
                                Identificación:</label>
                            <div class=" col-lg-2 col-xs-12">
                                <input style="cursor: auto" class="form-control input-sm" id="numeroIdentificacion"
                                    name="numeroIdentificacion" readonly>
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="genero">Género: </label>
                            <div class=" col-lg-2 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="genero" readonly>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="edad">Edad: </label>
                            <div class=" col-lg-2 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="edad" readonly>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-12" for="fechaNacimiento">Fecha Nacimiento:</label>
                            <div class=" col-lg-2 col-xs-12">
                                <input style="cursor: auto" class="form-control input-sm" id="fechaNacimiento" readonly>
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                        	<label id="labelNivel" class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="nivel"></label>
                            <div class=" col-lg-2 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="nivel" value="1" readonly>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="tipoAfiliado">Tipo Afiliado: </label>
                            <div class=" col-lg-2 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="tipoAfiliado" readonly>
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                        	<label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="estado">Estado Afiliación:</label>
                            <div class=" col-lg-2 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="estado" readonly>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-12" for="razonEstado">Razón estado afiliación:</label>
                            <div class=" col-lg-6 col-xs-12">
                                <input style="cursor: auto" class="form-control input-sm" id="razonEstado" readonly>
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="ipsPrimaria">IPS Primaria:</label>
                            <div class=" col-lg-4 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="ipsPrimaria" readonly>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-3 col-xs-6" for="municipioIpsPrimaria">Ciudad / Municipio IPS Primaria:</label>
                            <div class=" col-lg-3 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="municipioIpsPrimaria" readonly>
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="direccionIPS">Dirección IPS Primaria:</label>
                            <div class=" col-lg-6 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="direccionIPS" readonly>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="telefonoIPS">Teléfono IPS Primaria:</label>
                            <div class=" col-lg-2 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="telefonoIPS" readonly>
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="departamentoId">${DEPARTAMENTO}:</label>
                            <div class=" col-lg-2 col-xs-6">
                                <form:select path="departamentos" name="departamentoId" id="departamentoId" class="form-control input-sm">
                                    <form:option value="" label="${SELECCIONE_LABEL}" />
                                    <form:options items="${departamentos}" itemLabel="descripcion" itemValue="id" />
                                </form:select>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-12" for="municipioId">${CIUDAD_MUNICIPIO}:</label>
                            <div class=" col-lg-2 col-xs-12">
                                <select id="municipioId" name="municipioId" class="form-control input-sm">
                                    <option value="">${SELECCIONE_LABEL}</option>
                                </select>
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="direccionResidencial">${DIRECCION}:</label>
                            <div class=" col-lg-2 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm" id="direccionResidencial" name="direccionResidencial">
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-12" for="emailPersonal">${EMAIL}:</label>
                            <div class=" col-lg-2 col-xs-12">
                                <input style="cursor: auto" class="form-control input-sm" id="emailPersonal" name="emailPersonal">
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-12 digits-only" for="telefonoResidencial">${TELEFONO_FIJO}:</label>
                            <div class=" col-lg-2 col-xs-12">
                                <input style="cursor: auto" class="form-control input-sm" id="telefonoResidencial" name="telefonoResidencial">
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-xs-6" for="telefonoCelular">${TELEFONO_CELULAR}:</label>
                            <div class=" col-lg-2 col-xs-6">
                                <input style="cursor: auto" class="form-control input-sm digits-only" id="telefonoCelular" name="telefonoCelular">
                            </div>
                        </div>

                        <input type="hidden" name="target" value="${param.target}" />
                        <c:if test="${param.editable}">
                            <div class="form-group form-group-sm text-right" style="padding-right: 20px">
                                <br />
                                <button type="submit" id="aceptarAfiliado" class="btn btn-sm btn-success">Siguiente</button>
                                <button type="button" id="guardarAfiliado" class="btn btn-sm btn-default">Guardar</button>
                            </div>
                        </c:if>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>


<script>

<c:if test="${!param.editable}">
	$("#formAfiliado fieldset").find("input").prop("readonly", true);
	$("#formAfiliado fieldset").find("select").attr("disabled", true);
</c:if>

$(function() {	
	$("select#departamentoId").change(function(){
		reloadMunicipiosByDepartamento($("select#departamentoId"), $("select#municipioId"));
	});
	
	$("#formAfiliado").validate({
		onfocusout : false,
        focusInvalid : false,
        focusCleanup : false,
        onkeyup : false,
        onclick : false,
        rules:{
        	departamentoId: "required",
        	municipioId: "required",
        	direccionResidencial: "required",
        	emailPersonal: {
        		required : false,
        		email: true 
        		},
			telefonoResidencial: {
				required : false,
				digits: true
				},
			telefonoCelular: {
				required : false, 
				digits: true
			}
        },
        messages:{
        	departamentoId: '<fmt:message key="validation.required"><fmt:param value="${DEPARTAMENTO}"/></fmt:message>',
        	municipioId: '<fmt:message key="validation.required"><fmt:param value="${CIUDAD_MUNICIPIO}"/></fmt:message>',
        	direccionResidencial: '<fmt:message key="validation.required"><fmt:param value="${DIRECCION}"/></fmt:message>',
        	emailPersonal: {
        		required : '<fmt:message key="validation.required"><fmt:param value="${EMAIL}" /></fmt:message>',
        		email: '<fmt:message key="validation.email.valid"><fmt:param value="${EMAIL}" /></fmt:message>'
        	},
        	telefonoResidencial:{
        		required : '<fmt:message key="validation.required"><fmt:param value="${TELEFONO_FIJO}" /></fmt:message>',
            	digits: '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${TELEFONO_FIJO}"/></fmt:message>'
        	},
        	telefonoCelular:{
        		required : '<fmt:message key="validation.required"><fmt:param value="${TELEFONO_CELULAR}" /></fmt:message>',
        		digits: '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${TELEFONO_CELULAR}"/></fmt:message>'
        	}
        }
	});
	$("button#guardarAfiliado").click(function() {
		if($("#formAfiliado").valid()){
			$.ajax({
	            url: "${webContext}/afiliado/guardarDatosContacto",
	            type: "POST",
	            data: $('#formAfiliado').serializeArray(),
	            datatype: "json",
	            success: function (data) {
	            	 if(data.content){
	            		 createMessages($("#message"), ["Datos Almacenados Exitosamente"], 'success', 'icon-ok-sign');
	            	 }else{
	            		 createErrorMessages($("#message"), data.generalErrors);	            		 
	            	 }
	            }
	        });
		}
	});
	$("button#aceptarAfiliado").click(function() {
		$("#departamentoId").rules("remove");
		$("#municipioId").rules("remove");
		$("#direccionResidencial").rules("remove");
		$("#emailPersonal").rules("remove");
		$("#telefonoResidencial").rules("remove");
		$("#telefonoCelular").rules("remove");
	});

});
	function displayDatosAfiliado(data) {
    	 
    	if (data.generalErrors.length > 0) {
    		errorsContainer.empty();
    		$.each(data.generalErrors, function(i, value) {
    			appendErrorMessage(errorsContainer, value);
    		});
    	}
    
    	// llena los span con los datos correspondientes
    	if (data.content && data.content.primerApellido != null) {
    		$("#formAfiliado").validate().resetForm();
    		$("#formAfiliado .error").removeClass("error");
    		//Se insertan todos los campos en sus span correspondientes 		
    		$.map(data.content, function(value, key) {
    
    			var element = $("#datosAfiliado #" + key);
    			if($("#datosAfiliado #" + key).prop('tagName') == 'SELECT'){
    				try{
    					selectDefault(key, value);
        		        if(key == 'departamentoId'){        		        	
        	    			$("select#departamentoId").change();
        		        }
    	    		}catch(e){}     
    			}else if($("#datosAfiliado #" + key).prop('tagName') == 'INPUT') {
    				element.val(value);
    				if($(element).is('[readonly]') ){
	    				$(element).attr({
	    					'data-toggle':"tooltip", 
	    					'data-placement':"left", 
	    					'data-original-title':value
	    					}).tooltip();
    				}
    			} else {
    				element.html(value);
    			}    
    		});

    		if(data.content.esRegimenContributivo) {
    			$("#labelNivel").html("Nivel IBC:");
    		} else {
    			$("#labelNivel").html("Nivel SISBEN:");
			}
    		
    		$("#message").empty();
    		$("#aceptarAfiliado").show();

    		$("#datosAfiliado").slideDown();

    		if (data.content.habilitarCreacionSolicitud) {
    			$("#aceptarAfiliado").focus();
    		} else {
    			createMessages($("#message"), ["El estado de afiliación del usuario no permite registrar servicios, remitir a atención al usuario."], 'info', 'icon-info-sign');
    			$("#aceptarAfiliado").hide();
        	}
    	}
    }
</script>