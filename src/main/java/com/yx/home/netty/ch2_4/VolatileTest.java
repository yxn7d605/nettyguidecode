package com.yx.home.netty.ch2_4;

import java.util.concurrent.TimeUnit;

/**
 * 如果flag不加volatile修饰，则flag的值变更之后，其他线程什么时候能够获取到变化后的值是不确定的
 * 但是如果flag被volatile修饰后，则flag的值一旦变化就会被其他线程获取到
 */
public class VolatileTest {
//    private static boolean flag = false;
    private volatile static boolean flag = false;

    public static void main(String[] args) {
        new Thread() {
            int i = 0;

            @Override
            public void run() {
                long tm = System.currentTimeMillis();
                while (!flag) {
                    i++;
                }
                System.out.println(System.currentTimeMillis() - tm);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = true;
                System.out.println("flag=" + flag);
            }
        }.start();
    }
}
