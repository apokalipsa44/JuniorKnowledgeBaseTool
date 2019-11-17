package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("myplaylists")
public class MyPlaylists extends VerticalLayout {

    public MyPlaylists() {
        Text text = new Text("my playlists");
        add(text);
    }


}
