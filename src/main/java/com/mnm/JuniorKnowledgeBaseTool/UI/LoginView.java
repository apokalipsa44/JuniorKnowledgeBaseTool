package com.mnm.JuniorKnowledgeBaseTool.UI;



import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;



@Route("login")
public class LoginView extends VerticalLayout  {

    private LoginOverlay login = new LoginOverlay(); //

    public LoginView(){
        login.setAction("login"); //
        login.setOpened(true); //
        login.setTitle("Junior Developer Tools 3D");
        login.setDescription("Please login to proceed");
        getElement().appendChild(login.getElement()); //
    }
}