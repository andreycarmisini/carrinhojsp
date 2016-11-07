<%@page import="br.com.sematec.carrinho.modelo.Produto"%>
<%@page import="br.com.sematec.carrinho.modelo.Carrinho"%>
<%@page import="java.util.List"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="mensagem.titulo" /></title>
</head>
<body>
	<script type="text/javascript">
		function isNumberKey(evt){
		    var charCode = (evt.which) ? evt.which : evt.keyCode
		    return !(charCode > 31 && (charCode < 48 || charCode > 57));
		}
	</script>

	<c:import url="../cabecalho.jsp" />
	<h1><fmt:message key="mensagem.produtos" /></h1>
	<a href="<c:url value="/produto?funcao=carrinho" />">
		<h2><fmt:message key="mensagem.abrircarrinho"/> = <fmt:formatNumber value="${carrinhoTotal}" type="currency" /></h2>
	</a>
	
	<table width="100%">
		<tr>
			<td width="20%"><fmt:message key="mensagem.nome" /></td>
			<td><fmt:message key="mensagem.imagem" /></td>
			<td><fmt:message key="mensagem.preco" /></td>
			<td><fmt:message key="mensagem.quantidade" /></td>
			<td width="20%"><fmt:message key="mensagem.adicionar" /></td>
<!-- 			<td width="20%">Remover?</td> -->
		</tr>
		<c:forEach var="p" items="${produtoList}" varStatus="st">
			<tr id="produto${p.id}">
<%-- 				<td>${st.count}</td> --%>
				<form action="item" method="post">
					<c:set var="stringNome2" value="${fn:toUpperCase(p.nome)}" />
					<td>${stringNome2}</td>
					<td><img style="height:100px;width:100px;" src="<c:url value="${p.imagem}"/>" /></td>
					<td><fmt:formatNumber value="${p.preco}" type="currency" /></td>
					<td><input type="text" id="quantidade${p.getId()}" name="quantidade" size="3" maxlength="5" onkeypress="return isNumberKey(event);" value="" /> </td>
							<input type="hidden" value="${p.getId()}" name="id" />
					<td><input type="submit" value="<fmt:message key="mensagem.adicionar" />" /></td>
				</form>
			</tr>
		</c:forEach>
	</table>
	<c:import url="../rodape.jsp" />
</body>
</html>
