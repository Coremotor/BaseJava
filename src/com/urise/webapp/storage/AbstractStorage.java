package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<T> implements Storage {

    static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void save(Resume resume) {
        LOG.info("Save resume: " + resume);
        T searchKey = getNotExistSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get resume, uuid: " + uuid);
        T searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("Get all sorted resumes");
        List<Resume> list = doGetStorage();
        list.sort(COMPARATOR);
        return list;
    }

    public void update(Resume resume) {
        LOG.info("Update resume: " + resume);
        T searchKey = getExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete resume, uuid: " + uuid);
        T searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    private T getExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        }
        LOG.warning("Resume uuid: " + uuid + " not exist");
        throw new NotExistStorageException(uuid);
    }

    private T getNotExistSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            return searchKey;
        }
        LOG.warning("Resume uuid: " + uuid + " is exists");
        throw new ExistStorageException(uuid);
    }

    protected abstract void doSave(Resume resume, T searchKey);

    protected abstract Resume doGet(T searchKey);

    protected abstract List<Resume> doGetStorage();

    protected abstract void doUpdate(Resume r, T searchKey);

    protected abstract void doDelete(T searchKey);

    protected abstract boolean isExist(T searchKey);

    protected abstract T getSearchKey(String uuid);
}
