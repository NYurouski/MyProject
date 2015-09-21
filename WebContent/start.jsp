<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start page</title>
</head>
<body>
	<form action="SiteCont">
			<p>
			<input type="hidden" name="operation" value="search">
			Name:<input name="name" value="${name}"><br>
			Surname:<input name="surname" value="${surname}"><br>
			<input type="submit" value="Search">
			</p>
</form>
<c:if test="${error != null}">
 <FONT color="red">${error} </FONT>
</c:if>
</body>
</html>