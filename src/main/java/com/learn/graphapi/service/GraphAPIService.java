package com.learn.graphapi.service;

import com.learn.graphapi.model.Email;
import com.microsoft.graph.models.extensions.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * This class will mainly be responsible for a
 * adding any business logic to the response
 * from Microsoft Graph APIs
 *
 * */
@Service
public class GraphAPIService {

    private GraphAPIDelegateService graphAPIDelegateService;

    @Autowired
    public GraphAPIService(GraphAPIDelegateService graphAPIDelegateService) {
        this.graphAPIDelegateService = graphAPIDelegateService;
    }

    public void sendEmail(Email mail, String tenantId, String clientId, String secretId) throws Exception {
        graphAPIDelegateService.sendEmail(mail, tenantId, clientId, secretId);
        //Any business logic can be added here
    }

    public List<Message> readEmail(String email, String tenantId, String clientId, String secretId) {
        return graphAPIDelegateService.readEmail(email, tenantId, clientId, secretId);
        //Any business logic can be added here
    }
}
