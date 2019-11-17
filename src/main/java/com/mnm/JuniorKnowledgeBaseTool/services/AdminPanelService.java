package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepoImpl;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminPanelService  {
    private Grid<User> userGrid = new Grid<>();
    private List<User> userList = new ArrayList<>();
    private UserRepoImpl userRepoImpl;

    public AdminPanelService(UserRepoImpl userRepoImpl) {this.userRepoImpl = userRepoImpl;}

    public Grid<User> setUserGrid() {
        userGrid.setHeightByRows(true);
        userGrid.setColumnReorderingAllowed(true);
        return userGrid;
    }

    public List<User> userInit() {
        //createUser();
        this.userList= userRepoImpl.findAll();
        return this.userList;
    }

    /*public void createUser() {
        User user = new User("janusz", "janusz123", "janusz123@junior.pl", "Admin");
        userRepoImpl.save(user);

        User user1 = new User("michau", "michaumistrz", "michau123@junior.pl", "Admin");
        userRepoImpl.save(user1);

        User user2 = new User("random", "random", "random@random.pl", "User");
        userRepoImpl.save(user2);
    }*/


}
