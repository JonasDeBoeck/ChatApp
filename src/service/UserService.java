package service;

import com.google.gson.JsonObject;
import model.Gender;
import model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUser (String email);
    JsonObject getFriends(String email);
    JsonObject getMessages (String emailReceiver, User sender);
    JsonObject getFriendRequests (String email);
    void addMessage (String emailSender, String emailReceiver, String message);
    void addUser(User user);
    void updateUser(User user, String firstName, String lastName, int age, Gender gender);
}
