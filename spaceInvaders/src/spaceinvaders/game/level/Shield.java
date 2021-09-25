/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level;

import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;
import java.util.ArrayList;

/**
 *
 * @author el711240
 */
public class Shield extends BoxGameItem{

    private int etat;
    private ArrayList<String> SpriteShield;
    private final Memory memo;
    
    public Shield(Memory memo,Game g, int x, int y) {
        super(g, "bouclier_2", x, y);
        this.etat = 2;
        this.memo = memo;
        
        //Initialisation SpriteShield
        initialisationSprite();
        
        
    }

    @Override
    public void collideEffect(GameItem gi) {
        if ( (gi.getItemType() == "Alpha") || (gi.getItemType() == "Octopus") || (gi.getItemType() == "Mothership") || (gi.getItemType() == "Missile Alien")){
            if (this.etat <=0){
                this.getGame().remove(this);
            }else{
                this.etat -=1;
            }
        }
    }

    @Override
    public String getItemType() {
        return "Bouclier";
    }

    @Override
    public void evolve(long l) {
        this.changeSprite(SpriteShield.get(etat));
    }
    
    public void initialisationSprite(){
        SpriteShield = new ArrayList<>();
        SpriteShield.add(0,"bouclier_0");
        SpriteShield.add(1,"bouclier_1");
        SpriteShield.add(2,"bouclier_2");
             
    }
    
}
