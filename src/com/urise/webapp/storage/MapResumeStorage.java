package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected Resume doGet(Object searchKey) {
        Resume resume = (Resume) searchKey;
        return storage.get(resume.getUuid());
    }

    protected List<Resume> doGetStorage() {
        return new ArrayList<>(storage.values());
    }

    protected void doUpdate(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected void doDelete(Object searchKey) {
        Resume resume = (Resume) searchKey;
        storage.remove(resume.getUuid());
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
