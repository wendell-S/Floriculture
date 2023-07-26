package br.com.floricultura.controllers;

import br.com.floricultura.model.Message;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/floricultura")
public class MandarFlorController {

    private List<Message> messages = new ArrayList<>();


    @GetMapping("/mandar-flor")
    public ModelAndView showRegistration() {
        return new ModelAndView("messageScreen");
    }

    @PostMapping("/mandar-flor")
    public RedirectView saveMessage(@Valid @ModelAttribute Message message, RedirectAttributes redirectAttributes) {
        messages.add(message);
        boolean isEmailSent = sendEmail(message);

        if (isEmailSent) {
          redirectAttributes.addFlashAttribute("successMessage", "email enviado com sucesso!");

             new RedirectView("/floricultura/mandar-flor");

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "email não pode ser enviado,verifique as credenciais!");
        }
        return  new RedirectView("/floricultura/mandar-flor");


    }

    private boolean sendEmail(Message message) {
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
        String password = "mbvfuzwpnutdkbxe";

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

            return true; // Retorna true indicando que o e-mail foi enviado com sucesso
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar o email: " + e.getMessage());
            return false; // Retorna false indicando que ocorreu um erro ao enviar o e-mail
        }
    }
}