
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<fmt:message key='label.codigoCups' var="CODIGO_CUPS" />
<fmt:message key='label.code' var="CODIGO_CUM" />
<fmt:message key='label.codigoCIE10' var="CODIGO_CIE10" />
<fmt:message key='label.name' var="NOMBRE" />
<fmt:message key='label.tutela' var="TUTELA" />
<fmt:message key='label.id.number' var="NUMERO_DOCUMENTO_LABEL" />
<fmt:message key='label.medicamentosComerciales' var="MED_COMERCIALES" />
<fmt:message key='message.info.medicamentoComercial' var="INFO_MEDICAMENTO_COMERCIAL"/>
<jsp:include page="./../../includes/header.jsp">
	<jsp:param name="includeMenu" value="true" />
</jsp:include>
<jsp:include page="./../../includes/components.jsp" />	

<fmt:message key='label.select' var="SELECCIONE_LABEL" />

<c:set var="webContext" value="${pageContext.request.contextPath}" />

<script>


	
	var esLDF = false;
	<c:if test="${esLDF}">
		esLDF=true;
	</c:if>
	
	var sedeIps = false;
	<c:if test="${not empty sedeIps}">
		sedeIps=true;
	</c:if>
	
	var profesional = false;
	<c:if test="${not empty profesionalEspecialidad}">
		profesional=true;
	</c:if>
	var esParcial = false;
	<c:if test="${not empty esParcial}">
		esParcial=true;
	</c:if>


</script>
<div>
	<div class="form-horizontal" id="solicitudForm">
		<div class="col-lg-12" id="messages"></div>
		
<button type="button" class="btn btn-link btn-sm collapse-button" onclick="collapseContent(this)" title="<fmt:message key='label.colapsarTodos' />">
</button>		
<jsp:include page="./../includes/datosAfiliado.jsp">
	<jsp:param name="editable" value="false" />
</jsp:include>

<c:if test="${not empty nroSolicitud}">
	<div class="row" id="nroSolicitudForm">
		<div class="col-lg-12 title">
			<div id="formObservaciones"
				class="well clearfix">

				<fieldset>
					<blockquote class="col-lg-12 collapsible-header"
						style="padding: 2px; font-size: 16px;">
						<strong>Solicitud</strong>
					</blockquote>
					<div class="row collapsible-content"  style="margin-bottom: 21px">
						 <label class="control-label control-label-sm text-right col-lg-2" for="nroSolicitud">
						 	Número de Solicitud:
						 </label>
						<div class="col-lg-2">
							<input id="nroSolicitud" name="nroSolicitud" class="form-control input-sm" readonly value="${nroSolicitud}"/>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
