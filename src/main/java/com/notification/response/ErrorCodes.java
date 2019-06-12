package com.notification.response;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    MAIL_MESSGAE_NOT_PRESENT("ValidationException_01", "Mail or message should be present in request"),
    CLIENT_AUTHENTICATION_FAILED("ValidationException_02", "Authentication failed : client key missing or invalid"),
    SUBSCRIPTION_LIMIT_EXPIRED("SubscriptionException_01", "Subscription / Limit Expired"),;

    private String code;
    private String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
