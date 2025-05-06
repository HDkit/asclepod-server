package com.hms.microservices.patient_service.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    Boolean success;
    String message;
    T data;

    public Response(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
