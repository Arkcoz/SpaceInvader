/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.missile.missileJoueur;

import spaceinvaders.game.level.missile.missileJoueur.MissileJoueur;
import iut.Audio;
import iut.Game;
import spaceinvaders.game.level.player.Player;

/**
 *
 * @author lucas
 */
public class MissileJ2 extends MissileJoueur{
    private Audio sonTir = new Audio("MissileJoueur1");
    
    public MissileJ2(Game g, int x, int y) {
        super(g, "PlayerMissile2", x, y);
        sonTir.start();
    }

    @Override
    public void evolve(long l) {
        super.moveXY(0, -20);
        
        if (getBottom() > super.getGame().getHeight()){
            super.getGame().remove(this);
        }
        
        
    }
    
}
