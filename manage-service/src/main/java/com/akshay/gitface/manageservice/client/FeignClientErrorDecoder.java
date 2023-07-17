package com.akshay.gitface.manageservice.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

public class FeignClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 404 -> new NotFoundException();
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}