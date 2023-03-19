package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected final int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected final void addResume(Resume resume, int index) {
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    protected final void removeResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
        storage[size] = null;
    }
}
