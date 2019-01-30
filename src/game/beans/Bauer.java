package game.beans;

import game.bl.Square;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

public final class Bauer extends Piece {

    public Bauer(Square occupying, String color) {
        super(occupying, color);
        setMoved(false);
        if (getColor().equals("white")) {
            this.icon = new ImageIcon("images/whitebauer.png");
        } else if (getColor().equals("black")) {
            this.icon = new ImageIcon("images/blackbauer.png");
        }
    }

    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new LinkedList<>();

        Point pos = new Point(steht_auf.getPosition());

        if (getColor().equals("black")) {
            if (pos.y + 1 <= 7) {
                if (steht_auf.getBoard().getSquare(pos.x, pos.y + 1).getBesetzt_von() == null) {
                    moves.add(new Point(pos.x, pos.y + 1));
                    // 2 mal nach vorne
                    if (this.isMoved() == false && pos.y + 2 <= 7) {
                        if (steht_auf.getBoard().getSquare(pos.x, pos.y + 2).getBesetzt_von() == null) {
                            moves.add(new Point(pos.x, pos.y + 2));
                        }
                    }
                }
            }
            // Atacke nach links
            if (pos.x - 1 >= 0 && pos.y + 1 <= 7) {
                if (steht_auf.getBoard().getSquare(pos.x - 1, pos.y + 1).getBesetzt_von() != null
                        && !steht_auf.getBoard().getSquare(pos.x - 1, pos.y + 1).getBesetzt_von().getColor().equals(getColor())) {
                    moves.add(new Point(pos.x - 1, pos.y + 1));
                }
            }
            //Atacke nach rechts
            if (pos.x + 1 <= 7 && pos.y + 1 <= 7) {
                if (steht_auf.getBoard().getSquare(pos.x + 1, pos.y + 1).getBesetzt_von() != null
                        && !steht_auf.getBoard().getSquare(pos.x + 1, pos.y + 1).getBesetzt_von().getColor().equals(getColor())) {
                    moves.add(new Point(pos.x + 1, pos.y + 1));
                }
            }
        } else if (getColor().equals("white")) {
            if (pos.y - 1 >= 0) {
                if (steht_auf.getBoard().getSquare(pos.x, pos.y - 1).getBesetzt_von() == null) {
                    moves.add(new Point(pos.x, pos.y - 1));
                    if (this.isMoved() == false && pos.y - 2 >= 0
                            && steht_auf.getBoard().getSquare(pos.x, pos.y - 2).getBesetzt_von() == null) {
                        moves.add(new Point(pos.x, pos.y - 2));
                    }
                }
            }
            // Atacke nach links
            if (pos.x - 1 >= 0 && pos.y - 1 >= 0) {
                if (steht_auf.getBoard().getSquare(pos.x - 1, pos.y - 1).getBesetzt_von() != null
                        && !steht_auf.getBoard().getSquare(pos.x - 1, pos.y - 1).getBesetzt_von().getColor().equals(getColor())) {
                    moves.add(new Point(pos.x - 1, pos.y - 1));
                }
            }
            //Atacke nach rechts
            if (pos.x + 1 <= 7 && pos.y - 1 >= 0) {
                if (steht_auf.getBoard().getSquare(pos.x + 1, pos.y - 1).getBesetzt_von() != null
                        && !steht_auf.getBoard().getSquare(pos.x + 1, pos.y - 1).getBesetzt_von().getColor().equals(getColor())) {
                    moves.add(new Point(pos.x + 1, pos.y - 1));
                }
            }
        }
        return moves;
    }

}
