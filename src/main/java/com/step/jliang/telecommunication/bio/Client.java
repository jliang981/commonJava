package com.step.jliang.telecommunication.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 8777;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(ADDRESS, PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("hello server");
            System.out.println(in.readLine());
            out.println("transfer some data");
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
