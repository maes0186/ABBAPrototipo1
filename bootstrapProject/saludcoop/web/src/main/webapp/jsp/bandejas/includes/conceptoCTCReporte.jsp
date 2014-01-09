<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form class="form-horizontal" id="formFiltroPrincipal" method="post">
  <fmt:message key='label.infoSolicitud.descResHistClin'
    var="descResHistClinVar" />
  <fmt:message key='label.solicitud.informacionSolicitud'
    var="informacionSolicitud" />
  <fmt:message key='label.infoSolicitud.conceptoRegional'
    var="conceptoCTCRegional" />
  <fmt:message key='label.solicitud.conceptoCTCDepartamental'
    var="conceptoCTCDepartamental" />


  <fieldset>
    <blockquote class="col-lg-12 collapsible-header"
      style="padding: 2px; font-size: 20px;">
      <strong>${conceptoCTCRegional}</strong>
    </blockquote>

    <div class="form-group form-group-sm">
      <label class="col-lg-2 control-label control-label-sm"><fmt:message
          key="label.address.state" /></label>
      <div class="col-lg-4 text-right">
        <input type="text" name="periodoAprobado" id="periodoAprobado"
          placeholder="<fmt:message key="label.address.state"/>"
          class="form-control input-sm"
          value="${conceptoCTCVOReporte.estado}" disabled="disabled" />
      </div>
    </div>

    <div id="bandejaTabContent" class="tab-content">
      <div class="tab-pane fade active in" id="filtroPrincipal">
        <div class="form-group form-group-sm">
          <label class="col-lg-2 control-label control-label-sm"><fmt:message
              key="label.infoSolicitud.periodoAprobado" /></label>
          <div class="col-lg-4 text-right">
            <input type="text" name="periodoAprobado"
              id="periodoAprobado"
              placeholder="<fmt:message key="label.infoSolicitud.periodoAprobado"/>"
              class="form-control input-sm"
              value="${conceptoCTCVOReporte.periodoAprobado}"
              disabled="disabled" />
          </div>
          <label class="col-lg-2 control-label control-label-sm"><fmt:message
              key="label.infoSolicitud.unidadesAprobadas" /></label>

          <div class="col-lg-4 text-right">
            <input type="text" name="unidadesAprobadas"
              id="unidadesAprobadas"
              placeholder="<fmt:message key="label.infoSolicitud.unidadesAprobadas"/>"
              class="form-control input-sm"
              value="${conceptoCTCVOReporte.unidadesAprobadas}"
              disabled="disabled" />
          </div>
        </div>
        <div class="form-group form-group-sm">
          <label class="col-lg-2 control-label control-label-sm"><fmt:message
              key="label.infoSolicitud.numeroEntradas" /></label>
          <div class="col-lg-4 text-right">
            <input type="text" name="numeroEntradas" id="numeroEntradas"
              placeholder="<fmt:message key="label.infoSolicitud.numeroEntradas"/>"
              class="form-control input-sm"
              value="${conceptoCTCVOReporte.numeroEntregas}"
              disabled="disabled" />
          </div>
        </div>
        <div class="form-group form-group-sm">
          <label class="col-lg-2 control-label control-label-sm"><fmt:message
              key="label.infoSolicitud.diasPeriodo" /></label>
          <div class="col-lg-4 text-right">
            <input type="text" name="diasPeriodo" id="diasPeriodo"
              placeholder="<fmt:message key="label.infoSolicitud.diasPeriodo"/>"
              class="form-control input-sm"
              value="${conceptoCTCVOReporte.diasPeriodo}"
              disabled="disabled" />
          </div>
          <label class="col-lg-2 control-label control-label-sm"><fmt:message
              key="label.infoSolicitud.dosisAprobada" /></label>
          <div class="col-lg-4">
            <input type="text" name="dosisAprobada" id="dosisAprobada"
              placeholder="<fmt:message key="label.infoSolicitud.dosisAprobada"/>"
              class="form-control input-sm"
              value="${conceptoCTCVOReporte.dosisAprobada}"
              disabled="disabled" />
          </div>
        </div>
        <div class="form-group form-group-sm text-left">
          <label class="col-lg-12"><fmt:message
              key="label.nopos.justificacion" /></label>
          <div class="col-lg-12">
            <textarea id="justificacion" class="form-control" rows="3"
              disabled="disabled">
            <c:out value="${conceptoCTCVOReporte.justificacion}"></c:out>
            </textarea>
          </div>
        </div>
      </div>

    </div>
  </fieldset>
</form>


