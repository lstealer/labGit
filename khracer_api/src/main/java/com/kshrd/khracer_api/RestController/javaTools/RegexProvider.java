package com.kshrd.khracer_api.RestController.javaTools;

public enum RegexProvider {
    EMAIL_VERIFY("^(.+)@(.+)$"),
    NO_SPACE_VERIFY("[A-Za-z0-9]+");
    private String message;
    RegexProvider(String message){ this.message=message;}
    public String value(){return message;}
}
