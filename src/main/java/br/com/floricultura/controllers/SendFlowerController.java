package br.com.floricultura.controllers;

import br.com.floricultura.model.Flower;
import br.com.floricultura.model.Message;
import br.com.floricultura.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/floricultura")
public class SendFlowerController {

    @Autowired
    FlowerRepository flowerRepository;


    public Flower findFlowerByName(String flowerName) {
        Flower flower = flowerRepository.findByName(flowerName);
        if (flower != null) {
            String name = flower.getName();
            String link = flower.getLink();
            return new Flower(name, link);
        } else {
            return null;
        }
    }
    private List<Message> messages = new ArrayList<>();


    @GetMapping("/mandar-flor")
    public ModelAndView showRegistration() {
        return new ModelAndView("messageScreen");
    }

    @PostMapping("/mandar-flor")
    public RedirectView saveMessage(@Valid @ModelAttribute Message message, RedirectAttributes redirectAttributes) throws IOException {
        Flower flower = findFlowerByName(message.getFlower());
        if (flower == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Flor não encontrada. Verifique o nome da flor.");
        } else {
            boolean isEmailSent = sendEmail(message);
            if (isEmailSent) {
                redirectAttributes.addFlashAttribute("successMessage", "E-mail enviado com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Falha ao enviar o e-mail. Verifique as credenciais.");
            }
        }
        return new RedirectView("/floricultura/mandar-flor");
    }


    private boolean sendEmail(Message message) throws IOException {
        Flower flower = findFlowerByName(message.getFlower());
        if (flower == null) {
      throw new IOException();
        }
        String to = message.getEmail();
        String subject = message.getAssunto();
        String link = flower.getLink();
        String text = "Olá! " + to + ", um usuário dos nossos serviços mandou uma flor para você \uD83D\uDC90, ela se chama: " +
                    message.getFlower() + ", uma linda flor. Você pode acessar a foto direta desta flor neste link: " + link +
                    "\n\ne também deixou uma mensagem:\n\n" + message.getMensagem();


            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.trust", "*");

            String username = ""; //coloque aqui seu email
            String password = ""; //coloque aqui a sua senha conforme o video fornecido no README.md

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

                return true;
            } catch (MessagingException e) {
                System.out.println("Erro ao enviar o email: " + e.getMessage());
                return false;
            }
        }
    }