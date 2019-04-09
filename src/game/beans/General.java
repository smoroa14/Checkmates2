package game.beans;

import game.bl.Square;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

public final class General extends Piece {

    public General(Square occupying, String color) {
        super(occupying, color);

        if (getColor().equals("white")) {
            this.icon = new ImageIcon("images/whitekonigin.png");
        } else if (getColor().equals("black")) {
            this.icon = new ImageIcon("images/blackkonigin.png");
        }
    }

    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new LinkedList<>();

        Point pos = new Point(steht_auf.getPosition());
        int startx = pos.x;
        int starty = pos.y;

        if (pos.x - 2 >= 0 && pos.y - 2 >= 0) {
            if (steht_auf.getBoard().getSquare(pos.x - 2, pos.y - 2).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x - 2, pos.y - 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 2, pos.y - 2));
            }
        }

        //Atacke nach rechts
        if (pos.x - 1 >= 0 && pos.y - 2 >= 0) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x - 1, pos.y - 2).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x - 1, pos.y - 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 1, pos.y - 2));
            }
        }

        //Atacke nach rechts
        if (pos.x - 0 >= 0 && pos.y - 2 >= 0) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x - 0, pos.y - 2).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x - 0, pos.y - 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 0, pos.y - 2));
            }
        }

        //Atacke nach rechts
        if (pos.x + 1 <= 7 && pos.y - 2 >= 0) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x + 1, pos.y - 2).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x + 1, pos.y - 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 1, pos.y - 2));
            }
        }

        //Atacke nach rechts
        if (pos.x + 2 <= 7 && pos.y - 2 >= 0) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x + 2, pos.y - 2).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x + 2, pos.y - 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 2, pos.y - 2));
            }
        }

        //2.Reihe --------------------------------
        if (pos.x - 2 >= 0 && pos.y - 1 >= 0) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x - 2, pos.y - 1).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x - 2, pos.y - 1).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 2, pos.y - 1));
            }
        }

        //Atacke nach rechts
        if (pos.x + 2 <= 7 && pos.y - 1 >= 0) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x + 2, pos.y - 1).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x + 2, pos.y - 1).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 2, pos.y - 1));
            }
        }

        //3. Reihe --------------------------------
        if (pos.x - 2 >= 0 && pos.y - 0 >= 0) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x - 2, pos.y - 0).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x - 2, pos.y - 0).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 2, pos.y - 0));
            }
        }

        //Atacke nach rechts
        if (pos.x + 2 <= 7 && pos.y - 0 >= 0) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x + 2, pos.y - 0).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x + 2, pos.y - 0).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 2, pos.y - 0));
            }
        }

        // 4. Reihe -------------------------------
        System.out.println(pos.x + " - " + pos.y);
        if (pos.x - 2 >= 0 && pos.y + 1 <= 7) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x - 2, pos.y + 1).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x - 2, pos.y + 1).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 2, pos.y + 1));
            }
        }

        //Atacke nach rechts
        System.out.println(pos.x + " - " + pos.y);
        if (pos.x + 2 >= 0 && pos.y + 1 <= 7) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x + 2, pos.y + 1).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x + 2, pos.y + 1).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 2, pos.y + 1));
            }
        }

        // 5.Reihe ----------------------------
        if (pos.x - 2 >= 0 && pos.y + 2 <= 7) {
            if (steht_auf.getBoard().getSquare(pos.x - 2, pos.y + 2).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x - 2, pos.y + 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 2, pos.y + 2));
            }
        }
        //Atacke nach rechts
        if (pos.x - 1 >= 0 && pos.y + 2 <= 7) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x - 1, pos.y + 2).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x - 1, pos.y + 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 1, pos.y + 2));
            }
        }

        //Atacke nach rechts
        if (pos.x - 0 >= 0 && pos.y + 2 <= 7) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x - 0, pos.y + 2).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x - 0, pos.y + 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 0, pos.y + 2));
            }
        }

        //Atacke nach rechts
        if (pos.x + 1 <= 7 && pos.y + 2 <= 7) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x + 1, pos.y + 2).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x + 1, pos.y + 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 1, pos.y + 2));
            }
        }

        //Atacke nach rechts
        if (pos.x + 2 <= 7 && pos.y + 2 <= 7) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x + 2, pos.y + 2).
                    getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(pos.x + 2, pos.y + 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 2, pos.y + 2));
            }
        }

        return moves;
    }
}
