package com.urise.webapp.storage;

import com.urise.webapp.exeption.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void saveStorageOverflow() {
        final int STORAGE_SIZE = AbstractArrayStorage.STORAGE_LIMIT;
        try {
            storage.clear();
            for (int i = 0; i < STORAGE_SIZE; i++) {
                storage.save(new Resume(PERSON_6));
            }
        } catch (StorageException e) {
            fail(e.getMessage());
        }

        Assertions.assertThrows(StorageException.class, () -> storage.save(RESUME_4));
    }
}