package ru.itis.chat_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.chat_demo.models.User;
import ru.itis.chat_demo.services.MessageResolver;
import ru.itis.chat_demo.services.MessageService;
import ru.itis.chat_demo.services.UsersService;

@Controller
public class ChatController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageResolver resolver;

    @GetMapping("/chat")
    public String getChatPage(Model model,
                              @RequestParam("username") String username) {
        model.addAttribute("username", username);
        model.addAttribute("DBmessages", resolver.resolveMessages(messageService.getFromDB()));
        return "chat";
    }

    @GetMapping("/login")
    public String getPage() {
        return "login";
    }

    @PostMapping("/joinChat")
    public String joinChat(@ModelAttribute("form") User user) {
        usersService.addNewUser(user);
        return "redirect:/chat?username=" + user.getName();
    }
}
