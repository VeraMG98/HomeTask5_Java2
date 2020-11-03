package com.company;

import java.util.concurrent.CountDownLatch;

public class Uploader extends Thread {
    private final CountDownLatch countDownLatch;

    public Uploader(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                System.out.print("🟥");
                sleep(60);
            }
            System.out.println("\n Файл загружен");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
