package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (size >= STORAGE_LIMIT) {
            System.out.println("Для резюме: " + resume.getUuid() + " нет места!!!");
        } else {
            index = -index - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (isExist(index)) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size] = null;
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
    }
}
