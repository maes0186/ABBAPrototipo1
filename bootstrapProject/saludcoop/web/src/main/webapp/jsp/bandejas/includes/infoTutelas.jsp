<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:message key="label.tutela.numeroSAT" var="NUMERO_TUTELA" />
<fmt:message key="label.tutela.falloSAT" var="NUMERO_FALLO" />

<fieldset ${param.disableInput} class="well">
    <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
        <strong><fmt:message key="label.bandeja.infoTutela" /></strong>
    </blockquote>
    <div class="collapsible-content" style="margin-left: 30px">
        <div class="form-group form-group-sm">
            <div class="checkbox">
                <label class="col-lg-12 col-md-12 col-sm-12 col-xs-12 control-label control-label-sm"> <input type="checkbox"
                    name="esTutelaIntegral" id="esTutelaIntegral" <c:if test="${conceptoTutelas.esTutelaIntegral}">checked</c:if> /> <fmt:message key="label.tutela.esTutelaIntegral" />
                </label>
            </div>
        </div>

        <div class="form-group form-group-sm">

            <div class="checkbox">
                <label class="col-lg-12 col-md-12 col-sm-12 col-xs-12 control-label control-label-sm"> <input type="checkbox"
                    name="excentaCopagos" id="excentaCopagos" <c:if test="${conceptoTutelas.exentaCopago}">checked</c:if> /> <fmt:message key="label.tutela.exentaCopagos" />
                </label>
            </div>
        </div>
        <br />
        <div class="form-group form-group-sm">
            <label for="numeroTutela" class="control-label control-label-sm text-right col-lg-2" style="text-align: left !important">${NUMERO_TUTELA}</label>
            <div class="col-lg-4">
                <input class="form-control input-sm" id="numeroTutela" name="numeroTutela" placeholder="${NUMERO_TUTELA}"
                    value="${conceptoTutelas.numeroTutela}" />
            </div>
            <label for="numeroFallo" class="control-label control-label-sm text-right col-lg-2" style="text-align: left !important">${NUMERO_FALLO}</label>
            <div class="col-lg-4">
                <input class="form-control input-sm" id="numeroFallo" name="numeroFallo" placeholder="${NUMERO_FALLO}"
                    value="${conceptoTutelas.numeroFallo}" />
            </div>
        </div>
        <br />
    </div>
</fieldset>