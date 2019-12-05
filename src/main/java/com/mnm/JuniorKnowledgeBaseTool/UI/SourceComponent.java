package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Source;
import com.mnm.JuniorKnowledgeBaseTool.repositories.CommentRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.PlaylistRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.SourceRepoImpl;
import com.mnm.JuniorKnowledgeBaseTool.services.CommentListService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Route(value = "sources")
public class SourceComponent extends HorizontalLayout implements HasUrlParameter<String> {


    private Image image = new Image();
    private Grid<Source> sourceGrid = new Grid<>(Source.class);
    private List<Source> sources = new ArrayList<>();
    private String playlistUrl;
    private String playlistId;

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
            playlistId=parametersMap.get("playlistId").get(0);
        System.out.println(playlistId);

    }

    public SourceComponent(SourceRepoImpl sourceRepo, CommentListService commentListService, CommentRepository commentRepository, PlaylistRepository playlistRepository) {
        this.sourceRepo = sourceRepo;
        this.commentListService = commentListService;
        this.commentRepository = commentRepository;
        this.playlistRepository = playlistRepository;
        System.out.println(playlistUrl);
        //Playlist playlist = playlistRepository.findByPlaylistUrl(playlistUrl);

        //sources = sourceRepo.findAllByPlaylist(playlist);

        sourceGrid.setItems(sources);
        sourceGrid.removeAllColumns();
        sourceGrid.setHeightByRows(true);
        Grid.Column<Source> thumbnailColumn = sourceGrid.addColumn(new ComponentRenderer<>(source -> {
            return new Image(source.getThumbnailUrl(), "thumbnail");
        }));
        Grid.Column<Source> sourceNameColumn = sourceGrid.addColumn(Source::getName);
        Grid.Column<Source> sourceDescription = sourceGrid.addColumn(Source::getDescription);

//        sourceGrid.setColumns("thumbnailColumn", "sourceNameColumn", "sourceDescription");

        add(sourceGrid);


    }


}
