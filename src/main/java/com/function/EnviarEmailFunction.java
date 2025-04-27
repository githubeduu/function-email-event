package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.function.service.EmailService;

public class EnviarEmailFunction {
    
    @FunctionName("EnviarEmailFunction")
    public void run(
        @EventGridTrigger(name = "event") String eventJson,
        final ExecutionContext context
    ) {
        context.getLogger().info("Evento recibido: " + eventJson);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode eventNode = mapper.readTree(eventJson);
            JsonNode dataNode = eventNode.get("data");

            String to = dataNode.get("to").asText();
            String subject = dataNode.get("subject").asText();
            String body = dataNode.get("body").asText();

            context.getLogger().info("Preparando correo a: " + to);

            // Envía el correo
            EmailService.sendEmail(to, subject, body);

            context.getLogger().info("Correo enviado exitosamente.");

        } catch (Exception ex) {
            context.getLogger().severe("Error enviando correo: " + ex.getMessage());
        }
    }
}