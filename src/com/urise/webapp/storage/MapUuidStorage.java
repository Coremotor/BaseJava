package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected Resume doGet(Object searchKey) {
        String key = (String) searchKey;
        return storage.get(key);
    }

    public List<Resume> doGetStorage() {
        return new ArrayList<>(storage.values());
    }

    protected void doUpdate(Resume resume, Object searchKey) {
        String key = (String) searchKey;
        storage.put(key, resume);
    }

    protected void doDelete(Object searchKey) {
        String key = (String) searchKey;
        storage.remove(key);
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    protected Object getSearchKey(String key) {
        return key;
    }
}