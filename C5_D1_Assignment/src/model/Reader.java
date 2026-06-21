package C5_D1_Assignment.src.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import C5_D1_Assignment.src.service.Notifiable;

public class Reader implements Notifiable {
    private String readerId;
    private String name;
    private List<String> notifications = new ArrayList<>();

    public Reader(String readerId, String name) {
        this.readerId = readerId;
        this.name = name;
    }

    @Override
    public void sendNotification(String message) {
        notifications.add(message);
        System.out.println("[" + name + "] " + message);

    }

    @Override
    public List<String> getNotificationHistory() {
        return Collections.unmodifiableList(notifications);
    }

    @Override
    public String toString() {
        return "Reader{id='" + readerId + "', name='" + name + "'}";
    }

    public String getReaderId() {
        return readerId;
    }

    public String getName() {
        return name;
    }

}