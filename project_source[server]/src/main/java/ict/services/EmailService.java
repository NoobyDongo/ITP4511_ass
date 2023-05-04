/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

/**
 *
 * @author Ison Ho
 */
// File Name SendEmail.java
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class EmailBody{
    public String to, subject, head, body, date, timeLoc;
}

@RestController
@CrossOrigin
public class EmailService {

    @PostMapping("/email")
    public String email(@RequestBody EmailBody e) throws jakarta.mail.MessagingException, IOException {
        System.out.println("email");
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        Email.template = new Scanner(new URL("http://localhost/Project/ITP4511/email/1.html").openStream(), "UTF-8").useDelimiter("\\A").next();
        //Files.readString(Path.of("G:/Program/Xampp/htdocs/Project/ITP4511/email/New email template 2023-05-01.html"));
        String host = "smtp.gmail.com";
        int port = 587;
        String userName = "eplnoreply12@gmail.com";
        String password = "rianmbyvduukiyuq";

        String mailTo = e.to;
        String subject = e.subject;
        String content = Email.build(e.head, e.body, e.date, e.timeLoc);

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setJavaMailProperties(props);
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(userName);
        sender.setPassword(password);

        jakarta.mail.internet.MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setTo(mailTo);
        helper.setSubject(subject);
        helper.setText(content, true);

        sender.send(message);
        
        return "";
    }

    class Email {

        public static String template = "";
        private static final String key = "gD46k,_Yqfcpz",
                nameK = key + "name",
                msgK = key + "msg",
                dateK = key + "date",
                locK = key + "loc";
        

        public Email() {
        }

        public static String build(String name, String message, String date, String timeLoc) {

            String res = template;
            
            return res.replaceAll(nameK, name).replaceAll(msgK, message).replaceAll(dateK, date).replaceAll(locK, timeLoc);
        }
    }
}
