package model;

import java.time.LocalDateTime;

public class Message implements Comparable<Message>{
    private LocalDateTime localDateTime;
    private String content;
    private User sender, receiver;

    public Message (String content, User sender, User receiver) {
        this.localDateTime = LocalDateTime.now();
        setContent(content);
        setSender(sender);
        setReceiver(receiver);
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Message message) {
        return this.localDateTime.compareTo(message.getLocalDateTime());
    }
}
