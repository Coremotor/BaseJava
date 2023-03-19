package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }


    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
        Assertions.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    public void get() {
        Assertions.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    void getNotExists() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void save() {
        final int size = storage.size();
        final String uuid = "testUUID";
        storage.save(new Resume(uuid));
        Assertions.assertEquals(new Resume(uuid), storage.get(uuid));
        Assertions.assertEquals(size + 1, storage.size());
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    public void delete() {
        final int size = storage.size();
        storage.delete(UUID_1);
        Assertions.assertEquals(size - 1, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assertions.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void saveStorageOverflow() {
        final int STORAGE_SIZE = 10000;
        try {
            storage.clear();
            for (int i = 0; i < STORAGE_SIZE; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail(e.getMessage());
        }

        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }
}