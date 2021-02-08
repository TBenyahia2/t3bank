<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Home</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
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
				Master Transaction table<br>
			</p>
		</div>
	</div>
	<div id="container">
		<div id="content">
		<form action="EmpControlServlet" method="GET">	
		<input type="hidden" name="command" value="PENDINGACCOUNTS">
		<input type="submit" value="Approve Pending Accounts">
				</form>
				<a href="EmpControlServlet">Employee Home</a>
			<table>
				<tr>
					<th>Transaction ID</th>
					<th>Account ID</th>
					<th>user ID</th>
					<th>Amount</th>
				</tr>
				<c:forEach var="theTransaction" items="${transaction_list}" >
					<tr>
						<td>${theTransaction.transaction_id}</td>
						<td>${theTransaction.account_id}</td>
						<td>${theTransaction.user_id}</td>	
						<td>${theTransaction.amount}</td>			
					</tr>				
				</c:forEach>
			</table>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>