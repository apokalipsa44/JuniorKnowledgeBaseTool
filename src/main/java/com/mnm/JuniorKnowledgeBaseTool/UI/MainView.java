package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;


@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends AbstractAppRouterLayout {

    private final Tabs tabs= new Tabs();

    public MainView() {
        Tab tab = new Tab("Tab three");
        tabs.add(tab);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setHeight("200px");
        getElement().appendChild(tab.getElement());
    }

    @Override
    protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
        appLayout.setBranding(new Span("Buongiorno"));
        appLayoutMenu.addMenuItem(new AppLayoutMenuItem(tabs));
    }
}
