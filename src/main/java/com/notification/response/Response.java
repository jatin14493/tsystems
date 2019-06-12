package com.notification.response;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.notification.exception.Error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String requestId;
    private  int status;
    private int resultCode;
    private T data;
    private List<Error> error;
}
