package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void doSave(Resume resume, Integer searchKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполнено", resume.getUuid());
        }
        int index = (int) searchKey;
        addResume(resume, index);
        size++;
    }

    public Resume doGet(Integer searchKey) {
        int index = (int) searchKey;
        return storage[index];
    }

    protected List<Resume> doGetStorage() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public void doUpdate(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    public void doDelete(Integer searchKey) {
        removeResume(searchKey);
        size--;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    protected abstract void addResume(Resume resume, Integer searchKey);

    protected abstract void removeResume(Integer searchKey);
}
