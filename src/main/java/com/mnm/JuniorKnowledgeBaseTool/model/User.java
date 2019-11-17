package com.mnm.JuniorKnowledgeBaseTool.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    //private Enum<UserRole> role;
    private String role;

    public User(String login, String password, String email, String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role.toUpperCase();
    }

    @OneToOne
    private Comment comment;


}
