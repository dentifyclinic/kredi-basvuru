package com.murat.kredibasvuru.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Primary
public class EmailNotificationSender implements NotificationSender {

    @Override
    public void send(String to, String message) {
        log.info("E-posta gönderildi -> to: {}, message: {}", to, message);
    }
}
