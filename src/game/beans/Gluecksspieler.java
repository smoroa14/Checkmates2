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
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author rober
 */
public class Gluecksspieler extends Piece {

    public static Random rand = new Random();
    private List<Point> allowedPoints;
    private static List<Point> pointss;

    public static void setMoves(List<Point> li)
    {
        pointss = li;
    }
    
    public static List<Point> getMoves() {

        if(pointss != null && pointss.size() > 0)return pointss;
        pointss = new LinkedList<>();
        List<Point> points = new LinkedList<>();
        
        int x = rand.nextInt(4);
        System.out.println(x + " -----------------------------");

        switch (x) {
            case 0:
                for (int i = 1; i < 8; i++) {
                    points.add(new Point(i, i));
                    points.add(new Point(-i, -i));
                    points.add(new Point(-i, i));
                    points.add(new Point(i, -i));
                }
                break;
            case 1:
                for (int i = 1; i < 8; i++) {
                    points.add(new Point(i, 0));
                    points.add(new Point(-i, 0));
                    points.add(new Point(0, i));
                    points.add(new Point(0, -i));
                }
                break;
            case 2:
                points.add(new Point(1, 0));
                points.add(new Point(-1, 0));
                points.add(new Point(0, 1));
                points.add(new Point(0, -1));
                break;
            case 3:
                points.add(new Point(1, 1));
                points.add(new Point(-1, -1));
                points.add(new Point(-1, 1));
                points.add(new Point(1, -1));
                break;
        }
        for (Point move : points) {
            System.out.println("x: " + move.x + " y: " + move.y);
        }
        pointss = points;
        return points;
    }

    public Gluecksspieler(Square occupying, String color) {
        super(occupying, color);

        if (getColor().equals("white")) {
            this.icon = new ImageIcon("images/whiteMerch.png");
        } else if (getColor().equals("black")) {
            this.icon = new ImageIcon("images/blackMerch.png");
        }
        allowedPoints = getMoves();
    }

    @Override
    public List<Point> getPossibleMoves() {
        List<Point> moves = new LinkedList<>();

        Point pos = new Point(steht_auf.getPosition());
        int startx = pos.x;
        int starty = pos.y;

        for (Point move : pointss) {
            if ((pos.x + move.x >= 0) && (pos.x + move.x <= 7)
                    && (pos.y + move.y >= 0) && (pos.y + move.y <= 7)) {
                if (steht_auf.getBoard().getSquare(pos.x + move.x, pos.y + move.y).getBesetzt_von() == null
                        || !steht_auf.getBoard().getSquare(pos.x + move.x, pos.y + move.y).getBesetzt_von().getColor().equals(getColor())) {
                    moves.add(new Point(pos.x + move.x, pos.y + move.y));
                }
            }
        }

        return moves;
    }
}
