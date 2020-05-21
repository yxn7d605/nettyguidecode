package com.yx.home.netty.concurrent;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors) {
        lastNumber = i;
        if (factors == null || factors.length == 0) {
            lastFactors = null;
        } else {
            lastFactors = Arrays.copyOf(factors, factors.length);
        }
    }

    public BigInteger getLastNumber() {
        return lastNumber;
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null) {
            System.out.println(Thread.currentThread().getName() + " i=" + i + " lastNumber=null");
        } else {
            System.out.println(Thread.currentThread().getName() + "i=" + i + " lastNumber=" + lastNumber + " lastFactors[0]=" + lastFactors[0]);
        }
        if (Thread.currentThread().getName().equalsIgnoreCase("thread1")) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " lastNumber=" + lastNumber);
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
