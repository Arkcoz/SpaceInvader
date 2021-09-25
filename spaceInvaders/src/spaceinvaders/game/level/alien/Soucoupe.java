/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.alien;

import iut.Game;
import iut.GameItem;
import spaceinvaders.game.level.Bonus.BonusFast;
import spaceinvaders.game.level.Bonus.BonusHeal;
import spaceinvaders.game.level.Bonus.BonusSlow;
import spaceinvaders.game.level.Bonus.BonusWeapon;
import spaceinvaders.game.level.Bonus.Bonus_Armure.BonusArmure;
import spaceinvaders.game.level.Bonus.Bonus_Robot.BonusRobot;
import spaceinvaders.game.level.Memory;

/**
 *
 * @author lucas
 */
public class Soucoupe extends Alien{

    public Soucoupe(Memory memory, Game g, int x, int y) {
        super(memory, g, "Soucoupe", x, y);
    }


    @Override
    public void collideEffect(GameItem gi) {
        if (("Missile Joueur".equals(gi.getItemType()))||("Missile Robot".equals(gi.getItemType()))){
            dropBonus(this.getMiddleX(),this.getMiddleY());
            this.getGame().remove(this);
            
        }
    }

    @Override
    public String getItemType() {
        return "Soucoupe";
    }

    @Override
    public void evolve(long l) {
        this.moveXY(+2, 0);
        if (this.getLeft()>this.getGame().getWidth()){
            this.getGame().remove(this);
        }
        
    }
    
    public void dropBonus(int x, int y){
        int nbRandom = 1 + (int)(Math.random() * ((6 - 1) + 1));
        
        //int nbRandom = 1;
        
        switch(nbRandom){
            case 1 :
                this.getGame().addItem(new BonusHeal(this.getGame(),x,y));
                break;
            case 2 :
                this.getGame().addItem(new BonusRobot(this.getGame(),x,y));
                break;
            case 3 : 
                this.getGame().addItem(new BonusArmure(this.getGame(),x,y));
                break;
            case 4 : 
                this.getGame().addItem(new BonusSlow(this.getGame(),x,y));
                break;    
            case 5 : 
                this.getGame().addItem(new BonusFast(this.getGame(),x,y));
                break;    
            case 6 :
                this.getGame().addItem(new BonusWeapon(this.getGame(),x,y));
                break;
        }
    }

    @Override
    public void explose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
