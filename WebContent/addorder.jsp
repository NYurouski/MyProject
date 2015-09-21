<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add an order</title>
</head>
<body>
<a href="SiteCont">Main Page</a> <br>
<br>
<form action="SiteCont">
<p>
<input type="hidden" name="operation" value="addOrder">
<input type="hidden" name="carid" value="${param.carid}">
<input type="hidden" name="name" value="${param.name}">
<input type="hidden" name="surname" value="${param.surname}">
Date:<input name="date" value="${date}"><br>
Amount:<input name="amount" value="${amount}"><br>
Status:<select name="status">
<option value="In Progress">In Progress</option>
<option value="Completed">Completed</option>
<option value="Cancelled">Cancelled</option>
</select><br>

<input type="submit" value="Save">
</p>
</form>
<FONT color="red">${error} </FONT>
</body>
</html>