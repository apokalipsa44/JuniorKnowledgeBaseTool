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
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String playlistName;
    @Column(unique = true)
    private String playlistUrl;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playlist", cascade = CascadeType.ALL)
    private List<Source> sources;
    //@OneToMany
    //private List<Comment> comments;




}
