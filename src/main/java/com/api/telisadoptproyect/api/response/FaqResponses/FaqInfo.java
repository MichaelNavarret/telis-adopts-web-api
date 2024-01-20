package com.api.telisadoptproyect.api.response.FaqResponses;

import com.api.telisadoptproyect.library.entity.Faq;

public class FaqInfo {
    private String id;
    private String question;
    private String answer;
    private String warning;

    public FaqInfo(Faq faq){
        this.id = faq.getId();
        this.question = faq.getQuestion();
        this.answer = faq.getAnswer();
        this.warning = faq.getWarning() != null? faq.getWarning() : null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
