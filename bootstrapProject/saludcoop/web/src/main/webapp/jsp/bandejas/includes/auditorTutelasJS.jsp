<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="itemMedicamento" value="<%=SystemConstants.ITEM_MEDICAMENTO_STRING%>" />

<fmt:message key='label.justificacionConcepto' var="JUSTIFICACION_CONCEPTO_LABEL" />
<fmt:message key='label.justificacionConexidad' var="JUSTIFICACION_CONEXIDAD_LABEL" />
<fmt:message key='label.causaExterna' var="CAUSA_EXTERNA_LABEL" />
<fmt:message key='label.finalidad' var="FINALIDAD_LABEL" />
<fmt:message key='label.tipoCatastrofico' var="TIPO_CATASTROFICO_LABEL" />
<fmt:message key='label.entidadRecobro' var="ENTIDAD_RECOBRO_LABEL" />
<fmt:message key='label.concepto' var="CONCEPTO_LABEL" />
<fmt:message key='label.infoSolicitud.causalAnulacion' var="CAUSAL_ANU_LABEL" />
<fmt:message key='label.infoSolicitud.causalDevolucion' var="CAUSAL_DEV_LABEL" />
<fmt:message key="label.tutela.numeroSAT" var="NUMERO_TUTELA" />
<fmt:message key="label.tutela.falloSAT" var="NUMERO_FALLO" />
<fmt:message key='label.rangoinvalido' var="RANGO_INVALIDO" />
<fmt:message key='label.periodo.aprobadoDias' var="PERIODO_LABEL" />
<fmt:message key='label.dias.periodo' var="DIAS_PER_LABEL" />
<fmt:message key='label.unidades.aprobadasPeriodo' var="UNIDADES_LABEL" />
<fmt:message key='label.numeroEntregas' var="NUM_ENTREGAS_LABEL" />

<c:set var="webContext" value="${pageContext.request.contextPath}" />

