/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.missile.missileAlien;

import iut.Game;

/**
 *
 * @author lm178867
 */
public class MissileOctopus extends MissileAlien {
    
    public MissileOctopus(Game g, String nom, int x, int y) {
        super(g, nom, x, y);
        
        //VITESSE DU MISSILE
        this.vitesse = 12.5d;
        
    }

    
    @Override
    public void evolve(long l) {
        super.moveXY(0, vitesse);
        if (getTop() > super.getGame().getHeight()){
            super.getGame().remove(this);
        } 
    }
    
    
    
}
