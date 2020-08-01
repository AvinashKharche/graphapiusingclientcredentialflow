package com.learn.graphapi.service;

import com.learn.graphapi.auth.TokenService;
import com.learn.graphapi.dao.GraphAPIDaoImpl;
import com.learn.graphapi.model.Email;
import com.microsoft.graph.models.extensions.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/*
 * This class calls outh services and get the access token
 * and calls dao layer
 * */
@Service
public class GraphAPIDelegateService {

    private static Logger logger = LoggerFactory.getLogger(GraphAPIDelegateService.class);
    private GraphAPIDaoImpl graphAPIDao;
    private TokenService tokenService;

    public GraphAPIDelegateService(GraphAPIDaoImpl graphAPIDao, TokenService tokenService) {
        this.graphAPIDao = graphAPIDao;
        this.tokenService = tokenService;
    }

    public void sendEmail(Email emailObj, String tenantId, String clientId, String secretId) throws Exception {
        String accessToken = null;
        try {
            accessToken = tokenService.getAccessTokenByClientCredentialGrant(tenantId, clientId, secretId);
        } catch (Exception e) {
            logger.error("Error when fetching access_token", e);
            return;
        }
        graphAPIDao.sendEmail(emailObj, accessToken);
    }

    public List<Message> readEmail(String email, String tenantId, String clientId, String secretId) {
        String accessToken = null;
        try {
            accessToken = tokenService.getAccessTokenByClientCredentialGrant(tenantId, clientId, secretId);
        } catch (Exception e) {
            logger.error("Error when fetching access_token", e);
            return Collections.emptyList();
        }
        return graphAPIDao.readEmail(email, accessToken);
    }
}
