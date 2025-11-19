package com.app.backend.dto;

public class MessageResponse {
    private String message;

    // Removed duplicate constructor

    public MessageResponse(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}