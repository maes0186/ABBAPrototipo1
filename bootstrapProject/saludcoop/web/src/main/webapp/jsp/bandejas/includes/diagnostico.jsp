<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />

<fmt:message key='label.codigo' var="CODIGO_LABEL" />
<fmt:message key='label.descripcion' var="DESCRIPCION_LABEL" />
<fmt:message key='label.codigoCIE10' var="CODIGO_CIE10" />
<fmt:message key='label.name' var="NOMBRE" />

<input type="hidden" id="idDiagnostico" name="idDiagnostico" value="${diagnosticoBandeja.id}" />
<div class="form-group form-group-sm">
    <label for="codigoDiagnostico" class="control-label control-label-sm text-right col-lg-2">${CODIGO_CIE10}</label>
    <div class="col-lg-3">
        <input class="form-control input-sm" id="codigoDiagnostico" name="codigoDiagnostico" placeholder="${CODIGO_CIE10}"
            value="${diagnosticoBandeja.codigo}" readonly="readonly" />
    </div>
</div>
<div class="form-group form-group-sm">
    <label for="descripcionDiganostico" class="control-label control-label-sm text-right col-lg-2">${NOMBRE}</label>
    <div class="col-lg-9">
        <input class="form-control input-sm" id="descripcionDiagnostico" name="descripcionDiagnostico" placeholder="${NOMBRE}"
            value="${diagnosticoBandeja.descripcion}" readonly="readonly" />
    </div>
    <c:if test="${param.editable}">
        <div class="col-lg-1">
            <button type="button" id="btnLoadDiagnosticos" class="btn btn-link btn-sm" data-toggle="modal" href="#diagnosticosModal">
                <i class="icon-fixed-width icon-search"></i>
            </button>
        </div>
    </c:if>
</div>
<br />

<!-- Modal búsqueda de Diagnósticos -->
<div class="modal fade" id="diagnosticosModal" tabindex="-1" role="dialog" aria-labelledby="diagnosticosLabel" aria-hidden="true" data-width="60%">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title"><fmt:message key="label.bandeja.infoDiagnostico" /></h4>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <div class="col-lg-12 text-right">
                    <div class="form-group">
                        <div class="col-lg-12 text-right"">
                            <table id="tablaDiagnosticos" class="table table-hover" style="text-align: left !important;">
                                <thead>
                                    <tr>
                                        <th rowspan="1" class="col-lg-2" colspan="1"><input type="text" name="codDiagnostico" value=""
                                            class="search_init form-control input-sm" /></th>
                                        <th rowspan="1" colspan="1" class="col-lg-6"><input type="text" name="desctDiagnostico" value=""
                                            class="search_init form-control input-sm" /></th>
                                    </tr>
                                    <tr>
                                        <th>${CODIGO_CIE10}</th>
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
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">
                <fmt:message key="label.button.cerrar" />
            </button>
        </div>
    </div>
</div>

<script type="text/javascript" src="${webContext}/resources/js/auditor/funTablaDiagnosticos.js"></script>
<script type="text/javascript" language="javascript" src="${webContext}/resources/js/jdatatable/dataTables.scroller.js"></script>