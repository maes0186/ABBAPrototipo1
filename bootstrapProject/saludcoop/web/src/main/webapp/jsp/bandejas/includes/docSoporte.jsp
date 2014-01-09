<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />

<div class="panel panel-dropzone" style="font-size: 13px">
    <div class="panel-heading">${param.headerMessage}</div>
    <div id="${param.divId}" class="panel-body dropzone"></div>
</div>

<script>
    $(document).ready(function() {
        Dropzone.autoDiscover = false;
        var docAdj = new Dropzone("#${param.divId}", {
            url : "${webContext}/docSoporte/upload/${param.tipoArchivo}",
            dictResponseError : "Error al cargar el archivo",
            addRemoveLinks : true,
			maxFilesize : 3,
  			acceptedFiles : "application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            dictRemoveFile : "<fmt:message key='label.remover' />",
            parallelUploads : 1
        });

        docAdj.on("error", 
					function(file, message){
						file.errorCheck = true; 
						this.removeFile(file);
						newAlert('danger', [message], 3000, 'icon-warning-sign');
					});
        docAdj.on("addedfile", function(file) {
            var a = $(file.previewElement.lastChild);
            a.click(function(){
                var post = $.post( "${webContext}/docSoporte/removeFile/${param.tipoArchivo}/"+file.name+"/");
                post.success = function(data) {
                	docAdj.removeFile(file);
                };
            });
        });
        if(${param.disabled}) {
            docAdj.disable();
        }
    });
</script>