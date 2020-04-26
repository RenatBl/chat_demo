package ru.itis.chat_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.chat_demo.models.Message;

import java.util.List;

@Repository
public interface MessagesRepo extends JpaRepository<Message, Long> {
    List<Message> findAllByOwner(String username);
}
