package controller.handlers;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {
    protected UserService userService;

    public void setUserService (UserService userService) {
        this.userService = userService;
    }

    public abstract void handleRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
