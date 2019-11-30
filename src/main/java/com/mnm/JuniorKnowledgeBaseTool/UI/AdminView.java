package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.UserRole;
import com.mnm.JuniorKnowledgeBaseTool.services.UserFormService;
import com.mnm.JuniorKnowledgeBaseTool.services.UserGridService;
import com.mnm.JuniorKnowledgeBaseTool.services.UserRoleService;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;


@Route(value = "admin", layout = MainView.class)
@Secured(UserRole.ADMIN)
public class AdminView extends VerticalLayout {
    private UserGridService gridService;
    private UserRoleService userRoleService;
    private UserFormService userFormService;

    @Autowired
    public AdminView(UserGridService gridService, UserRoleService userRoleService, UserFormService userFormService) {
        this.gridService = gridService;
        this.userRoleService = userRoleService;
        this.userFormService = userFormService;

        add("User administration panel");
        add(gridService.setUserGrid());
        add(userRoleService.setRoleSelect(), userRoleService.setButtonUpdate());
        add(new Label());
        add(new Label());
        add(new Label("Add new user"));
        add(userFormService);

        setAlignItems(Alignment.CENTER);

    }


}

