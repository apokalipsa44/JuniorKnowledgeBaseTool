package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.Source;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("newsource")
public class NewSourceForm extends VerticalLayout {
    private Text text = new Text("Add new Source");
    private TextField name = new TextField("source name");
    private TextField url = new TextField("url");
    private TextArea description = new TextArea("descryption");
    private ComboBox<String> sourceTypeSelect = new ComboBox<>("Update selected user role");
    private Button saveButton = new Button("Save");
    private Button deleteButton = new Button("Remove");

    Binder<Source> binder = new Binder<>(Source.class);



    public NewSourceForm() {
        binder.forField(name).bind(Source::getName, Source::setName);
        binder.forField(url).bind(Source::getSourceUrl, Source::setSourceUrl);
        binder.forField(sourceTypeSelect).bind(Source::getSourceType, Source::setSourceType);
        binder.forField(description).bind(Source::getDescription, Source::setDescription);

        sourceTypeSelect.setItems("video", "webpage");
        saveButton.addClickListener(e -> {
        });
        deleteButton.addClickListener(e -> {

        });
        add(text, name, url, sourceTypeSelect, description, saveButton, deleteButton);

    }


}