</c:if>

		
		<c:if test="${empty sedeIps}">
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
												<th class="data col-lg-2" tdclass="col-lg-6" name="razonSocial">Razón
													Social</th>
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
		</c:if>

		<c:if test="${empty profesionalEspecialidad}">
			<div class="row">
				<div class="col-lg-12 title">
					<form id="formProfesional"
						class="well  clearfix abmComponent withSearch uniqueResult"
						javaObject="profesional">

						<fieldset>
							<blockquote class="col-lg-12 collapsible-header"
								style="padding: 2px; font-size: 16px;">
								<strong>Profesional</strong>
							</blockquote>
							<div class="row form-group form-group-sm searchForm  collapsible-content">
								<input placeholder="Registro Médico" id="registroMedico" class="col-lg-3"> 
								<input role="identificacion" tipoDatos="profesional" nameLeft="tipoDocumento" nameRight="numeroDocumento" class="template-hidden col-lg-12" avoidDescriptionLabel="true" row=2/> 
								<input	placeholder="Primer Nombre" id="primerNombre" class="col-lg-3 template-hidden" row="3"> 
								<input	placeholder="Segundo Nombre" id="segundoNombre" class="col-lg-3 template-hidden" row="3"> 
								<input	placeholder="Primer Apellido" id="primerApellido" class="col-lg-3 template-hidden" row="4"> 
								<input	placeholder="Segundo Apellido" id="segundoApellido"	class="col-lg-3 template-hidden" row="4">
								<input placeholder="Especialidad" row="5" role="combo-search" valueFrom="especialidades"
									id="especialidad" class="col-lg-3 template-hidden"> 
									<input id="id" role="hidden-search" type="hidden" class="template-hidden" avoidDescriptionLabel="true"/>
							</div>

							<div class="row collapsible-content">
								<div class="col-lg-12">
									<table class="table  table-hover">
										<thead>
											<tr class="row">
												<th class="data col-lg-1" name="tipoDocumentoDescripcion">Tipo
													Ident.</th>
												<th class="data col-lg-3" name="numeroDocumento">Número
													Ident.</th>
												<th class="data col-lg-3" name="registroMedico">Registro
													Médico</th>
												<th class="data col-lg-3" name="nombreCompleto">Nombre
													y Apellido</th>
												<th class="data col-lg-3" name="especialidadDescripcion">Especialidad</th>

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
		</c:if>

		<div class="row">
			<div class="col-lg-12 title">
				<form id="formDiagnosticos"
					class="well  clearfix abmComponent withSearch pageable"
					javaObject="diagnosticos">

					<fieldset>
						<blockquote class="col-lg-12 collapsible-header"
							style="padding: 2px; font-size: 16px;">
							<strong>Diagnóstico</strong>
						</blockquote>
						
						<div class="form-group searchForm  collapsible-content">
								 <input	placeholder="${CODIGO_CIE10}" id="codigo" class="template-hidden col-lg-3">
								 <input placeholder="${NOMBRE}"  id="descripcion" class="template-hidden col-lg-3">
						</div>
						
						<div class="row collapsible-content">
							<div class="col-lg-12">
								<table class="table  table-hover">
									<thead>
										<tr class="row">
											<th class="data" name="id" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
											<th class="data col-lg-1"  tdclass="col-lg-2" name="codigo">${CODIGO_CIE10}</th>
											<th class="data col-lg-10" tdclass="col-lg-10" name="descripcion">${NOMBRE}</th>
											<th class="editable col-lg-1" role="buttonEliminar"></th>
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
				<form id="formProcedimientos"
					class="well  clearfix abmComponent withSearch"
					javaObject="procedimientos">

					<fieldset>
						<blockquote class="col-lg-12 collapsible-header"
							style="padding: 2px; font-size: 16px;">
							<strong>Procedimientos</strong>
						</blockquote>

						<div class="form-group searchForm  collapsible-content">
								 <input	placeholder="${CODIGO_CUPS}" id="codigo" class="template-hidden col-lg-3">
								 <input placeholder="${NOMBRE}"  id="descripcion" class="template-hidden col-lg-3">
						</div>
						<div class="row collapsible-content">
							<div class="col-lg-12">
								<table class="table  table-hover">
									<thead>
										<tr class="row">
											<th class="data" name="id" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
											<th class="data col-lg-1" tdclass="col-lg-1" name="tipoPPM">Tipo</th>
											<th class="data col-lg-1" tdclass="col-lg-1" name="codigo">${CODIGO_CUPS}</th>
											<th class="data col-lg-2" tdclass="col-lg-9" name="descripcion">${NOMBRE}</th>
											<th class="editable col-lg-1" name="cant" role="input" value="1">Cant</th>
											<th class="editable col-lg-2" name="especialidad"
												role="combo-row-data" valueFromData="especialidades">Especialidades</th>
                                          	<th class="editable <c:if test="${!aplicaTutela}">col-lg-3</c:if> col-lg-2" name="dxAsociado"
                                                role="combo-sync" valueFrom="diagnosticos">DX Asociado</th>
                                            <c:if test="${aplicaTutela}">
                                                <th class="editable col-lg-1" name="aplicaTutela" role="checkbox">${TUTELA}</th>
                                            </c:if>
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
		<div class="row">
			<div class="col-lg-12 title">
				<form id="formMedicamentos"
					class="well  clearfix abmComponent withSearch"
					javaObject="medicamentos">

					<fieldset>
						<blockquote class="col-lg-12 collapsible-header"
							style="padding: 2px; font-size: 16px;">
							<strong>Medicamentos</strong>
						</blockquote>
						<div class="form-group searchForm  collapsible-content">
                            <c:if test="${esLDF }">
								 <input	placeholder="${CODIGO_CUM}" id="codigo" class="template-hidden col-lg-3" labelclass="col-lg-2">
								 <input placeholder="${NOMBRE}"  id="descripcion" class="template-hidden col-lg-3" labelclass="col-lg-2"> 
                                 <input placeholder="${MED_COMERCIALES}" id="comercial" type="checkbox-search" name="comercial" class="template-hidden col-lg-1 text-right" labelclass="col-lg-1">
                            </c:if>
                            <c:if test="${!esLDF }">
                                 <input placeholder="${CODIGO_CUM}" id="codigo" class="template-hidden col-lg-3">
                                 <input placeholder="${NOMBRE}"  id="descripcion" class="template-hidden col-lg-3">                                 
                            </c:if>    
						</div>
						<div class="row collapsible-content">
							<div class="col-lg-12">
								<table class="table  table-hover">
									<thead>
										<tr class="row">
											<th class="data" name="id" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
											<th class="data col-lg-1" tdclass="col-lg-1" name="tipoPPM">Tipo</th>
											<th class="data col-lg-1" tdclass="col-lg-1" name="codigo">${CODIGO_CUM}</th>
											<th class="data col-lg-4" tdclass="col-lg-9" name="descripcion">${NOMBRE}</th>
											<th class="editable col-lg-1" name="cant" role="input" value="0" digitsonly="true" readonly="readonly">Cant</th>
											<th class="editable <c:if test="${!aplicaTutela}">col-lg-3</c:if> col-lg-2" name="dxAsociado"
												role="combo-sync" valueFrom="diagnosticos">DX Asociado</th>
                                            <c:if test="${aplicaTutela}">
                                                <th class="editable col-lg-1" name="aplicaTutela" role="checkbox">${TUTELA}</th>
                                            </c:if>
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
		
		<div class="row">
			<div class="col-lg-12 title">
				<form id="formInsumos"
					class="well  clearfix abmComponent withSearch"
					javaObject="insumos">

					<fieldset>
						<blockquote class="col-lg-12 collapsible-header"
							style="padding: 2px; font-size: 16px;">
							<strong>Insumos</strong>
						</blockquote>
						<div class="form-group searchForm  collapsible-content">
                            	 <input	placeholder="${CODIGO_CUM}" id="codigo" class="template-hidden col-lg-3" labelclass="col-lg-2">
								 <input placeholder="${NOMBRE}"  id="descripcion" class="template-hidden col-lg-3" labelclass="col-lg-2"> 
						</div>
						<div class="row collapsible-content">
							<div class="col-lg-12">
								<table class="table  table-hover">
									<thead>
										<tr class="row">
                                                                        
											<th class="data" name="id" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
											<th class="data col-lg-1" tdclass="col-lg-1" name="tipoPPM">Tipo</th>
											<th class="data col-lg-1" tdclass="col-lg-1" name="codigo">${CODIGO_CUM}</th>
											<th class="data col-lg-4" tdclass="col-lg-9" name="descripcion">${NOMBRE}</th>
											<th class="editable col-lg-1" name="cant" role="input" value="0" digitsonly="true" readonly="readonly">Cant</th>
                                            <th class="editable 
                                                <c:if test="${!aplicaTutela}">col-lg-3</c:if> col-lg-2" name="dxAsociado" role="combo-sync" valueFrom="diagnosticos">DX Asociado
                                            </th>
                                            <c:if test="${aplicaTutela}">
                                                <th class="editable col-lg-1" name="aplicaTutela" role="checkbox">${TUTELA}</th>
                                            </c:if>
                                            <th class="editable col-lg-3" role="buttonEliminar"></th>
                                            <th class="editable" name="superaTopes" thclass="template-hidden" tdclass="template-hidden" role="hidden"></th>
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
				<div id="formObservaciones"
					class="well clearfix">

					<fieldset>
						<blockquote class="col-lg-12 collapsible-header"
							style="padding: 2px; font-size: 16px;">
							<strong>Observaciones</strong>
						</blockquote>
						<div class="row collapsible-content"  style="margin-bottom: 21px">
							<div class="col-lg-12">
							<textarea id="observaciones" name="observaciones"class="form-control" rows="3"></textarea>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 title">
				<fieldset>
    				<div class="well  clearfix">    					
						<blockquote class="col-lg-12 collapsible-header"
							style="padding: 2px; font-size: 16px;">
							<strong>Documentos de Soporte</strong>
						</blockquote>
						<div class="row collapsible-content">
							<div id="fileMessages" class="col-lg-12"></div>
							<div class="col-lg-12">
								<div class="row collapsible-content">
									<div class="col-lg-6">
										<div class="panel panel-dropzone" id="hClinica">
											<div class="panel-heading cursor-pointer">RESUMEN HISTORIA CLÍNICA -
												Haga click para adjuntar un resumen</div>
											<div id="resumenFile" class="panel-body dropzone">
                                                <c:if test="${!esLDF}">
	                                                <div class="col-lg-4" style="z-index: 900; margin-right: 20px;">
	                                                    <button data-target="#resumenManual" id="rm"
	                                                        onclick='setFormTargetContainer($(this));eval("fillResumenManual($(this))");'
	                                                        class="btn btn-warning-danger cursor-pointer" data-toggle="modal">
	                                                         <fmt:message key="label.button.crearResumen"/>
	                                                    </button>
	                                                </div>
                                                </c:if>
                                            </div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="panel panel-dropzone">
											<div class="panel-heading">OTROS ARCHIVOS - Haga click
												para adjuntar archivos</div>
											<div id="otrosFile" class="panel-body dropzone"></div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
		<div class="well-sm text-right clearfix" style="padding: 20px">
			<div class="row">
				<c:if test="${esLDF }">
					<div class="col-lg-2 ">
						<button type="button" id="guardar" class="btn btn-info btn-block">Guardar</button>
					</div>
					<c:if test="${esParcial }">
						<div class="col-lg-2 ">
							<button type="button" id="Eliminar" class="btn btn-danger btn-block">Eliminar</button>
						</div>	
					</c:if>	
				</c:if>
				<div class="col-lg-2 ">
					<button type="button" id="validar"
						class="btn btn-success btn-block">Enviar</button>
				</div>
				<div class="col-lg-2 ">
					<button type="button" id="cancelar"
						class="btn btn-danger btn-block">Cancelar</button>
				</div>

			</div>
		</div>
	</div>
