<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/main.css" />
<title>Registration</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>


</head>
<body>

	<p class="error">
		<c:out value="${errorMessage}" />
	</p>

	<div align="center">
		<form:form action="register" commandName="userForm">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>Registration</h2></td>
				</tr>
				<tr>
					<td>Firstname:</td>
					<td><form:input path="firstname" /> <form:errors
							path="firstname" cssClass="error" /></td>
				</tr>

				<tr>
					<td>Lastname:</td>
					<td><form:input path="lastname" /> <form:errors
							path="lastname" cssClass="error" /></td>
				</tr>

				<tr>
					<td>Address:</td>
					<td><form:input path="address" /> <form:errors path="address"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td>ZipCode:</td>
					<td><form:input path="zipcode" /> <form:errors path="zipcode"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td>Town:</td>
					<td><form:input path="town" /> <form:errors path="town"
							cssClass="error" /></td>
				</tr>
				
				
				
				<tr>
					<td>E-mail:</td>
					<td><form:input path="email" /> <form:errors path="email"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td>Username:</td>
					<td><form:input path="username" /> <form:errors
							path="username" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password" /> <form:errors
							path="password" cssClass="error" /></td>
				</tr>


				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Register" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>