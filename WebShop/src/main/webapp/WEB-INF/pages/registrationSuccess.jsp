<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Form handling</title>
</head>
<body>

<h2>Thank you for your registering!</h2>
   <table>
    <tr>
        <td>Firstname</td>
        <td>${firstname}</td>
    </tr>
    <tr>
        <td>Lastname</td>
        <td>${lastname}</td>
    </tr>
    <tr>
        <td>ID</td>
        <td>${id}</td>
    </tr>
</table>  
</body>
</html>