<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="webContext" value="${pageContext.request.contextPath}" />
<fmt:message key='label.select' var="SELECCIONE_LABEL" />

<div class="modal fade" id="historialSolicitudes" style="display: none;"
	data-width="100%" class="form-group">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">Historial de Solicitudes</h4>
		</div>
		<div class="modal-body">
			<div id="messagesHistorial"></div>
            <div class="well form-horizontal">
            	<form id="fieldSetFiltroHistorial">
                <fieldset>
                    <blockquote class="col-lg-12" style="padding: 2px; font-size: 20px;">
                        <strong><fmt:message key="label.bandeja.consultaSolicitudes" /></strong>
                    </blockquote>
                    <div id="filtrosContent" class="tab-content">
                        <div class="form-group form-group-sm collapsible-content">
                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="numeroSolicitudSearch"><fmt:message
                                    key="label.solicitud.numero" /></label>
                            <div class=" col-lg-3 col-sm-3 col-xs-6">
                                <input class="form-control input-sm" id="numeroSolicitudSearch" name="numeroSolicitud"
                                    value=""
                                    placeholder="<fmt:message
                                    key="label.solicitud.numero" />">
                            </div>
                            <label for="regionalSearch" class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"><fmt:message
                                        key="label.regional" /></label>
                                
                                <div class="col-lg-3 col-sm-3 col-xs-6">
                                    <form:select path="regionales" name="regional" id="regionalSearch" class="form-control input-sm">
                                        <form:option value="" label="${SELECCIONE_LABEL}" />
                                        <form:options items="${regionales}" itemLabel="descripcion" itemValue="id" />
                                    </form:select>
                                </div>
                        </div>
                        <div class="form-group form-group-sm collapsible-content">
                            <label for="fechaDesde" class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"><fmt:message
                                    key="label.date.from" /></label>
                            <div id="fechaDesdeButton" class="col-lg-3 input-group date"   data-date-format="dd-mm-yyyy" data-date="">
								<input type="text" name="fechaDesde" class="form-control" id="fechaDesde" readonly/>
								<span class="input-group-addon"><i class="icon-calendar"></i></span>
							</div>
                            <label for="fechaHasta" class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6"><fmt:message
                                    key="label.date.thru" /></label>
                            <div id="fechaHastaButton" class="col-lg-3 input-group date"   data-date-format="dd-mm-yyyy" data-date="">
								<input type="text" name="fechaHasta" class="form-control" id="fechaHasta" readonly/>
								<span class="input-group-addon"><i class="icon-calendar"></i></span>
							</div>
                        </div>
                        <div class="form-group form-group-sm collapsible-content">
                            <label class="control-label control-label-sm text-right col-lg-2 col-sm-2 col-xs-6" for="estadoSolicitudSearch"><fmt:message
                                    key="label.solicitud.estado" /></label>
                            <div class=" col-lg-3 col-sm-3 col-xs-6">
                                 <form:select path="estadosAutorizacion" name="estadoSolicitud" id="estadoSolicitudSearch" class="form-control input-sm">
                                     <form:option value="" label="${SELECCIONE_LABEL}" />
                                     <form:options items="${estadosAutorizacion}" itemLabel="descripcion" itemValue="id" />
                                 </form:select>
                            </div>
                        </div>
                        <br />
                        <div class="form-group text-right" style="">
                            <div class=" col-lg-12">
                                <button type="button" id="searchHistorial" class="btn btn-info btn-sm">
                                    <fmt:message key="label.button.buscar" />
                                    <i class="icon-search"></i>
                                </button>
                                <button type="button" id="cleanHistorial" class="btn btn-warning btn-sm" onclick="this.form.reset();">
                                    <fmt:message key="label.button.limpiar" />
                                    <i class="icon-eraser"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                
	               <input type="hidden" name="actualPage" id="actualPage" value="1" />
	               <input type="hidden" name="tipoDocumento" value="${infoGeneral.tipoIdentificacionUsuarioId}" />
	               <input type="hidden" name="numeroDocumento" value="${infoGeneral.identificacionUsuario}" />
				</fieldset>
				</form>
            </div>
		</div>
		
		<div class="row" id="bandejaResult">
	        <div class=" col-lg-12">
	            <div class="well form-horizontal">
	                <div id="bandejaContent" class="tab-content"></div>
	            </div>
	        </div>
   		</div>
   		<div class="row" id="downloadResult">
	        <div class="col-sm-offset-5 col-sm-2">
	            <div>
	               	<button type="button" id="downloadHistorialBtn" class="btn btn-warning btn-sm">
                          Descargar Historial
                    </button>
	            </div>
	        </div>
   		</div>

	</div>
