package game.GUI;

import bl.CurrentUser;
import database.DB_Access;
import game.beans.Message;
import game.beans.Packet;
import game.bl.Connectable;
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

    Server() {
        super();
        setColor("white");
    }

    public void runGame() {
        try {
            serverSocket = new ServerSocket(2004, 10);
            JOptionPane.showMessageDialog(null, "Deine ip ist: " + InetAddress.getLocalHost().getHostAddress());

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
                } catch (Exception e) {
                    try {
                        String[] arr = (String[]) o;
                        for (String string : arr) {
                            System.out.println(string + ", ");
                        }
                        game.getModel().getBoard().addEnemyBoard(arr);
                        out.writeObject(DB_Access.getInstance().loadDeck(CurrentUser.player.getUsername()));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } while (packet == null || !packet.isExit());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
