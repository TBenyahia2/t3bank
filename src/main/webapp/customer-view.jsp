<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Portal : Customer View</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
	<div id="wrapper">
		<div id="header" style="text-align: center">
			<h1>
				T<sup>3</sup> Banking
			</h1>
		</div>
		<div style="text-align: center">
			<h2>Employee Portal</h2>
			<div>
				<p>Employee ID: ${user.user_id}<br> Employee : ${user.first_name}
					${user.last_name}<br>
					<a href="EmpControlServlet">Employee Home</a></p>
			</div>
		</div>
		<div class="container">
			<div style="text-align: center">
			<h3 >Customer View</h3>
			</div>
			<ul>
				<li>Customer ID: ${customer.user_id}</li>
				<li>Customer : ${customer.first_name} ${customer.last_name}</li>
			</ul>
			<table>
				<tr>
					<th>Account ID</th>
					<th>Balance</th>
				</tr>
				<tr>
					<c:forEach var="theAccount" items="${cust_account_view}">
						<tr>
							<td>${theAccount.account_id}</td>
							<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
									value="${theAccount.balance}" type="currency" /></td>
						</tr>
					</c:forEach>
				</tr>
			</table>
			<table>
				<tr>
					<td>
				</tr>
			</table>
		</div>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>