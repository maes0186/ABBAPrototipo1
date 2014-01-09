<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<c:set var="titulo" value="${ipsTitulo}" />

<fmt:message key='label.nombre' var="NOMBRE_LABEL" />
<fmt:message key='label.direccionamientoIps' var="DIR_IPS_LABEL" />
<fmt:message key='label.direccion' var="DIRECCION_LABEL" />
<fmt:message key='label.telefono' var="TELEFONO_LABEL" />
<script>
	var ejecutarBusqueda = true;
	var idIpsOriginal, idIpsNueva;
    $(function (){
    	idIpsOriginal = '${direccionamiento.id}';
    	$("#idSedeIps").change(function() {
    		idIpsNueva = $("#idSedeIps").val();
    	});
    });
</script>
<fieldset class="well">
    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
        <c:choose>
            <c:when test="${not empty titulo}">            
                    <strong>${titulo}</strong>
            </c:when>
            <c:otherwise>
                    <strong>${DIR_IPS_LABEL}</strong>
            </c:otherwise>
        </c:choose>   
    </blockquote> 
    <div class="collapsible-content">
        <input type="hidden" id="idSedeIps" name="idSedeIps" value="${direccionamiento.id}"  />
        <div class="form-group form-group-sm" style="padding-left: 20px;">
            <label for="nombreIps" class="control-label control-label-sm text-right col-lg-1">${NOMBRE_LABEL}</label>
            <div class="col-lg-5">
                <input class="form-control input-sm" id="nombreIps" name="nombreIps" placeholder="${NOMBRE_LABEL}" readonly="readonly"
                    value="${direccionamiento.razonSocial}" />
            </div>
            <c:if test="${param.editable}">
                <div class="col-lg-1">
                    <button type="button" class="btn btn-link btn-sm" data-toggle="modal"  onclick="busquedaIps()">
                        <i class="icon-fixed-width icon-search"></i>
                    </button>
                </div>
            </c:if>
        </div>
        <div class="form-group form-group-sm" style="padding-left: 20px;">
            <label for="direccionIps" class="control-label control-label-sm text-right col-lg-1">${DIRECCION_LABEL}</label>
            <div class="col-lg-5">
                <input class="form-control input-sm" id="direccionIps" name="direccionIps" placeholder="${DIRECCION_LABEL}"
                    readonly="readonly" value="${direccionamiento.direccion}" />
            </div>
        </div>
        <div class="form-group form-group-sm" style="padding-left: 20px;">
            <label for="telefonoIps" class="control-label control-label-sm text-right col-lg-1">${TELEFONO_LABEL}</label>
            <div class="col-lg-5">
                <input class="form-control input-sm" id="telefonoIps" name="telefonoIps" placeholder="${TELEFONO_LABEL}" readonly="readonly"
                    value="${direccionamiento.telefono1}" />
            </div>
        </div>
        <br />
    </div>
</fieldset>

<!-- Modal búsqueda de IPS -->
<div class="modal fade" id="dirIps" tabindex="-1" role="dialog" aria-labelledby="dirIpsLabel" aria-hidden="true" data-width="80%">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">${DIR_IPS_LABEL}</h4>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <div class="col-lg-12 text-right">
                    <div class="form-group">
                        <div class="col-lg-12 text-right"">
                            <table id="tablaIps" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th rowspan="1" colspan="1"><input type="text" name="numeroIdentificacion" value=""
                                            class="search_init form-control input-sm" /></th>
                                        <th rowspan="1" colspan="1"><input type="text" name="razonSocial" value=""
                                            class="search_init form-control input-sm" /></th>
                                        <th rowspan="1" colspan="1"><input type="text" name="direccion" value=""
                                            class="search_init form-control input-sm" /></th>
                                        <th rowspan="1" colspan="1"><input type="text" name="telefono" value=""
                                            class="search_init form-control input-sm" /></th>
                                        <th rowspan="1" colspan="1"><input type="text" name="nombreIps" value=""
                                            class="search_init form-control input-sm" /></th>
                                    </tr>
                                    <tr>
                                        <th><fmt:message key="label.numeroIdentificacion" /></th>
                                        <th><fmt:message key="label.razonSocial" /></th>
                                        <th><fmt:message key="label.direccion" /></th>
                                        <th><fmt:message key="label.telefono" /></th>
                                        <th><fmt:message key="label.IPS" /></th>
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
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">
                <fmt:message key="label.button.cerrar" />
            </button>
        </div>
    </div>
</div>
<script>
function busquedaIps(){
    var tablaIpsR = {
	        initIps : function() {
	            var asInitVals = new Array();
	            $("form").submit(function(e) {
	                e.preventDefault();
	            });
	            var ipsTable = $('#tablaIps').dataTable({
	            	"sDefaultContent": "",
	                "sPaginationType" : "full_numbers",
	                "bDeferRender" : true, // Utilidad para optimar datasource al usar Ajax
	                "bInfo" : false, // Informacion Pagina Y de X
	                "bDestroy": true,
	                "bSort" : true, // Ordenar columnas
	                "bPaginate" : true, // Paginacion
	                "bLengthChange" : false, // Cantidad de Paginas
	                "bFilter" : true, // Boton Search
	                "bAutoWidth" : false, // Ancho Automatico de Columnas
	                "bProcessing" : true, // Cuando hay muchos de datos para cargar
	                "bServerSide" : false,
	                "sAjaxSource" : "/web/bandejas/listarSedeIpsGrupo/"+item,
	                "oLanguage" : {
	                    "oPaginate" : {"sFirst" : "««", "sPrevious" : "«", "sNext" : "»", "sLast" : "»»"}, 
	                    "sSearch"	: "Buscar"
	                },
	                "aoColumns" : [ {"mDataProp" : "id"}, 
	                                {"mDataProp" : "nombre"}, 
	                                {"mDataProp" : "direccion"}, 
	                                {"mDataProp" : "telefono1"}, 
	                                {"mDataProp" : "nombreIps"} ],
	                "fnInitComplete": function(oSettings, json) {
	                	$("#dirIps").modal('show');
		             },
	                "fnCreatedRow" : function(nRow, aData, iDataIndex) {
	                    $(nRow).css("cursor", "pointer");
	                    $(nRow).click(function() {
	                        $("#idSedeIps").val(aData.id).change();
	                        $("#nombreIps").val(aData.nombre);
	                        $("#direccionIps").val(aData.direccion);
	                        $("#telefonoIps").val(aData.telefono1);
	                        $('#dirIps').modal('hide');
	                    });
	                }
	            });
	            $("#tablaIps_filter").hide();
	            $("#tablaIps input").keyup(function() {
	                /* Filter on the column (the index) of this element */
	                log(this.value);
	                ipsTable.fnFilter(this.value, $("#tablaIps input").index(this));
	            });
	            $("#tablaIps input").each(function(i) {
	                asInitVals[i] = this.value;
	            });
	            $("#tablaIps").css("width", "100%");
	        }
	    };
	    tablaIpsR.initIps();
}
</script>
