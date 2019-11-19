package com.mnm.JuniorKnowledgeBaseTool.UI;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepoImpl;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import com.mnm.JuniorKnowledgeBaseTool.services.AdminPanelService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;


@Route("admin")
public class AdminPanel extends VerticalLayout {
    private UserRepository userRepository;
    private AdminPanelService gridService;
    private UserRepoImpl userRepoImpl;

    public AdminPanel(UserRepository userRepository, AdminPanelService gridService, UserRepoImpl userRepoImpl) {
        this.userRepository = userRepository;
        this.gridService = gridService;
        this.userRepoImpl = userRepoImpl;

        Grid<User> userGrid = new Grid<>(User.class);
        ListDataProvider<User> dataProvider = new ListDataProvider<>(gridService.userInit());
        userGrid.setDataProvider(dataProvider);
        userGrid.removeAllColumns();
        Grid.Column<User> userIdColumn = userGrid
                .addColumn(User::getId).setHeader("Id");
        Grid.Column<User> userLoginColumn = userGrid
                .addColumn(User::getLogin).setHeader("Login");
        Grid.Column<User> userEmailColumn = userGrid
                .addColumn(User::getEmail).setHeader("Email");
        Grid.Column<User> userRoleColumn = userGrid
                .addColumn(User::getRole).setHeader("Email");
        //userGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        //userGrid.setColumns(userLoginColumn, userEmailColumn, userRoleColumn);


        HeaderRow filterRow = userGrid.appendHeaderRow();
        //Id Filter
        TextField idField = new TextField();
        idField.addValueChangeListener(event -> dataProvider.addFilter(user -> StringUtils.containsIgnoreCase(user.getId().toString(), idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(userIdColumn).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("Filter");
        //login Filter
        TextField loginField = new TextField();
        loginField.addValueChangeListener(event -> dataProvider.addFilter(user -> StringUtils.containsIgnoreCase(user.getLogin(), loginField.getValue())));
        loginField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(userLoginColumn).setComponent(loginField);
        loginField.setSizeFull();
        loginField.setPlaceholder("Filter");

        //Email Filter
        TextField emailField = new TextField();
        emailField.addValueChangeListener(event -> dataProvider.addFilter(user -> StringUtils.containsIgnoreCase(user.getEmail(), emailField.getValue())));
        emailField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(userEmailColumn).setComponent(emailField);
        emailField.setSizeFull();
        emailField.setPlaceholder("Filter");

        //Role Filter
        TextField roleField = new TextField();
        roleField.addValueChangeListener(event -> dataProvider.addFilter(user -> StringUtils.containsIgnoreCase(user.getRole(), roleField.getValue())));
        roleField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(userRoleColumn).setComponent(roleField);
        roleField.setSizeFull();
        roleField.setPlaceholder("Filter");


        add(userGrid);


        ComboBox<String> statusSelect = new ComboBox<>("Update selected user role");
        statusSelect.setItems("ROLE_ADMIN", "ROLE_USER");
        statusSelect.clear();


        Button updateButton = new Button("Update");
        updateButton.addClickListener(event -> {
            for (User user : userGrid.getSelectedItems()) {
                if (!statusSelect.isEmpty()) {
                    user.setRole(statusSelect.getValue());
                }
                userGrid.getDataProvider().refreshItem(user);
            }

            statusSelect.clear();
            userGrid.deselectAll();
        });

        add(statusSelect, updateButton);


        NewUserForm newUserForm = new NewUserForm(userRepoImpl);

        add(newUserForm);

    }


}

