<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>T-Cubed Banking Home Page</title>
   <link type="text/css" rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>

<body>
    <div id="header" style="text-align:center">
			<h2>
				T<sup>3</sup> Banking
			</h2>
		</div>
    <p>Welcome to T<sup>3</sup>Banking!<br>
        We are prepared to handle your basic banking needs!</p>

    <h2>Please fill out the requested forms below.</h2>
   <!-- Create HTML form -->
   <div class="container">
    <form action="CustControlServlet" method="GET"><br>
    	<input type="hidden" name="command" value="REGISTRATION"><br>
       <table>
       		<tr><td>First Name:</td><td><input type="text" name="first_name" maxlength="40" required></td></tr>
       		<tr><td>Last Name:</td><td><input type="text" name="last_name" maxlength="40" required></td></tr>
        	<tr><td>Email:</td><td><input type="email" name="email" maxlength="60" required></td></tr>
        	<tr><td>Password:</td><td><input type="password" name="password"  maxlength="50"  required></td></tr>
       </table>
       <input type="submit" value="Register">
    </form>
    </div>

   
   <div class="container">
        <p><a href="index.jsp">Home Page</a></p>
    </div>
	<c:set var="stuff" value="<%= new java.util.Date() %>" />
	Time on the server is ${stuff}
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>

</html>