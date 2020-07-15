package lessons7.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String nick;


    public ClientHandler(Server server, Socket socket) throws IOException{
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String str = in.readUTF();
                            if(str.startsWith("/auth")){
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNikByLoginAndPass(tokens[1], tokens[2]);
                                System.out.println(newNick);
                                if(newNick != null){
                                    if(!server.isNickAuth(newNick)) {
                                        sendMsg("/authok");
                                        nick = newNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    } else {
                                        sendMsg("Такой ник уже используется");
                                    }
                                } else {
                                    sendMsg("Неверный логин или пароль");
                                }
                            }
                        }

                        while (true){
                            String str = in.readUTF();
                            if(str.startsWith("/")) {
                                if (str.equals("/end")) {
                                    out.writeUTF("/serverClosed");
                                    break;
                                }
                                if (str.startsWith("/w")) {
                                    String[] tokens = str.split(" ", 3);
                                    server.sendPersonalMsg(ClientHandler.this, tokens[1], tokens[2]);
                                }
                            } else {
                                server.broadcastMsg(nick + ": " + str);
                            }
                        }
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(String str) {
        try {
            out.writeUTF(str);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public String getNick() {
        return nick;
    }
}
