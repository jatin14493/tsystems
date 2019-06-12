package com.notification.exception;

import org.springframework.http.HttpStatus;

import com.notification.response.ErrorCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ErrorCodes errorCode;

    public NotificationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Notification Exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public NotificationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new Notification Exception.
     *
     * @param errorCode the error code
     */
    public NotificationException(ErrorCodes errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }


    public NotificationException(String message, int value, HttpStatus badRequest) {
        super(message);
        
        
    }

    @Override
    public String getMessage() {
        return String.format("Notification Exception occurred. message ::" + " %s, error code :: %s",
                super.getMessage(), errorCode);
    }
}
