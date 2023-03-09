package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {
    void clear();

    Resume[] getAll();

    Resume get(String uuid);

    void save(Resume resume);

    void update(Resume resume);

    void delete(String uuid);

    int size();
}
