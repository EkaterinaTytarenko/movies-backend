package com.practice.doesthecatdie.servises;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailSender {

    private final String senderEmail = "doesthecatdieapp@gmail.com";
    private final String senderPassword = "doesthecatdieappdoesthecatdieapp";
    private final String activationLink="http://localhost:8080/activateAccount?userId=";

    private void sendAsHtml(String to, String title, String html) throws MessagingException {

        Session session = createSession();

        //create message using session
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, to, title, html);

        //sending message
        Transport.send(message);
    }

    private void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    private  Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");//Outgoing server requires authentication
        props.put("mail.smtp.starttls.enable", "true");//TLS must be activated
        props.put("mail.smtp.host", "smtp.gmail.com"); //Outgoing server (SMTP) - change it to your SMTP server
        props.put("mail.smtp.port", "587");//Outgoing port

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        return session;
    }

    public void sendActivationLink(String email,String id) throws MessagingException {
        sendAsHtml(email,
                "Activate your account via link",
                "<p>"+activationLink+id+"</p>");
    }

    public void sendUserRequest(String admin_email, String request_text) throws MessagingException{
        sendAsHtml(admin_email,
                "A new request was submitted",
                request_text);
    }
}
