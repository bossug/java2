package com.company;


import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    public static void main(String[] args) {
	    new ChatClient();
    }

    public ChatClient() {
        try {
            Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
            new Clients(socket, "Клиент");
            while(true){
                if(socket.isClosed()){
                    break;
                }
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
