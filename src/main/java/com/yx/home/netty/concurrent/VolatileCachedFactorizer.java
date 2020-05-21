package com.yx.home.netty.concurrent;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class VolatileCachedFactorizer {
    private OneValueCache cache = new OneValueCache(null, null);

    public void service(BigInteger i) {
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
    }

    public OneValueCache getCache() {
        return cache;
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{i};
    }

    public static void main(String[] args) {
        VolatileCachedFactorizer volatileCachedFactorizer = new VolatileCachedFactorizer();

//        while (true) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileCachedFactorizer.service(BigInteger.valueOf(1));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.setName("thread1");

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileCachedFactorizer.service(BigInteger.valueOf(2));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.setName("thread2");

            t1.start();
            t2.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(volatileCachedFactorizer.getCache().getLastNumber());

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(volatileCachedFactorizer.getCache().getLastNumber());
//        }
    }
}
