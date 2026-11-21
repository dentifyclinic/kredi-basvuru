package com.murat.kredibasvuru.application;

import com.murat.kredibasvuru.notification.NotificationSender;

import java.util.ArrayList;
import java.util.List;

public class FakeNotificationSender implements NotificationSender {
    private final List<String> messages = new ArrayList<>();

    @Override
    public void send(String to, String message) {
        messages.add(to + ": " + message);
    }

    public List<String> getSendMessages() {
        return messages;
    }
}
