package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class UserRoleService {
    private UserGridService userGridService;
    private ComboBox<String> roleSelect = new ComboBox<>("Update selected user role");
    private Button buttonUpdate = new Button("Update");
    private Grid<User> userGrid;

    @Autowired
    UserRoleService(UserGridService userGridService) {
        this.userGridService = userGridService;
    }

    public ComboBox<String> setRoleSelect() {

        roleSelect.setItems("ROLE_ADMIN", "ROLE_USER");

    return roleSelect;

    }

    public Button setButtonUpdate() {
        userGrid = userGridService.setUserGrid();
        buttonUpdate.addClickListener(event -> {
            for (User user : userGrid.getSelectedItems()) {
                if (!roleSelect.isEmpty()) {
                    user.setRole(roleSelect.getValue());
                }
                userGrid.getDataProvider().refreshItem(user);
            }

            roleSelect.clear();
            userGrid.deselectAll();
        });

        return buttonUpdate;
    }



}
