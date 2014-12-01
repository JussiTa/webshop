<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h3>Order List</h3>
	<c:if test="${!empty orderlist}">
		<table class="tg" id="tblData">
			<tr>
				<th width="40">Order ID</th>
				<th width="120">Order Name</th>
				<th width="40">Total</th>

			</tr>

			<c:forEach items="${orderlist}" var="order">
				<c:url var="orderAction" value="/getuserorders"></c:url>
				<tr>
					<td>${order.id}</td>
					<td>${order.order_name}</td>
					<td>${order.total}</td>


				</tr>

			</c:forEach>

		</table>
	</c:if>
 <a href="http://localhost:8080/Webshop/">Home</a>

</body>
</html>