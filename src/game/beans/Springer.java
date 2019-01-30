package game.beans;

import game.bl.Square;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

public final class Springer extends Piece {

    public Springer(Square occupying, String color) {
        super(occupying, color);

        if (getColor().equals("white")) {
            this.icon = new ImageIcon("images/whitespringer.png");
        } else if (getColor().equals("black")) {
            this.icon = new ImageIcon("images/blackspringer.png");
        }
    }

    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new LinkedList<>();

        Point pos = new Point(steht_auf.getPosition());

        // Rechts unten 1 ++
        if (pos.x + 1 <= 7 && pos.y + 2 <= 7) {
            if (steht_auf.getBoard().getSquare(pos.x + 1, pos.y + 2).getBesetzt_von() == null
                    || steht_auf.getBoard().getSquare(pos.x + 1, pos.y + 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 1, pos.y + 2));
            }
        }
        // Rechts unten 2 ++
        if (pos.x + 2 <= 7 && pos.y + 1 <= 7) {
            if (steht_auf.getBoard().getSquare(pos.x + 2, pos.y + 1).getBesetzt_von() == null
                    || steht_auf.getBoard().getSquare(pos.x + 2, pos.y + 1).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 2, pos.y + 1));
            }
        }
        // Links unten 1 -+
        if (pos.x - 1 >= 0 && pos.y + 2 <= 7) {
            if (steht_auf.getBoard().getSquare(pos.x - 1, pos.y + 2).getBesetzt_von() == null
                    || steht_auf.getBoard().getSquare(pos.x - 1, pos.y + 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 1, pos.y + 2));
            }
        }
        //Links unten 2 -+
        if (pos.x - 2 >= 0 && pos.y + 1 <= 7) {
            if (steht_auf.getBoard().getSquare(pos.x - 2, pos.y + 1).getBesetzt_von() == null
                    || steht_auf.getBoard().getSquare(pos.x - 2, pos.y + 1).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 2, pos.y + 1));
            }
        }
        //Rechts oben 1 +-
        if (pos.x + 1 <= 7 && pos.y - 2 >= 0) {
            if (steht_auf.getBoard().getSquare(pos.x + 1, pos.y - 2).getBesetzt_von() == null
                    || steht_auf.getBoard().getSquare(pos.x + 1, pos.y - 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 1, pos.y - 2));
            }
        }
        // Rechts Oben 2 +-
        if (pos.x + 2 <= 7 && pos.y - 1 >= 0) {
            if (steht_auf.getBoard().getSquare(pos.x + 2, pos.y - 1).getBesetzt_von() == null
                    || steht_auf.getBoard().getSquare(pos.x + 2, pos.y - 1).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 2, pos.y - 1));
            }
        }
        //Links oben 1 --
        if (pos.x - 1 >= 0 && pos.y - 2 >= 0) {
            if (steht_auf.getBoard().getSquare(pos.x - 1, pos.y - 2).getBesetzt_von() == null
                    || steht_auf.getBoard().getSquare(pos.x - 1, pos.y - 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 1, pos.y - 2));
            }
        }
        //Links oben 2 --
        if (pos.x - 2 >= 0 && pos.y - 1 >= 0) {
            if (steht_auf.getBoard().getSquare(pos.x - 2, pos.y - 1).getBesetzt_von() == null
                    || steht_auf.getBoard().getSquare(pos.x - 2, pos.y - 1).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 2, pos.y - 1));
            }
        }
        return moves;
    }

}
