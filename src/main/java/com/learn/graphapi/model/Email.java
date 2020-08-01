package com.learn.graphapi.model;

import com.learn.graphapi.enums.EmailClassification;
import com.learn.graphapi.enums.EmailFormat;

import java.util.List;

public class Email {
    private String subject;
    private String content;
    private List<String> toRecipients;
    private List<String> ccRecipients;
    private List<String> bccRecipients;
    private String from;
    private EmailFormat format;
    private EmailClassification classfication;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getToRecipients() {
        return toRecipients;
    }

    public void setToRecipients(List<String> toRecipients) {
        this.toRecipients = toRecipients;
    }

    public List<String> getCcRecipients() {
        return ccRecipients;
    }

    public void setCcRecipients(List<String> ccRecipients) {
        this.ccRecipients = ccRecipients;
    }

    public List<String> getBccRecipients() {
        return bccRecipients;
    }

    public void setBccRecipients(List<String> bccRecipients) {
        this.bccRecipients = bccRecipients;
    }

    public EmailFormat getFormat() {
        return format;
    }

    public void setFormat(EmailFormat format) {
        this.format = format;
    }

    public EmailClassification getClassfication() {
        return classfication;
    }

    public void setClassfication(EmailClassification classfication) {
        this.classfication = classfication;
    }

    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", toRecipients=" + toRecipients +
                ", ccRecipients=" + ccRecipients +
                ", bccRecipients=" + bccRecipients +
                ", from='" + from + '\'' +
                ", format=" + format +
                ", classfication=" + classfication +
                '}';
    }
}
