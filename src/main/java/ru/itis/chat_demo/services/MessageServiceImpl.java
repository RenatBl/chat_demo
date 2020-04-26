package ru.itis.chat_demo.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat_demo.models.Message;
import ru.itis.chat_demo.repo.MessagesRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final List<Message> messages = new ArrayList<>();

    @Autowired
    private MessagesRepo messagesRepo;

    @Override
    public void addMessage(Message message, String username) {
        synchronized (messages) {
            message.setOwner(username);
            message.setDateTime(LocalDateTime.now());
            messages.add(message);
            messagesRepo.save(message);
            messages.notifyAll();
        }
    }

    @SneakyThrows
    @Override
    public List<Message> getMessages() {
        synchronized (messages) {
            if (messages.isEmpty()) {
                messages.wait();
            }
        }
        List<Message> list = new ArrayList<>(messages);
        messages.clear();
        return list;
    }

    @Override
    public List<Message> getFromDB() {
        return messagesRepo.findAll();
    }
}
