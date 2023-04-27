package com.urise.webapp.storage;


import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory cannot be null");
        checkDirectory(directory);
        this.directory = directory;

    }

    protected void doDelete(File file) {
        file.delete();
    }

    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected List<Resume> doGetStorage() {
        List<Resume> res = new ArrayList<>();
        for (File file : Objects.requireNonNull(this.directory.listFiles())) {
            res.add(doGet(file));
        }
        return res;
    }

    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected boolean isExist(File file) {
        return file.exists();
    }

    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    public void clear() {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            file.delete();
        }
    }

    public int size() {
        return Objects.requireNonNull(directory.list()).length;
    }

    private void checkDirectory(File directory) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not a readable/writable");
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}