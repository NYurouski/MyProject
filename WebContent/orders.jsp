<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders</title>
</head>
<body>
<a href="SiteCont">Main Page</a> <br>
<br>
Owner:<a href="SiteCont?operation=search&name=${name}&surname=${surname}">${name} ${surname}</a> <br>
Car:<br>
Model: ${car.model} <br>
Make: ${car.make}<br>
Year: ${car.year}<br>
VIN: ${car.vin}<br>

<a href="addorder.jsp?name=${name}&surname=${surname}&carid=${car.id}">Add an order</a>

<table width="100%"  cellspacing="0" cellpadding="10">
	<caption>Orders</caption>
   	<tr align="left">
    
	    <th>Date</th>
	    <th>Amount</th>
	    <th>Status</th>
   	</tr>
   	
  <c:forEach var="order" items="${orders}">

  <tr>
  <td>${order.date} </td>
  <td>${order.amount}$</td>
  <td>${order.status} </td>
  <td><a href="editOrder.jsp?name=${name}&surname=${surname}&date=${order.date}
		  &orderid=${order.id}&amount=${order.amount}&status=${order.status}
		  &carid=${car.id}&oper=edit">Edit this Order</a></td>
  <td> <a href="SiteCont?operation=deleteOrder&name=${name}&surname=${surname}&orderid=${order.id}&carid=${car.id}">Delete</a></td>
  </tr>
  
  </c:forEach>
  
  </table>
</body>
</html>