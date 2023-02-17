import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int count = 0;

    void clear() {
        Arrays.fill(storage, 0, count, null);
    }

    void save(Resume resume) {
        if (getIndex(resume.uuid) != -1) {
            System.out.println("Резюме: " + resume.uuid + " уже создано!!!");
        } else {
            storage[count] = resume;
            count++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < count; i++) {
            if (Objects.equals(storage[i].uuid, uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        int lastResumeIndex = count - 1;
        storage[index] = storage[lastResumeIndex];
        storage[lastResumeIndex] = null;
        count--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    int size() {
        return count;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (Objects.equals(storage[i].uuid, uuid)) {
                return i;
            }
        }
        return -1;
    }
}
