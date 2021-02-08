<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Validation</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>	
	<c:if test="${not empty param.myuser_id and not empty param.mypassword)">  
		<s:setDataSource var="dbconnector" driver="org.postgresql.Driver" url="jdbc:postgresql://localhost/tcubedbanking_db"
						user="postgres" password="password" />
		<s:query dataSource="${dbconnector}" var="result">
			SELECT role FROM users WHERE user_id = ? AND password = crypt(?, password)
			<s:param value="${user_id}" />
			<s:param value="${password}"/>
		</s:query>
		<c:forEach var="row" items="${validateLogin.row}">
				<c:if test="${row.role eq 1}"> <!-- when role == 1 -->
					<c:set scope="session" var="user_id" value="${param.user_id}" />
					<c:redirect url="CustControlServlet" />
				</c:if>
				<c:if test="${row.role eq 2}"> <!-- when role == 1 -->
					<c:set scope="session" var="user_id" value="${param.user_id}" />
					<c:redirect url="CustControlServlet" />
				</c:if>
				<c:redirect url="index.jsp">
					<c:param name="redirectMsg" value="User ID / Password does not match" />
				</c:redirect>
		</c:forEach>
	</c:if>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>