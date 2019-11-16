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

    private Enum<SourceType> sourceTypeEnum;

    @ManyToOne
    private Playlist playlist;

    private String thumbnailUrl;




}
