package az.javaman.kafkademo.service;

import az.javaman.kafkademo.entity.Message;
import az.javaman.kafkademo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC = "topic1";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private MessageRepository messageRepository;

    //@Scheduled(fixedRate = 1000)
    public void sendMessage(String messageContent) {
        log.info("Sending new message to Kafka");
         kafkaTemplate.send("topic1", "Hello Kafka" + LocalDateTime.now());
        //kafkaTemplate.send(TOPIC,"1",new MailMessageDto("test@kafka.com","test kafka","test") + LocalDateTime.now().toString());
        log.info("Message sent successfully");


        Message message = new Message();
        message.setContent(messageContent);
        message.setTimestamp(LocalDateTime.now());
        message.setDirection("PRODUCED");
        messageRepository.save(message);

        System.out.println("Message sent and saved to database.");
    }
}
