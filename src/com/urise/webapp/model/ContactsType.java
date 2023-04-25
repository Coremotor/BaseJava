package com.urise.webapp.model;


public enum ContactsType {
    PHONE("Phone: "),
    EMAIL("Email: "),
    SKYPE("Skype: "),
    LINKEDIN("LinkedIn: "),
    GITHUB("GitHub: "),
    STACKOVERFLOW("StackOverflow: "),
    HOME_PAGE("Home page: ");
    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
