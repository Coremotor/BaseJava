package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void doSave(Resume resume, Object searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполнено", resume.getUuid());
        }
        int index = (int) searchKey;
        addResume(resume, index);
        size++;
    }

    public Resume doGet(Object searchKey) {
        int index = (int) searchKey;
        return storage[index];
    }

    protected List<Resume> doGetStorage() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public void doUpdate(Resume resume, Object searchKey) {
        int index = (int) searchKey;
        storage[index] = resume;
    }

    public void doDelete(Object searchKey) {
        int index = (int) searchKey;
        removeResume(index);
        size--;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    protected boolean isExist(Object searchKey) {
        int index = (int) searchKey;
        return index >= 0;
    }

    protected abstract void addResume(Resume resume, int index);

    protected abstract void removeResume(int index);
}
