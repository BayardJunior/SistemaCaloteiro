<%@include file="includes.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Caloteiros</title>
</head>
<body>
<c:import url="cabecalho.jsp"></c:import>
	<table border="1">
		<c:forEach var="caloteiro" items="${lista}" varStatus="id">
			<tr bgcolor="#${id.count % 2 == 0 ? 'ff0000' : 'ffffff' }">
				<c:choose>
					<c:when test="${not empty caloteiro.nome}">
						<td>${caloteiro.nome}</td>
					</c:when>
					<c:otherwise>
						<li>Nome não preenchido!</li>
					</c:otherwise>
					<c:when test="${not empty caloteiro.email}">
						<td><a href="mailto:${caloteiro.email}"></a></td>
					</c:when>
					<c:otherwise>
						<li>Email nao preenchido!</li>
					</c:otherwise>
					<c:when test="${not empty caloteiro.devendo}">
						<td>${caloteiro.devendo}</td>
					</c:when>
					<c:otherwise>
						<li>Divida não preenchida!</li>
					</c:otherwise>
					<c:when test="${not empty caloteiro.dataDivida}">
						<td>${caloteiro.dataDivida.time}</td>
					</c:when>
					<c:otherwise>
						<li>Data não preenchida!</li>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
	<c:import url="rodape.jsp"></c:import>
</body>
</html>