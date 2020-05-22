package controller.handlers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Gender;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class UpdateUser extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Accept-Language, Origin, User-Agent");
        
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(buffer.toString()).getAsJsonObject();

        User user = userService.getUser(json.get("email").getAsString());
        userService.updateUser(user, json.get("firstName").getAsString(), json.get("lastName").getAsString(), json.get("age").getAsInt(), Gender.valueOf(json.get("gender").getAsString().toUpperCase()));

        response.getWriter().write(userToJson(user).toString());
    }

    JsonObject userToJson(User user) {
        JsonObject json = new JsonObject();
        json.addProperty("firstName", user.getFirstName());
        json.addProperty("lastName", user.getLastName());
        json.addProperty("email", user.getEmail());
        json.addProperty("gender", user.getGender().getGender());
        json.addProperty("age", user.getAge());
        return json;
    }
}
