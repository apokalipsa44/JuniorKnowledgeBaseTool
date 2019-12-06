package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.UserRole;
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.context.annotation.Role;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.jdbc.support.incrementer.AbstractDataFieldMaxValueIncrementer;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Route
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@HtmlImport("styles/shared-styles.css")
public class MainView extends AbstractAppRouterLayout {

    private final Tabs tabs= new Tabs();
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public MainView() {
        if(!hasUserRole) {
            Tab tab = new Tab(new RouterLink("Admin", AdminView.class));
            tabs.add(tab);
        }

        Tab tab1 = new Tab(new RouterLink("Playlists", MyPlaylists.class));
        Tab tab2 = new Tab(new RouterLink("Logout: " + getUserDetails(), LoginView.class));




        tabs.add(tab1);
        tabs.add(tab2);


        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.setFlexGrowForEnclosedTabs(1);
        getElement().appendChild(tabs.getElement());



    }


    @Override
    protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
        appLayout.setBranding(new Span("JuniorDeveloperTools"));
        appLayoutMenu.addMenuItem(new AppLayoutMenuItem(tabs));

    }

    boolean hasUserRole = auth.getAuthorities().stream()
            .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));

    private String getUserDetails() {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();

        return userDetails.getUsername();

    }
}
