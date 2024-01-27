package com.api.telisadoptproyect.api.request.FaqRequests;

public class FaqCreateRequest {
    private String question;
    private String answer;
    private String warning;

    public FaqCreateRequest() {
    }
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getWarning() {
        return warning;
    }
}