</div>
<div id="ajax-modal" class="modal fade" tabindex="-1" style="" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
		<h3></h3>
	</div>
	<div class="modal-body"></div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-danger">Cancelar</button>
	</div>
</div>

<form:select path="respClinicaObservadas" items ="${respClinicaObservadas}"  id="respuestasClinicasObservadas" itemValue="id" itemLabel="descripcion" cssClass="form-control template-hidden" cssStyle="display:none"/>

<form:select id="especialidades" path="especialidades"  cssClass="form-control template-hidden" cssStyle="display:none">  
                <form:option value="" label="${SELECCIONE_LABEL}"/>  
                <form:options items="${especialidades}" itemLabel="descripcion" itemValue="id"/>  
</form:select> 

<form:select id="departamentos" path="departamentos"  cssClass="form-control template-hidden" cssStyle="display:none">
	<form:option value="" label="${SELECCIONE_LABEL}"/>
	<form:options items="${departamentos}" itemLabel="descripcion" itemValue="id"/>  
</form:select>

<form:select id="municipios" path="municipios"  cssClass="form-control template-hidden" cssStyle="display:none">
	<form:option value="" label="${SELECCIONE_LABEL}"/>
</form:select>

<select class="template-hidden" id="diagnosticos" style="display: none"></select>

<input id="nroSolicitudHidden" type="hidden" value="${nroSolicitud}"/>

