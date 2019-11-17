package com.mnm.JuniorKnowledgeBaseTool.UI;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;

@Route
public class Login extends VerticalLayout {
    public static final String ROUTE = "login";

    public Login() {
        TextField userNameTextField = new TextField();
        userNameTextField.getElement().setAttribute("name", "username"); //
        PasswordField passwordField = new PasswordField();
        passwordField.getElement().setAttribute("name", "password"); //
        Button submitButton = new Button("Login");
        submitButton.setId("submitbutton"); //
        UI.getCurrent().getPage().executeJavaScript("document.getElementById('submitbutton').addEventListener('click', () => document.getElementById('ironform').submit());"); //

        FormLayout formLayout = new FormLayout(); //
        formLayout.add(userNameTextField, passwordField, submitButton);

        Element formElement = new Element("form"); //
        formElement.setAttribute("method", "post");
        formElement.setAttribute("action", "login");
        formElement.appendChild(formLayout.getElement());

        Element ironForm = new Element("iron-form"); //
        ironForm.setAttribute("id", "ironform");
        ironForm.setAttribute("allow-redirect", true); //
        ironForm.appendChild(formElement);

        getElement().appendChild(ironForm); //

        setClassName("login-view");
    }


}
