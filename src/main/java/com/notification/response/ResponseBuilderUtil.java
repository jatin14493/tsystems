package com.notification.response;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.notification.exception.Error;



public class ResponseBuilderUtil {

    private static final int SUCCESS = 1;
    private static final int FAIL = 0;

    
    public static <T> Response<T> buildSuccessResponse(T data) {
        return Response.<T>builder().status(HttpStatus.OK.value()).data(data).resultCode(SUCCESS).build();
    }

    
    public static <T> Response<T> buildFailureResponse(ErrorCodes errorCodes) {
        Error errors = Error.builder().errorCode(errorCodes.getCode()).errorMessage(errorCodes.getMessage()).build();
        return buildFailureResponse(Collections.singletonList(errors));
    }

    
    public static <T> Response<T> buildFailureResponse(Error error) {
        return buildFailureResponse(Collections.singletonList(error));
    }

    
    public static <T> Response<T> buildFailureResponse(List<Error> errors) {
        return Response.<T>builder().resultCode(FAIL).error(errors).build();
    }

    public static <T> Response<T> buildFailureResponse(List<Error> errors, int httpStatusValue) {
        return Response.<T>builder().status(httpStatusValue).error(errors).resultCode(FAIL).build();
    }

//    public static List<Error> buildFieldErrors(Error errors) {
//        return errors.getFieldErrors().stream().map(t -> new Errors(t.getField(), t.getCode()))
//                .collect(Collectors.toList());
//    }

    public static Response<Object> buildFailureResponse(ErrorCodes errorCodes, int httpStatusValue) {
        Error errors = Error.builder().errorCode(errorCodes.getCode()).errorMessage(errorCodes.getMessage()).build();
        return buildFailureResponse(Collections.singletonList(errors), httpStatusValue);
    }
    
   
}