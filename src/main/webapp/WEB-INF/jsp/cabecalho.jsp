<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="br.com.sematec.carrinho.modelo.Usuario"%>
<h2>
	<fmt:message key="mensagem.bemvindo" /><% Usuario u = (Usuario)session.getAttribute("usuarioLogado"); if (u != null ) out.print(", Usuário logado: "+u.getNome()); %>
</h2>
