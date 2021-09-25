/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.Bonus;

import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public abstract class Bonus extends BoxGameItem {
    
    protected boolean touche; 
    private int vitesse;
    
    public Bonus(Game g, String nom, int x, int y) {
        super(g, nom, x, y);
        this.touche = false;
        this.vitesse = 5;
    }
    
    @Override
    public void evolve(long l) {
        this.moveXY(0, +vitesse);
        if (this.getTop()>this.getGame().getHeight()){
            this.getGame().remove(this);
        }
        
    }
    
    @Override
    public void collideEffect(GameItem gi) {
        if(gi.getItemType()== "Player"){
            if (!touche){
                this.getGame().remove(this);
                this.touche = true;
            }
        }
    }
}
