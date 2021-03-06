package game.GUI;

import game.bl.Connectable;
import game.bl.Controller;
import game.bl.*;
import game.beans.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    private Model model;
    private View view;
    private Controller controller;
    private Connectable connectable;

    public Game() {
        model = new Model("white");
        view = new View(model.getBoard(), this, "null");
        model.getBoard().changeColor();
        setController(new Controller(model, view, this));
    }

    public Game(Connectable connectable) {
        model = new Model(Server.class == connectable.getClass() ? "white" : "black");
        setConnectable(connectable);
        if (connectable.getClass() == Server.class) {
            view = new View(model.getBoard(), this, "white");
        }
        if (connectable.getClass() == Client.class) {
            view = new View(model.getBoard(), this, "black");
        }
        setController(new Controller(model, view, this));

    }

    public void sendPacket(Command command, boolean restart, boolean restartConfirm, boolean undo, boolean exit) {
        connectable.sendPacket(new Packet(command, restart, restartConfirm, undo, exit));
    }

    public void sendMessage(Packet p) {
        connectable.sendPacket(p);
        handleMessage(p.getMessage());
    }

    public void handleReceivedPacket(Packet packet) {
        if (packet.getCommand() != null) {
            controller.move(packet.getCommand().getVon(), packet.getCommand().getNach());
        }

        if (packet.isRestart()) {
            if (packet.isRestartConfirm()) {
                controller.resetBoard();
                return;
            }
            boolean restarted = view.promptRestart();
            if (restarted) {
                sendPacket(null, true, true, false, false);
                controller.resetBoard();
            }
        }
        if (packet.isUndo()) {
            controller.deselect(controller.getSelectedSquare());
            model.undo();
            controller.switchTurn();
        }
        if (packet.isExit()) {
            view.opponentQuit();
            view.close();
        }
        if (packet.getMessage() != null) {
            handleMessage(packet.getMessage());
        }
    }

    public void handleMessage(Message m) {
        view.addToChat(m.getSender() + ":  " + m.getText());
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Connectable getConnectable() {
        return connectable;
    }

    public void setConnectable(Connectable connectable) {
        this.connectable = connectable;
    }

    public void close() {
        try {
            if (connectable instanceof Server) {
                ((Server) connectable).close();
            } else {
                ((Client) connectable).close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
