package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import com.mnm.JuniorKnowledgeBaseTool.model.Source;
import com.mnm.JuniorKnowledgeBaseTool.repositories.CommentRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.PlaylistRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.SourceRepoImpl;
import com.mnm.JuniorKnowledgeBaseTool.services.CommentListService;
import com.mnm.JuniorKnowledgeBaseTool.services.screenshotFromWebpage.ThumbnailCreator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Route(value = "sources")
@HtmlImport("styles/shared-styles.css")
public class SourceComponent extends HorizontalLayout implements HasUrlParameter<String> {

    private NewSource newSource;

    private Grid<Source> sourceGrid = new Grid<>(Source.class);
    private List<Source> sources = new ArrayList<>();

    private String playlistId;
    private Playlist activePlaylist;

    private ThumbnailCreator thumbnailCreator;
    private SourceRepoImpl sourceRepo;
    private CommentListService commentListService;
    private CommentRepository commentRepository;
    private PlaylistRepository playlistRepository;

    @Override
    public void setParameter(BeforeEvent beforeEvent,
                             @OptionalParameter String parameter) {

        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();

        Map<String, List<String>> parametersMap = queryParameters.getParameters();
        playlistId = parametersMap.get("playlistId").get(0);
        System.out.println(playlistId);
        // playlist from repo by provided Id
        Optional<Playlist> activePlaylistOptional = playlistRepository.findById(Long.valueOf(playlistId));
        if (activePlaylistOptional.isPresent()) {
            activePlaylist = activePlaylistOptional.get();
        } else {
            System.out.println("active playlist missing");

        }
        generateSourcesView(thumbnailCreator, sourceRepo);
    }

    public SourceComponent(ThumbnailCreator thumbnailCreator, SourceRepoImpl sourceRepo, CommentListService commentListService, CommentRepository commentRepository, PlaylistRepository playlistRepository) {
        this.thumbnailCreator = thumbnailCreator;
        this.sourceRepo = sourceRepo;
        this.commentListService = commentListService;
        this.commentRepository = commentRepository;
        this.playlistRepository = playlistRepository;






    }

    public void generateSourcesView(ThumbnailCreator thumbnailCreator, SourceRepoImpl sourceRepo) {
        //         creating new source or list if empty
        if (activePlaylist.getSources() == null) {
            activePlaylist.setSources(new ArrayList<Source>());
            sources = activePlaylist.getSources();

        } else {
            sources = activePlaylist.getSources();
        }

//        adding new source
        newSource = new NewSource(sourceRepo, thumbnailCreator);
        sourceRepo.findAll().forEach(source -> sources.add(source));
        sourceGrid.setItems(sources);
        sourceGrid.removeAllColumns();
        sourceGrid.setHeightByRows(true);
        Grid.Column<Source> thumbnailColumn = sourceGrid.addColumn(new ComponentRenderer<>(source -> {
            return new Image(source.getThumbnailUrl(), "thumbnail");
        }));
        Grid.Column<Source> sourceNameColumn = sourceGrid.addColumn(Source::getName);
        Grid.Column<Source> sourceDescription = sourceGrid.addColumn(Source::getDescription);

//        sourceGrid.setColumns("thumbnailColumn", "sourceNameColumn", "sourceDescription");
        Button textField=new Button(activePlaylist.getPlaylistName());
        add(textField,sourceGrid,newSource);
    }


}
