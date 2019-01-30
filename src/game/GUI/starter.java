/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GUI;

/**
 *
 * @author rober
 */
public class starter {

    public static void startGame(String side, String ip) {
        if (side.equalsIgnoreCase("client")) {
            Runnable client = new Runnable() {
                @Override
                public void run() {
                    Client c = new Client();
                    c.runGame(ip);
                }
            };

            new Thread(client).start();
        } else {
            Runnable server = new Runnable() {
                @Override
                public void run() {
                    Server s = new Server();
                    s.runGame();
                }
            };
            new Thread(server).start();
        }
    }

    public static void main(String[] args) {
        //starter s = new starter();
        starter.startGame("server", "localhost");
        starter.startGame("client", "localhost");
    }
}
