package ru.itis.chat_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.chat_demo.models.Message;
import ru.itis.chat_demo.models.MessageDto;
import ru.itis.chat_demo.services.MessageResolver;
import ru.itis.chat_demo.services.MessageService;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService service;

    @Autowired
    private MessageResolver resolver;

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessages(Model model,
                                                     @RequestParam("owner") String owner) {
        model.addAttribute("username", owner);
        List<MessageDto> response = resolver.resolveMessages(service.getMessages());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/messages")
    public ResponseEntity sendMessage(@RequestBody Message message) {
        service.addMessage(message, message.getOwner());
        return ResponseEntity.ok().build();
    }
}
