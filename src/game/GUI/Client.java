package game.GUI;

import bl.CurrentUser;
import database.DB_Access;
import game.beans.JoiningParameter;
import game.beans.Message;
import game.beans.Packet;
import game.bl.Connectable;
import game.bl.Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
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
            String pw = JOptionPane.showInputDialog("Password: ");
            String[] deck = DB_Access.getInstance().loadDeck(CurrentUser.player.getUsername());
            out.writeObject(new JoiningParameter(pw, deck));
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            do {
                Object o = in.readObject();
                try {
                    packet = (Packet) o;
                    handlePacket(packet);
                } catch (Exception e) {
                    try {
                        String[] arr = (String[]) o;
                        System.out.println("Client: von server");
                        for (String string : arr) {
                            System.out.print(string + ", ");
                        }
                        game.getModel().getBoard().addEnemyBoard(arr);
                        game.getModel().addActionListeners(Controller.instance);

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
        socket.close();
    }

    public static void main(String args[]) {
        Client client = new Client();
        client.runGame("localhost");
    }
}
