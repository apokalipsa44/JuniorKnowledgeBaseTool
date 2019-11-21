package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Route
//@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
@Theme(Lumo.class)
public class MainView extends AbstractAppRouterLayout {

    private final Tabs tabs= new Tabs();

    public MainView() {
        Tab tab = new Tab("Strona główna");


        Tab tab1 = new Tab("Katalog");


        Tab tab2 = new Tab("Logout");


        /*Map<Tab, Component> tabsWithPage = new HashMap<>();
        //tabsWithPage.put(tab, page1);
        //tabsWithPage.put(tab1, page2);*/
        tabs.add(tab);
        tabs.add(tab1);
        tabs.add(tab2);
        //Div pages = new Div(page1, page2);


        /*Set<Component> showPage = Stream.of(page1)
                .collect(Collectors.toSet());

        tabs.addSelectedChangeListener(event -> {
            showPage.forEach(page -> page.setVisible(false));
            showPage.clear();
            Component selectedPage = tabsWithPage.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            showPage.add(selectedPage);
        });*/

        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        //tabs.setHeight("200px");
        tabs.setFlexGrowForEnclosedTabs(1);
        getElement().appendChild(tabs.getElement());
    }

    @Override
    protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
        appLayout.setBranding(new Span("JuniorDeveloperTools"));
        appLayoutMenu.addMenuItem(new AppLayoutMenuItem(tabs));
    }
}
