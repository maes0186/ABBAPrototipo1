<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />
<c:set var="TITULO" value="${param.titulo}" />
<fmt:message key='label.id.type' var="TIPO_DOCUMENTO_LABEL" />
<fmt:message key='label.id.number' var="NUMERO_DOCUMENTO_LABEL" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />
<fmt:message key='label.button.crear' var="CREAR" />

<div id="filtrosContent" class="tab-content">
<div class="form-group form-group-sm ">
		<label
			class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"
			for="registroMedicoSearch"><fmt:message
				key="label.medical.id" /></label>
		<div class=" col-lg-3 col-sm-3 col-xs-6">
			<input class="form-control input-sm" id="registroMedicoSearch"
				name="registroMedico" value="${crudFilter.registroMedico}"
				placeholder="<fmt:message
                                    key="label.medical.id" />">
		</div>

	</div>
         <div id="identificacionComponent"
		class="form-group form-group-sm identificacionComponent ">
		<label class=" col-lg-2 control-label control-label-sm" for="">Tipo
			Documento</label>
		<div class="col-lg-3">
			<select id="tipoDocumento" name="tipoDocumento"
				class="form-control input-sm input-sm">
				<option value="" data-min-length="6" data-max-length="15"
					data-alpha="false">${SELECCIONE_LABEL}</option>
				<c:forEach items="${tiposDeDocumento}" var="tipoIdent">
                  <c:if test="${tipoIdent.aplicaProfesional}">
					<option value="${tipoIdent.id}"
						data-min-length="${tipoIdent.minLength}"
						data-max-length="${tipoIdent.maxLength}"
						data-alpha="${tipoIdent.esAlfanumerico}">${tipoIdent.descripcion}</option>
                  </c:if>
				</c:forEach>
			</select>
		</div>
		<label class="col-lg-2 control-label control-label-sm" for="">${NUMERO_DOCUMENTO_LABEL }</label>
		<div class="col-lg-3">
			<input type="text" class="form-control input-sm input-sm"
				id="numeroDocumento" name="numeroDocumento"
				placeholder="${NUMERO_DOCUMENTO_LABEL }">
		</div>
	</div>
	<div class="form-group form-group-sm ">
		<label
			class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"
			for="primerNombreSearch"><fmt:message
				key="label.name.first" /></label>
		<div class=" col-lg-3 col-sm-3 col-xs-6">
			<input class="form-control input-sm" id="primerNombreSearch"
				name="primerNombre" value="${crudFilter.primerNombre}"
				placeholder="<fmt:message
                                    key="label.name.first" />">
		</div>
		<label
			class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"
			for="segundoNombreSearch"><fmt:message
				key="label.name.first2" /></label>
		<div class=" col-lg-3 col-sm-3 col-xs-6">
			<input class="form-control input-sm" id="segundoNombreSearch"
				name="segundoNombre" value="${crudFilter.segundoNombre}"
				placeholder="<fmt:message
                                    key="label.name.first2" />">
		</div>

	</div>
	<div class="form-group form-group-sm ">
	   <label
			class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"
			for="primerApellidoSearch"><fmt:message
				key="label.name.last3" /></label>
		<div class=" col-lg-3 col-sm-3 col-xs-6">
			<input class="form-control input-sm" id="primerApellidoSearch"
				name="primerApellido" value="${crudFilter.primerApellido}"
				placeholder="<fmt:message
                                    key="label.name.last3" />">
		</div>
		
		<label
			class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"
			for="segundoApellidoSearch"><fmt:message
				key="label.name.last2" /></label>
		<div class=" col-lg-3 col-sm-3 col-xs-6">
			<input class="form-control input-sm" id="segundoApellidoSearch"
				name="segundoApellido" value="${crudFilter.segundoApellido}"
				placeholder="<fmt:message
                                    key="label.name.last2" />">
		</div>
	</div>
	
	<br />
	<div class="form-group text-right" style="">
		<div class=" col-lg-12">
			<button type="button" id="search" class="btn btn-info btn-sm">
				<fmt:message key="label.button.buscar" />
				<i class="icon-search"></i>
			</button>
			<button type="button" id="clean" class="btn btn-warning btn-sm"
				onclick="this.form.reset(); actualizarElementos(1);">
				<fmt:message key="label.button.limpiar" />
				<i class="icon-eraser"></i>
			</button>
			<button type="button" id="crearNuevoElemento" class="btn btn-success btn-sm">
			 ${CREAR}
			 <i class="icon-plus-sign"></i>
			</button>
		</div>
	</div>
</div>