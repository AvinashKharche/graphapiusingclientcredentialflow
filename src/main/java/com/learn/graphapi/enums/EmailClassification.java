package com.learn.graphapi.enums;

public enum EmailClassification {
    UNRESTRICTED("Unrestricted"),
    RESTRICTED_EXTERNAL("Restricted-External"),
    RESTRICTED_INTERNAL("Restricted-Internal"),
    SECRET("Secret");

    private String emailClassification;

    EmailClassification(String emailClassification) {
        this.emailClassification = emailClassification;
    }

    public String getName() {
        return emailClassification;
    }
}
