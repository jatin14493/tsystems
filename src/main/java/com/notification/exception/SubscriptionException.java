package com.notification.exception;

import com.notification.response.ErrorCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ErrorCodes errorCode;

    public SubscriptionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Subscription Exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public SubscriptionException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new Subscription Exception.
     *
     * @param errorCode the error code
     */
    public SubscriptionException(ErrorCodes errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return String.format("Subscription Exception occurred. message ::" + " %s, error code :: %s",
                super.getMessage(), errorCode);
    }
}
