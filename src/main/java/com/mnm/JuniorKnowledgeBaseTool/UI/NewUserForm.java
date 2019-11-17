package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("newuserform")
public class NewUserForm extends VerticalLayout {
    public NewUserForm() {
        Text text=new Text("create new user");
        add(text);

    }
}
