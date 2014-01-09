<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.conexia.saludcoop.common.util.SystemConstants" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${param.casoDevolucion != 0}">

    <!-- El caso 1 se trata cuando de una solicitud que fue devuelta y respondida por la IPS o línea de frente -->
    <c:if test="${param.casoDevolucion == 1}">
        <fieldset disabled="disabled" style="padding-left: 0px;" class="well">
            <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 16px;">
                <strong><fmt:message key="label.respuestaIPS" /> </strong>
            </blockquote>
            <div class="collapsible-content">
                <div class="col-lg-12">
                    <div class="form-group form-group-sm">
                        <label for="justifiConceptDeptTextArea" class="col-lg-12 control-label control-label-sm" style="text-align: left;">
                            <fmt:message key="label.respuestaIPS" />
                        </label>
                        <div class="col-lg-12">
                            <textarea name="devjustificacionDev" id="justificacionDev" class="form-control input-sm">${infoDevoluciones.justificacion}</textarea>
                        </div>
                    </div>
                </div>
                <br />
            </div>
        </fieldset>
    </c:if>

</c:if>