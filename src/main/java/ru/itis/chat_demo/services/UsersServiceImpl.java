package ru.itis.chat_demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat_demo.models.User;
import ru.itis.chat_demo.repo.UsersRepo;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public void addNewUser(User user) {
        if (usersRepo.findByName(user.getName()) == null) {
            usersRepo.save(user);
        }
    }
}
