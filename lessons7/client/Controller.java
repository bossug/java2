package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javafx.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


@SuppressWarnings("deprecation")
public class Controller {
    @FXML
    TextArea textArea;
    @FXML
    TextField textField;

    @FXML
    Button btn1;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;

    @FXML
    HBox upperPanel;
    @FXML
    HBox hbox;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;

    private boolean isAuthorized;
    public void setAuthorized(boolean isAuthorized){
        this.isAuthorized = isAuthorized;
        if(!isAuthorized){
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            hbox.setVisible(false);
            hbox.setManaged(false);
        } else {
            hbox.setVisible(true);
            hbox.setManaged(true);
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);

        }
    }
    public void connect(){
        try {
            socket = new Socket(IP_ADDRESS,PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String str = in.readUTF();
                            if(str.startsWith("/authok")){
                                setAuthorized(true);
                                break;
                            } else {
                                textArea.appendText(str + "\n");
                            }
                        }

                        while (true){
                            String str = in.readUTF();
                            if(str.equalsIgnoreCase("/serverClosed")){
                                break;
                            }
                            textArea.appendText(str + "\n");
                        }
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            socket.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(){
        try {
            out.writeUTF(textField.getText() + "\n");
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void tryToAuth(ActionEvent actionEvent) {
        if(socket == null || socket.isClosed()){
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
