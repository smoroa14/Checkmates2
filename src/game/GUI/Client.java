package game.GUI;

import bl.CurrentUser;
import database.DB_Access;
import game.beans.Message;
import game.beans.Packet;
import game.bl.Connectable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import pojos.Player;

public class Client extends Connectable {

    private Packet packet;
    private Socket socket;

    Client() {
        super();
        setColor("black");
    }

    public void runGame(String ip) {
        try {
            socket = new Socket(ip, 2004);
            System.out.println("Connected to " + ip + " in port 2004");
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(DB_Access.getInstance().loadDeck(CurrentUser.player.getUsername()));
            
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            do {
                try {
                    packet = (Packet) in.readObject();
                    game.handleReceivedPacket(packet);
                } catch (Exception e) {
                    try {
                         String[] deckarr = (String[]) in.readObject();
                         game.getModel().getBoard().addEnemyBoard(deckarr);
                    } catch (Exception ex) {
                    }
                }
            } while (!packet.isExit());
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
        socket.close();
    }

    public static void main(String args[]) {
        Client client = new Client();
        client.runGame("localhost");
    }
}
