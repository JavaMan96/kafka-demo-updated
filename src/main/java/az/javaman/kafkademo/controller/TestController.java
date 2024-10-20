package az.javaman.kafkademo.controller;

import az.javaman.kafkademo.entity.Transaction;
import az.javaman.kafkademo.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@AllArgsConstructor
public class TestController {

    private TransactionRepository transactionRepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveTransaction(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Transaction saved to DB");
    }
}
