package com.bit.learning.java.asm;

import java.util.Map;
import java.util.Set;

public class UDFByteCodeVerifierTest {
    public static final UDFByteCodeVerifier udfByteCodeVerifier = new UDFByteCodeVerifier();

    static {
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/Class", "forName");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/Class", "getClassLoader");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/Class", "getResource");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/Class", "getResourceAsStream");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "clearAssertionStatus");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "getResource");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "getResourceAsStream");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "getResources");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "getSystemClassLoader");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "getSystemResource");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "getSystemResourceAsStream");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "getSystemResources");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "loadClass");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "setClassAssertionStatus");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "setDefaultAssertionStatus");
        udfByteCodeVerifier.addDisallowedMethodCall("java/lang/ClassLoader", "setPackageAssertionStatus");
        udfByteCodeVerifier.addDisallowedMethodCall("java/nio/ByteBuffer", "allocateDirect");



        for (String ia : new String[]{"java/net/InetAddress", "java/net/Inet4Address", "java/net/Inet6Address"}) {
            // static method, probably performing DNS lookups (despite SecurityManager)
            udfByteCodeVerifier.addDisallowedMethodCall(ia, "getByAddress");
            udfByteCodeVerifier.addDisallowedMethodCall(ia, "getAllByName");
            udfByteCodeVerifier.addDisallowedMethodCall(ia, "getByName");
            udfByteCodeVerifier.addDisallowedMethodCall(ia, "getLocalHost");
            // instance methods, probably performing DNS lookups (despite SecurityManager)
            udfByteCodeVerifier.addDisallowedMethodCall(ia, "getHostName");
            udfByteCodeVerifier.addDisallowedMethodCall(ia, "getCanonicalHostName");
            // ICMP PING
            udfByteCodeVerifier.addDisallowedMethodCall(ia, "isReachable");
        }
        udfByteCodeVerifier.addDisallowedClass("java/net/NetworkInterface");
        udfByteCodeVerifier.addDisallowedClass("java/net/SocketException");
        udfByteCodeVerifier.addDisallowedClass("java/io/File");
        udfByteCodeVerifier.addDisallowedClass("java/lang/Thread");
    }

    public static void main(String[] args) throws Exception {
        Map<String, byte[]> className2ClassBytes = JarAnalysis.className2Bytes(null);
        Set<Map.Entry<String, byte[]>> set = className2ClassBytes.entrySet();
        for (Map.Entry<String, byte[]> entry : set) {
            String className = entry.getKey();
            byte[] value = entry.getValue();
            Set<String> result = udfByteCodeVerifier.verify(value);
            System.out.println(result);
        }
    }
}
