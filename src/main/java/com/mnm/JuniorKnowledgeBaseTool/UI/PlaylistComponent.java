package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class PlaylistComponent extends HorizontalLayout {

    public Button nameButton;
    public TextField playlistSize;
    public Button removePlaylistButton;


    public PlaylistComponent() {
        this.nameButton = new Button();
        this.playlistSize = new TextField();
        this.removePlaylistButton = new Button();

    }
}
