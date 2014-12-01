<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>






<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3>Edit item</h3>
	
		<table class="tg" id ="cartdata">
			<tr>
				<th width="80">Product ID</th>
				<th width="120">Product Name</th>
				<th width="120">Product price</th>
				<th width="30">Pcs</th>

			</tr>
			<c:forEach items="${cart}" var="ci">
			<c:url var="addAction" value="/saveEditCart"></c:url>
					<form:form action="${addAction}">
								<tr>
					<td>${ci.id}<input type="hidden" name="id"
								value="${ci.id}"></td>
					<td>${ci.name}</td>
					<td>${ci.price}</td>
					
											
                   <td><input type="text" name="pcs" size='5'value='${ci.pcs}'></td>
							<td><input type="submit" value="Save" /></td>
         			
				</tr>
				</form:form>
			</c:forEach>


		</table>
















</body>
</html>