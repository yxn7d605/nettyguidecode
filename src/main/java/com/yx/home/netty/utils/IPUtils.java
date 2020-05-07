package com.yx.home.netty.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IPUtils {
    /**
     * 获取本地网卡地址列表
     *
     * @return
     */
    public static List<String> localIps() throws SocketException {
        List<String> ips = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (inetAddress.isLoopbackAddress()) {
                    continue;
                }

                String ip = inetAddress.getHostAddress();
                if (ip.indexOf(":") != -1) {
                    continue;
                }

                ips.add(ip);
            }
        }

        return ips;
    }

    public static boolean isLocalIP(String ip) throws SocketException {
        List<String> ips = localIps();

        return ips.contains(ip);
    }

    public static String localIP() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();

        return inetAddress.getHostAddress();
    }

    public static void main(String[] args) {
        try {
            boolean result = isLocalIP("192.168.34.148");
            System.out.println(result);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
