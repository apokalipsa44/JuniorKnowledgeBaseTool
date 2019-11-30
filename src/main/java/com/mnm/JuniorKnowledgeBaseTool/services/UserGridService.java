package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepoImpl;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
@UIScope
public class UserGridService {
    private Grid<User> userGrid = new Grid<>(User.class);
    private List<User> userList = new ArrayList<>();
    private UserRepoImpl userRepoImpl;
    private ListDataProvider<User> dataProvider;
    TextField idField = new TextField();
    TextField loginField = new TextField();
    TextField emailField = new TextField();
    TextField roleField = new TextField();


    @Autowired
    public UserGridService(UserRepoImpl userRepoImpl) {this.userRepoImpl = userRepoImpl;


    }

    public Grid<User> setUserGrid() {
        this.dataProvider = new ListDataProvider<>(userInit());
        userGrid.setDataProvider(dataProvider);
        userGrid.removeAllColumns();
        Grid.Column<User> userIdColumn = userGrid
                .addColumn(User::getId).setHeader("Id");
        Grid.Column<User> userLoginColumn = userGrid
                .addColumn(User::getUsername).setHeader("Login");
        Grid.Column<User> userEmailColumn = userGrid
                .addColumn(User::getEmail).setHeader("Email");
        Grid.Column<User> userRoleColumn = userGrid
                .addColumn(User::getRole).setHeader("Role");


        HeaderRow filterRow = userGrid.appendHeaderRow();
        //Id Filter
        idField.addValueChangeListener(event -> dataProvider.addFilter(user -> StringUtils.containsIgnoreCase(user.getId().toString(), idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(userIdColumn).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("Filter");

        //username Filter
        loginField.addValueChangeListener(event -> dataProvider.addFilter(user -> StringUtils.containsIgnoreCase(user.getUsername(), loginField.getValue())));
        loginField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(userLoginColumn).setComponent(loginField);
        loginField.setSizeFull();
        loginField.setPlaceholder("Filter");

        //Email Filter
        emailField.addValueChangeListener(event -> dataProvider.addFilter(user -> StringUtils.containsIgnoreCase(user.getEmail(), emailField.getValue())));
        emailField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(userEmailColumn).setComponent(emailField);
        emailField.setSizeFull();
        emailField.setPlaceholder("Filter");

        //Role Filter
        roleField.addValueChangeListener(event -> dataProvider.addFilter(user -> StringUtils.containsIgnoreCase(user.getRole(), roleField.getValue())));
        roleField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(userRoleColumn).setComponent(roleField);
        roleField.setSizeFull();
        roleField.setPlaceholder("Filter");

        return userGrid;
    }

    public List<User> userInit() {
        //createUser();
        this.userList= userRepoImpl.findAll();
        return this.userList;
    }

    /*public void createUser() {
        User user = new User("janusz", "janusz123", "janusz123@junior.pl", "Admin");
        userRepoImpl.save(user);

        User user1 = new User("michau", "michaumistrz", "michau123@junior.pl", "Admin");
        userRepoImpl.save(user1);

        User user2 = new User("random", "random", "random@random.pl", "User");
        userRepoImpl.save(user2);
    }*/


}
