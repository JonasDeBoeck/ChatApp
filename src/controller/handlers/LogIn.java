package controller.handlers;

import model.Status;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogIn extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = userService.getUser(request.getParameter("email"));
        if (user != null) {
            if (user.goodPass(request.getParameter("password"))) {
                session.setAttribute("user", user);
                user.setStatus(Status.ONLINE);
                response.sendRedirect("Servlet?command=ShowFriends");
            } else {
                request.setAttribute("error", "Password/E-mail doesnt match");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "No account with this e-mail address exists");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
