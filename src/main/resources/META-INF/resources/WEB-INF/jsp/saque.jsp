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

<h1>Saque</h1>
	
 <form action="/contas/saque" method="POST">
 Agencia: <input type="number" name="agencia"><br>
  Conta: <input type="number" name="conta"><br>
  Valor: <input type="number" name="valor"><br>
  <input type="submit" value="Submit">
</form> 
</body>
</html>