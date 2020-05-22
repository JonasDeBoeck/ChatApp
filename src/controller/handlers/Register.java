package controller.handlers;

import model.Gender;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Register extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errors = new ArrayList<>();
        User user = new User();
        setEmail(user, request, errors);
        setFirstName(user, request, errors);
        setLastName(user, request, errors);
        setAge(user, request, errors);
        setPassword(user, request, errors);
        setGender(user, request, errors);
        if (errors.isEmpty()) {
            user.setUsername(createUsername(user.getFirstName(), user.getLastName()));
            user.setAdresFoto("img/default_profile.png");
            try {
                userService.addUser(user);
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            response.sendRedirect("Servlet?command=ShowLogIn");
        } else {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    public void setEmail (User user, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            user.setEmail(email);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setFirstName (User user, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            user.setFirstName(firstName);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setLastName (User user, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            user.setLastName(lastName);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setAge (User user, HttpServletRequest request, ArrayList<String> errors) {
        if (request.getParameter("age").trim().isEmpty()) {
            errors.add("Age can't be empty");
        } else {
            int age = Integer.parseInt(request.getParameter("age"));
            try {
                user.setAge(age);
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
    }

    public void setPassword (User user, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        String verifyPassword = request.getParameter("verifyPassword");
        if (!password.equals(verifyPassword)) {
            errors.add("Passwords don't match");
        } else {
            try {
                user.setPassword(password);
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
    }

    public void setGender (User user, HttpServletRequest request, ArrayList<String> errors) {
        String gender = request.getParameter("gender");
        try {
            user.setGender(Gender.valueOf(gender.toUpperCase()));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public String createUsername(String firstName, String lastName) {
        String[] partsOfLastName = lastName.split(" ");
        String username = firstName;
        for (String part : partsOfLastName) {
            username += part.substring(0, 1).toUpperCase();
        }
        return username;
    }
}
