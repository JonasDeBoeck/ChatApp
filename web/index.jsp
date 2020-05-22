<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="h-100">
<head>
  <meta charset="UTF-8">
  <title>Log In</title>
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
<body class="h-100">
<header role="banner">
  <nav class="navbar navbar-dark bg-dark">
    <div class="navbar-brand d-flex align-items-center">
      <img src="https://img.icons8.com/nolan/64/google-blog-search.png"
           class="d-inline-block align-top pr-2"/>
      Blog
    </div>
  </nav>
</header>
<main class="d-flex justify-content-center mt-5">
  <div class="w-75 h-75">
    <h2>
      Log In
    </h2>
      <c:if test="${error != null}">
          <div class="danger">
            <p class="alert alert-danger" role="alert">${error}</p>
          </div>
      </c:if>
      <c:if test="${user == null}">
          <form method="POST" action="Servlet?command=LogIn">
              <p class="form-group">
                  <label for="email">Your email </label>
                  <input type="text" id="email" name="email" class="form-control" placeholder="E-mail">
              </p>
              <p class="form-group">
                  <label for="password">Your password</label>
                  <input type="password" id="password" name="password" class="form-control" placeholder="Password">
              </p>
              <p class="form-group">
                  <input type="submit" id="loginbutton" value="Log in" class="btn btn-primary">
              </p>
          </form>
      </c:if>
    <c:if test="${user != null}">
      <a href="Servlet?command=ShowFriends" style="display: block" class="btn btn-primary">Go to friends page</a>
    </c:if>
    <a href="Servlet?command=ShowRegister" class="btn btn-primary">Register</a>
    <div id="topics" class="mt-4 list-group">
      <div class="list-group-item">
        <h2 class="h3 text-primary">
          Was het een interessante projectweek?
        </h2>
        <div>
          <ul class="list-group" id="proj">
          </ul>
        </div>
        <div class="form-group">
          <p>
            <label for="projName">Naam</label>
            <input type="text" id="projName" required class="form-control">
          </p>
          <p>
            <label for="projComment">Comment</label>
            <textarea name="comment" id="projComment" cols="30" rows="10" style="resize: none" required class="form-control"></textarea>
          </p>
          <p>
            <label for="projRating">Rating</label>
            <input type="number" min="1" max="10" id="projRating" class="form-control" required>
          </p>
          <button class="btn btn-primary comment" id="projButton">Comment</button>
        </div>
      </div>
      <div class="list-group-item">
        <h2 class="h3 text-primary">Wat ben je van plan om te doen vandaag?</h2>
        <div>
          <ul class="list-group" id="doe">
          </ul>
        </div>
        <div class="form-group">
          <p>
            <label for="doeName">Naam</label>
            <input type="text" id="doeName" required class="form-control">
          </p>
          <p>
            <label for="doeComment">Comment</label>
            <textarea name="comment" id="doeComment" cols="30" rows="10" style="resize: none" required class="form-control"></textarea>
          </p>
          <p>
            <label for="doeRating">Rating</label>
            <input type="number" min="1" max="10" id="doeRating" class="form-control" required>
          </p>
          <button class="btn btn-primary comment" id="doeButton">Comment</button>
        </div>
      </div>
      <div class="list-group-item">
        <h2 class="h3 text-primary">Naar welke muziek ben je momenteel aan het luisteren?</h2>
        <div>
          <ul class="list-group" id="muz">
          </ul>
        </div>
        <div class="form-group">
          <p>
            <label for="muzName">Naam</label>
            <input type="text" id="muzName" required class="form-control">
          </p>
          <p>
            <label for="muzComment">Comment</label>
            <textarea name="comment" id="muzComment" cols="30" rows="10" style="resize: none" required class="form-control"></textarea>
          </p>
          <p>
            <label for="muzRating">Rating</label>
            <input type="number" min="1" max="10" id="muzRating" class="form-control" required>
          </p>
          <button class="btn btn-primary comment" id="muzButton">Comment</button>
        </div>
      </div>
      <div class="list-group-item">
        <h2 class="h3 text-primary">Wat zijn de examenvragen voor het vak web4?</h2>
        <div>
          <ul class="list-group" id="ex">
          </ul>
        </div>
        <div class="form-group" novalidate>
          <p>
            <label for="exName">Naam</label>
            <input type="text" id="exName" required class="form-control">
          </p>
          <p>
            <label for="exComment">Comment</label>
            <textarea name="comment" id="exComment" cols="30" rows="10" style="resize: none" required class="form-control"></textarea>
          </p>
          <p>
            <label for="exRating">Rating</label>
            <input type="number" min="1" max="10" id="exRating" class="form-control" required>
          </p>
          <button class="btn btn-primary comment" id="exButton">Comment</button>
        </div>
      </div>
      <div class="list-group-item">
        <h2 class="h3 text-primary">Wat is jouw mening over het corona virus?</h2>
        <div>
          <ul class="list-group" id="cor">
          </ul>
        </div>
        <div class="form-group">
          <p>
            <label for="corName">Naam</label>
            <input type="text" id="corName" required class="form-control">
          </p>
          <p>
            <label for="corComment">Comment</label>
            <textarea name="comment" id="corComment" cols="30" rows="10" style="resize: none" required class="form-control"></textarea>
          </p>
          <p>
            <label for="corRating">Rating</label>
            <input type="number" min="1" max="10" id="corRating" class="form-control" required>
          </p>
          <button class="btn btn-primary comment" id="corButton">Comment</button>
        </div>
      </div>
    </div>
  </div>
</main>
<footer class="navbar navbar-dark bg-dark mt-4">
	<span class="navbar-brand"> &copy; Webontwikkeling 4, UC
		Leuven-Limburg
	</span>
</footer>
<script src="blog.js"></script>
</body>
</html>