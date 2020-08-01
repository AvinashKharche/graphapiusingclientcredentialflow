package com.learn.graphapi.dao;

import com.learn.graphapi.auth.SimpleAuthProvider;
import com.learn.graphapi.model.Email;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.http.GraphServiceException;
import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.extensions.*;
import com.microsoft.graph.models.generated.BodyType;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.IMessageCollectionPage;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class GraphAPIDaoImpl implements GraphAPIDao {

    private static IGraphServiceClient graphClient = null;
    private static SimpleAuthProvider authProvider = null;

    private static void ensureGraphClient(String accessToken) {
        if (graphClient == null) {
            // Create the auth provider
            authProvider = new SimpleAuthProvider(accessToken);

            // Create default logger to only log errors
            DefaultLogger logger = new DefaultLogger();
            logger.setLoggingLevel(LoggerLevel.DEBUG);

            // Build a Graph client
            graphClient = GraphServiceClient.builder()
                    .authenticationProvider(authProvider)
                    .logger(logger)
                    .buildClient();
        }
    }

    public void sendEmail(Email mail, String accessToken) throws Exception {
        ensureGraphClient(accessToken);
        Message message = new Message();
        message.subject = mail.getSubject();
        ItemBody body = new ItemBody();
        if (mail.getFormat().getName() == "PLAIN_TEXT" || mail.getFormat().getName() == "RICH_TEXT") {
            body.contentType = BodyType.TEXT;
        } else if (mail.getFormat().getName() == "HTML") {
            body.contentType = BodyType.HTML;
        }

        body.content = mail.getContent();
        message.body = body;
        LinkedList<Recipient> toRecipientsList = new LinkedList<Recipient>();
        for (String email : mail.getToRecipients()) {
            Recipient toRecipients = new Recipient();
            EmailAddress emailAddress = new EmailAddress();
            emailAddress.address = email;
            toRecipients.emailAddress = emailAddress;
            toRecipientsList.add(toRecipients);
        }
        message.toRecipients = toRecipientsList;
        LinkedList<Recipient> ccRecipientsList = new LinkedList<Recipient>();
        for (String email : mail.getCcRecipients()) {
            Recipient ccRecipients = new Recipient();
            EmailAddress emailAddress1 = new EmailAddress();
            emailAddress1.address = email;
            ccRecipients.emailAddress = emailAddress1;
            ccRecipientsList.add(ccRecipients);
        }
        message.ccRecipients = ccRecipientsList;
        boolean saveToSentItems = true;
        try {
            graphClient.users(mail.getFrom()).sendMail(message, saveToSentItems).buildRequest().post();
        } catch (ClientException e) {
            String errMessage = null;
            if((((GraphServiceException) e).getServiceError().code).equals("ErrorInvalidRecipients")) {
                errMessage = "Some of your recipients are invalid";
            }
            throw new Exception(errMessage);
        }
    }

    public List<Message> readEmail(String principalEmail, String accessToken) {
        ensureGraphClient(accessToken);
        IMessageCollectionPage as = graphClient.users(principalEmail).mailFolders("sentitems").messages().buildRequest().get();
        return as.getCurrentPage();
    }

}
