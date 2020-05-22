package controller;

import controller.handlers.RequestHandler;
import controller.handlers.RequestHandlerFactory;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request,response);
    }

    protected void doOptions (HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Accept-Language, Origin, User-Agent");
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response){
        String command = request.getParameter("command") != null ? request.getParameter("command") : "";
        RequestHandler handler = RequestHandlerFactory.createHandler(command);
        try {
            handler.setUserService(this.userService);
            handler.handleRequest(request,response);
        } catch (NullPointerException | ServletException | IOException e){
            e.printStackTrace();
        }
    }
}
