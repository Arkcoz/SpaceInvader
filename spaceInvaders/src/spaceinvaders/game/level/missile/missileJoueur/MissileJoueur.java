/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.missile.missileJoueur;

import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public abstract class MissileJoueur extends BoxGameItem{

    
    public MissileJoueur(Game g, String nom, int x, int y) {
        super(g, nom, x, y);
        
    }
    @Override
    public String getItemType() {
        return "Missile Joueur";
    }

    @Override
    public void collideEffect(GameItem gi) {
        if(!(gi == null)){
            if ((gi.getItemType() == "Alpha") || (gi.getItemType() == "Octopus") || (gi.getItemType() == "Mothership") || (gi.getItemType() == "Missile Alien") ||(gi.getItemType() == "Bouclier") ) {
                super.getGame().remove(this);        
            }
        }
    }
}
