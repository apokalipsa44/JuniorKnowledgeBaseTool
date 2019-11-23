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
        this.commentListService=commentListService;
        this.commentRepository=commentRepository;

        sources = sourceRepo.findAll();

        sourceGrid.setItems(sources);
//        sourceGrid.removeColumnByKey("Thumbnail Url");
//        sourceGrid.removeColumnByKey("Id");
        sourceGrid.setHeightByRows(true);
        sourceGrid.addColumn(new ComponentRenderer<>(source -> {
            return new Image(source.getThumbnailUrl(), "thumbnail");
        }));
        sourceGrid.addColumn(new ComponentRenderer<>(source -> {
            Accordion accordion=new Accordion();
            VerticalLayout comments=new VerticalLayout();
            Button button=new Button("ddj");
            TextArea textArea = new TextArea("Description");
            textArea.getStyle().set("maxHeight", "150px");
            textArea.setPlaceholder("Add comment");
            textArea.addValueChangeListener(textAreaStringComponentValueChangeEvent -> {
                Comment comment=new Comment();
                comment.setText(textArea.getValue());
                commentRepository.save(comment);
                commentListService.commentListUpdate();
            });
            comments.add(button);
            comments.add(textArea);
            comments.add(commentListService.createCommentGrid(commentRepository));
accordion.add("Comments",comments);
            return accordion;
        }));



        add(sourceGrid);


    }
}
