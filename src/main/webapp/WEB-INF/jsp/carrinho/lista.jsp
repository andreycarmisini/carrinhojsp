<%@page import="br.com.sematec.carrinho.modelo.Produto"%>
<%@page import="java.util.List"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="mensagem.titulo" /></title>
</head>
<body>
	<script type="text/javascript">
		function removeCarrinho(id) {
			$.get('carrinho?funcao=remove&id=' + id, function(data) {
				$("#mensagem").text(data);
			});
			$("#carrinho" + id).remove();
			location.reload();
		}
	</script>
	<c:import url="../cabecalho.jsp" />
	<h1><fmt:message key="mensagem.meucarrinho"/></h1>
	<h2><fmt:message key="mensagem.carrinhoatual" /> = <fmt:formatNumber  value="${carrinhoTotal}" type="currency" /></h2>
	<div id="mensagem"></div>
	<table width="100%">
		<tr>
			<td width="20%"><fmt:message key="mensagem.nome" /></td>
			<td><fmt:message key="mensagem.imagem" /></td>
			<td><fmt:message key="mensagem.preco" /></td>
			<td><fmt:message key="mensagem.quantidade" /></td>
			<td><fmt:message key="mensagem.total" /></td>
 			<td width="20%">Remover?</td> 
		</tr>
			<c:forEach var="i" items="${carrinho.getItens()}" varStatus="st1">
				<tr id="carrinho${i.getId()}">
					<c:set var="stringNome" value="${fn:toUpperCase(i.getProduto().getNome())}" />
					<td>${stringNome}</td>
					<td><img style="height:100px;width:100px;" src="<c:url value="${i.getProduto().getImagem()}"/>" /></td>
					<td><fmt:formatNumber value="${i.getProduto().getPreco()}" type="currency" /></td>
					<td>${i.getQuantidade()}</td>
					<td><fmt:formatNumber value="${i.getTotal()}" type="currency" /></td>
					<td><a href="#" onclick="return removeCarrinho(${i.getId()})">Remover</a></td>
				</tr>
			</c:forEach>
	</table>
	<hr/>
	<a href="<c:url value="/produto?funcao=lista" />"><fmt:message key="mensagem.voltar" /></a>
	<c:import url="../rodape.jsp" />
</body>
</html>
