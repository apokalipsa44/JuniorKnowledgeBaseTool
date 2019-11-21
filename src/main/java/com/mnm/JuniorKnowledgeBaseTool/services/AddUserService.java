package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.model.UserDTO;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepoImpl;
import org.springframework.stereotype.Service;

@Service
public class AddUserService {
    private final UserRepoImpl userRepoImpl;

    public AddUserService(UserRepoImpl userRepoImpl) {
        this.userRepoImpl = userRepoImpl;
    }

    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        userRepoImpl.save(user);
    }


}
