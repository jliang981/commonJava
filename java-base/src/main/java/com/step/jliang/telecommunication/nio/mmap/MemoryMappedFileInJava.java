package com.step.jliang.telecommunication.nio.mmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


public class MemoryMappedFileInJava {


    private static int count = 1024; // 10 MB


    public static void main(String[] args) throws Exception {

        URL resource = MemoryMappedFileInJava.class.getClassLoader().getResource("a.txt");

        RandomAccessFile randomAccessFile = new RandomAccessFile(resource.getFile(), "rw");

        // Mapping a file into memory
        MappedByteBuffer out = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, count);


        // Writing into Memory Mapped File
        for (int i = 0; i < count; i++) {
            out.put((byte) 'A');
        }
        System.out.println("Writing to Memory Mapped File is completed");

        Thread.sleep(5000);
        randomAccessFile.close();
    }


}