package com.notification.response;

import static  com.notification.dao.database.ClientDetails.PlanDetails;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


@Data
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionResponse  {
   private String clientKey;
   private String clientName;
   private List<PlanDetails> subscribePlanDetails;
}
