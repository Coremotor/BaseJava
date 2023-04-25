package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> storage = new HashMap<>();

    protected void doSave(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected Resume doGet(Resume searchKey) {
        return searchKey;
    }

    protected List<Resume> doGetStorage() {
        return new ArrayList<>(storage.values());
    }

    protected void doUpdate(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    protected void doDelete(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
