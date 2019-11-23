package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Source;
import com.mnm.JuniorKnowledgeBaseTool.repositories.SourceRepoImpl;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Route
public class SourceComponent extends HorizontalLayout {

    private Image image = new Image();
    private Grid<Source> sourceGrid = new Grid<>(Source.class);
    private List<Source> sources = new ArrayList<>();

    private SourceRepoImpl sourceRepo;

    public SourceComponent(SourceRepoImpl sourceRepo) {
        this.sourceRepo = sourceRepo;
        sources = sourceRepo.findAll();

        sourceGrid.setItems(sources);
        sourceGrid.addColumn(new ComponentRenderer<>(source -> {
            return new Image(source.getThumbnailUrl(), "thumbnail");
        }));



        add(sourceGrid);


    }
}
