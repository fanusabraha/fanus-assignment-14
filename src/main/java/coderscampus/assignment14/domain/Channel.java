package coderscampus.assignment14.domain;

import org.apache.logging.log4j.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private String username;
    private int id;
    private List<Message> messages = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
