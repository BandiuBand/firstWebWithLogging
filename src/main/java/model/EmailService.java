package model;

import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import org.glassfish.jersey.client.ClientResponse;

import javax.ws.rs.client.Client;

public class EmailService {
    private static final String API_KEY = "6eb7dda9a17e7f19a823810e6c89b754-5e3f36f5-95808194";
    private static final String DOMEN = "sandbox9b452c78cbe7446b9ad0827f98a3cbf8.mailgun.org";
    private static final String fromEmail = "Bandiubandi@gmail.com";

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
