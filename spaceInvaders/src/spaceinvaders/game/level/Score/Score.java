package spaceinvaders.game.level.Score;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import iut.Game;
import spaceinvaders.game.level.Memory;

/**
 *
 * @author el711240
 */
public class Score{
    private int value;
    private Chiffre[] chiffres;
    private final Memory mem;

    public Score(Memory mem, Game g) {
        this.value = 0;
        this.mem = mem;
        chiffres = new Chiffre[9];
        int y = g.getWidth()-50;
        for(int i =0; i<9; i++){
            chiffres[i] = new Chiffre(g, "Score_0", y, g.getHeight()-40);
            g.addItem(chiffres[i]);
            y -= chiffres[i].getWidth();
        }
    }

    public int getValue() {
        return value;
    }
    
    public void earnPoints(int value){
        this.value += value;
        updateChiffres();
    }
    
    public void losePoints(int value){
        if(this.value-value > 0){
            this.value -= value;
        }else if(this.value > 0){
            this.value = 0;
        }
        updateChiffres();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public Chiffre[] getChiffres() {
        return chiffres;
    }

    
    
    private void updateChiffres(){
        String val = Integer.toString(value);
        for(int i = 0; i < val.length(); i++){
            chiffres[i].changeSprite("Score_"+Character.getNumericValue(val.charAt(val.length()-i-1)));
        }
    }
}
