/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Server.ChessServer;
import Server.Client_Frame;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico
 */
public class Server_Frame extends javax.swing.JFrame {

    /**
     * Creates new form Server_Frame
     */
    public Server_Frame() {
        initComponents();
        this.setSize(600, 600);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private ChessServer server = null;
    boolean started = false;
    boolean stopped = true;

    private MenuGUI menGUI;
    private MenuGUI menGUI1;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btStart = new javax.swing.JButton();
        btStop = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAusgabe = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btStart.setText("Start");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });
        jPanel1.add(btStart);

        btStop.setText("Stopp");
        btStop.setEnabled(false);
        btStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStopActionPerformed(evt);
            }
        });
        jPanel1.add(btStop);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jScrollPane1.setEnabled(false);

        taAusgabe.setColumns(20);
        taAusgabe.setRows(5);
        taAusgabe.setEnabled(false);
        jScrollPane1.setViewportView(taAusgabe);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        if (started == false) {
            server = new ChessServer(); //this
            server.startServer();
            writeLog("Server started");
            btStart.setEnabled(false);
            btStop.setEnabled(true);
            stopped = false;
            started = true;

//            menGUI = new MenuGUI();
//            menGUI.dohier("Nico Morningstar");
//            menGUI1 = new MenuGUI();
//            menGUI1.dohier("Michi der Idiot");
//            menGUI.setDefaultCloseOperation(HIDE_ON_CLOSE);
//            menGUI1.setDefaultCloseOperation(HIDE_ON_CLOSE);
//            menGUI.setVisible(true);
//            menGUI1.setVisible(true);

            Client_Frame cl1 = new Client_Frame();
            Client_Frame cl2 = new Client_Frame();
            
        }
    }//GEN-LAST:event_btStartActionPerformed

    private void btStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStopActionPerformed
        if (stopped == false) {
            try {
                server.DisconnectAll();
                server.stopServer();
            } catch (IOException ex) {
                Logger.getLogger(Server_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            writeLog("Server stopped");
            btStart.setEnabled(true);
            btStop.setEnabled(false);
            stopped = true;
            started = false;
            server = null;
        }
    }//GEN-LAST:event_btStopActionPerformed

    /**
     * @param args the command line arguments
     */
    public void writeLog(String logMsg) {
        LocalDateTime ld = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM HH:mm:ss");
        taAusgabe.append(ld.format(dtf) + ": " + logMsg + "\n");
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btStart;
    private javax.swing.JButton btStop;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taAusgabe;
    // End of variables declaration//GEN-END:variables
}
