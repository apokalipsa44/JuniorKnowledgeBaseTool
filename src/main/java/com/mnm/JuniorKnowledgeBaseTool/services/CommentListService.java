package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.Comment;
import com.mnm.JuniorKnowledgeBaseTool.repositories.CommentRepository;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentListService {
    List<Comment> commentList = new ArrayList<>();
    Grid<Comment> commentGrid = new Grid<>(Comment.class);

    private CommentRepository commentRepository;

    public CommentListService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Grid<Comment> createCommentGrid(CommentRepository commentRepository) {
        commentListUpdate();
//        commentGrid.removeAllColumns();
//        commentGrid.addColumns("Text");

        return commentGrid;
    }

    public void commentListUpdate(){
        commentList.clear();
        commentList = commentRepository.findAll();
        commentGrid.setItems(commentList);
    }
}
