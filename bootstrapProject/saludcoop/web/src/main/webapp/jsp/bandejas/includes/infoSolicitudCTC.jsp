<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="itemProcedimiento" value="<%=SystemConstants.ITEM_PROCEDIMIENTO%>" />
<c:set var="itemMedicamento" value="<%=SystemConstants.ITEM_MEDICAMENTO%>" />
<c:set var="itemInsumo"	value="<%=SystemConstants.ITEM_INSUMO%>" />


<fmt:message key='label.bandeja.infoDiagnostico' var="DIAGNOSTICO_LABEL" />
<fmt:message key='label.formCTC' var="FORM_CTC_LABEL" />
<fmt:message key='label.listadoDocAdjuntos' var="DOC_ADJ_LABEL" />
<fmt:message key="label.medicamento.nopos.posHomologo"  var="HOMOLOGO"/>
<fmt:message key="label.insumo.nopos.posHomologo"  var="HOMOLOGO_INSUMO"/>
<fmt:message key='label.docComplementaria' var="DOC_COMP_LABEL" />
<fmt:message key='label.hagaClick' var="HAGA_CLICK_LABEL" />
<fmt:message key='label.soportes' var="SOPORTES" />



<c:if test="${bandejasParam.editable}">
    <c:set var="disableInput" value="" />
</c:if>
<c:if test="${!bandejasParam.editable}">
    <c:set var="disableInput" value="disabled=disabled" />
</c:if>

<c:choose>
    <c:when test="${tipoItem ==  itemMedicamento}">
        <c:set var="TIPO_ITEM" value="<%=SystemConstants.ITEM_MEDICAMENTO_STRING%>" />
    </c:when>
    <c:when test="${tipoItem ==  itemProcedimiento}">
        <c:set var="TIPO_ITEM" value="<%=SystemConstants.ITEM_PROCEDIMIENTO_STRING%>" />
    </c:when>
    <c:when test="${tipoItem ==  itemInsumo}">
		<c:set var="TIPO_ITEM" value="<%=SystemConstants.ITEM_INSUMO_STRING%>" />
	</c:when>
