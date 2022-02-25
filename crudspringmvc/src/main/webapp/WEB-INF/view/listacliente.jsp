<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Clientes</title>
<style>
	button{
		margin: 13px 0 0 0;
	}

</style>
</head>
<body>
	<h1>Lista de Clientes</h1>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Data de Nascimento</th>
			<th>Gênero</th>
			<th>Endereço</th>
			<th>Idade</th>
			<th>Telefone</th>
			<th>E-mail</th>
			<th>Produto de Interesse</th>
			<th>Alteração de Clientes</th>
			<th>Exclusão de Clientes</th>
		</tr>
		<c:forEach items="${cliente }" var="c">
			<tr>
				<td>${c.id }</td>
				<td>${c.nome }</td>
				<td><fmt:formatDate value="${c.dataNascimento.time}"
						pattern="dd/MM/yyyy" /></td>
				<td>${c.genero }</td>
				<td>${c.endereco }</td>
				<td>${c.idade }</td>
				<td>${c.telefone }</td>
				<td>${c.email }</td>
				<td>${c.prodInteresse }</td>
				<td><a href="alterarCliente?idCliente=${c.id }">Alterar</a></td>
				<td><a href="excluirCliente?idCliente=${c.id }"	onclick="return confirm('Confirmar exclusão do cliente ${c.nome}?')">Excluir</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="estatisticas"><button type="submit">Estatísticas</button></a>
</body>
</html>