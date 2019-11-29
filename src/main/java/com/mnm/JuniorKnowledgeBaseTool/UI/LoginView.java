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
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;



//@Tag("login-view")
@Route("login")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@HtmlImport("styles/shared-styles.css")
public class LoginView extends VerticalLayout/*PolymerTemplate<LoginView.Model>*/ implements PageConfigurator, AfterNavigationObserver {

    private LoginOverlay login = new LoginOverlay();

    public LoginView(){
        Image image=new Image("frontend/images/login.png", "logo");
        login.setAction("login");
        login.setTitle(image);
        login.setOpened(true);
        login.setDescription("[login: random]   [pass: random]");
        login.addForgotPasswordListener(e->{
            Notification.show("Forgot password not yet handled", 2000, Notification.Position.TOP_CENTER);
        });
        login.addLoginListener(e -> {
            UI.getCurrent().navigate("admin");
            //navigateToMainPage();
            /*boolean isAuthenticated =authenticate(e);
            if (isAuthenticated) {
                navigateToMainPage();
            } else {
                login.setError(true);
            }*/
        });
        getElement().appendChild(login.getElement());
    }


    @SuppressWarnings("unused")
    private boolean authenticate(AbstractLogin.LoginEvent e) {
        return false;
    }

    private void navigateToMainPage() {
        login.getUI().ifPresent(ui -> ui.navigate("login"));
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
}

