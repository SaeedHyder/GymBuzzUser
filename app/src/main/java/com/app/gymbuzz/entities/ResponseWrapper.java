package com.app.gymbuzz.entities;

public class ResponseWrapper<T> {

    private String message;
    private String response;
    private T result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        response = response;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        result = result;
    }
}
