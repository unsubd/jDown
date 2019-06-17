package com.personal.projects.jdown.services;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloaderTest {

    @Test
    public void merge() throws IOException {

        SeekableByteChannel part0 = Files.newByteChannel(Paths.get("D:/Workspace/IntelliJ/jdown/part0"));
        SeekableByteChannel part1 = Files.newByteChannel(Paths.get("D:/Workspace/IntelliJ/jdown/part1"));
        Charset charset = Charset.forName("US-ASCII");
//        SeekableByteChannel output = Files.newByteChannel(Paths.get("D:/Workspace/IntelliJ/jdown/final"),
//                StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        ByteBuffer buffer = ByteBuffer.allocate(100);
        while (part0.read(buffer) > 0) {
            buffer.flip();
            System.out.print(charset.decode(buffer));
            buffer.clear();
        }

        part0.close();
        buffer = buffer.clear();

        System.out.println();

        while (part1.read(buffer) > 0) {
            buffer.rewind();
            System.out.print(charset.decode(buffer));
            buffer.clear();
        }

        buffer.clear();
        part1.close();

    }

}