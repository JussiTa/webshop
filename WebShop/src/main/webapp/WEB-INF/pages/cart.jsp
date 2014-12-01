<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Cart</title>
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
</head>
<body>

	<br>
	<h3>Cart content</h3>
	<c:if test="${!empty cart}">
		<table class="tg" id ="cartdata">
			<tr>
				<th width="80">Product ID</th>
				<th width="120">Product Name</th>
				<th width="120">Product price</th>
				<th width="30">Pcs</th>

			</tr>
			<c:forEach items="${cart}" var="ci">
				<tr>
					<td>${ci.id}</td>
					<td>${ci.name}</td>
					<td>${ci.price}</td>
					<td>${ci.pcs}</td>					
                    <td><a href="<c:url value='/editCart/${ci.id}'  />" >Edit</a></td> 
         			<td><a href="<c:url value='/removeItem/${ci.id}' />" >Delete</a></td>
				</tr>

			</c:forEach>


		</table>

		<table>
			<tr>
				<th width="CDATA">Total sum (EUR)</th>
			<tr>
				<td><fmt:formatNumber type="number" minFractionDigits="2"
						value="${total}" /></td>
			</tr>
		</table>

		<a href="http://localhost:8080/Webshop/checkout">Checkout</a>


		



	</c:if>
	
	<a href="http://localhost:8080/Webshop/products">Continue shopping</a>
</body>
</html>