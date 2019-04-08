/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.beans;

import java.io.Serializable;

/**
 *
 * @author Robert
 */
public class JoiningParameter implements Serializable{
    private String password;
    private String[] deck;

    public JoiningParameter(String password, String[] deck) {
        this.password = password;
        this.deck = deck;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getDeck() {
        return deck;
    }

    public void setDeck(String[] deck) {
        this.deck = deck;
    }
    
    
}
