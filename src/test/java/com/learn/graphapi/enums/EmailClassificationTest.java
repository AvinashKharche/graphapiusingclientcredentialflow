package com.learn.graphapi.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailClassificationTest {

    @Test
    void getName_ConvertEnumIntoStringFormatSuccessfully() {
/*        Email email = new Email();
        email.setClassfication(EmailClassification.RESTRICTED_EXTERNAL);
        email.setContent("Testing Email APIs");
        email.setSubject("Initial Testing");
        List<String> list = Arrays.asList("esha@g.com","eshaaa@gmail.com");
        email.setToRecipients(list);
        email.setFrom("avinash@gmail.com");

        System.out.println(email);*/
        assertEquals("Secret", EmailClassification.SECRET.getName());
    }

}