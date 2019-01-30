package game.GUI;

import game.beans.Packet;
import game.bl.Connectable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
                try {
                    packet = (Packet) in.readObject();
                    handlePacket(packet);
                } catch (ClassNotFoundException e) {
                }
            } while (!packet.isExit());
        } catch (IOException e) {
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
