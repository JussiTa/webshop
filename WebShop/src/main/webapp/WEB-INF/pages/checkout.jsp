<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-8">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
</head>
<body>
	<h1>${title}</h1>
	<h1>${message}</h1>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>


	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>


	<c:if test="${pageContext.request.userPrincipal.name != null}">

	</c:if>

	<h3>Order content</h3>
	<c:if test="${!empty cart}">
		<table class="tg" id="tblData">
			<tr>
				<th width="80">Product ID</th>
				<th width="120">Product Name</th>
				<th width="120">Product Pcs</th>
				<th width="120">Product price</th>

			</tr>
			<c:forEach items="${cart}" var="cartItem">
				<tr>
					<td>${cartItem.id}</td>
					<td>${cartItem.name}</td>
					<c:if test="${cartItem.sufficient}" />
					<td>${cartItem.pcs}</td>
					<td>${cartItem.price}</td>
					<td>${cartItem.noOryes} </td>
			</c:forEach>


		</table>
	</c:if>

	<c:if test="${!empty cart}">
		<table>
			<tr>
				<td>${total}</td>
			</tr>
		</table>
	</c:if>

<c:url var="addAction" value="/paypal" ></c:url>
 

<form action="${addAction}">
<input type='image' name='submit' src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif'   alt='Check out with PayPal'/>
</form>


	<!-- <a href="http://localhost:8080/Webshop/confirm">Confirm order</a> -->
	<a href="http://localhost:8080/Webshop/product">Continue shopping</a>

</body>
</html>