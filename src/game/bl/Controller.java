package game.bl;

import game.GUI.Game;
import game.GUI.View;
import game.beans.Command;
import game.bl.Model;
import game.bl.Square;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller implements ActionListener {

    public static Controller instance;
    
    private Model model;
    private View view;
    private Square selectedSquare;
    private String turn;
    private Game game;

    public Controller(Model model, View view, Game game) {
        this.instance = this;
        this.setModel(model);
        this.setView(view);
        this.setGame(game);
        this.setTurn("white");
        this.setSelectedSquare(null);
        model.addActionListeners(this);
        view.addActionListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        System.out.println(action);
        
        switch (action) {
            case "square":
                isClicked((Square) e.getSource());
                break;
            case "restart":
                boolean restarted = view.promptRestart();
                if (restarted == true) {
                    game.sendPacket(null, true, false, false, false);
                }
                break;
            case "undo":
                //Nur den letzten Zug bevor der Gegner gespielt hat
                if ((!this.getTurn().equals(getGame().getConnectable().getColor()))) {
                    boolean undo = view.promptUndo();
                    if (undo == true) {
                        if (model.undo()) {
                            deselect(getSelectedSquare());
                            switchTurn();
                            game.sendPacket(null, false, false, true, false);
                        }
                    }
                }
                break;
            case "exit":
                boolean exit = view.promptExit();
                if (exit == true) {
                    game.sendPacket(null, false, false, false, true);
                    view.close();
                }
                break;
        }
    }

    public void resetBoard() {
        this.setModel(new Model(model.getWhiteScore(), model.getBlackScore()));
        this.setTurn("white");
        view.changeCurColor("white");
        this.setSelectedSquare(null);
        model.addActionListeners(this);
        view.resetBoard(getModel().getBoard());
    }

    public void isClicked(Square square) {
        // Select a square
        if (getSelectedSquare() == null) {
            if (square.getBesetzt_von() != null) {
                if (checkColorTurn(square)) {
                    select(square);
                }
            }
        } else {
            // Deselect
            if (getSelectedSquare() == square) {
                deselect(square);
            }
            // Make move
            else if (square.isHighlighted()) {
                game.sendPacket(new Command(selectedSquare.getPosition().getLocation(), square.getPosition().getLocation()), false, false, false, false);
                move(selectedSquare.getPosition(), square.getPosition());

                if (getSelectedSquare() != null) {
                    deselect(getSelectedSquare());
                }
            }
        }
    }

    public void select(Square square) {
        setSelectedSquare(square);
        square.setBackground(Color.green);

        List<Point> moves = square.getBesetzt_von().getFilteredMoves();

        for (Point p : moves) {
            model.getBoard().getSquare(p).setBackground(Color.green);
            model.getBoard().getSquare(p).setHighlighted(true);
        }
    }

    public void deselect(Square square) {
        if (square == null) {
            return;
        }

        square.setBackground(square.getOrigColor());
        setSelectedSquare(null);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                model.getBoard().getSquare(new Point(i, j)).setBackground(model.getBoard().getSquare(new Point(i, j)).getOrigColor());
                model.getBoard().getSquare(new Point(i, j)).setHighlighted(false);
            }
        }
    }

    public void move(Point from, Point to) {
        model.move(from, to);
        switchTurn();
        showPopup(getTurn());
    }

    public void showPopup(String color) {
        if (model.getBoard().isSchachMatt(color)) {
            view.notifySchachMatt(color);
            resetBoard();
            view.resetBoard(getModel().getBoard());
            return;
        }

        if (model.getBoard().isPatt(color)) {
            view.notifyUnentschieden();
            resetBoard();
            view.resetBoard(getModel().getBoard());
            return;
        }

        if (model.getBoard().isSchach(color)) {
            view.notifySchach(color);
            return;
        }
    }

    public void switchTurn() {
        if (this.getTurn() == "white") {
            this.setTurn("black");
            view.changeCurColor("black");
        } else if (this.getTurn() == "black") {
            this.setTurn("white");
            view.changeCurColor("white");
        }
    }

    public boolean checkColorTurn(Square test) {
        if ((test.getBesetzt_von().getColor().equals(this.getTurn())) && (this.getTurn().equals(getGame().getConnectable().getColor()))) {
            return true;
        }
        return false;
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

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public Square getSelectedSquare() {
        return selectedSquare;
    }

    public void setSelectedSquare(Square selectedSquare) {
        this.selectedSquare = selectedSquare;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
