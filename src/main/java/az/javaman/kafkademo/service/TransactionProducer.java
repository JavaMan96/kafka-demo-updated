package az.javaman.kafkademo.service;

import az.javaman.kafkademo.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionProducer {

    private static final String TOPIC = "transactions";

    private KafkaTemplate<String, Transaction> kafkaTemplate;

    public void sendTransaction(Transaction transaction) {
        kafkaTemplate.send(TOPIC, transaction);
        System.out.println("Produced transaction: " + transaction);
    }
}
