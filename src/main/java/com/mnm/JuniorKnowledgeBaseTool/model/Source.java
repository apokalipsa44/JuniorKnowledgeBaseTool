package com.mnm.JuniorKnowledgeBaseTool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String sourceUrl;

    private String sourceType;

    private String description;

    @ManyToOne
    @JoinColumn(name="playlist_id", nullable = false)
    private Playlist playlist;
//
//    //    @OneToMany
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "source", cascade = CascadeType.ALL)
//    private List<Comment> comments;

    private String thumbnailUrl;


}
