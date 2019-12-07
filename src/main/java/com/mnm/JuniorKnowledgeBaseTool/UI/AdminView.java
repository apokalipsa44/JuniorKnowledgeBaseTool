package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.UserRole;
import com.mnm.JuniorKnowledgeBaseTool.services.UserFormService;
import com.mnm.JuniorKnowledgeBaseTool.services.UserFormServiceAdmin;
import com.mnm.JuniorKnowledgeBaseTool.services.UserGridService;
import com.mnm.JuniorKnowledgeBaseTool.services.UserRoleService;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;


@Route(value = "admin", layout = MainView.class)
@Secured(UserRole.ADMIN)
@HtmlImport("styles/shared-styles.html")
public class AdminView extends VerticalLayout {
    private UserGridService gridService;
    private UserRoleService userRoleService;
    private UserFormServiceAdmin userFormServiceAdmin;

    @Autowired
    public AdminView(UserGridService gridService, UserRoleService userRoleService, UserFormServiceAdmin userFormService) {
        this.gridService = gridService;
        this.userRoleService = userRoleService;
        this.userFormServiceAdmin = userFormService;

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

