package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "puppy", layout = MainView.class)
public class TestView extends VerticalLayout {
    public TestView() {
    Button button=new Button("ssij");
   add(button);
    }




}
