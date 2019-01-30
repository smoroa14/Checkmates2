package game.beans;

import game.bl.Square;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

public final class Turm extends Piece {

    public Turm(Square occupying, String color) {
        super(occupying, color);

        if (getColor().equals("white")) {
            this.icon = new ImageIcon("images/whiteturm.png");
        } else if (getColor().equals("black")) {
            this.icon = new ImageIcon("images/blackturm.png");
        }
    }

    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new LinkedList<>();

        Point pos = new Point(steht_auf.getPosition());

        int startx = pos.x;
        int starty = pos.y;

        getHorizRight(moves, pos, 10);
        getHorizLeft(moves, pos, 10);
        getVertUp(moves, pos, 10);
        getVertDown(moves, pos, 10);

        pos.x = startx;
        pos.y = starty;

        return moves;
    }
}
