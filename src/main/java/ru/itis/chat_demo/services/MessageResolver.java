package ru.itis.chat_demo.services;

import ru.itis.chat_demo.models.Message;
import ru.itis.chat_demo.models.MessageDto;

import java.util.List;

public interface MessageResolver {
    List<MessageDto> resolveMessages(List<Message> messages);
}
