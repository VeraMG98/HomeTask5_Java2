package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloaders extends Thread {
    private int index;
    private Semaphore semaphore;
    private CountDownLatch countDownLatch;
    private CountDownLatch _countDownLatch;

    public Downloaders(int index, Semaphore semaphore,
                       CountDownLatch countDownLatch, CountDownLatch _countDownLatch) {
        this.index = index;
        this.semaphore = semaphore;
        this.countDownLatch = countDownLatch;
        this._countDownLatch = _countDownLatch;
    }

    public void run() {
        try {
            _countDownLatch.await();
            semaphore.acquire();
            System.out.println("Файл скачивается " + index);
            sleep(3000);
            System.out.println("Файл скачался " + index);
            semaphore.release();
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
