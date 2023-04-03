package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UPDATE_UUID = "update";
    private static final String UUID_NOT_EXIST = "dummy";
    private final Resume RESUME_1 = new Resume(UUID_1);
    private final Resume RESUME_2 = new Resume(UUID_2);
    private final Resume RESUME_3 = new Resume(UUID_3);
    private final Resume RESUME_4 = new Resume(UUID_4);
    private final Resume UPDATE_RESUME = new Resume(UPDATE_UUID);
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }


    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assertions.assertArrayEquals(storage.getAll(), new Resume[0]);
    }

    @Test
    public void update() {
        storage.save(UPDATE_RESUME);
        storage.update(UPDATE_RESUME);
        Assertions.assertSame(UPDATE_RESUME, storage.get(UPDATE_UUID));
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(new Resume(UUID_NOT_EXIST)));
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    @Test
    public void save() {
        final int size = storage.size();
        storage.save(RESUME_4);
        Assertions.assertEquals(RESUME_4, storage.get(UUID_4));
        assertSize(size + 1);
        assertGet(RESUME_4);
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    public void delete() {
        final int size = storage.size();
        storage.delete(UUID_1);
        assertSize(size - 1);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assertions.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void size() {
        assertSize(3);
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

    void assertGet(final Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    void assertSize(final int size) {
        Assertions.assertEquals(size, storage.size());
    }

}