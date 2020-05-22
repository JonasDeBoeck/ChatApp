package service;

import com.google.gson.JsonObject;
import model.Gender;
import model.Message;
import model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {

    private List<User> users;

    public UserServiceImpl () {
        users = new ArrayList<>();
        User jonas = new User("Jonas", "De Boeck", "JonasDB", "t", "jonasdeboeck@hotmail.be", "img/jonas.jpg", Gender.MALE, 20);
        User thibault = new User("Thibault", "Magnini", "ThibaultM", "t", "thibaultmagnini@hotmail.com", "img/thibault.jpg", Gender.MALE, 20);
        User rafael = new User("Rafael", "Backx", "RafaelB", "t", "rafaelbackx@hotmail.com", "img/rafael.jpg", Gender.MALE, 20);
        users.add(jonas);
        users.add(thibault);
        users.add(rafael);
        jonas.addFriend(thibault);
        jonas.acceptRequest(thibault);
        thibault.getAccepted(jonas);
    }

    public JsonObject getFriends(String email) {
        User user = getUser(email);
        if (user != null) {
            JsonObject friends = getFriendsAsJson(user.getFriends());
            return friends;
        }
        return null;
    }

    public JsonObject getFriendsAsJson(List<User> friends) {
        JsonObject object = new JsonObject();
        for (User user : friends) {
            JsonObject persoon = new JsonObject();
            persoon.addProperty("status", user.getStatus().getStatus());
            object.add(user.getEmail(), persoon);
        }
        return object;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public JsonObject getMessages(String emailReceiver, User sender) {
        List<Message> gezamelijkeMessages = new ArrayList<>();
        if (sender.getBerichten().get(getUser(emailReceiver)) != null) {
            gezamelijkeMessages.addAll(sender.getBerichten().get(getUser(emailReceiver)));
            gezamelijkeMessages.addAll(getUser(emailReceiver).getBerichten().get(sender));
            Collections.sort(gezamelijkeMessages);
        }
        return toJSON(gezamelijkeMessages, sender);
    }

    @Override
    public JsonObject getFriendRequests(String email) {
        User user = getUser(email);
        JsonObject json = new JsonObject();
        for (User request : user.getFriendRequests()) {
            JsonObject fRequest = new JsonObject();
            fRequest.addProperty("email", request.getEmail());
            json.add(request.getEmail(), fRequest);
        }
        return json;
    }

    public JsonObject toJSON (List<Message> messages, User sender) {
        JsonObject json = new JsonObject();
        for (Message message : messages) {
            JsonObject bericht = new JsonObject();;
            bericht.addProperty("content", message.getContent());
            bericht.addProperty("role", sender.equals(message.getSender()) ? "sender" : "receiver");
            json.add(message.getLocalDateTime().toString(), bericht);
        }
        return json;
    }

    @Override
    public void addMessage(String emailSender, String emailReceiver, String message) {
        User sender = getUser(emailSender);
        if (!sender.getBerichten().containsKey(getUser(emailReceiver))) {
            sender.getBerichten().put(getUser(emailReceiver), new ArrayList<>());
            sender.getBerichten().get(getUser(emailReceiver)).add(new Message(message, getUser(emailSender), getUser(emailReceiver)));
        } else {
            sender.getBerichten().get(getUser(emailReceiver)).add(new Message(message, getUser(emailSender), getUser(emailReceiver)));
        }
        User receiver = getUser(emailReceiver);
        if (!receiver.getBerichten().containsKey(getUser(emailSender))) {
            receiver.getBerichten().put(getUser(emailSender), new ArrayList<>());
            receiver.getBerichten().get(getUser(emailSender)).add(new Message(message, getUser(emailSender), getUser(emailReceiver)));
        } else {
            receiver.getBerichten().get(getUser(emailSender)).add(new Message(message, getUser(emailSender), getUser(emailReceiver)));
        }
    }

    @Override
    public void addUser(User user) {
        if (this.users.contains(user)) {
            throw new IllegalArgumentException("User already exists");
        } else {
            this.users.add(user);
        }
    }

    @Override
    public void updateUser(User user, String firstName, String lastName, int age, Gender gender) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setUsername(createUsername(firstName, lastName));
        user.setGender(gender);
    }

    private String createUsername(String firstName, String lastName) {
        String[] partsOfLastName = lastName.split(" ");
        String username = firstName;
        for (String part : partsOfLastName) {
            username += part.substring(0, 1).toUpperCase();
        }
        return username;
    }
}
