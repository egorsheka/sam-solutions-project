//package by.sam.mvc.service.mail;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//
//@Configuration
//@PropertySource("classpath:gmail.properties")
//public class GmailSenderService{
//
//
//    @Autowired
//    private Environment environment;
//    //TODO is ok?
//    private Properties props;
//
//
//
//
//    public GmailSenderService() {
//
////        props = new Properties();
////        props.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
////        props.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
////        props.put("mail.smtp.host", environment.getProperty("mail.smtp.host"));
////        props.put("mail.smtp.port", environment.getProperty("mail.smtp.port"));
//    }
//
//    public void send(String subject, String text, String toEmail){
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("df", "fgf");
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(env.getProperty("gmail")));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//            message.setSubject(subject);
//            message.setText(text);
//
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

package by.sam.mvc.service.mail;


import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@PropertySource("classpath:gmail.properties")
public class GmailSenderService {
    private Properties props;

    private final Environment env;

    public GmailSenderService(Environment env) {
        this.env = env;
        props = new Properties();
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));



    }

    public void send(String subject, String text, String toEmail){
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("gmail"), env.getProperty("password"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(env.getProperty("gmail")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}