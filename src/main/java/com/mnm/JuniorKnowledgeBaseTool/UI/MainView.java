package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Route
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class MainView extends AbstractAppRouterLayout {

    private final Tabs tabs= new Tabs();

    public MainView() {
        Tab tab = new Tab(new RouterLink("Admin", AdminView.class));
        Tab tab1 = new Tab(new RouterLink("New Source", NewSource.class));
        Tab tab2 = new Tab(new RouterLink("Logout", LoginView.class));


        tabs.add(tab);
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
}
