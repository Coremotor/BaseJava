package com.urise.webapp.model;

import com.urise.webapp.model.*;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {
    public static final Resume EMPTY = new Resume();
    @Serial
    private static final long serialVersionUID = 1L;

    static {
        EMPTY.addSection(SectionsType.OBJECTIVE, TextSection.EMPTY);
        EMPTY.addSection(SectionsType.PERSONAL, TextSection.EMPTY);
        EMPTY.addSection(SectionsType.ACHIEVEMENT, ListSection.EMPTY);
        EMPTY.addSection(SectionsType.QUALIFICATIONS, ListSection.EMPTY);
        EMPTY.addSection(SectionsType.EXPERIENCE, new CompanySection(Company.EMPTY));
        EMPTY.addSection(SectionsType.EDUCATION, new CompanySection(Company.EMPTY));
    }

    private String uuid;
    private String fullName;
    private Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);
    private Map<SectionsType, Section> sections = new EnumMap<>(SectionsType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid cannot be null");
        Objects.requireNonNull(fullName, "fullName cannot be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactsType, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<ContactsType, String> contacts) {
        this.contacts = contacts;
    }

    public void addContact(ContactsType contact, String value) {
        contacts.put(contact, value);
    }

    public void addSection(SectionsType sectionsType, Section section) {
        sections.put(sectionsType, section);
    }

    public String getContact(ContactsType contactsType) {
        return contacts.get(contactsType);
    }

    public Section getSection(SectionsType sectionsType) {
        return sections.get(sectionsType);
    }

    public Map<SectionsType, Section> getSections() {
        return sections;
    }

    public void setSections(Map<SectionsType, Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid)
                && Objects.equals(fullName, resume.fullName)
                && Objects.equals(contacts, resume.contacts)
                && Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }
}