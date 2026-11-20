package com.murat.kredibasvuru.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsNotificationSender implements NotificationSender {
    public void send(String to, String message) {
        log.info("Sms gönderildi -> to: {}, message: {}", to, message);
    }
}
