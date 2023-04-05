package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapStorage;

public class MainTestArrayStorage {
    static final MapStorage ARRAY_STORAGE = new MapStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");

        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        printAll();

        System.out.println("\nGet r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        try {
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (Exception ignored) {
            System.out.println("Резюме не найдено.");
        }

        System.out.println("Update r1");
        ARRAY_STORAGE.update(r1);
        printAll();
        System.out.println("\nDelete " + r1.getUuid());
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        System.out.println("\nClear");
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
