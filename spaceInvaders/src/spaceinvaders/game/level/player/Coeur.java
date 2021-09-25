/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.player;

import iut.Game;
import iut.GameItem;

/**
 *
 * @author lm178867
 */
public class Coeur extends GameItem{

    boolean isEmpty;
    
    public Coeur(Game g, String name, double _x, double _y) {
        super(g, name, _x, _y);
        isEmpty = false;
    }

    @Override
    public boolean isCollide(GameItem gi) {
        return false;
    }

    @Override
    public void collideEffect(GameItem gi) {
        
    }

    @Override
    public String getItemType() {
       return "Coeur";
    }

    @Override
    public void evolve(long l) {
       
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    
}
