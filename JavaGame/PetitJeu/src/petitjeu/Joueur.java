/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitjeu;

import iut.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Joueur du petitjeu
 * @author aguidet
 */
public class Joueur extends iut.BoxGameItem implements KeyListener {
    private int score=0;
    private boolean compte=false;
    private double time=0;
    private Score objScore;

    /**
     * Initialise le joueur
     * @param g le jeu 
     * @param x la position de départ
     * @param y la position de départ
     */
    public Joueur(Game g, int x, int y) {
        super(g, "joueur", x, y);
        this.setAngle(0);
        objScore = new Score(g,0);
        g.addItem(objScore);
    }

    @Override
    public void collideEffect(GameItem o) {
        if(!compte)
        {
            score++;        
            getGame().remove(objScore);
            objScore = new Score(getGame(),score);
            getGame().addItem(objScore);
            compte=true;
            time=0;
        }
        
    }

    @Override
    public void evolve(long dt) {
        if(compte)
        {
            time+=dt;
            if(time>500)
                compte=false;
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    private double angle=0;
    
    @Override
    public void keyPressed(KeyEvent e) {
        try{
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    if(this.getLeft()>0)
                        this.moveXY(-10, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    if(this.getRight()<this.getGame().getWidth())
                        this.moveXY(+10, 0);
                    break;     
                case KeyEvent.VK_UP:
                    angle += 5;
                    this.setAngle(angle);
                    break;
                case KeyEvent.VK_DOWN:
                    angle -= 5;
                    this.setAngle(angle);
                    break;
                case KeyEvent.VK_Q:                   
                    this.setScale(this.getScale()*1.1);
                    break;
                case KeyEvent.VK_W:
                    this.setScale(this.getScale()*0.9);
                    break;
            }
        }
        catch(Exception x){}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    @Override
    public String getItemType() {
        return "Joueur";
    }
    
}
