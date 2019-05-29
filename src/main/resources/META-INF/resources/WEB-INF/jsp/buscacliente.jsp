<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Relatorio por Cliente</title>
</head>
<body>

<h1>Relatorio por Cliente</h1>
	
 <form action="/contas/buscacliente" method="GET">
 Cpf Ou Cnpj: <input type="number" name="cpfOuCnpj"><br>
  
  <input type="submit" value="Submit">
</form> 
</body>
</html>