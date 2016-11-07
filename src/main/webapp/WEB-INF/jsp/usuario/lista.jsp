<%@page import="br.com.sematec.carrinho.modelo.Produto"%>
<%@page import="java.util.List"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="mensagem.titulo" /></title>
</head>
<body>
	<c:import url="../cabecalho.jsp" />

	<h1><fmt:message key="mensagem.usuarios" /></h1>
	<div id="mensagem"></div>
	<table width="100%">
		<tr>
			<td width="20%"><fmt:message key="mensagem.nome" /></td>
			<td><fmt:message key="mensagem.login" /></td>
			<td><fmt:message key="mensagem.senha" /></td>
		</tr>
		<c:forEach var="p" items="${usuarioList}" varStatus="st">
			<tr id="usuarios${p.id}">
				<td>${p.nome}</td>
				<td>${p.login}</td>
				<td>${p.senha}</td>
			</tr>
		</c:forEach>
	</table>
	<hr/>
	<a href="<c:url value="/produto?funcao=lista" />"><fmt:message key="mensagem.voltar" /></a>
	<c:import url="../rodape.jsp" />
</body>
</html>
