package game.beans;

import game.bl.Square;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

public final class Konig extends Piece {

    public Konig(Square occupying, String color) {
        super(occupying, color);

        if (getColor().equals("white")) {
            this.icon = new ImageIcon("images/whitekonig.png");
        } else if (getColor().equals("black")) {
            this.icon = new ImageIcon("images/blackkonig.png");
        }
    }

    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new LinkedList<>();

        Point pos = steht_auf.getPosition();

        getHorizRight(moves, pos, 1);
        getHorizLeft(moves, pos, 1);
        getVertUp(moves, pos, 1);
        getVertDown(moves, pos, 1);
        getDiagUpRight(moves, pos, 1);
        getDiagDownRight(moves, pos, 1);
        getDiagUpLeft(moves, pos, 1);
        getDiagDownLeft(moves, pos, 1);

        return moves;
    }
}
