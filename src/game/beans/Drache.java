/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.beans;

import game.bl.Square;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Nico
 */
public class Drache extends Piece {

    public Drache(Square steht_auf, String color) {
        super(steht_auf, color);
        
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

        if (pos.x - 1 >= 0 && pos.y - 2 >= 0) {
            if (steht_auf.getBoard().getSquare(pos.x - 1, pos.y - 2).getBesetzt_von() != null
                    && !steht_auf.getBoard().getSquare(pos.x - 1, pos.y - 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x - 1, pos.y - 2));
            }
        }
        //Atacke nach rechts
        System.out.println(pos.x + " - " + pos.y);
        if (pos.x + 1 <= 7 && pos.y - 2 >= 0) {
            if (steht_auf.
                    getBoard().
                    getSquare(pos.x + 1, pos.y - 2).
                    getBesetzt_von() != null
                    && !steht_auf.getBoard().getSquare(pos.x + 1, pos.y - 2).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(pos.x + 1, pos.y - 2));
            }
        }
        
        
        getVertUp(moves, pos, 2);
        
        
        pos.x = startx;
        pos.y = starty;
        return moves;
    }

}
