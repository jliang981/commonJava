package com.step.jliang.telecommunication.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server implements Runnable {

    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(128);

    public Server(int port) {
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            // 这个方法是有返回值的，返回值是selectionkey
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server start. port: " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 阻塞方法，直到注册的通道中有一个可用
                selector.select();
                // 这里selectedKeys，我理解的是，只要之前返回过可用。那么就一直呆在这个集合里
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accept(SelectionKey key) {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        try {
            // 如果channel是非阻塞模式，那么这个方法会直接返回。如果有等待的客户端连接，直接返回客户端连接，如果没有返回null。
            SocketChannel sc = channel.accept();
            if (sc == null) {
                return;
            }
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        try {
            SocketChannel sc = (SocketChannel) key.channel();
            readBuffer.clear();
            int count = sc.read(readBuffer);
            if (count <= -1) {
                key.channel().close();
                key.cancel();
                return;
            }
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            String string = new String(bytes).trim();

            System.out.println("Server receive: " + string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server(8765)).start();
    }

}
