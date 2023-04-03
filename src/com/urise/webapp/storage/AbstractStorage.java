package com.urise.webapp.storage;

import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        checkExistResume(resume);
        saveResume(resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return getResume(uuid);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        setResume(resume, index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(uuid);
    }

    @Override
    public void clear() {
        deleteAllResume();
    }

    @Override
    public int size() {
        return getSizeStorage();
    }


    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume resume);

    protected abstract Resume getResume(String uuid);

    protected abstract Resume[] getAllResume();

    protected abstract void setResume(Resume r, int index);

    protected abstract void deleteResume(String uuid);

    protected abstract void deleteAllResume();

    protected abstract int getSizeStorage();

    protected abstract void checkExistResume(Resume resume);
}


