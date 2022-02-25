<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro</title>

<style>
	h1{
		text-align: center;
	}

	form{
		border:solid 2px;
		width:15%;
		padding: 25px;
		margin:auto;
	}
		
	input{
		width:97%;
	}
	
	button{
		margin: 13px 0 0 0;
		width:100%;
	}
</style>
</head>
<body>
	<h1>Cadastro de Clientes</h1>
	
	<form action="salvar" method="post">
		<input type="hidden" name="id" value="${cliente.id }">
	
	
		<label><b>Nome:</b></label>
		<input type="text" name="nome" value="${cliente.nome }" required/>
		<label><b>Data de Nascimento:</b></label>
		<input type="date" name="dataNascimento" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${cliente.dataNascimento.time }"/>" required>
		<label><b>Gênero</b></label>
		<br>
		<select name="genero">
			<c:forEach items="${genero }" var="g">
				<option value="${g }" <c:if test="${cliente.genero == g }">selected</c:if>>${g.toString() }</option>
			</c:forEach>				
		</select>
		<br>
		<label><b>Endereço:</b></label>
		<input type="text" name="endereco" value="${cliente.endereco }" required/>
		<label><b>Telefone ou Celular:</b></label>
		<input type="number" maxlength="11" name="telefone" value="${cliente.telefone }" required>
		<label><b>E-mail:</b></label>
		<input type="email" name="email" value="${cliente.email }" required/>	
		<label><b>Produto(s) de interesse:</b></label>
		<input type="text" name="prodInteresse" value="${cliente.prodInteresse }" required/>
		<button type="submit">Cadastrar</button>		
	</form>
</body>
</html>