package com.notification.request;

import java.math.BigInteger;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Notification extends Request {

    private Mail mail;
    private Sms sms;
    private String id;

    public Notification() {
        id = UUID.randomUUID().toString();
    }

    @Getter
    @Setter
    public static class Mail {
        private String mailId;
        private String text;
        private String mailAddress;
    }

    @Getter
    @Setter
    public static class Sms {
        private String smsId;
        private String text;
        private BigInteger mobile;
    }

}
