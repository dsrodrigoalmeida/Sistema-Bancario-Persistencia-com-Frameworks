<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Conta</title>
</head>
<body>

	<h1>Cadastro de Conta</h1>

	<form action="/contas/cadastro" method="POST">
	
		Cpf/Cpnj do Cliente: <input type="number" name="cpfOuCpnj">
		<br />
		Nome <input type="text" name="nome"><br> 
		<br />
		<label class="radio-inline"> <input type="radio"
			name="radioName" id="pessoa_fisica" value="pessoa_fisica" checked="checked" />Pessoa Fisica
		</label> 
		
		<label class="radio-inline"> <input type="radio"
			name="radioName" id="pessoa_juridica" value="pessoa_juridica" />Pessoa Juridica
		</label> 
		<br />
		<br/>
		Agencia: <input type="number" name="agencia">
		<br />
		 Conta: <input type="number" name="conta"><br> 
			<br />
			
			<input type="submit" value="Submit">
	</form>

</body>
</html>