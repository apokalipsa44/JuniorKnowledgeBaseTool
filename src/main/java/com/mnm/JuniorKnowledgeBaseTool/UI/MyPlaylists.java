package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.repositories.CommentRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.PlaylistRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import com.mnm.JuniorKnowledgeBaseTool.services.CommentListService;
import com.mnm.JuniorKnowledgeBaseTool.services.PlaylistService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "my_playlists", layout = MainView.class)
//@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@HtmlImport("styles/shared-styles.html")
public class MyPlaylists extends HorizontalLayout {
    private HorizontalLayout newPlaylistLayout = new HorizontalLayout();
    private TextField playlistName = new TextField();
    private Button addPlaylistBtn = new Button("add");
    private List<Playlist> playlists = new ArrayList<>();
    private ListDataProvider<Playlist> dataProvider;
    private Grid<Playlist> playlistGrid = new Grid<>(Playlist.class);
    private CommentListService commentListService;
    private Accordion commentForm;

    private User activeUser;

    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;
    private CommentRepository commentRepository;

    @Autowired
    public MyPlaylists(PlaylistRepository playlistRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;

        this.activeUser = getActveUser(userRepository);

        System.out.println(activeUser.getUsername());

        this.dataProvider = new ListDataProvider<>(playlistInit());
//creates new playlist
        addPlaylistBtn.addClickListener(e -> {
            Playlist playlist = new Playlist();
            playlist.setPlaylistName(this.playlistName.getValue());
            playlist.setUser(activeUser);
            playlists.add(playlist);
            playlistRepository.save(playlist);
            playlistGrid.setItems(activeUser.getPlaylists());
            System.out.println("playlist saved");
            System.out.println(playlist.getUser().getUsername());
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
            Button button = new Button();
            button.setText(playlist.getPlaylistName());
            button.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_PRIMARY);
            button.setWidth("75%");
            button.addClickListener(buttonClickEvent -> {
                System.out.println("Klikałke");
//                for (Playlist playlist1 : playlistGrid.getSelectedItems()) {
//                    String route = UI.getCurrent().getRouter().getUrl(SourceComponent.class, playlist1.getPlaylistUrl());
//                    System.out.println(route);
//                    UI.getCurrent().navigate(route);
//                }

//                String route = UI.getCurrent().getRouter().getUrl(SourceComponent.class, playlist.getPlaylistUrl());
                Map<String, String> map = new HashMap<>();
                map.put("playlistId", String.valueOf(playlist.getId()));
                QueryParameters queryParameters = QueryParameters.simple(map);
                UI.getCurrent().navigate("sources", queryParameters);

                System.out.println("++++++++++");
            });
            return button;
        })).setHeader("Playlist");
        //kolumnaa ilosc sources w playlist
        Grid.Column<Playlist> playlistSizeColumn = playlistGrid.addColumn(Playlist::getSourcesCount).setHeader("Playlist Id");
//        playlistGrid.setColumns("playlistNameColumn", "playlistSizeColumn");
        commentForm = commentListService.commentAddForm(commentRepository);
        add(verticalLayout, commentForm);
    }

    public User getActveUser(UserRepository userRepository) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }

    public List<Playlist> playlistInit() {
playlists.clear();
        playlists = activeUser.getPlaylists();
        System.out.println("Rozmiar: " + this.playlists.size());
        for (Playlist play : this.playlists) {
            System.out.println("tutaj: " + play.getPlaylistName());
        }
        return playlists;
    }

}