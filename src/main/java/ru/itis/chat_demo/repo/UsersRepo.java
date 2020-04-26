package ru.itis.chat_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.chat_demo.models.User;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {
    User findByName(String name);
}
