package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void save(Resume resume) {
        if (size >= 10000) {
            System.out.println("Для резюме: " + resume.getUuid() + " нет места!!!");
        } else if (resumeIsExist(resume)) {
            System.out.println("Резюме: " + resume.getUuid() + " уже создано!!!");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        if (!resumeIsExist(resume)) {
            System.out.println("Резюме " + resume.getUuid() + " не найдено");
        } else {
            resume.setUuid(resume.getUuid() + " updated");
            storage[getIndex(resume.getUuid())] = resume;
        }
    }

    public Resume get(String uuid) {
        if (resumeIsExistByUuid(uuid)) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(storage[i].getUuid(), uuid)) {
                    return storage[i];
                }
            }
        }
        System.out.println("Резюме " + uuid + " не найдено");
        return null;
    }

    public void delete(String uuid) {
        if (resumeIsExistByUuid(uuid)) {
            int index = getIndex(uuid);
            int lastResumeIndex = size - 1;
            storage[index] = storage[lastResumeIndex];
            storage[lastResumeIndex] = null;
            size--;
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i].getUuid(), uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean resumeIsExist(Resume resume) {
        return getIndex(resume.getUuid()) != -1;
    }

    private boolean resumeIsExistByUuid(String uuid) {
        return getIndex(uuid) != -1;
    }
}
