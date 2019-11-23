package com.mnm.JuniorKnowledgeBaseTool.repositories;

import com.mnm.JuniorKnowledgeBaseTool.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
