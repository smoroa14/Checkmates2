package game.GUI;

import GUI.LoginGUI;
import GUI.MenuGUI;
import game.beans.Command;
import game.beans.Message;
import game.beans.Packet;
import game.bl.Board;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class View extends JFrame {

    private String currTurn;
    private Game game;
    private String side;

    private JPanel mainPanel;
    private JPanel infoBox;
    private JPanel turnBox;
    private JPanel boardPanel;
    private JPanel chatBox;

    private JScrollPane chatPane;
    private JTextArea chatArea;
    private JTextField chatEingabe;

    private JLabel turnTitle;
    private JLabel turn;
    private JLabel sideTitle;
    private JLabel sideText;

    private JMenuBar menuBar;
    private JMenu gameOptions;
    private JMenuItem restart;
    private JMenuItem undo;
    private JMenuItem exit;

    public View(Board board, Game game, String color) {
        setCurrTurn("white");
        boardPanel = board;
        setSide(color);
        createAndShowGUI();
        setGame(game);
        this.setTitle(MenuGUI.u.getUsername());
        this.setPreferredSize(new Dimension(950, 560));
        this.pack();

        
        changeCurColor("white");
    }

    public void createAndShowGUI() {

        createPanels();
        createMenu();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setSize(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(new Dimension(528, 446))))))))))))))));
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                closeWindow();
            }
        });
    }

    public void closeWindow() {
        boolean exit = promptExit();
        if (exit == true) {
            game.sendPacket(null, false, false, false, true);
            this.close();
        }
    }

    public void createMenu() {
        menuBar = new JMenuBar();

        gameOptions = new JMenu("Game Options");
        menuBar.add(gameOptions);

        restart = new JMenuItem("Restart");
        undo = new JMenuItem("Undo last move");
        exit = new JMenuItem("Exit Game");

        gameOptions.add(restart);
        gameOptions.add(undo);
        gameOptions.add(exit);

        this.setJMenuBar(menuBar);
    }

    public void createPanels() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        infoBox = new JPanel();
        infoBox.setSize(1100, 600);
        infoBox.setLayout(new BorderLayout());

        turnBox = new JPanel(new GridLayout(2, 1));
        JPanel pan = new JPanel(new GridLayout(2, 1));
        turnTitle = new JLabel("Current Turn: ");
        turn = new JLabel("" + getCurrTurn());
        pan.add(turnTitle);
        pan.add(turn);
        turnBox.add(pan);

        JPanel pan2 = new JPanel();
        sideTitle = new JLabel("Your side is: ");
        sideText = new JLabel("" + getSide());
        pan2.add(sideTitle);
        pan2.add(sideText);
        turnBox.add(pan2);

        chatBox = new JPanel(new BorderLayout(2, 1));
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatPane = new JScrollPane(chatArea);
        chatEingabe = new JTextField();
        chatEingabe.addActionListener((e) -> {
            onEnter();
        });
        chatBox.add(chatPane, BorderLayout.CENTER);
        chatBox.add(chatEingabe, BorderLayout.SOUTH);

        infoBox.add(turnBox, BorderLayout.NORTH);
        infoBox.add(chatBox, BorderLayout.CENTER);

        boardPanel.setPreferredSize(new Dimension(500, 500));
        boardPanel.setMaximumSize(new Dimension(500, 500));
        boardPanel.setMinimumSize(new Dimension(500, 500));
        boardPanel.setSize(new Dimension(500, 500));

        mainPanel.add(boardPanel, BorderLayout.WEST);
        mainPanel.add(infoBox, BorderLayout.CENTER);

        this.getContentPane().add(mainPanel);
    }

    public void notifySchachMatt(String turn) {
        JOptionPane.showMessageDialog(this, "Schachmatt! " + turn + " hat verloren!");
    }

    public void notifyUnentschieden() {
        JOptionPane.showMessageDialog(this, "Patt! -> Unentschieden!");
    }

    public void notifySchach(String turn) {
        JOptionPane.showMessageDialog(this, turn + " ist im Schach!");
    }

    public void changeCurColor(String color) {
        setCurrTurn(color);
        turn.setText("" + getCurrTurn());
        turn.setOpaque(true);
        if(color.equals("white"))
        {
            turn.setBackground(Color.WHITE);
            turn.setForeground(Color.BLACK);
        }else{
            turn.setBackground(Color.BLACK);
            turn.setForeground(Color.WHITE);
        }
    }

    public void addActionListeners(ActionListener a) {
        restart.addActionListener(a);
        restart.setActionCommand("restart");

        undo.addActionListener(a);
        undo.setActionCommand("undo");

        exit.addActionListener(a);
        exit.setActionCommand("exit");
    }

    public boolean promptRestart() {
        return JOptionPane.showConfirmDialog(this, "Möchten Sie das Spiel erneut starten?", "Restart", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public boolean promptUndo() {
        return JOptionPane.showConfirmDialog(this, "Möchten Sie den letzten Schritt rückgängig machen?", "Undo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public boolean promptExit() {
        return JOptionPane.showConfirmDialog(this, "Möchten Sie das Spiel wirklich beenden?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public void opponentQuit() {
        JOptionPane.showMessageDialog(this, "Der Gegner hat das Spiel verlassen.", "Gegner hat verlassen", JOptionPane.WARNING_MESSAGE);
    }

    public void resetBoard(Board boardReset) {
        mainPanel.remove(boardPanel);
        boardPanel = boardReset;
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        this.invalidate();
        this.validate();
    }

    public void close() {
        this.setVisible(false);
        this.dispose();
    }

    public String getCurrTurn() {
        return currTurn;
    }

    public void setCurrTurn(String currTurn) {
        this.currTurn = currTurn;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void onEnter() {
        if (!chatEingabe.getText().equals("")) {
            game.sendMessage(new Packet(null, false, false, false, false, new Message(MenuGUI.u.getUsername(), chatEingabe.getText())));
            chatEingabe.setText("");
        }
    }

    void addToChat(String string) {
        chatArea.append(string + "\n");
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {//Windows
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View(new Board(), new Game(), "white").setVisible(true);
            }
        });
    }

}
