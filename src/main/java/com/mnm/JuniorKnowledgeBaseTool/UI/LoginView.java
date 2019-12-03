package com.mnm.JuniorKnowledgeBaseTool.UI;



import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;


//@Tag("username-view")
@Route("login")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@HtmlImport("styles/shared-styles.css")
@Slf4j
public class LoginView extends VerticalLayout/*PolymerTemplate<LoginView.Model>*/ implements PageConfigurator, AfterNavigationObserver {
    private AuthenticationManager authenticationManager;
    private LoginOverlay login = new LoginOverlay();

    @Autowired
    public LoginView(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;

        Image image=new Image("frontend/images/login.png", "logo");
        //login.setAction("login");
        login.setTitle(image);
        login.setOpened(true);
        login.setDescription("[username: random]   [pass: random]");
        login.addForgotPasswordListener(e->{
            Notification.show("Forgot password not yet handled", 2000, Notification.Position.TOP_CENTER);
        });
        login.addLoginListener(e -> {
            //navigateToMainPage();
            log.info("Jedziemy z logowaniem");
            boolean isAuthenticated = authenticate(e);
            log.info("I jak poszło? {}", isAuthenticated);
            if (isAuthenticated) {
                //UI.getCurrent().navigate("admin");
                login.setAction("my_playlists");
                getUI().ifPresent(ui -> ui.navigate(MyPlaylists.class));
                UI.getCurrent().getPage().reload();
                log.info("Bangla?");
            } else {
                login.setError(true);
                login.setAction("/");
            }
        });
        //login.addLoginListener(this::authenticate);

        getElement().appendChild(login.getElement());
    }


    public boolean authenticate(AbstractLogin.LoginEvent e) {
        try {
            Authentication token= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(e.getUsername(),e.getPassword()));
            log.info("Token autoryzacji: {}", token);
            SecurityContextHolder.getContext().setAuthentication(token);
            return true;
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    private void navigateToMainPage() {
        login.getUI().ifPresent(ui -> ui.navigate("admin"));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {

    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {

    }

    public interface Model extends TemplateModel {
        void setError(boolean error);
    }

    /*@Override
    public void beforeEnter(BeforeEnterEvent event) { //
        // inform the user about an authentication error
        // (yes, the API for resolving query parameters is annoying...)
        if(!event.getLocation().getQueryParameters().getParameters().getOrDefault("error", Collections.emptyList()).isEmpty()) {
            login.setError(true); //
        }
    }*/
}


