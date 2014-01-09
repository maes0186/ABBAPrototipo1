<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fmt:message key='label.select' var="SELECCIONE_LABEL" />
<fmt:message key='label.SedeIps' var="VARIABLE_IPS" />
<fmt:message key='label.nopos.justificacion' var="VARIABLE_JUSTIFICACION" />
<fmt:message key='label.button.guardar' var="GUARDAR" />
<fmt:message key='label.button.volver' var="VOLVER" />
<fmt:message key='label.solicitud.informacionMedico' var="INFORMACION_MEDICO" />
<fmt:message key='label.numeroIdentificacion' var="NUMERO_DOCUMENTO_LABEL" />
<fmt:message key='label.medical.speciality' var="ESPECIALIDAD" />
<fmt:message key='label.button.agregar' var="AGREGAR" />
<fmt:message key='label.codigo' var="CODIGO" />
<fmt:message key='label.name' var="NOMBRE" />
<fmt:message key='label.listadoIpsProfesional' var="LISTADOIPS" />
<fmt:message key='label.listadoEspecialidadesProfesional' var="LISTADOESPECIALIDADES" />
<c:set var="webContext" value="${pageContext.request.contextPath}" />
<jsp:include page="./../../includes/components.jsp" />
<jsp:include page="./../../includes/header.jsp">
	<jsp:param name="includeMenu" value="true" />
</jsp:include>


<form id="formInfoProfesional">
    <div class="row">
        <div class=" col-lg-12">
            <div id="messages"></div>
            <div class="well form-horizontal">
                <fieldset>
                    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
                        <strong>${INFORMACION_MEDICO}</strong>
                    </blockquote>
                    <div class="form-group form-group-sm collapsible-content">
                        <div class="form-group form-group-sm ">
                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="registroMedicoSearch"><fmt:message
                                    key="label.medical.id" /></label>
                            <div class=" col-lg-3 col-sm-3 col-xs-6">
                                <input class="form-control input-sm" id="registroMedicoSearch" name="registroMedico"
                                    value="${profesional.registroMedico}" placeholder="<fmt:message key="label.medical.id" />">
                            </div>
                        </div>
                        <div id="identificacionComponent2" class="form-group form-group-sm ">
                            <label class=" col-lg-2 control-label control-label-sm" for="">Tipo Documento</label>
                            <div class="col-lg-3">
                                <select id="tipoDocumento" name="tipoDocumento" class="form-control input-sm input-sm">
                                    <option value="" data-min-length="6" data-max-length="15" data-alpha="false">${SELECCIONE_LABEL}</option>
                                    <c:forEach items="${tipoIdentificacion}" var="tipoIdent">
                                        <c:if test="${tipoIdent.aplicaProfesional}">
                                            <option value="${tipoIdent.id}" data-min-length="${tipoIdent.minLength}"
                                                data-max-length="${tipoIdent.maxLength}" data-alpha="${tipoIdent.esAlfanumerico}">${tipoIdent.descripcion}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="col-lg-2 control-label control-label-sm" for="">${NUMERO_DOCUMENTO_LABEL }</label>
                            <div class="col-lg-3">
                                <input type="text" class="form-control input-sm input-sm" id="numeroDocumento" name="numeroDocumento"
                                    placeholder="${NUMERO_DOCUMENTO_LABEL}" value="${profesional.numeroDocumento}">
                            </div>
                        </div>
                        <div class="form-group form-group-sm ">
                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="primerNombreSearch"><fmt:message
                                    key="label.name.first" /></label>
                            <div class=" col-lg-3 col-sm-3 col-xs-6">
                                <input class="form-control input-sm" id="primerNombreSearch" name="primerNombre"
                                    value="${profesional.primerNombre}"
                                    placeholder="<fmt:message
                                    key="label.name.first" />">
                            </div>
                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="segundoNombreSearch"><fmt:message
                                    key="label.name.first2" /></label>
                            <div class=" col-lg-3 col-sm-3 col-xs-6">
                                <input class="form-control input-sm" id="segundoNombreSearch" name="segundoNombre"
                                    value="${profesional.segundoNombre}"
                                    placeholder="<fmt:message
                                    key="label.name.first2" />">
                            </div>

                        </div>
                        <div class="form-group form-group-sm ">
                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="primerApellidoSearch"><fmt:message
                                    key="label.name.last3" /></label>
                            <div class=" col-lg-3 col-sm-3 col-xs-6">
                                <input class="form-control input-sm" id="primerApellidoSearch" name="primerApellido"
                                    value="${profesional.primerApellido}"
                                    placeholder="<fmt:message
                                    key="label.name.last3" />">
                            </div>

                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="segundoApellidoSearch"><fmt:message
                                    key="label.name.last2" /></label>
                            <div class=" col-lg-3 col-sm-3 col-xs-6">
                                <input class="form-control input-sm" id="segundoApellidoSearch" name="segundoApellido"
                                    value="${profesional.segundoApellido}"
                                    placeholder="<fmt:message
                                    key="label.name.last2" />">
                            </div>
                        </div>

                        <br />

                    </div>
                </fieldset>
            </div>
        </div>
    </div>
