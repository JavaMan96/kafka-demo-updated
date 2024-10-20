package az.javaman.kafkademo.controller;

import az.javaman.kafkademo.entity.Transaction;
import az.javaman.kafkademo.service.TransactionProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/bank")
@AllArgsConstructor
public class TransactionController {

    private TransactionProducer transactionProducer;

    @PostMapping("/transaction")
    public ResponseEntity<String> sendTransaction(@RequestBody Transaction transaction) {

        transactionProducer.sendTransaction(transaction);
        return ResponseEntity.ok("Transaction sent to Kafka");
    }
}
