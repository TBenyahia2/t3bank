<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Home</title>
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
			<h2>
				Accounts Home
				<c:out value="${user.first_name} ${user.last_name}"></c:out>
			</h2>
			<hr>
			<div style="text-align: center">
				<p>
					<b>User ID:</b>
					<c:out value="${user.user_id}"></c:out>
					<br> <b>Account ID:</b>
					<c:out value="${theAccount.account_id}"></c:out>
					<br> <b>Balance:</b>
					<c:out value="${theAccount.balance}"></c:out>
					<br>
				<form action="CustControlServlet" method="GET">	
				<input type="hidden" name="command" value="MAKEDEPOSIT">
				<input type="hidden" name="account_id" value="${theAccount.account_id}">
			<div class="container">
				<table>
					<tr>
						<td>Make Deposit</td>
						<td> <input type="number" name="amount" placeholder="amount" min="0.01" max="1000000000"  step="0.01" required></td>
						<td> <input type="submit" value="Submit">
					</tr>
				</table>
				</div>
				</form>
				<form action="CustControlServlet" method="GET">
				<input type="hidden" name="command" value="MAKEWITHDRAWAL">
				<input type="hidden" name="account_id" value="${theAccount.account_id}">	
				<div class="container">
				<table>
					<tr>
						<td>Make Withdrawal</td>
						<td> <input type="number" name="amount" placeholder="amount" min="0.01" max="1000000000"  step="0.01" required></td>
						<td> <input type="submit" value="Submit">
					</tr>
				</table>
				</div>
				</form>
				<form action="CustControlServlet" method="GET">	
				<input type="hidden" name="command" value="MAKETRANSFER">
				<input type="hidden" name="account_id" value="${theAccount.account_id}">
				<div class="container">
				<table>
					<tr>
						<td>Make Transfer</td>
						<td> <input type="number" name="amount" placeholder="amount" min="0.01" max="1000000000"  step="0.01" required></td>
						<td> <input type="number" name="target_account" placeholder="Recipient Account ID" min="5" max="1000000000" step="1" required></td>
						<td> <input type="submit" value="Submit">
					</tr>
				</table>
				</div>
				</form>
				<a href="CustControlServlet">Back to Your Home Page</a>
			</div>
		</div>
	</div>
	<div id="container" class="container">
		<div id="content">
			<table>
				<tr>
					<th>Transaction ID</th>
					<th>Amount</th>
				</tr>
				<c:forEach var="transaction" items="${acc_transactions}">
					<tr>
						<td>${transaction.transaction_id}</td>
						<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
								value="${transaction.amount}" type="currency" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>

</html>