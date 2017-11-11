package com.step.jliang.telecommunication.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketHandler implements Runnable {

    private Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String s = in.readLine();
                if (s == null) {
                    break;
                }
                System.out.println("Server:" + s);
                out.println("from Server:" + s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // todo 后续流的关闭操作
        }
    }
}
