package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new HashMap<>();

    protected void doSave(Resume resume, String searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected Resume doGet(String searchKey) {
        return storage.get(searchKey);
    }

    public List<Resume> doGetStorage() {
        return new ArrayList<>(storage.values());
    }

    protected void doUpdate(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    protected void doDelete(String searchKey) {
        storage.remove(searchKey);
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    protected String getSearchKey(String key) {
        return key;
    }
}