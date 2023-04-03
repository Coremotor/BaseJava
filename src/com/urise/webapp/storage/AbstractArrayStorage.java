package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void saveResume(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        int index = getIndex(resume.getUuid());
        addResume(resume, index);
        size++;
    }

    public Resume getResume(String uuid) {
        int index = getIndex(uuid);
        return storage[index];
    }

    public Resume[] getAllResume() {
        return Arrays.copyOf(storage, size);
    }

    public void setResume(Resume resume, int index) {
        storage[index] = resume;
    }

    public void deleteResume(String uuid) {
        int index = getIndex(uuid);
        removeResume(index);
        size--;
    }

    public void deleteAllResume() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int getSizeStorage() {
        return size;
    }

    protected void checkExistResume(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void removeResume(int index);
}
