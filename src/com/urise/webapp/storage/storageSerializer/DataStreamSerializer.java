package com.urise.webapp.storage.storageSerializer;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.ContactsType;
import com.urise.webapp.model.*;
import jdk.jfr.Frequency;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements Serializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream osOut = new DataOutputStream(os)) {
            osOut.writeUTF(r.getUuid());
            osOut.writeUTF(r.getFullName());
            writeObject(osOut, r.getContacts().entrySet(), entry -> {
                osOut.writeUTF(entry.getKey().name());
                osOut.writeUTF(entry.getValue());
            });
            writeObject(osOut, r.getSections().entrySet(), entry -> {
                SectionsType sectionsType = entry.getKey();
                AbstractSection section = entry.getValue();
                osOut.writeUTF(sectionsType.name());
                switch (sectionsType) {
                    case PERSONAL, OBJECTIVE -> {
                        osOut.writeUTF(((TextSection) section).getContent());
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        writeObject(osOut, ((ListSection) section).getSections(), osOut::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        writeObject(osOut, ((CompanySection) section).getCompanies(), organization -> {
                            osOut.writeUTF(organization.getName());
                            osOut.writeUTF(organization.getWebsite());
                            writeObject(osOut, organization.getPeriods(), period -> {
                                writeLocalDate(osOut, period.getStartDate());
                                writeLocalDate(osOut, period.getEndDate());
                                osOut.writeUTF(period.getTitle());
                                osOut.writeUTF(period.getDescription());
                            });
                        });
                    }
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream isOut = new DataInputStream(is)) {
            String uuid = isOut.readUTF();
            String fullName = isOut.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readObject(isOut, () -> resume.addContact(ContactsType.valueOf(isOut.readUTF()), isOut.readUTF()));
            readObject(isOut, () -> {
                SectionsType sectionsType = SectionsType.valueOf(isOut.readUTF());
                resume.addSection(sectionsType, getSection(isOut, sectionsType));
            });
            return resume;
        }
    }

    private AbstractSection getSection(DataInputStream is, SectionsType section) throws IOException {
        return switch (section) {
            case PERSONAL, OBJECTIVE -> new TextSection(is.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(is, is::readUTF));
            case EXPERIENCE, EDUCATION -> new CompanySection(readList(is, () -> new Company(
                    is.readUTF(), is.readUTF(), readList(is, () -> new Company.Period(
                    getLocalDate(is), getLocalDate(is), is.readUTF(), is.readUTF()
            ))
            )));
        };
    }

    private void readObject(DataInputStream isOut, Reader reader) throws IOException {
        int size = isOut.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private <T> void writeObject(DataOutputStream osOut, Collection<T> collection, Writer<T> write) throws IOException {
        osOut.writeInt(collection.size());
        for (T item : collection) {
            write.write(item);
        }
    }

    private void writeLocalDate(DataOutputStream os, LocalDate localDate) throws IOException {
        os.writeInt(localDate.getYear());
        os.writeInt(localDate.getMonth().getValue());
    }

    private <T> List<T> readList(DataInputStream is, GetRead<T> getRead) throws IOException {
        int size = is.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(getRead.getRead());
        }
        return list;
    }

    private LocalDate getLocalDate(DataInputStream is) throws IOException {
        return LocalDate.of(is.readInt(), is.readInt(), 1);
    }

    @FunctionalInterface
    private interface Reader {
        void read() throws IOException;
    }

    @FunctionalInterface
    private interface GetRead<T> {
        T getRead() throws IOException;
    }

    @FunctionalInterface
    private interface Writer<T> {
        void write(T t) throws IOException;
    }
}
