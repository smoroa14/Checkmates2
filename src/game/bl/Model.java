package game.bl;

import game.beans.Command;
import game.beans.Bauer;
import game.beans.Piece;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Model {

    private Board board;
    private int whiteScore;
    private int blackScore;
    private Stack<Command> commands;

    public Model() {
        setWhiteScore(0);
        setBlackScore(0);
        setBoard(new Board());
        setCommands(new Stack<Command>());
    }

    public Model(int whiteScore, int blackScore) {
        setWhiteScore(whiteScore);
        setBlackScore(blackScore);
        setBoard(new Board());
        setCommands(new Stack<Command>());
    }

    public void addActionListeners(ActionListener a) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                getBoard().chessBoard[x][y].setActionCommand("square");
                getBoard().chessBoard[x][y].addActionListener(a);
            }
        }
    }

    public boolean undo() {
        if (commands.size() > 0) {
            Command commandToUndo = commands.pop();
            Piece vonPiece = commandToUndo.getVonPiece();
            Piece nachPiece = commandToUndo.getNachPiece();
            Point von = commandToUndo.getVon();
            Point nach = commandToUndo.getNach();

            Square fromSquare = getBoard().getSquare(von);
            Square toSquare = getBoard().getSquare(nach);

            fromSquare.setBesetzt_von(vonPiece);
            fromSquare.getBesetzt_von().setSteht_auf(fromSquare);
            fromSquare.setIcon(vonPiece.getIcon());
            if (fromSquare.getBesetzt_von().getClass() == Bauer.class) {
                if (fromSquare.getBesetzt_von().getColor() == "white") {
                    if (fromSquare.getPosition().y == 6) {
                        fromSquare.getBesetzt_von().setMoved(false);
                    }
                }
                if (fromSquare.getBesetzt_von().getColor() == "black") {
                    if (fromSquare.getPosition().y == 1) {
                        fromSquare.getBesetzt_von().setMoved(false);
                    }
                }
            }

            toSquare.setBesetzt_von(nachPiece);
            if (nachPiece != null) {
                toSquare.getBesetzt_von().setSteht_auf(toSquare);
                toSquare.setIcon(nachPiece.getIcon());
            } else {
                toSquare.setIcon(null);
            }
            return true;
        }
        return false;
    }

    public void move(Point von, Point nach) {
        Piece vonPiece = getBoard().getSquare(von).getBesetzt_von();
        Piece nachPiece = getBoard().getSquare(nach).getBesetzt_von();
        Command command = new Command(vonPiece, nachPiece, new Point(von), new Point(nach));
        board.move(von, nach);
        commands.push(command);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(int whiteScore) {
        this.whiteScore = whiteScore;
    }

    public int getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(int blackScore) {
        this.blackScore = blackScore;
    }

    public Stack<Command> getCommands() {
        return commands;
    }

    public void setCommands(Stack<Command> commands) {
        this.commands = commands;
    }
}
