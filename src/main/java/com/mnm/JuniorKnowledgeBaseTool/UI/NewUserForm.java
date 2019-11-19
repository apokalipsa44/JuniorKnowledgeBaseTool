package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepoImpl;
import com.mnm.JuniorKnowledgeBaseTool.services.AddUserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Route("newuserform")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@HtmlImport("styles/shared-styles.css")
public class NewUserForm extends FormLayout {
    private AddUserService addUserService;
    private UserRepoImpl userRepoImpl;


    public NewUserForm(UserRepoImpl userRepoImpl) {

        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        Text text = new Text("create new user");

        TextField login = new TextField("Enter login");

        EmailField email = new EmailField("Email");
        email.setClearButtonVisible(true);
        email.setErrorMessage("Please enter a valid email address");
        email.isRequiredIndicatorVisible();

        PasswordField password = new PasswordField("Enter password");
        password.setRevealButtonVisible(true);
        password.setMinLength(6);
        if(password.getValue().length()<6){
            password.setErrorMessage("password must be at least 6 chars");
        }
        PasswordField passwordConfirm = new PasswordField("Confirm password");
        passwordConfirm.setRevealButtonVisible(true);
        passwordConfirm.setMinLength(6);
        if(passwordConfirm.getValue().length()<6){
            passwordConfirm.setErrorMessage("password must be at least 6 chars");
        }



        Button saveButton = new Button("Save");
        Button clearButton = new Button("Clear");
        saveButton.addClickListener(e->{
            userRepoImpl.save(new User(login.getValue(), password.getValue(), email.getValue()));
        });
        clearButton.addClickListener(e->{
            login.setValue("");
            email.setValue("");
            password.setValue("");
            passwordConfirm.setValue("");
        });
        HorizontalLayout buttons = new HorizontalLayout(saveButton, clearButton);

        add(login, email, password, passwordConfirm, buttons);
        setMaxWidth("350px");

    }
}
