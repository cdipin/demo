package com.example.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatusCode statusCode;

    private String errorMessage;

}
