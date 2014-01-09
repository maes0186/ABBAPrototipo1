<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:set var="webContext" value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html lang="es">
	<head>
		<title>SaludCoop EPS - by Conexia S.A.</title>
		<link rel="icon" href="${webContext}/resources/images/favicon.ico" type="image/x-icon">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
		<link rel="stylesheet" href="${webContext}/resources/css/font-awesome.css">
		<link rel="stylesheet" href="${webContext}/resources/css/doc.css">
		<link rel="stylesheet" href="${webContext}/resources/css/styles.css" >
		<link rel="stylesheet" href="${webContext}/resources/css/collapsible.css" >
		<link rel="stylesheet" href="${webContext}/resources/css/redmond/jquery-ui-1.8.18.custom.css" >
		<link rel="stylesheet" href="http://jschr.github.io/bootstrap-modal/css/bootstrap-modal-bs3patch.css" >
		<link rel="stylesheet" href="http://jschr.github.io/bootstrap-modal/css/bootstrap-modal.css">
		<script src="${webContext}/resources/js/jquery/jquery-1.7.2.min.js"></script> 
		<script src="${webContext}/resources/js/jquery-ui/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="${webContext}/resources/js/jquery/jquery.validate.min.js"></script> 
		<script src="${webContext}/resources/js/jquery/jquery.alphanumeric.pack.js"></script>
		<script src="${webContext}/resources/js/jquery/additional-methods.js"></script>
		<script src="${webContext}/resources/js/jdatatable/jquery.dataTables.js"></script>
		
		
		
	</head>
	<body>
		<!-- HEADER -->
		<div class="navbar navbar-default navbar-fixed-top" style="background:url(${webContext}/resources/images/bg.jpg) no-repeat; background-color:white">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${webContext}/" style="padding:0"><img src="${webContext}/resources/images/Saludcoop-logo.png"/></a>
				</div>
			</div>
			<!-- div class="user-info"><strong>IPS</strong> - <a href="${webContext}/logout"> Salir </a></div-->
		</div>
		<!-- HEADER -->
		
		<div class="container" style="margin-top:80px"> 
			<div class="row">
				<c:if test="${param.includeMenu}">
					<!--MENU-->
					<%@include file="./../includes/menu.jsp"%>
					<!--MENU-->
					<!--BODY-->
					<div class="col-lg-offset-1 col-lg-9 col-md-offset-1 col-md-9 col-sm-offset-1 col-sm-9 " role="main">
				</c:if>
				<c:if test="${!param.includeMenu}">
					<div class="col-lg-12 col-md-12 col-sm-12 " role="main">
				</c:if>
				
				