package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> storage = new ArrayList<>();

    protected void doSave(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    public Resume doGet(Integer searchKey) {
        return storage.get((int) searchKey);
    }

    public List<Resume> doGetStorage() {
        return storage;
    }
    public void doUpdate(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    public void doDelete(Integer searchKey) {
        storage.remove((int) searchKey);
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    protected Integer getSearchKey(String uuid) {
        int size = storage.size();
        for (int i = 0; i < size; i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

}
