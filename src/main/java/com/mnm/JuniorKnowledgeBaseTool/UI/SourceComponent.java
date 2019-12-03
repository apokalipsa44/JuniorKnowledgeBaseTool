package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Comment;
import com.mnm.JuniorKnowledgeBaseTool.model.Source;
import com.mnm.JuniorKnowledgeBaseTool.repositories.CommentRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.SourceRepoImpl;
import com.mnm.JuniorKnowledgeBaseTool.services.CommentListService;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;


import java.util.ArrayList;
import java.util.List;

@Route
public class SourceComponent extends HorizontalLayout {

    private Image image = new Image();
    private Grid<Source> sourceGrid = new Grid<>(Source.class);
    private List<Source> sources = new ArrayList<>();

    private SourceRepoImpl sourceRepo;
    private CommentListService commentListService;
    private CommentRepository commentRepository;

    public SourceComponent(SourceRepoImpl sourceRepo, CommentListService commentListService, CommentRepository commentRepository) {
        this.sourceRepo = sourceRepo;
        this.commentListService = commentListService;
        this.commentRepository = commentRepository;

        sources = sourceRepo.findAll();

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
