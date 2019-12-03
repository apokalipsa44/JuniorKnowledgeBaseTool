package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.Comment;
import com.mnm.JuniorKnowledgeBaseTool.repositories.CommentRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.ShortcutEvent;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentListService  {
    List<Comment> commentList = new ArrayList<>();
    Grid<Comment> commentGrid = new Grid<>(Comment.class);

    private CommentRepository commentRepository;

    public CommentListService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        commentAddForm(commentRepository);
    }

    public Accordion commentAddForm(CommentRepository commentRepository) {
        Accordion accordion = new Accordion();
        VerticalLayout comments = new VerticalLayout();
        TextArea textArea = new TextArea("Description");
        textArea.getStyle().set("maxHeight", "150px");
        textArea.setPlaceholder("Add comment");
        textArea.addValueChangeListener(textAreaStringComponentValueChangeEvent -> {
            newCommentFieldChange(commentRepository, textArea);
            textArea.clear();
        });
        textArea.addKeyDownListener(Key.ENTER, keyDownEvent -> newCommentFieldChange(commentRepository,textArea));
        comments.add( textArea);
        comments.add(createCommentGrid(commentRepository));
        accordion.add("cooments",comments);
        return accordion;
    }

    public void newCommentFieldChange(CommentRepository commentRepository, TextArea textArea) {
        Comment comment = new Comment();
        comment.setText(textArea.getValue());
        commentRepository.save(comment);
        textArea.clear();
        commentListUpdate();
    }

    public Grid<Comment> createCommentGrid(CommentRepository commentRepository) {
        commentListUpdate();
//        commentGrid.removeAllColumns();
//        commentGrid.addColumns("Text");

        return commentGrid;
    }

    public void commentListUpdate() {
        commentList.clear();
        commentList = commentRepository.findAll();
        commentGrid.setItems(commentList);
    }
}

