package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
           while (!server.isClosed()) {
               Socket socket = server.accept();
               try (OutputStream outputStream = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                   String word = in.readLine().substring(10).split(" ")[0];
                   if ("Exit".equals(word)) {
                       outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                       server.close();
                   }
                   if ("Hello".equals(word)) {
                       outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                       outputStream.write("Hello".getBytes());
                   }
                   if (!"Hello".equals(word) && !"Exit".equals(word)) {
                       outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                       outputStream.write("What".getBytes());
                   }
                   outputStream.flush();
               }
           }
        } catch (IOException e) {
            LOG.error("Exception ", e);
        }
    }
}
