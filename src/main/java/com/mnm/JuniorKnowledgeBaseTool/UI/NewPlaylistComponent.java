package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import com.mnm.JuniorKnowledgeBaseTool.repositories.CommentRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.PlaylistRepository;
import com.mnm.JuniorKnowledgeBaseTool.services.CommentListService;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class NewPlaylistComponent extends HorizontalLayout {
    private TextField playlistName = new TextField();
        private Button addPlaylist= new Button();
    private Accordion commentAddForm = new Accordion();
    private CommentListService commentListService;

private CommentRepository commentRepository;
private PlaylistRepository playlistRepository;


    public NewPlaylistComponent(CommentRepository commentRepository, PlaylistRepository playlistRepository) {
        this.commentRepository = commentRepository;
        this.playlistRepository=playlistRepository;

        commentListService=new CommentListService(commentRepository);
        //commentAddForm=commentListService.commentAddForm(commentRepository);

        addPlaylist.setText("add");
        addPlaylist.addClickListener(e -> {
            Playlist playlist = new Playlist();
            playlist.setPlaylistName(playlistName.getValue());
                        playlistRepository.save(playlist);
            System.out.println("playlist saved");
        });
        add(playlistName, addPlaylist/*,commentAddForm*/);

    }
}
