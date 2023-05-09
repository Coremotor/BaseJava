package com.urise.webapp.storage.storageSerializer;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements Serializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream osOut = new ObjectOutputStream(os)) {
            osOut.writeObject(r);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream isOut = new ObjectInputStream(is)) {
            return (Resume) isOut.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}