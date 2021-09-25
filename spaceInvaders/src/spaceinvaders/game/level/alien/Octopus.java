/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.alien;

import iut.Game;
import iut.GameItem;
import spaceinvaders.game.level.Memory;
import spaceinvaders.game.level.effects.effectKill;
import spaceinvaders.game.level.missile.missileAlien.MissileOctopus;
import spaceinvaders.game.level.player.Player;

/**
 *
 * @author el711240
 */
public class Octopus extends Alien{

    
    public Octopus(Memory memory, Game g,  int x, int y) {
        super(memory, g, "Octopus0", x, y);
        POINTS = 100;
        
        
        //Tir 
        this.timeToShoot = 3600 + (int)(Math.random() * ((4000 - 3600) + 4000));;
    }


    @Override
    public void collideEffect(GameItem gi) {
        if((gi.getItemType() == "Missile Joueur")||(gi.getItemType() == "Missile Robot")||(gi.getItemType() == "Robot")||(gi.getItemType() == "Armure") ||(gi.getItemType() == "Bouclier")||((gi.getItemType() == "Player")&&(!((Player)gi).isInvicible()))){
            if (!touche && !super.isInvicible) {
                touche = true;
                super.getGame().remove(this);   
                this.jouerSonExplosion();
                this.getGame().addItem(new effectKill(this.getGame(),"transparent", this.getMiddleX()-50, this.getMiddleY()-100,"red"));
                super.memory.getScore().earnPoints((int)(POINTS * ((((1/Math.abs((float)super.getMiddleY()))*100))+1)));
            }
        }
    }

    @Override
    public String getItemType() {
        return "Octopus";
    }

       
    
    @Override
    public void explose() {
        super.getGame().remove(this);
    }

    
    public void shoot(){
        MissileOctopus missile = new MissileOctopus(this.getGame(), "OctopusMissile", this.getMiddleX()-24, this.getTop());
        super.getGame().addItem(missile);
        
    }
    
    @Override
    public void evolve(long l) {
        this.shootClock += l;
        if(shootClock >= timeToShoot){
            shoot();
            this.shootClock =0;
        }
        if(super.getBottom() > 0){
            super.isInvicible = false;
        }
    }
    
    
}
