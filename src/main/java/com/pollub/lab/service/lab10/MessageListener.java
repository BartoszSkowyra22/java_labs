package com.pollub.lab.service.lab10;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "namesQueue")
    public void receiveMessage(String encryptedMessage) {
            System.out.println("Otrzymano zaszyfrowaną wiadomość: " + encryptedMessage);
            String decryptedMessage = decryptMessage(encryptedMessage);
            System.out.println("Odszyfrowana wiadomość: " + decryptedMessage + "\n");
    }

    private String decryptMessage(String encryptedMessage) {
        String cipherKey = "examplekey";
        return VigenereCipher.decrypt(encryptedMessage, cipherKey);
    }
}

