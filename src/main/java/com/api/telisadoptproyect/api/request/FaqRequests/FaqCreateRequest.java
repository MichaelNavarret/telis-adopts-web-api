package com.api.telisadoptproyect.api.request.FaqRequests;

public class FaqCreateRequest {
    private String question;
    private String answer;
    private String warning;
    private String specieId;

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

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getSpecieId() {
        return specieId;
    }

    public void setSpecieId(String specieId) {
        this.specieId = specieId;
    }
}
