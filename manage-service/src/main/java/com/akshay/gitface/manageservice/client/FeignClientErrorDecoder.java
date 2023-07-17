package com.akshay.gitface.manageservice.client;

import com.akshay.gitface.manageservice.exceptions.GFException;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

public class FeignClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 400 -> new GFException("Bad Request: Please provide correct input");
            case 404 -> new GFException("Not Found requested resource");
            default -> getDecode(methodKey, response);
        };
    }

    private Exception getDecode(String methodKey, Response response) {
        final Exception decode = errorDecoder.decode(methodKey, response);
        return new GFException(decode.getMessage());
    }
}