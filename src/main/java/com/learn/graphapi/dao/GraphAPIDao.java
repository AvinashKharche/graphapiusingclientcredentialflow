package com.learn.graphapi.dao;

import com.learn.graphapi.model.Email;
import com.microsoft.graph.models.extensions.Message;

import java.util.List;

public interface GraphAPIDao {
    void sendEmail(Email mail, String accessToken) throws Exception;

    List<Message> readEmail(String principalEmail, String accessToken);
}
