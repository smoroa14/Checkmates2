package game.beans;

import game.bl.Square;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

public final class Laufer extends Piece {

    public Laufer(Square occupying, String color) {
        super(occupying, color);

        if (getColor().equals("white")) {
            this.icon = new ImageIcon("images/whitelaufer.png");
        } else if (getColor().equals("black")) {
            this.icon = new ImageIcon("images/blacklaufer.png");
        }
    }

    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new LinkedList<>();

        Point pos = steht_auf.getPosition();

        int startx = pos.x;
        int starty = pos.y;

        getDiagUpRight(moves, pos, 10);
        getDiagDownRight(moves, pos, 10);
        getDiagUpLeft(moves, pos, 10);
        getDiagDownLeft(moves, pos, 10);

        pos.x = startx;
        pos.y = starty;
        return moves;
    }
}
