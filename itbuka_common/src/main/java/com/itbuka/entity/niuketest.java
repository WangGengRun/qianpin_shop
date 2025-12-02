package com.itbuka.entity;

class AlternatePrint {
    private static int num = 0;
    private static boolean flag = true;
    private static final Object lock = new Object();
    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (num <= 200) {
                synchronized (lock) {
                    if (!flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (num <= 200) {
                            System.out.println("线程A：" + num++);
                        }
                        flag = false;
                        lock.notify();
                    }
                }
            }
        });
        Thread threadB = new Thread(() -> {
            while (num <= 200) {
                synchronized (lock) {
                    if (flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (num <= 200) {
                            System.out.println("线程B：" + num++);
                        }
                        flag = true;
                        lock.notify();
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
