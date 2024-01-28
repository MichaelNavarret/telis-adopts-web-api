package com.api.telisadoptproyect.api.request.FaqRequests;

public class FaqUpdateRequest {
    private String question;
    private String answer;
    private String warning;

    public FaqUpdateRequest() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
