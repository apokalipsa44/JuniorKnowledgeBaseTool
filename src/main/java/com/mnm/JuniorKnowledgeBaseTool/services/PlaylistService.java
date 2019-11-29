package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.UI.PlaylistComponent;
import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import com.mnm.JuniorKnowledgeBaseTool.repositories.PlaylistRepository;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {
    private List<Playlist> playlists = new ArrayList<>();

    private PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<HorizontalLayout> createPlaylistList() {
        List<HorizontalLayout>horizontalLayoutList= new ArrayList<>();
        playlistRepository.findAll().forEach(playlist -> playlists.add(playlist));
        VerticalLayout verticalLayout = new VerticalLayout();
        PlaylistComponent playlistComponent = new PlaylistComponent();
        playlists.forEach(playlist -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            playlistComponent.nameButton.setText(playlist.getPlaylistName());
            playlistComponent.playlistSize.setValue("to do size of list");
            playlistComponent.removePlaylistButton.setText("remove");

            playlistComponent.nameButton.addClickListener(e -> new Notification("go to playlist details"));
            playlistComponent.removePlaylistButton.addClickListener(e -> {
                playlistRepository.delete(playlist);
            });
            horizontalLayout.add(playlistComponent.nameButton, playlistComponent.playlistSize, playlistComponent.removePlaylistButton);
            horizontalLayoutList.add(horizontalLayout);

        });
        return horizontalLayoutList;

    }
}

