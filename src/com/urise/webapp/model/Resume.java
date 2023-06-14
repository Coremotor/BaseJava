package com.urise.webapp.model;

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
    @Serial
    private static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;
    private Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);
    private Map<SectionsType, AbstractSection> sections = new EnumMap<>(SectionsType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume() {
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

    public String getUuid() {
        return uuid;
    }

    public Map<ContactsType, String> getContacts() {
        return contacts;
    }

    public void addContact(ContactsType contact, String value) {
        contacts.put(contact, value);
    }

    public void addSection(SectionsType sectionsType, AbstractSection section) {
        sections.put(sectionsType, section);
    }

    public String getFullName() {
        return fullName;
    }

    public String getContacts(ContactsType contactsType) {
        return contacts.get(contactsType);
    }
    public String getContact(ContactsType contactsType) {
        return contacts.get(contactsType);
    }
    public AbstractSection getSection(SectionsType sectionsType) {
        return sections.get(sectionsType);
    }

    public Map<SectionsType, AbstractSection> getSections() {
        return sections;
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
                && Objects.equals(fullName, resume.fullName)
                && Objects.equals(contacts, resume.contacts)
                && Objects.equals(sections, resume.sections);
    }

    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }
}
