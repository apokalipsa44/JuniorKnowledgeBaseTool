package com.mnm.JuniorKnowledgeBaseTool.repositories;

import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import com.mnm.JuniorKnowledgeBaseTool.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {
    List<Source>findAllByPlaylist(Playlist playlist);
}
