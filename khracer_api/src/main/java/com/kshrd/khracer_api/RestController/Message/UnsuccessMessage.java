package com.kshrd.khracer_api.RestController.Message;

public enum UnsuccessMessage {
    REGISTER_UNSUCCESS("You have register unsuccessfully");
    private String message;
    UnsuccessMessage(String message){this.message=message;}
    public String value(){
        return this.message;
    }
}
