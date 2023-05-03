package com.urise.webapp;

import java.io.File;
import java.util.Objects;

public class MainFiles {

    private static final StringBuilder string = new StringBuilder();
    private static final String addString = "- ";

    public static void main(String[] args) {
        String path = "./src/";
        File mainDir = new File(path);
        printNameFiles(mainDir);
    }

    private static void printNameFiles(File file) {
        for (File item : Objects.requireNonNull(file.listFiles())) {
            if (item.isDirectory()) {
                System.out.println(string + item.getName());
                string.append(addString);
                printNameFiles(item);
            } else {
                System.out.println(string + item.getName());
            }
        }

        int stringLength = string.length();
        int addStringLength = addString.length();
        if (stringLength > 0) {
            string.delete(stringLength - addStringLength, stringLength);
        }
    }
}
