package com.urise.webapp.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    public static final ListSection EMPTY = new ListSection("");
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<String> sections;
    public ListSection(List<String> sections) {
        Objects.requireNonNull(sections, "contentList cannot be null");
        this.sections = sections;
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public List<String> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection list = (ListSection) o;
        return Objects.equals(sections, list.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sections);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "sections=" + sections +
                '}';
    }
}
