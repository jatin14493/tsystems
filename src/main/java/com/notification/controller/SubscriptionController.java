package com.notification.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.request.SubscribeRequest;
import com.notification.response.ResponseBuilderUtil;
import com.notification.service.SubscriptionService;

import lombok.extern.slf4j.Slf4j;


/**
 * The type Subscription controller.
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @PostMapping("/subscribe")
    public ResponseEntity<Object> subscribeService(@Valid @RequestBody SubscribeRequest request){
        log.info("Inside subscription-service controller");
        return new ResponseEntity<>(ResponseBuilderUtil.buildSuccessResponse(subscriptionService.subscribe(request)), HttpStatus.OK) ;
    }
}
