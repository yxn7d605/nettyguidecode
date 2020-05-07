package com.yx.home.netty;

import com.yx.home.netty.utils.IPUtils;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("localhost ip: " + IPUtils.localIP());
            List<String> ips = IPUtils.localIps();
            System.out.println("localhost ip list: ");
            ips.forEach(ip -> System.out.println(ip));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
