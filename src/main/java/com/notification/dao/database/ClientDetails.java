package com.notification.dao.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Client Details. this will work as database
 */
public class ClientDetails {
    /**
     * key=clinetid Client=Data
     */
    static public HashMap<String, Client> clients = new LinkedHashMap<>();;
    static {

        clients.put("e92674c2-54da-441b-aa35-40dd8ae73e6f", new Client("JATIN", "mahajanjatin14@gmail.com", "Jatin", Collections
                .singletonList(new PlanDetails(SubscriptionDataDetails.SubscriptionType.GOLD, 1, 1 * 99, 0l))));
    }

    /**
     * The type Client.
     */
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Client {
        private String clientName;
        private String clientUserName;
        private String clientPassword;
        private List<PlanDetails> subsDetails;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PlanDetails {
        private SubscriptionDataDetails.SubscriptionType subscriptionType;
        private int month;
        private double amount;
        private Long currentUses;
    }
}
