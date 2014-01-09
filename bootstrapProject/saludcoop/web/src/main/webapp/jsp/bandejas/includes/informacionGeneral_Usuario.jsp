<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ul class="nav nav-tabs" style="margin-bottom: 15px">
    <li class="active"><a href="#infoGeneral" data-toggle="tab"><fmt:message key="label.title.infoGeneral" /></a></li>
    <li class=""><a href="#infoUsuario" data-toggle="tab"><fmt:message key="label.title.infoUsuario" /></a></li>
</ul>
<div id="infoGeneralTabContent" class="tab-content">
    <div class="tab-pane fade active in" id=infoGeneral>
    	<form class="form-horizontal" id="informacionForm">
	        <div class="form-group form-group-sm">
	            <label class="col-lg-2 control-label control-label-sm" for="numeroSolicitudInput"><fmt:message
	                    key="label.numero.item.solicitud" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="numeroSolicitud" id="numeroSolicitudInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.numeroSolicitud}" />
	            </div>
	        </div>
	        <div class="form-group form-group-sm">
	            <label class="col-lg-2 control-label control-label-sm" for="fechaSolicitudInput"><fmt:message
	                    key="label.fecha.solicitud" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="fechaSolicitud" id="fechaSolicitudInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.fechaSolicitud}" />
	            </div>
	            <label class="col-lg-3 control-label control-label-sm"><fmt:message key="label.solicitud.ambito" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="ambito" id="ambito" disabled="disabled" class="form-control input-sm" value="${infoGeneral.ambito}" />
	            </div>
	        </div>
	        <div class="form-group form-group-sm">
	            <label class="col-lg-2 control-label control-label-sm" for="regionalInput"><fmt:message key="label.regional" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="regional" id="regionalInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.regional}" />
	            </div>
	            <label class="col-lg-3 control-label control-label-sm" for="origenInput"><fmt:message key="label.solicitud.origen" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="origen" id="origenInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.origen}" />
	            </div>
	        </div>
	        <hr />
	        <div class="form-group form-group-sm">
	            <label class="col-lg-2 control-label control-label-sm" for="nombreIpsInput"><fmt:message key="label.solicitud.nombreIPS" /></label>
	            <div class="col-lg-9">
	                <input type="text" name="nombreIps" id="nombreIpsInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.nombreIps}" />
	            </div>
	        </div>
	        <hr />
	        <div class="form-group form-group-sm">
	            <p class="col-lg-12 text-muted">
	                &nbsp;&nbsp;&nbsp;<strong><fmt:message key="label.solicitud.informacionMedico" /></strong>
	            </p>
	            <label class="col-lg-2 control-label control-label-sm" for="tipoIdMedicoInput"><fmt:message
	                    key="label.tipoIdentificacion" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="tipoIdMedico" disabled="disabled" id="tipoIdMedicoInput" class="form-control input-sm"
	                    value="${infoGeneral.tipoIdentificacionMedico}" />
	            </div>
	            <label class="col-lg-3 control-label control-label-sm" for="IdentifMedicoInput"><fmt:message
	                    key="label.numero.identificacion" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="IdentifMedico" disabled="disabled" id="IdentifMedicoInput" class="form-control input-sm"
	                    value="${infoGeneral.identificacionMedico}" />
	            </div>
	        </div>
	        <div class="form-group form-group-sm">
	            <label class="col-lg-2 control-label control-label-sm" for="pNombreMedicoInput"><fmt:message key="label.name.first" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="pNombreMedico" id="pNombreMedicoInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.primerNombreMedico}" />
	            </div>
	            <label class="col-lg-3 control-label control-label-sm" for="sNombreMedicoInput"><fmt:message key="label.name.first2" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="sNombreMedico" id="sNombreMedicoInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.segundoNombreMedico}" />
	            </div>
	        </div>
	        <div class="form-group form-group-sm">
	            <label class="col-lg-2 control-label control-label-sm" for="pApellidoMedicoInput"><fmt:message key="label.name.last3" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="pApellidoMedico" id="pApellidoMedicoInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.primerApellidoMedico}" />
	            </div>
	            <label class="col-lg-3 control-label control-label-sm" for="sApellidoMedico"><fmt:message key="label.name.last2" /></label>
	            <div class="col-lg-3">
	                <input type="text" name="sApellidoMedico" id="sApellidoMedicoInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.segundoApellidoMedico}" />
	            </div>
	        </div>
	        <div class="form-group form-group-sm">
	            <label class="col-lg-2 control-label control-label-sm" for="especialidadMedInput"><fmt:message
	                    key="label.medical.speciality" /></label>
	            <div class="col-lg-9">
	                <input type="text" name="especialidadMed" id="especialidadMedInput" disabled="disabled" class="form-control input-sm"
	                    value="${infoGeneral.especialidadMedico}" />
	            </div>
	        </div>
	        <c:if test="${empty param.bandejaIps}">
	        <hr />
	        <div class="form-group form-group-sm">
	            <div class="col-lg-12">
	                <div class="panel panel-info">
	                    <div class="panel-body">
	                        <div class="form-group form-group-sm">
	                            <label class="col-lg-4 control-label control-label-sm" style="text-align: left;" for="numAutorUltimos30Input"><fmt:message
	                                    key="label.solicitud.ulitmos30" /></label>
	                            <div class="col-lg-2">
	                                <input type="text" name="numAutorUltimos30" id="numAutorUltimos30Input" disabled="disabled"
	                                    class="form-control input-sm" value="${infoGeneral.numeroAutorizaciones}" />
	                            </div>
	                            <label class="col-lg-3 control-label control-label-sm" for="fechaLiberaSolicitudInput"><fmt:message
	                                    key="label.solicitud.fechaLiberacion" /></label>
	                            <div class="col-lg-2">
	                                <input type="text" name="fechaLiberaSolicitud" id="fechaLiberaSolicitudInput" disabled="disabled"
	                                    class="form-control input-sm" value="${infoGeneral.fechaLiberacion}" />
	                            </div>
	                        </div>
	                        <div class="form-group form-group-sm">
	                            <label class="col-lg-4 control-label control-label-sm" style="text-align: left;"
	                                for="solicitudEntregasPendInput"><fmt:message key="label.solicitud.entregasPendientes" /></label>
	                            <div class="col-lg-2">
	                                <input type="text" name="solicitudEntregasPend" id="solicitudEntregasPendInput" disabled="disabled"
	                                    class="form-control input-sm" value="${infoGeneral.solicitudEntregas}" />
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        </c:if>
		</form>
    </div>
	<div class="tab-pane fade" id="infoUsuario">
		<div class="form-group form-group-sm">
			<label for="tipoDocumentoLabel" class="col-lg-2 control-label control-label-sm"><fmt:message
					key="label.tipo.doc" /></label>
			<div class="col-lg-4">
				<input type="text" name="tipoDocumentoInput" id="tipoDocumentoLabel"
					disabled="disabled" value="${infoGeneral.tipoIdentificacionUsuario}" class="form-control input-sm" />
			</div>

			<label for="numeroDocumentoLabel" class="col-lg-2 control-label control-label-sm"><fmt:message
					key="title.identificacion" /></label>
			<div class="col-lg-3">
				<input type="text" name="numeroDocumento" id="numeroDocumentoLabel"
					disabled="disabled" value="${infoGeneral.identificacionUsuario}" class="form-control input-sm" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="nombrecompletoLabel" class="col-lg-2 control-label control-label-sm"><fmt:message
					key="label.nombre.completo" /></label>
			<div class="col-lg-9">
				<input type="text" name="nombrecompletoInput" value="${infoGeneral.nombreCompletoUsuario}"
					id="nombrecompletoLabel" disabled="disabled"
					class="form-control input-sm" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="telefonosLabel" class="col-lg-2 control-label control-label-sm"><fmt:message
					key="label.address.phones" /></label>
			<div class="col-lg-3">
				<input type="text" name="telefonosInput" id="telefonosLabel" value="${infoGeneral.telefonosUsuario}"
					disabled="disabled" class="form-control input-sm" />
			</div>

			<label for="emailLabel" class="col-lg-3 control-label control-label-sm"><fmt:message
					key="label.mail" /></label>
			<div class="col-lg-3">
				<input type="text" name="emailInput" id="emailLabel" value="${infoGeneral.emailUsuario}"
					disabled="disabled" class="form-control input-sm" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="epsLabel" class="col-lg-2 control-label control-label-sm"><fmt:message
					key="label.EPS" /></label>
			<div class="col-lg-3">
				<input type="text" name="epsInput" id="epsLabel" disabled="disabled" value="${infoGeneral.epsUsuario}"
					class="form-control input-sm" />
			</div>

			<label for="nivelIBCLabel" class="col-lg-3 control-label control-label-sm"><fmt:message
					key="label.nivel.ibc" /></label>
			<div class="col-lg-3">
				<input type="text" name="nivelIBCInput" id="nivelIBCLabel" value="${infoGeneral.nivelIBC}"
					disabled="disabled" class="form-control input-sm" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="estadoAfiliacionLabel" class="col-lg-2 control-label control-label-sm"><fmt:message
					key="label.estado.afiliacion" /></label>
			<div class="col-lg-3">
				<input type="text" name="estadoAfiliacionInput" value="${infoGeneral.estadoAfiliacion}"
					id="estadoAfiliacionLabel" disabled="disabled"
					class="form-control input-sm" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="razonEstadoLabel" class="col-lg-2 control-label control-label-sm"><fmt:message
					key="label.razon.estado" /></label>
			<div class="col-lg-9">
				<input type="text" name="razonEstadoInput" id="razonEstadoLabel" value="${infoGeneral.razonEstado}"
					disabled="disabled" class="form-control input-sm" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="edadLabel" class="col-lg-2 control-label control-label-sm"><fmt:message
					key="label.edad" /></label>
			<div class="col-lg-3">
				<input type="text" name="edadInput" id="edadLabel" value="${infoGeneral.edadUsuario}"
					disabled="disabled" class="form-control input-sm" />
			</div>

			<label for="tipoAfiliadoInput" class="col-lg-3 control-label control-label-sm"><fmt:message
					key="label.tipo.afiliado" /></label>
			<div class="col-lg-3">
				<input type="text" name="tipoAfiliado" id="tipoAfiliadoInput" value="${infoGeneral.tipoAfiliadoUsuario}"
					disabled="disabled" class="form-control input-sm" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="semanasEpsLabel" class="col-lg-2 control-label control-label-sm"><fmt:message
					key="label.semanas.eps" /></label>
			<div class="col-lg-3">
				<input type="text" name="semanasEpsInput" id="semanasEpsLabel" value="${infoGeneral.semanasepsUsuario}"
					disabled="disabled" class="form-control input-sm" />
			</div>

			<label for="semanasSgsssLabel" class="col-lg-3 control-label control-label-sm"><fmt:message
					key="label.semanas.sgsss" /></label>
			<div class="col-lg-3">
				<input type="text" name="semanasSgsssInput" id="semanasSgsssLabel" value="${infoGeneral.semanassgsssUsuario}"
					disabled="disabled" class="form-control input-sm" />
			</div>
		</div>

        <div class="row" style="margin-top: 15px">
            <div class="col-lg-12">
                <div class="form-group">
                    <div class="col-lg-4">
                        <a data-toggle="modal" href="#historialSolicitudes" class="btn btn-info btn-sm" id="showCompleteHistory"><fmt:message
                                key="label.historial.completo" /></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="./historialSolicitudes.jsp"%>
<!------->