<script>
	//funcion jquery para deshabilitar la carga de archivos cuando se llena el resumen de historia clinica
	function archivos(){
	    resumenDZ.disable();
	}
	
	var resumenDZ=null;
	var otrosDZ=null;
	var resumenManual=false;
	
	function getMensajeRangoProfesional(){
		var identificacionMinLength = $("#formProfesional #tipoDocumento option:selected").attr("data-min-length");
		var identificacionMaxLength = $("#formProfesional #tipoDocumento option:selected").attr("data-max-length");
		
		if(identificacionMinLength === identificacionMaxLength){
			return '<fmt:message key="validation.restriction.exactlyNChars"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'.replace("{1}",identificacionMinLength);
		}else{
			return '<fmt:message key="validation.restriction.betweenNandMChars"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'.replace("{1}",identificacionMinLength).replace("{2}",identificacionMaxLength);	
		}
	}
	
	function addFormularioCTC(td,context,data, modalId){
// if(data.tipoPPM != 'POS' || data.visibleCtc) {
		if(esLDF){
			if($(td).find("div[id*='formularioCTC_']").size() <= 0){
			 var modal = $("#modal-template").clone().attr("id","formularioCTC_"+ $(td).parent().data("index"));
			 modal.removeClass("template-hidden");
			 modal.find("h3").html("Adjuntar Formulario CTC");
			 var tipoDocumento = "";
			 if(modalId=="formularioCTCMedicamento"){
				 tipoDocumento = "FORM_CTC_MEDICAMENTO";
			 }else if(modalId=="formularioCTCProcedimiento"){
				 tipoDocumento = "FORM_CTC_PROCEDIMIENTO";
			 } else if(modalId=="formularioCTCInsumo"){
				 tipoDocumento = "FORM_CTC_INSUMO";
			 }
			 modal.find(".modal-body").append($("#panel-dropzone-template").clone().attr("id","dz_"+ $(td).parent().data("data").id));
			 modal.find(".panel-dropzone").removeClass("hidden");
			 modal.find(".panel-heading").html("Haga click para adjuntar un archivo");
			 modal.find(".dropzone").addClass("dropzone").dropzone({
					url : "${webContext}/prestador/upload/formularioCTC?tipoDocumento="+tipoDocumento+"&idItem="+td.parents("tr").data("data").id,
					addRemoveLinks: true,
					maxFiles : 1,
					maxFilesize : 3,
					error: function(file, message){
						file.errorCheck = true; 
						this.removeFile(file);
						modal.find(".messages").empty(); 
						appendErrorMessage(modal.find(".messages"), message);
					},
					acceptedFiles : "application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"
				});
			 
			td.append(modal);
			
			var boton = $("#boton-adjunto").clone().attr("id","botonFormularioCTC_"+ $(td).parent().data("data").id)
			boton.removeClass("hidden");
			td.find("div:first").after(boton);
			boton.click(function(){$(this).parents("td").find(".modal").modal()});
			modal.on("hidden", function(){
				if(modal.find(".dz-preview").size()>0){
					boton.removeClass("btn-warning").removeClass("btn-success").addClass("btn-success");
				}else{
					boton.removeClass("btn-success").removeClass("btn-warning").addClass("btn-warning");
				}
			})
		}
		}else{
			if($(td).find('a[title*="CTC"]').size() <= 0){
			 td.find("div:first").after(createComponent({
					name : 'formularioCTC',
					type : 'modalCaller',
					description : 'Agregar formulario CTC',
					modalId : modalId,
					objectData : (data.formularioCTC) ? {data : obtieneListaNameValue(data.formularioCTC)} : null,
					serializedData : (data.formularioCTC) ? $.param(data.formularioCTC).split("%5D=").join("=").split("%5D%5B").join("%5D.") : ""
				}, context));
		}

		}
// 		}
	}
	
	function handlerAddInsumo(data, row) {
		addTooltipToDescripcion(row);
		var td = $($(row).find("button")[0]).parent();
		td.prepend(createComponent({
			name : 'formula',
			type : 'modalCaller',
			description : 'Agregar Prescripción Insumo',
			modalId : 'prescripcionInsumo',
			objectData : (data.prescripcion) ? obtieneObjetoNameValue(data.prescripcion) : null,
			serializedData : (data.prescripcion) ? $.param(data.prescripcion) : ""
		}, this));
		
		var esInsumoKit = false;
		url = "${webContext}/prestador/esInsumoKit/"+data.id;
		$.get(url, function(dataEsInsumo) {
		    esInsumoKit = $.parseJSON(dataEsInsumo);
			 if (!esInsumoKit && ($.parseJSON(data.nivel) == 5 ||($.parseJSON(data.nivel) == 0 && $.parseJSON(data.visibleCtc)))){
			     getInsumoHomologo(row, data.id);
				 addFormularioCTC(td,this,data,'formularioCTCInsumo');
			 }
        }, "json");			
	}
	
	function handlerAddMedicamento(data, row) {
		addTooltipToDescripcion(row);
		var td = $($(row).find("button")[0]).parent();
		td.prepend(createComponent({
			name : 'formula',
			type : 'modalCaller',
			description : 'Agregar Prescripción Medicamento',
			modalId : 'prescripcionMedicamento',
			objectData : (data.prescripcion) ? obtieneObjetoNameValue(data.prescripcion) : null,
			serializedData : (data.prescripcion) ? $.param(data.prescripcion) : ""
		}, this));
		if ($.parseJSON(data.visibleCtc)) {
			getMedicamentoHomologo(row, data.id);
			addFormularioCTC(td,this,data,'formularioCTCMedicamento');
		} else {
			$(row).find("select").attr('onchange', 'validarMedicamentoCondicionado("'+data.id+'", this)');
			if ($(row).find("select").children().size() > 0){
				cumple = false;
				url = "${webContext}/prestador/cumpleMedicamentoCondicionadoDiagnostico/"+data.id+"/"+$(row).find("select").find('option:first').val();
				$.get(url, function(dataMedCon) {
					 cumple = $.parseJSON(dataMedCon);
					 if (!cumple){
						 getMedicamentoHomologo(row, data.id);
						addFormularioCTC(td,this,data,'formularioCTCMedicamento');
					 }
		        }, "json");
			}			
		}		
	}
	
	function getInsumoHomologo(row, id){
		$.post("${webContext}/prestador/search/insumoPosHomologo/"+id,
				function(resultado){
					if (resultado.generalErrors.length > 0) {
						alert(resultado.generalErrors);
						 
					} else {
						row.data("insumoHomologo",resultado.content);
					}
				});
	}
	
	
	function getMedicamentoHomologo(row, id){
		$.post("${webContext}/prestador/search/medicamentoPosHomologo/"+id,
				function(resultado){
					if (resultado.generalErrors.length > 0) {
						alert(resultado.generalErrors);
						 
					} else {
						row.data("medicamentoHomologo",resultado.content);
					}
				});
	}
	function handlerAddProcedimiento(data, row) {
		addTooltipToDescripcion(row);
		for (index in data.especialidades){
			row.find("[name*='especialidad']").append(
					$("<option value='" + data.especialidades[index].id + "' " + 
							((data.especialidad && data.especialidad == data.especialidades[index].id) ? "selected='selected'" : "") + ">"
							+ data.especialidades[index].descripcion + "</option>"));
		}
		var td = $($(row).find("button")[0]).parent();

		td.prepend(createComponent({
			name : 'formula',
			type : 'modalCaller',
			description : 'Agregar Prescripción Procedimiento',
			modalId : 'prescripcionProcedimiento',
			objectData : (data.prescripcion) ? obtieneObjetoNameValue(data.prescripcion) : null,
			serializedData : (data.prescripcion) ? $.param(data.prescripcion) : ""
		}, this));

		if (parseInt(data.nivel) == 5 && data.tipoPPM == "No POS") {
			addFormularioCTC(td,this,data,'formularioCTCProcedimiento');
		}
	}
	
	function addTooltipToDescripcion(row){
		$(row).find("[name$='.descripcion']").tooltip({placement: 'bottom', title: $(row).find("[name$='.descripcion']").val()});
	}
	
	function addDiagnostico(data, row, container) {
		addTooltipToDescripcion(row);
	    if($(this).attr("id") == "formDiagnosticos") {
    		$("#diagnosticos").append(
    				$("<option value='"+data.codigo+"'>" + data.descripcion
    						+ "</option>"));
    		$(".diagnosticos").append(
    				$("<option value='"+data.codigo+"'>" + data.descripcion
    						+ "</option>"));
	    }
	    
		var td = $($(row).find("button")[0]).parent();
		//Solicitud parcial
		if($(this).find("i.icon-check").length > 0 && data.esPrincipal){ 
			$(this).find("button[data-group='diagnosticos.esPrincipal'] i").removeClass("icon-check").addClass("icon-unchecked");
		    $(this).find("button[data-group='diagnosticos.esPrincipal']").find("input[type=radio]").prop("checked", false);
		}
		td.prepend(createComponent({
			name : 'esPrincipal',
			type : 'radioButton',
			checked : (data.esPrincipal)
		}, this));
		if ($("#diagnosticos").children().size() == 1){
			$(".diagnosticos").change();
		}
		//Rompe la solicitud parcial, por eso se agrega el if de mas arriba
		if($(this).find("i.icon-check").length == 0){ 
		    $(row).find("button[data-group='diagnosticos.esPrincipal']")[0].click();
		}
		
	}
	
	function removeDiagnostico(data, row) {
	    if($(this).attr("id") == "formDiagnosticos") {
    		$("#diagnosticos option[value=" + data.codigo + "]").remove();
    		$(".diagnosticos option[value=" + data.codigo + "]").remove();
    		$(".diagnosticos").change();  		
	    }

		var input = $(row).find("input[value="+data.id+"]")[0];
		if(input && $($(input).closest("tr")).find("i.icon-check").length > 0) {
		    var i = $(row).find("i.icon-unchecked")[0];
		    
		    if(i) {
		        $($(i).closest("button")).click();
		    }
		}
	}

	function buildData() {
 		var data = $("#formDiagnosticos").serialize()+"&"+$("#formProcedimientos").serialize()+"&"+$("#formMedicamentos").serialize()+"&"+$("#formInsumos").serialize();
 		
 		if($("#formSedeIps").length > 0) {
			data += "&" + buildIps();
 		}
 		if($("#formProfesional").length > 0) {
			data += "&" + buildProfesional();
 		}
		data += buildExtraForms("medicamentos");
		data += buildExtraForms("procedimientos");
		data += buildExtraForms("insumos");

		data += buildResumen();
		data += "&observaciones=" + $("#formObservaciones textarea").val();
		
		if($("#nroSolicitudHidden").val()!=undefined){
			data += "&nroSolicitud=" + $("#nroSolicitudHidden").val();
		}
		
		return data;
	}

	function buildResumen() {
		var dataForm = "";
		var dataResumen = $("[data-form-resumen]").attr("data-form-resumen");
		if (dataResumen && dataResumen != "") {
			var dataResumenSplit = dataResumen.split("&");
			for (x in dataResumenSplit) {
				dataForm += "&resumen." + dataResumenSplit[x];
			}

			return dataForm;
		}
		return "";
	}
	
	function encodeArray(array) {
	    return $.map(array, function(e, i){
	       return encodeURIComponent(e).replace(/%20/g,'+') ;
	    });
	}

	function mergeAttributeValue(attributes, values) {
	    var array = [];
	    for (var element in attributes) {
	        array.push(attributes[element] + "=" + values[element]);
	    }
	    return array.join("&");
	}

	// NOTA: está hecho a mano porque no coincide con las propiedades del dto.
	function buildIps() {
		return "idSedeIps="+$("#formSedeIps #id").val();
	}
	
	function buildProfesional() {
		return "idProfesional="+$("#formProfesional #id").val();
	}

	function buildExtraForms(type) {

		var dataForms = "";

		//Procedimientos y sus formularios
		$.each(
				$("input#tipoPPM[name^='" + type + "[']"),
				function(i, e) {
					if ((type=="medicamentos" && $(e).parents("tr").data("data").visibleCtc) || (type=="medicamentos" && $(e).parents("tr").find('a[title*="CTC"]').size() != 0) ||
							(type=="insumos" && $(e).parents("tr").data("data").nivel == 5)  ||
							(type=="insumos" && $(e).parents("tr").data("data").nivel == 'null' && $(e).parents("tr").data("data").visibleCtc) ||
							(type=="insumos" && $(e).parents("tr").data("data").nivel == 0 && $(e).parents("tr").data("data").visibleCtc) ||
							(type=="procedimientos" && $(e).parents("tr").data("data").nivel == 5)) {
						
						switch(type){
							case "medicamentos":
								dataForms+=	("&" + type + "[" + $(e).parents("tr").data("index") + "].visibleCtc="+$(e).parents("tr").data("data").visibleCtc);
								
								/*var dataFarmacovigilancia = $(e).parents("tr").find("[data-form-farmacovigilancia]").attr("data-form-farmacovigilancia");
								if (dataFarmacovigilancia){
									var dataFarmacovigilanciaSplit = dataFarmacovigilancia.split("&");
									for (x in dataFarmacovigilanciaSplit) {
										dataForms += ("&" + type + "["
												+ $(e).parents("tr").data("index")
												+ "].formularioFarmacovigilancia." + dataFarmacovigilanciaSplit[x]);
									}
								}*/						
								
								break;
							case "insumos":
								dataForms+=	("&" + type + "[" + $(e).parents("tr").data("index") + "].visibleCtc="+$(e).parents("tr").data("data").visibleCtc);
								dataForms+=	("&" + type + "[" + $(e).parents("tr").data("index") + "].nivel="+$(e).parents("tr").data("data").nivel);
													
								break;
							case "procedimientos":
								dataForms+=	("&" + type + "[" + $(e).parents("tr").data("index") + "].nivel="+$(e).parents("tr").data("data").nivel);
								break;
						}
						
						//Formulario CTC
						var dataCTC = $(e).parents("tr").find("[data-form-ctc]").attr("data-form-ctc");
						if (dataCTC) {
							var dataCTCSplit = dataCTC.split("&");
							for (x in dataCTCSplit) {
								dataForms += ("&" + type + "[" + $(e).parents("tr").data("index") + "].formularioCTC." + dataCTCSplit[x]);
							}
						}
					}
					var dataPrescripcion = $(e).parent().parent().find("[data-form-prescr]").attr("data-form-prescr");
					if (dataPrescripcion) {
						var dataPrescripcionSplit = dataPrescripcion.split("&");
						for (x in dataPrescripcionSplit) {
							dataForms += ("&" + type + "[" + $(e).parents("tr").data("index") + "].prescripcion." + dataPrescripcionSplit[x]);
						}
					}
				});

		return dataForms;
	}

	function callbackSolicitud(data) {
		if (data.generalErrors.length > 0) {
			$("#messages").empty();
			createErrorMessages($("#messages"), data.generalErrors);
			scrollTop(500);
		}else{
			location.href='${pageContext.request.contextPath}/jsp/common/ticket.jsp';	
		}
		
	}
	function setSedeIps(data){
		$.ajax({
			url : "${webContext}/prestador/set_sedeIps",
			dataType : "json",
			type : "POST",
			data: data,
			success : function(resultado) {
				sedeIps = true;
			},
			error : function(resultado) {
					console.log(resultado);
			}
		});			
	}
	function setProfesional(data){
		$.ajax({
			url : "${webContext}/prestador/set_profesional",
			dataType : "json",
			data: data,
			type : "POST",
			success : function(resultado) {
					console.log(resultado);
					profesional = true;
					
				},
				error : function(resultado) {
						console.log(resultado);
				}
					
				}
			);
	}
	function isValid(){
		
		$("#messages").empty();
		var errores = [];
		
		if (($("#formDiagnosticos input[name*=diagnosticos]").length == 0) || 
				($("#formDiagnosticos input[name*=diagnosticos]").length > 0 && $("#formDiagnosticos [name*='diagnosticos']:checked").length == 0)){
			errores.push("Debe diligenciar al menos un diagnóstico, y marcar alguno como principal.");
		}
		if ($("button[onclick*='fill'].btn").length == 0){
			errores.push("Debe ingresar al menos un procedimiento, medicamento o insumo.");
		}else{
			if ($("td.data button.btn-warning").length > 0){
				errores.push("Debe diligenciar todos los formularios CTC y prescripciones correspondientes a cada item.");
			}	
		}
		if (!resumenManual && resumenDZ.getAcceptedFiles().length==0){
			errores.push("Debe adjuntar el resumen de historia Clínica.");
		}
		if (!sedeIps && !$("#formIPS").data("data")){
			errores.push("Debe diligenciar la sede IPS para la que está transaccionando.");
		}
		
		if (!profesional && !$("#formProfesional").data("data")){
			errores.push("Debe diligenciar el profesional para el que está transaccionando.");
		}
		
		errores = validarCantidadesMedicamento(errores);
		
		return errores;
	}
	
    function validarCantidadesMedicamento(errores) {
        $("#formMedicamentos tr").each(function(i, e) {
            
            if($(e).data("data")) {
            	var datos = new Array();
            	//TODO encontrar donde se esta guardando esta data, esto claramente es un PARCHE
            	$(e).find("button[rel=tooltip]").removeData("formPrescr");
            	var buttonData = $(e).find("button[rel=tooltip]").data();
            	for(var i = 0; i < Object.keys(buttonData).length; i++) {
            	    if(buttonData[i] != undefined) {
                	    if(buttonData[i].name=="dosis") {
                        	var dosis = buttonData[i];
                        	datos.push({name : "dosis", value : dosis ? dosis.value : 0 });
                	    } else if(buttonData[i].name=="frecuencia") {
                        	var frecuencia = buttonData[i];
                            datos.push({name : "frecuencia", value : frecuencia ? frecuencia.value : 0 });
                	    } else if(buttonData[i].name=="cada") {
                        	var cada = buttonData[i];
                            datos.push({name : "cada", value : cada ? cada.value : 0 });
                	    } else if(buttonData[i].name=="duracion") {
                        	var duracion = buttonData[i];
                            datos.push({name : "duracion", value : duracion ? duracion.value : 0 });
                    	}
            	    }
            	}
            	
                datos.push({name : "idMedicamento", value : $(e).data("data").id });
                var post = $.ajax({
                    type : 'POST',
                    async : false,
                    url : "${webContext}/prestador/cumpleRangoMedicamento",
                    data : datos
                });
                post.success(function(data) {
                    if(data[0] == 0) {
                        return errores;
                    } else if(data[0] == 2) {
                        errores.push('El valor de las unidades por mes (' + data[2] + ') para el medicamento ' + $(e).data("data").descripcion + ' no se encuentra en el rango valido de valores ' + data[1]);
                    } else if(data[0] == 3) {
                        errores.push('El medicamento ' + $(e).data("data").descripcion + ' se encuentra asociado a programas crónicos por lo que la duración del tratamiento debe ser mayor a 1 mes e inferior a 6 meses');
                    } else {
                        errores.push(data[1]);
                    }
               	});
        	}
		});
        return errores;
    }
	
	function callbackGuardarSolicitud(data) {
		$("#messages").empty();
		
		if (data.generalErrors.length == 0){
			createAlertMessage($("#messages"), {dismissable:true, type:'success', autoShow:true, message:'Se guardó exitosamente la solicitud con número: ' + data.content + '.<br/>Recuerde que los archivos adjuntos no se guardan en la solicitud parcial.'});
			$("#nroSolicitudHidden").val(data.content);
		}
		else{
			createErrorMessages($("#messages"), data.generalErrors);
		}
		scrollTop(250);
	}
	
	function cargarSolicitud(solicitud) {
		
		$("#messages").empty();
// 		if(solicitud.nroSolicitud!= undefined && solicitud.nroSolicitud!= ""){
// 			$("#nroSolicitud").val(solicitud.nroSolicitud);
// 			$("#nroSolicitudForm").show();
// 		}
		
		// Sede Ips
		if (solicitud.idSedeIps){
			$.get("${webContext}/prestador/search/sedeById/"+solicitud.idSedeIps, cargaSedeIps);
		}
		
		// Profesional
		if (solicitud.idProfesional){
			$.get("${webContext}/prestador/search/profesionalById/"+solicitud.idProfesional, cargaProfesional);
		}
		
		// Diagnósticos
		for (var diagnostico in solicitud.diagnosticos) {
		    var diag = solicitud.diagnosticos[diagnostico];
		    $("#formDiagnosticos")[0].addRow(diag, $("#formDiagnosticos")[0].techFields);
		}

		// Procedimientos
		for (var procedimiento in solicitud.procedimientos) {
		    var proc = solicitud.procedimientos[procedimiento];
		    $.get("${webContext}/prestador/search/procedimientoById/"+proc.id, function(data){cargaProcedimiento(data, proc);});
		}
		
		// Medicamentos
		for (var medicamento in solicitud.medicamentos) {
		    var med = solicitud.medicamentos[medicamento];
		    var copiaCamposM = $.extend(true, [], $("#formMedicamentos")[0].techFields);
		    copiaCamposM.filter(function(e){return e.name == "cant";})[0].value = med.cant;
		    copiaCamposM.filter(function(e){return e.name == "dxAsociado";})[0].value = med.dxAsociado;
		    $("#formMedicamentos")[0].addRow(med, copiaCamposM);
		}
		
		// Insumos
		for (var insumo in solicitud.insumos) {
		    var ins= solicitud.insumos[insumo];
		    var copiaCamposM = $.extend(true, [], $("#formInsumos")[0].techFields);
		    copiaCamposM.filter(function(e){return e.name == "cant";})[0].value = ins.cant;
		    copiaCamposM.filter(function(e){return e.name == "dxAsociado";})[0].value = ins.dxAsociado;
		    $("#formInsumos")[0].addRow(ins, copiaCamposM);
		}
		
		
		// Resúmen historia clínica
		if (solicitud.resumen) {
			$("#resumenFile button").attr("data-form-resumen", $.param(solicitud.resumen).split("%5D=").join("=").split("%5D%5B").join("%5D."));
			$("#resumenFile button").data({data: (solicitud.resumen) ? obtieneListaNameValue(solicitud.resumen) : null});
			if (resumenManual != undefined) {
				resumenManual = true;
			}
		}
		
		$("#observaciones").val(solicitud.observaciones);
	}
	
	function cargaSedeIps(sedeIpsResult) {
		if (sedeIpsResult.generalErrors.length == 0) {
			$("#formSedeIps")[0].addRow(sedeIpsResult.content, []);
		}
	}
	
	function cargaProfesional(profesionalResult) {
		if (profesionalResult.generalErrors.length == 0) {
			$("#formProfesional")[0].addRow(profesionalResult.content, []);
			$("#formProfesional .messages").empty();
		}
	}
	
	function cargaProcedimiento(procedimientoResult, proc){
		if (procedimientoResult.generalErrors.length == 0) {
			procedimientoResult.content.prescripcion = proc.prescripcion;
			$("#formProcedimientos")[0].addRow(procedimientoResult.content, $("#formProcedimientos")[0].techFields);
			var fila = $("#formProcedimientos .table tbody tr.row [name$=id][value="+ proc.id+ "]").parents("tr");
			fila.find("[name$=cant]").val(proc.cant)
			fila.find("[name$=especialidad]").val(proc.especialidad)
			fila.find("[name$=dxAsociado]").val(proc.dxAsociado)
		}
	}
	
	function eliminarResumen(){
		$.ajax({
			url : "${webContext}/prestador/eliminarResumen",
			dataType : "json",
			type : "POST"
		});
		
		
	}
	function eliminarOtros(file){
	    var data = new Array();
	    data.push({name : "name", value : file.name});
		$.post("${webContext}/prestador/eliminarOtros", data);
	}
	
	function loadJson(){
		
			$.ajax({
				url : "${webContext}/prestador/loadJson",
				dataType : "json",
				type : "POST",
				success : function(resultado) {
					cargarSolicitud(eval('(' + resultado.content + ')'));
				},
				error : function(resultado) {
//						form.onError();
				},
				complete: function() {
				}
			});
		
	}
	
	function postEliminarSP(response){
		if(response.content == "OK"){
			$("#messages").empty();
			location.href="${webContext}/ldf/bandejaSolicitudParcial";
		}else{
			$("#messages").empty();
			createErrorMessages($("#messages"), response.generalErrors);
			scrollTop(500);
		}
	}
	$(function() {
		
		if(esParcial){
			var modal = $("#modal-template-confirm").clone().attr("id","eliminarConfirmar");
			modal.find(".modal-body").empty();
			modal.find(".modal-body").append("<p>Está seguro que desea Eliminar la Solicitud Parcial #[${nroSolicitud}], Desea Continuar?</p>");
			modal.find("#aceptar").click(function(){
				$.post('${pageContext.request.contextPath}/prestador/eliminarSolicitudParcial/'+ $("#nroSolicitud").val() ,
						{}, postEliminarSP);
			})
			$("#Eliminar").parent().append(modal);
			$("#Eliminar").click(function(){
				$(this).parent().find("#eliminarConfirmar").modal();
			})
			
		}

	    var hayProfesional = $("#formProfesional").length > 0;
	    
		//Lleno los datos del afiliado
		$.post('${pageContext.request.contextPath}/prestador/getAfiliado',
							{}, displayDatosAfiliado);
		
		if(!sedeIps){
			$("#formProfesional").attr("dependentForm","formSedeIps");
		}

		configABMComponents();

		$("select#departamentoSedeIpsId").change(function(){
			if(!$(this).is("[readOnly]")){
				reloadMunicipiosByDepartamento($("select#departamentoSedeIpsId"), $("select#municipioSedeIpsId"));
			}
		});
		if(hayProfesional) {
    		$("#formProfesional")[0].setValidateBeforeSearch(function(){
    			if (!$.parseJSON($("#formProfesional #tipoDocumento option:selected").attr("data-alpha"))) 
    				$(this).validate(
    						{
    							onfocusout : false,
    							focusInvalid : false,
    							focusCleanup : false,
    							onkeyup : false,
    							onclick : false,
    							rules : {
    								numeroDocumento : {required:function(){return $("#formProfesional #tipoDocumento").val() != "";}, 
    														minlength:parseInt($("#formProfesional #tipoDocumento option:selected").attr("data-min-length")),
    														maxlength:parseInt($("#formProfesional #tipoDocumento option:selected").attr("data-max-length")),
    														digits:true
    															},
    								primerNombre: {minlength:4},
    								segundoNombre: {minlength:4},
    								primerApellido: {minlength:4},
    								segundoApellido: {minlength:4}						
    							},
    							messages : {
    								numeroDocumento : {	required:'<fmt:message key="validation.required"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.',
    													minlength:getMensajeRangoProfesional,
    													maxlength:getMensajeRangoProfesional,
    													digits: '<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.'},
    								primerNombre: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Primer Nombre"/><fmt:param value="4"/></fmt:message>.',
    								segundoNombre: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Segundo Nombre"/><fmt:param value="4"/></fmt:message>.',
    								primerApellido: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Primer Apellido"/><fmt:param value="4"/></fmt:message>.',
    								segundoApellido: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Segundo Apellido"/><fmt:param value="4"/></fmt:message>.'		
    													
    							},  errorPlacement: function (error, element){
    						    	appendErrorMessage($("#formProfesional .messages"), error.text());
    						    }
    						});
    			else
    				$(this).validate(
    						{
    							onfocusout : false,
    							focusInvalid : false,
    							focusCleanup : false,
    							onkeyup : false,
    							onclick : false,
    							rules : {
    								numeroDocumento : {required:function(){return $("#formProfesional #tipoDocumento").val() != "";}, 
    														minlength:parseInt($("#formProfesional #tipoDocumento option:selected").attr("data-min-length")),
    														maxlength:parseInt($("#formProfesional #tipoDocumento option:selected").attr("data-max-length")),
    														digits:false
    															},
    								primerNombre: {minlength:4},
    								segundoNombre: {minlength:4},
    								primerApellido: {minlength:4},
    								segundoApellido: {minlength:4}						
    							},
    							messages : {
    								numeroDocumento : {	required:'<fmt:message key="validation.required"><fmt:param value="${NUMERO_DOCUMENTO_LABEL}"/></fmt:message>.',
    													minlength:getMensajeRangoProfesional,
    													maxlength:getMensajeRangoProfesional},
    								primerNombre: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Primer Nombre"/><fmt:param value="4"/></fmt:message>.',
    								segundoNombre: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Segundo Nombre"/><fmt:param value="4"/></fmt:message>.',
    								primerApellido: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Primer Apellido"/><fmt:param value="4"/></fmt:message>.',
    								segundoApellido: '<fmt:message key="validation.restriction.atLeastNChars"><fmt:param value="Segundo Apellido"/><fmt:param value="4"/></fmt:message>.'		
    													
    							},  errorPlacement: function (error, element){
    						    	appendErrorMessage($("#formProfesional .messages"), error.text());
    						    }
    						});
    			
    			
    			return validateBeforeSearch(this) && $(this).valid();
    		});
		}

		$("#cancelar").click(function(){
			location.href="${webContext}/main";
		});
		
		//Es el boton Enviar.
		$("#validar").click(
			function() {
				var errores = isValid();
				
				if (errores.length == 0) {
					$("#messages").empty();
					$.post("${webContext}/prestador/crearSolicitud",
							buildData(), callbackSolicitud);
					$(window).scrollTop(0);
					/*document.forms[0].submit();*/
				} else {
					createErrorMessages($("#messages"), errores);
					scrollTop(500);
				}

			});
		
		$("#guardar").click(
				function() {
					var errores = [];
					if (errores.length == 0) {
						$("#messages").empty();
						$.post("${webContext}/prestador/guardarParcialmenteSolicitud",
								buildData(), callbackGuardarSolicitud);
						$("#messages").show();
						$(window).scrollTop(0);
					} else {
						createErrorMessages($("#messages"), errores);
					}

				});
		
		$("div[name='comercial']").click( function(){
		    $("#formMedicamentos").find(".messages").empty();
		    var checkbox = $(this).find(':checkbox');		    
		    if($(checkbox).is(':checked')){
		        createAlertMessage(  $("#formMedicamentos").find(".messages"),
						{
							type : "info",
							message : "${INFO_MEDICAMENTO_COMERCIAL}",
							autoShow : true,
							dismissable : true
						});
		    }
	    });
		
			
		
		Dropzone.autoDiscover = false;
		resumenDZ = new Dropzone("#resumenFile", {
			url : "${webContext}/prestador/upload/resumen",
			addRemoveLinks: true,
			maxFiles : 1,
			maxFilesize : 3,
			acceptedFiles : "application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"
		});
		
		resumenDZ.on("error", 
						function(file, message){
							file.errorCheck = true; 
							this.removeFile(file);
							$("#fileMessages").empty(); 
							appendErrorMessage($("#fileMessages"), message);
						});
		resumenDZ.on("removedfile",
						function(file){
							$("#fileMessages").empty(); 
							if (!file.errorCheck){ 
								eliminarResumen();
								$('#rm').removeAttr('disabled');
								$('#rm').removeAttr('style');
							}
						});
		
		resumenDZ.on("selectedfiles", function(){
		    								$("#fileMessages").empty();
		    								$('#rm').attr('disabled','disabled');
		    								$('#rm').css('color','white');
		    							});
		
		otrosDZ = new Dropzone("#otrosFile", {
			url : "${webContext}/prestador/upload/otros",
			addRemoveLinks: true,
			maxFiles: 5,
			maxFilesize : 3,
			acceptedFiles : "application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"
		});
		
		otrosDZ.on("error", 
					function(file, message){
						file.errorCheck = true; 
						this.removeFile(file);
						$("#fileMessages").empty(); 
						appendErrorMessage($("#fileMessages"), message);
					});
		otrosDZ.on("removedfile",
					function(file){
						$("#fileMessages").empty(); 
						if(!file.errorCheck) 
							eliminarOtros(file);
					});	
		otrosDZ.on("selectedfiles", function(){$("#fileMessages").empty(); });
		
		//Para que al cliquear sobre el titulo se ejecute el mismo evento que en el cuerpo del dropzone y sea intuitivo
		$(".panel-dropzone .panel-heading").click(function(){$(this).parent().find(".dropzone").click();});	
		
		if(!sedeIps){
			$("#formSedeIps")[0].setAddHandler(setSedeIps);
		}else{
		    if(hayProfesional) {
				$("#formProfesional")[0].enable();
		    }
		}
		
		if(!profesional && hayProfesional){
			$("#formProfesional")[0].setAddHandler(setProfesional);
		}
		
		$("#formProcedimientos")[0].setAddHandler(handlerAddProcedimiento);
		
		$("#formInsumos")[0].setAddHandler(handlerAddInsumo);

		$("#formMedicamentos")[0].setAddHandler(handlerAddMedicamento);

		$("#formDiagnosticos")[0].setAddHandler(addDiagnostico);
		$("#formDiagnosticos")[0].setRemoveHandler(removeDiagnostico);

		$("#adjuntarArchivoResumen").click(function() {
			$("#adjuntoResumen").click();
		});
		$("#adjuntarArchivoOtros").click(function() {
			$("#adjuntoArchivoOtros").click();
		});
		$("#adjuntoArchivoOtros").change(function() {
			$("#displayArchivoOtros").val($(this).val());
		});
		<c:if test="${esParcial}">
			loadJson();
		</c:if>		
		
		$("input[name=comercial]").closest(".form-group").append($("label[for=comercial]"));
		$("label[for=comercial]").addClass("label-check-right");
	});
	var $container;

	function setFormTargetContainer(cont) {
		$container = cont;
	}
	
	function validateDigitsOnly(field, msg){
		if (/[A-z]|\./.test(field.val())){
			appendEmbebbedErrorMessage(field, msg);
			return false;
		}
		return true;
	}
	function validarMedicamentoCondicionado(med, sel){
		td = $($(sel).closest('tr').find("button")[0]).closest('td'); 
		dx = $(sel).find('option:selected').val();
		if (dx == undefined) {
			$(td).find("button[id*='CTC']").remove();
			$(td).find("div[id*='formularioCTC_']").remove();
			$(td).find('a[title*="CTC"]').parent().remove();
			return;
		}
		url = "${webContext}/prestador/cumpleMedicamentoCondicionadoDiagnostico/"+med+"/"+dx;
		cumple = false;
		$.get(url, function(data) {
			 cumple = data;
			 if (!cumple){
				
				addFormularioCTC(td,this,data,'formularioCTCMedicamento');
			} else {
				$(td).find("button[id*='CTC']").remove();
				$(td).find("div[id*='formularioCTC_']").remove();
				$(td).find('a[title*="CTC"]').parent().remove();
			}
        }, "json");
	}
</script>

<jsp:include page="./../includes/resumenManual.jsp" />
<jsp:include page="./../includes/prescripcionMedicamentoDialog.jsp" />
<jsp:include page="./../includes/prescripcionInsumoDialog.jsp" />
<jsp:include page="./../includes/prescripcionProcedimientoDialog.jsp" />
<jsp:include page="./../includes/formularioCTCMedicamentos.jsp" />
<jsp:include page="./../includes/formularioCTCInsumos.jsp" />
<jsp:include page="./../includes/formularioCTCProcedimientos.jsp" />
<jsp:include page="./../../includes/footer.jsp" />
