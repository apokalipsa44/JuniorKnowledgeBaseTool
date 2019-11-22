package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Source;
import com.mnm.JuniorKnowledgeBaseTool.repositories.SourceRepoImpl;
import com.mnm.JuniorKnowledgeBaseTool.services.screenshot.ThumbnailCreator;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Route("newsource")
@UIScope
public class NewSource extends VerticalLayout {
    private Text text = new Text("Add new Source");
    private TextField name = new TextField("source name");
    private TextField url = new TextField("url");
    private TextArea description = new TextArea("description");
    private ComboBox<String> sourceTypeSelect = new ComboBox<>("Source type select");
    private Button saveButton = new Button("Save");
    private Button deleteButton = new Button("Remove");

    private Binder<Source> binder = new Binder<>(Source.class);

    private SourceRepoImpl sourceRepo;
    private ThumbnailCreator thumbnailCreator;


    public NewSource(SourceRepoImpl sourceRepo, ThumbnailCreator thumbnailCreator) {
        this.sourceRepo = sourceRepo;
        this.thumbnailCreator = thumbnailCreator;

        binder.forField(name).bind(Source::getName, Source::setName);
        binder.forField(url).bind(Source::getSourceUrl, Source::setSourceUrl);
        binder.forField(sourceTypeSelect).bind(Source::getSourceType, Source::setSourceType);
        binder.forField(description).bind(Source::getDescription, Source::setDescription);

        sourceTypeSelect.setItems("video", "webpage");
        saveButton.addClickListener(e -> {
            Source source = new Source();
            try {
                binder.writeBean(source);
            } catch (ValidationException ex) {
                ex.printStackTrace();
            }
            // creates thumbnail form url
            try {
                String thumbnailUrl = thumbnailCreator.createThumbnailFromUrl(source.getSourceUrl());
                source.setThumbnailUrl(thumbnailUrl);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
            //binds saves values from form to new source

            System.out.println(source.getThumbnailUrl());
            sourceRepo.save(source);
        });
        deleteButton.addClickListener(e -> {

        });
        add(text, name, url, sourceTypeSelect, description, saveButton, deleteButton);

    }


}
