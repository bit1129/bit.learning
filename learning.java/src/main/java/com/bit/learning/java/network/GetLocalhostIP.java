package com.bit.learning.java.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetLocalhostIP {
    public static void main(String[] args) throws UnknownHostException {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String hostName = InetAddress.getLocalHost().getHostName();
        String canonicalHostName = InetAddress.getLocalHost().getCanonicalHostName();

        System.out.println("hostAddress: " + hostAddress);
        System.out.println("hostName: " + hostName);
        System.out.println("canonicalHostName: " + canonicalHostName);

        /**
         * Result:
         hostAddress: 127.0.1.1
         hostName: yuzt-Aspire-TC-606
         canonicalHostName: yuzt-Aspire-TC-606
         */
    }
}
