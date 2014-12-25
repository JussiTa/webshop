<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order success</title>
</head>
<body>

<h1>Thank you, you have successfull finish your order!</h1>

		
	 <table class="tg" id="tblData">
			<tr>
				<th width="120">Name</th>
				<th width="180">Address</th>
				<th width="120">Total</th>
				
				
				

			</tr>
			<tr>
			
					<td>${name}</td>
					<td>${address}</td>
					<td>${total}</td>

			</tr>
		</table>











    <a href="http://localhost:8080/Webshop/">Home</a>
    

</body>
</html>