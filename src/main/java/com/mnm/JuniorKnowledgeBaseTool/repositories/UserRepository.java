package com.mnm.JuniorKnowledgeBaseTool.repositories;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    User findByLogin(String login);
}
