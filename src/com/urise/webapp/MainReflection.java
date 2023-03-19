package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        System.out.println(resume.getClass().getMethod("toString").invoke(resume));
    }
}
