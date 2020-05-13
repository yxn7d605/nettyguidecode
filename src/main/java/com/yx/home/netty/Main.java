package com.yx.home.netty;

import com.yx.home.netty.ch2_4.ConcurrencyTest;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConcurrencyTest.count = 100000000L;
        ConcurrencyTest.concurrency();
        ConcurrencyTest.serial();
    }
}
