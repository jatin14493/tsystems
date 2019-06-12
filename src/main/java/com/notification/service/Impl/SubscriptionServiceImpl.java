package com.notification.service.Impl;

import static com.notification.dao.database.ClientDetails.Client;
import com.notification.dao.database.ClientDetails;
import com.notification.dao.database.SubscriptionDataDetails;
import com.notification.request.SubscribeRequest;
import com.notification.response.Response;
import com.notification.response.ResponseBuilderUtil;
import com.notification.response.SubscriptionResponse;
import com.notification.service.SubscriptionService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Subscription service.
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public Response<?> subscribe(SubscribeRequest subscribeRequest) {
        /**
         * TODO need to validate plan in real time
         */
        List<ClientDetails.PlanDetails> planDetails = getPlanDetails(subscribeRequest);
        Client client = Client.builder().clientName(subscribeRequest.getClientName())
                .clientPassword(subscribeRequest.getClientPassword())
                .clientUserName(subscribeRequest.getClientUserName()).subsDetails(getPlanDetails(subscribeRequest))
                .build();
        ClientDetails.clients.put(subscribeRequest.getRequestId(), client);

        /*
         * return response to client with subscribe details
         */
        SubscriptionResponse subscriptionResponse = SubscriptionResponse.builder()
                .clientKey(subscribeRequest.getRequestId()).clientName(subscribeRequest.getClientName())
                .subscribePlanDetails(planDetails).build();
        return ResponseBuilderUtil.buildSuccessResponse(subscriptionResponse);

    }

    private List<ClientDetails.PlanDetails> getPlanDetails(SubscribeRequest subscribeRequest) {
        List<SubscribeRequest.SubscribePlan> subscribePlans = subscribeRequest.getSubscribePlan();
        List<ClientDetails.PlanDetails> planDetailsList = new ArrayList<>();
        subscribePlans.forEach(subscribePlan -> {
            SubscriptionDataDetails.SubscriptionDetails subscriptionDetails = SubscriptionDataDetails.subscriptionPlanData
                    .get(SubscriptionDataDetails.SubscriptionType.valueOf(subscribePlan.getSubsCribeType()));
            ClientDetails.PlanDetails planDetails = ClientDetails.PlanDetails.builder()
                    .amount(subscribePlan.getMonth() * subscriptionDetails.getAmountPerMonth()).currentUses(0L)
                    .subscriptionType(
                            SubscriptionDataDetails.SubscriptionType.valueOf(subscribePlan.getSubsCribeType()))
                    .build();
            planDetailsList.add(planDetails);
        });
        return planDetailsList;
    }
}
