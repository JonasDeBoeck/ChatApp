package controller.handlers;

import com.google.gson.JsonObject;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetUsers extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getUsers();
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(toJson(users));
    }

    public String toJson(List<User> users) {
        String json = "[";
        for (User user : users) {
            json += "{ \"firstName\": " + "\"" + user.getFirstName() + "\"" + ", \"lastName\": " + "\"" + user.getLastName() + "\"" + ", \"email\": " + "\"" + user.getEmail() + "\"" + ", \"gender\": " + "\"" + user.getGender().getGender() + "\"" + ", \"age\": " + user.getAge() + " }, ";
        }
        return  json.substring(0, json.length() - 2) + "]";
    }
}
