package com.mnm.JuniorKnowledgeBaseTool.UI;


import com.mnm.JuniorKnowledgeBaseTool.repositories.PlaylistRepository;
import com.mnm.JuniorKnowledgeBaseTool.services.PlaylistService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("myplaylists")
public class MyPlaylists extends VerticalLayout {
    private List<HorizontalLayout> horizontalLayoutList = new ArrayList<>();


    private PlaylistService playlistService;

    public MyPlaylists(PlaylistService playlistService) {
        this.playlistService = playlistService;

        horizontalLayoutList = playlistService.createPlaylistList();
//        horizontalLayoutList.forEach(horizontalLayout -> add(horizontalLayout));
        for (int i = 0; i < horizontalLayoutList.size(); i++) {
            add(horizontalLayoutList.get(i));
        }

    }
}

