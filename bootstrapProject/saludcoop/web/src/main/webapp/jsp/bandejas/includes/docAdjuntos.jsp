<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="webContext" value="${pageContext.request.contextPath}" />

<table class="table table-striped table-bordered table-hover" style="margin-bottom: 0px;">
    <thead>
        <tr>
            <th><fmt:message key="label.documento" /></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${docAdjuntos != null && docAdjuntos.get(param.docAdjuntosName) != null && docAdjuntos.get(param.docAdjuntosName).size() > 0}">
            <c:forEach items="${docAdjuntos.get(param.docAdjuntosName)}" var="doc">
                <tr>
                    <td>${doc.nombreArchivoOriginal}</td>
                    <td style="text-align: center;"><a style="cursor: pointer" onclick="return checkFile('${doc.nombreArchivoOriginal}', '${doc.nombreArchivoServidor}')"><i
                            class="icon-download-alt"></i></a></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${docAdjuntos == null || docAdjuntos.get(param.docAdjuntosName) == null || docAdjuntos.get(param.docAdjuntosName).size() == 0}">
            <tr>
                <td colspan="2"><fmt:message key="label.message.noDocuments" /></td>
            </tr>
        </c:if>
    </tbody>
</table>
<script>
	function checkFile(nombreOriginal, nombreServidor) {
	    this.event.preventDefault();
	    var post = jQuery.post("${webContext}/docSoporte/check/" + nombreServidor, function(data) {
            if (data.content) {
                location.href = "${webContext}/docSoporte/download/" + nombreOriginal + "/" + nombreServidor + "/"
            } else {
                // Se verifican los mensajes de error
                if (data.generalErrors.length > 0) {
                    newAlert('danger', data.generalErrors, 2000, 'icon-warning-sign');
                }
            }
        });
	}
</script>
