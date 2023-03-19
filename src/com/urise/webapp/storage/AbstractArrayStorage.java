package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (!isExist(index)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполнено", resume.getUuid());
        } else if (isExist(getIndex(resume.getUuid()))) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            addResume(resume, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            size--;
            removeResume(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public boolean isExist(int index) {
        return index >= 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void removeResume(int index);
}
