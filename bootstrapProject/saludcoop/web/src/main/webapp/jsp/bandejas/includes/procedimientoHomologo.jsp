<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<fmt:message key='label.codigoCups' var="CODIGO_CUPS" />
<fmt:message key='label.name' var="NOMBRE" />

<input type="hidden" id="idProcedimientoHom" name="idProcedimientoHom" value="${infoProcedimiento.procedimientoPOSHomologo.procedimiento.id}" />
<div class="form-group form-group-sm">
    <label for="codigoProcedimientoHom" class="control-label control-label-sm text-right col-lg-2">${CODIGO_CUPS}</label>
    <div class="col-lg-3">
        <input class="form-control input-sm digits-only" id="codigoProcedimientoHom" name="codigoProcedimientoHom"
            value="${infoProcedimiento.procedimientoPOSHomologo.procedimiento.codigo}" placeholder="${CODIGO_CUPS}" />
    </div>
    <c:if test="${param.editable}">
        <div class="col-lg-7 text-right">
            <button type="button" id="searchProHomologo" class="btn btn-info btn-sm">
                <fmt:message key="label.button.buscar" />
                <i class="icon-search"></i>
            </button>
            <button type="button" id="cleanProHomologo" class="btn btn-warning btn-sm">
                <fmt:message key="label.button.limpiar" />
                <i class="icon-eraser"></i>
            </button>
        </div>
    </c:if>
</div>
<div class="form-group form-group-sm">
    <label for="descripcionProcedimientoHom" class="control-label control-label-sm text-right col-lg-2">${NOMBRE}</label>
    <div class="col-lg-10">
        <input class="form-control input-sm" id="descripcionProcedimientoHom" name="descripcionProcedimientoHom"
            value="${infoProcedimiento.procedimientoPOSHomologo.procedimiento.descripcion}" placeholder="${NOMBRE}" />
    </div>
</div>
<!-- Modal búsqueda de Diagnósticos -->
<div class="modal fade" id="procedimientoHomModal" tabindex="-1" role="dialog" aria-labelledby="procHomLabel" aria-hidden="true"
    data-width="80%">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">
                <fmt:message key="label.procedimiento.nopos.posHomologo" />
            </h4>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <div class="col-lg-12 text-right">
                    <div class="form-group">
                        <div class="col-lg-12 text-right">
                            <table id="tablaProcedHomologo" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th rowspan="1" colspan="1"><input type="text" name="codProcedimientoHom" value=""
                                            class="search_init form-control input-sm" /></th>
                                        <th rowspan="1" colspan="1"><input type="text" name="descrProcedimientoHom" value=""
                                            class="search_init form-control input-sm" /></th>
                                    </tr>
                                    <tr>
                                        <th>${CODIGO_CUPS}</th>
                                        <th>${NOMBRE}</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                                <tfoot>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:if test="${param.editable}">
    <script>
    var tablaProcedHomologo;
        $(function() {
            if($.trim('${infoProcedimiento.procedimientoPOSHomologo.procedimiento.id}') != ''){
                blockFields();
            }
            
            $("#searchProHomologo").click(function() {
                $("#procedHomologo-messages").empty();
                if ($.trim($("#codigoProcedimientoHom").val()) != '' || $.trim($("#descripcionProcedimientoHom").val()) != '') {
                    verificarTabla();
                    tablaProcedHomologo.initProcHomologo();
                } else {
                    appendErrorMessage($("#procedHomologo-messages"),"Debe diligenciar al menos un campo de texto para ejecutar la búsqueda.");
                }
            });

            $("#cleanProHomologo").click(function() {
                verificarTabla();
                $("#codigoProcedimientoHom").reset();$("#codigoProcedimientoHom").attr("readonly", false);
                $("#descripcionProcedimientoHom").reset();$("#descripcionProcedimientoHom").attr("readonly", false);
                $("#searchProHomologo").prop("disabled",false);
            });
            function verificarTabla(){
                if($('#tablaProcedHomologo').prop("initialized")) {
                    $('#tablaProcedHomologo').dataTable().fnDestroy();
                    $("#tablaProcedHomologo input").val('');
                    $('#tablaProcedHomologo').prop("initialized", false);
                }
            }
            function blockFields(){
                $("#codigoProcedimientoHom").attr("readonly", true);
                $("#descripcionProcedimientoHom").attr("readonly", true);
                $("#searchProHomologo").prop("disabled",true);
            }
            tablaProcedHomologo = {
                initProcHomologo : function() {
                    var asInitVals = new Array();
                    $("form").submit(function(e) {
                        e.preventDefault();
                    });
                    var oTable = $('#tablaProcedHomologo').dataTable({
                        "sPaginationType" : "full_numbers",
                        "bDeferRender" : true, // Utilidad para optimar datasource al usar Ajax
                        "bInfo" : false, // Informacion Pagina Y de X
                        "bSort" : true, // Ordenar columnas
                        "bPaginate" : true, // Paginacion
                        "bLengthChange" : false, // Cantidad de Paginas
                        "bFilter" : true, // Boton Search
                        "bAutoWidth" : false, // Ancho Automatico de Columnas
                        "bProcessing" : true, // Cuando hay muchos de datos para cargar
                        "sAjaxSource" : '/web/bandejas/listarProcedimientosHomologos',
                        "oLanguage" : {
                            "oPaginate" : {
                                "sFirst" : "««",
                                "sPrevious" : "«",
                                "sNext" : "»",
                                "sLast" : "»»"
                            }
                        },
                        "aoColumns" : [{"mDataProp" : "codigo"},
                                       {"mDataProp" : "descripcion"}],
                        "fnServerParams" : function(aoData) {
                            aoData.push({name: "codigo", value: $("#codigoProcedimientoHom").val()});
                            aoData.push({name: "descripcion", value: $("#descripcionProcedimientoHom").val()});
                        },
                        "fnInitComplete": function(oSettings, json) {                   
                            $('#tablaProcedHomologo').prop("initialized", true);
                            if(oSettings.fnRecordsTotal() > 0){
                                $('#procedimientoHomModal').modal('show');
                            }else{
                                appendErrorMessage($("#procedHomologo-messages"), '<fmt:message key="message.noResults" />');
                                $("#cleanProHomologo").click();
                            }
                         },
                        "fnCreatedRow" : function(nRow, aData, iDataIndex) {
                             $(nRow).css("cursor", "pointer");
                             $(nRow).click(function() {
                                    $("#idProcedimientoHom").val(aData.id);
                                    $("#codigoProcedimientoHom").val(aData.codigo);
                                    $("#descripcionProcedimientoHom").val(aData.descripcion);
                                    blockFields();
                                    $('#procedimientoHomModal').modal('hide');
                             });
                        }
                    });
                    $("#tablaProcedHomologo_filter").hide();
                    $("#tablaProcedHomologo input").keyup(function() {
                        oTable.fnFilter(this.value, $("#tablaProcedHomologo input").index(this));
                    });
                    /*
                     * Support functions to provide a little bit of 'user friendlyness'
                     * to the textboxes in the footer
                     */
                    $("#tablaProcedHomologo input").each(function(i) {
                        asInitVals[i] = this.value;
                    });
                }
            };
        });
        
    </script>
</c:if>