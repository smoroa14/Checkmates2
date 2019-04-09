package game.GUI;

import bl.CurrentUser;
import database.DB_Access;
import game.beans.JoiningParameter;
import game.beans.Message;
import game.beans.Packet;
import game.bl.Connectable;
import game.bl.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Server extends Connectable {

    ServerSocket serverSocket;
    Socket connection = null;
    Packet packet;
    private boolean restart = false;

    Server() {
        super();
        setColor("white");
    }

    public void runGame() {
        try {
            serverSocket = new ServerSocket(2004, 10);
            JOptionPane.showMessageDialog(null, "Deine ip ist: " + InetAddress.getLocalHost().getHostAddress());

            do {
                System.out.println("Waiting for connection");
                connection = serverSocket.accept();
                System.out.println("Connected from " + connection.getInetAddress().getHostName());
                out = new ObjectOutputStream(connection.getOutputStream());
                out.flush();
                in = new ObjectInputStream(connection.getInputStream());
                do {
                    Object o = in.readObject();
                    try {
                        packet = (Packet) o;
                        handlePacket(packet);
                    } catch (ClassCastException e) {
                        try {
                            JoiningParameter params = (JoiningParameter) o;
                            if (!params.getPassword().equals(CurrentUser.password)) {
                                System.out.println("Password false -> " + params.getPassword() + " != " + CurrentUser.password);
                                out.writeObject(new Packet(null, false, false, false, true));
                                restart = true;
                            } else {
                                System.out.println("Game starting!");
                                String[] arr = params.getDeck();
                                System.out.println("Server: von client");
                                for (String string : arr) {
                                    System.out.print(string + ", ");
                                }
                                game.getModel().getBoard().addEnemyBoard(arr);
                                game.getModel().addActionListeners(Controller.instance);
                                out.writeObject(DB_Access.getInstance().loadDeck(CurrentUser.player.getUsername()));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            
                        }
                    }
                } while (!restart && (packet == null || !packet.isExit()));
            } while (restart && !CurrentUser.gamestarted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        in.close();
        out.close();
        serverSocket.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.runGame();
    }

}
