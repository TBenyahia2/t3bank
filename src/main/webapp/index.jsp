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
<div id="container">
<div id="header" style="text-align:center">
			<h2>
				T<sup>3</sup> Banking
			</h2>
		</div>
   
    </div>
    <p>Welcome to T<sup>3</sup>Banking!<br>
        We are prepared to handle your basic banking needs!</p>

    <h2>Log In to access your account(s)</h2>
   <!-- Create HTML form -->
   <div class="container">
    <form action="LoginServlet" method="POST">
        <input type="number" name="user_id" placeholder="User ID #" min="1" max="1000000000" required>
        <input type="password" name="password" placeholder="Password" maxlength="40"  required>
     	<br>${message}
        <input type="submit" value="Login">
    </form>
    </div>

   
    <div class="container">
        <p><a href="registration.jsp">Register</a></p>
    </div>
    <div class="container">
	<c:set var="stuff" value="<%= new java.util.Date() %>" />
	Time on the server is ${stuff}
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>

</html>