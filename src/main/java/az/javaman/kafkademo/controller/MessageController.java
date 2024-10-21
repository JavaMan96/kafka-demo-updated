package az.javaman.kafkademo.controller;

import az.javaman.kafkademo.entity.Message;
import az.javaman.kafkademo.repository.MessageRepository;
import az.javaman.kafkademo.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {


    private KafkaProducerService kafkaProducerService;


    private MessageRepository messageRepository;

    @PostMapping("/send")
    public String sendMessageToKafka(@RequestParam("content") String messageContent) {
        kafkaProducerService.sendMessage(messageContent);
        return "Message sent and saved: " + messageContent;
    }

    // Endpoint to get all messages from the database (both produced and consumed)
    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Endpoint to get only produced messages
    @GetMapping("/produced")
    public List<Message> getProducedMessages() {
        return messageRepository.findByDirection("PRODUCED");
    }

    // Endpoint to get only consumed messages
    @GetMapping("/consumed")
    public List<Message> getConsumedMessages() {
        return messageRepository.findByDirection("CONSUMED");
    }
}


