package az.javaman.kafkademo.service;

import az.javaman.kafkademo.entity.Message;
import az.javaman.kafkademo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private MessageRepository messageRepository;

    public KafkaConsumerService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @KafkaListener(topics = "topic1",groupId = "group1")
    public void consume(@Payload String messageContent,
                               @Header(KafkaHeaders.RECEIVED_PARTITION)int partition,
                               @Header(KafkaHeaders.OFFSET)int offsets) throws InterruptedException {
        log.info("Received message in group1 {}", messageContent);
        log.info("Partition {}", partition);
        log.info("Offsets {}", offsets);

        Message message = new Message();
        message.setContent(messageContent);
        message.setTimestamp(LocalDateTime.now());
        message.setDirection("CONSUMED");
        messageRepository.save(message);

        System.out.println("Message consumed and saved to database.");

    }
}
