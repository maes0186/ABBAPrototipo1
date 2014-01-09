<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:message key='label.variacion.ipc' var="IPC" />
<fmt:message key='label.year' var="ANIO" />
<fmt:message key='label.historico.ipc' var="TITULO" />
<fmt:message key="label.message.noValoresIPC" var="NO_VALORES"/>
<fmt:message key="label.button.agregar" var="AGREGAR"/>
<fmt:message key="label.button.eliminar" var="ELIMINAR"/>
<fmt:message key="label.button.editar" var="EDITAR"/>
<fmt:message key="label.button.volver" var="VOLVER"/>
<fmt:message key="label.message.yaExisteIPC" var="YA_EXISTE"/>
<fmt:message key="label.title.eliminarIPC" var="CONFIRM_ELIMINAR"/>
<fmt:message key="label.message.anio.digitos" var="DIGITOS_ANIO"/>

<c:set var="webContext" value="${pageContext.request.contextPath}" />

<jsp:include page="../includes/header.jsp">
	<jsp:param name="includeMenu" value="true" />
</jsp:include>

<div class="well">
    <form class="form-horizontal" id="parametrosForm">
       <blockquote class="col-lg-12 collapsible-header" style="padding: 2px; font-size: 20px;">
            <strong>
                ${TITULO}
            </strong>
        </blockquote>
        
         <table class="table subtable">
            <thead>
                <tr>
                    <th>${IPC}</th>
                    <th>${ANIO}</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${historialIPC != null && historialIPC.size() > 0}">
                    <c:forEach items="${historialIPC}" var="val">
                        <tr>
                            <td>${val.valor}</td>
                            <td>${val.anio}</td>
                            <td>
                                <a style="cursor:pointer" id="edit" title="${EDITAR}" onclick="editarRegistro('${val.id}')">
                                    <i class="btn btn-success btn-sm icon-edit"></i>
                                </a>
                            </td>
                            <td>
                                <a style="cursor:pointer" id="delete" title="${ELIMINAR}" onclick="eliminarRegistro('${val.id}')">
                                    <i class="btn btn-danger btn-sm icon-remove-circle"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${historialIPC == null || historialIPC.size() == 0}">
                    <tr>
                        <td colspan="2">${NO_VALORES}</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <div class=" col-lg-12">
            <div class="col-lg-offset-8 col-lg-2">
                <button type="button" id="aceptar" class="btn btn-info btn-sm"  data-toggle="modal" data-target="#agregarModal">
                    ${AGREGAR}
                    <i class="icon-plus"></i>
                </button>
            </div>
            <div class="col-lg-2">
                <button type="button" id="volver" class="btn btn-danger btn-sm" onclick="window.history.back();">
                    ${VOLVER}
                </button>
            </div>
        </div>
    </form>
</div>
 
<div class="modal fade" id="agregarModal" tabindex="-1"
    role="dialog" aria-labelledby="agregarLabel" aria-hidden="true">
    <form id="agregarIPCForm" class="form-horizontal">
        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="cerrarAgregarModal(this);">&times;</button>
                <h4 class="modal-title">
                    ${AGREGAR} ${IPC}
                </h4>
        </div>
        <div class="modal-body">      
            <div class="container">      
                <input type="hidden" class="ignore" value="" id="registroAgregarId" name="registroAgregarId" />   
                <div class="row">
                    <div class="col-lg-3">
                        <label for="valor"
                            class="control-label control-label-sm text-right ">${IPC}</label>
                    </div>
                    <div class="col-lg-9">
                        <input class="form-control input-sm" id="valor"
                            name="valor" value="" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3">
                        <label for="anio"
                            class="control-label control-label-sm text-right ">${ANIO}</label>
                    </div>
                    <div class="col-lg-9">
                        <input class="form-control input-sm" id="anio"
                            name="anio" value="" type="number"/>
                    </div>
                </div>         
            </div>
            <div id="agregar-messages" class="col-lg-12"></div>
        </div>
        <div class="modal-footer">
            <button type="submit" id="btnAgregar" class="btn btn-success">
                <fmt:message key="label.button.aceptar" />
            </button>
    
            <button type="button" id="btnCerrar" class="btn btn-default" data-dismiss="modal" onclick="cerrarAgregarModal(this);">
                <fmt:message key="label.button.cerrar" />
            </button>
        </div>
    </form>
</div>
       
<div class="modal fade" id="editarModal" tabindex="-1"
    role="dialog" aria-labelledby="editarLabel" aria-hidden="true">
    <form id="editarIPCForm" class="form-horizontal">
        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="cerrarEditarModal(this);">&times;</button>
                <h4 class="modal-title">
                    ${EDITAR} ${IPC}
                </h4>
        </div>
        <div class="modal-body">   
          <div class="container"> 
                <input type="hidden" value="" class="ignore" id="registroEditarId" name="registroEditarId" />       
                <div class="row">
                    <div class="col-lg-3">
                        <label for="valorEditar"
                            class="control-label control-label-sm text-right ">${IPC}</label>
                    </div>
                    <div class="col-lg-9">
                        <input class="form-control input-sm" id="valorEditar"
                            name="valorEditar" value="" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3">
                        <label for="anioEditar"
                            class="control-label control-label-sm text-right ">${ANIO}</label>
                    </div>
                    <div class="col-lg-9">
                        <input class="form-control input-sm" id="anioEditar"
                            name="anioEditar" value="" type="number"/>
                    </div>
                </div>         
            </div>
            <div id="editar-messages" class="col-lg-12"></div>          
        </div>
        <div class="modal-footer">
            <button type="submit" id="btnEditar" class="btn btn-success">
                <fmt:message key="label.button.aceptar" />
            </button>
    
            <button type="button" id="btnCerrarEditar" class="btn btn-default" data-dismiss="modal" onclick="cerrarEditarModal(this);">
                <fmt:message key="label.button.cerrar" />
            </button>
        </div>
    </form>
