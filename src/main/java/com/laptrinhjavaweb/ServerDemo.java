package com.laptrinhjavaweb;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Nguyen Anh Tuan
 * @created: 2/16/2023
 */

public class ServerDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        while (true) {
            Socket socket = server.accept();
            System.out.println("connected");
            InputStream inputStream = socket.getInputStream();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println(inFromClient.readLine());
            DataOutputStream outToClient =
                    new DataOutputStream(socket.getOutputStream());
            outToClient.write(19);
            socket.close();
            server.close();
        }
    }

}
