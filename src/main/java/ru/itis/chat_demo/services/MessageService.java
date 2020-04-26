package ru.itis.chat_demo.services;

import ru.itis.chat_demo.models.Message;

import java.util.List;

public interface MessageService {
    void addMessage(Message message, String username);
    List<Message> getMessages();
    List<Message> getFromDB();
}
