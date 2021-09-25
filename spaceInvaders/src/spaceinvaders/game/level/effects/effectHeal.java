/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.effects;

import iut.Game;
import iut.GameItem;
import java.util.ArrayList;
import spaceinvaders.game.level.Level;

/**
 *
 * @author lucas
 */
public class effectHeal extends GameItem {
   private ArrayList<String> SpriteEffect;
    private int etat; 
    private long spriteClock;
    
    public effectHeal(Game g, String name, double _x, double _y) {
        super(g, name, _x, _y);
        initialisationSprite();
        
        this.etat = 0;
        this.spriteClock = 0;
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
        return "heal effect";
    }

    @Override
    public void evolve(long l) {
        //MOUVEMENT DU SPRITE
        this.spriteClock += l;
        if(spriteClock >= 20){
            changementSprite();
            this.spriteClock = 0;
        }
        
        int deltaPosX = Level.getJoueur().getMiddleX() - this.getMiddleX();
        int deltaPosY = Level.getJoueur().getMiddleY() - this.getMiddleY()-20;
        moveXY(deltaPosX,deltaPosY);
    
    }
    
    
    
    public void initialisationSprite(){
        this.SpriteEffect = new ArrayList<>();
        for (int i = 0 ; i<20 ; i++ ){
            this.SpriteEffect.add("heal_effect"+i);
        }
        
    }   
    
    public void changementSprite(){
        this.changeSprite(SpriteEffect.get(this.etat));
        {
            if (this.etat == 19){
                this.getGame().remove(this);
            }
            else{
                this.etat +=1;
            }
        }
    }  
}