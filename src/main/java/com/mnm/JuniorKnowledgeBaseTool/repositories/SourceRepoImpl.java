package com.mnm.JuniorKnowledgeBaseTool.repositories;

import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import com.mnm.JuniorKnowledgeBaseTool.model.Source;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceRepoImpl {
    private SourceRepository sourceRepository;

    public SourceRepoImpl(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public List<Source> findAll(){
        return sourceRepository.findAll();
    }

    public Source save(Source source){
        return sourceRepository.save(source);
    }

    public List<Source> findAllByPlaylist(Playlist playlist) {
        return sourceRepository.findAllByPlaylist(playlist);
    }
}
