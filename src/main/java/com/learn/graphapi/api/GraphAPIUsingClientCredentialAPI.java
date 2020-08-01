package com.learn.graphapi.api;

import com.learn.graphapi.model.Email;
import com.learn.graphapi.model.GraphAPIRequest;
import com.learn.graphapi.model.GraphAPIResponse;
import com.learn.graphapi.service.GraphAPIService;
import com.microsoft.graph.models.extensions.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("/")
public class GraphAPIUsingClientCredentialAPI {

    private GraphAPIService graphAPIService;

    @Autowired
    public GraphAPIUsingClientCredentialAPI(GraphAPIService graphAPIService) {
        this.graphAPIService = graphAPIService;
    }

    @PostMapping("sendemail")
    public GraphAPIResponse sendEmail(@RequestBody GraphAPIRequest apiRequest) {
        apiRequest.getEmail().setFrom(apiRequest.getFrom());
        GraphAPIResponse graphAPIResponse = new GraphAPIResponse();
        try {
            graphAPIService.sendEmail(apiRequest.getEmail(), apiRequest.getTenantId(), apiRequest.getClientId(), apiRequest.getSecretId());
            graphAPIResponse.setMessage("Your mail has been sent successfully");
            graphAPIResponse.setSuccess(true);
        } catch (Exception e) {
            graphAPIResponse.setMessage(e.getMessage());
            graphAPIResponse.setSuccess(false);
        }
        return graphAPIResponse;
    }

    @GetMapping("/reademail")
    public List<String> getEmail(@RequestParam String email, @RequestParam String tenantId, @RequestParam String clientId, @RequestParam String secretId) {
        List<Message>  messages = graphAPIService.readEmail(email, tenantId, clientId, secretId);
        return messages.stream().map(message -> message.body.content).collect(Collectors.toList());
    }
}
