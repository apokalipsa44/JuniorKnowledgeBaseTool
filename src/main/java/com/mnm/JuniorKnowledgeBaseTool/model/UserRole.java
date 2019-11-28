package com.mnm.JuniorKnowledgeBaseTool.model;

public class UserRole {
    public static final String USER = "user";
    public static final String ADMIN = "admin";

    public UserRole() {
    }

    public static String[] getAllRoles() {
        return new String[] {USER, ADMIN};
    }
}
