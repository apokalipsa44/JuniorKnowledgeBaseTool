package com.mnm.JuniorKnowledgeBaseTool.repositories;

import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    Playlist findByPlaylistUrl(String playlistUrl);
}
