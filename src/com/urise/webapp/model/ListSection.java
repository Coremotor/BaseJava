package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> sections;

    public ListSection(List<String> sections) {
        this.sections = sections;
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
