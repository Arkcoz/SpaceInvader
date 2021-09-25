/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitjeu;

import iut.GameItem;
import iut.Vector;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 * Représente un petit jeu simple
 * @author aguidet
 * @version 2020
 */
public class PetitJeu extends iut.Game {

    /**
     * @param args the command line arguments
     * Fonction principal (main)
     */
    public static void main(String[] args) {
        PetitJeu jeu = new PetitJeu();
        jeu.play();
    }

    public PetitJeu(){
        super(1024,768,"La baballe");
        GameItem.DRAW_HITBOX=true;
    }
    
    private Joueur j;
    
    @Override
    protected void createItems() {
        int y = this.getHeight()-120;
        j = new Joueur(this,500,y);
        this.addItem(j);        
        Balle b = new Balle(this,500,220);
        this.addItem(b);
        this.addItem(new GenerateurBalle(this));
    }

    @Override
    protected void drawBackground(Graphics g) {
          g.setColor(Color.BLACK);
          g.fillRect(00, 0, getWidth(), getHeight());
    }

    @Override
    protected void lost() {
        JOptionPane.showMessageDialog(this, "Vous avez perdu");
    }

    @Override
    protected void win() {
        JOptionPane.showMessageDialog(this, "Vous avez gagné");
    }

    @Override
    protected boolean isPlayerWin() {
       return j.getScore()>10;
    }

    @Override
    protected boolean isPlayerLost() {
       return false;
    }

    @Override
    public Vector getGravity() {
        return new Vector(); // no gravity in this game
    }
    
}
