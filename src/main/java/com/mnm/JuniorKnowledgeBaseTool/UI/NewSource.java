package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Playlist;
import com.mnm.JuniorKnowledgeBaseTool.model.Source;
import com.mnm.JuniorKnowledgeBaseTool.repositories.PlaylistRepository;
import com.mnm.JuniorKnowledgeBaseTool.repositories.SourceRepoImpl;
import com.mnm.JuniorKnowledgeBaseTool.services.screenshotFromWebpage.ThumbnailCreator;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Route("newsource")
@UIScope
public class NewSource extends VerticalLayout {
    private Text text = new Text("Add new Source");
    private TextField name = new TextField("source name");
    private TextField url = new TextField("url");
    private TextArea description = new TextArea("description");
    private ComboBox<String> sourceTypeSelect = new ComboBox<>("Source type select");
    private Button saveButton = new Button("Save");


    private Binder<Source> binder = new Binder<>(Source.class);

    private SourceRepoImpl sourceRepo;
    private ThumbnailCreator thumbnailCreator;
    private Playlist activePlaylist;
    private List<Source> sources = new ArrayList<>();


    public NewSource(SourceRepoImpl sourceRepo, ThumbnailCreator thumbnailCreator, Playlist activePlaylist) {
        this.activePlaylist = activePlaylist;
        this.sourceRepo = sourceRepo;
        this.thumbnailCreator = thumbnailCreator;

        binder.forField(name).bind(Source::getName, Source::setName);
        binder.forField(url).bind(Source::getSourceUrl, Source::setSourceUrl);
        binder.forField(sourceTypeSelect).bind(Source::getSourceType, Source::setSourceType);
        binder.forField(description).bind(Source::getDescription, Source::setDescription);

        sourceTypeSelect.setItems("Other video", "Webpage", "YouTube");
        saveButton.addClickListener(e -> {
            Source source = new Source();
            // source options
            try {
                binder.writeBean(source);
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
            // ooption "webppage"
            if(source.getSourceType().equals("Webpage")){
                // creates thumbnail form url
                try {
                    String thumbnailUrl = thumbnailCreator.createThumbnailFromUrl(source.getSourceUrl());
                    source.setThumbnailUrl(thumbnailUrl);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                }
            }


            // option "Youtube"
            if(source.getSourceType().equals("YouTube")){
                System.out.println(createThumbnailFromYoutube());
            }
            // option "other video""Other video"
            if (source.getSourceType().equals("Other video")) {
                source.setThumbnailUrl(createThumbnailFromDefault());

            }
            source.setPlaylist(activePlaylist);
            sourceRepo.save(source);
            System.out.println("source saved");
            UI.getCurrent().getPage().reload();
        });

        add(text, name, url, sourceTypeSelect, description, saveButton);

    }

    private String createThumbnailFromDefault() {
        return "frontend/images/other-video.png";
    }

    private String createThumbnailFromYoutube() {
        return "youtube";
    }


}
