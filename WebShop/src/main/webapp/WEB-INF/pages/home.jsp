<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%> 

<html>
<head>
<link href='<c:url value="/resources/css/main.css" />' rel="stylesheet">
<title>Home</title>
</head>


<body>

	<div id="header">
		<h1>Finnish made</h1>
		<div id="link">
			<a href="http://localhost:8080/Webshop/login">Login</a>
		</div>
		
		<div id="linkleft">
			<a href="http://localhost:8080/Webshop/logout">Logout</a>
		</div>

		<div id="cart">
			<a href="cart"><img src="resources/css/shopingcart.svg" alt=""></a>
		</div>
	</div>


	<div id="nav">
		<ul>
			<li><a href="http://localhost:8080/Webshop/products">Products
			</a></li>
			<li><a href="http://localhost:8080/Webshop/register">Register
			</a></li>
			<li><a href="http://localhost:8080/Webshop/about">About </a></li>
			<li><a href="http://localhost:8080/Webshop/myPage">My page </a></li>

		</ul>
	</div>

	<div id="section">
		<p>Welcome finnish made shop!</p>
	</div>
	<div id="footer">
		Copyright: All rights reserved <br> Contacts
	</div>
</body>



</html>