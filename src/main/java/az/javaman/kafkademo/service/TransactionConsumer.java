package az.javaman.kafkademo.service;

import az.javaman.kafkademo.entity.Transaction;
import az.javaman.kafkademo.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionConsumer {

    private TransactionRepository transactionRepository;

    @KafkaListener(topics = "transactions", groupId = "bank-consumer-group")
    @Transactional
    public void consume(Transaction transaction) {
        try {
            System.out.println("Consumed transaction: " + transaction);

            System.out.println("Saving transaction to DB: " + transaction);

            transactionRepository.save(transaction);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }}
