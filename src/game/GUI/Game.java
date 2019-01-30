package game.GUI;

import game.bl.Connectable;
import game.bl.Controller;
import game.bl.*;
import game.beans.*;

public class Game {

    private Model model;
    private View view;
    private Controller controller;
    private Connectable connectable;

    public Game() {
        model = new Model();
        view = new View(model.getBoard(), this, "null");
        setController(new Controller(model, view, this));
    }

    public Game(Connectable connectable) {
        model = new Model();
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

}
