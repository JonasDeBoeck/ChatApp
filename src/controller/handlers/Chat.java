package controller.handlers;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Chat extends RequestHandler{

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("receiver", userService.getUser(request.getParameter("receiver")));
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            request.setAttribute("friends", user.getFriends());
            request.getRequestDispatcher("chat.jsp").forward(request, response);
        }  else {
            request.setAttribute("error", "You need to be logged in to have access to this feature");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
