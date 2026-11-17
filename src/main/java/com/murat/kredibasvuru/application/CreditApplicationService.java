package com.murat.kredibasvuru.application;

import com.murat.kredibasvuru.notification.NotificationSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CreditApplicationService {
    private final NotificationSender notificationSender;

    public CreditApplicationService(@Qualifier("smsNotificationSender") NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    public UUID submit(String customerName, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be null or blank");
        }

        UUID applicationId = UUID.randomUUID();
        notificationSender.send(customerName, "Başvurunuz alındı. No: " + applicationId);
        return applicationId;
    }
}
