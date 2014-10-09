

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
    <div align="center">
        <form:form action="register" method="post" commandName="userForm">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Registration</h2></td>
                </tr>
                 <tr>
                    <td>Firstname:</td>
                    <td><form:input path="firstname" /></td>
                </tr>
                
                 <tr>
                    <td>Lastname:</td>
                    <td><form:input path="lastname" /></td>
                </tr>
                
                <tr>
                    <td>Address:</td>
                    <td><form:input path="address" /></td>
                </tr>
                
                <tr>
                    <td>ZipCode:</td>
                    <td><form:input path="zipcode" /></td>
                </tr>
                
                 <tr>
                    <td>Town:</td>
                    <td><form:input path="town" /></td>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><form:input path="username" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:password path="password" /></td>
                </tr>
                
               
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Register" /></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>