package com.notification.dao.database;

import java.util.HashMap;
import java.util.LinkedHashMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Subscription data. this class data will be fetched from database in
 * real time. Because of time constraints, not including Database.
 */
public final class SubscriptionDataDetails {

    /**
     * The enum Subscription type.
     */
    public enum SubscriptionType {
        /**
         * Gold subscription type.
         */
        GOLD,
        /**
         * Silver subscription type.
         */
        SILVER,
        /**
         * Platinum subscription type.
         */
        PLATINUM;

    }

    /**
     * The Subscription data.
     */
    static public HashMap<SubscriptionType, SubscriptionDetails> subscriptionPlanData = new LinkedHashMap<>();
    static {

        subscriptionPlanData.put(SubscriptionType.GOLD, new SubscriptionDetails(10l, true, true, 99));
        subscriptionPlanData.put(SubscriptionType.SILVER, new SubscriptionDetails(10000l, true, false, 49));
        subscriptionPlanData.put(SubscriptionType.PLATINUM, new SubscriptionDetails(1000000000l, true, true, 500));
    }

    /**
     * The type Subscription details.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class SubscriptionDetails {
        private Long count;
        private boolean email;
        private boolean sms;
        private double amountPerMonth;

        /**
         * Instantiates a new Subscription details.
         *
         * @param count the count
         * @param email the email
         * @param sms   the sms
         */
        public SubscriptionDetails(Long count, boolean email, boolean sms, double amountPerMonth) {
            this.count = count;
            this.email = email;
            this.sms = sms;
            this.amountPerMonth = amountPerMonth;
        }
    }

}
