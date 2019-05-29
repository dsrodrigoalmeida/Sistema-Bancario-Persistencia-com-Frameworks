<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultados da Busca</title>
</head>
<body>
	<c:forEach var="conta" items="${contas}">

		<h1>Cliente</h1>
		<table>
			<tr>
				<td>CPF/CPNJ Do Cliente</td>
				<td>Nome do Cliente</td>
				<td>Tipo do Cliente</td>
			</tr>

			<tr>
				<td>${conta.cliente.cpfOuCpnj }</td>
				<td>${conta.cliente.nome }</td>
				<td>${conta.cliente.tipo }</td>
			</tr>
		</table>

		<br />

		<h1>Conta</h1>
		<table>
			<tr>
				<td>Conta Corrente</td>
				<td>Agencia</td>
				<td>Saldo</td>

			</tr>

			<tr>

				<td>${conta.conta }</td>
				<td>${conta.agencia }</td>
				<td>${conta.saldo }</td>

			</tr>
		</table>

		<br />
		<h1>Transação</h1>
		<table>
			<tr>
				<td>Transações</td>
			</tr>

			<tr>
				<td>${conta.transacoes }</td>
			</tr>

		</table>

		<br />
	</c:forEach>
	<a href="/contas/index">Voltar para a pagina inicial</a>


</body>
</html>