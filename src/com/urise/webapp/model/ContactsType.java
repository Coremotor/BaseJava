package com.urise.webapp.model;

public enum ContactsType {
    PHONE("Phone"),
    EMAIL("Email") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("mailto:" + value, value);
        }
    },
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("skype:" + value, value);
        }
    },
    LINKEDIN("LinkedIn") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    GITHUB("GitHub") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    STACKOVERFLOW("StackOverflow") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    HOME_PAGE("Home page") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };
    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }

    public String getTitle() {
        return title;
    }

    public String toHtml(String value) {
        return title + ": " + value;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toLink(String href) {
        return toLink(href, title);
    }
}