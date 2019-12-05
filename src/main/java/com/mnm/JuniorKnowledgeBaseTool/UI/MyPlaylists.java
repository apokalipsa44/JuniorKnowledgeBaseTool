package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import com.mnm.JuniorKnowledgeBaseTool.repositories.CommentRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.PlaylistRepository;
import com.mnm.JuniorKnowledgeBaseTool.services.CommentListService;
import com.mnm.JuniorKnowledgeBaseTool.services.PlaylistService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@Route(value = "my_playlists", layout = MainView.class)

public class MyPlaylists extends HorizontalLayout {
    private HorizontalLayout newPlaylistLayout = new HorizontalLayout();
    private TextField playlistName = new TextField();
    private Button addPlaylistBtn = new Button("add");
    private List<Playlist> playlists = new ArrayList<>();
    private ListDataProvider<Playlist> dataProvider;
    private Grid<Playlist> playlistGrid = new Grid<>(Playlist.class);
    private CommentListService commentListService;
    private Accordion commentForm;


    private PlaylistRepository playlistRepository;
    private CommentRepository commentRepository;

    @Autowired
    public MyPlaylists(PlaylistRepository playlistRepository, CommentRepository commentRepository) {
        this.playlistRepository = playlistRepository;
        this.commentRepository = commentRepository;
        this.dataProvider = new ListDataProvider<>(playlistInit());
        //initializePlaylists();
        addPlaylistBtn.addClickListener(e -> {
            Playlist playlist = new Playlist();
            playlist.setPlaylistName(this.playlistName.getValue());
            playlistRepository.save(playlist);
            playlistGrid.setItems(playlistRepository.findAll());
            System.out.println("playlist saved");
            playlistName.clear();
        });
        playlistName.setPlaceholder("enter new playlist name");
        newPlaylistLayout.add(playlistName, addPlaylistBtn);
        commentListService = new CommentListService(commentRepository);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(newPlaylistLayout, playlistGrid);
        //playlists = playlistRepository.findAll();
        //playlistGrid.setItems(playlists);
        playlistGrid.setDataProvider(dataProvider);
        playlistGrid.removeAllColumns();
        Grid.Column<Playlist> playlistNameColumn = playlistGrid.addColumn(new ComponentRenderer<>(playlist -> {
            Button button=new Button();
            button.setText(playlist.getPlaylistName());
            button.addClickListener(buttonClickEvent -> {
                System.out.println("Klikałke");
                for (Playlist playlist1 : playlistGrid.getSelectedItems()) {
                    String route = UI.getCurrent().getRouter().getUrl(SourceComponent.class, playlist1.getPlaylistUrl());
                    System.out.println(route);
                    UI.getCurrent().navigate(route);
                }
                new Notification("przechodzi na widok sources z parametrem playlist");
            });
            return button;
        })).setHeader("Playlist");
        //kolumnaa ilosc sources w playlist
        //todo playlist::getSouurcesCount()
        Grid.Column<Playlist> playlistSizeColumn=playlistGrid.addColumn(Playlist::getId).setHeader("Playlist Id");
//        playlistGrid.setColumns("playlistNameColumn", "playlistSizeColumn");
        commentForm = commentListService.commentAddForm(commentRepository);
        add(verticalLayout, commentForm);
    }

    public List<Playlist> playlistInit() {

        this.playlists= playlistRepository.findAll();
        System.out.println("Rozmiar: " + this.playlists.size());
        for(Playlist play : this.playlists) {
            System.out.println("tutaj: "+ play.getPlaylistName());
        }
        return this.playlists;
    }

}