package game.beans;

import game.bl.Square;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public abstract class Piece {

    protected String color;
    protected Square steht_auf;
    protected boolean moved;
    protected ImageIcon icon;

    public Piece(Square steht_auf, String color) {
        this.setSteht_auf(steht_auf);
        this.setColor(color);
        this.moved = false;
    }

    public Piece(Piece copy) {
        this.setColor(copy.color);
        this.setMoved(copy.moved);
        this.setSteht_auf(copy.steht_auf);
    }

    public abstract List<Point> getPossibleMoves();

    public List<Point> getFilteredMoves() {
        List<Point> moves = this.getPossibleMoves();

        List<Point> forRemoval = new ArrayList<Point>();

        for (Point p : moves) {
            if (getSteht_auf().getBoard().isKonigSchach(this.getSteht_auf(), this.getSteht_auf().getBoard().getSquare(p)) == true) {
                forRemoval.add(p);
            }
        }
        for (Point p : forRemoval) {
            moves.remove(p);
        }
        return moves;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Square getSteht_auf() {
        return steht_auf;
    }

    public void setSteht_auf(Square steht_auf) {
        this.steht_auf = steht_auf;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    // .+
    protected void getVertDown(List<Point> moves, Point pos, int lenght) {
        Point posVec = (Point) pos.clone();
        posVec.y--;

        while (posVec.y >= 0 && lenght > 0) {
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(posVec.x, posVec.y));
                System.out.println("moved");
            }
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() != null) {
                break;
            }
            posVec.y--;
            lenght--;
        }
    }

    //.-
    protected void getVertUp(List<Point> moves, Point pos, int lenght) {
        Point posVec = (Point) pos.clone();
        posVec.y++;

        while (posVec.y <= 7 && lenght > 0) {
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(posVec.x, posVec.y));
            }
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() != null) {
                break;
            }
            posVec.y++;
            lenght--;
        }
    }

    // -.
    protected void getHorizLeft(List<Point> moves, Point pos, int lenght) {
        Point posVec = (Point) pos.clone();
        posVec.x--;

        while (posVec.x >= 0 && lenght > 0) {
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(posVec.x, posVec.y));
            }
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() != null) {
                break;
            }
            posVec.x--;
            lenght--;
        }
    }

    // +.
    protected void getHorizRight(List<Point> moves, Point pos, int lenght) {
        Point posVec = (Point) pos.clone();
        posVec.x++;

        while (posVec.x <= 7 && lenght > 0) {
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(posVec.x, posVec.y));
            }
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() != null) {
                break;
            }
            posVec.x++;
            lenght--;
        }
    }

    // -+
    protected void getDiagDownLeft(List<Point> moves, Point pos, int lenght) {
        Point posVec = (Point) pos.clone();
        posVec.x--;
        posVec.y--;

        while (posVec.x >= 0 && posVec.y >= 0 && lenght > 0) {
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(posVec.x, posVec.y));
            }
            if (steht_auf.getBoard().getSquare(posVec).getBesetzt_von() != null) {
                break;
            }

            posVec.x--;
            posVec.y--;
            lenght--;
        }
    }

    // --
    protected void getDiagUpLeft(List<Point> moves, Point pos, int lenght) {
        Point posVec = (Point) pos.clone();
        posVec.x--;
        posVec.y++;
        while (posVec.x >= 0 && posVec.y <= 7 && lenght > 0) {
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von().getColor().equals(getColor())) {

                moves.add(new Point(posVec.x, posVec.y));
            }
            if (steht_auf.getBoard().getSquare(posVec).getBesetzt_von() != null) {
                break;
            }

            posVec.x--;
            posVec.y++;
            lenght--;
        }
    }

    // ++
    protected void getDiagDownRight(List<Point> moves, Point pos, int lenght) {
        Point posVec = (Point) pos.clone();
        posVec.x++;
        posVec.y--;
        while (posVec.x <= 7 && posVec.y >= 0 && lenght > 0) {
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(posVec.x, posVec.y));
            }
            if (steht_auf.getBoard().getSquare(posVec).getBesetzt_von() != null) {
                break;
            }

            posVec.x++;
            posVec.y--;
            lenght--;
        }
    }

    // +-
    protected void getDiagUpRight(List<Point> moves, Point pos, int lenght) {
        Point posVec = (Point) pos.clone();
        posVec.x++;
        posVec.y++;
        while (posVec.x <= 7 && posVec.y <= 7 && lenght > 0) {
            if (steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von() == null
                    || !steht_auf.getBoard().getSquare(posVec.x, posVec.y).getBesetzt_von().getColor().equals(getColor())) {
                moves.add(new Point(posVec.x, posVec.y));
            }
            if (steht_auf.getBoard().getSquare(posVec).getBesetzt_von() != null) {
                break;
            }

            posVec.x++;
            posVec.y++;
            lenght--;
        }
    }

}
