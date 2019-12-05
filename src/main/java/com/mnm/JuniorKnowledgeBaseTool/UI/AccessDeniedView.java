package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

import org.springframework.stereotype.Component;

@Component // No SpringView annotation because this view can not be navigated to
@UIScope
public class AccessDeniedView extends VerticalLayout {



    public AccessDeniedView() {
        setMargin(true);
        Button button = new Button("ssijss");
        add(button);
    }

//    @Override
//    public void enter(ViewChangeListener.ViewChangeEvent event) {
//    }

}
