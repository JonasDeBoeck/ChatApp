<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 31/03/2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Friends</title>
    <link rel="stylesheet" href="reset.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body class="h-100">
<header role="banner">
    <nav class="navbar navbar-dark bg-dark d-flex justify-content-between">
        <div class="navbar-brand d-flex align-items-center">
            <img src="https://img.icons8.com/nolan/64/trust.png" class="d-inline-block align-top pr-2"/>
            Friends
        </div>
        <div class="form-inline">
            <input class="form-control mr-sm-2" placeholder="E-mail friend" aria-label="Search" id="addFriend">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="addFriendButton">Add friend</button>
        </div>
    </nav>
</header>
<main class="d-flex justify-content-center mt-3">
    <div class="w-75" id="mainContent">
        <div class="alert alert-primary" role="alert">
            <p>Welcome ${sessionScope.user.username}</p>
            <p class="mb-0">Your status: ${sessionScope.user.status.status}</p>
        </div>
        <div>
            <div>
                <h2>Change status</h2>
                <p id="Other">
                    <label for="status">Status</label>
                    <select name="status" id="status" class="form-control">
                        <option value="Online">Online</option>
                        <option value="Offline">Offline</option>
                        <option value="Away">Away</option>
                        <option value="Other">Other</option>
                    </select>
                </p>
            </div>
        </div>
        <div>
            <h2>Friends list</h2>
            <table class="table table-striped table-bordered table-hover mt-3" id="friendsTable">
                <caption>List of friends</caption>
                <thead>
                <tr>
                    <th>E-mail</th>
                    <th>Status</th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="friendsBody">

                </tbody>
            </table>
        </div>
        <div>
            <h2>Friend requests</h2>
            <table class="table table-striped table-bordered table-hover mt-3" id="friendRequestsTable">
                <caption>List of friend requests</caption>
                <thead>
                <tr>
                    <th>Email</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="friendRequestsBody">

                </tbody>
            </table>
        </div>
    </div>
</main>
<footer class="navbar navbar-dark bg-dark d-flex justify-content-between mt-3 footer fixed-bottom">
	<span class="navbar-brand"> &copy; Webontwikkeling 4, UC
		Leuven-Limburg
	</span>
    <div class="d-flex">
        <a href="Servlet?command=ShowLogIn" style="display: block" class="btn btn-primary mr-2">Go to blog</a>
        <form action="Servlet?command=LogOut" method="POST">
            <input type="submit" value="Log out" class="btn btn-primary">
        </form>
    </div>
</footer>
<script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="friends.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
