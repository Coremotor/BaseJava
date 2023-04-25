package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected final void addResume(Resume resume, Integer searchKey) {
        storage[size] = resume;
    }

    protected final void removeResume(Integer searchKey) {
        storage[searchKey] = storage[size - 1];
        storage[size - 1] = null;
    }
}
