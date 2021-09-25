/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level;

import iut.Game;
import spaceinvaders.game.level.Score.Chiffre;
import spaceinvaders.game.level.Score.Score;
import spaceinvaders.game.level.alien.VagueAlien;

/**
 *
 * @author Eileen
 */
public class Memory {
    private final Score score;
    private final Game game;
    private boolean isUpdated = true;
    private boolean slow;
    private boolean alienPassed;
    private VagueAlien vs;

    public Memory(Game g) {
        score = new Score(this, g);
        game = g;
        slow = false;
        alienPassed = false;
    }    

    public Score getScore() {
        return score;
    }

    public boolean isSlow() {
        return slow;
    }

    public void setSlow(boolean slow) {
        this.slow = slow;
    }
    
    public boolean isUpdated(){
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }
    
    public void parDessus(){
        for(Chiffre c : score.getChiffres()){
            game.remove(c);
        }
        
        for(Chiffre c : score.getChiffres()){
            game.addItem(c);
        }
        
    }

    public boolean isAlienPassed() {
        return alienPassed;
    }

    public void setAlienPassed(boolean alienPassed) {
        this.alienPassed = alienPassed;
    }
        
    public void addVagueAlien(VagueAlien vs){
        this.vs = vs;
    }
    
    public VagueAlien getVague(){
        return vs;
    }
}
