<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a new Client</title>
</head>
<body>
<a href="SiteCont">Main Page</a> <br>
<br>
This is a new client. Add some information about him.
<form action="SiteCont">
<p>
<input type="hidden" name="operation" value="add">

Name:<input name="name" value="${name}"><br>
Surname:<input name="surname" value="${surname}"><br>
Date of Birth:<input name="birth" value="${birth}"><br>
Address:<input name="address" value="${address}"><br>
Phone:<input name="phone" value="${phone}"><br>
Mail:<input name="mail" value="${mail}"><br>

<input type="submit" value="Save">
</p>
<FONT color="red">${error} </FONT>
</form>
</body>
</html>