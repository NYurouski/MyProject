<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Client Card</title>
</head>
<body>
<a href="SiteCont">Main Page</a> <br>
<br>
Name: ${client.name}  <br>
Surname: ${client.surname} <br>
Date of Birth: ${client.birth} <br>
Address: ${client.address} <br>
Mail: ${client.mail}<br>
<a href="addCar.jsp?clientid=${client.id}&name=${client.name}&surname=${client.surname}">Add a new Car</a><br>
<HR>
<c:if test="${cars == null}">
    This client haven't any car.
</c:if>
<c:if test="${cars != null}">
    <c:forEach var="car" items="${cars}">

		  Car:<br>
		  Make: ${car.make} <br>
		  Model: ${car.model} <br>
		  Year: ${car.year} <br>
		  VIN: ${car.vin} <br>
		 
		 <a href="addorder.jsp?name=${client.name}&surname=${client.surname}&carid=${car.id}">Add an order</a>
		 <a href="editCar.jsp?name=${client.name}&surname=${client.surname}&carid=${car.id}&model=${car.model}&make=${car.make}&year=${car.year}&vin=${car.vin}">Edit this Car</a><br>
		 <c:if test="${car.orders == true}">
		 <a href="SiteCont?operation=order&name=${client.name}&surname=${client.surname}&carid=${car.id}">Show Car Orders</a><br>
		 </c:if>
		 <c:if test="${car.orders == false}">
		  <a href="SiteCont?operation=deleteCar&name=${client.name}&surname=${client.surname}&car=${car.id}">Delete a Car</a><br>
		 </c:if>
		 
		 <br>
		   		<HR>
  	</c:forEach>
</c:if>

</body>
</html>