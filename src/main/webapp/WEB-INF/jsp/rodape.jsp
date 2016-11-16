<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% if (session.getAttribute("usuarioLogado") != null) { %>
	<hr/>
    <a href="<c:url value="/usuario?funcao=lista" />"><fmt:message key="mensagem.listaUsuarios" /></a>
<hr/>
<fmt:message key="mensagem.dataatual" />: <jsp:useBean id="now" class="java.util.Date"/> <fmt:formatDate value="${now}" dateStyle="long"/>
<hr/>
<form action="logout" method="post">
	 <input type="submit" value="Logout" />
</form>
<% } %>
<hr/>
<fmt:message key="mensagem.rodape" />
<hr />