package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamStorageSerializer streamStorageSerializer;

    protected PathStorage(String dir, StreamStorageSerializer streamStorageSerializer) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory cannot be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory + "is not directory or is not writable");
        }
        this.streamStorageSerializer = streamStorageSerializer;
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path not deleted", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return streamStorageSerializer.doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> doGetStorage() {
        List<Resume> res = new ArrayList<>();
        for (Path Path : getStorageList().toList()) {
            res.add(doGet(Path));
        }
        return res;
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            streamStorageSerializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            streamStorageSerializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    public void clear() {
        getStorageList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getStorageList().count();
    }

    private Stream<Path> getStorageList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }
}