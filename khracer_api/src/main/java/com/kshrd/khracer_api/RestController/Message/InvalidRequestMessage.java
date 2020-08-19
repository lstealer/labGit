package com.kshrd.khracer_api.RestController.Message;

public enum InvalidRequestMessage {
    RANK_SIZE_ERROR("rank must between 1to 5"),
    ACCURACY_SIZE_ERROR("accuracy must smaller than 100 and bigger than 0"),
    WPM_SIZE_ERROR("WPM must smaller than 999 and bigger than 0"),
    USER_ID_UNAVAILABLE("Recheck userId field of your json request"),
    CONTENT_ID_UNAVAILABLE("Recheck contentId field of your json request"),
    REPORT_UNAVAILABLE("The report have not save in database"),
    REPORT_ERROR("Recheck reportText field of your json request"),
    USER_NOT_AVAILABLE("The User is not in registered"),
    PAGE_NOT_AVAILABLE("You page request has no data"),
    DATE_ERROR("Recheck date field of your json request"),
    EMAIL_ERROR("Recheck email field of your json request"),
    NAME_ALREADY_USED("The Name is already used"),
    EMAIL_ALREADY_USED("The Email is already used"),
    NAME_ERROR("Recheck name field of your json request");

    private String message;
    InvalidRequestMessage(String message) {
        this.message = message;
    }

    public String value() {
        return message;
    }
}
