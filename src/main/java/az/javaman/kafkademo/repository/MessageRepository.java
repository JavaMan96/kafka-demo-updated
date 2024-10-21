package az.javaman.kafkademo.repository;

import az.javaman.kafkademo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByDirection(String direction);
}
