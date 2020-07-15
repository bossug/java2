package lessons7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;
    public Server(){
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидание подключений");
            clients = new Vector<>();

            while (true){
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            try {
                server.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void subscribe(ClientHandler client){
        clients.add(client);
    }
    public void unsubscribe(ClientHandler client){
        clients.remove(client);
    }
    public void broadcastMsg(String msg){
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }
    public void sendPersonalMsg(ClientHandler clientFrom, String ClientTo, String text){
        for (ClientHandler o: clients) {
            if(o.getNick().equals(ClientTo)){
                o.sendMsg(text);
            }
        }
    }
    public boolean isNickAuth(String nick){
        for (ClientHandler o: clients) {
            if(o.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }
}
