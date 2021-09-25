/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitjeu;

import iut.Game;
import iut.GameItem;
import java.awt.Graphics;
import java.util.Random;

/**
 * Générateur de balle
 * Le générateur de balle ne s'affiche pas, ne touche rien. Il ne sert qu'à créer, de temps en temps, une balle.
 * @author aguidet
 */
public class GenerateurBalle extends GameItem {

    private long timeToCreate=1500; // durée avant de créer la prochaine balle
    
    public GenerateurBalle(Game g) {
        super(g, "",-1,-1);
    }

    @Override
    public void draw(Graphics g) throws Exception {
    
    }
    @Override
    public boolean isCollide(GameItem o) {
        return false;
    }

    @Override
    public void collideEffect(GameItem o) {
        
    }

    @Override
    public String getItemType() {
        return "GenerateurBalle";
    }

    @Override
    public void evolve(long dt) {
        timeToCreate -= dt;
        if(timeToCreate<=0){
            Balle b= new Balle(getGame(),getGame().getWidth()/2,50);
            getGame().addItem(b);
            Random r = new Random();
            timeToCreate = r.nextInt(10000)+2000; // entre 1 et 6 s
        }
    }
    
}
