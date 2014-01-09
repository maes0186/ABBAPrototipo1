
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<jsp:include page="./../includes/header.jsp">
	<jsp:param name="includeMenu" value="true" />
</jsp:include>
<c:set var="webContext" value="${pageContext.request.contextPath}" />

	<div class="row">
		<div class="col-lg-offset-1 col-lg-10">
			<div class="well">
				<h3>Ticket</h3>
				<div class="content">
					Transacción realizada con Éxito
				</div>
				<table class="table  table-hover">
					<thead>
						<tr class="row">
							<th class="col-lg-1">Numero Solicitud</th>		
							<th class="col-lg-1">Número Solicitud Item</th>
							<th class="col-lg-1">Código</th>
							<th class="col-lg-4">Descripción</th>
							<th class="col-lg-1">Cant</th>
							<th class="col-lg-2">Numero Autorización</th>
							<th class="col-lg-2">Estado</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${autorizaciones}" var="a">
							<c:forEach items="${a.solicitudItems}" var="sol">
								<tr class="row">
									<td>${ a.numeroSolicitud }</td>
									<td>${ sol.nroItem }</td>
									<c:if test="${not empty sol.solMedicamento}">
										<td>${ sol.solMedicamento.medicamento.codigo }</td>
										<td>${ sol.solMedicamento.medicamento.descripcion }</td>	
									</c:if>								
									<c:if test="${not empty sol.solProcedimiento}">
										<td>${ sol.solProcedimiento.procedimiento.codigo }</td>
										<td>${ sol.solProcedimiento.procedimiento.descripcion }</td>	
									</c:if>
									<c:if test="${not empty sol.solInsumo}">
										<td>${ sol.solInsumo.insumo.codigo }</td>
										<td>${ sol.solInsumo.insumo.descripcion }</td>	
									</c:if>									
									<td>${ sol.cantidad }</td>
									<td><c:if test="${a.estadoAutorizacion.descripcion == 'Autorizada'}">${ a.numero }</c:if></td>
									<td>${ a.estadoAutorizacion.descripcion }</td>
									<td>
										<c:if test="${a.estadoAutorizacion.descripcion == 'Autorizada'}">
											<a href='${webContext}/ticket/obtenerPDF/${a.numero}/1' target="_blank">
												<i class="icon-print"></i>
											</a>
										</c:if>								
									</td>
								</tr>	
								<c:if test="${not empty sol.mensajeValidacion}">
									<tr class="row">
										<td colspan=6 class="text-warning">${ sol.mensajeValidacion }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table> 
			</div>
		</div>
	</div>
<%@include file="./../includes/footer.jsp"%>