</c:choose>
<div class="row">
    <div class="col-lg-12">
        <div id="messages"></div>
        <div class="form-horizontal">
            <form id="tramiteForm" method="post">
                <input type="hidden" value="${itemId}" id="itemId" name="itemId" />
                <jsp:include page="informacionSolicitud.jsp">
                    <jsp:param value="${disableInput}" name="disableInput" />
                </jsp:include>
                <fieldset class="well">
                    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                        <strong>${DIAGNOSTICO_LABEL}</strong>
                    </blockquote>
                    <div class="collapsible-content">
                        <jsp:include page="./diagnostico.jsp">
                            <jsp:param value="${bandejasParam.editable}" name="editable" />
                        </jsp:include>
                    </div>
                </fieldset>
                <c:if test="${tieneFormCTC}">
                    <c:if test="${tipoItem == itemMedicamento}">
                        <jsp:include page="infoMedicamentoCTC.jsp" />
                    </c:if>
                    <c:if test="${tipoItem == itemProcedimiento}">
                        <jsp:include page="infoProcedimientoCTC.jsp" />
                    </c:if>
                    <c:if test="${tipoItem == itemInsumo}">
						<jsp:include page="infoInsumoCTC.jsp" />
					</c:if>
                </c:if>
                    <fieldset class="well">
                        <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                            <strong>${DOC_ADJ_LABEL}</strong>
                        </blockquote>
                        <div class="collapsible-content">
                            
                            <div class="form-group form-group-sm">
                                <div class="col-lg-6">
                                    <c:if test="${!tieneFormCTC}">
    	                                <div class="form-group form-group-sm">
    		                                <label class="control-label control-label-sm text-right col-lg-6" style="text-align: left">${FORM_CTC_LABEL}</label>
    		                            </div>
                                    
                                        <jsp:include page="./docAdjuntos.jsp">
                                            <jsp:param name="docAdjuntosName" value="docAdjuntosItem" />
                                        </jsp:include>
                                        <br>
                                    </c:if>
                                    <div class="form-group form-group-sm">
		                                <label class="control-label control-label-sm text-right col-lg-6" style="text-align: left">${SOPORTES}</label>
		                            </div>
                                    <jsp:include page="./docAdjuntos.jsp">
                                        <jsp:param name="docAdjuntosName" value="docAdjuntos" />
                                    </jsp:include>
                                </div>
                                <div class="col-lg-6">
                                    <jsp:include page="./docSoporte.jsp">
                                        <jsp:param value="archivosCTC" name="tipoArchivo" />
                                        <jsp:param value="docAdjuntos" name="divId" />
                                        <jsp:param value="${DOC_COMP_LABEL}<br/>${HAGA_CLICK_LABEL}" name="headerMessage" />
                                        <jsp:param value="${!bandejasParam.editable&&!bandejasParam.editableAdjuntos}" name="disabled" />
                                    </jsp:include>
                                </div>
                            </div>
                            <br />
                        </div>
                    </fieldset>
                     
                    <c:if test="${not empty infoMedicamento.medicamentoHomologo}">                    
                        <fieldset class="well" <c:out value="${disableInput}"/>>
                            <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                                <strong><fmt:message key="label.medicamento.nopos.posHomologo" /></strong>
                            </blockquote>
                            <div class="collapsible-content">
                                <div id="medicaHomologo-messages"></div>
                                <jsp:include page="./medicamentoHomologo.jsp">
                                    <jsp:param value="false" name="editable" />
                                </jsp:include>
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-2 control-label  control-label-sm" for="cantidadMedicamHom"><fmt:message key="label.cantidad" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="cantidadMedicamHom" id="cantidadMedicamHom" class="form-control input-sm"
                                            value="${infoMedicamento.cantidadMedicamentoHomologo}" />
                                    </div>
                                    <label class="col-lg-2 control-label control-label-sm" for="dosisMedicamHom"><fmt:message
                                            key="label.infoSolicitud.dosisDias" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="dosisMedicamHom" id="dosisMedicamHom" class="form-control input-sm"
                                            value="${infoMedicamento.medicamentoHomologo.dosis}" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-2 control-label control-label-sm" for="frecuenciaMedicamHom"><fmt:message key="label.frecuencia" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="frecuenciaMedicamHom" id="frecuenciaMedicamHom" class="form-control input-sm"
                                            value="${infoMedicamento.medicamentoHomologo.frecuencia}" />
                                    </div>
                                    <label class="col-lg-2 control-label  control-label-sm" for="duracionTratMedicamHom"><fmt:message
                                            key="label.infoSolicitud.duracionTratamiento" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="duracionTratMedicamHom" id="duracionTratMedicamHom" class="form-control input-sm"
                                            value="${infoMedicamento.medicamentoHomologo.duracion}" />
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </c:if>
                    <c:if test="${not empty infoInsumo.insumoHomologo}">                    
                        <fieldset class="well" <c:out value="${disableInput}"/>>
                        <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                            <strong><fmt:message key="label.insumo.nopos.posHomologo" /></strong>
                        </blockquote>
                        <div class="collapsible-content">
                            <div id="insumoHomologo-messages"></div>
                            <jsp:include page="./insumoHomologo.jsp">
                                <jsp:param value="false" name="editable" />
                            </jsp:include>
                            <div class="form-group form-group-sm">
                                <label class="col-lg-2 control-label  control-label-sm" for="cantidadMedicamHom"><fmt:message key="label.cantidad" /></label>
                                <div class="col-lg-2">
                                    <input type="text" name="cantidadMedicamHom" id="cantidadMedicamHom" class="form-control input-sm"
                                        value="${infoInsumo.cantidadInsumoHomologo}" />
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                            <label class="col-lg-2 control-label  control-label-sm" for="duracionTratMedicamHom"><fmt:message key="label.infoSolicitud.duracionTratamiento" /></label>
                            <div class="col-lg-2">
                                     <input type="text" name="duracionTratMedicamHom" id="duracionTratMedicamHom" class="form-control input-sm" value="${infoInsumo.insumoHomologo.duracion}" />
                                 </div>
                             </div> 
                         </div> 
                     </fieldset>
                    </c:if>
                    <fieldset disabled="disabled" class="well">
                        <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                            <strong> <c:if test="${tipoItem == itemMedicamento}">
                                    <fmt:message key="label.medi.noPos.solicitado" />
                                </c:if> <c:if test="${tipoItem == itemProcedimiento}">
                                    <fmt:message key="label.proc.noPos.solicitado" />
                                </c:if>
                                <c:if test="${tipoItem == itemInsumo}">
                                    <fmt:message key="label.insumo.noPos.solicitado" />
                                </c:if>
                            </strong>
                        </blockquote>
                        <div class="collapsible-content">
                        
                        <c:if test="${tipoItem == itemInsumo}">
                        <table class="table table-striped table-bordered table-hover">
				            <thead>
				                <tr>
				                    <th style="width: 20%" class="text-center"><small><fmt:message key="label.code" /></small></th>
				                    <th class="text-center"><small><fmt:message key="label.description" /></small></th>
				                </tr>
				            </thead>
				            <tbody>
				                <tr>
				                    <td>${infoInsumo.insumoSolicitado.insumo.codigo}</td>
				                    <td>${infoInsumo.insumoSolicitado.insumo.descripcion}</td>
				                </tr>
				            </tbody>
				        </table>
				        <div class="form-group form-group-sm">
				            <label class="col-lg-2 control-label  control-label-sm" for="cantidadMedicamSolic"><fmt:message key="label.cantidad" /></label>
				            <div class="col-lg-2">
				                <input type="text" name="cantidadMedicamSolic" id="cantidadMedicamSolic" class="form-control input-sm"
				                    value="${infoInsumo.cantidadInsumoSolicitado}" />
				            </div>
				            <label class="col-lg-2 control-label  control-label-sm" for="duracionTratMedicamSolic"><fmt:message
				                    key="label.infoSolicitud.duracionTratamiento" /></label>
				            <div class="col-lg-2">
				                <input type="text" name="duracionTratMedicamSolic" id="duracionTratMedicamSolic" class="form-control input-sm"
				                    value="${infoInsumo.insumoSolicitado.duracion}" />
				            </div>
				        </div>
				        <div class="form-group form-group-sm">
				            <label for="justificMedico" class="col-lg-2 control-label control-label-sm"><fmt:message
				                    key="label.infoSolicitud.justificacionMedico" />:</label>
				
				            <div class="col-lg-9">
				                <textarea name="justificMedico" id="justificMedico" class="form-control">${infoInsumo.justificacionMedico}</textarea>
				            </div>
				        </div>
                        </c:if>
                            <c:if test="${tipoItem == itemMedicamento}">
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-2 control-label  control-label-sm" for="codigoMedicamento"><fmt:message
                                            key="label.code" /></label>
                                    <div class="col-lg-1">
                                        <input type="text" name="codigoMedicamento" id="codigoMedicamento"
                                            class="form-control input-sm" readonly="readonly"
                                            value="${infoMedicamento.medicamentoSolicitado.medicamento.codigo}" />
                                    </div>
                                </div>    
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-2 control-label  control-label-sm" for="descripcionMedicamento"><fmt:message
                                            key="label.description" /></label>
                                    <div class="col-lg-9">
                                        <input type="text" name="descripcionMedicamento" id="descripcionMedicamento"
                                            class="form-control input-sm" readonly="readonly"
                                            value="${infoMedicamento.medicamentoSolicitado.medicamento.descripcion}" />
                                    </div>
                                </div>    
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-2 control-label  control-label-sm" for="cantidadMedicamSolic"><fmt:message
                                            key="label.cantidad" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="cantidadMedicamSolic" id="cantidadMedicamSolic"
                                            class="form-control input-sm" readonly="readonly"
                                            value="${infoMedicamento.cantidadMedicamentoSolicitado}" />
                                    </div>
                                    <label class="col-lg-2 control-label control-label-sm" for="dosisMedicamSolic"><fmt:message
                                            key="label.dosis" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="dosisMedicamSolic" id="dosisMedicamSolic" class="form-control input-sm"
                                            readonly="readonly" value="${infoMedicamento.medicamentoSolicitado.dosis}" />
                                    </div>
                                    <label class="col-lg-2 control-label control-label-sm" for="frecuenciaMedicamSolic"><fmt:message
                                            key="label.frecuencia" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="frecuenciaMedicamSolic" id="frecuenciaMedicamSolic"
                                            class="form-control input-sm" readonly="readonly"
                                            value="${infoMedicamento.medicamentoSolicitado.frecuencia} ${infoMedicamento.medicamentoSolicitado.tipoFrecuencia}" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label class="col-lg-2 control-label  control-label-sm" for="duracionTratMedicamSolic"><fmt:message
                                            key="label.duracionTratamiento" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="duracionTratMedicamSolic" readonly="readonly" id="duracionTratMedicamSolic"
                                            class="form-control input-sm" value="${infoMedicamento.medicamentoSolicitado.duracion}" />
                                    </div>
                                    <label class="col-lg-2 control-label control-label-sm" for="viaAdministracion"><fmt:message
                                            key="label.viaAdministracion" /></label>
                                    <div class="col-lg-2">
                                        <input type="text" name="frecuenciaMedicamSolic" id="viaAdministracion"
                                            class="form-control input-sm" readonly="readonly"
                                            value="${infoMedicamento.medicamentoSolicitado.viaAdministracion}" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="posologia" class="col-lg-2 control-label control-label-sm"><fmt:message
                                            key="label.posologia" />:</label>

                                    <div class="col-lg-9">
                                        <textarea name="posologia" id="posologia" class="form-control" readonly="readonly">${infoMedicamento.posologia}</textarea>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="efectosAdversos" class="col-lg-2 control-label control-label-sm"><fmt:message
                                            key="label.medicamento.nopos.efectosAdversos.mini" />:</label>

                                    <div class="col-lg-9">
                                        <textarea name="efectosAdversos" id="efectosAdversos" readonly="readonly" class="form-control">${infoMedicamento.medicamentoSolicitado.efectosAdversos}</textarea>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${tipoItem == itemProcedimiento}">
                                 <div class="form-group form-group-sm">
                                    <label for="codigoInput" class="col-lg-2 control-label control-label-sm"><small><fmt:message
                                                key="label.code" /></small></label>
                                    <div class="col-lg-2">
                                        <input type="text" id="codigoInput" name="codigoProcSolicitado" class="form-control input-sm"
                                            value="${infoProcedimiento.procedimientoSolicitado.codigo}" />
                                    </div>
                                 </div>
                                 <div class="form-group form-group-sm">
                                    <label for="descripcionInput" class="col-lg-2 control-label control-label-sm"><small><fmt:message
                                                key="label.descripcion" /></small></label>
                                    <div class="col-lg-9">
                                        <input type="text" id="descripcionInput" name="descripcionProcSolicitado" class="form-control input-sm"
                                            value="${infoProcedimiento.procedimientoSolicitado.descripcion}" />
                                    </div>
                                 </div>
                                 <div class="form-group form-group-sm">
                                    <label for="especialidadInput" class="col-lg-2 control-label control-label-sm"><small><fmt:message
                                                key="label.medical.speciality" /></small></label>
                                    <div class="col-lg-3">
                                        <input type="text" id="especialidadInput" name="especialidadProcSolicitado" class="form-control input-sm"
                                            value="${infoProcedimiento.especialidadProcSolicitado}" />
                                    </div>
                                 </div>
                                 
                                <div class="form-group form-group-sm">
                                    <label for="lateralidadInput" class="col-lg-2 control-label control-label-sm"><small><fmt:message
                                                key="label.lateralidad" /></small></label>
                                    <div class="col-lg-3">
                                        <input type="text" id="lateralidadInput" name="lateralidad" class="form-control input-sm"
                                            value="${infoProcedimiento.lateralidad}" />
                                    </div>
                                    <label for="objetivoJustifInput" class="col-lg-2 control-label control-label-sm"><small><fmt:message
                                                key="label.objetivo" /></small></label>
                                    <div class="col-lg-3">
                                        <input type="text" id="objetivoJustifInput" name="objetivoJustif" class="form-control input-sm"
                                            value="${infoProcedimiento.objetivoSolicitud}" />
                                    </div>
                                    <label for="cantidadInput" class="col-lg-1 control-label control-label-sm"><small><fmt:message
                                                key="label.cantidad" /></small></label>
                                    <div class="col-lg-1">
                                        <input type="text" name="cantidadInput" id="cantidadInput" class="form-control input-sm"
                                            value="${infoProcedimiento.cantidadSolicitada}" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="tipoPrestacionInput" class="col-lg-2 control-label control-label-sm"><small><fmt:message
                                                key="label.tipoPrestacion" /></small></label>
                                    <div class="col-lg-3">
                                        <input type="text" id="tipoPrestacionInput" name="tipoPrestacionInput" class="form-control input-sm"
                                            value="${infoProcedimiento.tipoPrestacion}" />
                                    </div>
                                    <label for="origenRepetInput" class="col-lg-2 control-label control-label-sm"><small><fmt:message
                                                key="label.origenRepeticion" /></small></label>
                                    <div class="col-lg-3">
                                        <input type="text" id="origenRepetInput" name="origenRepetInput" class="form-control input-sm"
                                            value="${infoProcedimiento.origenRepeticion}" />
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="indicacionesMedico" class="col-lg-2 control-label control-label-sm"><fmt:message
                                            key="label.indicaciones" />:</label>

                                    <div class="col-lg-9">
                                        <textarea name="indicacionesMedico" id="indicacionesMedico" class="form-control">${infoProcedimiento.indicaciones}</textarea>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </fieldset>
                <jsp:include page="direccionamientoIps.jsp">
                    <jsp:param value="${bandejasParam.editableRedireccion}" name="editable" />
                </jsp:include>

                <jsp:include page="infoDevolucionesCTC.jsp">
                    <jsp:param value="${bandejasParam.caseDevoluciones}" name="casoDevolucion" />
                    <jsp:param value="${TIPO_ITEM}" name="tipoItem" />
                </jsp:include>
                <jsp:include page="conceptoCTCNac_Reg.jsp">
                    <jsp:param value="${disableInput}" name="disableInput" />
                    <jsp:param value="${TIPO_ITEM}" name="tipoItem" />
                </jsp:include>
            </form>
        </div>
    </div>
</div>