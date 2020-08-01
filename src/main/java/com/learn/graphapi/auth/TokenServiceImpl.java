package com.learn.graphapi.auth;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@Service
public class TokenServiceImpl implements TokenService {
    private final static String BASE_URL = "https://login.microsoftonline.com/";
    private final static String GRAPH_DEFAULT_SCOPE = "https://graph.microsoft.com/.default";

    /**
     * This method gets the access_token from Microsoft Outh Toen end Point
     *
     * @param tenantId
     * @param clientId
     * @param secretId
     * @return
     * @throws Exception
     */
    public String getAccessTokenByClientCredentialGrant(String tenantId, String clientId, String secretId) throws Exception {
        ConfidentialClientApplication app = ConfidentialClientApplication.builder(clientId,
                ClientCredentialFactory.createFromSecret(secretId))
                .authority(BASE_URL+tenantId)
                .build();

        // With client credentials flows the scope is ALWAYS of the shape "resource/.default", as the
        // application permissions need to be set statically (in the portal), and then granted by a tenant administrator
        ClientCredentialParameters clientCredentialParam = ClientCredentialParameters.builder(
                Collections.singleton(GRAPH_DEFAULT_SCOPE))
                .build();

        CompletableFuture<IAuthenticationResult> future = app.acquireToken(clientCredentialParam);
        IAuthenticationResult iAuthenticationResult = future.get();
        return iAuthenticationResult.accessToken();
    }
}
