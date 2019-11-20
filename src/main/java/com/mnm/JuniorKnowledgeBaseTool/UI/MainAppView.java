package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;

@Route("main")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
//@PWA(name = "My Application", shortName = "My App")
class MainAppView implements RouterLayout {

    public MainAppView() {
        Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
        img.setHeight("44px");
        //(new DrawerToggle(), img);
        Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);


        //add(tabs);
        //addToDrawer(tabs);

        //createMenu().addMenuItem(tabs);
    }

    @Override
    public Element getElement() {
        return null;
    }
}