</div>

<div class="modal fade" id="eliminarModal" tabindex="-1" role="dialog" aria-labelledby="eliminarLabel" aria-hidden="true">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">&times;</a>
        <h4 class="modal-title">${ELIMINAR} ${IPC}</h4>
    </div>

    <div class="modal-body">
        <p>${CONFIRM_ELIMINAR}</p>
    </div>

    <div class="modal-footer">
        <button type="submit" id="btnEliminar" class="btn btn-success">
            <fmt:message key="label.button.aceptar" />
        </button>
        <button type="button" id="btnCerrar" class="btn btn-default" data-dismiss="modal" onclick="">
            <fmt:message key="label.button.cerrar" />
        </button>
    </div>
</div>

<script>
var identificador;

$(document).ready(function() {
   
    $('#editarIPCForm').validate({
        onfocusout: false,
        focusInvalid: false,
        focusCleanup: false,
        onkeyup: false,
        onclick: false,
        ignore: ".ignore", //se ignoran los campos que tengan esta clase
        rules: { //Reglas para cada campo
            valorEditar: { required: true, number: true },
            anioEditar: { required: true, number: true, minlength: 4, maxlength: 4 }
        },
        messages: {//Mensajes de error formateados para cada uno de los campos
            valorEditar: {required:'<fmt:message key="validation.required"><fmt:param value="${IPC}"/></fmt:message>', 
                    number:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${IPC}"/></fmt:message>'},
            anioEditar: {required:'<fmt:message key="validation.required"><fmt:param value="${ANIO}"/></fmt:message>', 
                   number:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${ANIO}"/></fmt:message>', 
                   minlength:'${DIGITOS_ANIO}', 
                   maxlength:'${DIGITOS_ANIO}'}     
        }
    });
    
    $('#agregarIPCForm').validate({
        onfocusout: false,
        focusInvalid: false,
        focusCleanup: false,
        onkeyup: false,
        onclick: false,
        ignore: ".ignore", //se ignoran los campos que tengan esta clase
        rules: { //Reglas para cada campo
            valor: { required: true, number: true },
            anio: { required: true, number: true, minlength: 4, maxlength: 4 }
        },
        messages: {//Mensajes de error formateados para cada uno de los campos
            valor: {required:'<fmt:message key="validation.required"><fmt:param value="${IPC}"/></fmt:message>', 
                    number:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${IPC}"/></fmt:message>'},
            anio: {required:'<fmt:message key="validation.required"><fmt:param value="${ANIO}"/></fmt:message>', 
                   number:'<fmt:message key="validation.restriction.digitsOnly"><fmt:param value="${ANIO}"/></fmt:message>', 
                   minlength:'${DIGITOS_ANIO}', 
                   maxlength:'${DIGITOS_ANIO}'}     
        }
    });
    
    $("#btnAgregar").click(function() {
        $("#agregar-messages").empty();
        if($('#agregarIPCForm').valid()){    
           var formData = $('#agregarIPCForm').serializeArray();
           $.ajax({
                url : "${webContext}/admin/agregarIPC",
                data : formData,
                type : 'POST',
                async : false, 
                dataType : "json",
                complete : function(data) {
                    
                    var respuesta = data.responseText;
                    if(respuesta == 'OK'){
                        
                        $('#agregarIPCForm').reset();
                        $("#agregarModal").modal('hide');   
                        window.location = "${webContext}/admin/configurarIPC";
                        
                    } else if (respuesta == 'Ya existe'){
                        
                        appendErrorMessage($('#agregar-messages'), '${YA_EXISTE}');                     
                    
                    }
                }
            });
           return false;            
        }
        
    });
    
    $("#btnEliminar").click(function(){
        window.location = "${webContext}/admin/eliminarIPC/"+identificador;      
        
    });
    
    $("#btnEditar").click(function(){
        
        $("#editar-messages").empty();
        if($('#editarIPCForm').valid()){ 
           var formData = $('#editarIPCForm').serializeArray();
           $.ajax({
                url : "${webContext}/admin/editarIPC",
                data : formData,
                type : 'POST',
                async : false, 
                dataType : "json",
                complete : function(data) {
                    
                    var respuesta = data.responseText;
                    if(respuesta == 'OK'){
                        
                    $('#editarIPCForm').reset();
                    $("#editarModal").modal('hide');    
                    window.location = "${webContext}/admin/configurarIPC";
                    
                    } else if (respuesta == 'Ya existe'){
                        
                        appendErrorMessage($('#editar-messages'), '${YA_EXISTE}');                      
                    
                    }
                }
            }); 
           
           return false;
        }
    });
        
});
    
function cerrarEditarModal(button){
    $("#editar-messages").empty();
    button.form.reset();
} 

function cerrarAgregarModal(button){
    $("#agregar-messages").empty();
    button.form.reset();
}

function eliminarRegistro(id){
    identificador = id;
    $("#eliminarModal").modal('show');
}

function editarRegistro(id){
    identificador = id;                      
    $.ajax({
        type : 'POST',
        async : false,
        url : "${webContext}/admin/cargarIPCEditar/"+id,
        data : "",
        success : function(response) {
            var ipc = $.parseJSON(response);
	        $("#registroEditarId").val(ipc.id);
	        $("#valorEditar").val(ipc.valor);
	        $("#anioEditar").val(ipc.anio);	        
	        $("#editarModal").modal('show');
        }
        
    });
    return false;
 }


</script>

<jsp:include page="../includes/footer.jsp" />