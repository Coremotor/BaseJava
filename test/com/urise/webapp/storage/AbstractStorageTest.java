package com.urise.webapp.storage;

import com.urise.webapp.exeption.ExistStorageException;
import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.ResumeTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";
    protected static final String PERSON_1 = "Dog Bobby";
    protected static final String PERSON_2 = "Cat Tom";
    protected static final String PERSON_3 = "Fish Gold";
    protected static final String PERSON_4 = "Spider Man";
    protected static final String PERSON_5 = "Sony PS";
    protected static final String PERSON_6 = "Mac Book";
    protected static final String UUID_NOT_EXIST = "dummy";
    protected static final String UPDATE_UUID = "update";
    protected final Resume RESUME_1 = ResumeTestData.getResume(UUID_1, PERSON_1);
    protected final Resume RESUME_2 = ResumeTestData.getResume(UUID_2, PERSON_2);
    protected final Resume RESUME_3 = ResumeTestData.getResume(UUID_3, PERSON_3);
    protected final Resume RESUME_4 = ResumeTestData.getResume(UUID_4, PERSON_4);
    protected final Resume UPDATE_RESUME = ResumeTestData.getResume(UPDATE_UUID, PERSON_5);
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
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
    void clear() {
        storage.clear();
        assertSize(0);
        Assertions.assertArrayEquals(storage.getAllSorted().toArray(), new Resume[0]);
    }

    @Test
    void update() {
        storage.save(UPDATE_RESUME);
        storage.update(UPDATE_RESUME);
        Assertions.assertSame(UPDATE_RESUME, storage.get(UPDATE_UUID));
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void getNotExists() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    @Test
    void getAll() {
        List<Resume> expected = new ArrayList<>(Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
        expected.sort(ArrayStorage.COMPARATOR);
        Assertions.assertArrayEquals(expected.toArray(), storage.getAllSorted().toArray());
    }

    @Test
    void save() {
        final int size = storage.size();
        storage.save(RESUME_4);
        Assertions.assertEquals(RESUME_4, storage.get(UUID_4));
        assertSize(size + 1);
        assertGet(RESUME_4);
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1, PERSON_1)));
    }

    @Test
    void delete() {
        final int size = storage.size();
        storage.delete(UUID_1);
        assertSize(size - 1);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void size() {
        assertSize(3);
    }

    void assertSize(final int size) {
        Assertions.assertEquals(size, storage.size());
    }

    void assertGet(final Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }
}