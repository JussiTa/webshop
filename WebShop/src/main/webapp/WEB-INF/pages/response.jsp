<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show order</title>
<script type="text/javascript">
	function stringify() {
		var request =
<%=request.getAttribute("request")%>
	;
		var response =
<%=request.getAttribute("response")%>
	;
		var error ='<%=request.getAttribute("error")%>';
		var req = document.getElementById("request");
		var resp = document.getElementById("response");
		if (request != null) {
			req.innerHTML = JSON.stringify(request, null, 4);
		} else {
			req.innerHTML = "No payload for this request";
		}
		if (response != null) {
			resp.innerHTML = JSON.stringify(response, null, 4);
		} else if (error != null) {
			resp.innerHTML = error;

		}
	}
</script>
</head>
<body onload="stringify();">
	
	<br />
	<%
		if (request.getAttribute("redirectURL") != null) {
	%>
	<a href=<%=(String) request.getAttribute("redirectURL")%>>Continue
		</a>
	<br />
	<%
		}
	%>
	
	
	<h2>
			<c:out value="${cancel}" />
		</h2>
	
	
	 <table  class="tg"id="tblData">
		<tr>
		<!-- 	<th>Request</th> -->
			<!-- <th>Response</th> -->
		</tr>
		<tr>
			<!-- <td valign="top"><pre id="request"> -->
					
		<!-- 	</pre></td> -->
			<td  id="response"> </td>
					
				<!-- </pre></td> -->
		</tr>
	</table> 
	<br />
	<a href="http://localhost:8080/Webshop/checkout">Back</a>
</body>
</html>