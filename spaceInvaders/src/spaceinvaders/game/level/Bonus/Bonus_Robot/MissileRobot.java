/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.Bonus.Bonus_Robot;

import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class MissileRobot extends BoxGameItem {

    public MissileRobot(Game g, String nom, int x, int y) {
        super(g, nom, x, y);
    }

    @Override
    public void collideEffect(GameItem gi) {
       if(!(gi == null)){
            if ((gi.getItemType() == "Alpha") || (gi.getItemType() == "Octopus") || (gi.getItemType() == "Mothership") || (gi.getItemType() == "Missile Alien") || (gi.getItemType() == "Soucoupe")||(gi.getItemType() == "Bouclier") ) {
                super.getGame().remove(this);        
            }
        }
    }

    @Override
    public String getItemType() {
        return "Missile Robot";
    }

    @Override
    public void evolve(long l) {
        this.moveXY(0, -20);
        
        if (getBottom() < 20){
            super.getGame().remove(this);
        }  
    }
    
}
