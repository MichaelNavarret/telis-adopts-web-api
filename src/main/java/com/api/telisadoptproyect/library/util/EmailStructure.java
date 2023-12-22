package com.api.telisadoptproyect.library.util;

public class EmailStructure {
    private String emailSubjectTitle;
    private String logoUrl;
    private String tittleMessage;
    private String sender;
    private String addressee;
    private String ownerName;
    private String messageType;
    private String bodyParam;

    public EmailStructure(){}

    public String getEmailSubjectTitle() {
        return emailSubjectTitle;
    }

    public void setEmailSubjectTitle(String emailSubjectTitle) {
        this.emailSubjectTitle = emailSubjectTitle;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTittleMessage() {
        return tittleMessage;
    }

    public void setTittleMessage(String tittleMessage) {
        this.tittleMessage = tittleMessage;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getBodyParam() {
        return bodyParam;
    }

    public void setBodyParam(String bodyParam) {
        this.bodyParam = bodyParam;
    }
}
