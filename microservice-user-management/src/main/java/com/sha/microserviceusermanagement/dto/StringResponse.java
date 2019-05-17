package com.sha.microserviceusermanagement.dto;

public class StringResponse {

    private String response;

    public StringResponse(String r){
        this.response = r;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
