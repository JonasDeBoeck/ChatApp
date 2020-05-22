<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 31/03/2020
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
    <link rel="stylesheet" href="reset.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body class="h-100">
<header role="banner">
    <nav class="navbar navbar-dark bg-dark d-flex justify-content-between">
        <div class="navbar-brand d-flex align-items-center">
            <img src="https://img.icons8.com/nolan/64/membership-card.png"/>
            Chat
        </div>
    </nav>
</header>
<main class="container w-100 mt-5">
    <div class="row w-100 d-flex justify-content-center">
        <div class="col-sm-3 w-100 pr-0">
            <div class="w-100 list-group">
                <c:forEach var="friend" items="${friends}">
                    <div class="list-group-item">
                        <img src="img/moreInfoFriends.png" class="moreInfo" id="${friend.username}"/>
                        <p class="d-inline-block openChat" id="${friend.email}">${friend.username}</p>
                        <div id="${friend.username}MoreInfo" style="display: none" class="mb-0">
                            <div class="d-flex flex-column align-items-center">
                                <img src="${friend.adresFoto}" alt="Foto van ${friend.username}" style="width: 100px; height: 100px; border-radius: 50%">
                                <p>Is ${friend.age} years old</p>
                                <p>${friend.gender}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-sm-9 w-100 pl-1">
            <div class="list-group">
                <div class="list-group-item">
                    <p class="mb-0" id="receiver">Now chatting with: ${sessionScope.receiver.email}</p>
                </div>
                <div id="messages" class="list-group-item">

                </div>
                <div class="list-group list-group-item">
                    <div class="form-group">
                        <label for='message'>Message</label>
                        <input type='text' name='message' id='message' class="form-control">
                    </div>
                    <button id="sendMessage" class="btn btn-primary">Send message</button>
                </div>
            </div>
        </div>
    </div>
</main>
<footer class="navbar navbar-dark bg-dark d-flex justify-content-between mt-3 footer fixed-bottom">
	<span class="navbar-brand"> &copy; Webontwikkeling 4, UC
		Leuven-Limburg
	</span>
    <form action="Servlet?command=LogOut" method="POST">
        <input type="submit" value="Log out" class="btn btn-primary">
    </form>
</footer>
<script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="chat.js" type="application/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
