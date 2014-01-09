<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="itemProcedimiento" value="<%=SystemConstants.ITEM_PROCEDIMIENTO_STRING%>" />
<c:set var="itemMedicamento" value="<%=SystemConstants.ITEM_MEDICAMENTO_STRING%>" />

<fmt:message key='label.periodo.aprobadoDias' var="PERIODO_LABEL" />
<fmt:message key='label.dias.periodo' var="DIAS_PER_LABEL" />
<fmt:message key='label.unidades.aprobadasPeriodo' var="UNIDADES_LABEL" />
<fmt:message key='label.numeroEntregas' var="NUM_ENTREGAS_LABEL" />
<fmt:message key='label.justificacion.anulacion' var="JUSTIFICACION_ANULACION_LABEL" />
<fmt:message key='label.justificacion' var="JUSTIFICACION_LABEL" />
<fmt:message key='label.causaExterna' var="CAUSA_EXTERNA_LABEL" />
<fmt:message key='label.finalidad' var="FINALIDAD_LABEL" />
<fmt:message key='label.tipoCatastrofico' var="TIPO_CATASTROFICO_LABEL" />
<fmt:message key='label.entidadRecobro' var="ENTIDAD_RECOBRO_LABEL" />
<fmt:message key='label.concepto' var="CONCEPTO_LABEL" />
<fmt:message key='label.infoSolicitud.causalAnulacion' var="CAUSAL_ANU_LABEL" />
<fmt:message key='label.infoSolicitud.causalDevolucion' var="CAUSAL_DEV_LABEL" />
<fmt:message key='label.infoSolicitud.criterioNegociacion1' var="CRITERIOS_NEG_LABEL" />
<fmt:message key='label.lateralidad' var="LATERALIDAD_LABEL" />
<fmt:message key='label.rangoinvalido' var="RANGO_INVALIDO" />
<c:if test="${bandejaAC.tipoItem == itemMedicamento}">
    <fmt:message key='label.unidades.aprobadasPeriodo' var="UNIDADES_LABEL" />
</c:if>
<c:if test="${bandejaAC.tipoItem == itemProcedimiento}">
    <fmt:message key='label.cantidadAprobada' var="UNIDADES_LABEL" />
</c:if>

<jsp:include page="/jsp/includes/components.jsp" />

<c:set var="webContext" value="${pageContext.request.contextPath}" />

