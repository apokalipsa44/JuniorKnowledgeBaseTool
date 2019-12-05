package com.mnm.JuniorKnowledgeBaseTool.repositories;


import com.mnm.JuniorKnowledgeBaseTool.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UserRepoImpl {
    private UserRepository userRepository;

    public UserRepoImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    public List<User> findAll() {return userRepository.findAll();}

    public User save(User user) {
        return userRepository.save(user);
    }



}
