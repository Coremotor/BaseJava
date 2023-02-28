package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
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
            System.out.println("Резюме " + resume.getUuid() + " не найдено");
        } else {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            return storage[index];
        }
        System.out.println("Резюме " + uuid + " не найдено");
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public boolean isExist(int index) {
        return index != -1;
    }

    protected abstract int getIndex(String uuid);
    public abstract void save(Resume resume);
    public abstract void delete(String uuid);
}
