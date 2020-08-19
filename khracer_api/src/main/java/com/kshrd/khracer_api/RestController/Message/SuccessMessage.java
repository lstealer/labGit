package com.kshrd.khracer_api.RestController.Message;

public enum SuccessMessage {
    GET_HISTORY_SUCCESS("You have get history successfully"),
    UPDATE_REPORT_SUCCESS("You have update record status successfully"),
    UPDATE_REPORT_UNSUCESS("You report seen like can not update plz try again later"),
    INSERT_HISTORY_UNSUCCESS("Your History seen like not save in database plz try again"),
    INSERT_HISTORY_SUCCESS("You have insert history successfully"),
    INSERT_REPORT_UNSUCESS("You report seen like can not reach to admin plz try again later"),
    INSERT_REPORT_SUCCESS("You have reported the problem"),
    HAS_NO_RECORD("You Database Have No Enough Data To Response"),
    REGISTER_SUCCESS("You have register successfully"),
    GET_USERS_SUCCESS("You have get Users Successfully"),
    GET_REPORTS_SUCCESS("You have get Reports Successfully"),
    GET_CONTENT_SUCCESS("You have get Content successfully");

    private String message;
    SuccessMessage(String message) {
        this.message = message;
    }

    public String value() {
        return message;
    }
}
