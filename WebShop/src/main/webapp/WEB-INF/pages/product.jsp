<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>



<script src="<c:url value="/resources/css/jquery.autocomplete.min.js" />"></script> 
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

		<div>
			<input type="text" id="w-input-search" value=""> <span>
				<button id="button-id" type="button">Search</button>
			</span>
		</div>

		     
     <script>
  $(document).ready(function() {
 
	$('#w-input-search').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/getTags',
		paramName: "tagName",
		delimiter: ",",
	   transformResult: function(response) {
 
		return {      	
		  //must convert json to javascript object before process
		  suggestions: $.map($.parseJSON(response), function(item) {
 
		      return { value: item.tagName, data: item.id };
		   })
 
		 };
 
            }
 
	 });
 
  });
  </script>
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
						<td>${product.category}</td>
						<td>${product.price}</td>
						<td>
						<form:form action="addtocart" commandName="userForm">
						<form:input path="pcs" />
            					
        						<input type="submit"
                    				value="<spring:message text="Add Product to cart"/>" />
        				</form:form>	
        				</td>
						<%-- 
						<td><a
							href="${pageContext.request.contextPath}/addtocart/${product.id}/addtocart2/${product.name}/addtocart3/${product.price}">Add
								to cart</a></td> --%>
					</tr>

				</c:forEach>
				
				
				
			</table>
			
			
		</c:if>
	

	</div>


	<div id="footer">
		Copyright: All rights reserved <br> Contacts
	</div>



</body>
</html>