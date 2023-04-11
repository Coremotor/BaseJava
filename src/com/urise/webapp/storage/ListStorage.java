package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    protected void doSave(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    public Resume doGet(Object searchKey) {
        int index = (int) searchKey;
        return storage.get(index);
    }

    public List<Resume> doGetStorage() {
        return storage;
    }
    public void doUpdate(Resume resume, Object searchKey) {
        int index = (int) searchKey;
        storage.set(index, resume);
    }

    public void doDelete(Object searchKey) {
        int index = (int) searchKey;
        storage.remove(index);
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    protected Object getSearchKey(String uuid) {
        int size = storage.size();
        for (int i = 0; i < size; i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isExist(Object searchKey) {
        int index = (int) searchKey;
        return index >= 0;
    }

}
