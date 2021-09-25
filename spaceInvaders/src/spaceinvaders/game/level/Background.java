/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level;

import iut.Game;
import iut.GameItem;

/**
 *
 * @author Eileen
 */
public class Background extends GameItem{
    private int speed;
    private int compteur =0;
            
    public Background(Game g, String name, double _x, double _y, int speed) {
        super(g, name, _x, _y);
        this.speed = speed;
        super.moveXY(0, - (super.getHeight()-super.getGame().getHeight()));
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
        return "Background";
    }

    @Override
    public void evolve(long l) {
        super.moveXY(0, speed);
        compteur+= 1*speed;
        
        if(super.getTop() == 0){
            super.moveXY(0, -(super.getHeight()-super.getGame().getHeight()));
            compteur = 0;
        }
    }
    
}
