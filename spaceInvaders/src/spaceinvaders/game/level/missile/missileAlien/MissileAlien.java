/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.missile.missileAlien;

import iut.Audio;
import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author el711240
 */
public abstract class MissileAlien extends BoxGameItem{
    private Audio sonTir = new Audio("MissileJoueur1");
    protected double vitesse;

    public MissileAlien(Game g, String nom, int x, int y) {
        super(g, nom, x, y);
        sonTir.start();
    }

    @Override
    public void collideEffect(GameItem gi) {
        if ( (gi.getItemType() == "Joueur") || (gi.getItemType() == "Missile Joueur")||(gi.getItemType() == "Armure")||(gi.getItemType() == "Missile Robot")||(gi.getItemType() == "Robot") ||(gi.getItemType() == "Bouclier") ){
            super.getGame().remove(this);        
        }
    }

    @Override
    public String getItemType() {
        return "Missile Alien";
    }

    
    
    
    
}
