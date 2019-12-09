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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    //@OneToMany
    //private List<Comment> comments;

    public String getSourcesCount() {
        return (sources == null) ? "No sources available" : "Available " + getSources().size() + " source(s)";
    }

}
