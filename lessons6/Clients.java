package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Clients {
    private Scanner in;
    private Scanner input;
    private PrintWriter out;
    public Clients(Socket socket, String name) {
        try {
            in = new Scanner(socket.getInputStream());
            input = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (input.hasNext()) {
                        String q = input.nextLine();
                        sendMsg(name+": "+q);
                        if (q.equalsIgnoreCase("/close")) break;
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
         new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (in.hasNext()) {
                        String w = in.nextLine();
                        System.out.println(w);
                        if (w.contains("close")) break;
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void sendMsg(String msg) {
        out.println(msg);
        out.flush();
    }
}
