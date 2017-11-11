package com.step.jliang.telecommunication.bio;

import com.step.jliang.telecommunication.handler.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8777;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println(serverSocket.getInetAddress() + " : " + serverSocket.getLocalPort());
            Socket socket = serverSocket.accept();
            new Thread(new SocketHandler(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // todo 后续流的关闭操作
        }
    }

}
