package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepoImpl;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import com.mnm.JuniorKnowledgeBaseTool.services.AdminPanelService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Service;


@Route("admin")
public class AdminPanel extends VerticalLayout {
    private UserRepository userRepository;
    private AdminPanelService gridService;
    //private UserRepoImpl userRepoImpl;

    public AdminPanel(UserRepository userRepository, AdminPanelService gridService) {
        this.userRepository = userRepository;
        this.gridService = gridService;

        Grid<User> userGrid = new Grid<>(User.class);
        userGrid.setItems(gridService.userInit());

        add(userGrid);
    }

}
