<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<fmt:message key='label.code' var="CODIGO_CUM" />
<fmt:message key='label.name' var="NOMBRE" />

<input type="hidden" id="idMediHomologo" name="idMediHomologo" value="${infoMedicamento.medicamentoHomologo.medicamento.id}" />
<div class="form-group form-group-sm">
    <label for="codigoMediHomologo" class="control-label control-label-sm text-right col-lg-2">${CODIGO_CUM}</label>
    <div class="col-lg-3">
        <input class="form-control input-sm digits-only" id="codigoMediHomologo" name="codigoMediHomologo"
            value="${infoMedicamento.medicamentoHomologo.medicamento.codigo}" placeholder="${CODIGO_CUM}" />
    </div>
    <c:if test="${param.editable}">
        <div class="col-lg-7 text-right">
            <button type="button" id="searchMedHomologo" class="btn btn-info btn-sm">
                <fmt:message key="label.button.buscar" />
                <i class="icon-search"></i>
            </button>
            <button type="button" id="cleanMedHomologo" class="btn btn-warning btn-sm">
                <fmt:message key="label.button.limpiar" />
                <i class="icon-eraser"></i>
            </button>
        </div>
    </c:if>
</div>
<div class="form-group form-group-sm">
    <label for="descripcionMediHomologo" class="control-label control-label-sm text-right col-lg-2">${NOMBRE}</label>
    <div class="col-lg-10">
        <input class="form-control input-sm" id="descripcionMediHomologo" name="descripcionMediHomologo"
            value="${infoMedicamento.medicamentoHomologo.medicamento.descripcion}" placeholder="${NOMBRE}" />
    </div>
</div>
<!-- Modal búsqueda de Homologo -->
<div class="modal fade" id="mediHomologosModal" tabindex="-1" role="dialog" aria-labelledby="MediHomologosLabel" aria-hidden="true"
    data-width="80%">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">
                <fmt:message key="label.medicamento.nopos.posHomologo" />
            </h4>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <div class="col-lg-12 text-right">
                    <div class="form-group">
                        <div class="col-lg-12 text-right">
                            <table id="tablaMedicamentos" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th rowspan="1" colspan="1"><input type="text" name="codMediHomologo" value=""
                                            class="search_init form-control input-sm" /></th>
                                        <th rowspan="1" colspan="1"><input type="text" name="desctMediHomologo" value=""
                                            class="search_init form-control input-sm" /></th>
                                    </tr>
                                    <tr>
                                        <th>${CODIGO_CUM}</th>
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
    var tablaMediHomologos;
        $(function() {
            if($.trim('${infoMedicamento.medicamentoHomologo.medicamento.id}') != ''){
                blockFields();
            }
            
            $("#searchMedHomologo").click(function() {
                $("#medicaHomologo-messages").empty();
                if ($.trim($("#codigoMediHomologo").val()) != '' || $.trim($("#descripcionMediHomologo").val()) != '') {
                    verificarTabla();
                    tablaMediHomologos.initMediHomologos();
                } else {
                    appendErrorMessage($("#medicaHomologo-messages"),"Debe diligenciar al menos un campo de texto para ejecutar la búsqueda.");
                }
            });

            $("#cleanMedHomologo").click(function() {
                verificarTabla();
                $("#codigoMediHomologo").reset();$("#codigoMediHomologo").attr("readonly", false);
                $("#descripcionMediHomologo").reset();$("#descripcionMediHomologo").attr("readonly", false);
                $("#searchMedHomologo").prop("disabled",false);
            });
            function verificarTabla(){
                if($('#tablaMedicamentos').prop("initialized")) {
                    $('#tablaMedicamentos').dataTable().fnDestroy();
                    $("#tablaMedicamentos input").val('');
                    $('#tablaMedicamentos').prop("initialized", false);
                }
            }
            function blockFields(){
                $("#codigoMediHomologo").attr("readonly", true);
                $("#descripcionMediHomologo").attr("readonly", true);
                $("#searchMedHomologo").prop("disabled",true);
            }
            tablaMediHomologos = {
                initMediHomologos : function() {
                    var asInitVals = new Array();
                    $("form").submit(function(e) {
                        e.preventDefault();
                    });
                    var diagTable = $('#tablaMedicamentos').dataTable({
                        "sPaginationType" : "full_numbers",
                        "bDeferRender" : true, // Utilidad para optimar datasource al usar Ajax
                        "bInfo" : false, // Informacion Pagina Y de X
                        "bSort" : true, // Ordenar columnas
                        "bPaginate" : true, // Paginacion
                        "bLengthChange" : false, // Cantidad de Paginas
                        "bFilter" : true, // Boton Search
                        "bAutoWidth" : false, // Ancho Automatico de Columnas
                        "bProcessing" : true, // Cuando hay muchos de datos para cargar
                        "sAjaxSource" : '/web/bandejas/listarMedicamentosHomologos',
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
                            aoData.push({name: "codigo", value: $("#codigoMediHomologo").val()});
                            aoData.push({name: "descripcion", value: $("#descripcionMediHomologo").val()});
                        },
                        "fnInitComplete": function(oSettings, json) {                   
                            $('#tablaMedicamentos').prop("initialized", true);
                            if(oSettings.fnRecordsTotal() > 0){
                                $('#mediHomologosModal').modal('show');
                            }else{
                                appendErrorMessage($("#medicaHomologo-messages"), '<fmt:message key="message.noResults" />');
                                $("#cleanMedHomologo").click();
                            }
                         },
                        "fnCreatedRow" : function(nRow, aData, iDataIndex) {
                             $(nRow).css("cursor", "pointer");
                             $(nRow).click(function() {
                                    $("#idMediHomologo").val(aData.id);
                                    $("#codigoMediHomologo").val(aData.codigo);
                                    $("#descripcionMediHomologo").val(aData.descripcion);
                                    blockFields();
                                    $('#mediHomologosModal').modal('hide');
                             });
                        }
                    });
                    $("#tablaMedicamentos_filter").hide();
                    $("#tablaMedicamentos input").keyup(function() {
                        diagTable.fnFilter(this.value, $("#tablaMedicamentos input").index(this));
                    });
                    /*
                     * Support functions to provide a little bit of 'user friendlyness'
                     * to the textboxes in the footer
                     */
                    $("#tablaMedicamentos input").each(function(i) {
                        asInitVals[i] = this.value;
                    });
                }
            };
        });
        
    </script>
</c:if>