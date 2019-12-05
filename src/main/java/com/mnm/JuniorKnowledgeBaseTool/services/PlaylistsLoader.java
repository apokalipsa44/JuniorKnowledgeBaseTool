package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import com.mnm.JuniorKnowledgeBaseTool.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlaylistsLoader {
    PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistsLoader(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void initializePlaylists() {
        Playlist playlist = new Playlist();
        playlist.setPlaylistName("spring");
        playlist.setPlaylistUrl(UUID.randomUUID().toString());
        playlistRepository.save(playlist);

        Playlist playlist1 = new Playlist();
        playlist1.setPlaylistName("react");
        playlist.setPlaylistUrl(UUID.randomUUID().toString());
        playlistRepository.save(playlist1);

        Playlist playlist2 = new Playlist();
        playlist2.setPlaylistName("java tutorial");
        playlist.setPlaylistUrl(UUID.randomUUID().toString());
        playlistRepository.save(playlist2);

        Playlist playlist3 = new Playlist();
        playlist3.setPlaylistName("spring kurs video");
        playlist.setPlaylistUrl(UUID.randomUUID().toString());
        playlistRepository.save(playlist3);
    }
}