</form>
<c:if test="${esEdicion}">

    <div class="row">
        <div class="col-lg-12 title">
            <form id="formListaIps" class="well">
                <fieldset>
                    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
                        <strong>${LISTADOIPS}</strong>
                    </blockquote>
                    <div class="form-group collapsible-content">
                        <div class="col-lg-12 text-right">
                            <div class="form-group">
                                <div class="col-lg-12 text-right">
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
                </fieldset>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12 title">
            <form id="formListaEspecialidades" class="well">
                <fieldset>
                    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
                        <strong>${LISTADOESPECIALIDADES}</strong>
                    </blockquote>
                    <div class="form-group collapsible-content">
                        <div class="col-lg-12 text-right">
                            <div class="form-group">
                                <div class="col-lg-12 text-right">
                                    <table id="tablaEspecialidades" class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th rowspan="1" colspan="1"><input type="text" name="codigo" value=""
                                                    class="search_init form-control input-sm" /></th>
                                                <th rowspan="1" colspan="1"><input type="text" name="descripcion" value=""
                                                    class="search_init form-control input-sm" /></th>
                                            </tr>
                                            <tr>
                                                <th><fmt:message key="label.codigo" /></th>
                                                <th><fmt:message key="label.descripcion" /></th>
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
                </fieldset>
            </form>
        </div>
    </div>

    <div class="row">
		<div class="col-lg-12 title">
			<form id="formSedeIps"
				class="well clearfix abmComponent withSearch uniqueResult"
				javaObject="sedeIps">
				
				<fieldset>
					<blockquote class="col-lg-12 collapsible-header"
						style="padding: 2px; font-size: 16px;">
						<strong>Información Ips</strong>
					</blockquote>

					<div class="row form-group form-group-sm searchForm  collapsible-content">
						<input id="id" role="hidden-search" type="hidden" class="template-hidden" avoidDescriptionLabel="true"/>
						<input role="identificacion" tipoDatos="ips" nameLeft="tipoIdentificacion" nameRight="numeroIdentificacion" class="template-hidden col-lg-12" avoidDescriptionLabel="true"/>
						<input placeholder="Razón Social" name="razonSocial" id="razonSocial" row="2" class="col-lg-4 template-hidden">
                       	<input placeholder="Departamento" row="5" role="combo-search" reload="true" valueFrom="departamentos" 
							id="departamentoSedeIpsId" class="col-lg-3 template-hidden">
						<input placeholder="Municipio" row="5" role="combo-search" valueFrom="municipios"
							id="municipioSedeIpsId" class="col-lg-3 template-hidden"> 
                        <input placeholder="Dirección" id="direccion"  row="4" class="col-lg-4 template-hidden">
					</div>

					<div class="row collapsible-content">
						<div class="col-lg-12">
							<table class="table  table-hover">
								<thead>
									<tr class="row">
										<th class="data col-lg-2" tdclass="col-lg-6" name="razonSocial">Razón Social</th>
										<th class="data col-lg-2" tdclass="col-lg-2" name="municipioNombre">Municipio</th>
										<th class="data col-lg-2" tdclass="col-lg-4" name="direccion">Dirección</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>


    <div class="row">
        <div class="col-lg-12 title">
            <form id="formEspecialidades" class="well  clearfix abmComponent withSearch uniqueResult" javaObject="especialidades">

                <fieldset>
                    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
                        <strong>${ESPECIALIDAD}</strong>
                    </blockquote>

                    <div class="form-group searchForm  collapsible-content">
                        <input placeholder="${CODIGO}" id="codigo" class="template-hidden col-lg-3"> <input placeholder="${NOMBRE}"
                            id="descripcion" class="template-hidden col-lg-3">
                    </div>
                    <div class="row collapsible-content">
                        <div class="col-lg-12">
                            <br>
                            <table class="table  table-hover">
                                <thead>
                                    <tr class="row">
                                        <th class="data" name="id" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
                                        <th class="data col-lg-2" tdclass="col-lg-2" name="codigo">${CODIGO}</th>
                                        <th class="data col-lg-2" tdclass="col-lg-8" name="descripcion">${NOMBRE}</th>
                                        <th class="editable col-lg-3" role="buttonEliminar"></th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <br>

</c:if>
<div id="acciones">
    <div class=" col-lg-12 text-right">
        <button type="button" id="guardarCambiosProfesion" class="btn btn-success btn-sm">${GUARDAR}</button>
        <button type="button" id="cancelar" class="btn btn-danger btn-sm">${VOLVER}</button>
    </div>
