<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Transferencia</h1>
	
 <form action="/contas/transferencia" method="POST">
 Agencia: <input type="number" name="agenciaOrigem"><br>
  Conta: <input type="number" name="contaOrigem"><br>
  Agencia: <input type="number" name="agenciaDestino"><br>
  Conta: <input type="number" name="contaDestino"><br>
  Valor: <input type="number" name="valor"><br>
  <input type="submit" value="Submit">
</form> 
</body>
</html>