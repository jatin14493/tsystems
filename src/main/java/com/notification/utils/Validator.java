package com.notification.utils;

import java.util.List;

import org.springframework.util.StringUtils;

import com.notification.dao.database.ClientDetails;
import com.notification.dao.database.SubscriptionDataDetails;
import com.notification.exception.SubscriptionException;
import com.notification.exception.ValidationException;
import com.notification.request.Notification;
import com.notification.response.ErrorCodes;

import lombok.experimental.UtilityClass;

/**
 * The type Validator. Utility class
 */
@UtilityClass
public class Validator {

    public void validateClientAndRequest(Notification notification, String clientKey) {
        if (notification == null) {
            throw new ValidationException(ErrorCodes.MAIL_MESSGAE_NOT_PRESENT);
        }
        if (clientKey == null || ClientDetails.clients.get(clientKey) == null) {
            throw new ValidationException(ErrorCodes.CLIENT_AUTHENTICATION_FAILED);
        }
    }

    public void checkEligibilityAndUpdate(Notification notification, String clientKey) {
        ClientDetails.Client client = ClientDetails.clients.get(clientKey);
        List<ClientDetails.PlanDetails> planDetailsList = client.getSubsDetails();
        ClientDetails.PlanDetails planDetail = planDetailsList.get(0);
        if (planDetailsList.size() == 1) {
            if (StringUtils.endsWithIgnoreCase(SubscriptionDataDetails.SubscriptionType.GOLD.name(),
                    planDetail.getSubscriptionType().name())) {
                planDetail.setCurrentUses(planDetail.getCurrentUses() + 1);

            } else {
                SubscriptionDataDetails.SubscriptionDetails subscriptionDetails = SubscriptionDataDetails.subscriptionPlanData
                        .get(planDetailsList.get(0).getSubscriptionType());
                planDetail.setCurrentUses(planDetail.getCurrentUses() + 1);
                Long count = subscriptionDetails.getCount();
                if (count < planDetail.getCurrentUses()) {
                    throw new SubscriptionException(ErrorCodes.SUBSCRIPTION_LIMIT_EXPIRED);
                }
            }
        }
    }
}
