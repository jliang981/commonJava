package com.step.jliang.telecommunication.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8765);
        ByteBuffer writeBuff = ByteBuffer.allocate(64);
        byte[] bytes = new byte[64];
        try {
            SocketChannel sc = SocketChannel.open();
            sc.connect(address);
            while (true) {
                System.in.read(bytes);
                if (bytes.length == 0) {
                    return;
                }
                writeBuff.clear();
                writeBuff.put(bytes);
                writeBuff.flip();
                sc.write(writeBuff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("shut down client.");
        }

    }

}
