package com.murat.kredibasvuru.notification;

public interface NotificationSender {
    void send(String to, String message);
}
