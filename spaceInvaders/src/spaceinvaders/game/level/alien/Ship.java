/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.alien;

import iut.Game;
import iut.GameItem;
import spaceinvaders.game.level.Memory;
import spaceinvaders.game.level.missile.missileAlien.MissileShip;

/**
 *
 * @author el711240
 */
public class Ship extends Alien{
    
    public Ship(Memory memory, Game g, String nom, int x, int y) {
        super(memory, g, nom, x, y);
    }

   

    @Override
    public String getItemType() {
       return "Ship";
    }

       
    
    @Override
    public void explose() {
        super.getGame().remove(this);
    }
    
    
    public void shoot(){
        MissileShip missile = new MissileShip(this.getGame(),"AlienMissile", this.getMiddleX()-24, this.getTop());
        super.getGame().addItem(missile);
        
    }
    
    @Override
    public void evolve(long l) {
        this.shootClock += l;
        if(shootClock >= timeToShoot){
            shoot();
            this.shootClock =0;
        }
    }

    @Override
    public void collideEffect(GameItem gi) {
        
        
    }
}
