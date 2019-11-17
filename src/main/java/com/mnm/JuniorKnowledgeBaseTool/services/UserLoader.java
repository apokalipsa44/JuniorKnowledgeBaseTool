package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.model.UserRole;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.support.incrementer.AbstractDataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;

@Service
public class UserLoader {
    UserRepository userRepository;

    @Autowired
    public UserLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createUser() {
        User user = new User("janusz", "janusz123", "janusz123@junior.pl", "Admin");
        userRepository.save(user);

        User user1 = new User("michau", "michaumistrz", "michau123@junior.pl", "Admin");
        userRepository.save(user1);

        User user2 = new User("random", "random", "random@random.pl", "User");
        userRepository.save(user2);
    }
}
