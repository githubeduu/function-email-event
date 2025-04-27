package com.function.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService {

    public static void sendEmail(String to, String subject, String body) throws Exception {

        final String username = "kotesepulveda28@gmail.com";
        final String password = "ieou biui ilct wvqg"; 

        // Configuración de propiedades para SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS
        props.put("mail.smtp.host", "smtp.gmail.com"); // para Gmail
        props.put("mail.smtp.port", "587"); // para Gmail
        props.put("mail.smtp.starttls.enable", "true");
        
        // Si usas Outlook sería:
        // props.put("mail.smtp.host", "smtp.office365.com");
        // props.put("mail.smtp.port", "587");

        // Crear sesión SMTP autenticada
        Session session = Session.getInstance(props,
        new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Crear el mensaje
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username)); // desde tu email
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        message.setSubject(subject);
        message.setText(body);

        // Enviar el mensaje
        Transport.send(message);

        System.out.println("Correo enviado exitosamente a: " + to);
    }
}
