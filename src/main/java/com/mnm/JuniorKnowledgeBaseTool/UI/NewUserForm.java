package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.services.UserFormService;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;


@Route("newuserform")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@HtmlImport("styles/shared-styles.css")
public class NewUserForm extends FormLayout {
    private final UserFormService userFormService;

    @Autowired
    public NewUserForm(UserFormService userFormService) {
        this.userFormService = userFormService;

        add(userFormService);
        setMaxWidth("350px");

    }
}
