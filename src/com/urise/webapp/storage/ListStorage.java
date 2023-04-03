package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storage = new ArrayList<>();

    public void saveResume(Resume resume) {
        storage.add(resume);
    }

    public Resume getResume(String uuid) {
        return storage.get(getIndex(uuid));
    }

    public Resume[] getAllResume() {
        return Arrays.copyOf(storage.toArray(), storage.size(), Resume[].class);
    }

    public void setResume(Resume resume, int index) {
        storage.set(index, resume);
    }

    public void deleteResume(String uuid) {
        storage.remove(getIndex(uuid));
    }

    public void deleteAllResume() {
        storage.clear();
    }

    public int getSizeStorage() {
        return storage.size();
    }

    protected void checkExistResume(Resume resume) {
        if (storage.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    protected final int getIndex(String uuid) {
        int size = storage.size();
        for (int i = 0; i < size; i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
