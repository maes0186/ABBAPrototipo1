<%@ tag import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ tag import="net.tanesha.recaptcha.ReCaptchaFactory" %>
 
<script type="text/javascript">var RecaptchaOptions = {theme : 'clean'};</script> 
<%
    ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptcha("6LfxoOsSAAAAAMI4Y9u4wQBqSRW5mjDhbEiYeFD2", "6LfxoOsSAAAAAHMxglfLnUGyWnuzatboC7-KDTdY", false);
    out.print(reCaptcha.createRecaptchaHtml(null, null));
%>