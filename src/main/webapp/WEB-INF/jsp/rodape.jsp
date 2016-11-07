<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% if (session.getAttribute("usuarioLogado") != null) { %>
	<hr/>
    <a href="<c:url value="/usuario?funcao=lista" />"><fmt:message key="mensagem.listaUsuarios" /></a>
<hr/>
<form action="logout" method="post">
	 <input type="submit" value="Logout" />
</form>
<% } %>
<hr/>
Carrinho de compras! Andrey
<hr/>