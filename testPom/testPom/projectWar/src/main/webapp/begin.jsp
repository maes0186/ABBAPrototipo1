<html>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
<head>
<title>Una simple aplicacion JavaServer Faces</title>
</head>
<body>
<h:form>
<h3>Por favor, introduzca su nombre y password.</h3>
<p>
<h:inputText value="#{usuario.usuario}"></h:inputText>
<rich:comboBox id="a"></rich:comboBox>
<h:commandButton value="Aceptar" action="accion"/>

</p>
</h:form>
</body>
</f:view>
</html>