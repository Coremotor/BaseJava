package com.urise.webapp.model;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume {

    private final String uuid;
    private final String fullName;
    private Map<ContactsType, String> contacts;
    private Map<SectionsType, AbstractSection> sections;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String toString() {
        return "{ uuid: " + uuid + ", fullName: " + fullName + " }";
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContacts(ContactsType contactsType) {
        return contacts.get(contactsType);
    }

    public AbstractSection getSection(SectionsType sectionsType) {
        return sections.get(sectionsType);
    }

    public void setContacts(Map<ContactsType, String> contacts) {
        this.contacts = contacts;
    }

    public void setSections(Map<SectionsType, AbstractSection> sections) {
        this.sections = sections;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid)
                && Objects.equals(fullName, resume.fullName);
    }

    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }
}
