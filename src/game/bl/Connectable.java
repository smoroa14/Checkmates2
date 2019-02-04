package game.bl;

import game.GUI.Game;
import game.beans.Message;
import game.beans.Packet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Connectable {

    protected Game game;
    protected String color;
    protected ObjectOutputStream out;
    protected ObjectInputStream in;

    public Connectable() {
        this.setGame(new Game(this));
    }

    public void sendPacket(Packet packet) {
        try {
            if (out != null) {
                out.writeObject(packet);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message m) {
        try {
            if (out != null) {
                out.writeObject(m);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void handlePacket(Packet packet) {
        game.handleReceivedPacket(packet);
    }
    
    public void handleMessage(Message m){
        game.handleMessage(m);
    }
}