<script>
    $(function() {
        $("#fieldSetConceptoAC").prop("disabled", "disabled");
        $("#justificacionAC").val($.trim($("#justificacionAC").val()));

        $("#btnAceptar").click(function(event) {
            event.preventDefault();
            if(eval('${bandejaAC.tipoItem == itemMedicamento}')) {
            	validarCantidadesMedicamento();
            }
            validacionForm();
        });
        
        if(eval('${redireccionAutorizAuditor}')){
            jQuery.validator.addMethod("compareDifferent", function(value, element, param) {
                return value != param;
            });
            
            $("#btnRedireccionar").click(function() { 
                event.preventDefault(); 
//                 if ($('#idSedeIps').valid()) {
                    var formData = $('#idSedeIps').serializeArray();
                    formData.push({name: "itemId", value: $("#itemId").val()});
                    var post = jQuery.post("${webContext}/redireccionarAutorizacion/procesarTramite", formData, function(data) {
                        if (data.content) {
                        	location.href = "${webContext}/bandejas/${nombreBandeja}";
                        } else {
                            // Se verifican los mensajes de error
                            if (data.generalErrors.length > 0) {
                                newAlert('danger', data.generalErrors, 2000, 'icon-warning-sign');
                            }
                        }
                    });
//                 } else {
//                     newAlert('danger', [ '<fmt:message key="validation.errors" />','<fmt:message key="validation.redireccion.ips"/>' ], 2000, 'icon-warning-sign');
//                 }

            });
		}else{
			try {
				$("#idSedeIps").rules("remove");
			} catch(e) {}
		}
        
        if(eval('${anulacionAutorizAuditor}')){
			var modal = $("#modal-template-confirm").clone().attr("id","eliminarConfirmar");
			modal.find(".modal-body").empty();
			modal.find(".modal-body").append("<p>Está seguro que desea Eliminar la Autorización, Desea Continuar?</p>");
			modal.find("#aceptar").click(function(){
                var formData = $('#justificacionAnulacion').serializeArray();
                formData.push({name: "itemId", value: $("#itemId").val()});
                var post = jQuery.post("../procesarTramite", formData, function(data) {
                    if (data.content) {
                        location.href = "${webContext}/bandejas/${nombreBandeja}";
                    } else {
                        // Se verifican los mensajes de error
                        if (data.generalErrors.length > 0) {
                            newAlert('danger', data.generalErrors, 2000, 'icon-warning-sign');
                        }
                    }
                });
			});
			
			$("#btnAnular").parent().append(modal);
            $("#btnAnular").click(function() {
                $("#justificacionAnulacion").rules('add', {
                	required : true, 
                	minlength: 30,
                	messages: {
                		required: '<fmt:message key="validation.justificacion.anulacion" />',
                		minlength:'<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="${JUSTIFICACION_ANULACION_LABEL}"/><fmt:param value="30"/></fmt:message>'
                	}
                });
            	
                if ($('#justificacionAnulacion').valid()) {
                	$(this).parent().find("#eliminarConfirmar").modal();
                } else {
                    newAlert('danger', [ '<fmt:message key="validation.errors" />' ], 2000, 'icon-warning-sign');
                }
            });
        }else{
			try {
				$("#justificacionAnulacion").rules("remove");
			} catch(e) {}
        }
        
        // Se establecen los parámetros de validación del formulario
        $('#formInfoSolicitudAC')
                .validate(
                        {
                            onfocusout : false,
                            focusInvalid : false,
                            focusCleanup : false,
                            onkeyup : false,
                            onclick : false,
                            rules : {
                                justificacion : {required: true, minlength: 100},
                                concepto : "required"
                            },
                            messages : {
                                periodoAprobado : {
                                    required : '<fmt:message key="validation.required"><fmt:param value="${PERIODO_LABEL}"/></fmt:message>',
                                    digits : '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${PERIODO_LABEL}"/></fmt:message>'
                                },
                                diasPeriodo : {
                                    required : '<fmt:message key="validation.required"><fmt:param value="${DIAS_PER_LABEL}"/></fmt:message>',
                                    digits : '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${DIAS_PER_LABEL}"/></fmt:message>'
                                },
                                unidadesAprobadas : {
                                    required : '<fmt:message key="validation.required"><fmt:param value="${UNIDADES_LABEL}"/></fmt:message>',
                                    digits : '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${UNIDADES_LABEL}"/></fmt:message>'
                                },
                                numeroEntregas : {
                                    required : '<fmt:message key="validation.required"><fmt:param value="${NUM_ENTREGAS_LABEL}"/></fmt:message>',
                                    digits : '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${NUM_ENTREGAS_LABEL}"/></fmt:message>'
                                },
                                lateralidad : {
                                    required : '<fmt:message key="validation.required"><fmt:param value="${LATERALIDAD_LABEL}"/></fmt:message>',
                                    digits : '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${LATERALIDAD_LABEL}"/></fmt:message>'
                                },
                                justificacion : { 
                                    required:'<fmt:message key="validation.required"><fmt:param value="${JUSTIFICACION_LABEL}"/></fmt:message>',
                                    minlength:'<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="${JUSTIFICACION_LABEL}"/><fmt:param value="100"/></fmt:message>'
                                },
                                causaExterna : '<fmt:message key="validation.required"><fmt:param value="${CAUSA_EXTERNA_LABEL}"/></fmt:message>',
                                finalidad : '<fmt:message key="validation.required"><fmt:param value="${FINALIDAD_LABEL}"/></fmt:message>',
                                tipoCatastrofico : '<fmt:message key="validation.required"><fmt:param value="${TIPO_CATASTROFICO_LABEL}"/></fmt:message>',
                                entidadRecobro : '<fmt:message key="validation.required"><fmt:param value="${ENTIDAD_RECOBRO_LABEL}"/></fmt:message>',
                                concepto : '<fmt:message key="validation.required"><fmt:param value="${CONCEPTO_LABEL}"/></fmt:message>',
                                causalAnulacion : '<fmt:message key="validation.required"><fmt:param value="${CAUSAL_ANU_LABEL}"/></fmt:message>',
                                causalDevolucion : '<fmt:message key="validation.required"><fmt:param value="${CAUSAL_DEV_LABEL}"/></fmt:message>'
                            },
                        });

        $("#btnCancelar").click(function() {
    		location.href = "${webContext}/bandejas/${nombreBandeja}";
        });

        $("#procesarFechas").click(function() {
            procesaryvalidarfechas();
        });
    });
    
    function calcularPeriodoAprobado() {
        var val1 = $("#numeroEntregasAC").val();
        var val2 = $("#diasPeriodoAC").val();
        $("#periodoAprobadoAC").val(val1 * val2);
    }
    
    function validarCantidadesMedicamento() {
        if($("input[name=concepto]:checked").val() == 1) {
            $.ajax({
                type : 'POST',
                async : false,
                url : "${webContext}/bandejas/cumpleRangoMedicamento",
                data : $("#formInfoSolicitudAC").serializeArray(),
                success : function(response) {
                    adicionarRegla(response);
                }
            });
        }
    }

    function adicionarRegla(data) {
        var respuesta = false;
        if (data[0] == 0) {
            respuesta = true;
        }
        
        jQuery.validator.addMethod("notEqual", function(value, element, param) {
            return Boolean(respuesta);
        }, function() {
            if(data[0] == 1) {
            	return data[1]; 
            } else if(data[0] == 2) {
                return '<fmt:message key="validation.required.rangoinvalido"><fmt:param value="' + data[2] + '" /></fmt:message> ' + data[1];
            } else {
                return data[1];
            }
        });
        
        jQuery.validator.addMethod("equalTo", function(value, element, param) {
            return value == param;
        }, '<fmt:message key="validation.required.cantidadesdiferentes" />');
        
        val = $("#numeroEntregasAC").val();
        val2 = $("#diasPeriodoAC").val();
        
        $("#periodoAprobadoAC").rules('add', {
            equalTo : (val * val2).toString()
        });

        $("#unidadesAprobadasAC").rules('add', {
            notEqual : true
        });
    }

    function procesaryvalidarfechas() {
        validarCantidadesMedicamento();
        var valid = true;
        $("#fieldSetConceptoAC input").each(function(i, e) {
            if (!$(e).valid()) {
                valid = false;
            }
        });
        if (valid) {

            $("#fechasEntrega").modal('show');
            $('#tablaFechas').dataTable().fnDestroy();
            $('#tablaFechas').dataTable({
                "bDeferRender" : true, // Utilidad para optimar datasource al
                // usar Ajax
                "bInfo" : false, // Informacion Pagina Y de X
                "bSort" : true, // Ordenar columnas
                "bPaginate" : false, // Paginacion
                // "iDisplayLength" : 40, // # de registros por pagina
                "bLengthChange" : false, // Cantidad de Paginas
                "bFilter" : false, // Boton Search
                "bAutoWidth" : true, // Ancho Automatico de Columnas
                "fnServerParams" : function(aoData) {
                    var formData = $('#formInfoSolicitudAC').serializeArray();
                    aoData.push.apply(aoData, formData);
                },
                // cargar
                "bServerSide" : false,
                "sAjaxSource" : '${webContext}/bandejas/fechasEntrega',
                "aoColumns" : [ {
                    "mDataProp" : "id"
                }, {
                    "mDataProp" : "descripcion"
                } ]
            });
            $("#tablaFechas").css("width", "100%");
        }
    }
    
    function validacionForm() {
        if ($("#formInfoSolicitudAC").valid()) {
            var formData = $('#formInfoSolicitudAC').serializeArray();
            var post = jQuery.post("../procesarTramite", formData, function(data) {
                if (data.content) {
                    location.href = "${webContext}/bandejas/${nombreBandeja}";
                } else {
                    // Se verifican los mensajes de error
                    if (data.generalErrors.length > 0) {
                        newAlert('danger', data.generalErrors, 2000, 'icon-warning-sign');
                    }
                }
            });
        } else {
            $("label[for=concepto]").appendTo("#conceptoValidation");
            $("label[for=causalAnulacion]").appendTo("#conceptoValidation");
            $("label[for=causalDevolucion]").appendTo("#conceptoValidation");
            $("label[for=criteriosNegacion]").appendTo("#conceptoValidation");
            newAlert('danger', [ '<fmt:message key="validation.errors" />' ], 2000, 'icon-warning-sign');
        }
    }
