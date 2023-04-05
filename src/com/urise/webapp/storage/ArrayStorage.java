package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected final void addResume(Resume resume, int index) {
        storage[size] = resume;
    }

    protected final void removeResume(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }
}
