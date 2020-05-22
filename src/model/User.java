package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String firstName, lastName, username, password, email;
    private Gender gender;
    private Status status;
    private List<User> friends;
    private List<User> friendRequests;
    private Map<User, List<Message>> berichten;
    private int age;
    private String adresFoto;

    public User (String firstName, String lastName, String username, String password, String email, String adresFoto, Gender gender, int age) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setAdresFoto(adresFoto);
        setStatus(Status.OFFLINE);
        setGender(gender);
        setAge(age);
        berichten = new HashMap<>();
        friends = new ArrayList<>();
        friendRequests = new ArrayList<>();
    }

    public User () {
        berichten = new HashMap<>();
        friends = new ArrayList<>();
        friendRequests = new ArrayList<>();
        setStatus(Status.OFFLINE);
    }

    @Override
    public boolean equals(Object o) {
        boolean res = false;
        if (o instanceof User) {
            User user = (User) o;
            if (user.getEmail().equals(this.getEmail())) {
                res = true;
            }
        }
        return res;
    }

    public void addFriend (User user) {
        friendRequests.add(user);
    }

    public void acceptRequest (User user) {
        friends.add(user);
        friendRequests.remove(user);
    }

    public void getAccepted (User user) {
        friends.add(user);
    }

    public void denyRequest (User user) {
        friendRequests.remove(user);
    }

    public boolean goodPass (String password) {
        if (this.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail can't be empty!");
        } else {
            this.email = email;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name can't be empty!");
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name can't be empty");
        } else {
            this.lastName = lastName;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password can't be empty!");
        } else {
            this.password = password;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<User, List<Message>> getBerichten() {
        return berichten;
    }

    public void setBerichten(Map<User, List<Message>> berichten) {
        this.berichten = berichten;
    }

    public String getAdresFoto() {
        return adresFoto;
    }

    public void setAdresFoto(String adresFoto) {
        this.adresFoto = adresFoto;
    }

    public List<User> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(List<User> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender != Gender.MALE && gender != Gender.FEMALE) {
            throw new IllegalArgumentException("Gender must be either male or female!");
        } else {
            this.gender = gender;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if  (age < 0 || age > 100) {
            throw new IllegalArgumentException("Age must be between 0 and a 100");
        } else {
            this.age = age;
        }
    }
}
