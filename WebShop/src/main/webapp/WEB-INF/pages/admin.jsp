<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%>
<html>
<head>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>


<body>

	<div id="header">

		<h1>${title}</h1>
		<%-- <h1>Message : ${message}</h1>
 --%>

		<a href="javascript:formSubmit()"> Logout</a>
	</div>
	
	<h1>
    Add a Product
</h1>
 
<c:url var="addAction" value="/add" ></c:url>
 
<form:form action="${addAction}" commandName="product">
<table>
    <c:if test="${!empty product.name}">
    <tr>
        <td>
            <form:label path="id">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="8"  disabled="true" />
            <form:hidden path="id" />
        </td>
    </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="name">
                <spring:message text="Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="name" />
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="gategory">
                <spring:message text="Gategory"/>
            </form:label>
        </td>
        <td>
            <form:input path="gategory" />
        </td>
    </tr>
    
    <tr>
        <td>
            <form:label path="price">
                <spring:message text="Price"/>
            </form:label>
        </td>
        <td>
            <form:input path="Price" />
        </td>
    </tr>    
    
    <tr>
        <td colspan="2">
            <c:if test="${!empty product.name}">
                <input type="submit"
                    value="<spring:message text="Edit Product"/>" />
            </c:if>
            <c:if test="${empty product.name}">
                <input type="submit"
                    value="<spring:message text="Add Product"/>" />
            </c:if>
        </td>
    </tr>
</table> 
</form:form>	


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
		<p>Welcome admin : ${pageContext.request.userPrincipal.name} </p>
	</c:if>
	
	<h3>Product List</h3>
		<c:if test="${!empty listProducts}">
			<table class="tg">
				<tr>
					<th width="80">Product ID</th>
					<th width="120">Product Name</th>
					<th width="120">Product Gategory</th>
					<th width="120">Product price</th>
										
				</tr>
				<c:forEach items="${listProducts}" var="product">
					<tr>
						<td>${product.id}</td>
						<td>${product.name}</td>
						<td>${product.gategory}</td>
						<td>${product.price}</td>
						<td><a href="<c:url value='/edit/${product.id}' />" >Edit</a></td>
         				   <td><a href="<c:url value='/remove/${product.id}' />" >Delete</a></td>
					</tr>

				</c:forEach>
				
				
				
			</table>
		</c:if>

</body>


</html>