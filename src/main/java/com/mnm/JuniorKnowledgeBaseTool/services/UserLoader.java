package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.model.UserRole;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.support.incrementer.AbstractDataFieldMaxValueIncrementer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class UserLoader {
    UserRepository userRepository;

    @Autowired
    public UserLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createUser() {
        User user = new User("janusz", passwordEncoder().encode("janusz123"), "janusz123@junior.pl", UserRole.ADMIN);
        userRepository.save(user);

        User user1 = new User("michau", passwordEncoder().encode("michaumistrz"), "michau123@junior.pl", UserRole.ADMIN);
        userRepository.save(user1);

        User user2 = new User("random", passwordEncoder().encode("random"), "random@random.pl", UserRole.USER);
        userRepository.save(user2);

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
