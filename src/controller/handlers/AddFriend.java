package controller.handlers;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddFriend extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            User friend = userService.getUser(request.getParameter("email"));
            if (friend != null) {
                friend.addFriend(user);
                response.getWriter().write(friend.getEmail());
            } else {
                response.getWriter().write("A user with name: " + request.getParameter("email") + "could not be found");
            }
        } else {
            request.setAttribute("error", "You need to be logged in to have access to this feature");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