</div>
<!-- <script type="text/javascript" -->
<%--   src="${webContext}/resources/js/auditor/funTablaHistorial.js"></script> --%>
<script src="${webContext}/resources/js/bandejasTable.js"></script>
<script>
    $(document).ready(function() {    
        selectDefaultValues();
        setContainer($('#bandejaContent'));
        
        
        var startDate ="" , endDate = "" ;
		$('#fechaDesdeButton')
		    .datepicker()
		    .on('changeDate', function(ev){
		        if (endDate != "" && endDate!=undefined && ev.date.valueOf() > endDate.valueOf()){
		            $('#alert').show().find('strong').text('<fmt:message key="validation.date.range.notGreater"/>');
		            startDate = "";
		            $('#fechaDesde').val("");
		        } else {
		            $('#alert').hide();
		            startDate = new Date(ev.date);
		        }
		        $('#fechaDesdeButton').datepicker('hide');
		    });
		
		$('#fechaHastaButton')
		    .datepicker()
		    .on('changeDate', function(ev){
		        if (startDate != "" && startDate!=undefined && ev.date.valueOf() < startDate.valueOf()){
		            $('#alert').show().find('strong').text('<fmt:message key="validation.date.range.notLess"/>');
		            endDate = "";
		            $('#fechaHasta').val("");
		        } else {
		            $('#alert').hide();
		            endDate = new Date(ev.date);
		        }
		        $('#fechaHastaButton').datepicker('hide');
		    });

        $('#showCompleteHistory').click(function(event) {
            $('#messages').empty();
            updateSolicitudes(1);
        });
        
        
        $('#searchHistorial').click(function(event) {
        	updateSolicitudes(1)
        });
        
        $('#downloadHistorialBtn').click(function(event) {
        	var formData = $('#fieldSetFiltroHistorial').serializeArray();
        	location.href="${webContext}/bandejas/downloadHistorial?"+$.param(formData);
        });
        
        

    });
    
	// El parámetro page corresponde a la página de la consulta que se quiere cargar
    function updateSolicitudes(page) {
	    
	    // Se actualiza el valor de la página actual para que lo envíe en la solicitud
    	$('#actualPage').val(page);
	    
    	$("#messagesHistorial").empty();
    	$('#bandejaContent').empty();
    	$('#downloadResult').hide();
    	
        var formData = $('#fieldSetFiltroHistorial').serializeArray();
        
        $.ajax({
       	  dataType: "json",
       	  url: "${webContext}/bandejas/listarHistorial",
       	  data: formData,
       	  success: function(data) {

              if (data.generalErrors.length > 0) {
            	  
            	  if(data.generalErrors[0] === "sizeExceeds"){
            		  $('#downloadResult').show();
            		  $("#bandejaResult").hide();  
            	  }else{
                      createMessages($("#messagesHistorial"), data.generalErrors, 'info', 'icon-info-sign');
                      $('#downloadResult').hide();
                      $("#bandejaResult").hide();  
            	  }
            	  
              } else {
                  $("#bandejaResult").show();
                  createTableHistorialAsync(data.content, data.actualPage, data.totalPages);
              }
          }
       	});
        
    }

    
    /**
    * Selecciona por defecto los valores que se habían aplicado previamente al filtro 
    */
    function selectDefaultValues() {
        try {
            selectDefault("regionalSearch", ${bandejasFilter.regional != null ? bandejasFilter.regional : 'null'});
            selectDefault("estadoSolicitudSearch", ${bandejasFilter.estadoSolicitud != null ? bandejasFilter.estadoSolicitud : 'null'});
        } catch(e) {
            log(e);
        }
    }
</script>