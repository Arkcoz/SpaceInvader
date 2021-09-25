/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.Bonus.Bonus_Armure;

import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;
import java.util.ArrayList;
import spaceinvaders.game.level.Level;
import spaceinvaders.game.level.player.Player;

/**
 *
 * @author lucas
 */
public class Armure extends BoxGameItem {

    private int etat; 
    private long spriteClock;
    private long liveClock;
    private ArrayList<String> SpriteArmure;
    private int X;
    private int Y;
    private Player joueur;
    private boolean bientotFini;
    
    
    public Armure(Game g, String nom, int x, int y, Player joueur) {
        super(g, nom, x, y);
        initialisationSprite();
        this.etat = 0;
        this.spriteClock = 0;
        this.liveClock = 0;
        this.joueur = joueur;
        this.bientotFini = false;

        
    }

    @Override
    public void collideEffect(GameItem gi) {
        
    }

    @Override
    public String getItemType() {
        return "Armure";
    }

    @Override
    public void evolve(long l) {
        //MOUVEMENT DU SPRITE
        this.spriteClock += l;
        if(spriteClock >= 20){
            changementSprite();
            this.spriteClock = 0;
        }
        
        //TEMPS DE VIE (5 secondes)
        this.liveClock += l; 
        
        if (liveClock >= 6000){
            this.bientotFini = true;
        }
        if(liveClock >=7000){
            super.getGame().remove(this);
        }
        
        int deltaPosX = Level.getJoueur().getMiddleX() - this.getMiddleX()-3;
        int deltaPosY = Level.getJoueur().getMiddleY() - this.getMiddleY()-3;
        moveXY(deltaPosX,deltaPosY);
        
        
    }
    
    public void changementSprite(){
        this.changeSprite(SpriteArmure.get(this.etat));
        if (bientotFini){
            switchEtat();
        }
        else
        {
            if (this.etat == 38){
                this.etat = 0;
            }
            else{
                this.etat +=1;
            }
        }
        
    }
    
    public void initialisationSprite(){
        this.SpriteArmure = new ArrayList<>();
        for (int i = 0 ; i<39 ; i++ ){
            this.SpriteArmure.add("circle-"+i);
        }
        
        //Ajout de l'image transparent
        this.SpriteArmure.add("armureTransparente");
        
    }    
    
    private void switchEtat() {
        if(this.etat == 39){
            etat = 0;
        }else{
            etat = 39;
        }
    }
    
    
}