<script>
    $(function() {
        $("#justificacionConcepto").val($.trim($("#justificacionConcepto").val()));

        $("#btnAceptar").click(function(event) {
            event.preventDefault();
            
            if(eval('${!esLDF && bandejaAC.tipoItem == itemMedicamento}')) {
            	validarCantidadesMedicamento();                
            }
            validacionForm();
        });

        // Se establecen los parámetros de validación del formulario
        $('#formInfoSolicitudTutelas')
                .validate(
                        {
                            onfocusout : false,
                            focusInvalid : false,
                            focusCleanup : false,
                            onkeyup : false,
                            onclick : false,
                            rules : {
                                justificacionConcepto : "required",
                                justificacionConexidad : "required",
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
                                justificacionConcepto : '<fmt:message key="validation.required"><fmt:param value="${JUSTIFICACION_CONCEPTO_LABEL}"/></fmt:message>',
                                justificacionConexidad : '<fmt:message key="validation.required"><fmt:param value="${JUSTIFICACION_CONEXIDAD_LABEL}"/></fmt:message>',
                                causaExterna : '<fmt:message key="validation.required"><fmt:param value="${CAUSA_EXTERNA_LABEL}"/></fmt:message>',
                                finalidad : '<fmt:message key="validation.required"><fmt:param value="${FINALIDAD_LABEL}"/></fmt:message>',
                                tipoCatastrofico : '<fmt:message key="validation.required"><fmt:param value="${TIPO_CATASTROFICO_LABEL}"/></fmt:message>',
                                entidadRecobro : '<fmt:message key="validation.required"><fmt:param value="${ENTIDAD_RECOBRO_LABEL}"/></fmt:message>',
                                concepto : '<fmt:message key="validation.required"><fmt:param value="${CONCEPTO_LABEL}"/></fmt:message>',
                                causalAnulacion : '<fmt:message key="validation.required"><fmt:param value="${CAUSAL_ANU_LABEL}"/></fmt:message>',
                                causalDevolucion : '<fmt:message key="validation.required"><fmt:param value="${CAUSAL_DEV_LABEL}"/></fmt:message>',
                                numeroTutela : '<fmt:message key="validation.required"><fmt:param value="${NUMERO_TUTELA}"/></fmt:message>',
                                numeroFallo : '<fmt:message key="validation.required"><fmt:param value="${NUMERO_FALLO}"/></fmt:message>'
                            },
                        });

        $("#btnCancelar").click(function() {
            location.href = "${webContext}/bandejas/${nombreBandeja}";
        });

        $("#procesarFechas").click(function() {
            procesaryvalidarfechas();
        });
        
        addRules();
    });

    function validacionForm() {
        if ($("#formInfoSolicitudTutelas").valid()) {
            var formData = $('#formInfoSolicitudTutelas').serializeArray();
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
            newAlert('danger', [ '<fmt:message key="validation.errors" />' ], 2000, 'icon-warning-sign');
        }
    }

    function addRules() {
        var validator = $("#formInfoSolicitudTutelas").validate();
        validator.resetForm();

        if (eval('${!esLDF}')) {
            $("#justificacionConexidad").val($.trim($("#justificacionConexidad").val()));
            
            $("input[name=causalAnulacion]").rules("add", "required");
            $("input[name=causalDevolucion]").rules("add", "required");

            if ($("input[name=concepto]:checked").val() == 1) {
                
                $("#fieldSetConceptoTutelas").removeAttr("disabled");
                $("#unidadesAprobadasTutelas").rules("add", {
                    required : true,
                    digits : true
                });
                
                if(eval('${bandejaAC.tipoItem == itemMedicamento}')) {
                    $("#periodoAprobadoTutelas").rules("add", {
                        required : true,
                        digits : true
                    });
                    $("#diasPeriodoTutelas").rules("add", {
                        required : true,
                        digits : true
                    });
                    $("#numeroEntregasTutelas").rules("add", {
                        required : true,
                        digits : true
                    });
                }
                $("#causaExternaSelect").rules("add", "required");
                $("#finalidadSelect").rules("add", "required");
                $("#tipoCatastroficoSelect").rules("add", "required");
                $("#entidadRecobroSelect").rules("add", "required");
                $("#numeroTutela").rules("add", "required");
                $("#numeroFallo").rules("add", "required");
                $("#justificacionConexidad").rules("add", "required");
            } else {
                $("#fieldSetConceptoTutelas").prop("disabled", "disabled");
                $("#causaExternaSelect").rules("remove");
                $("#finalidadSelect").rules("remove");
                $("#tipoCatastroficoSelect").rules("remove");
                $("#entidadRecobroSelect").rules("remove");
                $("#numeroTutela").rules("remove");
                $("#numeroFallo").rules("remove");
                $("#justificacionConexidad").rules("remove");
                $("#unidadesAprobadasTutelas").rules("remove");
                
                if(eval('${bandejaAC.tipoItem == itemMedicamento}')) {
                    $("#periodoAprobadoTutelas").rules("remove");
                    $("#diasPeriodoTutelas").rules("remove");
                    $("#numeroEntregasTutelas").rules("remove");
                }
            }
        }
    }
    
    function calcularPeriodoAprobado() {
        var val1 = $("#numeroEntregasTutelas").val();
        var val2 = $("#diasPeriodoTutelas").val();
        $("#periodoAprobadoTutelas").val(val1 * val2);
    }
    
    function validarCantidadesMedicamento() {
        if($("input[name=concepto]:checked").val() == 1) {
            $.ajax({
                type : 'POST',
                async : false,
                url : "${webContext}/bandejas/cumpleRangoMedicamento",
                data : $("#formInfoSolicitudTutelas").serializeArray(),
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
        
        val = $("#numeroEntregasTutelas").val();
        val2 = $("#diasPeriodoTutelas").val();
        
        $("#periodoAprobadoTutelas").rules('add', {
            equalTo : (val * val2).toString()
        });

        $("#unidadesAprobadasTutelas").rules('add', {
            notEqual : true
        });
    }

    function procesaryvalidarfechas() {
        validarCantidadesMedicamento();
        var valid = true;
        $("#fieldSetConceptoTutelas input").each(function(i, e) {
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
                    var formData = $('#formInfoSolicitudTutelas').serializeArray();
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
    
</script>