</script>
<!-- Agrega reglas a los campos del formulario dependiendo del concepto seleccionado -->
<c:if test="${bandejaAC.tipoItem == itemMedicamento}">
    <script>
                    function addRules() {
                        var validator = $("#formInfoSolicitudAC").validate();
                        validator.resetForm();

                        if ($("input[name=concepto]:checked").val() == 1) {
                            $("#fieldSetConceptoAC").removeAttr("disabled");
                            $("#periodoAprobadoAC").rules("add", {
                                required : true,
                                digits : true
                            });
                            $("#diasPeriodoAC").rules("add", {
                                required : true,
                                digits : true
                            });
                            $("#unidadesAprobadasAC").rules("add", {
                                required : true,
                                digits : true
                            });
                            $("#numeroEntregasAC").rules("add", {
                                required : true,
                                digits : true
                            });
                            $("#causaExternaSelect").rules("add", "required");
                            $("#finalidadSelect").rules("add", "required");
                            $("#tipoCatastroficoSelect").rules("add", "required");
                            $("#entidadRecobroSelect").rules("add", "required");
                        } else {
                            $("#fieldSetConceptoAC").prop("disabled", "disabled");
                            $("#periodoAprobadoAC").rules("remove");
                            $("#diasPeriodoAC").rules("remove");
                            $("#unidadesAprobadasAC").rules("remove");
                            $("#numeroEntregasAC").rules("remove");
                            $("input[name=causalAnulacion]").rules("add", "required");
                            $("input[name=causalDevolucion]").rules("add", "required");
                            $("#causaExternaSelect").rules("remove");
                            $("#finalidadSelect").rules("remove");
                            $("#tipoCatastroficoSelect").rules("remove");
                            $("#entidadRecobroSelect").rules("remove");
                        }
                    }
                </script>
</c:if>
<c:if test="${bandejaAC.tipoItem == itemProcedimiento}">
    <script>
                    function addRules() {
                        var validator = $("#formInfoSolicitudAC").validate();
                        validator.resetForm();

                        if ($("input[name=concepto]:checked").val() == 1) {
                            $("#fieldSetConceptoAC").removeAttr("disabled");
                            $("#unidadesAprobadasAC").rules("add", {
                                required : true,
                                digits : true
                            });
                            $("#causaExternaSelect").rules("add", "required");
                            $("#finalidadSelect").rules("add", "required");
                            $("#tipoCatastroficoSelect").rules("add", "required");
                            $("#entidadRecobroSelect").rules("add", "required");
                        } else {
                            $("#fieldSetConceptoAC").prop("disabled", "disabled");
                            $("#unidadesAprobadasAC").rules("remove");
                            $("input[name=causalAnulacion]").rules("add", "required");
                            $("input[name=causalDevolucion]").rules("add", "required");
                            $("#causaExternaSelect").rules("remove");
                            $("#finalidadSelect").rules("remove");
                            $("#tipoCatastroficoSelect").rules("remove");
                            $("#entidadRecobroSelect").rules("remove");
                        }
                    }
                </script>
</c:if>