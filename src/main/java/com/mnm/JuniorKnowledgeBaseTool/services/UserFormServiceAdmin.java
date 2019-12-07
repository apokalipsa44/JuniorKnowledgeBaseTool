package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.UI.NewUserForm;
import com.mnm.JuniorKnowledgeBaseTool.model.UserDTO;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class UserFormServiceAdmin extends FormLayout {
    private final AddUserService addUserService;
    private UserDTO userDTO = new UserDTO();
    private ResponsiveStep responsiveStep = new ResponsiveStep("0", 1);
    private TextField login = new TextField("Enter username");
    private EmailField email = new EmailField("Email");
    private PasswordField password = new PasswordField("Enter password");
    private PasswordField passwordConfirm = new PasswordField("Confirm password");
    private Button saveButton = new Button("Save");
    private Button clearButton = new Button("Clear");
    private HorizontalLayout buttons = new HorizontalLayout(saveButton, clearButton);

    public UserFormServiceAdmin(AddUserService addUserService) {
        this.addUserService = addUserService;

        setResponsiveSteps(responsiveStep);
        email.setClearButtonVisible(true);
        email.setErrorMessage("Please enter a valid email address");
        email.isRequiredIndicatorVisible();

        password.setRevealButtonVisible(true);
        password.setMinLength(6);
        if(password.getValue().length()<6){
            password.setErrorMessage("password must be at least 6 chars");
        }
        passwordConfirm.setRevealButtonVisible(true);
        passwordConfirm.setMinLength(6);
        if(passwordConfirm.getValue().length()<6){
            passwordConfirm.setErrorMessage("password must be at least 6 chars");
        }

        saveButton.addClickListener(e->{
            System.out.println(VaadinServletRequest.getCurrent());
            System.out.println(VaadinServletRequest.getCurrent().getHttpServletRequest().getPathTranslated());
            System.out.println(VaadinServletRequest.getCurrent().getRequestURI());
            this.userDTO.setUsername(login.getValue());
            this.userDTO.setEmail(email.getValue());
            this.userDTO.setPassword(password.getValue());
            addUserService.saveUser(userDTO);

            UI.getCurrent().getPage().reload();
        });
        clearButton.addClickListener(e->{
            login.setValue("");
            email.setValue("");
            password.setValue("");
            passwordConfirm.setValue("");
        });

        add(login, email, password, passwordConfirm, buttons);
    }
}
