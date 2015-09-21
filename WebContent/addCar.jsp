<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new Car</title>
</head>
<body>
<a href="SiteCont">Main Page</a> <br>
<br>
This is a new car of ${param.name} ${param.surname}.
<form action="SiteCont">
<p>
<input type="hidden" name="operation" value="addCar">
<input type="hidden" name="clientid" value="${param.clientid}">
<input type="hidden" name="name" value="${param.name}">
<input type="hidden" name="surname" value="${param.surname}">
Make:<input name="make" value="${make}"><br>
Model:<input name="model" value="${model}"><br>
Year:<input name="year" value="${year}"><br>
VIN:<input name="vin" value="${vin}"><br>

<input type="submit" value="Save">
</p>
<FONT color="red">${error} </FONT>
</form>
</body>
</html>