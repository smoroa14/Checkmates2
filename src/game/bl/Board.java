package game.bl;

import bl.CurrentUser;
import database.DB_Access;
import game.beans.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class Board extends JPanel {

    public Square[][] chessBoard;
    public String farbe;
    public String[] deck;
    public String[] enemydeck;
    public String enemyUser;

    public Board() {
        String[] deck = DB_Access.getInstance().loadDeck(CurrentUser.player.getUsername());
        //String[] enemydeck = DB_Access.getInstance().loadDeck(enemyUser);

        chessBoard = new Square[8][8];
        this.deck = deck;
        setLayout(new GridLayout(8, 8));
        boardInit(null);
    }

    public Board(String farbe) {
        this.farbe = farbe;
        String[] deck = DB_Access.getInstance().loadDeck(CurrentUser.player.getUsername());
        //String[] enemydeck = DB_Access.getInstance().loadDeck(enemyUser);

        this.deck = deck;
        chessBoard = new Square[8][8];
        setLayout(new GridLayout(8, 8));
        boardInit(null);
    }

    public void changeColor() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x + y) % 2 == 0) {
                    this.chessBoard[x][y].setBackground(Color.white);
                    this.chessBoard[x][y].setOrigColor(Color.white);
                } else {
                    this.chessBoard[x][y].setBackground(Color.black);
                    this.chessBoard[x][y].setOrigColor(Color.black);
                }
            }
        }
    }

    public void boardInit(String[] dd) {
        removeAll();

        this.repaint();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {

                this.chessBoard[x][y] = new Square(this, new Point(x, y));
                if ((x + y) % 2 == 0) {
                    this.chessBoard[x][y].setBackground(Color.white);
                    this.chessBoard[x][y].setOrigColor(Color.white);
                } else {
                    this.chessBoard[x][y].setBackground(Color.black);
                    this.chessBoard[x][y].setOrigColor(Color.black);
                }
                Piece p = null;
                String color = null;

                if (y == 0 || y == 1) {
                    color = "black";
                } else if (y == 6 || y == 7) {
                    color = "white";
                }

                if (y == 1 || y == 6) {
                    p = new Bauer(chessBoard[x][y], color);
                } else {
                    p = addCustomBoard(x, y, p, color, dd);
                }

//                if (y == 0 || y == 7) {
//                    switch (x) {
//                        case 0:
//                        case 7:
//                            p = new Turm(chessBoard[x][y], color);
//                            break;
//                        case 1:
//                        case 6:
//                            p = new Springer(chessBoard[x][y], color);
//                            break;
//                        case 2:
//                        case 5:
//                            p = new Laufer(chessBoard[x][y], color);
//                            break;
//                        case 3:
//                            p = new Konigin(chessBoard[x][y], color);
//                            break;
//                        case 4:
//                            p = new Konig(chessBoard[x][y], color);
//                            break;
//                    }
//                }
                chessBoard[x][y].setBesetzt_von(p);
                add(chessBoard[x][y]);
                
                this.repaint();
            }
        }
        for (Component component : getComponents()) {
            System.out.println(((Square)component).getIcon());
            component.repaint();
        }
    }

    public void addEnemyBoard(String[] enemydeck) {
        boardInit(enemydeck);
    }

    public Piece addCustomBoard(int x, int y, Piece p, String color, String[] enemydeck) {

        if ((y == 7 && farbe.equals("white")) || (y == 0 && farbe.equals("black"))) {
            switch (deck[x]) {
                case "Dame":
                    p = new Konigin(chessBoard[x][y], color);
                    break;

                case "Bauer":
                    p = new Bauer(chessBoard[x][y], color);
                    break;

                case "Läufer":
                    p = new Laufer(chessBoard[x][y], color);
                    break;

                case "Springer":
                    p = new Springer(chessBoard[x][y], color);
                    break;

                case "Turm":
                    p = new Turm(chessBoard[x][y], color);
                    break;

                case "König":
                    p = new Konig(chessBoard[x][y], color);
                    break;

                case "Agent":
                    p = new Bauer(chessBoard[x][y], color);
                    break;

                case "Drache":
                    p = new Drache(chessBoard[x][y], color);
                    break;

                case "General":
                    p = new General(chessBoard[x][y], color);
                    break;
            }
        } else if (enemydeck != null && (y == 7 && farbe.equals("black")) || (y == 0 && farbe.equals("white"))) {
            // gegners deck
            try {
                switch (enemydeck[x]) {
                    case "Dame":
                        p = new Konigin(chessBoard[x][y], color);
                        break;

                    case "Bauer":
                        p = new Bauer(chessBoard[x][y], color);
                        break;

                    case "Läufer":
                        p = new Laufer(chessBoard[x][y], color);
                        break;

                    case "Springer":
                        p = new Springer(chessBoard[x][y], color);
                        break;

                    case "König":
                        p = new Konig(chessBoard[x][y], color);
                        break;

                    case "Turm":
                        p = new Turm(chessBoard[x][y], color);
                        break;

                    case "Agent":
                        p = new Bauer(chessBoard[x][y], color);
                        break;

                    case "Drache":
                        p = new Drache(chessBoard[x][y], color);
                        break;

                    case "General":
                        p = new General(chessBoard[x][y], color);
                        break;
                }
            } catch (Exception e) {
            }
        }
        return p;
    }

    public boolean move(Point from, Point to) {

        if (from.x <= 7 && from.x >= 0 && to.x <= 7 && to.y >= 0) {

            Square origin = getSquare(from);
            Square destination = getSquare(to);
            if (origin.getBesetzt_von() != null) {
                List<Point> possibleMoves = origin.getBesetzt_von().getPossibleMoves();
                if (possibleMoves.contains(to)) {
                    if (isKonigSchach(origin, destination) == false) {
                        destination.setBesetzt_von(origin.getBesetzt_von());
                        destination.getBesetzt_von().setSteht_auf(destination);
                        origin.setBesetzt_von(null);
                        origin.setIcon(null);
                        destination.getBesetzt_von().setMoved(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // king is in schach
    public boolean isKonigSchach(Square von, Square nach) {

        boolean check;
        Piece von_Piece = von.getBesetzt_von();
        Piece nach_Piece = nach.getBesetzt_von();
        String colorYou = von_Piece.getColor();

        Square helper = new Square(von.getBoard(), new Point(-1, -1));

        helper.setBesetzt_von(nach_Piece);
        nach.setBesetzt_von(von_Piece);
        von.setBesetzt_von(null);

        check = isSchach(colorYou);

        if (nach_Piece == null) {
            nach.setIcon(null);
        }
        von.setBesetzt_von(nach.getBesetzt_von());
        nach.setBesetzt_von(helper.getBesetzt_von());
        return check;
    }

    public Square getSquare(int x, int y) {
        if (x >= 0 && x <= 7 && y <= 7 && y >= 0) {
            return chessBoard[x][y];
        }
        return null;
    }

    public Square getSquare(Point point) {
        return getSquare(point.x, point.y);
    }

    // Farbe in Schach
    public boolean isSchach(String color) {
        Point konig_pos = GetKonigPos(color);
        String opponentColor = color.equals("white") ? "black" : "white";

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getSquare(x, y).getBesetzt_von();

                if (piece != null) {
                    if (piece.getColor().equals(opponentColor) && piece.getPossibleMoves().contains(konig_pos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasMoves(String color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getSquare(x, y).getBesetzt_von();

                if (piece != null) {
                    if (piece.getColor().equals(color)) {
                        List<Point> test = piece.getPossibleMoves();
                        if (!piece.getPossibleMoves().isEmpty()) {
                            Square from = piece.getSteht_auf();
                            for (Point potentialMoves : test) {
                                Square to = getSquare(potentialMoves);
                                if (isKonigSchach(from, to) == false) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public Point GetKonigPos(String color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getSquare(x, y).getBesetzt_von();
                if (piece != null && piece.getClass().equals(Konig.class) && piece.getColor().equals(color)) {
                    return new Point(x, y);
                }
            }
        }
        return new Point(-1, -1);
    }

    public boolean isPatt(String color) {
        boolean schach = isSchach(color);
        boolean hasMoves = hasMoves(color);

        if (schach == false) {
            if (hasMoves == false) {
                return true;
            }
        }
        return false;
    }

    public boolean isSchachMatt(String color) {
        boolean schach = isSchach(color);
        boolean hasMoves = hasMoves(color);

        if (schach == true) {
            if (hasMoves == false) {
                return true;
            }
        }
        return false;
    }
}
