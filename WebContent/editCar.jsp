<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit this Car</title>
</head>
<body>
<a href="SiteCont">Main Page</a> <br>
<br>
This is a car of ${param.name} ${param.surname}.
<form action="SiteCont">
<p>
<input type="hidden" name="operation" value="editCar">
<input type="hidden" name="name" value="${param.name}">
<input type="hidden" name="surname" value="${param.surname}">
<input type="hidden" name="carid" value="${param.carid}">
Make:<input name="make" value="${param.make}"><br>
Model:<input name="model" value="${param.model}"><br>
Year:<input name="year" value="${param.year}"><br>
VIN:<input name="vin" value="${param.vin}"><br>

<input type="submit" value="Save">
</p>
</form>
<FONT color="red">${error} </FONT>
</body>
</html>