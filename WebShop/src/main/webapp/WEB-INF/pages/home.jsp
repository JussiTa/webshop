<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<link href='<c:url value="/resources/css/main.css" />' rel="stylesheet">
<title>Home</title>
</head>


<body>

	<div id="header">
		<h1>Finnish made</h1>
		<div id="link">
			<a href="http://localhost:8080/Webshop/login">Login</a>
		</div>
		   
			<a href="cart"> <img src="http://www.clker.com/cliparts/5/b/5/0/11949857881524867289hotel_icon_grocery_stor_01.svg.thumb.png" 
			
			width="35" height="35" align ="middle"
			></a>
		   
	</div>


	<div id="nav">
		<ul>
			<li><a href="http://localhost:8080/Webshop/products">Products
			</a></li>
			<li><a href="http://localhost:8080/Webshop/register">Register
			</a></li>
			<li><a href="http://localhost:8080/Webshop/about">About </a></li>
			<li><a href="http://localhost:8080/Webshop/myPage">My page </a></li>
			<li><a href="http://localhost:8080/Webshop/logout">Logout</a> </li>
		

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