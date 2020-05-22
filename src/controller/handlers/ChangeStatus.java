package controller.handlers;

import model.Status;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeStatus extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.getWriter().write(request.getParameter("status"));
            User user = (User) session.getAttribute("user");
            user.setStatus(Status.valueOf(request.getParameter("status").toUpperCase()));
        } else {
            request.setAttribute("error", "You need to be logged in to have access to this feature");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
