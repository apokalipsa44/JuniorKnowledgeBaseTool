package com.mnm.JuniorKnowledgeBaseTool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
//@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private LocalDate addedDate;

    @OneToOne
    private User user;

    @ManyToOne
    private Playlist playlist;

    public Comment() {
        this.addedDate=LocalDate.now();
    }
}
