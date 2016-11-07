<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="windows-1256"%>
<%@page import="br.com.sematec.carrinho.modelo.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="mensagem.titulo" /></title>
</head>

<body>
	<h2>
	<fmt:message key="mensagem.bemvindo" />
</h2>
	<form action="login" method="post">
		<fmt:message key="mensagem.login" /> <input type="text" name="login" />
		<br />
		<fmt:message key="mensagem.senha" /> <input type="password" name="senha" />
		<br />
		<input type="submit" value="<fmt:message key="mensagem.autenticar" /> " />
	</form>
	<br /><br />
	<h2><fmt:message key="mensagem.novousuario" /></h2><br />
	<form action="novo" method="post">
		<fmt:message key="mensagem.nome" /> <input type="text" name="nome" />
		<br />
		<fmt:message key="mensagem.login" /> <input type="text" name="login" />
		<br />
		<fmt:message key="mensagem.senha" /> <input type="password" name="senha" />
		<br />
		<input type="submit" value="<fmt:message key="mensagem.criar" /> " />
	</form>
	<%
		if (request.getAttribute("erro") != null) {
	%>
	<h2>
		<%=request.getAttribute("erro")%>
	</h2>
	<%
		}
	%>
</body>
</html>