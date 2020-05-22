package controller.handlers;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HandleFriendRequest extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String emailOtherUser = request.getParameter("email");
            String action = request.getParameter("action");
            User otherUser = userService.getUser(emailOtherUser);
            if (action.equals("Accept")) {
                user.acceptRequest(otherUser);
                otherUser.getAccepted(user);
                response.getWriter().write("Succesfully added: " + otherUser.getEmail());
            } else {
                user.denyRequest(otherUser);
                response.getWriter().write("Succesfully denied: " + otherUser.getEmail());
            }
        } else {
            request.setAttribute("error", "You need to be logged in to have access to this feature");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
