package com.tsudo.notification;

import com.tsudo.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/notifications")
public record NotificationController(NotificationService notificationService) {
    @PostMapping
    public String createNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("saving a notification {}", notificationRequest);
        notificationService.saveNotification(notificationRequest);

        return "success";
    }
}
