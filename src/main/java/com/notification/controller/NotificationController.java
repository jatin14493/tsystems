package com.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.exception.NotificationException;
import com.notification.request.Notification;
import com.notification.response.NotificationResponse;
import com.notification.response.ResponseBuilderUtil;
import com.notification.service.NotificationService;
import com.notification.utils.Validator;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    @Qualifier("mailServiceImpl")
    private NotificationService<Notification> notificationService;

    @PostMapping("/send")
    public ResponseEntity<Object> sendNotification(@RequestBody Notification notification,
            @RequestHeader("clientKey") String clientKey) throws NotificationException {
        Validator.validateClientAndRequest(notification, clientKey);
        Validator.checkEligibilityAndUpdate(notification, clientKey);
        notificationService.send(notification);
        return new ResponseEntity<>(
                ResponseBuilderUtil.buildSuccessResponse(NotificationResponse.builder()
                        .message("request process successfully").requestId(notification.getRequestId()).build()),
                HttpStatus.OK);

    }
}
