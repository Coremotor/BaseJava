package com.urise.webapp;


public class MainConcurrency {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        MyThread myThread1 = new MyThread(lock1, lock2, 200);
        MyThread myThread2 = new MyThread(lock2, lock1, 0);

        myThread1.start();
        myThread2.start();
    }
}

class MyThread extends Thread {
    private final Object lock1;
    private final Object lock2;
    private final int sleepTime;

    public MyThread(Object lock1, Object lock2, int sleepTime) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start");
        synchronized (lock1) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2) {
            }
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }
}
