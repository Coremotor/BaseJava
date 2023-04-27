package com.urise.webapp;

import java.io.File;
import java.util.Objects;

public class MainFiles {
        public static void main(String[] args) {
        String path = "./src/";
        File mainDir = new File(path);
        printNameFiles(mainDir);
    }

    private static void printNameFiles(File file) {
        for (File item : Objects.requireNonNull(file.listFiles())) {
            if (item.isDirectory()) {
                System.out.println(item.getName());
                printNameFiles(item);
            } else {
                System.out.println(item.getName());
            }
        }
    }
}
