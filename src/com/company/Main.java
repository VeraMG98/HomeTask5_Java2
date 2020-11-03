package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Semaphore semaphore = new Semaphore(3, true);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        CountDownLatch _countDownLatch = new CountDownLatch(1);
        Uploader uploader = new Uploader(_countDownLatch);
        uploader.start();

        for (int i = 1; i <= 10; i++) {
            new Downloaders(i, semaphore, countDownLatch, _countDownLatch).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Файл удален");
    }
}