</div>


<div id="ajax-modal" class="modal fade" tabindex="-1" style="" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
        <h3></h3>
    </div>
    <div class="modal-body"></div>
</div>
<c:if test="${esEdicion}">
<form:select id="departamentos" path="departamentos"  cssClass="form-control template-hidden" cssStyle="display:none">
    <form:option value="" label="${SELECCIONE_LABEL}"/>
    <form:options items="${departamentos}" itemLabel="descripcion" itemValue="id"/>  
</form:select>

<form:select id="municipios" path="municipios"  cssClass="form-control template-hidden" cssStyle="display:none">
    <form:option value="" label="${SELECCIONE_LABEL}"/>
</form:select>
</c:if>
<%@include file="./../../includes/footer.jsp"%>


<script>

$(document).ready(function() {
	tablaIps();
	tablaEspecialidad();
	$("#formEspecialidades").attr("dependentForm","formSedeIps");
	configABMComponents();
	$("select#departamentoSedeIpsId").change(function(){
		if(!$(this).is("[readOnly]")){
			reloadMunicipiosByDepartamento($("select#departamentoSedeIpsId"), $("select#municipioSedeIpsId"));
		}
	});
	selectDefaultValues();
});


function setFormTargetContainer(cont) {
	$container = cont;
}


$("#guardarCambiosProfesion").click(function(){
	//var formData = $('#formInfoProfesional').serializeArray();
	var datos = ($("#formInfoProfesional").serializeArray()).concat($("#formEspecialidades").serializeArray()).concat($("#formSedeIps").serializeArray());
	 url = "${webContext}/profesionalCrud/guardarProfesional";
	$.post(url, datos, function(data) {
        if (data.generalErrors.length > 0) {
            createMessages($("#messages"), data.generalErrors, 'danger', 'icon-warning-sign');
            $("#bandejaResult").hide();
        } else {
        	if(data.content=="NUEVO"){
            	window.history.back();
        	}else{
        		location.reload();
        	}
        }
    }, "json");  	
	
	
});


$("#cancelar").click(function(){
	window.history.back();
});

function selectDefaultValues() {
    try {
        selectDefault("tipoDocumento", '${profesional.tipoDocumento != null ? profesional.tipoDocumento : ""}');
    } catch(e) {
        log(e);
    }
}

function setSedeIps(data){
	$.ajax({
		url : "${webContext}/prestador/set_sedeIps",
		dataType : "json",
		type : "POST",
		data: data,
		success : function(resultado) {
			console.log(resultado);
			sedeIps = true;
			$("#formProfesional")[0].enable();
		},
		error : function(resultado) {
				console.log(resultado);
		}
			
		}
	);
}

function tablaEspecialidad(){
    var tablaEspecialidadesR = {
	        initEspe : function() {
	            var asInitVals = new Array();
	            $("form").submit(function(e) {
	                e.preventDefault();
	            });
	            var ipsTable = $('#tablaEspecialidades').dataTable({
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
	                "sAjaxSource" : "/web/profesionalCrud/listarEspecialidadesPorMedico",
	                "oLanguage" : {
	                    "oPaginate" : {"sFirst" : "««", "sPrevious" : "«", "sNext" : "»", "sLast" : "»»"}, 
	                    "sSearch"	: "Buscar"
	                },
	                "aoColumns" : [ {"mDataProp" : "codigo"}, 
	                                {"mDataProp" : "descripcion"}]
	            });
	            $("#tablaEspecialidades_filter").hide();
	            $("#tablaEspecialidades input").keyup(function() {
	                /* Filter on the column (the index) of this element */
	                log(this.value);
	                ipsTable.fnFilter(this.value, $("#tablaEspecialidades input").index(this));
	            });
	            /*
	             * Support functions to provide a little bit of 'user friendlyness'
	             * to the textboxes in the footer
	             */
	            $("#tablaEspecialidades input").each(function(i) {
	                asInitVals[i] = this.value;
	            });
	        }
	    };
    tablaEspecialidadesR.initEspe();
}

function tablaIps(){
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
	                "sAjaxSource" : "/web/profesionalCrud/listarSedeIpsPorMedico",
	                "oLanguage" : {
	                    "oPaginate" : {"sFirst" : "««", "sPrevious" : "«", "sNext" : "»", "sLast" : "»»"}, 
	                    "sSearch"	: "Buscar"
	                },
	                "aoColumns" : [ {"mDataProp" : "id"}, 
	                                {"mDataProp" : "nombre"}, 
	                                {"mDataProp" : "direccion"}, 
	                                {"mDataProp" : "telefono1"}, 
	                                {"mDataProp" : "ips.razonSocial"} ]
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


