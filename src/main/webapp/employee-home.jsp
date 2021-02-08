<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Home</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>
				T<sup>3</sup> Banking
			</h2>
		</div>
		<div style="text-align: center">
			<h3>
				Welcome to Employee Portal
				<c:out value="${user.first_name} ${user.last_name}"></c:out>
			</h3>
			<p>
				On this page you have links to all functionalities<br>
			</p>
		</div>
	</div>
	<div id="container">
		<div id="content" style="text-align: center">
			<form action="EmpControlServlet" method="GET">
				<input type="hidden" name="command" value="TRANSACTIONLIST">
				<input type="submit" value="Master Transaction List">
			</form>
		</div>
	</div>
	<div id="container">
		<div id="content" style="text-align: center">
			<form action="EmpControlServlet" method="GET">
				<input type="hidden" name="command" value="PENDINGACCOUNTS">
				<input type="submit" value="Approve Pending Accounts">
			</form>
			<a href="LogoutServlet">Logout</a>
		</div>
	</div>
	<div id="container">
		<div id="content" class="col-2">
		<div id="content" class="col-8">
		<table>
			<tr>
				<th>User_ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>View</th>
			</tr>
			<c:forEach var="theUser" items="${user_list}">
				<c:url var="theLink" value="EmpControlServlet">
					<c:param name="command" value="SELECTCUSTOMER" />
					<c:param name="user_id" value="${theUser.user_id}" />
				</c:url>
				<tr>
					<td>${theUser.user_id}</td>
					<td>${theUser.first_name}</td>
					<td>${theUser.last_name}</td>
					<td>${theUser.email}</td>
					<td><a href="${theLink}">View</a></td>
				</tr>
			</c:forEach>

		</table>
		</div>
	</div>
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>