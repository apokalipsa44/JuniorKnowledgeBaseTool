package com.mnm.JuniorKnowledgeBaseTool.model;

public class UserRole {
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";

    public UserRole() {
    }

    public static String[] getAllRoles() {
        return new String[] {USER, ADMIN};
    }
}
