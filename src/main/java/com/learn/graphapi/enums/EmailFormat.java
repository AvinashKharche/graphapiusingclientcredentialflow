package com.learn.graphapi.enums;

public enum EmailFormat {
    HTML("HTML"),
    PLAIN_TEXT("Plain-Text"),
    RICH_TEXT("Rich-Text");

    private String emailFormat;

    EmailFormat(String format) {
        this.emailFormat = format;
    }

    public String getName() {
        return emailFormat;
    }
}
