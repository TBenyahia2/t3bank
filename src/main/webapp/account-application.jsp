<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Account Application Page</title>
        <link type="text/css" rel="stylesheet" href="css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    </head>

    <body>
       <div id="header" style="text-align:center">
			<h2>
				T<sup>3</sup> Banking
			</h2>
		</div>
        <p>Please enter the requested information and click apply to apply for a new account with
            <b>T<sup>3</sup>Banking</b>.
        </p>

        <h2>New Customer Account Application Form</h2>
        <div class="container">
            <ul>
                <li>Thank you for your inerest in opening a new account with us.</li>
                <li>Your account will be held for review by a member of our staff.</li>
                <li>Upon approval your account will be visible on your Customer Home Page!</li>
            </ul>
            <div id="wrapper">
                <div id="header">
                    <h3>Account registration</h3>
                </div>
                <form action="CustControlServlet" method="GET">
                    <input type="hidden" name="command" value="INSERTACCOUNT" /> <!-- Hidden value hits the switch on CustControlsERVLET -->
                    <input type="hidden" name="user_id" value="${user.user_id}"/>                    
                    <table>
                        <tbody>
                            <tr>
                                <td><label>Initial Deposit:</label></td>
                                <td><input type="number" name="amount" min="0.01" max="999999999" step="0.01" /></td>
                            </tr>
                            <tr>
                                <td><label></label></td>
                                <td><input type="submit" value="Apply" />
                            </tr>
                        </tbody>
                    </table>
                </form>
                <hr>
                <div>
                    <p><a href="CustControlServlet">Back to Customer Home</a></p>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>

    </html>