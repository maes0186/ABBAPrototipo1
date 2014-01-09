<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<c:set var="webContext" value="${pageContext.request.contextPath}"  />

	<div class="navbar navbar-default" style="margin-bottom: 8px;">
		<div class="container">
		  <div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
			<a class="navbar-brand" href="#">Indicadores</a>
		 </div>
		  <div class="navbar-collapse collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav">
			  <li><a href="#practicas"> <i class="icon-medkit"></i> Practicas</a></li>
			  <li><a href="#solicitudes"> <i class="icon-gears"></i> Estado de Solicitudes</a></li>
			  <li><a href="#afiliados"> <i class="icon-user"></i> Afiliados</a></li>
			  <li><a href="#resolucion"> <i class="icon-time"></i> Tiempo de Resolución</a></li>
			  <li><a href="#montos"> <i class="icon-dollar"></i> Montos</a></li>			  
			</ul>
			<ul class="nav navbar-nav navbar-right">
			  <li><a href="${webContext}/logout"> <i class="icon-off"></i> Salir</a></li>
			</ul>
		  </div>
		  <!-- /.nav-collapse --> 
		</div>
		<!-- /.container --> 
  </div>
  <!-- /.navbar --> 