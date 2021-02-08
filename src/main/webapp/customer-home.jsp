<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Home</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
	<div id="wrapper">
		<div id="header" style="text-align:center">
			<h2>
				T<sup>3</sup> Banking
			</h2>
		</div>
		<div style="text-align: center">
			<h3>
				Welcome Home
				<c:out value="${user.first_name} ${user.last_name}"></c:out>
			</h3>
			<h3>
				User ID:
				<c:out value="${user.user_id}"></c:out>
			</h3>
			<p>
				When's the next trip? Should we open a new Account and start saving?<br>
				Here at <b>T<sup>3</sup> Banking
				</b> we offer free holding accounts.<br>
			</p>
		</div>
		<ul>
			<li>This account is designed with trips in mind!</li>
			<li>No charges!</li>
			<li>No interest!</li>
			<li>Easy Access!</li>
			<li>Free in house transfers!</li>
			<li><b>Apply for a new account now!</b></li>
			<li>Take more trips tomorrow!</li>
		</ul>
	</div>
	${message}
	<div class="wrap">
		<div id="container">
			<div id="content">
				<input type="button" value="Apply for a new Account"
					onclick="window.location.href='account-application.jsp'; return false;" />
				<a href="LogoutServlet">Logout</a>
				<table>
					<tr>
						<th>Account id</th>
						<th>Balance</th>
						<th>View Account</th>
					</tr>
					<c:forEach var="theAccount" items="${account_list}">
						<c:url var="theLink" value="CustControlServlet">
							<c:param name="command" value="SELECTACCOUNT" />
							<c:param name="account_id" value="${theAccount.account_id}" />
						</c:url>
						<tr>
							<td>${theAccount.account_id}</td>
							<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
									value="${theAccount.balance}" type="currency" /></td>
							<td><a href="${theLink}">View</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th colspan="5">Your pending transfers</th>
				</tr>
				<tr>
					<th>Transfer ID</th>
					<th>Sending Account ID</th>
					<th>Receiving Account ID</th>
					<th>Amount</th>
					<th>Approve</th>
				</tr>
				<c:forEach var="theTransfer" items="${customer_pending_transfers}">
					<c:url var="theLinkApproves" value="CustControlServlet">
						<c:param name="command" value="APPROVETRANSFER" />
						<c:param name="transfer_id" value="${theTransfer.transfer_id}" />
					</c:url>
					<c:url var="theLinkDenies" value="CustControlServlet">
						<c:param name="command" value="DENYTRANSFER" />
						<c:param name="transfer_id" value="${theTransfer.transfer_id}" />
					 </c:url>
					<tr>
						<td>${theTransfer.transfer_id}</td>
						<td>${theTransfer.sender_id}</td>
						<td>${theTransfer.target_id}</td>
						<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
								value="${theTransfer.amount}" type="currency" /></td>
						<td><a href="${theLinkApproves}">Approve</a> | <a href="${theLinkDenies}">Deny</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="clear: both;"></div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>

</html>