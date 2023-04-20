package com.tsudo.notification;

import com.tsudo.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public record NotificationService(NotificationRepository notificationRepository) {
    public void saveNotification(NotificationRequest notificationRequest) {

        Notification n = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .message(notificationRequest.message())
                .sender("tsudo")
                .sentAt(LocalDateTime.now())
                .build();
        notificationRepository.save(n);
        log.info("notification successfully saved {} ", n);

    }

    public void send(NotificationRequest notificationRequest) {

        Notification n = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .message(notificationRequest.message())
                .sender("tsudo")
                .sentAt(LocalDateTime.now())
                .build();
        notificationRepository.save(n);
        log.info("notification successfully saved {} ", n);

    }
}
