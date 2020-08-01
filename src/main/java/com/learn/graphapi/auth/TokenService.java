package com.learn.graphapi.auth;

public interface TokenService {
    String getAccessTokenByClientCredentialGrant(String tenantId, String clientId, String secretId) throws Exception;
}
