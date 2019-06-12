package com.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.notification.response.Response;
import com.notification.response.ResponseBuilderUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Rest response entity exception handler.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Custom exception response entity.
     *
     * @param notificationException the custom notification exception
     * @return the response entity
     */
    @ExceptionHandler(NotificationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Response<Object> notificationExceptionAdvice(NotificationException ex) {
        log.warn("inside notification exception advice. message : {}", ex.getMessage());
        return ResponseBuilderUtil.buildFailureResponse(ex.getErrorCode(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response<Object> validationExceptionAdvice(ValidationException ex) {
        log.warn("inside validation exception advice. message : {}", ex.getMessage());
        return ResponseBuilderUtil.buildFailureResponse(ex.getErrorCode(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(SubscriptionException.class)
    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    @ResponseBody
    public Response<Object> subscriptionExceptionAdvice(SubscriptionException ex) {
        log.warn("inside Subscription Exception advice. message : {}", ex.getMessage());
        return ResponseBuilderUtil.buildFailureResponse(ex.getErrorCode(), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value());
    }
}