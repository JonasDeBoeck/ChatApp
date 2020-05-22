package controller.handlers;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetMessages extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            response.getWriter().write(userService.getMessages(request.getParameter("receiver"), user).toString());
        } else {
            request.setAttribute("error", "You need to be logged in to have access to this feature");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
