package spaceinvaders.game.level.Score;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class Chiffre extends GameItem {

    public Chiffre(Game g, String name, double _x, double _y) {
        super(g, name, _x, _y);
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
        return "Chiffre";
    }

    @Override
    public void evolve(long l) {
        
    }
    
}
