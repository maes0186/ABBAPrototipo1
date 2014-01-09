<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<c:set var="webContext" value="${pageContext.request.contextPath}"  />

<jsp:include page="./../includes/header.jsp">
	<jsp:param name="includeMenu" value="true" />
</jsp:include>

<%--Templates de codigo html para usar con javascript --%>
<script type="text/template" id="1-chart-template">
<div class="well">
	<blockquote>
		<strong><@= title @></strong>
	</blockquote>
	<fieldset>
		<div class="col-sm-12" id="chart-container-1">
		</div>
	</fieldset>
</div>
</script>
<script type="text/template" id="2-chart-template">
<div class="well">
	<blockquote>
		<strong><@= title @></strong>
	</blockquote>
	<fieldset>
		<div class="col-sm-6" id="chart-container-1">
		</div>
		<div class="col-sm-6 text-center" id="chart-container-2">
			<span>Seleccione una categoría del grafico de tortas</span>
		</div>
	</fieldset>
</div>
</script>
<script type="text/template" id="3-chart-template">
<div class="well">
	<blockquote>
		<strong><@= title @></strong>
	</blockquote>
	<fieldset>
		<div class="col-sm-4" id="chart-container-1">
		</div>
		<div class="col-sm-4" id="chart-container-2">
		</div>
		<div class="col-sm-4" id="chart-container-3">
		</div>
	</fieldset>
</div>
</script>

<div id="indicadores">
</div>

<script src="${webContext}/resources/js/highcharts/chartConfig.js"></script>
<script src="${webContext}/resources/js/highcharts/highcharts.js"></script>
<script src="${webContext}/resources/js/lodash/lodash.min.js"></script>
<script src="${webContext}/resources/js/director/director.min.js"></script>
<script src="${webContext}/resources/js/indicadores/indicadores.js"></script>
<script src="${webContext}/resources/js/indicadores/practicas.js"></script>
<script src="${webContext}/resources/js/indicadores/solicitudes.js"></script>
<script src="${webContext}/resources/js/indicadores/afiliados.js"></script>
<script src="${webContext}/resources/js/indicadores/resolucion.js"></script>
<script src="${webContext}/resources/js/indicadores/montos.js"></script>

<script>
	$('document').ready(function(){
	  Indicadores.startup();
	});
</script>

<%@include file="./../includes/footer.jsp"%>
