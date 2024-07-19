package coderscampus.assignment14.domain;

import org.apache.logging.log4j.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private String username;
    private String id;
    private List<Message> messages = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}