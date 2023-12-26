package model;

import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import org.glassfish.jersey.client.ClientResponse;

import javax.ws.rs.client.Client;

public class EmailService {
    private static final String API_KEY  = Config.getApiKey();
    private static final String DOMEN = Config.getDomen();
    private static final String fromEmail = Config.getFromEmail();

    public static void sendEmail(String toEmail, String subject, String body) {
        Configuration configuration = new Configuration()
                .domain(DOMEN)
                .apiKey(API_KEY)
                .from("Test", fromEmail);

        Mail.using(configuration)
                .to(toEmail)
                .subject(subject)
                .text(body)
                .build()
                .send();
    }
    private EmailService(){}
}
