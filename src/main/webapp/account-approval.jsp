<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Account Approval Page</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>

<body>
<div id="header" style="text-align:center">
			<h2>
				T<sup>3</sup> Banking
			</h2>
		</div>	<h1>
		T<sup>3</sup>Banking
	</h1>
	<h2>Employee Page: Account Approval</h2>
	<h3>
		Home:
		<c:out value="${user.first_name} ${user.last_name}"></c:out>
	</h3>
	<div class="container">
		<ul>
			<li>Assess the pending accounts are all legitimate.</li>
			<li>Press Approve/Deny to settle account.</li>
			<li>Upon approval the account will be visible on the Customer
				Home Page!</li>
		</ul>
	</div>
	<div id="container">
		<div id="content">
			<a href="EmpControlServlet">Employee Home</a>
			<table>
				<tr>
					<th>User_ID</th>
					<th>Account ID</th>
					<th>Balance</th>
					<th colspan=2>Approve</th>
				</tr>
				<c:forEach var="theAccount" items="${pending_account_list}">
					<c:url var="theLinkApproves" value="EmpControlServlet">
						<c:param name="command" value="APPROVEACCOUNT" />
						<c:param name="account_id" value="${theAccount.account_id}" />
					</c:url>
					<c:url var="theLinkDenies" value="EmpControlServlet">
						<c:param name="command" value="DENYACCOUNT" />
						<c:param name="account_id" value="${theAccount.account_id}" />
					</c:url>
					<tr>
						<td>${theAccount.user_id}</td>
						<td>${theAccount.account_id}</td>
						<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
								value="${theAccount.balance}" type="currency" /></td>
						<td>
						
						<td><a href="${theLinkApproves}">Approve</a> | <a
							href="${theLinkDenies}">Deny</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>

</html>