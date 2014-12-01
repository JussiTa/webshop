<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>


<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="<c:url value="/resources/css/jquery.autocomplete.min.js" />"></script>

<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-8">
<title>Products Page</title>

</head>
<body>
	<div id="header">
		<h1>Finnish made</h1>
		<div id="link">
			<a href="http://localhost:8080/Webshop/login">Login</a>
		</div>

		<div id="cart">
			<a href="cart"><img src="webapp/resources/images/shopingcart.svg"></a>
		</div>

	</div>


	<div id="nav">
		<ul>
			<li><a href="http://localhost:8080/Webshop/home">Home </a></li>
			<li><a href="http://localhost:8080/Webshop/register">Register
			</a></li>
			<li><a href="http://localhost:8080/Webshop/about">About </a></li>
		</ul>
	</div>



	<div id="section">
		<h2>
			<c:out value="${errorMessage}" />
		</h2>

		<c:url var="findaction" value="/findproduct"></c:url>

		<form:form action="${findaction}" commandName="product">

			<form:input type="text" id="product-search" value="" path="name" />
			<span> <!-- <button id="button-id" type="button">Search</button> -->
				<input type="submit" value="Search" />

			</span>

		</form:form>

		<script>
			$(document)
					.ready(
							function() {

								$('#product-search')
										.autocomplete(
												{
													serviceUrl : '${pageContext.request.contextPath}/getTags',
													paramName : "tagName",
													delimiter : ",",
													transformResult : function(
															response) {

														return {
															//must convert json to javascript object before process
															suggestions : $
																	.map(
																			$
																					.parseJSON(response),
																			function(
																					item) {

																				return {
																					value : item.tagName,
																					data : item.id
																				};
																			})

														};

													}

												});

							});
		</script>
		<br>
		<h3>Product List</h3>
		<c:if test="${!empty listProducts}">
			<table class="tg" id="tblData">
				<tr>
					<th width="80">Product ID</th>
					<th width="120">Product Name</th>
					<th width="120">Product Gategory</th>
					<th width="120">Product price</th>
					<th width="120">Add to cart</th>
				</tr>

				<c:forEach items="${listProducts}" var="product">
					<c:url var="addAction" value="/addtoCart"></c:url>
					<form:form action="${addAction}">
						<tr>
							<td>${product.id}<input type="hidden" name="id"
								value="${product.id}">
							</td>
							<td>${product.name}<input type="hidden" name="name"
								value="${product.name}">
							</td>
							<td>${product.category}<input type="hidden" name="category"
								value="${product.category}">
							</td>
							<td>${product.price}<input type="hidden" name="price"
								value="${product.price}">
							</td>
							<td><input type="text" name="pcs" size='5' value='1'></td>
							<td><input type="submit" value="Add to cart" /></td>
						</tr>
					</form:form>
				</c:forEach>



			</table>



		</c:if>

	</div>


	<div id="footer">
		Copyright: All rights reserved <br> Contacts
	</div>



</body>
</html>