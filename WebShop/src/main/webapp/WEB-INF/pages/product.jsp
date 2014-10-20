<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
<title>Products Page</title>

</head>
<body>
	<div id="header">
		<h1>Finnish made</h1>
		<div id="link">
			<a href="http://localhost/Webshop/login">Login</a>
		</div>
		
		<div id="cart">			
				<a href="cart"><img src="resources/images/shopingcart.svg"></a> 
		</div>
		
	</div>
	
	
	<div id="nav">
		<ul>
			<li><a href="http://localhost/Webshop/home">Home </a></li>			
			<li><a href="http://localhost/Webshop/register">Register </a></li>
			<li><a href="http://localhost/Webshop/about">About </a></li>
			

		</ul>
	</div>

	<c:url var="addAction" value="/product/add"></c:url>

<div id="section">
	<h2>
		<c:out value="${errorMessage}" />
	</h2>

	<br>
	<h3>Product List</h3>
	<c:if test="${!empty listProducts}">
		<table class="tg">
			<tr>
				<th width="80">Product ID</th>
				<th width="120">Product Name</th>
				<th width="120">Product Gategory</th>
				<th width="120">Product price</th>
				<th width="120">add to cart</th>
			<!-- 	<img alt="logo" src="/resources/images/shoppingcart.svg"/> -->

			</tr>
			<c:forEach items="${listProducts}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.gategory}</td>
					<td>${product.price}</td>
					<td><a
						href="${pageContext.request.contextPath}/addtocart/${product.id}/addtocart2/${product.name}/addtocart3/${product.price}">Add
							to cart</a></td>
				</tr>

			</c:forEach>
		</table>
	</c:if>
	
	
	</div>
	
		
	<div id="footer">
		 Copyright: All rights reserved   <br> Contacts
</div>
	
	
	
</body>
</html>