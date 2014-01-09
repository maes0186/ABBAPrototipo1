<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>				
<fmt:message key='label.id.type' var="TIPO_DOCUMENTO_LABEL" />
<fmt:message key='label.id.number' var="NUMERO_DOCUMENTO_LABEL" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />
<script>
	var NOT_GREATER = '<fmt:message key="validation.date.range.notGreater"/>';
	var NOT_LESS = '<fmt:message key="validation.date.range.notLess"/>';
</script>
				<div id="date-range"class="form-group form-group-sm template-hidden">
					<label id="date-range-from-label" class="control-label control-label-sm col-lg-1">Desde</label>
				  	<div id="date-range-from" class="col-lg-3 input-group date"   data-date-format="dd-mm-yyyy" data-date="">
						<input type="text" name="fechaInicio" class="form-control input-sm" id="date-range-from-input" readonly/>
						<span class="input-group-addon input-sm"><i class="icon-calendar"></i></span>
					</div>
					<label id="date-range-to-label" class="control-label control-label-sm col-lg-1">Hasta</label>
				  	<div id="date-range-to" class="col-lg-3 input-group date"   data-date-format="dd-mm-yyyy" data-date="">
						<input type="text" name="fechaFin" class="form-control input-sm " id="date-range-to-input" readonly/>
						<span class="input-group-addon input-sm" ><i class="icon-calendar"></i></span>
					</div>
				</div>
				
				
				<div id="modal-template-confirm" class="modal fade" tabindex="-1" style=""
					aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3>Confirmación</h3>
					</div>
					<div class="modal-body">
						<p>
							Desea continuar?
						</p>
					</div>
					<div class="modal-footer buttons">
						<button type="button" data-dismiss="modal" id="aceptar" class="btn btn-success">Aceptar</button>
						<button type="button" data-dismiss="modal" class="btn btn-warning">Cancelar</button>
					</div>
				</div>
				
				<div id="modal-template" class="modal fade" tabindex="-1" style=""
					aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3></h3>
					</div>
					<div class="messages col-lg-12"></div>
					<div class="modal-body"></div>
					<div class="modal-footer buttons">
						<button type="button" data-dismiss="modal" class="btn btn-success">Aceptar</button>
					</div>
				</div>
				
				
	
				<button id="boton-adjunto"class="hidden btn btn-warning btn-sm" type="button">
					<i class="icon-paperclip"></i>
					</button>
				
				
				<div id="identificacionComponent" class="template-hidden identificacionComponent row ">
					<label class="text-right col-lg-2 control-label control-label-sm" for="">Tipo Documento</label>
					<div class="col-lg-3">
						<select id="" class="form-control input-sm input-sm">
							<option value="" data-min-length="6" data-max-length="15" data-alpha="false">${SELECCIONE_LABEL}</option>
							<c:forEach items="${tipoIdentificacion}" var="tipoIdent">
								<option value="${tipoIdent.id}" es-afiliado ="${tipoIdent.aplicaAfiliado}" es-ips="${tipoIdent.aplicaIps}" es-profesional="${tipoIdent.aplicaProfesional}" data-min-length="${tipoIdent.minLength}" 
                                 data-max-length="${tipoIdent.maxLength}" data-alpha="${tipoIdent.esAlfanumerico}">${tipoIdent.descripcion}</option>	
							</c:forEach>
						</select>
					</div>
					<label class="text-right col-lg-2 control-label control-label-sm" for="">${NUMERO_DOCUMENTO_LABEL }</label>
					<div class="col-lg-3">
						<input type="text" class="form-control input-sm input-sm" id="" placeholder ="${NUMERO_DOCUMENTO_LABEL }">
					</div>
				</div>
				
				
			<div id="panel-dropzone-template" class="panel panel-dropzone hidden">
				<div class="panel-heading cursor-pointer"></div>
				<div  class="panel-body dropzone dz-clickable">
                 
             <div class="dz-default dz-message"><span>Arrastre sus archivos aquí para subirlos.</span></div></div>
			</div>