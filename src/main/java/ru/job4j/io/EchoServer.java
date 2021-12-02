package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
           while (!server.isClosed()) {
               Socket socket = server.accept();
               try (OutputStream outputStream = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                   String word = in.readLine().substring(10).split(" ")[0];
                   if ("Bye".equals(word)) {
                       server.close();
                   }
                   outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                   System.out.println("You wrote: " + word);
                   for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                       System.out.println(str);
                   }
                   outputStream.flush();
               }
           }
        }
    }
}
