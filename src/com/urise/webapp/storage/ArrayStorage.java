package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Objects;

public class ArrayStorage extends AbstractArrayStorage {
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i].getUuid(), uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Для резюме: " + resume.getUuid() + " нет места!!!");
        } else if (isExist(getIndex(resume.getUuid()))) {
            System.out.println("Резюме: " + resume.getUuid() + " уже создано!!!");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
    }
}
