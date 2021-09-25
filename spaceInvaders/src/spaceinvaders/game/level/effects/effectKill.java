/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.effects;

import iut.Game;
import iut.GameItem;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class effectKill extends GameItem{

    private ArrayList<String> SpriteEffect;
    private int etat; 
    private long spriteClock;
    
    public effectKill(Game g, String name, double _x, double _y, String couleur) {
        super(g, name, _x, _y);
        initialisationSprite(couleur);
        
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
        return "kill effect";
    }

    @Override
    public void evolve(long l) {
        //MOUVEMENT DU SPRITE
        this.spriteClock += l;
        if(spriteClock >= 20){
            changementSprite();
            this.spriteClock = 0;
        }
    }
    
    public void initialisationSprite(String couleur){
        this.SpriteEffect = new ArrayList<>();
        for (int i = 0 ; i<25 ; i++ ){
            this.SpriteEffect.add("kill_effect_"+couleur+i);
        }
        
    }   
    
    public void changementSprite(){
        this.changeSprite(SpriteEffect.get(this.etat));
        {
            if (this.etat == 24){
                this.getGame().remove(this);
            }
            else{
                this.etat +=1;
            }
        }
    }  
}
