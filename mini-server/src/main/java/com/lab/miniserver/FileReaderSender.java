package com.lab.miniserver;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class FileReaderSender {

    private final RabbitTemplate rabbitTemplate;
    private final VigenereCipher cipher;
    private final List<String> names;

    @Value("${app.rabbitmq.key}")
    private String cipherKey;

    public FileReaderSender(RabbitTemplate rabbitTemplate, VigenereCipher cipher) throws IOException {
        this.rabbitTemplate = rabbitTemplate;
        this.cipher = cipher;
        ClassPathResource resource = new ClassPathResource("names.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            this.names = reader.lines().collect(Collectors.toList());
        }
    }

    @Scheduled(fixedRate = 10000)
    public void sendName() {
        String name = names.get(new Random().nextInt(names.size()));
        String message = String.format("%s, o godzinie: %s", name, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println(message);
        String encryptedMessage = cipher.encrypt(message, cipherKey);

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, encryptedMessage);
        System.out.println("Wysłano zaszyfrowaną wiadomość: " + encryptedMessage);
    }
}

