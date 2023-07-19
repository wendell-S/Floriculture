package com.floriculture.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@RestController

public class MessageController {

    private List<com.floriculture.Message> messages = new ArrayList<>();

    @PostMapping("/api/messages")
    public void saveMessage(@RequestBody com.floriculture.Message message){
        messages.add(message);
     // sendEmail(message);
        System.out.println("enviado!");
    }
    @GetMapping("/api/messages")
    public List<com.floriculture.Message> getMessages() {
        return messages;
    }

    private void sendEmail(com.floriculture.Message message) {
        String to = message.getEmail();
        String subject = message.getAssunto();
        String text = message.getMensagem();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "*");

        String username = "botinterprise@gmail.com";
        String password = "ucjrcovndnofalxa";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            javax.mail.Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
            emailMessage.setSubject(subject);
            emailMessage.setText(text);

            Transport.send(emailMessage);
            System.out.println("Email enviado para: " + to);
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar o email: " + e.getMessage());
        }

    }
}
