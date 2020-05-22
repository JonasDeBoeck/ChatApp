<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05/05/2020
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="reset.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<header role="banner">
    <nav class="navbar navbar-dark bg-dark d-flex justify-content-between">
        <div class="navbar-brand d-flex align-items-center">
            <img src="https://img.icons8.com/nolan/64/membership-card.png" class="d-inline-block align-top pr-2"/>
            Register
        </div>
    </nav>
</header>
<main class="d-flex justify-content-center mt-5">
    <div class="w-75 h-75">
        <h2>
            Log In
        </h2>
        <c:if test="${not empty errors}">
        <div class="danger">
            <c:forEach items="${errors}" var="error">
                <p class="alert alert-danger" role="alert">${error}</p>
            </c:forEach>
        </div>
        </c:if>
        <form method="POST" action="Servlet?command=Register">
            <p class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First Name">
            </p>
            <p class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Last Name">
            </p>
            <p class="form-group">
                <label for="email">Your email</label>
                <input type="text" id="email" name="email" class="form-control" placeholder="E-mail">
            </p>
            <p class="form-group">
                <label for="age">Age</label>
                <input type="number" id="age" name="age" class="form-control" placeholder="Age" min="0" max="100">
            </p>
            <p class="form-group">
                <label for="password">Your password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password">
            </p>
            <p class="form-group">
                <label for="verifyPassword">Verify password</label>
                <input type="password" id="verifyPassword" name="verifyPassword" class="form-control" placeholder="Verify Password">
            </p>
            <p class="form-group">
                <label for="gender">Gender</label>
                <select class="form-control" name="gender" id="gender">
                    <option>Male</option>
                    <option>Female</option>
                </select>
            </p>
            <p class="form-group">
                <input type="submit" id="registerButton" value="Register" class="btn btn-primary">
            </p>
        </form>
    </div>
</main>
<footer class="navbar navbar-dark bg-dark d-flex justify-content-between mt-3 footer sticky-bottom">
	<span class="navbar-brand"> &copy; Webontwikkeling 4, UC
		Leuven-Limburg
	</span>
    <div>
        <a href="Servlet?command=ShowLogIn" class="btn btn-primary" style="display: block">Back</a>
    </div>
</footer>
</body>
</html>
