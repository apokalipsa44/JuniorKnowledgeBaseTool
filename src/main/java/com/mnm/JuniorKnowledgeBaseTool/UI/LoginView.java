package com.mnm.JuniorKnowledgeBaseTool.UI;



import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Route("login")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@HtmlImport("styles/shared-styles.css")
public class LoginView extends VerticalLayout  {

    private LoginOverlay login = new LoginOverlay();

    public LoginView(){
        login.setAction("login");
        login.setOpened(true);
        login.setTitle("Junior Developer Tools 3D");
        login.setDescription("Please login to proceed");
        login.addForgotPasswordListener(e->{
            Notification.show("Forgot password not yet handled", 30, Notification.Position.TOP_CENTER);
        });
        getElement().appendChild(login.getElement());
    }
}